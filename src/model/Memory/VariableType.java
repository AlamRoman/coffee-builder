package model.Memory;

/**
 * This enum class contains all the valid variable types
 * */
public enum VariableType {
	Integer("Integer"), String("String"), Double("Double");
	
	public String name;
	
	private VariableType(String name) {
		this.name = name;
	}
}
