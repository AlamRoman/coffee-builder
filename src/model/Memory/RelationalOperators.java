package model.Memory;

public enum RelationalOperators {
	
	NOT("!"),
	GREATER_THAN(">"),
	LESS_THAN("<"),
	EQUAL_TO("=="),
	NOT_EQUAL_TO("!="),
	GREATER_THAN_EQUAL_TO(">="),
	LESS_THAN_EQUAL_TO("<=");

	public final String symbol;
	
	private RelationalOperators(String symbol) {
		this.symbol = symbol;
	}
}
