package model.Components;

import java.util.ArrayList;

import model.DebuggerConsole;
import model.Exceptions;
import model.Line;
import model.Memory.MemoryStorage;
import model.Memory.VariableType;
import model.Memory.Variable;
import view.Panel;

/**<p>
 * This class represents the assign component. 
 * </p>
 * <p>
 * This component is used whenever a variable has to be initialized and saved in the memory
 * </p>
 */
public class ComponentDeclaration extends AlgorithmComponent{

	private VariableType variableType;
	private String variableName;
	private MemoryStorage MS;
	private static final String referenceTypeMessage = "C-DECL";
	
	/**<p>
	* The constructor of the class {@link ComponentDeclaration}
	* </p>
	* <p>
	* It creates a new instance of the class
	* </p>
	* @param nextComponent1 The component that is going to be pointed by default by the component
	* @param nextComponent2 The component that is going to be pointed if a condition is false
	* @param memory The memory that is shared in the program
	* 
	*/
	public ComponentDeclaration(AlgorithmComponent nextComponent1, AlgorithmComponent nextComponent2, MemoryStorage MS) {
		super(nextComponent1, nextComponent2, MS);
		this.variableType = null;
		this.variableName = "";
		this.MS = MS.getInstance();
	}
	
	public Variable getVariable() {
		return new Variable(this.variableType, this.variableName, null);
	}
	
	public void set(VariableType variableType, String variableName) {
		this.variableType = variableType;
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
	
	public AlgorithmComponent getNextComponent() {
		return super.getNextComponent1();
	}

	@Override
	public String print() {
		String out = "";
		if(variableType != null) {
			out += variableType.name + " $";
		}
		if(variableName != null) {
			out += variableName;			
		}
		
		if (out.equals("")) {
			out = "DECLARATION";
		}
		
		return out;
		
	}
	
	@Override
	public ArrayList<Line> printCode(String language) {
		ArrayList<Line> lines = new ArrayList<Line>();
		switch(language) {
			case "java":
				lines.add(new Line(((variableType==null)?"%VariableType% ":variableType.name+" ") + ((variableName==null)?"%VariableName%":variableName) + ";"));
				break;
			case "pseudocode":
				lines.add(new Line(print()));
				break;
			case "python":
//				lines.add(new Line(((variableName==null)?"%VariableName%":variableName)));
				break;
		}
		return lines;
		
	}

	public String getVariableName() {
		return variableName;
	}
	
	public String getTypeString() {
		if (variableType != null) {
			return variableType.name;
		}
		return null;
	}

	public VariableType getVariableType() {
		return variableType;
	}
	
}
