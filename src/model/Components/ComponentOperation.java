package model.Components;

import model.Debug;

import java.util.ArrayList;

import model.Color;
import model.DebuggerConsole;
import model.Exceptions;
import model.Line;
import model.Memory.MemoryStorage;
import model.Memory.OperationType;
import model.Memory.VariableType;
import model.Memory.Variable;

/**<p>
 * This class represents the operation component. 
 * </p>
 * <p>
 * This component is used whenever the user wants to make simple operations with both values and variables, the result of this operation
 * will be assigned automatically to a variable, set by the user during the configuration of the component 
 * </p>
 */
public class ComponentOperation extends AlgorithmComponent{

	private String variableName;
	private String variableFirstOperandName;
	private String variableSecondOperandName;
	private OperationType operation;
	
	private Variable finalVariable;
	private Variable variable1;
	private Variable variable2;
	private static final String referenceTypeMessage = "C-OP";

	/**<p>
	* The constructor of the class {@link ComponentOperation}
	* </p>
	* <p>
	* It creates a new instance of the class
	* </p>
	* @param nextComponent1 The component that is going to be pointed by default by the component
	* @param nextComponent2 The component that is going to be pointed if a condition is false
	* @param memory The memory that is shared in the program
	* 
	*/
	public ComponentOperation(AlgorithmComponent nextComponent1, AlgorithmComponent nextComponent2, MemoryStorage memory) {
		
		super(nextComponent1, nextComponent2, memory);
		
		//initial values
		operation=null;
		variableFirstOperandName="";
		variableSecondOperandName="";
		variableName="";
		variable1=null;
		variable2=null;
		finalVariable=null;
	
	}

	public void set(String variableName, String variableFirstOperandName, String variableSecondOperandName, OperationType operation) {
		
		this.variableName = variableName;
		this.variableFirstOperandName = variableFirstOperandName;
		this.variableSecondOperandName = variableSecondOperandName;
		this.operation = operation;
		
	}
	
	public Object execute() throws Exceptions {
		
		DebuggerConsole.getInstance().printDefaultInfoLog(referenceTypeMessage, "Executing...");
		
		finalVariable = super.getMemory().getVariableByName(variableName);
		
		
		
		if(variableFirstOperandName.startsWith("$")) {
			
			variable1 = super.getMemory().getVariableByName(variableFirstOperandName.substring(1));
			
		}else{
			DebuggerConsole.getInstance().printDefaultInfoLog(referenceTypeMessage, "Checking if '" + finalVariable.getType() + "' is valid (Double, Integer, String)");
			variable1 = getVariableFromTerm(finalVariable, variableFirstOperandName);
			if(variable1==null) {
				variable1 = new Variable(VariableType.String, "" , variableSecondOperandName);
			}
			
		}
		
		
		
		
		if(variableSecondOperandName.startsWith("$")) {
			
			variable2 = super.getMemory().getVariableByName(variableSecondOperandName.substring(1));
		
		}else{
			
			DebuggerConsole.getInstance().printDefaultInfoLog(referenceTypeMessage, "Checking if '" + finalVariable.getType() + "' is valid (Double, Integer, String)");
			variable2 = getVariableFromTerm(finalVariable, variableSecondOperandName);
		
			if(variable2==null) {
				variable2 = new Variable(VariableType.String, "" , variableSecondOperandName);
			}
			
		}
		
		DebuggerConsole.getInstance().printDefaultInfoLog(referenceTypeMessage, "Checking if " + finalVariable.getType() + " == " + variable1.getType() + " AND " + finalVariable.getType() + " == " + variable2.getType() + " (" + finalVariable.getType().equals(variable1.getType()) + ", " + finalVariable.getType().equals(variable2.getType()) + ") >> " + (finalVariable.getType().equals(variable1.getType()) && finalVariable.getType().equals(variable2.getType())));
		if(finalVariable.getType().equals(variable1.getType()) && finalVariable.getType().equals(variable2.getType())) {
			
			switch (finalVariable.getType()) {
			
			case  String: {
				
				if(operation != OperationType.ADD) {
					throw new Exceptions(Exceptions.STRING_ERROR);
				}
				
				finalVariable.setValue( variable1.getValue().toString() + variable2.getValue().toString() );
				
				break;
				
				}
			
			case  Integer: {
				
				switch (operation) {
				case  ADD: {
					finalVariable.setValue( (int) variable1.getValue() + (int) variable2.getValue() );
					break;
					}
				case  SUB: {
					finalVariable.setValue( (int) variable1.getValue() - (int) variable2.getValue() );
					break;
					}
				case  DIV: {
					finalVariable.setValue( (int) variable1.getValue() / (int) variable2.getValue() );
					break;
					}
				case  MOD: {
					finalVariable.setValue( (int) variable1.getValue() % (int) variable2.getValue() );
					break;
					}
				case  MUL: {
					finalVariable.setValue( (int) variable1.getValue() * (int) variable2.getValue() );
					break;
					}
				}
				
				break;
				
			}
			
			case Double: {
				
				switch (operation) {
				case  ADD: {
					finalVariable.setValue( (Double) variable1.getValue() + (Double) variable2.getValue() );
					break;
					}
				case  SUB: {
					finalVariable.setValue( (Double) variable1.getValue() - (Double) variable2.getValue() );
					break;
					}
				case  DIV: {
					finalVariable.setValue( (Double) variable1.getValue() / (Double) variable2.getValue() );
					break;
					}
				case  MOD: {
					finalVariable.setValue( (Double) variable1.getValue() % (Double) variable2.getValue() );
					break;
					}
				case  MUL: {
					finalVariable.setValue( (Double) variable1.getValue() * (Double) variable2.getValue() );
					break;
					}
				}
				
				break;
				}
			
			}
			
			
		}else{
			
			throw new Exceptions(Exceptions.UNMATCH_TYPE);
		
		}
		
		DebuggerConsole.getInstance().printDefaultSuccessLog(referenceTypeMessage, "Executed.");
		
//		super.getMemory().showMemory();
		
		return null;
		
	}
	
	@Override
	public AlgorithmComponent getNextComponent() {
		return super.getNextComponent1();
	}

	@Override
	public String print() {
		String out = "";
		try {
			out += variableName;
			
			out += " = ";
			out += variableFirstOperandName;
			out += " ";
			
			switch (operation) {
			case  ADD: {
				out+="+";
				break;
				}
			case  SUB: {
				out+="-";
				break;
				}
			case  DIV: {
				out+="/";
				break;
				}
			case  MOD: {
				out+="%";
				break;
				}
			case  MUL: {
				out+="*";
				break;
				}
			}
			
			out += " ";
			out += variableSecondOperandName;
		} catch (Exception e) {
			out = "Operation";
		}
		
		DebuggerConsole.getInstance().printCustomMSGColorLog(referenceTypeMessage + "-PRINT-OUTPUT", Color.PURPLE, "Showing: '" + out + "' to the panel");
		return out;
	}
	
	@Override
	public ArrayList<Line> printCode(String language) {
		ArrayList<Line> lines = new ArrayList<Line>();
		String operator = "";
		switch (operation) {
		case  ADD: {
			operator = "+";
			break;
			}
		case  SUB: {
			operator = "-";
			break;
			}
		case  DIV: {
			operator = "/";
			break;
			}
		case  MOD: {
			operator = "%";
			break;
			}
		case  MUL: {
			operator = "*";
			break;
			}
		}
		
		switch(language) {
			case "java":
				lines.add(new Line(variableName + " = " + variableFirstOperandName + operator + variableSecondOperandName + ";"));
				break;
			case "pseudocode":
				lines.add(new Line(print()));
				break;
			case "python":
				lines.add(new Line(variableName + " = " + variableFirstOperandName + operator + variableSecondOperandName));
				break;
		}
		return lines;
		
	}
	
	/**<p>
	* This method creates an auxiliary {@link Variable} from the given term
	* </p>
	* <p>
	* It creates an auxiliary {@link Variable} with the correct value type, given by the RegEX controls and its
	* casted value
	* </p>
	* @param term The term that has to be converted to a variable
	*/
	private Variable getVariableFromTerm(Variable variable, String term) {
		
		if(term == null) return null;
		
		Variable v = null;
		String cleanedTerm = term.trim().toLowerCase();
		
		if(variable != null) {
			if(variable.getType() == VariableType.String) {
				v = new Variable(VariableType.String, term, term);
				DebuggerConsole.getInstance().printDefaultInfoLog(referenceTypeMessage, "returning variable from getVariableFromTerm("+ term +", " + variable + ") >> " + v);
				return v;
			}
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
	
	/**<p>
	 * This method returns the name of the variable that is going to store the final result of the operation
	 * </p>
	 * @return {@link String} The variable name
	 * */
	public String getVariableName() {
		return variableName;
	}

	/**<p>
	 * This method returns the first operand name
	 * </p>
	 * @return {@link String} The first operand name
	 * */
	public String getVariableFirstOperandName() {
		return variableFirstOperandName;
	}

	/**<p>
	 * This method returns the second operand name
	 * </p>
	 * @return {@link String} The second operand name
	 * */
	public String getVariableSecondOperandName() {
		return variableSecondOperandName;
	}

	/**<p>
	 * This method returns the type of the operation
	 * </p>
	 * @return {@link OperationType} The type of the operation
	 * */
	public OperationType getOperation() {
		return operation;
	}
	
}
