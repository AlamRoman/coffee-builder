package model;

import java.util.concurrent.Semaphore;

import model.Components.Component;
import model.Components.ComponentEnd;
import model.Memory.MemoryStorage;

public class AlgorithmExecuter implements Runnable{
	
	private static final String referenceType = "EXECUTER";
	private Component component;
	private Semaphore exec;
	private Semaphore wait;
	private Thread T;
	private Timer timer;
	private String result;
	

	public AlgorithmExecuter(Semaphore exec, Semaphore wait, Timer timer) {
		super();
		this.timer = timer;
		this.exec = exec;
		this.wait = wait;
		this.T = new Thread(this, "EXECUTER");
		DebuggerConsole.getInstance().printDefaultSuccessLog(referenceType, "Created.");
	}

	public void start(Component start) throws Exceptions {
		
		result=null;
		component = start;
		T.start();
		DebuggerConsole.getInstance().printDefaultSuccessLog(referenceType+"-THREAD", "Started thread: " + T.getName());
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		//Finche il componente non è nullo (arrivati alla fine)
		DebuggerConsole.getInstance().printDefaultInfoLog(referenceType+"-THREAD", "Thread initialized. Running until 'component != null'");
				while (component!=null) {
					
					DebuggerConsole.getInstance().printDefaultInfoLog(referenceType+"-THREAD", "Waiting wait Semaphore");
					try {
						wait.acquire();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					DebuggerConsole.getInstance().printDefaultInfoLog(referenceType+"-THREAD", "Semaphore wait acquired. ");
					//Eseguo il componente
					try {
						result=null;
						result = (String) component.execute();
					} catch (Exception e) {
						// TODO Auto-generated catch block
//						*HANDLE EXCEPTION*
						e.printStackTrace();
					}
					
					//Controllo se c'è stato un output dal componente
					if(result!=null) {
						System.out.println(result);
						result = null;
					}
					
					//Se il prossimo componente è la fine del programma mostra la tabella della memoria in debug
					if(component.getNextComponent() instanceof ComponentEnd) MemoryStorage.getInstance().showMemory();
					
					component=component.getNextComponent(); //Passo all'esecutore il componente successivo
					
					//Mostro in fase di debug il passaggio del componente
					if(component != null)DebuggerConsole.getInstance().printCustomMSGColorLog(referenceType, Color.RED_UNDERLINED, "PASSING EXECUTION TO COMPONENT: " + component.getClass().getSimpleName());
					exec.release();
					DebuggerConsole.getInstance().printDefaultInfoLog(referenceType+"-THREAD", "Semaphore execute acquired. ");
				}
				timer.stop();
				DebuggerConsole.getInstance().printDefaultSuccessLog(referenceType, "Execution completed.");
//				MemoryStorage.getInstance().showMemory();
		
	}

}
