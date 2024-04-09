package model.Components;

import model.Memory.MemoryStorage;
import model.Memory.Variable;

public class ComponentOperation extends AlgorithmComponent{

	public ComponentOperation(AlgorithmComponent nextComponent1, AlgorithmComponent nextComponent2,
			MemoryStorage memory) {
		super(nextComponent1, nextComponent2, memory);
		// TODO Auto-generated constructor stub
	}

	private String operation;
	
	private Variable finalVariable;
	private Variable variable1;
	private Variable variable2;

	public ComponentOperation(AlgorithmComponent nextComponent1, AlgorithmComponent nextComponent2, MemoryStorage memory,
			String finalVariableName, String variable1Name, String variable2Name, String operation) {
		
		super(nextComponent1, nextComponent2, memory);
		this.finalVariable = ;
		this.variable1 = variable1Name;
		this.variable2 = variable2Name;
		this.operation = operation;
		
	}
	
	
}
