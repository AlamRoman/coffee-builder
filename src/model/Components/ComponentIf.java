package model.Components;

import model.Color;
import model.DebuggerConsole;
import model.Exceptions;
import model.Memory.MemoryStorage;
import model.Memory.RelationalOperators;

public class ComponentIf extends Component{
	
	private static final String referenceTypeMessage = "C-IF";
	private String term1;
	private String term2;
	private RelationalOperators operand;
	private Component nextComponent;
	private Condition C;

	public ComponentIf(Component nextComponent1, Component nextComponent2, MemoryStorage memory) {
		super(nextComponent1, nextComponent2, memory);
		// TODO Auto-generated constructor stub
		
	}
	
	public void set(String term1, RelationalOperators operand, String term2) {
		this.term1 = term1;
		this.term2 = term2;
		this.operand = operand; 
		this.C = new Condition(getMemory(), term1, operand.symbol, term2);
	}
	
	@Override
	public Object execute() throws Exceptions {
		DebuggerConsole.getInstance().printDefaultInfoLog(referenceTypeMessage , "Executing...");
		if(C.resolve()) {
			nextComponent = getNextComponent1();
		}else {
			nextComponent = getNextComponent2();
		}
		DebuggerConsole.getInstance().printDefaultSuccessLog(referenceTypeMessage , "Executed.");
		return null;
	}
	
	@Override
	public Component getNextComponent() {
		return nextComponent;
	}
	
	public String print() {
		String out = "IF ";
		out += C.toString();
		DebuggerConsole.getInstance().printCustomMSGColorLog(referenceTypeMessage + "-PRINT-OUTPUT", Color.PURPLE, "Showing: '" + out + "' to the panel");
		return out;
	}

}
