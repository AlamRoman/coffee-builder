package model.Components;

import java.util.ArrayList;

import model.Color;
import model.DebuggerConsole;
import model.Exceptions;
import model.Line;
import model.Memory.MemoryStorage;
import view.FlowChartContentPanel;

/**<p>
 * This class represents the add component. This component is added and initialized whenever a {@link ComponentWhile} or a {@link ComponentIf} is
 * being added to the algorithm by the user
 * </p>
 * This component is graphically added at the end of the {@link ComponentWhile} loop, and at the junction of the two branches of the {@link ComponentIf}
 * */
public class ComponentAdd extends AlgorithmComponent {

	private static final String referenceTypeMessage = "C-ADD";
	private AlgorithmComponent nextComponent;

	/**<p>
	* The constructor of the class {@link ComponentAdd}
	* </p>
	* <p>
	* It creates a new instance of the class
	* </p>
	* @param nextComponent1 The component that is going to be pointed by default by the component
	* @param nextComponent2 The component that is going to be pointed if a condition is false
	* @param memory The memory that is shared in the program
	* 
	*/
	public ComponentAdd(AlgorithmComponent nextComponent1, AlgorithmComponent nextComponent2, MemoryStorage memory) {
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
		return "";
	}
	
	@Override
	public ArrayList<Line> printCode(String language) {
		ArrayList<Line> lines = new ArrayList<Line>();
		switch(language) {
			case "java":
				lines.add(new Line("}"));
				break;
			case "pseudocode":
				AlgorithmComponent c = MemoryStorage.getInstance().recursiveSearchRelatedComponentFromAdd(this);
				String aus = "";
				if(c instanceof ComponentWhile) {
					aus = "END-WHILE";
				}else if(c instanceof ComponentIf){
					aus = "END-IF";
				}else {
					aus = c.getClass().getSimpleName();
				}
				lines.add(new Line(aus));
				break;
			case "python":
				break;
		}
		return lines;
		
	}

}
