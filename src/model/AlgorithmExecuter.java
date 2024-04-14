package model;

import model.Components.Component;

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
			DebuggerConsole.getInstance().printCustomMSGColorLog(referenceType, Color.RED_UNDERLINED, "PASSING EXECUTION TO COMPONENT: " + component.getClass().getSimpleName());
		}
		
	}

}
