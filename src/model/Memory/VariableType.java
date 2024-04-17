package model.Memory;

public enum VariableType {
	Integer("Integer"), String("String"), Double("Double");
	
	public String name;
	
	private VariableType(String name) {
		this.name = name;
	}
}
