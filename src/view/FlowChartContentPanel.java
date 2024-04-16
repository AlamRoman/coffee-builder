package view;

import javax.swing.JPanel;

import model.Components.ComponentEnd;
import model.Components.ComponentOutput;
import model.Components.ComponentStart;
import view.flowChartComponents.OvalPanel;
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
		ciao.setBounds(217, 143, 180, 80);
		ciao.setBounds(124, 54, 180, 80);

		OvalPanel ok = new OvalPanel(new ComponentStart(null), null);
		ok.setBounds(0, 144, 180, 80);

		OvalPanel ok2 = new OvalPanel(new ComponentEnd(), null);
		ok2.setBounds(0, 0, 180, 80);

		add(ciao);
		add(ok);
		add(ok2);

	}
}