package main;

import java.util.concurrent.Semaphore;

import controller.ContentPaneController;
import controller.Controller;
import model.AlgorithmExecuter;
import model.Timer;
import model.Memory.MemoryStorage;
import model.Memory.RelationalOperators;
import view.Frame;

public class Main {
	
	public static void main(String[] args) {

		final Frame MAIN_FRAME = new Frame();
		final Semaphore EXECUTE_S = new Semaphore(1);
		final Semaphore WAIT_S = new Semaphore(0);
		final Timer TIMER = new Timer(EXECUTE_S, WAIT_S);
		final AlgorithmExecuter ALGORITHM_EXECUTER = new AlgorithmExecuter(EXECUTE_S, WAIT_S, TIMER);
		final MemoryStorage MEMORY = new MemoryStorage();
		final ContentPaneController CONTROLLER = new ContentPaneController(ALGORITHM_EXECUTER, TIMER, MEMORY, MAIN_FRAME.getContentPanel());
		MAIN_FRAME.setVisible(true);
	}
	
}