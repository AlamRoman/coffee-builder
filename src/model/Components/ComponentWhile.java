package model.Components;

import java.util.ArrayList;

import model.Color;
import model.DebuggerConsole;
import model.Exceptions;
import model.Line;
import model.Memory.MemoryStorage;
import model.Memory.OperationType;
import model.Memory.RelationalOperators;

/**<p>
 * This class represents the while component. 
 * </p>
 * <p>
 * This component, when created, points always to itself as the first component (that is going to be executed when the inner condition is true) and to a {@link ComponentAdd}
 * as the second component (that is going to be executed when the inner condition is false)
 * </p>
 * <p>
 * This component always ends with a {@link ComponentAdd}
 * </p>
 */
public class ComponentWhile extends AlgorithmComponent{
	
	private static final String referenceTypeMessage = "C-WHILE";
	private String term1;
	private String term2;
	private RelationalOperators operator;
	private AlgorithmComponent nextComponent;
	private Condition C;

	/**<p>
	* The constructor of the class {@link ComponentWhile}
	* </p>
	* <p>
	* It creates a new instance of the class, it doesn't require the first component parameter because
	* it'll always point at itself when created (empty while loop)
	* </p>
	* @param nextComponent2 The component that is going to be pointed if a condition is false
	* @param memory The memory that is shared in the program
	* 
	*/
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
			boolean flag = C.resolve();
			DebuggerConsole.getInstance().printDefaultInfoLog(referenceTypeMessage , "Got " + flag + " from C.resolve()");
			if(flag) {
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

	@Override
	public String print() {
		String out = "WHILE ";
		if(C != null) {
			out += C.toString();			
		}
		DebuggerConsole.getInstance().printCustomMSGColorLog(referenceTypeMessage + "-PRINT-OUTPUT", Color.PURPLE, "Showing: '" + out + "' to the panel");
		return out;
	}
	
	@Override
	public ArrayList<Line> printCode(String language) {
		ArrayList<Line> lines = new ArrayList<Line>();
		switch(language) {
			case "java":
				lines.add(new Line("while (" + ((C==null)?"true":C.printCode()) + "){"));
				break;
			case "pseudocode":
				lines.add(new Line(print()));
				break;
			case "python":
				String result = ((C==null)?"true":C.printCode());
				if(result.startsWith("!")) {
					result = result.replace("!", "not");
				}
				lines.add(new Line("while " + result + ":"));
				break;
		}
		return lines;
		
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
