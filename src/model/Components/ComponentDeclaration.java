package model.Components;

import model.DebuggerConsole;
import model.Exceptions;
import model.Memory.MemoryStorage;
import model.Memory.Type;
import model.Memory.Variable;
import view.Panel;

public class ComponentDeclaration extends Component{

	private Type type;
	private String variableName;
	private String value;
	private MemoryStorage MS;
	private static final String referenceTypeMessage = "C-DECL";
	
	public ComponentDeclaration(Component nextComponent1, Component nextComponent2, MemoryStorage MS) {
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
	
	public Object execute() throws Exceptions {
			try {
				DebuggerConsole.getInstance().printDefaultInfoLog(referenceTypeMessage , "Executing...");
				MS.addVariable(getVariable());
			}catch(Exceptions e) {
				//Handle exception...
				System.err.println(e.getMessage());
			}
			DebuggerConsole.getInstance().printDefaultSuccessLog(referenceTypeMessage , "Executed.");
			return null;
		
	}
	
	public Component getNextComponent() {
		return super.getNextComponent1();
	}

	public String print() {
		String out="DECLARATION ";
		
		out+=variableName;
		
		out+=" = ";
		out+= value.toString();
		
		return out;
		
	}
	
}
