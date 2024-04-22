package model;

import java.util.concurrent.Semaphore;

import controller.ContentPaneController;
import model.Components.AlgorithmComponent;
import model.Components.ComponentEnd;
import model.Memory.MemoryStorage;
import view.flowChartComponents.FlowChartPanel;

public class AlgorithmExecuter implements Runnable{
	
	private static final String referenceType = "EXECUTER";
	private AlgorithmComponent algorithmComponent;
	private Semaphore exec;
	private Semaphore wait;
	private Thread T;
	private Timer timer;
	private String result;
	private ContentPaneController controller;

	public AlgorithmExecuter(Semaphore exec, Semaphore wait, Timer timer) {
		super();
		this.timer = timer;
		this.exec = exec;
		this.wait = wait;
		DebuggerConsole.getInstance().printDefaultSuccessLog(referenceType, "Created.");
	}

	public void start(AlgorithmComponent start) throws Exceptions {
		
		createThread();
		result=null;
		algorithmComponent = start;
		DebuggerConsole.getInstance().printDefaultInfoLog(referenceType, "Got ComponentStart from " + Thread.currentThread().getStackTrace()[2].getClassName());
		T.start();
		DebuggerConsole.getInstance().printDefaultSuccessLog(referenceType+"-THREAD", "Started thread: " + T.getName());
	}

	private void createThread() {
		// TODO Auto-generated method stub
		this.T = new Thread(this, "EXECUTER");
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		//Finche il componente non è nullo (arrivati alla fine)
		DebuggerConsole.getInstance().printDefaultInfoLog(referenceType+"-THREAD", "Thread initialized. Running until 'component != null'");
				while (algorithmComponent!=null && !T.isInterrupted()) {
					DebuggerConsole.getInstance().printDefaultInfoLog(referenceType+"-THREAD", "Waiting wait Semaphore");
					try {
						wait.acquire();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						DebuggerConsole.getInstance().printDefaultInfoLog(referenceType+"-THREAD", "Thread got interrupted. Ending the process...");
						break;
					}
					DebuggerConsole.getInstance().printDefaultInfoLog(referenceType+"-THREAD", "Semaphore wait acquired. ");
					//Eseguo il componente
					try {
						result=null;
						callControllerUpdateTable();
						
						FlowChartPanel fcp = algorithmComponent.getAssociatedPanel();
						
						fcp.toggleExecuting();
						fcp.repaint();
						
						result = (String) algorithmComponent.execute();
						
						
					} catch (Exceptions e) {
						// TODO Auto-generated catch block
//						*HANDLE EXCEPTION*
//						e.printStackTrace();
						controller.showErrorDialog(e.getMessage());
					} catch (Exception e) {
						System.err.println(e.getMessage());
					}
					
					//Controllo se c'è stato un output dal componente
					if(result!=null) {
						System.out.println(result);
						result = null;
					}
					
					//Se il prossimo componente è la fine del programma mostra la tabella della memoria in debug
					if(algorithmComponent.getNextComponent() instanceof ComponentEnd) MemoryStorage.getInstance().print();
					
					algorithmComponent=algorithmComponent.getNextComponent(); //Passo all'esecutore il componente successivo
					
					//Mostro in fase di debug il passaggio del componente
					if(algorithmComponent != null)DebuggerConsole.getInstance().printCustomMSGColorLog(referenceType, Color.RED_UNDERLINED, "PASSING EXECUTION TO COMPONENT: " + algorithmComponent.getClass().getSimpleName());
					exec.release();
					DebuggerConsole.getInstance().printDefaultInfoLog(referenceType+"-THREAD", "Semaphore execute acquired. ");
				}
				timer.stop();
				callControllerDestroyVariables();
				DebuggerConsole.getInstance().printDefaultSuccessLog(referenceType, "Execution completed.");
//				MemoryStorage.getInstance().showMemory();
		
	}
	
	private void callControllerDestroyVariables() {
		// TODO Auto-generated method stub
		this.controller.deleteVariablesFromMemoryStorage();
		
	}

	public void setController(ContentPaneController controller) {
		this.controller = controller;
	}
	
	public void stop() {
		this.T.interrupt();
		createThread();
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
	
	public void callControllerUpdateTable() {
		controller.updateTable();
	}

}
