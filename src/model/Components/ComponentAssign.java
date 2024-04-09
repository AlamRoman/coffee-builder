package model.Components;

import model.Memory.MemoryStorage;
import model.Memory.Variable;

public class ComponentAssign extends AlgorithmComponent{

	private String operation;
	private String value;
	private Variable finalVariable;
	
	public ComponentAssign(AlgorithmComponent nextComponent1, AlgorithmComponent nextComponent2, MemoryStorage memory,
			String operation, String value, Variable finalVariable) {
		
		super(nextComponent1, nextComponent2, memory);
		
		
		
	}

}
