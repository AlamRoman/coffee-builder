package controller;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

import javax.swing.JButton;
import javax.swing.JLabel;

import model.AlgorithmExecuter;
import model.Timer;
import model.Memory.MemoryStorage;
import view.FlowChartContentPanel;
import view.flowChartComponents.FlowChartPanel;
import view.flowChartComponents.OvalPanel;
import view.flowChartComponents.ParallelogramPanel;
import view.flowChartComponents.RectanglePanel;
import view.flowChartComponents.RhombusPanel;

public class FlowChartController extends Controller implements ActionListener {
	
	public FlowChartController(AlgorithmExecuter ALGORITHM_EXECUTER, Timer TIMER, MemoryStorage MEMORY) {
		super(ALGORITHM_EXECUTER, TIMER, MEMORY);
		// TODO Auto-generated constructor stub
	}

	private static FlowChartController instance;
	private FlowChartPanel FCPanel;

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object source = e.getSource();
		if(source instanceof JButton) {
			JButton button = (JButton) source;
			FCPanel = (FlowChartPanel) button.getParent();
		}
//		System.out.println(e.getSource());
//		System.out.println(FCPanel);
		
		switch (e.getActionCommand()) {
		case "ADD": {
			
			
			
			
		break;
			}
		}
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}

}
