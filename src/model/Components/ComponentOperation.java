package model.Components;

import model.Debug;
import model.Color;
import model.DebuggerConsole;
import model.Exceptions;
import model.Memory.MemoryStorage;
import model.Memory.OperationType;
import model.Memory.Type;
import model.Memory.Variable;

public class ComponentOperation extends Component{

	private String variableName;
	private String variableFirstOperandName;
	private String variableSecondOperandName;
	private OperationType operation;
	
	private Variable finalVariable;
	private Variable variable1;
	private Variable variable2;
	private static final String referenceTypeMessage = "C-OP";

	public ComponentOperation(Component nextComponent1, Component nextComponent2, MemoryStorage memory) {
		
		super(nextComponent1, nextComponent2, memory);
		
		//initial values
		operation=OperationType.ADD;
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
				variable1 = new Variable(Type.String, "" , variableSecondOperandName);
			}
			
		}
		
		
		
		
		if(variableSecondOperandName.startsWith("$")) {
			
			variable2 = super.getMemory().getVariableByName(variableSecondOperandName.substring(1));
		
		}else{
			
			DebuggerConsole.getInstance().printDefaultInfoLog(referenceTypeMessage, "Checking if '" + finalVariable.getType() + "' is valid (Double, Integer, String)");
			variable2 = getVariableFromTerm(finalVariable, variableSecondOperandName);
		
			if(variable2==null) {
				variable2 = new Variable(Type.String, "" , variableSecondOperandName);
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
	
	public Component getNextComponent() {
		return super.getNextComponent1();
	}

	
	public String print() {
		
		String out="OPERATION ";
		
		out+=variableName;
		
		out+=" = ";
		out+=variableFirstOperandName;
		out+=" ";
		
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
		
		out+=" ";
		out+=variableSecondOperandName;
		DebuggerConsole.getInstance().printCustomMSGColorLog(referenceTypeMessage + "-PRINT-OUTPUT", Color.PURPLE, "Showing: '" + out + "' to the panel");
		return out;
	}
	
	private Variable getVariableFromTerm(Variable variable, String term) {
		
		if(term == null) return null;
		
		Variable v = null;
		String cleanedTerm = term.trim().toLowerCase();
		
		if(variable != null) {
			if(variable.getType() == Type.String) {
				v = new Variable(Type.String, term, term);
				DebuggerConsole.getInstance().printDefaultInfoLog(referenceTypeMessage, "returning variable from getVariableFromTerm("+ term +", " + variable + ") >> " + v);
				return v;
			}
		}
		if (cleanedTerm.matches(".*[a-z].*") && cleanedTerm.matches("[a-z0-9]+")) {
			v =  new Variable(Type.String, term, term);
		}
		
		if (cleanedTerm.matches("-?\\d*\\.\\d+")) {
			double doubleValue = Double.parseDouble(term);
			v =  new Variable(Type.Double, term, doubleValue);
		}
		
		if (cleanedTerm.matches("\\d+")) {
			int integerValue = Integer.parseInt(term);
			v =  new Variable(Type.Integer, term, integerValue);
		}	
//		super.getMemory().showMemory();
		DebuggerConsole.getInstance().printDefaultInfoLog(referenceTypeMessage, "returning variable from getVariableFromTerm("+ term +", " + variable + ") >> " + v);
		return v;
	}

	public String getVariableName() {
		return variableName;
	}

	public String getVariableFirstOperandName() {
		return variableFirstOperandName;
	}

	public String getVariableSecondOperandName() {
		return variableSecondOperandName;
	}

	public OperationType getOperation() {
		return operation;
	}
	
}
