package model.Components;

import model.DebuggerConsole;
import model.Exceptions;
import model.Memory.MemoryStorage;
import model.Memory.Type;
import model.Memory.Variable;

public class ComponentAssign extends Component{

	private static final String referenceTypeMessage = "C-ASSIGN";
	private Object value;
	private String variableName;
	private Variable finalVariable;
	
	public ComponentAssign(Component nextComponent1, Component nextComponent2, MemoryStorage memory) {
		
		super(nextComponent1, nextComponent2, memory);
		this.value=null;
		this.variableName="";
		
	}
	
	public void set(Object value, String variableName) {
		
		this.value=value;
		this.variableName=variableName;
		
	}
	
	public Object execute() throws Exceptions {
		DebuggerConsole.getInstance().printDefaultInfoLog(Thread.currentThread().getStackTrace()[1].getLineNumber(), this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[2], referenceTypeMessage , "Executing...");
		finalVariable = super.getMemory().getVariableByName(variableName);
		
//		System.out.println("Variable type: " + finalVariable.getType());
//	    System.out.println("Value type: " + value.getClass().getSimpleName());
		DebuggerConsole.getInstance().printDefaultInfoLog(Thread.currentThread().getStackTrace()[1].getLineNumber(), this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[2], referenceTypeMessage , "Assigning" + value.toString() + "to the variable '" + finalVariable + "'");
		if(value instanceof String && finalVariable.getType()==Type.String) {
			finalVariable.setValue(value);
		}else if(value instanceof Integer && finalVariable.getType()==Type.Integer){
			finalVariable.setValue(value);
		}else if(value instanceof Double && finalVariable.getType()==Type.Double){
			finalVariable.setValue(value);
		}else {
			throw new Exceptions(Exceptions.UNMATCH_TYPE, "| thrown in " + this.getClass().getSimpleName());
		}
		DebuggerConsole.getInstance().printDefaultSuccessLog(Thread.currentThread().getStackTrace()[1].getLineNumber(), this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[2], referenceTypeMessage , "Executed.");
//		super.getMemory().showMemory();
		return null;
	}

	@Override
	public Component getNextComponent() {
		return super.getNextComponent1();
	}
	
	public String print() {
		String out="ASSIGN ";
		
		out+=variableName;
		
		out+=" = ";
		out+= value.toString();
		
		return out;
		
	}
	
	

}
