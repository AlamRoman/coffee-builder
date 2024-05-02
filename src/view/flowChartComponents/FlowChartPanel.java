package view.flowChartComponents;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.ViewportLayout;
import javax.swing.plaf.FontUIResource;

import controller.Controller;
import controller.FlowChartController;
import model.Components.AlgorithmComponent;
import model.Components.ComponentAdd;
import model.Components.ComponentAssign;
import model.Components.ComponentComment;
import model.Components.ComponentDeclaration;
import model.Components.ComponentEnd;
import model.Components.ComponentIf;
import model.Components.ComponentInput;
import model.Components.ComponentOperation;
import model.Components.ComponentOutput;
import model.Components.ComponentStart;
import model.Components.ComponentWhile;

public class FlowChartPanel extends JPanel {

	private static final String ACTION_COMMAND_ADD_BUTTON = "ADD_COMPONENT";
	public AlgorithmComponent associatedComponent;
	public JButton[] buttons;
	private final FlowChartController controller;
	private JLabel label;
	public boolean executing;
	
	public FlowChartPanel(AlgorithmComponent associatedComponent, FlowChartController controller) {
		setLayout(null);
		setPreferredSize(new Dimension(180, 80));
		this.controller = controller;
		addMouseListener(controller);
		buttons = new JButton[4];
		this.associatedComponent = associatedComponent;
		executing = false;
		associatedComponent.setAssociatedPanel(this);
		if(associatedComponent instanceof AlgorithmComponent && !(associatedComponent instanceof ComponentEnd)) {
			createButtons(false, false, true, false);
		}
		addCenteredLabelWithComponentInfo();
	}

	private void createButtons(boolean top, boolean right, boolean bottom, boolean left) {
		// TODO Auto-generated method stub
//		if(top) {
//			buttons[0] = new JButton("ADD");
//			buttons[0].setBounds(70, 0, 40, 20);
//			buttons[0].setFocusable(false);
//		}
//		if(right) {
//			buttons[1] = new JButton("ADD");
//			buttons[1].setBounds(0, 30, 40, 20);
//			buttons[1].setFocusable(false);
//		}
		
		if(bottom) {
			buttons[2] = new JButton("ADD");
			if(associatedComponent instanceof ComponentAdd) {
				buttons[2].setBounds(70, 30, 40, 20);								
			}else {
				buttons[2].setBounds(70, 60, 40, 20);				
			}
			buttons[2].setFocusable(false);
		}
//		if(left) {
//			buttons[3] = new JButton("ADD");
//			buttons[3].setBounds(140, 30, 40, 20);
//			buttons[3].setFocusable(false);
//		}
		buttons[2].setBorder(null);
		buttons[2].setPreferredSize(new Dimension(40, 20));
		buttons[2].setFont(new FontUIResource("Rubik", Font.PLAIN, 8));
		buttons[2].setBackground(Color.WHITE);
		buttons[2].setForeground(Color.BLACK);
		buttons[2].setBorder(BorderFactory.createCompoundBorder(
				
				BorderFactory.createLineBorder(Color.BLACK, 2),
				//BorderFactory.createLineBorder(Color.BLACK),
				BorderFactory.createLineBorder(Color.WHITE, 4)
				
				));
		buttons[2].setFocusPainted(false);
		buttons[2].setActionCommand(ACTION_COMMAND_ADD_BUTTON);
		buttons[2].addActionListener(controller);
		add(buttons[2]);
		
	}
	
	public void updateInnerInfo() {
		label.setText(associatedComponent.print());
	}
	
	private void addCenteredLabelWithComponentInfo() {
		label = new JLabel(associatedComponent.print());
		label.setBounds(40, 30, 100, 20);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		add(label);
	}

	public void setButtonLocation(int i, int x, int y) {
		// TODO Auto-generated method stub
		buttons[i].setLocation(x,y);
	}
	
	public void updatePrint() {
		label.setText(associatedComponent.print());
	}

	public void toggleExecuting() {
		// TODO Auto-generated method stub
		executing = true;
	}
}
