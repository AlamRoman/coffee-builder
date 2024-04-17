package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;

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

	public ContentPaneController(AlgorithmExecuter ALGORITHM_EXECUTER, Timer TIMER, MemoryStorage MEMORY, Panel panel) {
		super(ALGORITHM_EXECUTER, TIMER, MEMORY);
		// TODO Auto-generated constructor stub
		this.panel = panel;
		panel.registerEvents(this);
		super.getMemory().initializeDefaultComponents();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch(e.getActionCommand()) {
			case "START":
				if(panel.isAutoRun()) {
					int ms = panel.getMilliseconds();
					try {
						DebuggerConsole.getInstance().printDefaultInfoLog(referenceType, "Setting timer to " + ms + "ms");
						super.getTimer().set(ms);
						Component c = super.getMemory().getStartComponent();
						DebuggerConsole.getInstance().printDefaultInfoLog(referenceType, "Running the executer with start component: " + c);
						super.getExecuter().start(super.getMemory().getStartComponent());
					} catch (Exceptions customException) {
						JOptionPane.showMessageDialog(panel, customException.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
					} catch (Exception exception) {
						System.err.println(exception.getMessage());
					}
				}
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

}
