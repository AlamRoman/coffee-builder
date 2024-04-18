package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FlowChartController implements ActionListener {
	
	private static FlowChartController istance;
	
	private FlowChartController() {};
	
	public static FlowChartController getIstance() {
		
		istance = (istance==null)?new FlowChartController():istance;
		return istance;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//System.out.println(e.getSource().toString());
		
		switch (e.getActionCommand()) {
		case "ADD": {
			
			
			
			
		break;
			}
		}
		
	}

}
