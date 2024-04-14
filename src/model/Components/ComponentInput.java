package model.Components;

import java.util.Scanner;

import model.DebuggerConsole;
import model.Exceptions;
import model.Memory.MemoryStorage;
import model.Memory.Variable;

public class ComponentInput extends Component{

	private static final String referenceTypeMessage = "C-INPUT";
	private Variable finalVar;
	private String nomeVariabile;
	private Object inputValue;
	
	public ComponentInput(Component nextComponent1, Component nextComponent2, MemoryStorage memory) {
		super(nextComponent1, nextComponent2, memory);
		
		this.nomeVariabile = "";
		this.inputValue = null;
		
	}
	
	public Object execute() throws Exceptions {
		DebuggerConsole.getInstance().printDefaultInfoLog(referenceTypeMessage , "Executing...");
		finalVar = super.getMemory().getVariableByName(nomeVariabile);
		requestInput();
		finalVar.setValue(inputValue);
		
		DebuggerConsole.getInstance().printDefaultSuccessLog(referenceTypeMessage , "Executed.");
		return null;
	}
	
	public void set(String nomeVariabile) {
		this.nomeVariabile = nomeVariabile;
	}
	
	public void setInputValue(Object o) {
		inputValue = o;
	}
	
	public void requestInput() {
		Scanner s = new Scanner(System.in);
		System.out.print("Insert a number: ");
		setInputValue(s.nextInt());
	}

	@Override
	public Component getNextComponent() {
		return super.getNextComponent1();
	}

	@Override
	public String print() {
		String out = "IN ";
		
		out += "$" + finalVar.getName();
		
		return out;
	}

	public String getNomeVariabile() {
		return nomeVariabile;
	}

	public void setNomeVariabile(String nomeVariabile) {
		this.nomeVariabile = nomeVariabile;
	}
	
}
