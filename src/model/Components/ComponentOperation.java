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
			try {
				switch (finalVariable.getType()) {
				case  Double: {
					variable1.setType(Type.Double);
					variable1.setValue(Double.parseDouble(variableFirstOperandName));
					break;
					}
				case  Integer: {
					variable1.setType(Type.Integer);
					variable1.setValue(Integer.parseInt(variableFirstOperandName));
					break;
					}
				case  String: {
					variable1.setType(Type.String);
					variable1.setValue(variableFirstOperandName);
					break;
					}
				}
			} catch (Exception e2) {
				throw new Exceptions(Exceptions.CONVERSION_ERROR);
			}
		}
		
		if(variableSecondOperandName.startsWith("$")) {
			variable2 = super.getMemory().getVariableByName(variableSecondOperandName.substring(1));
		}else{
			try {
				switch (finalVariable.getType()) {
				case  Double: {
					variable2.setType(Type.Double);
					variable2.setValue(Double.parseDouble(variableSecondOperandName));
					break;
					}
				case  Integer: {
					variable2.setType(Type.Integer);
					variable1.setValue(Integer.parseInt(variableSecondOperandName));
					break;
					}
				case  String: {
					variable2.setType(Type.String);
					variable2.setValue(variableSecondOperandName);
					break;
					}
				}
			} catch (Exception e2) {
				throw new Exceptions(Exceptions.CONVERSION_ERROR);
			}
		}
		
		if(finalVariable.getType().equals(variable1.getType()) && finalVariable.getType().equals(variable1.getType())) {
			
			switch (finalVariable.getType()) {
			case  String: {
				
				if(operation != TipoOperazioni.PIU) {
					throw new Exceptions(Exceptions.STRING_ERROR);
				}
				
				finalVariable.setValue( (String) variable1.getValue() + (String) variable2.getValue() );
				
				break;
				}
			
			default: {
				
				Type temp=finalVariable.getType();
				finalVariable.setType(Type.Double);
				
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
				
				if(temp==Type.Integer) {
					finalVariable.setType(Type.Integer);
					finalVariable.setValue(Integer.parseInt(finalVariable.getValue().toString()));
				}
				
				break;
				}
			}
			
			
		}else {
			throw new Exceptions(Exceptions.UNMATCH_TYPE);
		}
		System.out.println("Executed.");
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
	
	
}
