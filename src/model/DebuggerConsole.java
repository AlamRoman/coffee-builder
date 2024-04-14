package model;

import java.util.ArrayList;

public class DebuggerConsole {

	private static DebuggerConsole instance;
	private ArrayList<Line> lines;
	private String finalText;
	
	public static DebuggerConsole getInstance() {
		if(instance == null ) {
			instance = new DebuggerConsole();
		}
		return instance;
	}
	
	public String printLog(Color messageTypeColor, String messageType, Color messageColor, String message) {
		String s = messageTypeColor + messageType + messageColor + message;
		System.out.println(s);
	}
	
	
}
