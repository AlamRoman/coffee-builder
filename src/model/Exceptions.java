package model;

public class Exceptions extends Exception{
	
	public static final String COMPONENT_NOT_SET = "Set the component before executing the algorithm";
	public static final String EXISTING_VARIABLE = "The variable already exists, choose another name";
	public static final String NON_EXISTING_VARIABLE = "The variable does not exists, try with another name";
	public static final String UNMATCH_TYPE = "The variable type and the type of the value/variable aren't the same!";
	public static final String CONVERSION_ERROR = "The value cannot be converted";
	public static final String STRING_ERROR = "Strings don't support this operation!";

	public Exceptions(String s) {
		super(s);
	}
	
}
