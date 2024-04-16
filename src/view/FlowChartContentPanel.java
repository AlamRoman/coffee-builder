package view;

import javax.swing.JPanel;

import model.Components.ComponentEnd;
import model.Components.ComponentInput;
import model.Components.ComponentOperation;
import model.Components.ComponentOutput;
<<<<<<< Updated upstream
import model.Components.ComponentStart;
import view.flowChartComponents.OvalPanel;
=======
import model.Memory.OperationType;
>>>>>>> Stashed changes
import view.flowChartComponents.ParallelogramPanel;
import view.flowChartComponents.RectanglePanel;

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
		ciao.setBounds(124, 54, 180, 80);
		
		OvalPanel ok = new OvalPanel(new ComponentStart(null), null);
		ok.setBounds(0, 144, 180, 80);
		
		OvalPanel ok2 = new OvalPanel(new ComponentEnd(), null);
		ok2.setBounds(0, 0, 180, 80);
		
		ComponentOperation operation = new ComponentOperation(null, null, null);
		operation.set("$var1", "$var2", "$var3", OperationType.ADD);
		
		RectanglePanel opPanel = new RectanglePanel(operation, null);
		opPanel.setBounds(217, 250, 180, 80);
		
		add(ciao);
<<<<<<< Updated upstream
		add(ok);
		add(ok2);
=======
		add(opPanel);
>>>>>>> Stashed changes
	
	}
}
