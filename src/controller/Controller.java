package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToggleButton;

import model.AlgorithmExecuter;
import model.Buffer;
import model.Timer;
import model.Memory.MemoryStorage;
import view.Panel;

/**<p>
* This class is the parent class of all the controllers used in this program such as {@link ContentPaneController} and {@link FlowChartController}
* </p>
* <p>
* It implements {@link ActionListener}, {@link ItemListener} and {@link MouseListener}
* </p>
*/
public abstract class Controller implements ActionListener, ItemListener, MouseListener {
	
	protected AlgorithmExecuter algorithmExecuter;
	protected MemoryStorage memory;
	protected Timer timer;
	protected Buffer buffer;

	/**<p>
	* The constructor method of the {@link Controller} class
	* </p>
	* <p>
	* Creates an instance of {@link Controller}
	* </p>
	* @param ALGORITHM_EXECUTER The {@link AlgorithmExecuter} instance that executes all the different components
	* @param TIMER The {@link Timer} instance that will coordinate the execution and handles the delay between all the exections
	* @param buffer The {@link Buffer} instance that handles all the outputs from the execution process
	* @param MEMORY The {@link MemoryStorage} that handles all the variables and components informations
	*/
	public Controller(AlgorithmExecuter ALGORITHM_EXECUTER, Timer TIMER, Buffer buffer, MemoryStorage MEMORY) {
		// TODO Auto-generated constructor stub
		this.algorithmExecuter = ALGORITHM_EXECUTER;
		this.timer = TIMER;
		this.memory = MEMORY;
		this.buffer = buffer;
	}
	
	/**<p>
	* This method returns the {@link AlgorithmExecuter} instance associated with this controller
	* </p>
	* @return {@link AlgorithmExecuter} the executer
	*/
	public AlgorithmExecuter getExecuter() {
		return algorithmExecuter;
	}
	
	/**<p>
	* This method returns the {@link MemoryStorage} instance associated with this controller
	* </p>
	* @return {@link MemoryStorage} the memory
	*/
	public MemoryStorage getMemory() {
		return memory;
	}
	
	@Override
	public abstract void actionPerformed(ActionEvent e);
	
	@Override
	public abstract void itemStateChanged(ItemEvent e);

	/**<p>
	* This method returns the {@link Timer} instance associated with this controller
	* </p>
	* @return {@link Timer} the timer
	*/
	public Timer getTimer() {
		// TODO Auto-generated method stub
		return this.timer;
	}

}
