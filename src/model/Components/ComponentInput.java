package model.Components;

import model.Exceptions;
import model.Memory.MemoryStorage;
import model.Memory.Variable;

public class ComponentInput extends AlgorithmComponent{

	private Variable var;
	
	public ComponentInput(AlgorithmComponent nextComponent1, AlgorithmComponent nextComponent2, MemoryStorage memory) {
		super(nextComponent1, nextComponent2, memory);
	}
	
	public void setVar(String nomeVariabile, Object inputValue) throws Exceptions{
		try {
			var = super.getMemory().getVariableByName(nomeVariabile);
			
			var.setValue(inputValue);
		} catch (Exception e) {
			throw new Exceptions(Exceptions.NON_EXISTING_VARIABLE);
		}
	}
	
}
