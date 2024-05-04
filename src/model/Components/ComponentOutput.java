package model.Components;

import java.util.ArrayList;
import java.util.regex.Pattern;

import model.Color;
import model.DebuggerConsole;
import model.Exceptions;
import model.Line;
import model.Memory.MemoryStorage;
import model.Memory.Variable;
import view.Panel;

/**<p>
 * This class represents the output component. 
 * </p>
 * <p>
 * This component is used whenever the user wants the algorithm to print an output string in the Output textArea in the {@link Panel}
 * </p>
 */
public class ComponentOutput extends AlgorithmComponent{

	private static final String referenceTypeMessage = "C-OUTPUT";
	private String rawOutPutString;
	private String outPutText;

	/**<p>
	* The constructor of the class {@link ComponentOutput}
	* </p>
	* <p>
	* It creates a new instance of the class
	* </p>
	* @param nextComponent1 The component that is going to be pointed by default by the component
	* @param nextComponent2 The component that is going to be pointed if a condition is false
	* @param memory The memory that is shared in the program
	* 
	*/
	public ComponentOutput(AlgorithmComponent nextComponent1, AlgorithmComponent nextComponent2, MemoryStorage memory) {
		super(nextComponent1, nextComponent2, memory);
		
		outPutText = "";
		rawOutPutString="";
	}
	
	/**<p>
	 * This method sets the required attributes for a correct execution of the {@link ComponentOutput} component
	 * </p>
	 * @param outPut The string that will be shown in the output TextArea
	 * @see Panel
	 * */
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
	
	/**<p>
	* This method returns the same info given by the {@link ComponentOutput#print()} but translated for the conversions to the different programming languages
	* </p>
	* <ul>
	* <li>Java</li>
	* <li>Python</li>
	* <li>PseudoCode</li>
	* </ul>
	* @return {@link String} The result String
	*/
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

	/**<p>
	 * This method returns the string set by the user as the output String
	 * </p>
	 * @return {@link String} The string of the output
	 * */
	public String getRawOutputString() {
		return rawOutPutString;
	}

	/**<p>
	 * This method sets the given string as the output String
	 * </p>
	 * @param rawOutPutString The string that is going to be set as the output String of this component
	 * */
	public void setRawOutPutString(String rawOutPutString) {
		this.rawOutPutString = rawOutPutString;
	}
}
