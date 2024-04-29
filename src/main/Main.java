package main;

import java.util.concurrent.Semaphore;

import controller.ContentPaneController;
import controller.Controller;
import controller.FlowChartController;
import model.AlgorithmExecuter;
import model.Buffer;
import model.Timer;
import model.File.SoundPlayer;
import model.Memory.MemoryStorage;
import model.Memory.RelationalOperators;
import view.FlowChartContentPanel;
import view.Frame;

/** The Main Class of this program
 * */
public class Main {
	
	public static void main(String[] args) {

		final Frame MAIN_FRAME = new Frame();
		final Semaphore EXECUTE_S = new Semaphore(1);
		final Semaphore WAIT_S = new Semaphore(0);
		final Semaphore READ_S = new Semaphore(1);
		final Semaphore WRITE_S = new Semaphore(0);
		final Timer TIMER = new Timer(EXECUTE_S, WAIT_S);
		final Buffer BUFFER = new Buffer();
		final AlgorithmExecuter ALGORITHM_EXECUTER = new AlgorithmExecuter(EXECUTE_S, WAIT_S, READ_S, WRITE_S, TIMER, BUFFER);
		final ContentPaneController CONTROLLER = new ContentPaneController(ALGORITHM_EXECUTER, TIMER, BUFFER, EXECUTE_S, WAIT_S, READ_S, WRITE_S, MAIN_FRAME.getContentPanel());
		final FlowChartController CONTROLLER_FLOWCHART = new FlowChartController(ALGORITHM_EXECUTER, TIMER, BUFFER, MAIN_FRAME.getContentPanel().getFlowChartPanel(), MemoryStorage.getInstance());
		MAIN_FRAME.setVisible(true);

	}
	
}