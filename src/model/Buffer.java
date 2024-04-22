package model;

public class Buffer {

	private String s;
	
	public Buffer() {
		s = "";
	}
	
	public void write(String s) {
		this.s += s + "\n";
	}
	
	public String read() {
		return s;
	}
	
}
