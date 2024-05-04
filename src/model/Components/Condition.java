package model.Components;

import java.util.ArrayList;
import java.util.regex.Pattern;

import model.Color;
import model.DebuggerConsole;
import model.Exceptions;
import model.Memory.MemoryStorage;
import model.Memory.VariableType;
import view.FlowChartContentPanel;
import model.Memory.Variable;

/**<p>
 * This class represents the condition component. 
 * </p>
 * This component, contains a condition that is going to be either true or false, and will be used in the conditional components:
 * <ul>
 * <li>{@link ComponentWhile}</li>
 * <li>{@link ComponentIf}</li>
 * </ul>
 */
public class Condition {
	
	private static final String referenceTypeMessage = "CONDITION";
	private MemoryStorage MS;
	private String firstTerm;
	private String symbol;
	private String secondTerm;

	/**<p>
	* The constructor of the class {@link Condition}
	* </p>
	* @param MS The memory instance shared in the program
	* @param firstTerm The first term of the condition
	* @param symbol The symbol of the condition
	* @param secondTerm The second term of the condition
	*/
	public Condition(MemoryStorage MS, String firstTerm, String symbol, String secondTerm) {
		this.firstTerm = firstTerm;
		this.symbol = symbol;
		this.secondTerm = secondTerm;
		this.MS = MemoryStorage.getInstance();
	}
	
	/**<p>
	* This method resolves the condition and returns either true or false
	* </p>
	* <p>
	* This method resolves the condition, by the parameters that were given by the user during the configuration, and returns either true or false
	* </p>
	* @return {@link Boolean} the result of the condition
	* @throws Exceptions MISSING_ARGUMENTS : If there are missing arguments
	* @throws Exceptions TERM_IS_STRING : If there's an invalid conversion where one of the terms is a String
	* @throws Exceptions INVALID_CONDITION_SYMBOL : If there's an invalid conditional operator
	* @throws Exceptions CONDITION_TERMS_NOT_NUMBER : If one or both terms are Strings and the operator is (&gt;, &lt;, &gt;= or &gt;=)
	*/
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
			if(firstVar.getType() == VariableType.Integer || firstVar.getType() == VariableType.Double) {
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
			if(firstVar.getType() == VariableType.Double && secondVar.getType() == VariableType.Double) {
				DebuggerConsole.getInstance().printDefaultSuccessLog(referenceTypeMessage , "Returning (" + ((Double)firstVar.getValue()) + " > " + ((Double)secondVar.getValue()) + " = " +  ((Double)firstVar.getValue() > (Double)secondVar.getValue()));
				return (Double)firstVar.getValue() > (Double)secondVar.getValue();
			}else if(firstVar.getType() == VariableType.Integer && secondVar.getType() == VariableType.Integer) {
				DebuggerConsole.getInstance().printDefaultSuccessLog(referenceTypeMessage , "Returning (" + ((Integer)firstVar.getValue()) + " > " + ((Integer)secondVar.getValue()) + " = " +  ((Integer)firstVar.getValue() > (Integer)secondVar.getValue()));
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
			if(firstVar.getType() == VariableType.Double && secondVar.getType() == VariableType.Double) {
				DebuggerConsole.getInstance().printDefaultSuccessLog(referenceTypeMessage , "Returning (" + ((Double)firstVar.getValue()) + " >= " + ((Double)secondVar.getValue()) + " = " +  ((Double)firstVar.getValue() >= (Double)secondVar.getValue()));
				return (Double)firstVar.getValue() >= (Double)secondVar.getValue();
			}else if(firstVar.getType() == VariableType.Integer && secondVar.getType() == VariableType.Integer) {
				DebuggerConsole.getInstance().printDefaultSuccessLog(referenceTypeMessage , "Returning (" + ((Integer)firstVar.getValue()) + " >= " + ((Integer)secondVar.getValue()) + " = " +  ((Integer)firstVar.getValue() >= (Integer)secondVar.getValue()));
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
			if(firstVar.getType() == VariableType.Double && secondVar.getType() == VariableType.Double) {
				DebuggerConsole.getInstance().printDefaultSuccessLog(referenceTypeMessage , "Returning (" + ((Double)firstVar.getValue()) + " <= " + ((Double)secondVar.getValue()) + " = " +  ((Double)firstVar.getValue() <= (Double)secondVar.getValue()));
				return (Double)firstVar.getValue() <= (Double)secondVar.getValue();
			}else if(firstVar.getType() == VariableType.Integer && secondVar.getType() == VariableType.Integer) {
				DebuggerConsole.getInstance().printDefaultSuccessLog(referenceTypeMessage , "Returning (" + ((Integer)firstVar.getValue()) + " <= " + ((Integer)secondVar.getValue()) + " = " +  ((Integer)firstVar.getValue() <= (Integer)secondVar.getValue()));
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
			if(firstVar.getType() == VariableType.Double && secondVar.getType() == VariableType.Double) {
				DebuggerConsole.getInstance().printDefaultSuccessLog(referenceTypeMessage , "Returning (" + ((Double)firstVar.getValue()) + " < " + ((Double)secondVar.getValue()) + " = " +  ((Double)firstVar.getValue() < (Double)secondVar.getValue()));
				return (Double)firstVar.getValue() < (Double)secondVar.getValue();
			}else if(firstVar.getType() == VariableType.Integer && secondVar.getType() == VariableType.Integer) {
				DebuggerConsole.getInstance().printDefaultSuccessLog(referenceTypeMessage , "Returning (" + ((Integer)firstVar.getValue()) + " < " + ((Integer)secondVar.getValue()) + " = " +  ((Integer)firstVar.getValue() < (Integer)secondVar.getValue()));
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
			if(firstVar.getType() == VariableType.Double && secondVar.getType() == VariableType.Double) {
				DebuggerConsole.getInstance().printDefaultSuccessLog(referenceTypeMessage , "Returning (" + ((Double)firstVar.getValue()) + " == " + ((Double)secondVar.getValue()) + " = " +  ((Double)firstVar.getValue() == (Double)secondVar.getValue()));
				return (Double)firstVar.getValue() == (Double)secondVar.getValue();
			}else if(firstVar.getType() == VariableType.Integer && secondVar.getType() == VariableType.Integer) {
				DebuggerConsole.getInstance().printDefaultSuccessLog(referenceTypeMessage , "Returning (" + ((Integer)firstVar.getValue()) + " == " + ((Integer)secondVar.getValue()) + " = " +  ((Integer)firstVar.getValue() == (Integer)secondVar.getValue()));
				return (Integer)firstVar.getValue() == (Integer)secondVar.getValue();
			}else {
				String s = firstVar.getValueString();
				String _s = secondVar.getValueString();
				return s.equals(_s);
			}
		}
		default:
			if(firstVar == null) throw new Exceptions(Exceptions.MISSING_ARGUMENTS);
			if(firstVar.getType() == VariableType.Integer || firstVar.getType() == VariableType.Double) {
				if((Integer)firstVar.getValue() > 0) {
					return true;
				}else {
					return false;
				}	
			}else {
				if(firstVar.getType() == VariableType.String) {
					throw new Exceptions(Exceptions.TERM_IS_STRING);
				}
			}
			throw new Exceptions(Exceptions.INVALID_CONDITION_SYMBOL);
		}
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
	private Variable getVariableFromTerm(String term) {
		if(term == null) return null;
		DebuggerConsole.getInstance().printDefaultInfoLog(referenceTypeMessage , "Getting variable from term/value: '" + term + "'");
		String cleanedTerm = term.trim().toLowerCase();
		Variable v = null;
		if (cleanedTerm.matches(".*[a-z].*") && cleanedTerm.matches("[a-z0-9]+")) {
            v = new Variable(VariableType.String, term, term);
        }
		if (cleanedTerm.matches("\\d+")) {
            int integerValue = Integer.parseInt(term);
            v = new Variable(VariableType.Integer, term, integerValue);
        }
		if (cleanedTerm.matches("-?\\d*\\.\\d+")) {
            double doubleValue = Double.parseDouble(term);
            v = new Variable(VariableType.Double, term, doubleValue);
        }
		DebuggerConsole.getInstance().printDefaultSuccessLog(referenceTypeMessage, "Returning variable: " + v);
		return v;
	}
	
	@Override
	public String toString() {
		String out = "";
		if(firstTerm != null && symbol.equals("!")) {
			out = symbol + firstTerm;
		}else {
			out = firstTerm + " " + symbol + " " + secondTerm;
		}
		DebuggerConsole.getInstance().printCustomMSGColorLog(referenceTypeMessage + "-PRINT-OUTPUT", Color.PURPLE, "Showing: '" + out + "' to the panel");
		return out;
	}
	
	/**<p>
	* This method returns the same info given by the {@link Condition#toString()} but translated for the conversions to the different programming languages
	* </p>
	* <ul>
	* <li>Java</li>
	* <li>Python</li>
	* <li>PseudoCode</li>
	* </ul>
	* @param s The term that has to be translated to code String
	* @return {@link String} The result
	*/
	public String getCodeString(String s){
		
		String outPutText = "";
		
			if(s!="") {
				String[] words = s.split(" ");
				
				for (int i = 0; i < words.length; i++) {
					
					int count = words[i].split(Pattern.quote(String.valueOf('$'))).length - 1;
					
					if(!words[i].equals("")) {
						if (words[i].charAt(0) == '$' && count == 1) {
							
							words[i] = words[i].substring(1);
							
						}else {
							words[i] = getStringFromTerm(words[i]);
						}
					}else {
						words[i] = "\"\"";
					}
				}
				
				outPutText = String.join(" + ", words);
				outPutText = outPutText.replace("\" + \"", " ");
				outPutText = outPutText.replace(" + \"",  " + \" ");
				outPutText = outPutText.replace("\" + ",  " \" + ");
			}
			return outPutText;
	}
	
	/**<p>
	* Returns a conversion of the provided string
	* </p>
	* @return String The conversion result
	*/
    private static String getStringFromTerm(String string) {
        // Check if the string contains only digits\
    	if(string.equals("") || string == null) return "\"\"";
        if (string.matches("\\d+")) {
            // Convert the string to integer
            return Integer.parseInt(string) + "";
        }

        // Check if the string contains only digits and a dot
        if (string.matches("\\d+\\.\\d+")) {
            // Convert the string to double
            return Double.parseDouble(string) + "";
        }

        // Otherwise, return the original string
        return string;
    }

    /** <p>
	* This method translates the informations contained in the condition to different programming languages such as Python, Java and PseudoCode
	* </p>
	* @return {@link String} The final translation
	**/
	public String printCode() {
		String op1 = getCodeString(firstTerm);
		String op2 = getCodeString(secondTerm);
		String operand = symbol;
		if(firstTerm != null && symbol.equals("!")) {
			return operand + op1;
		}else {
			return op1 + " " + operand + " " + op2;
		}
	}
	
	
}
