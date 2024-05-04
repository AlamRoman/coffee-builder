package model.Components;

import java.util.ArrayList;

import model.Color;
import model.DebuggerConsole;
import model.Exceptions;
import model.Line;
import model.Memory.MemoryStorage;
import model.Memory.RelationalOperators;

/**<p>
 * This class represents the if component. 
 * </p>
 * <p>
 * This component, when created, points always to a {@link ComponentAdd} as the first component (that is going to be executed when the inner condition is true) and to a {@link ComponentElse}
 * as the second component (that is going to be executed when the inner condition is false)
 * </p>
 * <p>
 * This component always ends with a {@link ComponentAdd}
 * </p>
 */
public class ComponentIf extends AlgorithmComponent{
	
	private static final String referenceTypeMessage = "C-IF";
	private String term1;
	private String term2;
	private RelationalOperators operator;
	private AlgorithmComponent nextComponent;
	private Condition C;

	/**<p>
	* The constructor of the class {@link ComponentIf}
	* </p>
	* <p>
	* It creates a new instance of the class
	* </p>
	* @param nextComponent1 The component that is going to be pointed by default by the component
	* @param nextComponent2 The component that is going to be pointed if a condition is false
	* @param memory The memory that is shared in the program
	* 
	*/
	public ComponentIf(AlgorithmComponent nextComponent1, AlgorithmComponent nextComponent2, MemoryStorage memory) {
		super(nextComponent1, nextComponent2, memory);
		//initial values
		String term1 = "";
		String term2 = "";
		operator = null;
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
		return null;
	}
	
	@Override
	public AlgorithmComponent getNextComponent() {
		return nextComponent;
	}
	
	@Override
	public String print() {
		String out = "IF ";
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
				lines.add(new Line("if (" + ((C==null)?"true":C.printCode()) + "){"));
				break;
			case "pseudocode":
				lines.add(new Line(print()));
				break;
			case "python":
				String result = ((C==null)?"true":C.printCode());
				if(result.startsWith("!")) {
					result = result.replace("!", "not");
					
				}
				lines.add(new Line("if " + result + ":"));
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
