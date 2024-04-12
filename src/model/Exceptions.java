package model;

import java.awt.Component;

public class Exceptions extends Exception{
	
	public static final String COMPONENT_NOT_SET = "Set the component before executing the algorithm";
	public static final String EXISTING_VARIABLE = "The variable already exists, choose another name";
	public static final String NON_EXISTING_VARIABLE = "The variable does not exists, try with another name";
	public static final String UNMATCH_TYPE = "The variable type and the type of the value/variable aren't the same!";
	public static final String CONVERSION_ERROR = "The value cannot be converted";
	public static final String STRING_ERROR = "Strings don't support this operation!";
	public static final String INVALID_CONDITION_SYMBOL = "The condition symbol used is not valid";
	public static final String NOT_CONDITION_IS_STRING = "Invalid String to Boolean conversion in NOT condition";
	public static final String NOT_CONDITION_MISSING_TERM = "Missing the term in NOT condition";
	public static final String MISSING_ARGUMENTS = "Missing one or more term in a condition";
	public static final String CONDITION_TERMS_NOT_NUMBER = "One or more term is not a number (invalid for '<', '>', '<=', '>=')";
	
	public Exceptions(String s) {
		super(s);
	}
	
	public Exceptions(String s, Component c) {
		super(s + " (" + c.getClass().getSimpleName() +")");
	}

	public Exceptions(String s, Component c, String customMsg) {
		super(s + " (" + c.getClass().getSimpleName() +", " + customMsg + ")");
	}
	
	public Exceptions(String s, String customMsg) {
		super(s + " " +  customMsg);
	}
	
}
