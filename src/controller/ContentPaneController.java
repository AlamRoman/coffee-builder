package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.util.concurrent.Semaphore;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.AlgorithmExecuter;
import model.DebuggerConsole;
import model.Exceptions;
import model.Timer;
import model.Components.Component;
import model.Memory.MemoryStorage;
import view.Panel;

public class ContentPaneController extends Controller{
	
	private static final String referenceType = "CP-CONTROLLER";
	private Panel panel;

	public ContentPaneController(AlgorithmExecuter ALGORITHM_EXECUTER, Timer TIMER, Semaphore execute, Semaphore wait, Panel panel) {
		super(ALGORITHM_EXECUTER, TIMER, MemoryStorage.getInstance());
		// TODO Auto-generated constructor stub
		this.panel = panel;
		panel.registerEvents(this);
		ALGORITHM_EXECUTER.setController(this);
		
		//REMOVE THIS TRY-CATCH BLOCK WHEN NOT TESTING~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		try {
			super.getMemory().initializeDefaultComponents();
//			updateTable();
		} catch (Exceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch(e.getActionCommand()) {
			case "START":
				
				if(panel.getExecuteButtonStatus()) {
					//SE IL PROGRAMMA NON E' IN ESECUZIONE---------------------------------------------------------------------------
					if(panel.isAutoRun()) {
						//SE IL PROGRAMMA E' IN MODALITA' AUTORUN-------------------------------------------------------
						int ms = panel.getMilliseconds();
						DebuggerConsole.getInstance().printDefaultInfoLog(referenceType, "User setted " + ms + "ms as the execution delay");
						try {
							DebuggerConsole.getInstance().printDefaultInfoLog(referenceType, "Setting timer to " + ms + "ms");
							super.getTimer().set(ms);
							Component c = super.getMemory().getStartComponent();
							DebuggerConsole.getInstance().printDefaultInfoLog(referenceType, "Running the executer with start component: " + c.getClass().getSimpleName());
							super.getExecuter().start(super.getMemory().getStartComponent());
						} catch (Exceptions customException) {
							JOptionPane.showMessageDialog(panel, customException.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
						} catch (Exception exception) {
							System.err.println(exception.getMessage());
						}
						//----------------------------------------------------------------------------------------------
					}else{
						//SE IL PROGRAMMA E' IN MODALITA' MANUAL--------------------------------------------------------
						super.getTimer().setPanelInstance(panel.getPanelInstance());
						//----------------------------------------------------------------------------------------------
					}
				}
				//IL PROGRAMMA E' ANCORA IN ESECUZIONE...
				break;
			case "END":
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

}
