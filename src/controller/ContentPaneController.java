package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.util.concurrent.Semaphore;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.AlgorithmExecuter;
import model.DebuggerConsole;
import model.Exceptions;
import model.Timer;
import model.Components.AlgorithmComponent;
import model.Memory.MemoryStorage;
import view.Panel;

public class ContentPaneController extends Controller{
	
	private static final String referenceType = "CP-CONTROLLER";
	private Panel panel;
	private Semaphore execute;
	private Semaphore wait;

	public ContentPaneController(AlgorithmExecuter ALGORITHM_EXECUTER, Timer TIMER, Semaphore execute, Semaphore wait, Panel panel) {
		super(ALGORITHM_EXECUTER, TIMER, MemoryStorage.getInstance());
		// TODO Auto-generated constructor stub
		this.panel = panel;
		this.execute = execute;
		this.wait = wait;
		panel.registerEvents(this);
		ALGORITHM_EXECUTER.setController(this);
		
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
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch(e.getActionCommand()) {
			case "START":
				DebuggerConsole.getInstance().printDefaultInfoLog(referenceType, "EXECUTE BUTTON GOT CLICKED=================");
				if(panel.getExecuteButtonStatus()) {
					//SE IL PROGRAMMA NON E' IN ESECUZIONE---------------------------------------------------------------------------
//					if(panel.isAutoRun()) {
						//SE IL PROGRAMMA E' IN MODALITA' AUTORUN-------------------------------------------------------
						int ms = panel.getMilliseconds();
						DebuggerConsole.getInstance().printDefaultInfoLog(referenceType, "User setted " + ms + "ms as the execution delay");
						try {
							panel.setExecuteButtonUsable(false);
							DebuggerConsole.getInstance().printDefaultInfoLog(referenceType, "Setting timer to " + ms + "ms");
							super.getTimer().set(ms, panel.isAutoRun());
							AlgorithmComponent c = super.getMemory().getStartComponent();
							DebuggerConsole.getInstance().printDefaultInfoLog(referenceType, "Running the executer with start component: " + c.getClass().getSimpleName());
							super.getExecuter().start(super.getMemory().getStartComponent());
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
				super.getTimer().nextButtonGotClicked = true;
				break;
			case "END":
				
				algorithmExecuter.stop();
				panel.setExecuteButtonUsable(true);
				panel.toggleExecuteSelected();
				
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
//	        super.getTimer().next();
//	    }
//	}

}
