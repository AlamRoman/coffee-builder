package main.TEST;

import model.AlgorithmExecuter;
import model.Exceptions;
import model.Components.ComponentAssign;
import model.Components.ComponentDeclaration;
import model.Components.ComponentEnd;
import model.Components.ComponentOperation;
import model.Components.ComponentStart;
import model.Components.Condition;
import model.Memory.MemoryStorage;
import model.Memory.Type;

public class Main_TESTS {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//EXECUTION TESTS--------------------------------------
		
				MemoryStorage MEMORY = MemoryStorage.getInstance();
				ComponentEnd end = new ComponentEnd();
				AlgorithmExecuter EXECUTER = new AlgorithmExecuter();
				
				System.out.println("\nOPERATION TEST--------\n");
				
				ComponentOperation operation = new ComponentOperation(end, null, MEMORY);
				operation.set(null, null, null, null);
				
				System.out.println("\nDECLARE AND ASSIGN TEST--------\n");
				
				ComponentAssign assign_1 = new ComponentAssign(operation, null, MEMORY);
				assign_1.set(2, "num1");
				ComponentDeclaration declare_1 = new ComponentDeclaration(assign_1, null, MEMORY);
				declare_1.set(Type.Integer, "num1");
				ComponentStart start = new ComponentStart(declare_1);
				MEMORY.toString();
				
				//EXECUTE--------------------------------------
				
				try {
					EXECUTER.start(start);
				} catch (Exceptions e) {
					// TODO Auto-generated catch block
					System.err.println(e.getMessage());
				}
				
				//OUT OF EXECUTION TESTS------------------------
				
				System.out.println("\nCONDITION.class--------\n");
				
				Condition C = new Condition(null, "0", "<", "1");
				try {
					System.out.println(C.toString() + " | " +  C.resolve());
				} catch (Exceptions e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}

}
