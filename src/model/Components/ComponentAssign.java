package model.Components;

import model.Memory.Variable;

public class ComponentAssign extends AlgorithmComponent{

	private String operation;
	
	private Variable finalVariable;
	private Variable variable1;
	private Variable variable2;

	public ComponentAssign(AlgorithmComponent nextComponent1, AlgorithmComponent nextComponent2,
			String finalVariableName, String variable1Name, String variable2Name, String operation) {
		super(nextComponent1, nextComponent2);
		this.finalVariable = finalVariableName;
		this.variable1 = variable1Name;
		this.variable2 = variable2Name;
		this.operation = operation;
	}
	
	
}
