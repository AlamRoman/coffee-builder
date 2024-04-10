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
		
		if(variable1Name.startsWith("$")) {
			variable1 = super.getMemory().getVariableByName(variable1Name.substring(1));
		}else{
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
			} catch (Exception e2) {
				throw new Exceptions(Exceptions.CONVERSION_ERROR);
			}
		}
		
		if(variable2Name.startsWith("$")) {
			variable2 = super.getMemory().getVariableByName(variable2Name.substring(1));
		}else{
			try {
				switch (finalVariable.getType()) {
				case  Double: {
					variable2.setType(Type.Double);
					variable2.setValue(Double.parseDouble(variable2Name));
					break;
					}
				case  Integer: {
					variable2.setType(Type.Integer);
					variable1.setValue(Integer.parseInt(variable2Name));
					break;
					}
				case  String: {
					variable2.setType(Type.String);
					variable2.setValue(variable2Name);
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
		
	}
	
	
	
}
