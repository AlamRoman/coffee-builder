package model.Components;

import model.Exceptions;
import model.Memory.MemoryStorage;
import model.Memory.Variable;
import view.Panel;

public class ComponentDeclaration {

	private String type;
	private String variableName;
	private String value;
	private Panel settingsPanel;
	private MemoryStorage MS;
	private Boolean isset;
	
	public ComponentDeclaration() {
		this.type = "";
		this.variableName = "";
		this.value = "";
		this.isset = false;
		this.MS = MS.getInstance();
	}
	
	public Variable getVariable() {
		return new Variable(this.type, this.variableName, this.value);
	}
	
	public void set(String type, String variableName, String value) {
		this.type = type;
		this.variableName = variableName;
		this.value = value;
		this.isset = true;
	}
	
	public void execute() throws Exceptions {
		if(isset){
			try {			
				MS.addVariable(getVariable());
			}catch(Exception e) {
				//Handle exception...
			}
			throw new Exceptions(Exceptions.COMPONENT_NOT_SET);
		}
		
	}
	
}
