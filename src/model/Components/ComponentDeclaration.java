package model.Components;

import model.Exceptions;
import model.Memory.MemoryStorage;
import model.Memory.Variable;
import view.Panel;

public class ComponentDeclaration extends AlgorithmComponent{

	private String type;
	private String variableName;
	private String value;
	private MemoryStorage MS;
	private Boolean isset;
	
	public ComponentDeclaration() {
		super();
		this.type = "";
		this.variableName = "";
		this.isset = false;
		this.MS = MS.getInstance();
	}
	
	public Variable getVariable() {
		return new Variable(this.type, this.variableName);
	}
	
	public void set(String type, String variableName) {
		this.type = type;
		this.variableName = variableName;
		this.isset = true;
	}
	
	public void execute() throws Exceptions {
		if(isset){
			try {			
				MS.addVariable(getVariable());
			}catch(Exception e) {
				//Handle exception...
			}
		}else {
			throw new Exceptions(Exceptions.COMPONENT_NOT_SET);			
		}
		
	}
	
}
