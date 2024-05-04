package model.Components;

import java.util.ArrayList;

import model.Color;
import model.DebuggerConsole;
import model.Line;
import model.Memory.MemoryStorage;
import model.Memory.VariableType;

/**<p>
 * This class represents the comment component. 
 * </p>
 * <p>
 * This component is used whenever the user has to create a comment
 * </p>
 */
public class ComponentComment extends AlgorithmComponent{

	private static final String referenceTypeMessage = "C-COMMENT";
	private String comment;

	/**<p>
	* The constructor of the class {@link ComponentComment}
	* </p>
	* <p>
	* It creates a new instance of the class
	* </p>
	* @param nextComponent1 The component that is going to be pointed by default by the component
	* @param nextComponent2 The component that is going to be pointed if a condition is false
	* @param memory The memory that is shared in the program
	* 
	*/
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
	
	/**<p>
	 * This method returns the final comment string
	 * </p>
	 * @return {@link String} The final comment
	 * */
	public String getCommentString() {
		return comment;
	}
}
