package model;

import java.util.concurrent.Semaphore;

public class Timer implements Runnable{
	
	private static final String referenceType = "TIMER";
	private Thread T;
	private Semaphore execute;
	private Semaphore wait;
	private int ms;
	private boolean stop;
	private boolean autoRun;
	public boolean nextButtonGotClicked;
	private boolean executeAcquired;
	
	public Timer(Semaphore exec, Semaphore wait) {
		stop = false;
		executeAcquired = false;
		this.execute = exec;
		this.wait = wait;
		T = new Thread(this, "TIMER");
		DebuggerConsole.getInstance().printDefaultSuccessLog(referenceType, "Created.");
	}
	
	public void set(int ms, boolean autoRun) {
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
	
	public void delay() {
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void stop() {
		// TODO Auto-generated method stub
		stop = true;
	}
	
	public void nextButtonClicked() {
		DebuggerConsole.getInstance().printDefaultInfoLog(referenceType, "Received next button click. Setting boolean to true");
		nextButtonGotClicked = true;
	}

}
