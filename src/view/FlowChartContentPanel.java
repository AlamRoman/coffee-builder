package view;

import javax.swing.JPanel;

import model.Components.ComponentOutput;
import view.flowChartComponents.ParallelogramPanel;
import java.awt.Color;

public class FlowChartContentPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public FlowChartContentPanel() {
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		
		
		ComponentOutput output = new ComponentOutput(null, null, null);
	    output.set("ciao");
		
		ParallelogramPanel ciao = new ParallelogramPanel(output, null);
		ciao.setBounds(122, 74, 160, 80);
		
		add(ciao);
	
	}
}
