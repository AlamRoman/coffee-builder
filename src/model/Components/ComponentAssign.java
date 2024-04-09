package model.Components;

import model.Exceptions;
import model.Memory.MemoryStorage;
import model.Memory.Type;
import model.Memory.Variable;

public class ComponentAssign extends AlgorithmComponent{

	private Object value;
	private String variableName;
	private Variable finalVariable;
	
	public ComponentAssign(AlgorithmComponent nextComponent1, AlgorithmComponent nextComponent2, MemoryStorage memory,
			String value, String variableName) {
		
		super(nextComponent1, nextComponent2, memory);
		this.value=null;
		this.variableName="";
		
	}
	
	public void set(String value, String variableName) {
		
		this.value=value;
		this.variableName=variableName;
		
	}
	
	public void execute() throws Exceptions {
		
		finalVariable = super.getMemory().getVariableByName(variableName);
		
		if(value instanceof String && finalVariable.getType()==Type.String) {
			finalVariable.setValue(value);
		}else if(value instanceof Integer && finalVariable.getType()==Type.Integer){
			finalVariable.setValue(value);
		}else if(value instanceof Double && finalVariable.getType()==Type.Double){
			finalVariable.setValue(value);
		}else {
			throw new Exceptions(Exceptions.UNMATCH_TYPE);
		}
	}
	
	

}
