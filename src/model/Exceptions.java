package model;

public class Exceptions extends Exception{
	
	public static final String COMPONENT_NOT_SET = "Set the component before executing the algorithm";
	public static final String EXISTING_VARIABLE = "The variable already exists, choose another name";
	public static final String NON_EXISTING_VARIABLE = "The variable not exists, try whit another name";
	public static final String UNMATCH_TYPE = "The variable type and the type of the value aren't the same!";

	public Exceptions(String s) {
		super(s);
	}
	
}
