package view.flowChartComponents;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;

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
        
        if (super.executing) {
            g2d.setColor(Color.GREEN); // Se la booleana è vera, il bordo sarà verde
        } else {
            g2d.setColor(Color.BLACK); // Se la booleana è falsa, il bordo sarà nero
        }
        
        // Disegna un ovale
        
        g2d.setStroke(new BasicStroke(2));
        g2d.drawOval(2, 2, getWidth() - 3, getHeight() - 3);
    }
	
}
