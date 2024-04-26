package model.Components;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.DebuggerConsole;
import model.Exceptions;
import model.Line;
import model.Memory.MemoryStorage;
import model.Memory.Variable;
import model.Memory.VariableType;
import view.GetInputWindow;
import model.Memory.VariableType;

public class ComponentInput extends AlgorithmComponent{

	private static final String referenceTypeMessage = "C-INPUT";
	private Variable finalVar;
	private String nomeVariabile;
	private Object inputValue;
	
	public ComponentInput(AlgorithmComponent nextComponent1, AlgorithmComponent nextComponent2, MemoryStorage memory) {
		super(nextComponent1, nextComponent2, memory);
		
		this.nomeVariabile = "";
		this.inputValue = null;
		
	}
	
	public Object execute() throws Exceptions {
		JFrame frame = (JFrame) getAssociatedPanel().getParent().getParent().getParent().getParent().getParent().getParent().getParent();
		DebuggerConsole.getInstance().printDefaultInfoLog(referenceTypeMessage , "Executing...");
		finalVar = super.getMemory().getVariableByName(nomeVariabile);
		String input = requestInput(frame);
		
		try {
			switch(finalVar.getType()) {
			case Integer:
				finalVar.setValue(Integer.parseInt(input));				
				break;
			case String:
				finalVar.setValue(input);				
				break;
			case Double:
				finalVar.setValue(Double.parseDouble(input));				
				break;
			default:
				break;
			}			
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exceptions(Exceptions.INVALID_CAST);
		}
		

		
		DebuggerConsole.getInstance().printDefaultSuccessLog(referenceTypeMessage , "Executed.");
		return null;
	}


	public void set(String nomeVariabile) {
		this.nomeVariabile = nomeVariabile;
	}
	
	public void setInputValue(Object o) {
		inputValue = o;
	}
	
	public String requestInput(JFrame frame) {
		GetInputWindow GIW = new GetInputWindow(nomeVariabile, frame);
		String res = GIW.showInputRequestWindow();
		return res;
	}

	@Override
	public AlgorithmComponent getNextComponent() {
		return super.getNextComponent1();
	}

	@Override
	public String print() {
		String out = "IN ";
		
		if (nomeVariabile != null && !nomeVariabile.equals("")) {
			out += "$" + nomeVariabile;
		}
		
		return out;
	}
	
	@Override
	public ArrayList<Line> printCode(String language) {
		ArrayList<Line> lines = new ArrayList<Line>();
		switch(language) {
			case "java":
				if(finalVar != null) {
					switch(finalVar.getType()) {
						case String:
							lines.add(new Line(((nomeVariabile != null && !nomeVariabile.equals("")?nomeVariabile:"%VariableName%")) + " = " + "scanner.nextLine()"));
							break;
						case Double:
							lines.add(new Line(((nomeVariabile != null && !nomeVariabile.equals("")?nomeVariabile:"%VariableName%")) + " = " + "scanner.nextDouble()"));
							break;
						case Integer:
							lines.add(new Line(((nomeVariabile != null && !nomeVariabile.equals("")?nomeVariabile:"%VariableName%")) + " = " + "scanner.nextInt()"));
							break;
					}
				} else {
					lines.add(new Line(((nomeVariabile != null && !nomeVariabile.equals("")?nomeVariabile:"%VariableName%")) + " = " + "scanner.nextLine()"));
				}
				break;
			case "pseudocode":
				lines.add(new Line(print()));
				break;
			case "python":
				if(finalVar != null) {
					switch(finalVar.getType()) {
					case String:
						lines.add(new Line(((nomeVariabile != null && !nomeVariabile.equals("")?nomeVariabile:"%VariableName%")) + " = " + "input()"));
						break;
					case Double:
						lines.add(new Line(((nomeVariabile != null && !nomeVariabile.equals("")?nomeVariabile:"%VariableName%")) + " = " + "float(input())"));
						break;
					case Integer:
						lines.add(new Line(((nomeVariabile != null && !nomeVariabile.equals("")?nomeVariabile:"%VariableName%")) + " = " + "int(input())"));
						break;	
					}
				} else {
					lines.add(new Line(((nomeVariabile != null && !nomeVariabile.equals("")?nomeVariabile:"%VariableName%")) + " = " + "input()"));
				}
				break;
		}
		return lines;
		
	}

	public String getNomeVariabile() {
		return nomeVariabile;
	}

	public void setNomeVariabile(String nomeVariabile) {
		this.nomeVariabile = nomeVariabile;
	}
	
}
