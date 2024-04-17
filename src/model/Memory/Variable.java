package model.Memory;

import java.util.Objects;

import model.DebuggerConsole;
import model.Exceptions;

public class Variable{
	
	private static final String referenceTypeMessage = "VAR";
	private VariableType variableType;
	private String name;
	private Object value;
	
	public Variable(VariableType variableType, String name, Object value) {
		super();
		this.variableType = variableType;
		this.name = name;
		
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
		
	}

	public VariableType getType() {
		return variableType;
	}

	public void setType(VariableType variableType) {
		this.variableType = variableType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getValue() {
		return value;
	}

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

	public String getValueString() {
		return value.toString();
	}

	@Override
	public String toString() {
		return "V(" + variableType +", " + name + ", " + value +")";
	}
	
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
