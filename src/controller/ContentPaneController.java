package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTable;

import model.AlgorithmExecuter;
import model.Buffer;
import model.DebuggerConsole;
import model.Exceptions;
import model.Timer;
import model.Components.AlgorithmComponent;
import model.File.FileDefragger;
import model.File.FileSaver;
import model.File.ImageLoader;
import model.File.OpenHTMLFile;
import model.File.SaveFileWithCode;
import model.File.SoundPlayer;
import model.Memory.MemoryStorage;
import model.Memory.Variable;
import view.FlowChartContentPanel;
import view.Panel;

/**<p>
* This class is used to control the content pane ({@link Panel}) contained in the main {@link JFrame}, it controls all the actions
* and events from the user
* </p>
*/
public class ContentPaneController extends Controller implements Runnable{
	
	private static final String referenceType = "CP-CONTROLLER";
	private Panel panel;
	private Semaphore execute;
	private Semaphore wait;
	private Semaphore read;
	private Semaphore write;
	private Thread T;

	
	/**<p>
	* This method creates a new instance of the {@link ContentPaneController}
	* </p>
	* @param ALGORITHM_EXECUTER The {@link AlgorithmExecuter} instance that executes all the different components
	* @param TIMER The {@link Timer} instance that will coordinate the execution and handles the delay between all the exections
	* @param BUFFER The {@link Buffer} instance that handles all the outputs from the execution process
	* @param read The {@link Semaphore} that handles the reading process of the buffer
	* @param write The {@link Semaphore} that handles the writing process of the buffer
	* @param execute The {@link Semaphore} that handles the correct execution of the algorithm
	* @param wait The {@link Semaphore} that synchronizes the execution of the algorithm with the {@link Timer}
	* @param panel The {@link Panel} handled by this controller
	*/
	public ContentPaneController(AlgorithmExecuter ALGORITHM_EXECUTER, Timer TIMER, Buffer BUFFER, Semaphore execute, Semaphore wait, Semaphore read, Semaphore write, Panel panel) {
		super(ALGORITHM_EXECUTER, TIMER, BUFFER, MemoryStorage.getInstance());
		// TODO Auto-generated constructor stub
		this.panel = panel;
		this.execute = execute;
		this.wait = wait;
		this.read = read;
		this.write = write;
		panel.registerEvents(this);
		ALGORITHM_EXECUTER.setController(this);
		T = new Thread(this, "BUFFER_THREAD");
		T.start();	
		//REMOVE THIS TRY-CATCH BLOCK WHEN NOT TESTING~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//		try {
//			super.getMemory().initializeDefaultComponents();
////			updateTable();
//		} catch (Exceptions e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			try {
				DebuggerConsole.getInstance().printDefaultInfoLog(referenceType+"-THREAD_BUFFER", "Waiting for write semaphore...");
				write.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DebuggerConsole.getInstance().printDefaultInfoLog(referenceType+"-THREAD_BUFFER", "Got semaphore write.");
			panel.setOutputArea(super.buffer.read());
			read.release();
			DebuggerConsole.getInstance().printDefaultInfoLog(referenceType+"-THREAD_BUFFER", "Read semaphore released");
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch(e.getActionCommand()) {
			case "START":
				
				memory.setOnGoing(true);
				buffer.clear();
				
				DebuggerConsole.getInstance().printDefaultInfoLog(referenceType, "EXECUTE BUTTON GOT CLICKED=================");
				if(panel.getExecuteButtonStatus()) {
					//SE IL PROGRAMMA NON E' IN ESECUZIONE---------------------------------------------------------------------------
//					if(panel.isAutoRun()) {
						//SE IL PROGRAMMA E' IN MODALITA' AUTORUN-------------------------------------------------------
						int ms = panel.getMilliseconds();
//						if(ms==0) {
//							ms = 5;
//						}
						DebuggerConsole.getInstance().printDefaultInfoLog(referenceType, "User setted " + ms + "ms as the execution delay");
						try {
							panel.setExecuteButtonUsable(false);
							DebuggerConsole.getInstance().printDefaultInfoLog(referenceType, "Setting timer to " + ms + "ms");
							resetSempahores();
							super.timer.set(ms, panel.isAutoRun());
							AlgorithmComponent c = super.getMemory().getStartComponent();
							DebuggerConsole.getInstance().printDefaultInfoLog(referenceType, "Running the executer with start component: " + c.getClass().getSimpleName());
							super.algorithmExecuter.start(super.getMemory().getStartComponent());
						} catch (Exceptions customException) {
							JOptionPane.showMessageDialog(panel, customException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						} catch (Exception exception) {
							System.err.println(exception.getMessage());
						}
						//----------------------------------------------------------------------------------------------
//					}else{
						//SE IL PROGRAMMA E' IN MODALITA' MANUAL--------------------------------------------------------
						//----------------------------------------------------------------------------------------------
//					}
				}
				//IL PROGRAMMA E' ANCORA IN ESECUZIONE...
				break;
			case "NEXT":
				DebuggerConsole.getInstance().printDefaultInfoLog(referenceType, "Next button got clicked. Sending info to the timer");
				super.timer.nextButtonGotClicked = true;
				break;
			case "END":
				//check if the program is running
				if (super.algorithmExecuter.isRunning()) {
					
					memory.setOnGoing(false);
					memory.destroyVariables();
					algorithmExecuter.stop();
					panel.setExecuteButtonUsable(true);
					panel.toggleExecuteSelected();
					super.memory.resetExecutingStatusOfPanels();
				}
				
				break;
			case "SHOW_MENU":
				JButton optionsButton = (JButton)e.getSource();
				// Crea e mostra il menu a discesa quando si clicca sul pulsante
				
				ImageLoader imageLoader = new ImageLoader();
				
				ImageIcon saveIcon = imageLoader.getImageFrom("resources/save.png");
				saveIcon = imageLoader.scaleImage(saveIcon, 17, 17);
				
				ImageIcon clearIcon = imageLoader.getImageFrom("resources/clear.png");
				clearIcon = imageLoader.scaleImage(clearIcon, 17, 17);
				
				ImageIcon openIcon = imageLoader.getImageFrom("resources/open.png");
				openIcon = imageLoader.scaleImage(openIcon, 17, 17);
				
				ImageIcon helpIcon = imageLoader.getImageFrom("resources/help.png");
				helpIcon = imageLoader.scaleImage(helpIcon, 17, 17);
				
                JPopupMenu popupMenu = new JPopupMenu();
                JMenuItem saveMenuItem = new JMenuItem("Save...", saveIcon);
                JMenuItem clearAllMenuItem = new JMenuItem("Clear algorithm", clearIcon);
                JMenuItem openMenuItem = new JMenuItem("Open...", openIcon);
                JMenuItem helpMenuItem = new JMenuItem("Help", helpIcon);
                
                clearAllMenuItem.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						memory.reloadComponents();
						panel.getFlowChartPanel().updatePane(memory.getComponents());
						updateTable();
					}
				});
                
                // Aggiungi gli action listener per le voci di menu
                saveMenuItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    	FileSaver.saveToFile((JFrame)panel.getParent().getParent().getParent());
                    }
                });

                openMenuItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    	FileDefragger.openFile((JFrame)panel.getParent().getParent().getParent());
                    	memory.print();
                    	panel.getFlowChartPanel().updatePane(memory.getComponents());
                    }
                });

                helpMenuItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        OpenHTMLFile HTMLopener = new OpenHTMLFile("doc" + File.separator + "index.html");
                    }
                });
                
             // Aggiungi un sottomenu "Convert to code..."
                
                ImageIcon codeIcon = imageLoader.getImageFrom("resources/code.png");
                codeIcon = imageLoader.scaleImage(codeIcon, 17, 17);
                
                JMenu convertMenu = new JMenu("Convert to code...");
                JMenuItem javaMenuItem = new JMenuItem("Java");
                JMenuItem pythonMenuItem = new JMenuItem("Python");
                JMenuItem pseudoCodeMenuItem = new JMenuItem("PseudoCode");
                
                JMenuItem convertMenuItem = convertMenu;
                convertMenuItem.setIcon(codeIcon);
                
                javaMenuItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    	SaveFileWithCode.translateAndSaveCode((JFrame)panel.getParent().getParent().getParent(), "java");
                    }
                });

                pythonMenuItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    	SaveFileWithCode.translateAndSaveCode((JFrame)panel.getParent().getParent().getParent(), "python");
                    }
                });

                pseudoCodeMenuItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    	SaveFileWithCode.translateAndSaveCode((JFrame)panel.getParent().getParent().getParent(), "pseudocode");
                    }
                });

                convertMenu.add(javaMenuItem);
                convertMenu.add(pythonMenuItem);
                convertMenu.add(pseudoCodeMenuItem);

                popupMenu.add(clearAllMenuItem);
                popupMenu.addSeparator();
                popupMenu.add(saveMenuItem);
                popupMenu.add(openMenuItem);
                popupMenu.addSeparator(); // Aggiungi un separatore tra gli elementi principali e il sottomenu
                popupMenu.add(convertMenuItem); // Aggiungi il sottomenu "Convert to code..."
                popupMenu.addSeparator();
                popupMenu.add(helpMenuItem);

                popupMenu.show(optionsButton, 0, optionsButton.getHeight());
				break;
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if(e.getStateChange() == ItemEvent.SELECTED) {
			panel.setNextButtonUsable(false);
			panel.setMillisecondsUsable(true);
		} else if(e.getStateChange() == ItemEvent.DESELECTED) {
			panel.setNextButtonUsable(true);
			panel.setMillisecondsUsable(false);
		}
		
	}

	/**<p>
	* This method updates the table containing all the variables informations in the associated {@link Panel}
	* </p>
	* <p>
	* It creates a new {@link JTable} and updates the debug panel
	* </p>
	*/
	public void updateTable() {
		// TODO Auto-generated method stub
		DebuggerConsole.getInstance().printDefaultInfoLog(referenceType, "Updating table...");
		panel.setMemoryTableInDebugPanel(MemoryStorage.getInstance().createMemoryTable());
		DebuggerConsole.getInstance().printDefaultInfoLog(referenceType, "Table updated.");
	}
	
	/**<p>
	* This method calls the {@link MemoryStorage#destroyVariables()} from the memory
	* </p>
	* @see MemoryStorage
	*/
	public void deleteVariablesFromMemoryStorage() {
		MemoryStorage.getInstance().destroyVariables();
	}

	/**<p>
	* This method shows an error dialog with {@link JOptionPane} in the associated panel
	* </p>
	* <p>
	* Creates a {@link JOptionPane} containing an error message provided as a parameter and shows it in the panel
	* </p>
	* @param message The message that has to be shown
	*/
	public void showErrorDialog(String message) {
		//play sound
		SoundPlayer soundPlayer = new SoundPlayer();
		
		soundPlayer.playErrorSound();
		
		JOptionPane.showMessageDialog(panel, message, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	/**<p>
	* This method resets the {@link Semaphore}s to their initial status
	* </p>
	* <p>
	* This method resets the wait and execute semaphores to their initial status to guarantee the correct
	* execution of the algorithm after a first execution
	* </p>
	*/
	private void resetSempahores() {
	    // Ripristina i semafori allo stato iniziale
	    execute.release(); // Rilascia il semaforo EXECUTE_S
	    wait.drainPermits(); // Rimuove tutte le concessioni dal semaforo WAIT_S
	    super.timer.stop = false;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
//	public void nextButtonClicked() {
//	    if (!panel.isAutoRun()) {
//	        super.timer.next();
//	    }
//	}

}
