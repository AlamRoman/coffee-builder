package model.Components;

import model.Exceptions;
import model.Memory.MemoryStorage;
import model.Memory.Variable;

public class ComponentInput extends Component{

	private Variable finalVar;
	private String nomeVariabile;
	private Object inputValue;
	
	public ComponentInput(Component nextComponent1, Component nextComponent2, MemoryStorage memory, String nomeVariabile, Object inputValue) {
		super(nextComponent1, nextComponent2, memory);
		
		this.nomeVariabile = "";
		this.inputValue = null;
		
	}
	
	public Object execute() throws Exceptions{
		finalVar = super.getMemory().getVariableByName(nomeVariabile);
		
		finalVar.setValue(inputValue);
		
		return null;
	}
	
	public void set(String nomeVariabile, Object inputValue) {
		this.nomeVariabile = nomeVariabile;
		this.inputValue = inputValue;
	}

	@Override
	public Component getNextComponent() {
		return super.getNextComponent1();
	}

	@Override
	public String print() {
		String out = "IN ";
		
		out += "$" + finalVar.getName();
		
		return out;
	}
	
	
}
