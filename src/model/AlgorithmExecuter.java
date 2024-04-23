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
	private Semaphore read;
	private Semaphore write;
	private Thread T;
	private Timer timer;
	private Buffer buffer;
	private String result;
	private ContentPaneController controller;

	public AlgorithmExecuter(Semaphore exec, Semaphore wait, Semaphore read, Semaphore write, Timer timer, Buffer buffer) {
		super();
		this.timer = timer;
		this.buffer = buffer;
		this.exec = exec;
		this.wait = wait;
		this.read = read;
		this.write = write;
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
						MemoryStorage.getInstance().print();
						
					} catch (Exceptions e) {
						// TODO Auto-generated catch block
//						*HANDLE EXCEPTION*
//						e.printStackTrace();
						controller.showErrorDialog(e.getMessage());
					} catch (Exception e) {
						System.err.println(e.getMessage());
					}
					
					MemoryStorage.getInstance().showMemory();
					
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
					
					algorithmComponent=algorithmComponent.getNextComponent1(); //Passo all'esecutore il componente successivo
					
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
