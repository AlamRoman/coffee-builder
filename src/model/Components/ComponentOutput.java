package model.Components;

import java.util.ArrayList;
import java.util.regex.Pattern;

import model.Color;
import model.DebuggerConsole;
import model.Exceptions;
import model.Line;
import model.Memory.MemoryStorage;
import model.Memory.Variable;

public class ComponentOutput extends AlgorithmComponent{

	private static final String referenceTypeMessage = "C-OUTPUT";
	private String rawOutPutString;
	private String outPutText;

	public ComponentOutput(AlgorithmComponent nextComponent1, AlgorithmComponent nextComponent2, MemoryStorage memory) {
		super(nextComponent1, nextComponent2, memory);
		
		outPutText = "";
		rawOutPutString="";
	}
	
	public void set(String outPut){
		//this function creates an output string with the values of the variables in it
		//example: "The number is : $var" 
		//if var = 5, output = "The number is : 5" 
		if(outPut != null) {
			rawOutPutString = outPut;			
		}else {
			rawOutPutString = "";
		}
	
	}
	
	public String execute() throws Exceptions {
		DebuggerConsole.getInstance().printDefaultInfoLog(referenceTypeMessage , "Executing...");
		
		outPutText = "";
		
			if(rawOutPutString!="") {
				String[] words = rawOutPutString.split(" ");
				
				for (int i = 0; i < words.length; i++) {
					
					if (words[i].charAt(0) == '$') {
						
						String varName = words[i].substring(1);
						
						Variable var = super.getMemory().getVariableByName(varName);
						
						words[i] = var.getValueString();
					}
				}
				
				outPutText = String.join(" ", words);
			}
			
			
			DebuggerConsole.getInstance().printCustomMSGColorLog(referenceTypeMessage, Color.WHITE, outPutText);
			DebuggerConsole.getInstance().printDefaultSuccessLog(referenceTypeMessage , "Executed.");
			return outPutText;
	}
	
	public AlgorithmComponent getNextComponent() {
		return super.getNextComponent1();
	}
	
	public String getCodeString(){
		
		outPutText = "";
		
			if(rawOutPutString!="") {
				String[] words = rawOutPutString.split(" ");
				
				for (int i = 0; i < words.length; i++) {
					
					int count = words[i].split(Pattern.quote(String.valueOf('$'))).length - 1;
					
					if (words[i].charAt(0) == '$' && count == 1) {
						
						words[i] = words[i].substring(1);
						
					}else {
						words[i] = "\"" + words[i] + "\"";
					}
				}
				
				outPutText = String.join(" + ", words);
				outPutText = outPutText.replace("\" + \"", " ");
				outPutText = outPutText.replace(" + \"",  " + \" ");
				outPutText = outPutText.replace("\" + ",  " \" + ");
			}
			return outPutText;
	}

	@Override
	public String print() {
		String out = "OUT \"";
		
		out += rawOutPutString + "\"";
		
		return out;
	}
	
	@Override
	public ArrayList<Line> printCode(String language) {
		ArrayList<Line> lines = new ArrayList<Line>();
		switch(language) {
			case "java":
				lines.add(new Line("System.out.println(" + getCodeString() + ");"));
				break;
			case "pseudocode":
				lines.add(new Line(print()));
				break;
			case "python":
				lines.add(new Line("print(" + getCodeString() + ")"));
				break;
		}
		return lines;
		
	}

	public String getRawOutputString() {
		return rawOutPutString;
	}

	public void setRawOutPutString(String rawOutPutString) {
		this.rawOutPutString = rawOutPutString;
	}
}
