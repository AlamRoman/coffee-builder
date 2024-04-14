package model;

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
	
	public void printLog(int line, String env, StackTraceElement caller, String messsageTypeColor, String messageType, String messageColor, String message) {
		String s = Color.GREEN_BOLD + "#" + line + " in " + env + Color.PURPLE +  " from " + caller.getMethodName() + "()" + " in " + caller.getClassName() + Color.WHITE + " [" + messsageTypeColor + messageType + Color.WHITE + "]" + Color.RESET + " : " + messageColor + message + Color.RESET;
		System.out.println(s);
		addLine(s);
		addLineToFinalLog(s);
	}
	
	public void printDefaultInfoLog(int line, String env, StackTraceElement caller, String messageType, String message) {
		String s = Color.GREEN_BOLD + "#" + line + " in " + env + Color.PURPLE +  " from " + caller.getMethodName() + "()" + " in " + caller.getClassName() + Color.WHITE + " [" + Color.CYAN + messageType + Color.WHITE + "]" + Color.RESET + " : " + Color.YELLOW + message + Color.RESET;
		System.out.println(s);
		addLine(s);
		addLineToFinalLog(s);
	}
	
	public void printDefaultSuccessLog(int line, String env, StackTraceElement caller, String messageType, String message) {
		String s = Color.GREEN_BOLD + "#" + line + " in " + env + Color.PURPLE +  " from " + caller.getMethodName() + "()" + " in " + caller.getClassName() + Color.WHITE + " [" + Color.CYAN + messageType + Color.WHITE + "]" + Color.RESET + " : " + Color.GREEN_BOLD_BRIGHT + message + Color.RESET;
		System.out.println(s);
		addLine(s);
		addLineToFinalLog(s);
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
