package model.Memory;

/**
 * This enum class contains all the valid operation types
 * */
public enum OperationType {
	ADD("+"), SUB("-"), MUL("*"), DIV("/"), MOD("%");
	
	public String symbol;
	
	private OperationType(String symbol) {
		this.symbol = symbol;
	}
}
