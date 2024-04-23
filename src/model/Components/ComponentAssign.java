package model.Components;

import model.DebuggerConsole;
import model.Exceptions;
import model.Memory.MemoryStorage;
import model.Memory.VariableType;
import model.Memory.Variable;

public class ComponentAssign extends AlgorithmComponent{

	private static final String referenceTypeMessage = "C-ASSIGN";
	private Object value;
	private String variableName;
	private Variable finalVariable;
	
	public ComponentAssign(AlgorithmComponent nextComponent1, AlgorithmComponent nextComponent2, MemoryStorage memory) {
		
		super(nextComponent1, nextComponent2, memory);
		this.value=null;
		this.variableName="";
		
	}
	
	public void set(Object value, String variableName) {
		
		this.value=value;
		this.variableName=variableName;
		
	}
	
	public Object execute() throws Exceptions {
		DebuggerConsole.getInstance().printDefaultInfoLog(referenceTypeMessage , "Executing...");
		try {
			finalVariable = super.getMemory().getVariableByName(variableName);
		} catch (Exceptions e) {
			//if the final variable doesnt exist, creates a new variable
			VariableType variableType = Variable.getTypeFromValue(value);
			finalVariable = super.getMemory().addVariable(new Variable(variableType, variableName, null));
		}
		
//		System.out.println("Variable type: " + finalVariable.getType());
//	    System.out.println("Value type: " + value.getClass().getSimpleName());
		DebuggerConsole.getInstance().printDefaultInfoLog(referenceTypeMessage , "Assigning " + value.toString() + "(" + value.getClass().getSimpleName() + ") to the variable '" + finalVariable + "(" + finalVariable.getType() + ")'");
		if(value instanceof String && finalVariable.getType()==VariableType.String) {
			finalVariable.setValue(value);
		}else if(value instanceof Integer && finalVariable.getType()==VariableType.Integer){
			finalVariable.setValue(value);
		}else if(value instanceof Double && finalVariable.getType()==VariableType.Double){
			finalVariable.setValue(value);
		}else {
			throw new Exceptions(Exceptions.UNMATCH_TYPE, "| thrown in " + this.getClass().getSimpleName());
		}
		DebuggerConsole.getInstance().printDefaultSuccessLog(referenceTypeMessage , "Executed.");
//		super.getMemory().showMemory();
		return null;
	}

	@Override
	public AlgorithmComponent getNextComponent() {
		return super.getNextComponent1();
	}
	
	public String print() {
		String out = "$" + variableName;
		
		out+=" = ";
		
		out+= getValueString();
		
		return out;
		
	}

	public String getValueString() {
	    if (value != null) {
	        if (value instanceof Integer || value instanceof Double) {
	            return String.valueOf(value);
	        } else if (value instanceof String) {
	            return (String) value;
	        } else {
	            return "";
	        }
	    } else {
	        return "";
	    }
	}

	public String getVariableName() {
		return variableName;
	}

}
