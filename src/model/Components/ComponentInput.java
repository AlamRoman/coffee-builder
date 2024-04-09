package model.Components;

import model.Exceptions;
import model.Memory.MemoryStorage;
import model.Memory.Variable;

public class ComponentInput extends AlgorithmComponent{

	private Variable finalVar;
	private String nomeVariabile;
	private Object inputValue;
	
	public ComponentInput(AlgorithmComponent nextComponent1, AlgorithmComponent nextComponent2, MemoryStorage memory, String nomeVariabile, Object inputValue) {
		super(nextComponent1, nextComponent2, memory);
		
		this.nomeVariabile = "";
		this.inputValue = null;
		
	}
	
	public void execute() throws Exceptions{
		finalVar = super.getMemory().getVariableByName(nomeVariabile);
		
		finalVar.setValue(inputValue);
	}
	
	public void set(String nomeVariabile, Object inputValue) {
		this.nomeVariabile = nomeVariabile;
		this.inputValue = inputValue;
	}
}
