package view.flowChartComponents;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import controller.Controller;
import controller.FlowChartController;
import model.Components.AlgorithmComponent;
import model.Components.ComponentAdd;

public class RectanglePanel extends FlowChartPanel {
	
	public RectanglePanel(AlgorithmComponent associatedComponent, FlowChartController controller) {
		super(associatedComponent, controller);
		// TODO Auto-generated constructor stub
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.setStroke(new BasicStroke(2));
        
        if (super.executing) {
            g2d.setColor(Color.GREEN); // Se la booleana è vera, il bordo sarà verde
        } else {
            g2d.setColor(Color.BLACK); // Se la booleana è falsa, il bordo sarà nero
        }
        
        // Disegna un rettangolo
        setBackground(new Color(255, 255, 255));
        if(associatedComponent instanceof ComponentAdd) {      	        	
        	g2d.drawRect(60, 20, getWidth() - 120, getHeight() - 40);        	        	
        }else {
        	g2d.drawRect(2, 2, getWidth() - 3, getHeight() - 3);        	
        }
    }

}
