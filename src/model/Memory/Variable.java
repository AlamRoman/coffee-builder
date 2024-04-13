package model.Memory;

import java.util.Objects;

public class Variable{
	
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
	
	
}
