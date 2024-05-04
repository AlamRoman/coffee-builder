package model;

import java.awt.Component;


/**This class contains all the possible exceptions that can be thrown during the execution of the algorithm
 * */
public class Exceptions extends Exception{
	
	/**
	 * COMPONENT_NOT_SET
	 * */
	public static final String COMPONENT_NOT_SET = "Set the component before executing the algorithm";
	/**
	 * EXISTING_VARIABLE
	 * */
	public static final String EXISTING_VARIABLE = "The variable already exists, choose another name";
	/**
	 * NON_EXISTING_VARIABLE
	 * */
	public static final String NON_EXISTING_VARIABLE = "The variable does not exists, try with another name";
	/**
	 * UNMATCH_TYPE
	 * */
	public static final String UNMATCH_TYPE = "The variable type and the type of the value/variable aren't the same!";
	/**
	 * INVALID_CAST
	 * */
	public static final String INVALID_CAST = "The variable type and the type of the value aren't the same!";
	/**
	 * UNKNOWN_TYPE
	 * */
	public static final String UNKNOWN_TYPE = "Unknown variable type!";
	/**
	 * CONVERSION_ERROR
	 * */
	public static final String CONVERSION_ERROR = "The value cannot be converted";
	/**
	 * STRING_ERROR
	 * */
	public static final String STRING_ERROR = "Strings don't support this operation!";
	/**
	 * INVALID_CONDITION_SYMBOL
	 * */
	public static final String INVALID_CONDITION_SYMBOL = "The condition symbol used is not valid";
	/**
	 * TERM_IS_STRING
	 * */
	public static final String TERM_IS_STRING = "Invalid String to Boolean conversion in NOT condition";
	/**
	 * MISSING_ARGUMENTS
	 * */
	public static final String MISSING_ARGUMENTS = "Missing one or more term in a condition";
	/**
	 * CONDITION_TERMS_NOT_NUMBER
	 * */
	public static final String CONDITION_TERMS_NOT_NUMBER = "One or more term is not a number (invalid for '<', '>', '<=', '>=')";
	/**
	 * COMPONENT_NOT_EMPTY
	 * */
	public static final String COMPONENT_NOT_EMPTY = "This component is not empty, delete the inner components first";
	/**
	 * NOT_DELETABLE
	 * */
	public static final String NOT_DELETABLE = "This component cannot be deleted";
	

	/**
	 * It creates a new {@link Exception}
	 * */
	public Exceptions(String s) {
		super(((Debug.SHOW_EXTRA_INFO_IN_EXCEPTIONS)?getExtraInfo():"") + s);
	}
	
	/**
	 * It creates a new {@link Exception}
	 * */
	public Exceptions(String s, Component c) {
		super(((Debug.SHOW_EXTRA_INFO_IN_EXCEPTIONS)?getExtraInfo():"") + s + " (in: " + c.getClass().getSimpleName() +")");
	}

	/**
	 * It creates a new {@link Exception}
	 * */
	public Exceptions(String s, Component c, String customMsg) {
		super(((Debug.SHOW_EXTRA_INFO_IN_EXCEPTIONS)?getExtraInfo():"") + s + " (" + c.getClass().getSimpleName() +", " + customMsg + ")");
	}
	
	/**
	 * It creates a new {@link Exception}
	 * */
	public Exceptions(String s, String customMsg) {
		super(((Debug.SHOW_EXTRA_INFO_IN_EXCEPTIONS)?getExtraInfo():"") + s + " " +  customMsg);
	}
	
	/**
	 * <p>
	 * This method returns the info of the class that has thrown the {@link Exception}
	 * </p>
	 * @return {@link String} The class info
	 * */
	private static String getExtraInfo() {
		return "[" + getClassThrowingException() + ", " + getLineNumber() + "] ";
	}
	
	/**
	 * <p>
	 * This method returns the number of the line that has thrown the {@link Exception}
	 * </p>
	 * @return {@link Integer} The line number
	 * */
	private static int getLineNumber() {
        return Thread.currentThread().getStackTrace()[3].getLineNumber();
    }
	
	/**
	 * <p>
	 * This method returns the class name that has thrown the {@link Exception}
	 * </p>
	 * @return {@link Integer} The name of the class
	 * */
	private static String getClassThrowingException() {
        return Thread.currentThread().getStackTrace()[3].getClassName();
    }
	
}
