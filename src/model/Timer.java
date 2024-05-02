package model;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/**This class handles all the delays between each component during the execution of an alogrithm
 * */
public class Timer implements Runnable{
	
	private static final String referenceType = "TIMER";
	private Thread T;
	private Semaphore execute;
	private Semaphore wait;
	private int ms;
	public boolean stop;
	private boolean autoRun;
	public boolean nextButtonGotClicked;
	private boolean executeAcquired;
	
	/**<p>
	 * The constructor method of the {@link Timer} class
	 * </p>
	 * <p>
	 * It creates a new instance of the timer class with the provided parameters
	 * </p>
	 * @param exec The {@link Semaphore} that guarantees the correct execution of the algorithm
	 * @param wait The {@link Semaphore} that guarantees the synchronization with the Timer and handles the delays between the single components
	 * */
	public Timer(Semaphore exec, Semaphore wait) {
		stop = false;
		executeAcquired = false;
		this.execute = exec;
		this.wait = wait;
		DebuggerConsole.getInstance().printDefaultSuccessLog(referenceType, "Created.");
	}
	
	/**<p>
	* This method sets the required parameters for the {@link Timer} instance and starts the thread
	* </p>
	* @param ms The duration of the delay (in milliseconds)
	* @param autoRun The flag that determinate if the program is being executed in auto running mode
	*/
	public void set(int ms, boolean autoRun) {
		T = new Thread(this, "TIMER");
		this.ms = ms;
		this.autoRun = autoRun;
		DebuggerConsole.getInstance().printDefaultSuccessLog(referenceType, "Setted timer to " + ms + "ms");
		T.start();
		DebuggerConsole.getInstance().printDefaultSuccessLog(referenceType+"-THREAD", "Started thread: " + T.getName());
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		DebuggerConsole.getInstance().printDefaultInfoLog(referenceType+"-THREAD", "Thread initialized. Running until stop() is called");
		while(!stop) {
			
			if(!executeAcquired) {
				DebuggerConsole.getInstance().printDefaultInfoLog(referenceType+"-THREAD", "Waiting execute Semaphore");
				try {
					execute.acquire();
					executeAcquired = true;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
			if(autoRun) {
				DebuggerConsole.getInstance().printDefaultInfoLog(referenceType+"-THREAD", "Semaphore execute acquired. ");
				DebuggerConsole.getInstance().printDefaultInfoLog(referenceType, "Waiting for the timer to finish.");
				DebuggerConsole.getInstance().printDefaultInfoLog(referenceType, "Starting timer (" + ms + "ms) ... ");
				try {
					Thread.sleep(ms);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				DebuggerConsole.getInstance().printDefaultInfoLog(referenceType, "Done.");
				wait.release();
				executeAcquired = false;
				DebuggerConsole.getInstance().printDefaultInfoLog(referenceType+"-THREAD", "Semaphore wait released. ");
			}else {
				delay();
				if(nextButtonGotClicked) {
					wait.release();
					executeAcquired = false;
					DebuggerConsole.getInstance().printDefaultInfoLog(referenceType, "Next button CLICKED. Semaphore wait released.");
					nextButtonGotClicked = false;
				}
			}
		}
		DebuggerConsole.getInstance().printDefaultSuccessLog(referenceType, "Timer stopped.");
	}
	
	/**<p>
	* This method creates a 1ms delay
	* </p>
	*/
	public void delay() {
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**<p>
	* This method stops the timer by setting the <code>stop</code> flag to <code>false</code>
	* </p>
	*/
	public void stop() {
		// TODO Auto-generated method stub
		stop = true;
	}
	
	/**<p>
	* This method is called when the next button gets clicked, that is enabled by the program if the algorithm is being executed without
	* the auto run feature
	* </p>
	* <p>
	* When the algorithm is being executed without the auto run feature, the delays are disabled and the next component will be
	* executed only after the click of the next button
	* </p>
	*/
	public void nextButtonClicked() {
		DebuggerConsole.getInstance().printDefaultInfoLog(referenceType, "Received next button click. Setting boolean to true");
		nextButtonGotClicked = true;
	}

}
