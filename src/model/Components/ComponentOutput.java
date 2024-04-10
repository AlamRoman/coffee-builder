package model.Components;

import model.Exceptions;
import model.Memory.MemoryStorage;
import model.Memory.Variable;

public class ComponentOutput extends AlgorithmComponent{

	private String outPutText;

	public ComponentOutput(AlgorithmComponent nextComponent1, AlgorithmComponent nextComponent2, MemoryStorage memory) {
		super(nextComponent1, nextComponent2, memory);
		
		outPutText = "";
	}
	
	public void set(String outPut) throws Exceptions{
		//this function creates an output string with the values of the variables in it
		//example: "The number is : $var" 
		//if var = 5, output = "The number is : 5" 
		
		outPutText = "";
		
		String[] words = outPut.split(" ");
		
		for (int i = 0; i < words.length; i++) {
			
			if (words[i].charAt(0) == '$') {
				
				String varName = words[i].substring(1);
				
				Variable var = super.getMemory().getVariableByName(varName);
				
				words[i] = var.getValueString();
			}
		}
		
		outPutText = String.join(" ", words);
	}
	
	public String execute() {
		return outPutText;
	}
}
