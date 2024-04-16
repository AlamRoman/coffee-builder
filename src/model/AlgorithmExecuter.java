package model;

import model.Components.Component;
import model.Components.ComponentEnd;
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
		
		//Finche il componente non è nullo (arrivati alla fine)
		while (component!=null) {
			
			result=null;
			
			//Eseguo il componente
			result = (String) component.execute();
			
			//Controllo se c'è stato un output dal componente
			if(result!=null) {
				System.out.println(result);
				result = null;
			}
			
			//Se il prossimo componente è la fine del programma mostra la tabella della memoria in debug
			if(component.getNextComponent() instanceof ComponentEnd) MemoryStorage.getInstance().showMemory();
			
			component=component.getNextComponent(); //Passo all'esecutore il componente successivo
			
			//Mostro in fase di debug il passaggio del componente
			if(component != null)DebuggerConsole.getInstance().printCustomMSGColorLog(referenceType, Color.RED_UNDERLINED, "PASSING EXECUTION TO COMPONENT: " + component.getClass().getSimpleName());
		}
		DebuggerConsole.getInstance().printDefaultSuccessLog(referenceType, "Execution completed.");
//		MemoryStorage.getInstance().showMemory();
	}

}
