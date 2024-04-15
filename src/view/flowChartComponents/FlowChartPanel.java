package view.flowChartComponents;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
		setPreferredSize(new Dimension(200, 100));
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
			buttons[0] = new JButton("NEXT");
		}
		if(right) {
			buttons[1] = new JButton("NEXT");
		}
		if(bottom) {
			buttons[2] = new JButton("NEXT");
		}
		if(left) {
			buttons[3] = new JButton("NEXT");
		}
		for(JButton btn : buttons) {
			if(btn != null) {
				btn.setSize(50, 20);
				btn.setActionCommand(ACTION_COMMAND_ADD_BUTTON);
				btn.addActionListener(controller);
				add(btn);
			}
		}
	}
	
	private void addCenteredLabelWithComponentInfo() {
		JLabel label = new JLabel(associatedComponent.print());
		label.setLocation((int)getWidth()/2, (int)getHeight()/2);
		add(label);
	}

	public void setButtonLocation(int i, int x, int y) {
		// TODO Auto-generated method stub
		buttons[i].setLocation(x,y);
	}
}
