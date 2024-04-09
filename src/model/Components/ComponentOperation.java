package model.Components;

import model.Exceptions;
import model.Memory.MemoryStorage;
import model.Memory.TipoOperazioni;
import model.Memory.Type;
import model.Memory.Variable;

public class ComponentOperation extends AlgorithmComponent{

	private String finalVariableName;
	private String variable1Name;
	private String variable2Name;
	private TipoOperazioni operation;
	
	private Variable finalVariable;
	private Variable variable1;
	private Variable variable2;

	public ComponentOperation(AlgorithmComponent nextComponent1, AlgorithmComponent nextComponent2, MemoryStorage memory) {
		
		super(nextComponent1, nextComponent2, memory);
		
		operation=null;
		variable1Name="";
		variable2Name="";
		finalVariableName="";
		
	}

	public void set(String finalVariableName, String variable1Name, String variable2Name, TipoOperazioni operation) {
		
		this.finalVariableName = finalVariableName;
		this.variable1Name = variable1Name;
		this.variable2Name = variable2Name;
		this.operation = operation;
		
	}
	
	public void execute() throws Exceptions {
		
		finalVariable = super.getMemory().getVariableByName(finalVariableName);
		
		try {
			variable1 = super.getMemory().getVariableByName(variable1Name);
		} catch (Exception e) {
			variable1.setValue(null);
			try {
				switch (finalVariable.getType()) {
				case  Double: {
					variable1.setType(Type.Double);
					variable1.setValue(Double.parseDouble(variable1Name));
					break;
					}
				case  Integer: {
					variable1.setType(Type.Integer);
					variable1.setValue(Integer.parseInt(variable1Name));
					break;
					}
				case  String: {
					variable1.setType(Type.String);
					variable1.setValue(variable1Name);
					break;
					}
				}
				if(variable1.getValue()==null) {
					throw new Exceptions(Exceptions.NON_EXISTING_VARIABLE);
				}
			} catch (Exception e2) {
				throw new Exceptions(Exceptions.CONVERSION_ERROR);
			}
			
		}
		

		try {
			variable2 = super.getMemory().getVariableByName(variable2Name);
		} catch (Exception e) {
			variable2.setValue(null);
			try {
				switch (finalVariable.getType()) {
				case  Double: {
					variable2.setType(Type.Double);
					variable2.setValue(Double.parseDouble(variable1Name));
					break;
					}
				case  Integer: {
					variable2.setType(Type.Integer);
					variable2.setValue(Integer.parseInt(variable1Name));
					break;
					}
				case  String: {
					variable2.setType(Type.String);
					variable2.setValue(variable1Name);
					break;
					}
				}
				if(variable2.getValue()==null) {
					throw new Exceptions(Exceptions.NON_EXISTING_VARIABLE);
				}
			} catch (Exception e2) {
				throw new Exceptions(Exceptions.CONVERSION_ERROR);
			}
			
		}
		
		
		if(finalVariable.getType().equals(variable1.getType()) && finalVariable.getType().equals(variable1.getType())) {
			
			switch (finalVariable.getType()) {
			case  String: {
				
				if(operation != TipoOperazioni.PIU) {
					throw new Exceptions(Exceptions.NON_EXISTING_VARIABLE);
				}
				
					finalVariable.setValue( variable1.getValue() + variable2.getValue() );
				
				break;
				}
			
			default: {
				
				switch (operation) {
				case  PIU: {
					finalVariable.setValue( variable1.getValue() + variable2.getValue() );
					break;
					}
				case  MENO: {
					finalVariable.setValue( variable1.getValue() - variable2.getValue() );
					break;
					}
				case  DIV: {
					finalVariable.setValue( variable1.getValue() / variable2.getValue() );
					break;
					}
				case  MOD: {
					finalVariable.setValue( variable1.getValue() % variable2.getValue() );
					break;
					}
				case  PER: {
					finalVariable.setValue( variable1.getValue() * variable2.getValue() );
					break;
					}
				}
				
				break;
				}
			}
			
			
		}else {
			throw new Exceptions(Exceptions.UNMATCH_TYPE);
		}
		
	}
	
	
	
}
