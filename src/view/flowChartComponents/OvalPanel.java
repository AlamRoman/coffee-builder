package view.flowChartComponents;

import java.awt.Graphics;
import java.awt.Graphics2D;

import controller.Controller;
import controller.FlowChartController;
import model.Components.Component;

public class OvalPanel extends FlowChartPanel {

	public OvalPanel(Component associatedComponent, FlowChartController controller) {
		super(associatedComponent, controller);
		// TODO Auto-generated constructor stub
	}

	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        // Disegna un ovale
        g2d.drawOval(0, 0, getWidth() - 1, getHeight() - 1);
    }
	
}
