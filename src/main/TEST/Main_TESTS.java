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
import model.Memory.TipoOperazioni;
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
		operation.set("num1", "1", "1", TipoOperazioni.PIU);
		
		System.out.println("\nDECLARE AND ASSIGN TEST--------\n");
		
		ComponentAssign assign_4 = new ComponentAssign(operation, null, MEMORY);
		assign_4.set("Hello, world!", "stringHelloWorld");
		ComponentDeclaration declare_4 = new ComponentDeclaration(assign_4, null, MEMORY);
		declare_4.set(Type.String, "stringHelloWorld");
		ComponentAssign assign_3 = new ComponentAssign(declare_4, null, MEMORY);
		assign_3.set(2, "num3");
		ComponentDeclaration declare_3 = new ComponentDeclaration(assign_3, null, MEMORY);
		declare_3.set(Type.Integer, "num3");
		ComponentAssign assign_2 = new ComponentAssign(declare_3, null, MEMORY);
		assign_2.set(2, "num2");
		ComponentDeclaration declare_2 = new ComponentDeclaration(assign_2, null, MEMORY);
		declare_2.set(Type.Integer, "num2");
		ComponentAssign assign_1 = new ComponentAssign(declare_2, null, MEMORY);
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