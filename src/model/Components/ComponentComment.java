package model.Components;

import model.Color;
import model.DebuggerConsole;
import model.Memory.MemoryStorage;

public class ComponentComment extends Component{

	private static final String referenceTypeMessage = "C-COMMENT";
	private String comment;

	public ComponentComment(Component nextComponent1, Component nextComponent2, MemoryStorage memory) {
		super(nextComponent1, nextComponent2, memory);
		
		this.comment = "";
	}

	public String execute() {
		DebuggerConsole.getInstance().printDefaultInfoLog(referenceTypeMessage , "Executing...");
		DebuggerConsole.getInstance().printCustomMSGColorLog(referenceTypeMessage, Color.GREEN, comment);
		DebuggerConsole.getInstance().printDefaultSuccessLog(referenceTypeMessage , "Executed.");
		return comment;
	}

	public void set(String comment) {
		this.comment = comment;
	}

	@Override
	public Component getNextComponent() {
		return super.getNextComponent1();
	}

	@Override
	public String print() {
		String out = "// ";
		
		out += comment;
		
		return out;
	}
	
	public String getCommentString() {
		return comment;
	}
}
