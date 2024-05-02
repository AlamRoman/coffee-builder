package model;

import java.net.CookieHandler;
import java.util.ArrayList;

/**This class is a Singleton that handles all the debugging process
 * */
public class DebuggerConsole {

	private static DebuggerConsole instance;
	private ArrayList<Line> lines;
	private String finalText;
	
	/**<p>
	* This method returns the instance of {@link DebuggerConsole}
	* </p>
	* <p>
	* It returns the existing instance of this class, if it doesn't exist yet, it creates the instance via the 
	* {@link DebuggerConsole#DebuggerConsole()} constructor
	* </p>
	*/
	public static DebuggerConsole getInstance() {
		instance = (instance == null) ? (new DebuggerConsole()) : instance;
		return instance;
	}
	
	/**<p>
	* The constructer method of the {@link DebuggerConsole} class
	* </p>
	*/
	public DebuggerConsole() {
		super();
		this.lines = new ArrayList<Line>();
		this.finalText = "";
	}

	/**<p>
	* This method prints a fully customizable log message
	* </p>
	* <p>
	* <code>
	* 	[MESSAGE-TYPE] : MESSAGE
	* </code>
	* </p>
	* @param messageTypeColor The color of the message type
	* @param messageType The message type
	* @param messageColor The color of the message
	* @param message The message
	*/
	public void printLog(String messageTypeColor, String messageType, String messageColor, String message) {
		String s = "[" + messageTypeColor + messageType + Color.WHITE + "]" + Color.RESET + " : " + messageColor + message + Color.RESET;
		System.out.println(s);
		addLine(s);
		addLineToFinalLog(s);
	}
	
	/**<p>
	* This method prints a fully customizable log message with the stack trace informations
	* </p>
	* <p>
	* <code>
	* 	STACK_TRACE_INFO [MESSAGE-TYPE] : MESSAGE
	* </code>
	* </p>
	* @param messageTypeColor The color of the message type
	* @param messageType The message type
	* @param messageColor The color of the message
	* @param message The message
	*/
	public void printInfoLog(String messageTypeColor, String messageType, String messageColor, String message) {
		if(!checkIfOperational(Thread.currentThread().getStackTrace()[2].getClassName(), messageType)) return;
		String s = Color.GREEN_BOLD + "#" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " in " + Thread.currentThread().getStackTrace()[2].getClassName() + "[" + Thread.currentThread().getStackTrace()[2].getMethodName() + "()]" + Color.PURPLE + ((Debug.SHOW_CALLERS && Thread.currentThread().getStackTrace().length >= 4)?(" from " + Thread.currentThread().getStackTrace()[3].getClassName() + "[" + Thread.currentThread().getStackTrace()[3].getMethodName() + "()]{" + Thread.currentThread().getStackTrace()[3].getLineNumber() +"}"):"") + Color.WHITE + " [" + messageTypeColor + messageType + Color.WHITE + "]" + Color.RESET + " : " + messageColor + message + Color.RESET;
		System.out.println(s);
		addLine(s);
		addLineToFinalLog(s);
	}
	
	/**<p>
	* This method prints a default info log message with the stack trace informations (Message color: Yellow)
	* </p>
	* <p>
	* <code>
	* 	STACK_TRACE_INFO [MESSAGE-TYPE] : MESSAGE
	* </code>
	* </p>
	* @param messageType The message type
	* @param message The message
	*/
	public void printDefaultInfoLog(String messageType, String message) {
		if(!checkIfOperational(Thread.currentThread().getStackTrace()[2].getClassName(), messageType)) return;
		String s = Color.GREEN_BOLD + "#" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " in " + Thread.currentThread().getStackTrace()[2].getClassName() + "[" + Thread.currentThread().getStackTrace()[2].getMethodName() + "()]" + Color.PURPLE + ((Debug.SHOW_CALLERS && Thread.currentThread().getStackTrace().length >= 4)?(" from " + Thread.currentThread().getStackTrace()[3].getClassName() + "[" + Thread.currentThread().getStackTrace()[3].getMethodName() + "()]{" + Thread.currentThread().getStackTrace()[3].getLineNumber() +"}"):"") + Color.WHITE + " [" + Color.CYAN_BOLD_BRIGHT + messageType + Color.WHITE + "]" + Color.RESET + " : " + Color.YELLOW_BOLD_BRIGHT + message + Color.RESET;
		System.out.println(s);
		addLine(s);
		addLineToFinalLog(s);
	}
	
	/**<p>
	* This method prints a default success log message with the stack trace informations (Message color: Green)
	* </p>
	* <p>
	* <code>
	* 	STACK_TRACE_INFO [MESSAGE-TYPE] : MESSAGE
	* </code>
	* </p>
	* @param messageType The message type
	* @param message The message
	*/
	public void printDefaultSuccessLog(String messageType, String message) {
		if(!checkIfOperational(Thread.currentThread().getStackTrace()[2].getClassName(), messageType)) return;
		String s = Color.GREEN_BOLD + "#" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " in " + Thread.currentThread().getStackTrace()[2].getClassName() + "[" + Thread.currentThread().getStackTrace()[2].getMethodName() + "()]" + Color.PURPLE + ((Debug.SHOW_CALLERS && Thread.currentThread().getStackTrace().length == 4)?(" from " + Thread.currentThread().getStackTrace()[3].getClassName() + "[" + Thread.currentThread().getStackTrace()[3].getMethodName() + "()]{" + Thread.currentThread().getStackTrace()[3].getLineNumber() +"}"):"") + Color.WHITE + " [" + Color.CYAN_BOLD_BRIGHT + messageType + Color.WHITE + "]" + Color.RESET + " : " + Color.GREEN_BOLD_BRIGHT + message + Color.RESET;
		System.out.println(s);
		addLine(s);
		addLineToFinalLog(s);
	}
	
	/**<p>
	* This method prints a default error log message with the stack trace informations (Message color: Red)
	* </p>
	* <p>
	* <code>
	* 	STACK_TRACE_INFO [MESSAGE-TYPE] : MESSAGE
	* </code>
	* </p>
	* @param messageType The message type
	* @param message The message
	*/
	public void printDefaultErrorLog(String messageType, String message) {
		if(!checkIfOperational(Thread.currentThread().getStackTrace()[2].getClassName(), messageType)) return;
		String s = Color.GREEN_BOLD + "#" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " in " + Thread.currentThread().getStackTrace()[2].getClassName() + "[" + Thread.currentThread().getStackTrace()[2].getMethodName() + "()]" + Color.PURPLE + ((Debug.SHOW_CALLERS && Thread.currentThread().getStackTrace().length == 4)?(" from " + Thread.currentThread().getStackTrace()[3].getClassName() + "[" + Thread.currentThread().getStackTrace()[3].getMethodName() + "()]{" + Thread.currentThread().getStackTrace()[3].getLineNumber() +"}"):"") + Color.WHITE + " [" + Color.CYAN_BOLD_BRIGHT + messageType + Color.WHITE + "]" + Color.RESET + " : " + Color.RED_BOLD_BRIGHT + message + Color.RESET;
		System.out.println(s);
		addLine(s);
		addLineToFinalLog(s);
	}
	
	/**<p>
	* This method prints a log message with a custom message color and stack trace informations
	* </p>
	* <code>
	* 	[MESSAGE-TYPE] : MESSAGE
	* </code>
	* @param messageType
	* @param color
	* @param message
	*/
	public void printCustomMSGColorLog(String messageType, String color, String message) {
		if(!checkIfOperational(Thread.currentThread().getStackTrace()[2].getClassName(), messageType)) return;
		String s = Color.GREEN_BOLD + "#" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " in " + Thread.currentThread().getStackTrace()[2].getClassName() + "[" + Thread.currentThread().getStackTrace()[2].getMethodName() + "()]" + Color.PURPLE + ((Debug.SHOW_CALLERS && Thread.currentThread().getStackTrace().length == 4)?(" from " + Thread.currentThread().getStackTrace()[3].getClassName() + "[" + Thread.currentThread().getStackTrace()[3].getMethodName() + "()]{" + Thread.currentThread().getStackTrace()[3].getLineNumber() +"}"):"") + Color.WHITE + " [" + Color.CYAN_BOLD_BRIGHT + messageType + Color.WHITE + "]" + Color.RESET + " : " + color + message + Color.RESET;
		System.out.println(s);
		addLine(s);
		addLineToFinalLog(s);
	}

	/**<p>
	* This method checks the {@link Debug} flags for the different debug messages
	* </p>
	* <p>
	* If a flag is true, it prints the message, if its false it doesn't
	* </p>
	* @param className
	* @param messageType
	*/
	private boolean checkIfOperational(String className, String messageType) {
		// TODO Auto-generated method stub
		if(Debug.FORCE_DEBUG) return true;
		className = className.toLowerCase();
		messageType = messageType.toLowerCase();
		if      (className.contains("assign")) {
			return Debug.COMPONENT_ASSIGN_DEBUG;
		}else if(className.contains("comment")) {
			return Debug.COMPONENT_COMMENT_DEBUG;
		}else if(className.contains("declaration")) {
			return Debug.COMPONENT_DECLARATION_DEBUG;
		}else if(className.contains("if")) {
			return Debug.COMPONENT_IF_DEBUG;
		}else if(className.contains("input")) {
			return Debug.COMPONENT_INPUT_DEBUG;
		}else if(className.contains("operation")) {
			return Debug.COMPONENT_OPERATION_DEBUG;
		}else if(className.contains("output")) {
			return Debug.COMPONENT_OUTPUT_DEBUG;
		}else if(className.contains("while")) {
			return Debug.COMPONENT_WHILE_DEBUG;
		}else if(className.contains("memorystorage")) {
			return Debug.MEMORY_DEBUG;
		}else if(className.contains("variable")) {
			return Debug.VARIABLE_DEBUG;
		}else if(className.contains("algorithmexecuter")) {
			return Debug.EXECUTER_DEBUG;
		}else if(className.contains("timer")) {
			return Debug.TIMER_DEBUG;
		}else if(className.contains("condition")) {
			return Debug.CONDITION_DEBUG;
		}else {
			if(messageType.contains("thread")) {
				return Debug.SEMAPHORE_DEBUG;
			}else {
				return true;
			}
		}
	}

	/**<p>
	* This method appends the debug message to the final debug log
	* </p>
	* @param s The message that has to be appended to the final debug log
	*/
	private void addLineToFinalLog(String s) {
		// TODO Auto-generated method stub
		this.finalText += s + "\n";
	}

	/**<p>
	* This method adds a line of debugging to the {@link ArrayList} of {@link Line}s
	* </p>
	* @param s The message that has to be added as a line
	*/
	private void addLine(String s) {
		// TODO Auto-generated method stub
		this.lines.add(new Line(s));
	}
	
	/**<p>
	* This method prints the final debugging log
	* </p>
	*/
	public void showLog() {
		System.out.println(finalText);
	}
	
	
}
