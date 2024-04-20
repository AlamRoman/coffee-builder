package view;

import javax.swing.JPanel;

import controller.FlowChartController;
import model.Components.ComponentAssign;
import model.DebuggerConsole;
import model.Components.AlgorithmComponent;
import model.Components.ComponentComment;
import model.Components.ComponentDeclaration;
import model.Components.ComponentEnd;
import model.Components.ComponentIf;
import model.Components.ComponentInput;
import model.Components.ComponentOperation;
import model.Components.ComponentOutput;
import model.Components.ComponentStart;
import model.Components.ComponentWhile;
import model.Memory.OperationType;
import model.Memory.RelationalOperators;
import view.flowChartComponents.OvalPanel;
import view.flowChartComponents.ParallelogramPanel;
import view.flowChartComponents.RectanglePanel;
import view.flowChartComponents.RhombusPanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.ArrayList;

public class FlowChartContentPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private static final String referenceType = "FC-COMP-CONTAINER";
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

	public void updatePane(ArrayList<AlgorithmComponent> components) {
		// TODO Auto-generated method stub
		for(AlgorithmComponent c : components) {
			DebuggerConsole.getInstance().printDefaultInfoLog(referenceType, "Adding " + c + " to the panel...");
			if(c instanceof ComponentStart || c instanceof ComponentEnd) {
				OvalPanel o_p = new OvalPanel(c, FC_Controller);
				o_p.setBounds(20, 20, 180, 80);
				add(o_p);
				DebuggerConsole.getInstance().printDefaultSuccessLog(referenceType, "Added.");
			}else if(c instanceof ComponentAssign || c instanceof ComponentOperation || c instanceof ComponentComment || c instanceof ComponentDeclaration) {
				RectanglePanel r_p = new RectanglePanel(c, FC_Controller);
				r_p.setBounds(200, 230, 180, 80);
				add(r_p);
				DebuggerConsole.getInstance().printDefaultSuccessLog(referenceType, "Added.");
			}else if(c instanceof ComponentWhile || c instanceof ComponentIf) {
				RhombusPanel rh_p = new RhombusPanel(c, FC_Controller);
				rh_p.setBounds(100, 500, 180, 80);
				add(rh_p);
				DebuggerConsole.getInstance().printDefaultSuccessLog(referenceType, "Added.");
			}else if(c instanceof ComponentInput || c instanceof ComponentOutput) {
				ParallelogramPanel par_p = new ParallelogramPanel(c, FC_Controller);
				par_p.setBounds(200, 130, 180, 80);
				add(par_p);
				DebuggerConsole.getInstance().printDefaultSuccessLog(referenceType, "Added.");
			}
		}
		revalidate();
		repaint();
		
	}
}