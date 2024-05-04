package model.Components;

import java.util.ArrayList;

import model.Color;
import model.DebuggerConsole;
import model.Exceptions;
import model.Line;
import model.Memory.MemoryStorage;

/**<p>
 * This class represents the else component. 
 * </p>
 * <p>
 * This component is pointed as the the second component of a {@link ComponentIf} and will be executed only if the internal condition
 * of the {@link ComponentIf} is false
 * </p>
 * <p>
 * This component always ends with a {@link ComponentAdd}
 * </p>
 */
public class ComponentElse extends AlgorithmComponent {
	
	private static final String referenceTypeMessage = "C-ELSE";
	private AlgorithmComponent nextComponent;

	/**<p>
	* The constructor of the class {@link ComponentElse}
	* </p>
	* <p>
	* It creates a new instance of the class
	* </p>
	* @param nextComponent1 The component that is going to be pointed by default by the component
	* @param nextComponent2 The component that is going to be pointed if a condition is false
	* @param memory The memory that is shared in the program
	* 
	*/
	public ComponentElse(AlgorithmComponent nextComponent1, AlgorithmComponent nextComponent2, MemoryStorage memory) {
		super(nextComponent1, nextComponent2, memory);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Object execute() throws Exceptions {
		return null;
	}
	
	@Override
	public AlgorithmComponent getNextComponent() {
		return nextComponent;
	}
	
	@Override
	public String print() {
		return "ELSE";
	}
	
	@Override
	public ArrayList<Line> printCode(String language) {
		ArrayList<Line> lines = new ArrayList<Line>();
		switch(language) {
			case "java":
				lines.add(new Line("} else {"));
				break;
			case "pseudocode":
				lines.add(new Line(print()));
				break;
			case "python":
				lines.add(new Line("else:"));
				break;
		}
		return lines;
		
	}

}
