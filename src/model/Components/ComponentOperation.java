package model.Components;

import model.Exceptions;
import model.Memory.MemoryStorage;
import model.Memory.TipoOperazioni;
import model.Memory.Type;
import model.Memory.Variable;

public class ComponentOperation extends Component{

	private String variableName;
	private String variableFirstOperandName;
	private String variableSecondOperandName;
	private TipoOperazioni operation;
	
	private Variable finalVariable;
	private Variable variable1;
	private Variable variable2;

	public ComponentOperation(Component nextComponent1, Component nextComponent2, MemoryStorage memory) {
		
		super(nextComponent1, nextComponent2, memory);
		
		operation=null;
		variableFirstOperandName="";
		variableSecondOperandName="";
		variableName="";
		variable1=null;
		variable2=null;
		finalVariable=null;
	
	}

	public void set(String variableName, String variableFirstOperandName, String variableSecondOperandName, TipoOperazioni operation) {
		
		this.variableName = variableName;
		this.variableFirstOperandName = variableFirstOperandName;
		this.variableSecondOperandName = variableSecondOperandName;
		this.operation = operation;
		
	}
	
	public Object execute() throws Exceptions {
		
		System.out.println("Executing: " + this.getClass().getSimpleName());
		
		finalVariable = super.getMemory().getVariableByName(variableName);
		
		
		
		if(variableFirstOperandName.startsWith("$")) {
			
			variable1 = super.getMemory().getVariableByName(variableFirstOperandName.substring(1));
			
		}else{
			
			System.out.println("[C-Op] : Checking if '" + finalVariable.getType() + "' is valid (Double, Integer, String)");
			variable1 = getVariableFromTerm(variableFirstOperandName);
			if(variable1==null) {
				variable1 = new Variable(Type.String, "" , variableSecondOperandName);
			}
			
		}
		
		
		
		
		if(variableSecondOperandName.startsWith("$")) {
			
			variable2 = super.getMemory().getVariableByName(variableSecondOperandName.substring(1));
		
		}else{
			
			System.out.println("[C-Op] : Checking if '" + finalVariable.getType() + "' is valid (Double, Integer, String)");
			variable2 = getVariableFromTerm(variableSecondOperandName);
		
			if(variable2==null) {
				variable2 = new Variable(Type.String, "" , variableSecondOperandName);
			}
			
		}
		
		
		if(finalVariable.getType().equals(variable1.getType()) && finalVariable.getType().equals(variable2.getType())) {
			
			switch (finalVariable.getType()) {
			
			case  String: {
				
				if(operation != TipoOperazioni.PIU) {
					throw new Exceptions(Exceptions.STRING_ERROR);
				}
				
				finalVariable.setValue( variable1.getValue().toString() + variable2.getValue().toString() );
				
				break;
				
				}
			
			case  Integer: {
				
				switch (operation) {
				case  PIU: {
					finalVariable.setValue( (int) variable1.getValue() + (int) variable2.getValue() );
					break;
					}
				case  MENO: {
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
				case  PER: {
					finalVariable.setValue( (int) variable1.getValue() * (int) variable2.getValue() );
					break;
					}
				}
				
				break;
				
			}
			
			case Double: {
				
				switch (operation) {
				case  PIU: {
					finalVariable.setValue( (Double) variable1.getValue() + (Double) variable2.getValue() );
					break;
					}
				case  MENO: {
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
				case  PER: {
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
		
		System.out.println("Executed.");
		
		super.getMemory().showMemory();
		
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
		case  PIU: {
			out+="+";
			break;
			}
		case  MENO: {
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
		case  PER: {
			out+="*";
			break;
			}
		}
		
		out+=" ";
		out+=variableSecondOperandName;
		
		return out;
	}
	
	private Variable getVariableFromTerm(String term) {
		
		if(term == null) return null;
		
		String cleanedTerm = term.trim().toLowerCase();
		
		if (cleanedTerm.matches(".*[a-z].*") && cleanedTerm.matches("[a-z0-9]+")) {
            return new Variable(Type.String, term, term);
        }
		
		if (cleanedTerm.matches("-?\\d*\\.\\d+")) {
            double doubleValue = Double.parseDouble(term);
            return new Variable(Type.Double, term, doubleValue);
        }
		
		if (cleanedTerm.matches("\\d+")) {
            int integerValue = Integer.parseInt(term);
            return new Variable(Type.Integer, term, integerValue);
        }
		
		return null;
	}
	
	
}
