package model.Components;

import model.DebuggerConsole;
import model.Exceptions;
import model.Memory.MemoryStorage;
import model.Memory.Variable;
import model.Memory.VariableType;

public class ComponentStart extends AlgorithmComponent{

	private static final String referenceTypeMessage = "C-START";
	private AlgorithmComponent nextComponent;
	
	public ComponentStart(AlgorithmComponent nextComponent) {
		super(nextComponent, null, null);
	}

	@Override
	public Object execute() throws Exceptions {
		// TODO Auto-generated method stub
		DebuggerConsole.getInstance().printDefaultInfoLog(referenceTypeMessage , "Executing...");
		MemoryStorage MS = MemoryStorage.getInstance();
		MS.destroyVariables();
		//DELETE WHEN NOT TESTING-----------------------------------------------
//		MS.addVariable(new Variable(VariableType.String, "Ciao", "ciao"));
//		MS.addVariable(new Variable(VariableType.String, "Ciao2", "ciao2"));
		//----------------------------------------------------------------------
		DebuggerConsole.getInstance().printDefaultSuccessLog(referenceTypeMessage , "Program starts. Memory has been created.");
		DebuggerConsole.getInstance().printDefaultSuccessLog(referenceTypeMessage , "Executed.");
		super.setMemory(MS);
		return  null;
	}
	public AlgorithmComponent getNextComponent() {
		return super.getNextComponent1();
	}

	@Override
	public String print() {
		return "START";
	}
	
	
}
