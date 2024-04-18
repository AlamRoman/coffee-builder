package view;

import javax.swing.JPanel;

import controller.FlowChartController;
import model.Components.ComponentAssign;
import model.Components.ComponentComment;
import model.Components.ComponentEnd;
import model.Components.ComponentIf;
import model.Components.ComponentInput;
import model.Components.ComponentOutput;
import model.Components.ComponentStart;
import model.Components.ComponentWhile;
import model.Memory.RelationalOperators;
import view.flowChartComponents.OvalPanel;
import view.flowChartComponents.ParallelogramPanel;
import view.flowChartComponents.RectanglePanel;
import view.flowChartComponents.RhombusPanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;

public class FlowChartContentPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	        
	        public Dimension getPreferredSize() {
	            int maxX = 0;
	            int maxY = 0;
	            Component[] components = this.getComponents();
	            for(int i = 0; i < components.length; i++){
	                Rectangle bounds = components[i].getBounds();
	                maxX = Math.max(maxX, (int)bounds.getMaxX());
	                maxY = Math.max(maxY, (int)bounds.getMaxY());
	            }   
	            return new Dimension(maxX,maxY);
	        }

	
	
	public FlowChartContentPanel() {
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		
		setSize(10000, 10000);
		
		
		ComponentOutput output = new ComponentOutput(null, null, null);
	    output.set("ciao");

		ParallelogramPanel ciao = new ParallelogramPanel(output, FlowChartController.getIstance());
		ciao.setBounds(200, 130, 180, 80);

		OvalPanel ok = new OvalPanel(new ComponentStart(null), FlowChartController.getIstance());
		ok.setBounds(20, 20, 180, 80);

		OvalPanel ok2 = new OvalPanel(new ComponentEnd(), FlowChartController.getIstance());
		ok2.setBounds(20, 15000, 180, 80);
		
		
		RectanglePanel russo = new RectanglePanel(new ComponentComment(null, null, null), FlowChartController.getIstance());
		russo.setBounds(200, 230, 180, 80);
		
		ComponentWhile tt = new ComponentWhile(null, null);
		tt.set("ciao", RelationalOperators.EQUAL_TO, "ciao");
		RhombusPanel alam = new RhombusPanel(tt, FlowChartController.getIstance());
		alam.setBounds(200, 330, 180, 80);
		alam.setBounds(100, 500, 180, 80);
		
		ComponentInput ci = new ComponentInput(null, null, null);
		ci.set("var");
		ParallelogramPanel inputPanel = new ParallelogramPanel(ci, FlowChartController.getIstance());
		inputPanel.setBounds(100, 700, 180, 80);

		
		
		add(ciao);
		add(ok);
		add(ok2);
		add(alam);
		add(russo);
		add(inputPanel);

	}
}