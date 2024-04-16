package view.flowChartComponents;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import controller.Controller;
import controller.FlowChartController;
import model.Components.Component;

public class ParallelogramPanel extends FlowChartPanel {
	
	private static final int OFFSET_WIDTH = 10;
	private static final int BUTTON_TOP_OFFSET = 10;
	
	public ParallelogramPanel(Component associatedComponent, FlowChartController controller) {
		super(associatedComponent, controller);
		// TODO Auto-generated constructor stub
	}
	
	/*
	  
	         D______________C
		     /             /
		    /             /
		   /             /
		  /_____________/
		A               B
		
		Punto A: (x_A, y_A)
		Punto B: (x_B, y_B)
		Punto C: (x_C, y_C)
		Punto D: (x_D, y_D)
	 
     */
	
	@Override
	protected void paintComponent(Graphics g) {
		
		setBackground(new Color(255, 255, 255));
		
	    super.paintComponent(g);
	    Graphics2D g2d = (Graphics2D) g;
	    
	    int width = getWidth();
	    int height = getHeight();
	    
	    // Calcola le coordinate dei vertici
	    int x_D = 40;
	    int x_A = 0; // da 0.01 a 0.99
	    int x_B = width - 40;
	    int x_C = width;
	    int y_D = 0; //somma di questo...
	    int y_A = height-1;//...e questo sempre = 1.0 per garantire lo stesso offset da sotto e da sopra
	    int y_B = y_A;
	    int y_C = y_D;
	    
	    // Disegna il parallelogramma
	    int[] xPoints = {x_D, x_A, x_B, x_C};
	    int[] yPoints = {y_D, y_A, y_B, y_C};
	    g2d.drawPolygon(xPoints, yPoints, 4);
	}

	
	
}
