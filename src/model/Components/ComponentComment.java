package model.Components;

import java.util.ArrayList;

import model.Color;
import model.DebuggerConsole;
import model.Line;
import model.Memory.MemoryStorage;

public class ComponentComment extends AlgorithmComponent{

	private static final String referenceTypeMessage = "C-COMMENT";
	private String comment;

	public ComponentComment(AlgorithmComponent nextComponent1, AlgorithmComponent nextComponent2, MemoryStorage memory) {
		super(nextComponent1, nextComponent2, memory);
		
		this.comment = "";
	}

	public String execute() {
		DebuggerConsole.getInstance().printDefaultInfoLog(referenceTypeMessage , "Executing...");
		DebuggerConsole.getInstance().printCustomMSGColorLog(referenceTypeMessage, Color.GREEN, comment);
		DebuggerConsole.getInstance().printDefaultSuccessLog(referenceTypeMessage , "Executed.");
		return null;
	}

	public void set(String comment) {
		this.comment = comment;
	}

	@Override
	public AlgorithmComponent getNextComponent() {
		return super.getNextComponent1();
	}

	@Override
	public String print() {
		String out = "// ";
		
		out += comment;
		
		return out;
	}
	
	@Override
	public ArrayList<Line> printCode(String language) {
		ArrayList<Line> lines = new ArrayList<Line>();
		switch(language) {
			case "java":
				lines.add(new Line("// " + comment));
				break;
			case "pseudocode":
				lines.add(new Line(print()));
				break;
			case "python":
				lines.add(new Line("# " + comment));
				break;
		}
		return lines;
		
	}
	
	public String getCommentString() {
		return comment;
	}
}
