package model.Components;

import model.Exceptions;
import model.Memory.MemoryStorage;
import model.Memory.Variable;

public class ComponentOutput extends Component{

	private String rawOutPutString;
	private String outPutText;

	public ComponentOutput(Component nextComponent1, Component nextComponent2, MemoryStorage memory) {
		super(nextComponent1, nextComponent2, memory);
		
		outPutText = "";
	}
	
	public void set(String outPut) throws Exceptions{
		//this function creates an output string with the values of the variables in it
		//example: "The number is : $var" 
		//if var = 5, output = "The number is : 5" 
		
		rawOutPutString = outPut;
	
	}
	
	public String execute() throws Exceptions {
		
		outPutText = "";
			
			String[] words = rawOutPutString.split(" ");
			
			for (int i = 0; i < words.length; i++) {
				
				if (words[i].charAt(0) == '$') {
					
					String varName = words[i].substring(1);
					
					Variable var = super.getMemory().getVariableByName(varName);
					
					words[i] = var.getValueString();
				}
			}
			
			outPutText = String.join(" ", words);
			
			return outPutText;
	}
	
	public Component getNextComponent() {
		return super.getNextComponent1();
	}

	@Override
	public String print() {
		String out = "OUT ";
		
		out += rawOutPutString;
		
		return out;
	}

	public String getRawOutPutString() {
		return rawOutPutString;
	}

	public void setRawOutPutString(String rawOutPutString) {
		this.rawOutPutString = rawOutPutString;
	}
}
