package main.TEST;

import model.AlgorithmExecuter;
import model.Color;
import model.Debug;
import model.DebuggerConsole;
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
		
		
		ComponentOperation operation_2 = new ComponentOperation(end, null, MEMORY);
		operation_2.set("num4", "2.0", "2.0", TipoOperazioni.PIU);
		ComponentOperation operation_1 = new ComponentOperation(operation_2, null, MEMORY);
		operation_1.set("stringHelloWorld", "2.0", "2.0", TipoOperazioni.PIU);
		
		
		ComponentAssign assign_5 = new ComponentAssign(operation_1, null, MEMORY);
		assign_5.set(0.0, "num4");
		ComponentDeclaration declare_5 = new ComponentDeclaration(assign_5, null, MEMORY);
		declare_5.set(Type.Double, "num4");
		ComponentAssign assign_4 = new ComponentAssign(declare_5, null, MEMORY);
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
		
		//EXECUTE--------------------------------------
		
		try {
			EXECUTER.start(start);
		} catch (Exceptions e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}
		
		MEMORY.showMemory();
		
		//OUT OF EXECUTION TESTS------------------------
		
		System.out.println("\nCONDITION.class--------\n");
		
		Condition C = new Condition(null, "0", "<", "1");
		try {
			System.out.println(C.toString() + " | " +  C.resolve());
		} catch (Exceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DebuggerConsole.getInstance().printLog(Color.BLUE_BOLD_BRIGHT, "TEST", Color.RED_BOLD, "Viva la patata");
		DebuggerConsole.getInstance().printInfoLog(Thread.currentThread(), Color.BLUE_BOLD_BRIGHT, "TEST-INFO-LOG", Color.RED_BOLD, "Viva la patata");
		DebuggerConsole.getInstance().printDefaultInfoLog(Thread.currentThread(), "TEST-DEFAULT-INFO", "Viva la patata");
		DebuggerConsole.getInstance().printDefaultSuccessLog(Thread.currentThread(), "TEST-SUCCESS-INFO", "Viva la patata");
		DebuggerConsole.getInstance().printDefaultErrorLog(Thread.currentThread(), "TEST-ERROR-INFO", "Viva la patata");
		
	}

}
