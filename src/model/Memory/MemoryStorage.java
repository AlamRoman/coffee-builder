package model.Memory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import model.DebuggerConsole;
import model.Exceptions;
import model.Components.Component;
import model.Components.ComponentEnd;
import model.Components.ComponentStart;

public class MemoryStorage {
	
	/* IDEA PER L'ELIMINAZIONE
	 * 
	 * 0. L'utente sceglie di elminare un componente, e gli viene chiesta la conferma con un JOptionPane
	 * 1. Il controller capisce quale componente e' stato cliccato e scelto per l'eliminazione
	 * 2. Il controller passa questo componente alla memoria con un metodo .delete(c)
	 * 3. La memoria controlla dov'e' posizionato il c. e se non e' ne' START ne' END procede all'eliminazione
	 * 4. Assegna come next c. il c. posizionato in posizione (indexDaEliminare + 1)
	 * 5. Dopo l'eliminazione, aggiornare la grafica
	 * 
	 * */
	
	//IDEE PER L'AGGIUNTA (C_A = comp. add, C = general component)
	
	//C, C_A, C, C_A, C PRIMA
	//          /  |  \ RIMPIAZZA {C_A} con {C_A, C, C_A} ELIMINANDO IL C_A CLICCATO DALL'UTENTE
//C, C_A, C, C_A, C,  C_A, C DOPO
	
	//C, C_A, C, C_A, C PRIMA
	//            |  \ AGGIUNGE {C_A, C, C_A} DOPO IL C_A CLICCATO DALL'UTENTE
//C, C_A, C, C_A, C,  C_A, C
	
	HashSet<Variable> memory;
	ArrayList<Component> components;
	private static MemoryStorage instance;
	private static final String referenceTypeMessage = "MEMORY";

	public MemoryStorage() {
		super();
		this.memory = new HashSet<Variable>();
		this.components = new ArrayList<Component>();
		initializeComponents();
	}
	
	private void initializeComponents() {
		// TODO Auto-generated method stub
		components.add(new ComponentStart(null));
		components.add(new ComponentEnd());
		components.get(0).setNextComponent1(components.get(1));
		
	}
	
	public void addComponent(Component c) {
		components.add(c);
		Collections.swap(components, components.size()-2, components.size()-1);
		
	}

	public static MemoryStorage getInstance() {
		instance = (instance==null) ? (new MemoryStorage()) : instance;
		return instance;
	}
	
	public void destroyVariables() {
		memory.clear();
	}
	
	public static void destroyInstance() {
		instance = null;
	}
	
	public void addVariable(Variable var) throws Exceptions{
		DebuggerConsole.getInstance().printDefaultInfoLog(referenceTypeMessage, "Adding variable " + var + " to the memory");
		if(!memory.add(var)) {
			throw new Exceptions(Exceptions.EXISTING_VARIABLE, "| thrown in " + this.getClass().getSimpleName());
		}
		DebuggerConsole.getInstance().printDefaultSuccessLog(referenceTypeMessage, "Added");
//		showMemory();
	}
	
	public Variable getVariableByName(String name) throws Exceptions{
		DebuggerConsole.getInstance().printDefaultInfoLog(referenceTypeMessage, "Getting variable with name: " + name + " from the memory table");
//		showMemory();
		for (Variable temp : memory) {
			DebuggerConsole.getInstance().printDefaultInfoLog(referenceTypeMessage, "Checking if " + temp.getName() + " == " + name);
			if(temp.getName().equals(name)) {
				DebuggerConsole.getInstance().printDefaultSuccessLog(referenceTypeMessage, "Returning: " + temp);
				return temp;
			}
			
		}
		
		throw new Exceptions(Exceptions.NON_EXISTING_VARIABLE);
		
	}
	
	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MEMORY TABLE\n");
        sb.append(String.format("%-25s %-25s %-25s\n", "Type", "VariableName", "Value"));
        sb.append("--------------------------------------------------------------\n");
        for (Variable var : memory) {
            sb.append(String.format("%-25s %-25s %-25s\n", var.getType(), var.getName(), var.getValue()));
        }
        return sb.toString();
    }
	
	public void showMemory() {
		System.out.println("\n" + this.toString() + "\n");
	}
	
	
	
}
