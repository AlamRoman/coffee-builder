package model;

import java.net.CookieHandler;
import java.util.ArrayList;

public class DebuggerConsole {

	private static DebuggerConsole instance;
	private ArrayList<Line> lines;
	private String finalText;
	
	public static DebuggerConsole getInstance() {
		instance = (instance == null) ? (new DebuggerConsole()) : instance;
		return instance;
	}
	
	public DebuggerConsole() {
		super();
		this.lines = new ArrayList<Line>();
		this.finalText = "";
	}

	public void printLog(String messsageTypeColor, String messageType, String messageColor, String message) {
		String s = "[" + messsageTypeColor + messageType + Color.WHITE + "]" + Color.RESET + " : " + messageColor + message + Color.RESET;
		System.out.println(s);
		addLine(s);
		addLineToFinalLog(s);
	}
	
	public void printInfoLog(String messageTypeColor, String messageType, String messageColor, String message) {
		if(!checkIfOperational(Thread.currentThread().getStackTrace()[2].getClassName(), messageType)) return;
		String s = Color.GREEN_BOLD + "#" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " in " + Thread.currentThread().getStackTrace()[2].getClassName() + "[" + Thread.currentThread().getStackTrace()[2].getMethodName() + "()]" + Color.PURPLE + ((Debug.SHOW_CALLERS && Thread.currentThread().getStackTrace().length == 4)?(" from " + Thread.currentThread().getStackTrace()[3].getClassName() + "[" + Thread.currentThread().getStackTrace()[3].getClassName() + "()]"):"") + Color.WHITE + " [" + messageTypeColor + messageType + Color.WHITE + "]" + Color.RESET + " : " + messageColor + message + Color.RESET;
		System.out.println(s);
		addLine(s);
		addLineToFinalLog(s);
	}
	
	public void printDefaultInfoLog(String messageType, String message) {
		if(!checkIfOperational(Thread.currentThread().getStackTrace()[2].getClassName(), messageType)) return;
		String s = Color.GREEN_BOLD + "#" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " in " + Thread.currentThread().getStackTrace()[2].getClassName() + "[" + Thread.currentThread().getStackTrace()[2].getMethodName() + "()]" + Color.PURPLE + ((Debug.SHOW_CALLERS && Thread.currentThread().getStackTrace().length == 4)?(" from " + Thread.currentThread().getStackTrace()[3].getClassName() + "[" + Thread.currentThread().getStackTrace()[3].getClassName() + "()]"):"") + Color.WHITE + " [" + Color.CYAN_BOLD_BRIGHT + messageType + Color.WHITE + "]" + Color.RESET + " : " + Color.YELLOW_BOLD_BRIGHT + message + Color.RESET;
		System.out.println(s);
		addLine(s);
		addLineToFinalLog(s);
	}
	
	public void printDefaultSuccessLog(String messageType, String message) {
		if(!checkIfOperational(Thread.currentThread().getStackTrace()[2].getClassName(), messageType)) return;
		String s = Color.GREEN_BOLD + "#" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " in " + Thread.currentThread().getStackTrace()[2].getClassName() + "[" + Thread.currentThread().getStackTrace()[2].getMethodName() + "()]" + Color.PURPLE + ((Debug.SHOW_CALLERS && Thread.currentThread().getStackTrace().length == 4)?(" from " + Thread.currentThread().getStackTrace()[3].getClassName() + "[" + Thread.currentThread().getStackTrace()[3].getClassName() + "()]"):"") + Color.WHITE + " [" + Color.CYAN_BOLD_BRIGHT + messageType + Color.WHITE + "]" + Color.RESET + " : " + Color.GREEN_BOLD_BRIGHT + message + Color.RESET;
		System.out.println(s);
		addLine(s);
		addLineToFinalLog(s);
	}
	
	public void printDefaultErrorLog(String messageType, String message) {
		if(!checkIfOperational(Thread.currentThread().getStackTrace()[2].getClassName(), messageType)) return;
		String s = Color.GREEN_BOLD + "#" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " in " + Thread.currentThread().getStackTrace()[2].getClassName() + "[" + Thread.currentThread().getStackTrace()[2].getMethodName() + "()]" + Color.PURPLE + ((Debug.SHOW_CALLERS && Thread.currentThread().getStackTrace().length == 4)?(" from " + Thread.currentThread().getStackTrace()[3].getClassName() + "[" + Thread.currentThread().getStackTrace()[3].getClassName() + "()]"):"") + Color.WHITE + " [" + Color.CYAN_BOLD_BRIGHT + messageType + Color.WHITE + "]" + Color.RESET + " : " + Color.RED_BOLD_BRIGHT + message + Color.RESET;
		System.out.println(s);
		addLine(s);
		addLineToFinalLog(s);
	}
	
	public void printCustomMSGColorLog(String messageType, String color, String message) {
		if(!checkIfOperational(Thread.currentThread().getStackTrace()[2].getClassName(), messageType)) return;
		String s = Color.GREEN_BOLD + "#" + Thread.currentThread().getStackTrace()[2].getLineNumber() + " in " + Thread.currentThread().getStackTrace()[2].getClassName() + "[" + Thread.currentThread().getStackTrace()[2].getMethodName() + "()]" + Color.PURPLE + ((Debug.SHOW_CALLERS && Thread.currentThread().getStackTrace().length == 4)?(" from " + Thread.currentThread().getStackTrace()[3].getClassName() + "[" + Thread.currentThread().getStackTrace()[3].getClassName() + "()]"):"") + Color.WHITE + " [" + Color.CYAN_BOLD_BRIGHT + messageType + Color.WHITE + "]" + Color.RESET + " : " + color + message + Color.RESET;
		System.out.println(s);
		addLine(s);
		addLineToFinalLog(s);
	}

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
		}else {
			if(messageType.contains("thread")) {
				return Debug.SEMAPHORE_DEBUG;
			}else {
				return true;
			}
		}
	}

	private void addLineToFinalLog(String s) {
		// TODO Auto-generated method stub
		this.finalText += s + "\n";
	}

	private void addLine(String s) {
		// TODO Auto-generated method stub
		this.lines.add(new Line(s));
	}
	
	public void showLog() {
		System.out.println(finalText);
	}
	
	
}
