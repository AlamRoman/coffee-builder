package model.Memory;

public enum OperationType {
	ADD("+"), SUB("-"), MUL("*"), DIV("/"), MOD("%");
	
	public String symbol;
	
	private OperationType(String symbol) {
		this.symbol = symbol;
	}
}
