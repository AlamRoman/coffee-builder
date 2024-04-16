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
	    int x_D = 0 + OFFSET_WIDTH;
	    int x_A = (int) (width * 0.1) + OFFSET_WIDTH; // da 0.01 a 0.99
	    int x_B = width - OFFSET_WIDTH;
	    int x_C = x_B - x_A - OFFSET_WIDTH;
	    int y_D = (int) (height * 0.01); //somma di questo...
	    int y_A = (int) (height * 0.99); //...e questo sempre = 1.0 per garantire lo stesso offset da sotto e da sopra
	    int y_B = y_A;
	    int y_C = y_D;
	    
	    // Disegna il parallelogramma
	    int[] xPoints = {x_D, x_A, x_B, x_C};
	    int[] yPoints = {y_D, y_A, y_B, y_C};
	    g2d.drawPolygon(xPoints, yPoints, 4);
	    updateButtons(x_D, y_D, x_A, y_A, x_B, y_B, x_C, y_C);
	}

	private void updateButtons(int x_D, int y_D, int x_A, int y_A, int x_B, int y_B, int x_C, int y_C) {
		// TODO Auto-generated method stub
		super.setButtonLocation(0, ((int)super.getWidth()/2-((int)buttons[0].getWidth()/2)), y_C + BUTTON_TOP_OFFSET);
		super.setButtonLocation(2, ((int)super.getWidth()/2-((int)buttons[2].getWidth()/2)), y_A - BUTTON_TOP_OFFSET - buttons[2].getHeight());

	}
	
}
