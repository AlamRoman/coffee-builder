package model.Components;

import model.Exceptions;
import model.Memory.MemoryStorage;
import model.Memory.Type;
import model.Memory.Variable;

public class Condition {
	
	private MemoryStorage MS;
	private String firstTerm;
	private String symbol;
	private String secondTerm;

	public Condition(MemoryStorage MS, String firstTerm, String symbol, String secondTerm) {
		this.firstTerm = firstTerm;
		this.symbol = symbol;
		this.secondTerm = secondTerm;
		this.MS = MS;
	}
	
	public boolean resolve() throws Exceptions {
		Variable firstVar = null;
		Variable secondVar = null;
		if(firstTerm.startsWith("$")){
			firstVar = MS.getVariableByName(firstTerm.substring(1));
		}else {
			firstVar = getVariableFromTerm(firstTerm);
		}
		if(secondTerm.startsWith("$")) {
			secondVar = MS.getVariableByName(secondTerm.substring(1));
		}else {
			secondVar = getVariableFromTerm(secondTerm);
		}
		switch (symbol) {
		case "!": {
			//Controlla se il tipo è un'altra condizione o se il valore ritornato da MS è un intero 0 o 1
			if(firstVar == null) throw new Exceptions(Exceptions.NOT_CONDITION_MISSING_TERM);
			if(firstVar.getType() == Type.Integer || firstVar.getType() == Type.Double) {
				if((Integer)firstVar.getValue() > 0) {
					return !true;
				}else {
					return !false;
				}	
			}else {
				throw new Exceptions(Exceptions.NOT_CONDITION_IS_STRING);
			}
		}
		case ">": {
			//Controlla se sono due numeri, se no, exception
			if(firstVar == null || secondVar == null) {
				throw new Exceptions(Exceptions.MISSING_ARGUMENTS);
			}
			if(firstVar.getType() == Type.Double && secondVar.getType() == Type.Double) {
				return (Double)firstVar.getValue() > (Double)secondVar.getValue();
			}else if(firstVar.getType() == Type.Integer && secondVar.getType() == Type.Integer) {
				return (Integer)firstVar.getValue() > (Integer)secondVar.getValue();
			}else {
				throw new Exceptions(Exceptions.CONDITION_TERMS_NOT_NUMBER);
			}
		}
		case ">=": {
			//Controlla se sono due numeri, se no, exception
			if(firstVar == null || secondVar == null) {
				throw new Exceptions(Exceptions.MISSING_ARGUMENTS);
			}
			if(firstVar.getType() == Type.Double && secondVar.getType() == Type.Double) {
				return (Double)firstVar.getValue() >= (Double)secondVar.getValue();
			}else if(firstVar.getType() == Type.Integer && secondVar.getType() == Type.Integer) {
				return (Integer)firstVar.getValue() >= (Integer)secondVar.getValue();
			}else {
				throw new Exceptions(Exceptions.CONDITION_TERMS_NOT_NUMBER);
			}
		}
		case "<=": {
			//Controlla se sono due numeri, se no, exception
			if(firstVar == null || secondVar == null) {
				throw new Exceptions(Exceptions.MISSING_ARGUMENTS);
			}
			if(firstVar.getType() == Type.Double && secondVar.getType() == Type.Double) {
				return (Double)firstVar.getValue() <= (Double)secondVar.getValue();
			}else if(firstVar.getType() == Type.Integer && secondVar.getType() == Type.Integer) {
				return (Integer)firstVar.getValue() <= (Integer)secondVar.getValue();
			}else {
				throw new Exceptions(Exceptions.CONDITION_TERMS_NOT_NUMBER);
			}
		}
		case "<": {
			//Controlla se sono due numeri, se no, exception
			if(firstVar == null || secondVar == null) {
				throw new Exceptions(Exceptions.MISSING_ARGUMENTS);
			}
			if(firstVar.getType() == Type.Double && secondVar.getType() == Type.Double) {
				return (Double)firstVar.getValue() < (Double)secondVar.getValue();
			}else if(firstVar.getType() == Type.Integer && secondVar.getType() == Type.Integer) {
				return (Integer)firstVar.getValue() < (Integer)secondVar.getValue();
			}else {
				throw new Exceptions(Exceptions.CONDITION_TERMS_NOT_NUMBER);
			}
		}
		case "==": {
			//Se sono numeri fai esegui la semplice operazione, se sono stringhe usa .equals tra i due valori
			if(firstVar == null || secondVar == null) {
				throw new Exceptions(Exceptions.MISSING_ARGUMENTS);
			}
			if(firstVar.getType() == Type.Double && secondVar.getType() == Type.Double) {
				return (Double)firstVar.getValue() == (Double)secondVar.getValue();
			}else if(firstVar.getType() == Type.Integer && secondVar.getType() == Type.Integer) {
				return (Integer)firstVar.getValue() == (Integer)secondVar.getValue();
			}else {
				String s = firstVar.getValueString();
				String _s = secondVar.getValueString();
				return s.equals(_s);
			}
		}
		default:
			throw new Exceptions(Exceptions.INVALID_CONDITION_SYMBOL);
		}
	}

	private Variable getVariableFromTerm(String term) {
		String cleanedTerm = term.trim().toLowerCase();
		if (cleanedTerm.matches(".*[a-z].*") && cleanedTerm.matches("[a-z0-9]+")) {
            return new Variable(Type.String, term, term);
        }
		if (cleanedTerm.matches("\\d+")) {
            int integerValue = Integer.parseInt(term);
            return new Variable(Type.Integer, term, integerValue);
        }
		if (cleanedTerm.matches("-?\\d*\\.\\d+")) {
            double doubleValue = Double.parseDouble(term);
            return new Variable(Type.Double, term, doubleValue);
        }
		return null;
	}
	
}
