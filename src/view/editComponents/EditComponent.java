package view.editComponents;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.File.ImageLoader;
import model.Memory.MemoryStorage;

/**
 * <p>This class is the parent class of all the configuration dialogs of the components</p>
 * */
public class EditComponent extends JDialog{

	private JPanel panel;

	private String componentName;
	
	/**<p>
	* The constructor of the class {@link EditComponent}
	* </p>
	* @param componentName The name of the component
	* @param parent The parent frame
	*/
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

	/**
	 * <p>
	 * This method returns the component name
	 * </p>
	 * @return {@link String} The component name
	 * */
	public String getComponentName() {
		return componentName;
	}

	/**
	 * <p>
	 * This method sets the component name
	 * </p>
	 * @param componentName The new component name
	 * */
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
	
	/**
	 * <p>
	 * This method returns the associated panel
	 * </p>
	 * @return {@link JPanel} The panel
	 * */
	public JPanel getPanel() {
		return panel;
	}

	/**
	 * <p>
	 * This method sets the associated panel
	 * </p>
	 * @param panel The new panel
	 * */
	public void setPanel(JPanel panel) {
		this.panel = panel;
	}
}
