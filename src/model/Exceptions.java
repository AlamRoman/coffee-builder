package model;

public class Exceptions extends Exception{
	
	public static final String EXISTING_VARIABLE = "The variable already exists, choose another name";
	public static final String NON_EXISTING_VARIABLE = "The variable not exists, try whit another name";

	public Exceptions(String s) {
		super(s);
	}
	
}
