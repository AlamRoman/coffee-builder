package model;

import java.util.concurrent.Semaphore;

public class Timer implements Runnable{
	
	private Thread T;
	private Semaphore execute;
	private Semaphore wait;
	private int ms;
	
	public Timer(Semaphore exec, Semaphore wait) {
		this.execute = exec;
		this.wait = wait;
		T = new Thread("TIMER");
	}
	
	public void set(int ms) {
		this.ms = ms;
		T.run();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			try {
				execute.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				Thread.sleep(ms);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			wait.release();
		}
		
	}

}
