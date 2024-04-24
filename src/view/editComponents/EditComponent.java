package view.editComponents;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.File.ImageLoader;

public class EditComponent extends JDialog{

	private JPanel panel;

	private String componentName;
	
	public EditComponent(String componentName, JFrame parent) {
		super(parent, componentName, true);
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle(componentName);
		
		ImageLoader imageLoader = new ImageLoader();
		
		ImageIcon icon;
		
		icon = imageLoader.getImageFrom("resources/edit.png");
		
		setIconImage(icon.getImage());
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
