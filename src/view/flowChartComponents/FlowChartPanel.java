package view.flowChartComponents;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ViewportLayout;
import javax.swing.plaf.FontUIResource;

import controller.Controller;
import controller.FlowChartController;
import model.ID;
import model.Components.Component;
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
	public Component associatedComponent;
	private final ID id = new ID();
	public JButton[] buttons;
	private final FlowChartController controller;
	
	public FlowChartPanel(Component associatedComponent, FlowChartController controller) {
		setLayout(null);
		setPreferredSize(new Dimension(180, 80));
		this.controller = controller;
		buttons = new JButton[4];
		this.associatedComponent = associatedComponent;
		if(associatedComponent instanceof ComponentWhile) {
			createButtons(false, true, true, false);
		}else if(associatedComponent instanceof ComponentIf) {
			createButtons(false, true, false, true);
		}else if(associatedComponent instanceof ComponentStart) {
			createButtons(false, false, true, false);
		}else if(associatedComponent instanceof ComponentEnd) {
			createButtons(true, false, false, false);
		}else if(associatedComponent instanceof ComponentAssign
				|| associatedComponent instanceof ComponentDeclaration
				|| associatedComponent instanceof ComponentOperation
				|| associatedComponent instanceof ComponentOutput
				|| associatedComponent instanceof ComponentInput
				|| associatedComponent instanceof ComponentComment) {
			createButtons(true, false, true, false);
		}
		addCenteredLabelWithComponentInfo();
	}

	private void createButtons(boolean top, boolean right, boolean bottom, boolean left) {
		// TODO Auto-generated method stub
		if(top) {
			buttons[0] = new JButton("ADD");
			buttons[0].setBounds(70, 0, 40, 20);
		}
		if(right) {
			buttons[1] = new JButton("ADD");
			buttons[1].setBounds(0, 30, 40, 20);
		}
		if(bottom) {
			buttons[2] = new JButton("ADD");
			buttons[2].setBounds(70, 60, 40, 20);
		}
		if(left) {
			buttons[3] = new JButton("ADD");
			buttons[3].setBounds(140, 30, 40, 20);
		}
		for(JButton btn : buttons) {
			if(btn != null) {
				btn.setBorder(null);
				btn.setPreferredSize(new Dimension(40, 20));
				btn.setFont(new FontUIResource("Rubik", Font.PLAIN, 8));
				btn.setActionCommand(ACTION_COMMAND_ADD_BUTTON);
				btn.addActionListener(controller);
				add(btn);
			}
		}
	}
	
	private void addCenteredLabelWithComponentInfo() {
		JLabel label = new JLabel(associatedComponent.print());
		label.setBounds(40, 30, 100, 20);
		add(label);
	}

	public void setButtonLocation(int i, int x, int y) {
		// TODO Auto-generated method stub
		buttons[i].setLocation(x,y);
	}
}
