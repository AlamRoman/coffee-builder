package model.Components;

import java.util.ArrayList;

import model.Color;
import model.DebuggerConsole;
import model.Exceptions;
import model.Line;
import model.Memory.MemoryStorage;

public class ComponentEndWhile extends AlgorithmComponent {

	private static final String referenceTypeMessage = "C-END_WHILE";
	private AlgorithmComponent nextComponent;

	public ComponentEndWhile(AlgorithmComponent nextComponent1, AlgorithmComponent nextComponent2,
			MemoryStorage memory) {
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
				lines.add(new Line(print()));
				break;
			case "python":
				break;
		}
		return lines;
		
	}

}
