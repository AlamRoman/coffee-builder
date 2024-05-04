package model.Memory;

import java.util.Objects;

import model.DebuggerConsole;
import model.Exceptions;
import model.Components.AlgorithmComponent;
import view.editComponents.EditInput;

/**<p>
* This class is used to create logic Variables that contain the type of the value, the variable name and the value of the variable
* </p>
*/
public class Variable{
	
	private static final String referenceTypeMessage = "VAR";
	private VariableType variableType;
	private String name;
	private Object value;
	
	/**<p>
	* The constructor of the class {@link Variable}
	* </p>
	* @param variableType The type of the variable
	* @param name The name of the variable
	* @param value The value stored in the variable
	*/
	public Variable(VariableType variableType, String name, Object value) {
		super();
		this.variableType = variableType;
		this.name = name;
		
		if (value != null) {
			switch (variableType) {
				case Integer: {
					this.value = (Integer) value;
					break;
				}
				case String: {
					this.value = (String) value;			
						break;
				}
				case Double: {
					this.value = (Double) value;
					break;
				}
			}
		}else {
			this.value = null;
		}
		
	}

	/**
	 * <p>
	 * This method returns the type of the variable
	 * </p>
	 * @return {@link VariableType} The variable type
	 * */
	public VariableType getType() {
		return variableType;
	}
	
	/**
	 * <p>
	 * This method sets the type of the variable
	 * </p>
	 * @param variableType The new type
	 * */
	public void setType(VariableType variableType) {
		this.variableType = variableType;
	}

	/**
	 * <p>
	 * This method returns the name of the variable
	 * </p>
	 * @return {@link String} The variable name
	 * */
	public String getName() {
		return name;
	}

	/**
	 * <p>
	 * This method sets the name of the variable
	 * </p>
	 * @param name The new name
	 * */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * <p>
	 * This method returns the value of the variable
	 * </p>
	 * @return {@link Object} The value of the variable
	 * */
	public Object getValue() {
		return value;
	}

	/**
	 * <p>
	 * This method sets the value of the variable
	 * </p>
	 * @param value The new value
	 * */
	public void setValue(Object value) {
		
		switch (variableType) {
			case Integer: {
				this.value = (Integer) value;
				break;
			}
			case String: {
				this.value = (String) value;			
					break;
			}
			case Double: {
				this.value = (Double) value;
				break;
			}
		}
		DebuggerConsole.getInstance().printDefaultInfoLog(referenceTypeMessage , "Assigned.");
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, variableType, value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Variable other = (Variable) obj;
		return Objects.equals(name, other.name);
	}

	/**
	 * <p>
	 * This method returns the value of the variable as a string
	 * </p>
	 * @return {@link String} The variable value
	 * */
	public String getValueString() {
		return value.toString();
	}

	@Override
	public String toString() {
		return "V(" + variableType +", " + name + ", " + value +")";
	}
	
	/**
	 * <p>
	 * This method returns the type of a value given as a parameter
	 * </p>
	 * @param value The value that has to be inspected
	 * @return {@link VariableType} The type
	 * */
	public static VariableType getTypeFromValue(Object value) throws Exceptions{
		if (value instanceof Integer) {
			return VariableType.Integer;
		} else if (value instanceof String) {
			return VariableType.String;
		} else if (value instanceof Double) {
			return VariableType.Double;
		} else {
			throw new Exceptions(Exceptions.UNKNOWN_TYPE);
		}
	}
}
