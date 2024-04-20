package view.editComponents;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class EditComponent extends JDialog{

	private JPanel panel;

	private String componentName;
	
	public EditComponent(String componentName, JFrame parent) {
		super(parent, componentName, true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle(componentName);
	}

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
	
	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}
}
