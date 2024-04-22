package view.flowChartComponents;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import controller.Controller;
import controller.FlowChartController;
import model.Components.AlgorithmComponent;

public class RhombusPanel extends FlowChartPanel {

	public RhombusPanel(AlgorithmComponent associatedComponent, FlowChartController controller) {
		super(associatedComponent, controller);
		// TODO Auto-generated constructor stub
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        setBackground(new Color(255, 255, 255));
        
        if (super.executing) {
            g2d.setColor(Color.GREEN); // Se la booleana è vera, il bordo sarà verde
        } else {
            g2d.setColor(Color.BLACK); // Se la booleana è falsa, il bordo sarà nero
        }
        
        // Disegna un rombo
        int x[] = {getWidth() / 2, 0, getWidth() / 2, getWidth()};
        int y[] = {0, getHeight() / 2, getHeight(), getHeight() / 2};
        g2d.drawPolygon(x, y, 4);
    }

}
