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

public class FlowChartController extends Controller{
	
	private FlowChartContentPanel panel;
	
	public FlowChartController(AlgorithmExecuter ALGORITHM_EXECUTER, Timer TIMER, FlowChartContentPanel panel) {
		super(ALGORITHM_EXECUTER, TIMER, MemoryStorage.getInstance());
		// TODO Auto-generated constructor stub
		this.panel = panel;
		this.panel.setControllerAttribute(this);
//		System.out.println(this.panel);
//		System.out.println(this);
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
		//MOSTRA IL CMD
//		System.out.println(e.getActionCommand());
		//MOSTRA DA DOVE PROVIENE IL CMD
		System.out.println(e.getSource());
		//MOSTRA IL PANNELLO CHE CONTIENE IL BOTTONE PREMUTO
		System.out.println(FCPanel);
		
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
