package model.Components;

import model.DebuggerConsole;
import model.Exceptions;
import model.Memory.MemoryStorage;
import model.Memory.Type;
import model.Memory.Variable;

public class Condition {
	
	private static final String referenceTypeMessage = "CONDITION";
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
		DebuggerConsole.getInstance().printDefaultInfoLog(referenceTypeMessage , "Resolving the condition: " + this.toString());
		Variable firstVar = null;
		Variable secondVar = null;
		if(firstTerm != null) {
			if(firstTerm.startsWith("$")){
				DebuggerConsole.getInstance().printDefaultInfoLog(referenceTypeMessage , "Term1: variable");
				firstVar = MS.getVariableByName(firstTerm.substring(1));
			}else {
				DebuggerConsole.getInstance().printDefaultInfoLog(referenceTypeMessage , "Term1: not a variable");
				firstVar = getVariableFromTerm(firstTerm);
			}			
		}
		if(secondTerm != null) {
			if(secondTerm.startsWith("$")) {
				DebuggerConsole.getInstance().printDefaultInfoLog(referenceTypeMessage , "Term2: variable");
				secondVar = MS.getVariableByName(secondTerm.substring(1));
			}else {
				DebuggerConsole.getInstance().printDefaultInfoLog(referenceTypeMessage , "Term2: not a variable");
				secondVar = getVariableFromTerm(secondTerm);
			}		
		}
		if(symbol == null) {
			symbol = "";
		}
		switch (symbol) {
		case "!": {
			DebuggerConsole.getInstance().printDefaultInfoLog(referenceTypeMessage , "Operand NOT");
			//Controlla se il tipo è un'altra condizione o se il valore ritornato da MS è un intero 0 o 1
			if(firstVar == null) throw new Exceptions(Exceptions.MISSING_ARGUMENTS);
			if(firstVar.getType() == Type.Integer || firstVar.getType() == Type.Double) {
				if((Integer)firstVar.getValue() > 0) {
					DebuggerConsole.getInstance().printDefaultSuccessLog(referenceTypeMessage , "Returning false");
					return !true;
				}else {
					DebuggerConsole.getInstance().printDefaultSuccessLog(referenceTypeMessage , "Returning true");
					return !false;
				}	
			}else {
				throw new Exceptions(Exceptions.TERM_IS_STRING);
			}
		}
		case ">": {
			DebuggerConsole.getInstance().printDefaultInfoLog(referenceTypeMessage , "Operand GREATER");
			//Controlla se sono due numeri, se no, exception
			if(firstVar == null || secondVar == null) {
				throw new Exceptions(Exceptions.MISSING_ARGUMENTS);
			}
			if(firstVar.getType() == Type.Double && secondVar.getType() == Type.Double) {
				DebuggerConsole.getInstance().printDefaultSuccessLog(referenceTypeMessage , "Returning " + ((Double)firstVar.getValue() > (Double)secondVar.getValue()));
				return (Double)firstVar.getValue() > (Double)secondVar.getValue();
			}else if(firstVar.getType() == Type.Integer && secondVar.getType() == Type.Integer) {
				DebuggerConsole.getInstance().printDefaultSuccessLog(referenceTypeMessage , "Returning " + ((Integer)firstVar.getValue() > (Integer)secondVar.getValue()));
				return (Integer)firstVar.getValue() > (Integer)secondVar.getValue();
			}else {
				throw new Exceptions(Exceptions.CONDITION_TERMS_NOT_NUMBER);
			}
		}
		case ">=": {
			DebuggerConsole.getInstance().printDefaultInfoLog(referenceTypeMessage , "Operand GREATER OR EQUAL THAN");
			//Controlla se sono due numeri, se no, exception
			if(firstVar == null || secondVar == null) {
				throw new Exceptions(Exceptions.MISSING_ARGUMENTS);
			}
			if(firstVar.getType() == Type.Double && secondVar.getType() == Type.Double) {
				DebuggerConsole.getInstance().printDefaultSuccessLog(referenceTypeMessage , "Returning " + ((Double)firstVar.getValue() >= (Double)secondVar.getValue()));
				return (Double)firstVar.getValue() >= (Double)secondVar.getValue();
			}else if(firstVar.getType() == Type.Integer && secondVar.getType() == Type.Integer) {
				DebuggerConsole.getInstance().printDefaultSuccessLog(referenceTypeMessage , "Returning " + ((Integer)firstVar.getValue() >= (Integer)secondVar.getValue()));
				return (Integer)firstVar.getValue() >= (Integer)secondVar.getValue();
			}else {
				throw new Exceptions(Exceptions.CONDITION_TERMS_NOT_NUMBER);
			}
		}
		case "<=": {
			DebuggerConsole.getInstance().printDefaultInfoLog(referenceTypeMessage , "Operand LESS OR EQUAL THAN");
			//Controlla se sono due numeri, se no, exception
			if(firstVar == null || secondVar == null) {
				throw new Exceptions(Exceptions.MISSING_ARGUMENTS);
			}
			if(firstVar.getType() == Type.Double && secondVar.getType() == Type.Double) {
				DebuggerConsole.getInstance().printDefaultSuccessLog(referenceTypeMessage , "Returning " + ((Double)firstVar.getValue() <= (Double)secondVar.getValue()));
				return (Double)firstVar.getValue() <= (Double)secondVar.getValue();
			}else if(firstVar.getType() == Type.Integer && secondVar.getType() == Type.Integer) {
				DebuggerConsole.getInstance().printDefaultSuccessLog(referenceTypeMessage , "Returning " + ((Integer)firstVar.getValue() <= (Integer)secondVar.getValue()));
				return (Integer)firstVar.getValue() <= (Integer)secondVar.getValue();
			}else {
				throw new Exceptions(Exceptions.CONDITION_TERMS_NOT_NUMBER);
			}
		}
		case "<": {
			DebuggerConsole.getInstance().printDefaultInfoLog(referenceTypeMessage , "Operand LOWER");
			//Controlla se sono due numeri, se no, exception
			if(firstVar == null || secondVar == null) {
				throw new Exceptions(Exceptions.MISSING_ARGUMENTS);
			}
			if(firstVar.getType() == Type.Double && secondVar.getType() == Type.Double) {
				DebuggerConsole.getInstance().printDefaultSuccessLog(referenceTypeMessage , "Returning " + ((Double)firstVar.getValue() < (Double)secondVar.getValue()));
				return (Double)firstVar.getValue() < (Double)secondVar.getValue();
			}else if(firstVar.getType() == Type.Integer && secondVar.getType() == Type.Integer) {
				DebuggerConsole.getInstance().printDefaultSuccessLog(referenceTypeMessage , "Returning " + ((Integer)firstVar.getValue() < (Integer)secondVar.getValue()));
				return (Integer)firstVar.getValue() < (Integer)secondVar.getValue();
			}else {
				throw new Exceptions(Exceptions.CONDITION_TERMS_NOT_NUMBER);
			}
		}
		case "==": {
			DebuggerConsole.getInstance().printDefaultInfoLog(referenceTypeMessage , "Operand EQUALS");
			//Se sono numeri fai esegui la semplice operazione, se sono stringhe usa .equals tra i due valori
			if(firstVar == null || secondVar == null) {
				throw new Exceptions(Exceptions.MISSING_ARGUMENTS);
			}
			if(firstVar.getType() == Type.Double && secondVar.getType() == Type.Double) {
				DebuggerConsole.getInstance().printDefaultSuccessLog(referenceTypeMessage , "Returning " + ((Double)firstVar.getValue() == (Double)secondVar.getValue()));
				return (Double)firstVar.getValue() == (Double)secondVar.getValue();
			}else if(firstVar.getType() == Type.Integer && secondVar.getType() == Type.Integer) {
				DebuggerConsole.getInstance().printDefaultSuccessLog(referenceTypeMessage , "Returning " + ((Integer)firstVar.getValue() == (Integer)secondVar.getValue()));
				return (Integer)firstVar.getValue() == (Integer)secondVar.getValue();
			}else {
				String s = firstVar.getValueString();
				String _s = secondVar.getValueString();
				return s.equals(_s);
			}
		}
		default:
			if(firstVar == null) throw new Exceptions(Exceptions.MISSING_ARGUMENTS);
			if(firstVar.getType() == Type.Integer || firstVar.getType() == Type.Double) {
				if((Integer)firstVar.getValue() > 0) {
					return true;
				}else {
					return false;
				}	
			}else {
				if(firstVar.getType() == Type.String) {
					throw new Exceptions(Exceptions.TERM_IS_STRING);
				}
			}
			throw new Exceptions(Exceptions.INVALID_CONDITION_SYMBOL);
		}
	}

	private Variable getVariableFromTerm(String term) {
		if(term == null) return null;
		DebuggerConsole.getInstance().printDefaultInfoLog(referenceTypeMessage , "Getting variable from term/value: '" + term + "'");
		String cleanedTerm = term.trim().toLowerCase();
		Variable v = null;
		if (cleanedTerm.matches(".*[a-z].*") && cleanedTerm.matches("[a-z0-9]+")) {
            v = new Variable(Type.String, term, term);
        }
		if (cleanedTerm.matches("\\d+")) {
            int integerValue = Integer.parseInt(term);
            v = new Variable(Type.Integer, term, integerValue);
        }
		if (cleanedTerm.matches("-?\\d*\\.\\d+")) {
            double doubleValue = Double.parseDouble(term);
            v = new Variable(Type.Double, term, doubleValue);
        }
		DebuggerConsole.getInstance().printDefaultSuccessLog(referenceTypeMessage, "Returning variable: " + v);
		return v;
	}

	@Override
	public String toString() {
		return "Condition [MS=" + MS + ", firstTerm=" + firstTerm + ", symbol=" + symbol + ", secondTerm=" + secondTerm
				+ "]";
	}
	
	
	
}
