package model.Memory;

public enum TipoOperazioni {
	PIU("+"), MENO("-"), PER("*"), DIV("/"), MOD("%");
	
	public String symbol;
	
	private TipoOperazioni(String symbol) {
		this.symbol = symbol;
	}
}
