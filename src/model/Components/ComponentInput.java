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
		
		this.nomeVariabile = nomeVariabile;
		this.inputValue = inputValue;
		
	}
	
	public void setVar() throws Exceptions{
		finalVar = super.getMemory().getVariableByName(nomeVariabile);
		
		finalVar.setValue(inputValue);
	}

	public String getNomeVariabile() {
		return nomeVariabile;
	}

	public void setNomeVariabile(String nomeVariabile) {
		this.nomeVariabile = nomeVariabile;
	}

	public Object getInputValue() {
		return inputValue;
	}

	public void setInputValue(Object inputValue) {
		this.inputValue = inputValue;
	}
}
