package model.Components;

import model.Exceptions;
import model.Memory.MemoryStorage;
import model.Memory.Type;
import model.Memory.Variable;
import view.Panel;

public class ComponentDeclaration extends AlgorithmComponent{

	private Type type;
	private String variableName;
	private String value;
	private MemoryStorage MS;
	
	public ComponentDeclaration(AlgorithmComponent nextComponent1, AlgorithmComponent nextComponent2, MemoryStorage MS) {
		super(nextComponent1, nextComponent2, MS);
		this.type = null;
		this.variableName = "";
		this.MS = MS.getInstance();
	}
	
	public Variable getVariable() {
		return new Variable(this.type, this.variableName, this.value);
	}
	
	public void set(Type type, String variableName) {
		this.type = type;
		this.variableName = variableName;
	}
	
	public void execute() throws Exceptions {
			try {			
				MS.addVariable(getVariable());
			}catch(Exception e) {
				//Handle exception...
			}
		
	}
	
}
