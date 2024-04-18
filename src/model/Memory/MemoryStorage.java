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
	
	public void addComponent(Component c, int index) {
	//	ComponentAdd addAfter = new ComponentAdd();
		components.add(index, c);
	//	components.add(index+1, addAfter);
		updateComponentConnections(index);
		
	}
	// E = C
	// F = C_ADD
	// A > B > C | + E, F | > D components.get(index-1).setNextComponent1(components.get(index));
	//A > B > C > E | + F| > D components.get(index).setNextComponent1(components.get(index));
	//A > B > C > E > F > D
	
	private void updateComponentConnections(int index) {
		//Supponendo siano componenti semplici e non while o if:
		//index = 5 | (4,5) > (5,6), (6,7)
		for (int i = index - 1; i <= index + 1; i++) {
		    components.get(i).setNextComponent1(components.get(i + 1));
		}
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
	
	public Variable addVariable(Variable var) throws Exceptions{
		DebuggerConsole.getInstance().printDefaultInfoLog(referenceTypeMessage, "Adding variable " + var + " to the memory");
		if(!memory.add(var)) {
			throw new Exceptions(Exceptions.EXISTING_VARIABLE, "| thrown in " + this.getClass().getSimpleName());
		}
		DebuggerConsole.getInstance().printDefaultSuccessLog(referenceTypeMessage, "Added");
		return memory.toArray(new Variable[memory.size()])[memory.size()-1];
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
	
    public String printMemory() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-31s %s %-31s\n", "", "MEMORY TABLE", ""));
        sb.append("--------------------------------------------------------------\n");
        sb.append(String.format("%-25s %-25s %-25s\n", "Type", "VariableName", "Value"));
        sb.append("--------------------------------------------------------------\n");
        for (Variable var : memory) {
            sb.append(String.format("%-25s %-25s %-25s\n", var.getType(), var.getName(), var.getValue()));
        }
        sb.append("==============================================================\n");
        return sb.toString();
    }
	
	public void showMemory() {
		System.out.println("\n" + this.printMemory() + "\n");
	}
	
	public String printComponents() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%-44s %s %-44s\n", "", "COMPONENTS TABLE", ""));
        sb.append("-------------------------------------------------------------------------------------------------\n");
		sb.append(String.format("%-25s %-25s %-25s %-25s\n", "Index", "Class", "NComp1", "NComp2"));
        sb.append("-------------------------------------------------------------------------------------------------\n");
        
        for (int i = 0; i < components.size(); i++) {
            Component component = components.get(i);
            String className = component.getClass().getSimpleName();
            Component n1 = component.getNextComponent1();
            Component n2 = component.getNextComponent2();
            String nComp1 = (n1==null)?null:component.getNextComponent1().getClass().getSimpleName();
            String nComp2 = (n2==null)?null:component.getNextComponent1().getClass().getSimpleName();
            sb.append(String.format("%-25s %-25s %-25s %-25s\n", i, className, nComp1, nComp2));
        }
        sb.append("================================================================================================\n");
        return sb.toString();
    }
	
	public void showComponents() {
		System.out.println("\n" + this.printComponents() + "\n");
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "\nMEMORY:\n" + printMemory() + "\n" + printComponents() + "\n";
	}

	public Component getStartComponent() {
		// TODO Auto-generated method stub
		return components.get(0);
	}
	
	public Component getEndComponent() {
		// TODO Auto-generated method stub
		return components.get(components.size()-1);
	}

	public void initializeDefaultComponents() {
		components.add(new ComponentEnd());
		components.add(0, new ComponentStart(components.get(0)));
		
	}
	
	public void print() {
		System.out.println(this.toString());
	}
	
	
	
}
