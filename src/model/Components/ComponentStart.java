package model.Components;

import model.DebuggerConsole;
import model.Exceptions;
import model.Memory.MemoryStorage;

public class ComponentStart extends Component{

	private static final String referenceTypeMessage = "C-START";
	private Component nextComponent;
	
	public ComponentStart(Component nextComponent) {
		super(nextComponent, null, null);
	}

	@Override
	public Object execute() throws Exceptions {
		// TODO Auto-generated method stub
		DebuggerConsole.getInstance().printDefaultInfoLog(referenceTypeMessage , "Executing...");
		MemoryStorage MS = MemoryStorage.getInstance();
		DebuggerConsole.getInstance().printDefaultSuccessLog(referenceTypeMessage , "Program starts. Memory has been created.");
		DebuggerConsole.getInstance().printDefaultSuccessLog(referenceTypeMessage , "Executed.");
		super.setMemory(MS);
		return  null;
	}
	public Component getNextComponent() {
		return super.getNextComponent1();
	}

	@Override
	public String print() {
		return "START";
	}
	
	
}
