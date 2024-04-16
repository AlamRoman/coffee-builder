package model.Memory;

import java.util.Objects;

import model.DebuggerConsole;
import model.Exceptions;

public class Variable{
	
	private static final String referenceTypeMessage = "VAR";
	private Type type;
	private String name;
	private Object value;
	
	public Variable(Type type, String name, Object value) {
		super();
		this.type = type;
		this.name = name;
		
		switch (type) {
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

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
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
		
		switch (type) {
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
		return Objects.hash(name, type, value);
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
		return "V(" + type +", " + name + ", " + value +")";
	}
	
	public static Type getTypeFromValue(Object value) throws Exceptions{
		if (value instanceof Integer) {
			return Type.Integer;
		} else if (value instanceof String) {
			return Type.String;
		} else if (value instanceof Double) {
			return Type.Double;
		} else {
			throw new Exceptions(Exceptions.UNKNOWN_TYPE);
		}
	}
}
