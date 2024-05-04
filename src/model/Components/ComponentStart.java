package model.Components;

import java.util.ArrayList;

import model.DebuggerConsole;
import model.Exceptions;
import model.Line;
import model.Memory.MemoryStorage;
import model.Memory.Variable;
import model.Memory.VariableType;

/**<p>
 * This class represents the start of the algorithm. 
 * </p>
 */
public class ComponentStart extends AlgorithmComponent{

	private static final String referenceTypeMessage = "C-START";
	private AlgorithmComponent nextComponent;
	
	/**<p>
	* The constructor of the class {@link ComponentStart}
	* </p>
	* <p>
	* It creates a new instance of the class
	* </p>
	* @param nextComponent The component that is going to be pointed by default by the component
	* 
	*/
	public ComponentStart(AlgorithmComponent nextComponent) {
		super(nextComponent, null, null);
	}

	@Override
	public Object execute() throws Exceptions {
		// TODO Auto-generated method stub
		DebuggerConsole.getInstance().printDefaultInfoLog(referenceTypeMessage , "Executing...");
		MemoryStorage MS = MemoryStorage.getInstance();
		MS.destroyVariables();
		//DELETE WHEN NOT TESTING-----------------------------------------------
//		MS.addVariable(new Variable(VariableType.String, "Ciao", "ciao"));
//		MS.addVariable(new Variable(VariableType.String, "Ciao2", "ciao2"));
		//----------------------------------------------------------------------
		DebuggerConsole.getInstance().printDefaultSuccessLog(referenceTypeMessage , "Program starts. Memory has been created.");
		DebuggerConsole.getInstance().printDefaultSuccessLog(referenceTypeMessage , "Executed.");
		super.setMemory(MS);
		return  null;
	}
	public AlgorithmComponent getNextComponent() {
		return super.getNextComponent1();
	}

	@Override
	public String print() {
		return "START";
	}
	
	@Override
	public ArrayList<Line> printCode(String language) {
		ArrayList<Line> lines = new ArrayList<Line>();
		switch(language) {
			case "java":
				lines.add(new Line("package main;"));
				lines.add(new Line((MemoryStorage.getInstance().containsInput())?"import java.util.Scanner;":""));
				lines.add(new Line("public class Main {"));
				lines.add(new Line("    public static void main(String[] args) {"));
				lines.add(new Line((MemoryStorage.getInstance().containsInput())?"        Scanner scanner = new Scanner(System.in);":""));
				break;
			case "pseudocode":
				lines.add(new Line(print()));
				break;
			case "python":
				break;
		}
		return lines;
		
	}
	
	
}
