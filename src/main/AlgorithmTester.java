package main;

import java.util.concurrent.Semaphore;

import model.AlgorithmExecuter;
import model.Color;
import model.Debug;
import model.DebuggerConsole;
import model.Exceptions;
import model.Timer;
import model.Components.ComponentAdd;
import model.Components.ComponentAssign;
import model.Components.ComponentComment;
import model.Components.ComponentDeclaration;
import model.Components.ComponentEnd;
import model.Components.ComponentIf;
import model.Components.ComponentInput;
import model.Components.ComponentOperation;
import model.Components.ComponentOutput;
import model.Components.ComponentStart;
import model.Components.ComponentWhile;
import model.Components.Condition;
import model.Memory.MemoryStorage;
import model.Memory.RelationalOperators;
import model.Memory.OperationType;
import model.Memory.VariableType;

public class AlgorithmTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//EXECUTION TESTS--------------------------------------
		
		Semaphore exec = new Semaphore(1);
		Semaphore wait = new Semaphore(0);
		MemoryStorage MEMORY = MemoryStorage.getInstance();
		ComponentEnd end = new ComponentEnd();
		ComponentAdd ADD__1 = new ComponentAdd();
		AlgorithmExecuter EXECUTER = new AlgorithmExecuter();
		Timer timer = new Timer(exec, wait);
		
		ComponentComment comment_1 = new ComponentComment(end, null, MEMORY);
		comment_1.set("This is a comment");
		ComponentOutput output_1 = new ComponentOutput(comment_1, null, MEMORY);
		output_1.set("Mi piace la patata $num1");
		ComponentOperation operation_2 = new ComponentOperation(output_1, null, MEMORY);
		operation_2.set("num4", "2.0", "2.0", OperationType.ADD);
		ComponentOperation operation_1 = new ComponentOperation(operation_2, null, MEMORY);
		operation_1.set("stringHelloWorld", "2.0", "2.0", OperationType.ADD);
		ComponentAssign assign_5 = new ComponentAssign(operation_1, null, MEMORY);
		assign_5.set(0.0, "num4");
		ComponentDeclaration declare_5 = new ComponentDeclaration(assign_5, null, MEMORY);
		declare_5.set(VariableType.Double, "num4");
		ComponentAssign assign_4 = new ComponentAssign(declare_5, null, MEMORY);
		assign_4.set("Hello, world!", "stringHelloWorld");
		ComponentDeclaration declare_4 = new ComponentDeclaration(assign_4, null, MEMORY);
		declare_4.set(VariableType.String, "stringHelloWorld");
		
		//-----WHILE TEST---------------------------------
		ComponentWhile _while = new ComponentWhile(declare_4, MEMORY);
		_while.set("$num3", RelationalOperators.NOT, "10");

		//-----REQUIRED COMPONENTS FOR WHILE TEST---------
		//-----------------IF TRUE EXECUTE THIS:------
		ComponentOperation operation_true_while = new ComponentOperation(_while, null, MEMORY);
		operation_true_while.set("num3", "$num3", "1", OperationType.ADD);
		_while.setNextComponent1(operation_true_while);
		
		
		
		//-----REQUIRED COMPONENTS FOR IF TEST---------
		//-----------------IF FALSE EXECUTE THIS:------
		ComponentAssign assign_false = new ComponentAssign(_while, null, MEMORY);
		assign_false.set(100, "num1");
		//-----------------IF TRUE EXECUTE THIS:------
		ComponentAssign assign_true = new ComponentAssign(_while, null, MEMORY);
		assign_true.set(100, "num2");
		
		//-----IF TEST---------------------------------
		ComponentIf _if = new ComponentIf(assign_true, assign_false, MEMORY);
		_if.set("0", RelationalOperators.NOT, null);
		
		ComponentAssign assign_3 = new ComponentAssign(_if, null, MEMORY);
		assign_3.set(2, "num3");
		ComponentDeclaration declare_3 = new ComponentDeclaration(assign_3, null, MEMORY);
		declare_3.set(VariableType.Integer, "num3");
		ComponentAssign assign_2 = new ComponentAssign(declare_3, null, MEMORY);
		assign_2.set(2, "num2");
		ComponentDeclaration declare_2 = new ComponentDeclaration(assign_2, null, MEMORY);
		declare_2.set(VariableType.Integer, "num2");
//		ComponentAssign assign_1 = new ComponentAssign(declare_2, null, MEMORY);
//		assign_1.set(2, "num1");
		ComponentInput input_1 = new ComponentInput(declare_2, null, MEMORY);
		input_1.set("num1");
		ComponentDeclaration declare_1 = new ComponentDeclaration(input_1, null, MEMORY);
		declare_1.set(VariableType.Integer, "num1");
		ComponentStart start = new ComponentStart(declare_1);
		
		//EXECUTE--------------------------------------
		
		try {
			timer.set(1);
			EXECUTER.start(start);
		} catch (Exceptions e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}
		
		//OUT OF EXECUTION TESTS------------------------
//		
//		System.out.println("\nCONDITION.class--------\n");
//		
//		Condition C = new Condition(null, "0", "<", "1");
//		try {
//			System.out.println(C.toString() + " | " +  C.resolve());
//		} catch (Exceptions e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		DebuggerConsole.getInstance().printLog(Color.BLUE_BOLD_BRIGHT, "TEST", Color.RED_BOLD, "Viva la patata");
//		DebuggerConsole.getInstance().printInfoLog(Color.BLUE_BOLD_BRIGHT, "TEST-INFO-LOG", Color.RED_BOLD, "Viva la patata");
//		DebuggerConsole.getInstance().printDefaultInfoLog("TEST-DEFAULT-INFO", "Viva la patata");
//		DebuggerConsole.getInstance().printDefaultSuccessLog("TEST-SUCCESS-INFO", "Viva la patata");
//		DebuggerConsole.getInstance().printDefaultErrorLog("TEST-ERROR-INFO", "Viva la patata");
//		System.out.println(_if.print());
//		System.out.println(_while.print());
		
	}

}
