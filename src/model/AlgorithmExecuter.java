package model;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

import controller.ContentPaneController;
import controller.Controller;
import model.Components.AlgorithmComponent;
import model.Components.ComponentEnd;
import model.Components.ComponentIf;
import model.Components.ComponentWhile;
import model.Memory.MemoryStorage;
import view.flowChartComponents.FlowChartPanel;

/**The class that handles all the component executions, outputs and exceptions that has been thrown during the execution of the single components
 * */
public class AlgorithmExecuter implements Runnable{
	
	private static final String referenceType = "EXECUTER";
	private AlgorithmComponent algorithmComponent;
	private Semaphore exec;
	private Semaphore wait;
	private Semaphore read;
	private Semaphore write;
	private Thread T;
	private Timer timer;
	private Buffer buffer;
	private String result;
	private ContentPaneController controller;
	private boolean running;

	
	/**<p>
	 * The constructor method of the {@link AlgorithmExecuter} class
	 * </p>
	 * <p>
	 * It creates a new instance of the algorithm executer class with the provided parameters
	 * </p>
	 * @param exec The {@link Semaphore} that guarantees the correct execution of the algorithm
	 * @param wait The {@link Semaphore} that guarantees the synchronization with the Timer and handles the delays between the single components
	 * @param read The {@link Semaphore} that handles the mutual exclusion for reading buffer
	 * @param write The {@link Semaphore} that handles the mutual exclusion for writing in the buffer
	 * @param timer The {@link Timer} that sets the delays between each components
	 * @param buffer The {@link Buffer} containing all the output text 
	 * */
	public AlgorithmExecuter(Semaphore exec, Semaphore wait, Semaphore read, Semaphore write, Timer timer, Buffer buffer) {
		super();
		this.timer = timer;
		this.buffer = buffer;
		this.exec = exec;
		this.wait = wait;
		this.read = read;
		this.write = write;
		this.running = false;
		DebuggerConsole.getInstance().printDefaultSuccessLog(referenceType, "Created.");
	}

	/**<p>
	* This method its called to start the execution of the algorithm 
	* </p>
	* <p>
	* Starts the Thread of the executer after setting the Start component, provided as a parameter and executes its followings
	* </p>
	* @param start The {@link AlgorithmComponent} that represents the start of the algorithm
	* @throws Exceptions n/a
	*/
	public void start(AlgorithmComponent start) throws Exceptions {
		
		createThread();
		result=null;
		algorithmComponent = start;
		running = true;
		DebuggerConsole.getInstance().printDefaultInfoLog(referenceType, "Got ComponentStart from " + Thread.currentThread().getStackTrace()[2].getClassName());
		T.start();
		DebuggerConsole.getInstance().printDefaultSuccessLog(referenceType+"-THREAD", "Started thread: " + T.getName());
	}

	/**<p>
	* This method resets the executer Thread after it has been interrupted
	* </p>
	*/
	private void createThread() {
		// TODO Auto-generated method stub
		this.T = new Thread(this, "EXECUTER");
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		//Finche il componente non è nullo (arrivati alla fine)
		DebuggerConsole.getInstance().printDefaultInfoLog(referenceType+"-THREAD", "Thread initialized. Running until 'component != null'");
			while (algorithmComponent!=null) {
					if(checkThread()) break;
					DebuggerConsole.getInstance().printDefaultInfoLog(referenceType+"-THREAD", "Waiting wait Semaphore");
					try {
						wait.acquire();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						DebuggerConsole.getInstance().printDefaultInfoLog(referenceType+"-THREAD", "Thread got interrupted. Ending the process...");
						break;
					}
					if(checkThread()) break;
					DebuggerConsole.getInstance().printDefaultInfoLog(referenceType+"-THREAD", "Semaphore wait acquired. ");
					//Eseguo il componente
					try {
						result=null;
						callControllerUpdateTable();
						
						FlowChartPanel fcp = algorithmComponent.getAssociatedPanel();
						
						fcp.toggleExecuting();
						fcp.repaint();
						if(checkThread()) break;
						result = (String) algorithmComponent.execute();
						if(checkThread()) break;
						MemoryStorage.getInstance().print();
						
					} catch (Exceptions e) {
						// TODO Auto-generated catch block
//						*HANDLE EXCEPTION*
//						e.printStackTrace();
						controller.showErrorDialog(e.getMessage());
						break;
					} catch (Exception e) {
						System.err.println(e.getMessage());
						break;
					}
					
					MemoryStorage.getInstance().showMemory();
					if(checkThread()) break;
					try {
						DebuggerConsole.getInstance().printDefaultInfoLog(referenceType+"-THREAD_BUFFER", "Waiting for read semaphore...");
						read.acquire();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					DebuggerConsole.getInstance().printDefaultInfoLog(referenceType+"-THREAD_BUFFER", "Got read semaphore.");
					//Controllo se c'è stato un output dal componente
					if(result!=null) {
						DebuggerConsole.getInstance().printDefaultInfoLog(referenceType+"-THREAD", "Writing '" + result + "' in buffer.");
						buffer.write(result);
						System.out.println(result);
						result = null;
					}
					
					DebuggerConsole.getInstance().printDefaultInfoLog(referenceType+"-THREAD_BUFFER", "Write semaphore released");
					write.release();
					
					//Se il prossimo componente è la fine del programma mostra la tabella della memoria in debug
					if(algorithmComponent.getNextComponent1() instanceof ComponentEnd) MemoryStorage.getInstance().print();
					
					if(algorithmComponent instanceof ComponentWhile || algorithmComponent instanceof ComponentIf) {
						algorithmComponent=algorithmComponent.getNextComponent(); //Passo all'esecutore il componente successivo
					}else {
						algorithmComponent=algorithmComponent.getNextComponent1(); //Passo all'esecutore il componente successivo						
					}
					
					//Mostro in fase di debug il passaggio del componente
					if(algorithmComponent != null)DebuggerConsole.getInstance().printCustomMSGColorLog(referenceType, Color.RED_UNDERLINED, "PASSING EXECUTION TO COMPONENT: " + algorithmComponent.getClass().getSimpleName());
					if(checkThread()) break;
					exec.release();
					DebuggerConsole.getInstance().printDefaultInfoLog(referenceType+"-THREAD", "Semaphore execute acquired. ");
				}
				timer.stop();
				callControllerDestroyVariables();
				DebuggerConsole.getInstance().printDefaultSuccessLog(referenceType, "Execution completed.");
//				MemoryStorage.getInstance().showMemory();
		
	}
	
	/**<p>
	* This method checks the status of the Thread
	* </p>
	* <p>
	* This method checks if the thread has been interrupted via the {@link Thread#interrupt()} method
	* </p>
	* @return true if the thread has been interrupted
	* @return false if the thread has not been interrupted
	*/
	private boolean checkThread() {
		// TODO Auto-generated method stub
		if(T.isInterrupted()) {
			DebuggerConsole.getInstance().printDefaultInfoLog(referenceType+"-THREAD", "Thread got interrupted. Ending the process...");
			return true;
		}
		return false;
	}

	/**<p>
	* This method calls the method {@link ContentPaneController#deleteVariablesFromMemoryStorage()} from the Content pane controller
	* </p>
	* @see ContentPaneController
	* @see Controller
	*/
	private void callControllerDestroyVariables() {
		// TODO Auto-generated method stub
		this.controller.deleteVariablesFromMemoryStorage();
		
	}

	/**<p>
	* This method sets a {@link ContentPaneController} as the associated controller of the {@link AlgorithmExecuter}
	* </p>
	* @param controller the controller that has to be associated
	*/
	public void setController(ContentPaneController controller) {
		this.controller = controller;
	}
	
	/**<p>
	* This method interrupts the executer Thread with the {@link Thread#interrupt()} method
	* </p>
	*/
	public void stop() {
		this.T.interrupt();
		running = false;
//		createThread();
	}
	
//	public void checkSemaphoreIntialization() {
//		// TODO Auto-generated method stub
//		if(!exec.tryAcquire()) {
//			exec.release();
//		}
//		if(wait.tryAcquire()) {
//			wait.release();
//		}
//	}
	
	/**<p>
	* This method calls the method {@link ContentPaneController#updateTable()} from the Content pane controller
	* </p>
	* @see ContentPaneController
	* @see Controller
	*/
	public void callControllerUpdateTable() {
		controller.updateTable();
	}

	/**<p>
	* This method returns a flag that represents the current status of the execution
	* </p>
	* <ul>
	* <li><code>True</code> if executing</li>
	* <li><code>False</code> if not executing</li>
	* </ul>
	* @return {@link Boolean}
	*/
	public Boolean isRunning() {
		return this.running;
	}
}
