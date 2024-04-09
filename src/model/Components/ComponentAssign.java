package model.Components;

import model.Memory.Variable;

public class ComponentAssign extends AlgorithmComponent{

	private String finalVariableName;
	private String variable1Name;
	private String variable2Name;
	private String operation;
	
	private Variable finalVariable;
	private Variable variable1;
	private Variable variable2;
	
	 
	
	public ComponentAssign(AlgorithmComponent nextComponent1, AlgorithmComponent nextComponent2) {
		super(nextComponent1, nextComponent2);
	}
	
	
}
