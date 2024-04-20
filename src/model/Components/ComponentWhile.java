package model.Components;

import model.Color;
import model.DebuggerConsole;
import model.Exceptions;
import model.Memory.MemoryStorage;
import model.Memory.RelationalOperators;

public class ComponentWhile extends AlgorithmComponent{
	
	private static final String referenceTypeMessage = "C-WHILE";
	private String term1;
	private String term2;
	private RelationalOperators operator;
	private AlgorithmComponent nextComponent;
	private Condition C;

	public ComponentWhile(AlgorithmComponent nextComponent2, MemoryStorage memory) {
		super(null, nextComponent2, memory);
		setNextComponent1(this);
		
		//initial values
		this.term1 = "";
		this.term2 = "";
		this.operator = null; 
		
	}
	
	public void set(String term1, RelationalOperators operator, String term2) {
		this.term1 = term1;
		this.term2 = term2;
		this.operator = operator; 
		this.C = new Condition(getMemory(), term1, operator.symbol, term2);
	}
	
	@Override
	public Object execute() throws Exceptions {
		DebuggerConsole.getInstance().printDefaultInfoLog(referenceTypeMessage , "Executing...");
		if(C != null) {
			if(C.resolve()) {
				nextComponent = getNextComponent1();
			}else {
				nextComponent = getNextComponent2();
			}			
		}else {
			throw new Exceptions(Exceptions.MISSING_ARGUMENTS);
		}
		DebuggerConsole.getInstance().printDefaultSuccessLog(referenceTypeMessage , "Executed.");
//		super.getMemory().showMemory();
		return null;
	}
	
	@Override
	public AlgorithmComponent getNextComponent() {
		return nextComponent;
	}

	public String print() {
		String out = "WHILE ";
		if(C != null) {
			out += C.toString();			
		}
		DebuggerConsole.getInstance().printCustomMSGColorLog(referenceTypeMessage + "-PRINT-OUTPUT", Color.PURPLE, "Showing: '" + out + "' to the panel");
		return out;
	}

	public String getTerm1() {
		return term1;
	}

	public String getTerm2() {
		return term2;
	}

	public RelationalOperators getOperator() {
		return operator;
	}
}
