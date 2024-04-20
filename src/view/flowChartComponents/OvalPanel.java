package view.flowChartComponents;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import controller.Controller;
import controller.FlowChartController;
import model.Components.AlgorithmComponent;

public class OvalPanel extends FlowChartPanel {

	public OvalPanel(AlgorithmComponent associatedComponent, FlowChartController controller) {
		super(associatedComponent, controller);
		// TODO Auto-generated constructor stub
	}

	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(new Color(255, 255, 255));
        Graphics2D g2d = (Graphics2D) g;
        
        // Disegna un ovale
        
        g2d.drawOval(1, 1, getWidth() - 1, getHeight() - 1);
    }
	
}
