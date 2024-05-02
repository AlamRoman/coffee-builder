package model.Components;

import java.util.ArrayList;

import model.DebuggerConsole;
import model.Line;
import model.Memory.MemoryStorage;

public class ComponentEnd extends AlgorithmComponent{

	private static final String referenceTypeMessage = "C-END";

	public ComponentEnd() {
		super(null, null, null);
	}
	
	public Object execute() {
		DebuggerConsole.getInstance().printDefaultInfoLog(referenceTypeMessage , "Executing...");
		DebuggerConsole.getInstance().printDefaultSuccessLog(referenceTypeMessage , "Program ends.");
		DebuggerConsole.getInstance().printDefaultSuccessLog(referenceTypeMessage , "Executed.");
		return null;
	}
	
	public AlgorithmComponent getNextComponent() {
		return null;
	}

	@Override
	public String print() {
		return "END";
	}
	
	@Override
	public ArrayList<Line> printCode(String language) {
		ArrayList<Line> lines = new ArrayList<Line>();
		switch(language) {
			case "java":
				lines.add(new Line("    }"));
				lines.add(new Line("}"));
				break;
			case "pseudocode":
				lines.add(new Line(print()));
				break;
			case "python":
				break;
		}
		return lines;
		
	}
	
	public String printJavaCode() {
		StringBuilder sb = new StringBuilder();
		sb.append("    }");
		sb.append("}");
		return sb.toString();
	}
	
}
