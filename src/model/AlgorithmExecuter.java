package model;

import model.Components.Component;
import model.Memory.MemoryStorage;

public class AlgorithmExecuter {
	
	private static final String referenceType = "EXECUTER";
	private Component component;

	public AlgorithmExecuter() {
		super();
	}
	
	public void start(Component start) throws Exceptions {
		
		String result=null;
		component = start;
		
		while (component!=null) {
			
			result=null;
			result = (String) component.execute();
			
			if(result!=null) {
				System.out.println(result);
				result = null;
			}
			
			component=component.getNextComponent();
			if(component != null)DebuggerConsole.getInstance().printCustomMSGColorLog(referenceType, Color.RED_UNDERLINED, "PASSING EXECUTION TO COMPONENT: " + component.getClass().getSimpleName());
		}
		DebuggerConsole.getInstance().printDefaultSuccessLog(referenceType, "Execution complete. Memory:");
//		MemoryStorage.getInstance().showMemory();
	}

}
