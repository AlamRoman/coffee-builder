package model.Components;

import model.DebuggerConsole;
import model.Memory.MemoryStorage;

public class ComponentEnd extends Component{

	private static final String referenceTypeMessage = "C-END";

	public ComponentEnd() {
		super(null, null, null);
	}
	
	public Object execute() {
		DebuggerConsole.getInstance().printDefaultInfoLog(referenceTypeMessage , "Executing...");
		MemoryStorage.getInstance().destroyVariables();
		DebuggerConsole.getInstance().printDefaultSuccessLog(referenceTypeMessage , "Program ends. Memory deleted.");
		DebuggerConsole.getInstance().printDefaultSuccessLog(referenceTypeMessage , "Executed.");
		return null;
	}
	
	public Component getNextComponent() {
		return null;
	}

	@Override
	public String print() {
		// TODO Auto-generated method stub
		return "END";
	}
	
}
