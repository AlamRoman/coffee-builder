package model;

import java.util.concurrent.Semaphore;

import controller.Controller;
import model.Components.ComponentAdd;
import model.Components.ComponentComment;
import model.Components.ComponentDeclaration;
import model.Components.ComponentIf;
import model.Components.ComponentInput;
import model.Components.ComponentOperation;
import model.Components.ComponentOutput;
import model.Components.ComponentWhile;
import model.Components.Condition;
import model.Memory.MemoryStorage;
import model.Memory.Variable;

/**
 * <p>
 * This class contains all the possible flags that can be changed for a cleaner debugging process
 * </p>
 * */
public class Debug {
	
	/**
	 * <p>The flag that ignores all the other flags if true</p>
	 * */
	public final static boolean FORCE_DEBUG = true;
	
	/**
	 * <p>The flag that specifies if {@link ComponentAdd} info should be shown during debug</p>
	 * */
	public final static boolean COMPONENT_ADD_DEBUG = false;
	
	/**
	 * <p>The flag that specifies if {@link ComponentComment} info should be shown during debug</p>
	 * */
	public final static boolean COMPONENT_ASSIGN_DEBUG = false;
	
	/**
	 * <p>The flag that specifies if {@link ComponentAdd} info should be shown during debug</p>
	 * */
	public final static boolean COMPONENT_COMMENT_DEBUG = false;
	
	/**
	 * <p>The flag that specifies if {@link ComponentDeclaration} info should be shown during debug</p>
	 * */
	public final static boolean COMPONENT_DECLARATION_DEBUG = false;
	
	/**
	 * <p>The flag that specifies if {@link ComponentIf} info should be shown during debug</p>
	 * */
	public final static boolean COMPONENT_IF_DEBUG = false;
	
	/**
	 * <p>The flag that specifies if {@link ComponentInput} info should be shown during debug</p>
	 * */
	public final static boolean COMPONENT_INPUT_DEBUG = false;
	
	/**
	 * <p>The flag that specifies if {@link ComponentOperation} info should be shown during debug</p>
	 * */
	public final static boolean COMPONENT_OPERATION_DEBUG = false;
	
	/**
	 * <p>The flag that specifies if {@link ComponentOutput} info should be shown during debug</p>
	 * */
	public final static boolean COMPONENT_OUTPUT_DEBUG = false;
	
	/**
	 * <p>The flag that specifies if {@link ComponentWhile} info should be shown during debug</p>
	 * */
	public final static boolean COMPONENT_WHILE_DEBUG = false;
	
	/**
	 * <p>The flag that specifies if {@link MemoryStorage} info should be shown during debug</p>
	 * */
	public final static boolean MEMORY_DEBUG = false;
	
	/**
	 * <p>The flag that specifies if {@link Variable} info should be shown during debug</p>
	 * */
	public final static boolean VARIABLE_DEBUG = false;
	
	/**
	 * <p>The flag that specifies if {@link Semaphore} info should be shown during debug</p>
	 * */
	public final static boolean SEMAPHORE_DEBUG = true;
	
	/**
	 * <p>The flag that specifies if {@link Timer} info should be shown during debug</p>
	 * */
	public final static boolean TIMER_DEBUG = false;
	
	/**
	 * <p>The flag that specifies if {@link AlgorithmExecuter} info should be shown during debug</p>
	 * */
	public final static boolean EXECUTER_DEBUG = true;
	
	/**
	 * <p>The flag that specifies if {@link Condition} info should be shown during debug</p>
	 * */
	public static final boolean CONDITION_DEBUG = false;
	
	/**
	 * <p>The flag that specifies if {@link Controller} info should be shown during debug</p>
	 * */
	public static final boolean CONTROLLER_DEBUG = true;
	
	/**
	 * <p>The flag that specifies if method callers info should be shown during debug</p>
	 * */
	public final static boolean SHOW_CALLERS = true;
	
	/**
	 * <p>The flag that specifies if extra info should be shown during debug</p>
	 * */
	public final static boolean SHOW_EXTRA_INFO_IN_EXCEPTIONS = false;
	
}
