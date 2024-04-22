package view;

import javax.swing.JPanel;
import javax.swing.border.Border;

import controller.FlowChartController;
import model.Components.ComponentAssign;
import model.DebuggerConsole;
import model.Components.AlgorithmComponent;
import model.Components.ComponentAdd;
import model.Components.ComponentComment;
import model.Components.ComponentDeclaration;
import model.Components.ComponentElse;
import model.Components.ComponentEnd;
import model.Components.ComponentEndWhile;
import model.Components.ComponentIf;
import model.Components.ComponentInput;
import model.Components.ComponentOperation;
import model.Components.ComponentOutput;
import model.Components.ComponentStart;
import model.Components.ComponentWhile;
import model.Memory.MemoryStorage;
import model.Memory.OperationType;
import model.Memory.RelationalOperators;
import view.flowChartComponents.FlowChartPanel;
import view.flowChartComponents.OvalPanel;
import view.flowChartComponents.ParallelogramPanel;
import view.flowChartComponents.RectanglePanel;
import view.flowChartComponents.RhombusPanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.RenderingHints.Key;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;
import java.util.Map;

public class FlowChartContentPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private static final String referenceType = "FC-COMP-CONTAINER";
	private static final int OFFSET_X = 20;
	private static final int OFFSET_Y = 20;
	private static final int PANEL_WIDTH = 180;
	private static final int PANEL_HEIGHT = 80;
	private static final int X_INCREMENT = PANEL_WIDTH;
	private static final int Y_INCREMENT = PANEL_HEIGHT + OFFSET_Y;
	private FlowChartController FC_Controller;

	public FlowChartContentPanel() {
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		
		setSize(10000, 10000);

	}
	
	public void setControllerAttribute(FlowChartController controller) {
		this.FC_Controller = controller;
		addComps();
	}
	
	//METODO CHIAMATO D-O-P-O LA CONNESSIONE AL CONTROLLER
	private void addComps() {
		// TODO Auto-generated method stub
		ComponentOutput output = new ComponentOutput(null, null, null);
	    output.set("ciao");
	    
//	    System.out.println(FC_Controller);
//
//		ParallelogramPanel ciao = new ParallelogramPanel(output, FC_Controller);
//		ciao.setBounds(200, 130, 180, 80);
//
//		OvalPanel ok = new OvalPanel(new ComponentStart(null), FC_Controller);
//		ok.setBounds(20, 20, 180, 80);
//
//		OvalPanel ok2 = new OvalPanel(new ComponentEnd(), FC_Controller);
//		ok2.setBounds(20, 15000, 180, 80);
//		
//		
//		RectanglePanel russo = new RectanglePanel(new ComponentComment(null, null, null), FC_Controller);
//		russo.setBounds(200, 230, 180, 80);
//		
//		ComponentWhile tt = new ComponentWhile(null, null);
//		tt.set("ciao", RelationalOperators.EQUAL_TO, "ciao");
//		RhombusPanel alam = new RhombusPanel(tt, FC_Controller);
//		alam.setBounds(200, 330, 180, 80);
//		alam.setBounds(100, 500, 180, 80);
//		
//		ComponentInput ci = new ComponentInput(null, null, null);
//		ci.set("var");
//		ParallelogramPanel inputPanel = new ParallelogramPanel(ci, FC_Controller);
//		inputPanel.setBounds(100, 700, 180, 80);
//		
//		ComponentOperation co = new ComponentOperation(null, null, null);
//		co.set("$var1", "$var2", "$var3", OperationType.DIV);
//		RectanglePanel operationPanel = new RectanglePanel(co, null);
//		operationPanel.setBounds(100, 900, 180, 80);
//
//		
//		
//		add(ciao);
//		add(ok);
//		add(ok2);
//		add(alam);
//		add(russo);
//		add(inputPanel);
//		add(operationPanel);
	}

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

	public void updatePane(ArrayList<AlgorithmComponent> components, FlowChartPanel FCPanel) {
		// TODO Auto-generated method stub
		
		int posX=OFFSET_X, posY=OFFSET_Y;
		
		this.removeAll();
		
		for(AlgorithmComponent c : components) {
			
			//if(c.getNextComponent() != null) {
			//	c.getNextComponent1().equals(MemoryStorage.getInstance().getIndexOf(c)+1);				
			//}
			
			
			DebuggerConsole.getInstance().printDefaultInfoLog(referenceType, "Adding " + c + " to the panel...");
			
			if(c instanceof ComponentStart) {
				
				OvalPanel o_p = new OvalPanel(c, FC_Controller);
				o_p.setBounds(posX, posY, 180, 80);
				
				
				
				posX+=X_INCREMENT; //180
				posY+=Y_INCREMENT; //100
				add(o_p);
				
			}else if (c instanceof ComponentEnd) {
				
				OvalPanel o_p = new OvalPanel(c, FC_Controller);
				o_p.setBounds(20, posY, 180, 80);
				add(o_p);
				DebuggerConsole.getInstance().printDefaultSuccessLog(referenceType, "Added.");
				
			}else if(c instanceof ComponentAssign || c instanceof ComponentOperation || c instanceof ComponentComment || c instanceof ComponentDeclaration) {
				
				RectanglePanel r_p = new RectanglePanel(c, FC_Controller);
				r_p.setBounds(posX, posY, 180, 80);
				add(r_p);
				
				posY+=Y_INCREMENT; //100
				
				DebuggerConsole.getInstance().printDefaultSuccessLog(referenceType, "Added.");
			
			}else if(c instanceof ComponentWhile) {
				
				RhombusPanel rh_p = new RhombusPanel(c, FC_Controller);
				rh_p.setBounds(posX, posY, 180, 80);
				add(rh_p);
				
			/*	if( components.get(MemoryStorage.getInstance().getIndexOf(c)+1) instanceof ComponentEndWhile){
					
				}else {
					posX+=X_INCREMENT;
				}*/
				
				posX+=X_INCREMENT;
				
				posY+=Y_INCREMENT; //100
				
				DebuggerConsole.getInstance().printDefaultSuccessLog(referenceType, "Added.");
				
			}else if(c instanceof ComponentAdd) {
				
				posX-=X_INCREMENT;
				
				
				RectanglePanel o_p = new RectanglePanel(c, FC_Controller);
				o_p.setBounds(posX, posY, 180, 80);
				add(o_p);
				
				posY+=Y_INCREMENT; //100
				
				DebuggerConsole.getInstance().printDefaultSuccessLog(referenceType, "Added.");
				
				
			}else if(c instanceof ComponentElse) {
				
				posX-=X_INCREMENT;
				
				
				RhombusPanel o_p = new RhombusPanel(c, FC_Controller);
				o_p.setBounds(posX, posY, 180, 80);
				add(o_p);
				
				posX+=X_INCREMENT;
				
				posY+=Y_INCREMENT; //100
				
				DebuggerConsole.getInstance().printDefaultSuccessLog(referenceType, "Added.");
				
				
			}else if(c instanceof ComponentIf) {
				
				RhombusPanel rh_p = new RhombusPanel(c, FC_Controller);
				rh_p.setBounds(posX, posY, 180, 80);
				add(rh_p);
				
				posX+=X_INCREMENT;
				
				posY+=Y_INCREMENT; //100
				
				DebuggerConsole.getInstance().printDefaultSuccessLog(referenceType, "Added.");
				
			}else if(c instanceof ComponentInput || c instanceof ComponentOutput) {
				
				ParallelogramPanel par_p = new ParallelogramPanel(c, FC_Controller);
				par_p.setBounds(posX, posY, 180, 80);
				add(par_p);
				
				posY+=Y_INCREMENT; //100
				
				DebuggerConsole.getInstance().printDefaultSuccessLog(referenceType, "Added.");
			}
		}
		
		revalidate();
		repaint();
		
	}
}