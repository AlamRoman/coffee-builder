package model.Components;

import model.Color;
import model.DebuggerConsole;
import model.Exceptions;
import model.Memory.MemoryStorage;

public class ComponentElse extends AlgorithmComponent {
	
	private static final String referenceTypeMessage = "C-ELSE";
	private AlgorithmComponent nextComponent;

	public ComponentElse(AlgorithmComponent nextComponent1, AlgorithmComponent nextComponent2, MemoryStorage memory) {
		super(nextComponent1, nextComponent2, memory);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Object execute() throws Exceptions {
		return null;
	}
	
	@Override
	public AlgorithmComponent getNextComponent() {
		return nextComponent;
	}
	
	public String print() {
		return "ELSE";
	}

}
