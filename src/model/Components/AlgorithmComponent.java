package model.Components;

import java.util.ArrayList;

import controller.Controller;
import model.AlgorithmExecuter;
import model.Buffer;
import model.DebuggerConsole;
import model.Exceptions;
import model.Line;
import model.Timer;
import model.Memory.MemoryStorage;
import view.flowChartComponents.FlowChartPanel;

/**This is the parent class of all the components that can be used in this program
 * @see ComponentAdd
 * @see ComponentAssign
 * @see ComponentComment
 * @see ComponentDeclaration
 * @see ComponentElse
 * @see ComponentEnd
 * @see ComponentIf
 * @see ComponentInput
 * @see ComponentOperation
 * @see ComponentOutput
 * @see ComponentStart
 * @see ComponentWhile
 * */
public class AlgorithmComponent {

	private static final String referenceTypeMessage = "AC";
	private AlgorithmComponent nextComponent1;
	private AlgorithmComponent nextComponent2;
	private FlowChartPanel associatedPanel;
	private MemoryStorage memory;
	
	/**<p>
	* The constructor method of the {@link AlgorithmComponent} class
	* </p>
	* <p>
	* Creates an instance of {@link AlgorithmComponent}
	* </p>
	* @param nextComponent1 The component that is going to be pointed as next, by default, or if the condition is true (in {@link ComponentWhile} and {@link ComponentIf})
	* @param nextComponent2 The component that is going to be pointed as next if the condition is false (in {@link ComponentWhile} and {@link ComponentIf})
	* @param memory The {@link MemoryStorage} instance that is shared in the program and contains all the variables and components
	*/
	public AlgorithmComponent(AlgorithmComponent nextComponent1, AlgorithmComponent nextComponent2, MemoryStorage memory) {
		super();
		this.nextComponent1 = nextComponent1;
		this.nextComponent2 = nextComponent2;
		this.memory = memory;
	}
	
	/**<p>
	* This method will be called from every instance of any class that extends this class
	* </p>
	* <p>
	* This method needs to be overrided by the single child classes for proper functionality
	* </p>
	* @throws Exceptions
	*/
	public Object execute() throws Exceptions {
		return null;
	}
	
	/**<p>
	* This method will be called from every instance of any class that extends this class
	* </p>
	* <p>
	* This method needs to be overrided by the single child classes for proper functionality
	* </p>
	* @throws Exceptions
	*/
	public AlgorithmComponent getNextComponent() {
		return null;
	}
	
	/**<p>
	* This method will be called from every instance of any class that extends this class
	* </p>
	* <p>
	* This method needs to be overrided by the single child classes for proper functionality
	* </p>
	* @throws Exceptions
	*/
	public String print() {
		return null;
	}
	
	/**<p>
	* This method will be called from every instance of any class that extends this class
	* </p>
	* <p>
	* This method needs to be overrided by the single child classes for proper functionality and it will translate the 
	* informations contained in the instances to different programming languages such as Python, Java and PseudoCode
	* </p>
	* @param language The language that it needs to be translated in
	* <p>
	* Possible values:
	* <ul>
	* <li>"Python"</li>
	* <li>"Java"</li>
	* <li>"PseudoCode"</li>
	* </ul>
	* </p>
	*/
	public ArrayList<Line> printCode(String language) {
		return null;
	}

	public AlgorithmComponent getNextComponent1() {
		return nextComponent1;
	}

	public void setNextComponent1(AlgorithmComponent nextComponent1) {
		DebuggerConsole.getInstance().printDefaultInfoLog(referenceTypeMessage, "Changing next comp1: " + this.classSimpleName() + " > " + getNextComponent1() + " -> " + this.classSimpleName() + " > " + nextComponent1);
		this.nextComponent1 = nextComponent1;
	}

	public AlgorithmComponent getNextComponent2() {
		return nextComponent2;
	}

	public void setNextComponent2(AlgorithmComponent nextComponent2) {
		DebuggerConsole.getInstance().printDefaultInfoLog(referenceTypeMessage, "Changing next comp2: " + this.classSimpleName() + " > " + getNextComponent2() + " -> " + this.classSimpleName() + " > " + nextComponent2);
		this.nextComponent2 = nextComponent2;
	}

	public MemoryStorage getMemory() {
		return memory;
	}

	public void setMemory(MemoryStorage memory) {
		this.memory = memory;
	}
	
	public String classSimpleName() {
		// TODO Auto-generated method stub
		return getClass().getSimpleName();
	}
	
	public FlowChartPanel getAssociatedPanel() {
		return associatedPanel;
	}
	
	public void setAssociatedPanel(FlowChartPanel p) {
		this.associatedPanel = p;
	}
	
	
}
