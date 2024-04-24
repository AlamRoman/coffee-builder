package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.concurrent.Semaphore;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import model.AlgorithmExecuter;
import model.Buffer;
import model.DebuggerConsole;
import model.Exceptions;
import model.Timer;
import model.Components.AlgorithmComponent;
import model.File.FileDefragger;
import model.File.FileSaver;
import model.File.OpenHTMLFile;
import model.Memory.MemoryStorage;
import view.Panel;

public class ContentPaneController extends Controller implements Runnable{
	
	private static final String referenceType = "CP-CONTROLLER";
	private Panel panel;
	private Semaphore execute;
	private Semaphore wait;
	private Semaphore read;
	private Semaphore write;
	private Thread T;

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
							JOptionPane.showMessageDialog(panel, customException.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
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
				
				memory.setOnGoing(false);
				memory.destroyVariables();
				algorithmExecuter.stop();
				panel.setExecuteButtonUsable(true);
				panel.toggleExecuteSelected();
				super.memory.resetExecutingStatusOfPanels();
				
				break;
			case "SHOW_MENU":
				JButton optionsButton = (JButton)e.getSource();
				// Crea e mostra il menu a discesa quando si clicca sul pulsante
                JPopupMenu popupMenu = new JPopupMenu();
                JMenuItem saveMenuItem = new JMenuItem("Save...");
                JMenuItem openMenuItem = new JMenuItem("Open...");
                JMenuItem helpMenuItem = new JMenuItem("Help");
                
                // Aggiungi gli action listener per le voci di menu
                saveMenuItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    	FileSaver.saveToFile(memory.getComponents(), (JFrame)panel.getParent().getParent().getParent());
                    }
                });

                openMenuItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    	FileDefragger.openFile(memory, (JFrame)panel.getParent().getParent().getParent());
                    }
                });

                helpMenuItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        OpenHTMLFile HTMLopener = new OpenHTMLFile("doc" + File.separator + "index.html");
                    }
                });
                
                popupMenu.add(saveMenuItem);
                popupMenu.add(openMenuItem);
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

	public void updateTable() {
		// TODO Auto-generated method stub
		DebuggerConsole.getInstance().printDefaultInfoLog(referenceType, "Updating table...");
		panel.setMemoryTableInDebugPanel(MemoryStorage.getInstance().createMemoryTable());
		DebuggerConsole.getInstance().printDefaultInfoLog(referenceType, "Table updated.");
	}
	
	public void deleteVariablesFromMemoryStorage() {
		MemoryStorage.getInstance().destroyVariables();
	}

	public void showErrorDialog(String message) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(panel, message, "Errore", JOptionPane.ERROR_MESSAGE);
	}
	
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
