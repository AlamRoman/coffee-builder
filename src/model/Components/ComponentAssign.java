package model.Components;

import java.util.ArrayList;

import model.DebuggerConsole;
import model.Exceptions;
import model.Line;
import model.Memory.MemoryStorage;
import model.Memory.VariableType;
import model.Memory.Variable;
import model.Memory.VariableType.*;
import view.FlowChartContentPanel;

/**<p>
 * This class represents the assign component. 
 * </p>
 * <p>
 * This component is used whenever a value has to be assigned to an existing variable, in the memory, provided by the user
 * </p>
 */
public class ComponentAssign extends AlgorithmComponent{

	private static final String referenceTypeMessage = "C-ASSIGN";
	private Object value;
	private String variableName;
	private Variable finalVariable;
	private boolean isSecondValueVariable;
	private String secondVarName;
	
	/**<p>
	* The constructor of the class {@link ComponentAssign}
	* </p>
	* <p>
	* It creates a new instance of the class
	* </p>
	* @param nextComponent1 The component that is going to be pointed by default by the component
	* @param nextComponent2 The component that is going to be pointed if a condition is false
	* @param memory The memory that is shared in the program
	* 
	*/
	public ComponentAssign(AlgorithmComponent nextComponent1, AlgorithmComponent nextComponent2, MemoryStorage memory) {
		
		super(nextComponent1, nextComponent2, memory);
		this.value=null;
		this.variableName="";
		this.isSecondValueVariable = false;
		this.secondVarName = null;
	}
	
	/**<p>
	 * This method sets the required attributes for a correct execution of the {@link ComponentAssign} component
	 * </p>
	 * @param value The value that has to be assigned
	 * @param variableName The name of the variable that will be assigned
	 * */
	public void set(Object value, String variableName) {
		
		this.value=value;
		this.variableName=variableName;
		
		if (value instanceof String ) {
			String varName = (String) value;
			
			if (varName.startsWith("$")) {
				this.secondVarName = varName;
				this.isSecondValueVariable = true;
			}
		}
		
	}
	
	public Object execute() throws Exceptions {
		Object tempValue;
		DebuggerConsole.getInstance().printDefaultInfoLog(referenceTypeMessage , "Executing...");
		try {
			finalVariable = super.getMemory().getVariableByName(variableName);
			Variable v = getVariableFromTerm(finalVariable, value.toString());
			tempValue = v.getValue();
		} catch (Exceptions e) {
			//if the final variable doesnt exist, creates a new variable
//			VariableType variableType = Variable.getTypeFromValue(value);
//			finalVariable = super.getMemory().addVariable(new Variable(variableType, variableName, null));
			throw new Exceptions(variableName + ": " + Exceptions.NON_EXISTING_VARIABLE);
		}
		
		DebuggerConsole.getInstance().printDefaultInfoLog(referenceTypeMessage , "Assigning " + tempValue.toString() + "(" + tempValue.getClass().getSimpleName() + ") to the variable '" + finalVariable + "(" + finalVariable.getType() + ")'");
		if(tempValue instanceof String && finalVariable.getType()==VariableType.String) {
			finalVariable.setValue(tempValue);
		}else if(tempValue instanceof Integer && finalVariable.getType()==VariableType.Integer){
			finalVariable.setValue(tempValue);
		}else if(tempValue instanceof Double && finalVariable.getType()==VariableType.Double){
			finalVariable.setValue(tempValue);
		}else {
			throw new Exceptions(Exceptions.UNMATCH_TYPE, "| thrown in " + this.getClass().getSimpleName());
		}
		DebuggerConsole.getInstance().printDefaultSuccessLog(referenceTypeMessage , "Executed.");
//		super.getMemory().showMemory();
		return null;
	}

	@Override
	public AlgorithmComponent getNextComponent() {
		return super.getNextComponent1();
	}
	
	@Override
	public String print() {
		String out = "ASSIGN";
		
		if (!variableName.equals("")) {
			out = "$" + variableName;
			
			out += " = ";
		}
		
		if (value != null) {
			out += getValueString();
		}
		
		return out;
		
	}
	
	@Override
	public ArrayList<Line> printCode(String language) {
		ArrayList<Line> lines = new ArrayList<Line>();
		switch(language) {
			case "java":
				lines.add(new Line(variableName + " = " + value + ";"));
				break;
			case "pseudocode":
				lines.add(new Line(print()));
				break;
			case "python":
				lines.add(new Line(variableName + " = " + value));
				break;
		}
		return lines;
		
	}

	/**<p>
	 * This method returns the value to be assigned as a {@link String}
	 * </p>
	 * @return {@link String} The value to string
	 * */
	public String getValueString() {
		
		if (this.isSecondValueVariable) {
			return secondVarName;
		}
		
	    if (value != null) {
	        if (value instanceof Integer || value instanceof Double) {
	            return String.valueOf(value);
	        } else if (value instanceof String) {
	            return (String) value;
	        } else {
	            return "";
	        }
	    } else {
	        return "";
	    }
	}

	/**<p>
	 * This method returns the name of the variable that is going to assigned
	 * </p>
	 * @return {@link String} The variable name
	 * */
	public String getVariableName() {
		return variableName;
	}

	/**<p>
	* This method creates an auxiliary {@link Variable} from the given term
	* </p>
	* <p>
	* It creates an auxiliary {@link Variable} with the correct value type, given by the RegEX controls and its
	* casted value
	* </p>
	* <p>
	* A variable is required for the correct casting method
	* </p>
	* @param term The term that has to be converted to a variable
	* @param variable The variable that will finalize the variable type controls
	*/
	private Variable getVariableFromTerm(Variable variable, String term) {
		
		if(term == null) return null;
		
		Variable v = null;
		String cleanedTerm = term.trim().toLowerCase();
		
		if(variable != null) {
			if(term.startsWith("$")) {
				try {
					v = super.getMemory().getVariableByName(term.substring(1));
					return v;
				} catch (Exceptions e) {
					e.printStackTrace();
				}
			}
			if(variable.getType() == VariableType.String) {
				v = new Variable(VariableType.String, term, term);
				DebuggerConsole.getInstance().printDefaultInfoLog(referenceTypeMessage, "returning variable from getVariableFromTerm("+ term +", " + variable + ") >> " + v);
				return v;
			}
		}
		try {
			switch (variable.getType()) {
			case Integer: 
				v = new Variable(VariableType.Integer, term, Integer.parseInt(cleanedTerm));
				break;
			case Double:
				v = new Variable(VariableType.Double, term, Double.parseDouble(cleanedTerm));
				break;
			case String:
				v = new Variable(VariableType.String, term, cleanedTerm);
				break;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			v = new Variable(variable.getType(), variable.getName(), null);
		}
		if (cleanedTerm.matches(".*[a-z].*") && cleanedTerm.matches("[a-z0-9]+")) {
			v =  new Variable(VariableType.String, term, term);
		}
		
		if (cleanedTerm.matches("-?\\d*\\.\\d+")) {
			double doubleValue = Double.parseDouble(term);
			v =  new Variable(VariableType.Double, term, doubleValue);
		}
		
		if (cleanedTerm.matches("\\d+")) {
			int integerValue = Integer.parseInt(term);
			v =  new Variable(VariableType.Integer, term, integerValue);
		}	
//		super.getMemory().showMemory();
		DebuggerConsole.getInstance().printDefaultInfoLog(referenceTypeMessage, "returning variable from getVariableFromTerm("+ term +", " + variable + ") >> " + v);
		return v;
	}
	
}
