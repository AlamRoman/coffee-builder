package model;

import java.util.concurrent.Semaphore;

public class Timer implements Runnable{
	
	private static final String referenceType = "TIMER";
	private Thread T;
	private Semaphore execute;
	private Semaphore wait;
	private int ms;
	private boolean stop;
	
	public Timer(Semaphore exec, Semaphore wait) {
		stop = false;
		this.execute = exec;
		this.wait = wait;
		T = new Thread(this, "TIMER");
		DebuggerConsole.getInstance().printDefaultSuccessLog(referenceType, "Created.");
	}
	
	public void set(int ms) {
		this.ms = ms;
		DebuggerConsole.getInstance().printDefaultSuccessLog(referenceType, "Setted timer to " + ms + "ms");
		T.start();
		DebuggerConsole.getInstance().printDefaultSuccessLog(referenceType+"-THREAD", "Started thread: " + T.getName());
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		DebuggerConsole.getInstance().printDefaultInfoLog(referenceType+"-THREAD", "Thread initialized. Running until stop() is called");
		while(!stop) {
			
			DebuggerConsole.getInstance().printDefaultInfoLog(referenceType+"-THREAD", "Waiting execute Semaphore");
			try {
				execute.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			DebuggerConsole.getInstance().printDefaultInfoLog(referenceType+"-THREAD", "Semaphore execute acquired. ");
			DebuggerConsole.getInstance().printDefaultInfoLog(referenceType, "Starting timer (" + ms + "ms) ... ");
			try {
				Thread.sleep(ms);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DebuggerConsole.getInstance().printDefaultInfoLog(referenceType, "Done.");
			wait.release();
			DebuggerConsole.getInstance().printDefaultInfoLog(referenceType+"-THREAD", "Semaphore wait released. ");
		}
		DebuggerConsole.getInstance().printDefaultSuccessLog(referenceType, "Timer stopped.");
	}

	public void stop() {
		// TODO Auto-generated method stub
		stop = true;
	}

}
