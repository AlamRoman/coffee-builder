package model.Memory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;

import javax.management.modelmbean.ModelMBean;
import javax.script.Compilable;
import javax.sound.midi.Instrument;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Debug;
import model.DebuggerConsole;
import model.Exceptions;
import model.Line;
import model.Components.AlgorithmComponent;
import model.Components.ComponentAdd;
import model.Components.ComponentElse;
import model.Components.ComponentEnd;
import model.Components.ComponentIf;
import model.Components.ComponentInput;
import model.Components.ComponentStart;
import model.Components.ComponentWhile;
import model.Components.Condition;
import view.editComponents.AddComponent;
import view.editComponents.EditWhile.ValuesWhileComponent;

/**<p>
* This class is used to keep saved all the {@link Variable}s and all the {@link AlgorithmComponent}s used and initalized during the 
* execution of the algorithm created by the user, also manages the connections of the single components when one/more components are being
* added or deleted from the memory
* </p>
*/
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
	
	HashSet<Variable> memory;
	ArrayList<AlgorithmComponent> algorithmComponents;
	private static MemoryStorage instance;
	private static final String referenceTypeMessage = "MEMORY";
	private boolean onGoing;

	/**<p>
	* The constructor of the class {@link MemoryStorage}
	* </p>
	*/
	public MemoryStorage() {
		super();
		this.memory = new HashSet<Variable>();
		this.algorithmComponents = new ArrayList<AlgorithmComponent>();
		onGoing=false;
		initializeComponents();
	}
	
	/**<p>
	* This method intializes the base components, needed for the correct 
	* execution of the algorithm created by the user
	* </p>
	* <p>
	* Creates <code>ComponentStart</code> and <code>ComponentEnd</code> and adds them to the MemoryStorage via the {@link java.util.ArrayList#add(E e)}
	* method
	* </p>
	*/
	private void initializeComponents() {
		// TODO Auto-generated method stub
		algorithmComponents.add(new ComponentStart(null));
		algorithmComponents.add(new ComponentEnd());
		algorithmComponents.get(0).setNextComponent1(algorithmComponents.get(1));
		showComponents();
		
	}
	
	/**<p>
	* This method adds a component to the {@link ArrayList} of components in MemoryStorage in a specific index
	* </p>
	* <p>
	* Creates <code>ComponentStart</code> and <code>ComponentEnd</code> and adds them to the MemoryStorage via the {@link ArrayList#add(E e)}
	* method
	* </p>
	* @param c the component that needs to be added
	* @param index the position we want the component to be in
	*/
	public void addComponent(AlgorithmComponent c, int index) {
		showComponents();
		algorithmComponents.add(index, c);
		DebuggerConsole.getInstance().printDefaultSuccessLog(referenceTypeMessage, "Added component at position " + index + ", updating connections...");
		updateComponentConnections(index);
		
	}
	
	/**<p>
	* This method updates the component connections after adding a new component
	* </p>
	* <p>
	* Updates the link of the previous and next components after the method {@link MemoryStorage#addComponent(AlgorithmComponent, int)} is called.
	* It calls two different methods, {@link AlgorithmComponent#setNextComponent1(AlgorithmComponent)} and {@link AlgorithmComponent#setNextComponent2(AlgorithmComponent)} to set
	* the new connection to the previous component [<code>index-1</code>] and the added one[<code>index</code>] 
	* </p>
	* 
	* @param index the position we put the new component in
	* @return      nothing
	*/
	private void updateComponentConnections(int index) {
		algorithmComponents.get(index).setNextComponent1(algorithmComponents.get(index-1).getNextComponent1());
		algorithmComponents.get(index-1).setNextComponent1(algorithmComponents.get(index));
		DebuggerConsole.getInstance().printDefaultSuccessLog(referenceTypeMessage, "Connections updated.");
		showComponents();
	}


	/**<p>
	 * This method returns the instance of {@link MemoryStorage}
	 * </p>
	 * <p>
	 * This method creates the instance of {@link MemoryStorage} if it doesn't exist and returns it
	 * If its already created it returns the instance
	 * </p>
	 * 
	 * @return {@link MemoryStorage} The instance of the memory
	 */
	public static MemoryStorage getInstance() {
		instance = (instance==null) ? (new MemoryStorage()) : instance;
		return instance;
	}
	

	/**<p>
	 * This method destroys all the variables inside the memory
	 * </p>
	 * <p>
	 * This method clears the {@link ArrayList} containing the variables stored in the memory
	 * by calling {@link ArrayList#clear()}
	 * </p>
	 * 
	 * @see ArrayList
	 */
	public void destroyVariables() {
		memory.clear();
		DebuggerConsole.getInstance().printDefaultInfoLog(referenceTypeMessage, "Memory deleted.");
	}
	
	/**<p>
	 * This method destroys the memory instance
	 * </p>
	 * <p>
	 * This method deletes the {@link MemoryStorage} instance by setting it to <code>null</code>
	 * </p> 
	 */
	public static void destroyInstance() {
		instance = null;
	}
	
	/**<p>
	 * This method adds a variable to the memory
	 * </p>
	 * <p>
	 * This methods adds the variable <code>var</code> to the memory by calling the {@link ArrayList#add(E e)}
	 * method to the {@link MemoryStorage#memory}, the {@link ArrayList} containg all the variables
	 * </p>
	 * 
	 * @return {@link Variable} the variable added to the memory
	 * @param var the variable that will be added in the memory
	 * @throws Exceptions EXISTING_VARIABLE: If the variable already exists in the memory
	 * @see ArrayList
	 */
	public Variable addVariable(Variable var) throws Exceptions{
		DebuggerConsole.getInstance().printDefaultInfoLog(referenceTypeMessage, "Adding variable " + var + " to the memory");
		if(!memory.add(var)) {
			throw new Exceptions(Exceptions.EXISTING_VARIABLE, "| thrown in " + this.getClass().getSimpleName());
		}
		DebuggerConsole.getInstance().printDefaultSuccessLog(referenceTypeMessage, "Added");
		return memory.toArray(new Variable[memory.size()])[memory.size()-1];
//		showMemory();
	}
	
	/**<p>
	 * This method returns a variable obtained with the provided <code>name</code>.
	 * </p>
	 * <p>
	 * This methods searches the variable with the <code>name</code> provided to the method by looping through all the variables in the
	 * {@link ArrayList} in the {@link MemoryStorage}, that contains all the variables, if a variable name matches the <code>name</code> provided, it returns the variable
	 * </p>
	 * 
	 * @return {@link Variable} the variable found in the memory
	 * @param name The name of the requested variable
	 * @throws Exceptions NON_EXISTING_VARIABLE: If the variable doesn't exists in the memory
	 * @see ArrayList
	 */
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
		
		throw new Exceptions(name + ": " + Exceptions.NON_EXISTING_VARIABLE);
		
	}
	
	/**<p>
	 * This method returns a {@link String} containing a table with all the {@link Variable}s informations.
	 * </p>
	 * <p>
	 * This method builds a string with {@link StringBuilder} containing all the {@link Variable}s informations and returns
	 * the final built string by calling the method {@link StringBuilder#toString()}
	 * </p>
	 * 
	 * @return {@link String} The string containing the table with the {@link Variable}s informations.
	 * @see StringBuilder
	 * @see Variable
	 */
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
	
    /**<p>
	 * This method prints via <code>System.out.println()</code> the return from {@link MemoryStorage#showMemory()}.
	 * </p>
	 * @see MemoryStorage#showMemory()
	 */
	public void showMemory() {
		System.out.println("\n" + this.printMemory() + "\n");
	}
	
	/**<p>
	 * This method returns a {@link String} containing a table with all the {@link AlgorithmComponent}s informations.
	 * </p>
	 * <p>
	 * This method builds a string with {@link StringBuilder} containing all the {@link AlgorithmComponent}s informations and returns
	 * the final built string by calling the method {@link StringBuilder#toString()}
	 * </p>
	 * @return {@link String} The string containing the table with the {@link AlgorithmComponent}s informations.
	 * @see StringBuilder
	 * @see AlgorithmComponent
	 */
	public String printComponents() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%-44s %s %-44s\n", "", "COMPONENTS TABLE", ""));
        sb.append("--------------------------------------------------------------------------------------------------------------\n");
		sb.append(String.format("%-5s %-20s %-25s %-25s %-30s\n", "Index", "Class", "NComp1[index]", "NComp2[index]", "toString"));
        sb.append("--------------------------------------------------------------------------------------------------------------\n");
        
        for (int i = 0; i < algorithmComponents.size(); i++) {
            AlgorithmComponent algorithmComponent = algorithmComponents.get(i);
            String className = algorithmComponent.getClass().getSimpleName();
            AlgorithmComponent n1 = algorithmComponent.getNextComponent1();
            AlgorithmComponent n2 = algorithmComponent.getNextComponent2();
            String nComp1 = (n1==null)?null:algorithmComponent.getNextComponent1().getClass().getSimpleName();
            String nComp2 = (n2==null)?null:algorithmComponent.getNextComponent2().getClass().getSimpleName();
            int idx1 = getIndexOf(n1);
            int idx2 = getIndexOf(n2);
            String ts = algorithmComponent.toString();
            sb.append(String.format("%-5s %-20s %-25s %-25s %-30s\n", i, className, nComp1+"["+((idx1==-1)?"n/a":idx1)+"]", nComp2+"["+((idx2==-1)?"n/a":idx2)+"]", ts));
        }
        sb.append("=============================================================================================================\n");
        sb.append(printComponentsList());
        return sb.toString();
    }
	
	/**
	 * This method returns a {@link JTable} containing a table with all the {@link Variable}s informations.
	 * <p>
	 * This method builds a {@link JTable} with a {@link DefaultTableModel} containing all the {@link Variable}s informations and returns
	 * the final built {@link JTable}
	 * 
	 * @return {@link JTable} The table containing the table with the {@link Variable}s informations.
	 * @see JTable
	 * @see Variable
	 */
	public JTable createMemoryTable() {
        // Creazione del modello della tabella
        DefaultTableModel model = new DefaultTableModel() {
        	
        	@Override
        	public boolean isCellEditable(int row, int column) {
        		// TODO Auto-generated method stub
        		return false;
        	}
        	
        };
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
	
	/**<p>
	 * This method prints via <code>System.out.println()</code> the return from {@link MemoryStorage#printComponents()}.
	 * </p>
	 * @see MemoryStorage#printComponents()
	 */
	public void showComponents() {
		System.out.println("\n" + this.printComponents() + "\n");
	}
	
	/**
	 * This method returns a string with all the {@link MemoryStorage} informations.
	 * <p>
	 * This method returns a {@link String} with all the {@link MemoryStorage} informations <code>System.out.println()</code> returned from {@link MemoryStorage#printComponents()}
	 * and {@link MemoryStorage#printMemory()}
	 * @see MemoryStorage#printComponents()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "\nMEMORY:\n" + printMemory() + "\n" + printComponents() + "\n";
	}

	/**<p>
	 * This method returns the {@link ComponentStart} positioned on the first position of the {@link MemoryStorage#algorithmComponents} arrayList
	 * </p>
	 * @return {@link ComponentStart}
	 */
	public AlgorithmComponent getStartComponent() {
		// TODO Auto-generated method stub
		return algorithmComponents.get(0);
	}
	
	/**<p>
	 * This method returns the {@link ComponentEnd} positioned at the end of the {@link MemoryStorage#algorithmComponents} arrayList
	 * </p>
	 * @return {@link ComponentEnd}
	 */
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
	
	/**
	 * This method calls the {@link MemoryStorage#toString()} method.
	 * @see MemoryStorage#toString()
	 */
	
	public void print() {
		System.out.println(this.toString());
	}

	/**
	 * This method returns the index of the provided {@link AlgorithmComponent} <code>c</code> in the {@link ArrayList} of components.
	 * 
	 * @return int The index of the provided {@link AlgorithmComponent} <code>c</code> in the {@link ArrayList}, returns -1 if not found.
	 * @param c The algorithmComponent that's going to be searched in the array
	 * @see Variable
	 */
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

	/**
	 * This method returns the {@link ArrayList} of components.
	 * 
	 * @return {@link ArrayList} of {@link AlgorithmComponent} The ArrayList of components.
	 */
	public ArrayList<AlgorithmComponent> getComponents() {
		// TODO Auto-generated method stub
		return algorithmComponents;
	}
	
	/**
	 * This method returns a {@link String} containing all the components contained in {@link ArrayList} <code>algorithmComponents</code>.
	 * 
	 * @return {@link String} The string containing all the components
	 */
	public String printComponentsList() {
		String s = "\n";
		for (AlgorithmComponent comp : algorithmComponents) {
			s += comp.toString() + ", ";
		}
		return s;
	}

	/**<p>
	 * This method adds {@link AlgorithmComponent}s to the memory based on the array of {@link AlgorithmComponent} provided as a parameter
	 * </p>
	 * <p>
	 * This methods adds the components contained in the <code>components</code> Array to the memory by calling the {@link ArrayList#add(E e)}
	 * method to the {@link MemoryStorage#algorithmComponents}, the {@link ArrayList} containg all the {@link AlgorithmComponent}s
	 * </p>
	 * <p>
	 * If the size <code>components</code> is 3, it means that a <code>ComponentIf</code> is being added, so it also adds a <code>ComponentElse</code> and a <code>ComponentAdd</code>.
	 * The <code>ComponentElse</code> and its inner AlgorithmComponents will be the executed if the Condition in the <code>ComponentIf</code> is false.
	 * If the Condition it's true, the components following the <code>ComponentIf</code> will be executed.
	 * Both the <code>ComponentElse</code> and the <code>ComponentIf</code> last components point to the final component <code>ComponentAdd</code>
	 * </p>
	 * <p>
	 * If the size <code>components</code> is 2, it means that a <code>ComponentWhile</code> is being added, so it also adds a <code>ComponentAdd</code>.
	 * The <code>ComponentWhile</code> and its inner AlgorithmComponents will be the executed if the Condition in the <code>ComponentIf</code> is true.
	 * The last component in the ComponentWhile will point to the final component ComponentAdd
	 * </p>
	 * 
	 * @param components The Array of algorithm components that needs to be added
	 * @param index The position in which the <code>ComponentIf</code> or <code>ComponentElse</code> is being added
	 * @param isSetAddNextComp1 The boolean flag that will change the way the <code>nextComponent1</code> of the <code>ComponentIf</code>'s <code>ComponentAdd</code> is setted
	 * @see ArrayList
	 * @see AlgorithmComponent
	 * @see ComponentIf
	 * @see ComponentElse
	 * @see ComponentWhile
	 * @see ComponentAdd
	 */
	public void addComponent(AlgorithmComponent[] components, int index, boolean isSetAddNextComp1) {
		// TODO Auto-generated method stub
		showComponents();
		if(components.length == 3) {
			
			final int INDEX_PREV = index-1;
			final int INDEX_IF = index; 
			final int INDEX_ELSE = index+1; 
			final int INDEX_ADD = index+2; 
			
			final int INDEX_NEXT = index+3; 
			
			algorithmComponents.add(INDEX_IF, components[0]);
			algorithmComponents.add(INDEX_ELSE, components[1]);
			algorithmComponents.add(INDEX_ADD, components[2]);
			
			algorithmComponents.get(INDEX_PREV).setNextComponent1(algorithmComponents.get(INDEX_IF));
			algorithmComponents.get(INDEX_IF).setNextComponent1(algorithmComponents.get(INDEX_ADD));
			algorithmComponents.get(INDEX_IF).setNextComponent2(algorithmComponents.get(INDEX_ELSE));
			algorithmComponents.get(INDEX_ELSE).setNextComponent1(algorithmComponents.get(INDEX_ADD));
			if(!isSetAddNextComp1) {
				algorithmComponents.get(INDEX_ADD).setNextComponent1(algorithmComponents.get(INDEX_NEXT));				
			}
			
		}else if(components.length == 2) {
			
			final int INDEX_PREV = index-1;
			final int INDEX_WHILE = index; 
			final int INDEX_ADD = index+1; 
			final int INDEX_NEXT = index+2; 
			
			algorithmComponents.add(INDEX_WHILE, components[0]);
			algorithmComponents.add(INDEX_ADD, components[1]);
			
			algorithmComponents.get(INDEX_PREV).setNextComponent1(algorithmComponents.get(INDEX_WHILE));
			algorithmComponents.get(INDEX_WHILE).setNextComponent1(algorithmComponents.get(INDEX_WHILE));
			algorithmComponents.get(INDEX_WHILE).setNextComponent2(algorithmComponents.get(INDEX_ADD));
			if(!isSetAddNextComp1) {
				algorithmComponents.get(INDEX_ADD).setNextComponent1(algorithmComponents.get(INDEX_NEXT));				
			}
			
		}
		showComponents();
	}

	/**<p>
	 * This method deletes a provided {@link AlgorithmComponent} <code>c</code> from the memory and updates the connections of the previous and the next component
	 * </p>
	 * <p>
	 * This method deletes a provided {@link AlgorithmComponent} <code>c</code> from the memory by calling the method {@link ArrayList#remove(int)} and updates the connections of the previous and the next component with the methods
	 * {@link AlgorithmComponent#setNextComponent1(AlgorithmComponent)} and {@link AlgorithmComponent#setNextComponent2(AlgorithmComponent)}
	 * </p>
	 * 
	 * @param ac The algorithmComponent that will be deleted
	 * @throws Exceptions NOT_DELETABLE: If the algorithmComponent <code>ac</code> cannot be deleted
	 * @throws Exceptions COMPONENT_NOT_EMPTY: If the algorithmComponent contains inner components
	 */
	public void delete(AlgorithmComponent ac) throws Exceptions {
		if(ac instanceof ComponentStart || ac instanceof ComponentAdd || ac instanceof ComponentEnd || ac instanceof ComponentElse) {
			throw new Exceptions(Exceptions.NOT_DELETABLE);
		}
		
		AlgorithmComponent previousAC = algorithmComponents.get(getIndexOf(ac)-1);
		AlgorithmComponent nextAC = algorithmComponents.get(getIndexOf(ac)+1);
		
		//Controllo se il while e' vuoto
		if(ac instanceof ComponentWhile) {
			ac = (ComponentWhile) ac;
//			DebuggerConsole.getInstance().printDefaultInfoLog(referenceTypeMessage, "Checking if " + ac.getNextComponent1() + " == " + ac + " && " + ac.getNextComponent2() + " instanceof ComponentAdd (" + (ac.getNextComponent1() == ac) + ", " + (ac.getNextComponent2() instanceof ComponentAdd) + ")");
			if(ac.getNextComponent1() == ac && ac.getNextComponent2() instanceof ComponentAdd) {
				DebuggerConsole.getInstance().printDefaultInfoLog(referenceTypeMessage, "Passed testing for while deletion");
				//Il while e' vuoto, posso passare alla eliminazione
				DebuggerConsole.getInstance().printDefaultErrorLog(referenceTypeMessage, "Deleting " + algorithmComponents.get(getIndexOf(ac)) + " and " + algorithmComponents.get(getIndexOf(ac)+1));
				
				//Aggiorna i collegamenti
				if(previousAC instanceof ComponentWhile) {
					previousAC.setNextComponent1(previousAC);
					algorithmComponents.remove(getIndexOf(ac.getNextComponent2()));
					algorithmComponents.remove(getIndexOf(ac));
				}else if(previousAC instanceof ComponentIf) {
					ComponentAdd add = recursiveSearchRelatedAdd(previousAC);
					previousAC.setNextComponent1(add);
					algorithmComponents.remove(getIndexOf(ac)+1);
					algorithmComponents.remove(getIndexOf(ac));
				}else {
					algorithmComponents.get(getIndexOf(ac)-1).setNextComponent1(algorithmComponents.get(getIndexOf(ac)+2));
					
					//Elimina componenti
					algorithmComponents.remove(getIndexOf(ac)+1);
					algorithmComponents.remove(getIndexOf(ac));					
				}
			}else {
				DebuggerConsole.getInstance().printDefaultInfoLog(referenceTypeMessage, "Failed testing for while deletion");
				throw new Exceptions(Exceptions.COMPONENT_NOT_EMPTY);
			}
		//Controllo se il if e' vuoto
		}else if(ac instanceof ComponentIf) {
			ac = (ComponentIf) ac;
			if(ac.getNextComponent1() instanceof ComponentAdd && ac.getNextComponent2() instanceof ComponentElse && ac.getNextComponent2().getNextComponent1() instanceof ComponentAdd) {
				DebuggerConsole.getInstance().printDefaultInfoLog(referenceTypeMessage, "Passed testing for while deletion");
				//Il if e' vuoto, posso passare alla eliminazione
				DebuggerConsole.getInstance().printDefaultErrorLog(referenceTypeMessage, "Deleting " + algorithmComponents.get(getIndexOf(ac)) + ", " + algorithmComponents.get(getIndexOf(ac)+1) + " and " + algorithmComponents.get(getIndexOf(ac)+2));
				
				if(previousAC instanceof ComponentWhile) {
					previousAC.setNextComponent1(previousAC);
					
				}else if(previousAC instanceof ComponentIf){
					ComponentAdd addTemp = recursiveSearchRelatedAdd(previousAC);
					algorithmComponents.get(getIndexOf(ac)-1).setNextComponent1(algorithmComponents.get(getIndexOf(ac)+3));	
					previousAC.setNextComponent1(addTemp);
				}else{
					//Aggiorna i collegamenti
					algorithmComponents.get(getIndexOf(ac)-1).setNextComponent1(algorithmComponents.get(getIndexOf(ac)+3));					
				}
				
				//Elimina componenti
				algorithmComponents.remove(getIndexOf(ac)+2);
				algorithmComponents.remove(getIndexOf(ac)+1);
				algorithmComponents.remove(getIndexOf(ac));
			}else {
				DebuggerConsole.getInstance().printDefaultInfoLog(referenceTypeMessage, "Failed testing for if deletion");
				throw new Exceptions(Exceptions.COMPONENT_NOT_EMPTY);
			}
			//model.Components.ComponentAdd@340a82ef
		}else {
			
			DebuggerConsole.getInstance().printDefaultErrorLog(referenceTypeMessage, "Deleting " + algorithmComponents.get(getIndexOf(ac)));
			
			if(previousAC instanceof ComponentIf) {
				previousAC = (ComponentIf) previousAC;
//				previousAC.setNextComponent1(algorithmComponents.get(getIndexOf(ac)+2));
				if(!(nextAC instanceof ComponentElse)) {
					algorithmComponents.get(getIndexOf(ac)-1).setNextComponent1(algorithmComponents.get(getIndexOf(ac)+1));	
				}else {
					ComponentAdd add = recursiveSearchRelatedAdd(previousAC);
					previousAC.setNextComponent1(add);
				}
			}else if(previousAC instanceof ComponentElse) {
				previousAC = (ComponentElse) previousAC;
				previousAC.setNextComponent1(algorithmComponents.get(getIndexOf(ac)+1));
			}else if(previousAC instanceof ComponentWhile) {
				previousAC = (ComponentWhile) previousAC;
				previousAC.setNextComponent1(previousAC);
			}else if(previousAC instanceof ComponentAdd) {
				previousAC.setNextComponent1(ac.getNextComponent1());
			}else {
				algorithmComponents.get(getIndexOf(ac)-1).setNextComponent1(algorithmComponents.get(getIndexOf(ac)+1));				
			}
		
			
			algorithmComponents.remove(getIndexOf(ac));
			
		}
		showComponents();
		
	}

	/**<p>
	 * This method returns the related ComponentAdd of a provided {@link AlgorithmComponent} <code>previousAC</code>
	 * </p>
	 * <p>
	 * This method returns the related <code>ComponentAdd</code> of a provided <code>AlgorithmComponent</code> <code>previousAC</code> found by using a 
	 * counter that decrements itself if a <code>ComponentIf</code> or a <code>ComponentWhile</code> is found during the recursion, it increments if a 
	 * <code>ComponentAdd</code> is found.
	 * When the counter equals to 1 it means that the related <code>ComponentAdd</code> is found and the method returns it
	 * </p>
	 * @param previousAC The algorithmComponent that will be used to start the recursive search
	 * @return {@link ComponentAdd} The ComponentAdd related to the provided algorithm component <code>ac</code>
	 */
	private ComponentAdd recursiveSearchRelatedAdd(AlgorithmComponent previousAC) {
		int counter = 0;
		AlgorithmComponent aus = previousAC;
		aus = aus.getNextComponent2();
		while(counter < 1) {
			aus = algorithmComponents.get(getIndexOf(aus)+1);
			System.out.println(aus);
			if(aus instanceof ComponentIf || aus instanceof ComponentWhile) {
				counter--;
			}else if(aus instanceof ComponentAdd) {
				counter++;
			}
		}
		return (ComponentAdd)aus;			
	}
	
	public AlgorithmComponent recursiveSearchRelatedComponentFromAdd(ComponentAdd compAdd) {
	    int counter = 0;
	    AlgorithmComponent aus = compAdd;
	    while (counter >= 0) {
	        aus = algorithmComponents.get(getIndexOf(aus) - 1);
	        if (aus instanceof ComponentIf || aus instanceof ComponentWhile) {
	            counter--;
	        } else if (aus instanceof ComponentAdd) {
	            counter++;
	        }
	    }
	    return aus;
	}

	/**<p>
	 * This method loops through all the associated panels of the {@link AlgorithmComponent} and sets their executing status to false
	 * </p>
	 */
	public void resetExecutingStatusOfPanels() {
		// TODO Auto-generated method stub
		for(AlgorithmComponent a : algorithmComponents) {
			a.getAssociatedPanel().executing = false;
			a.getAssociatedPanel().repaint();
		}
	}
	
	/**<p>
	 * This method returns the boolean value of the {@link MemoryStorage#onGoing} attribute
	 * </p>
	 * @return boolean the value of the attribute
	 */
	public boolean isOnGoing() {
		return onGoing;
	}

	/**<p>
	 * This sets the boolean value of the {@link MemoryStorage#onGoing} attribute
	 * </p>
	 * @param onGoing the new boolean value
	 */ 
	public void setOnGoing(boolean onGoing) {
		this.onGoing = onGoing;
	}

	public void setComponents(ArrayList<AlgorithmComponent> newArrayComponents) {
		// TODO Auto-generated method stub
		this.algorithmComponents = newArrayComponents;
	}

	public boolean containsInput() {
		for (AlgorithmComponent algorithmComponent : algorithmComponents) {
			if(algorithmComponent instanceof ComponentInput) {
				return true;
			}
		}
		return false;
	}

	public void reloadComponents() {
		// TODO Auto-generated method stub
		algorithmComponents.clear();
		initializeComponents();
		
	}
	
	
	
	
	
}
