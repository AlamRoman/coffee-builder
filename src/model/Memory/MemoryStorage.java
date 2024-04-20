package model.Memory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Debug;
import model.DebuggerConsole;
import model.Exceptions;
import model.Components.AlgorithmComponent;
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
	ArrayList<AlgorithmComponent> algorithmComponents;
	private static MemoryStorage instance;
	private static final String referenceTypeMessage = "MEMORY";

	public MemoryStorage() {
		super();
		this.memory = new HashSet<Variable>();
		this.algorithmComponents = new ArrayList<AlgorithmComponent>();
		initializeComponents();
	}
	
	private void initializeComponents() {
		// TODO Auto-generated method stub
		algorithmComponents.add(new ComponentStart(null));
		algorithmComponents.add(new ComponentEnd());
		algorithmComponents.get(0).setNextComponent1(algorithmComponents.get(1));
		showComponents();
		
	}
	
	public void addComponent(AlgorithmComponent c, int index) {
		showComponents();
		algorithmComponents.add(index, c);
		DebuggerConsole.getInstance().printDefaultSuccessLog(referenceTypeMessage, "Added component at position " + index + ", updating connections...");
		updateComponentConnections(index);
		
	}
	// E = C
	// F = C_ADD
	// A > B > C | + E, F | > D components.get(index-1).setNextComponent1(components.get(index));
	//A > B > C > E | + F| > D components.get(index).setNextComponent1(components.get(index));
	//A > B > C > E > F > D
	
	private void updateComponentConnections(int index) {
		algorithmComponents.get(index).setNextComponent1(algorithmComponents.get(index-1).getNextComponent1());
		algorithmComponents.get(index-1).setNextComponent1(algorithmComponents.get(index));
		DebuggerConsole.getInstance().printDefaultSuccessLog(referenceTypeMessage, "Connections updated.");
		showComponents();
	}

	public static MemoryStorage getInstance() {
		instance = (instance==null) ? (new MemoryStorage()) : instance;
		return instance;
	}
	
	public void destroyVariables() {
		memory.clear();
		DebuggerConsole.getInstance().printDefaultInfoLog(referenceTypeMessage, "Memory deleted.");
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
        sb.append("--------------------------------------------------------------------------------------------------------------\n");
		sb.append(String.format("%-5s %-20s %-20s %-20s %-30s\n", "Index", "Class", "NComp1", "NComp2", "toString"));
        sb.append("--------------------------------------------------------------------------------------------------------------\n");
        
        for (int i = 0; i < algorithmComponents.size(); i++) {
            AlgorithmComponent algorithmComponent = algorithmComponents.get(i);
            String className = algorithmComponent.getClass().getSimpleName();
            AlgorithmComponent n1 = algorithmComponent.getNextComponent1();
            AlgorithmComponent n2 = algorithmComponent.getNextComponent2();
            String nComp1 = (n1==null)?null:algorithmComponent.getNextComponent1().getClass().getSimpleName();
            String nComp2 = (n2==null)?null:algorithmComponent.getNextComponent1().getClass().getSimpleName();
            String ts = algorithmComponent.toString();
            sb.append(String.format("%-5s %-20s %-20s %-20s %-30s\n", i, className, nComp1, nComp2, ts));
        }
        sb.append("=============================================================================================================\n");
        return sb.toString();
    }
	
	public JTable createMemoryTable() {
        // Creazione del modello della tabella
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Type");
        model.addColumn("Variable Name");
        model.addColumn("Value");

        // Aggiunta delle righe alla tabella
        for (Variable var : memory) {
            model.addRow(new Object[]{var.getType(), var.getName(), var.getValue()});
        }

        // Creazione della JTable con il modello creato
        JTable table = new JTable(model);
        return table;
    }
	
	public void showComponents() {
		System.out.println("\n" + this.printComponents() + "\n");
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "\nMEMORY:\n" + printMemory() + "\n" + printComponents() + "\n";
	}

	public AlgorithmComponent getStartComponent() {
		// TODO Auto-generated method stub
		return algorithmComponents.get(0);
	}
	
	public AlgorithmComponent getEndComponent() {
		// TODO Auto-generated method stub
		return algorithmComponents.get(algorithmComponents.size()-1);
	}

//	public void initializeDefaultComponents() throws Exceptions {
//		components.add(new ComponentEnd());
//		components.add(0, new ComponentStart(components.get(0)));
//		showComponents();
//		
//	}
	
	public void print() {
		System.out.println(this.toString());
	}

	public int getIndexOf(AlgorithmComponent c) {
		// TODO Auto-generated method stub
		DebuggerConsole.getInstance().printDefaultInfoLog(referenceTypeMessage, "Searching for " + c + " in the memory...");
		int idx = this.algorithmComponents.indexOf(c);
		if(idx == -1) {
			DebuggerConsole.getInstance().printDefaultErrorLog(referenceTypeMessage, "Not found.");
		}else {
			DebuggerConsole.getInstance().printDefaultSuccessLog(referenceTypeMessage, "Found at " + idx);
		}
		return idx;
	}

	public ArrayList<AlgorithmComponent> getComponents() {
		// TODO Auto-generated method stub
		return algorithmComponents;
	}
	
	
	
}
