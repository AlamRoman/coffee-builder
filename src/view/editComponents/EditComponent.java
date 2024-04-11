package view.editComponents;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class EditComponent extends JFrame{

	private JPanel contentPane;
	private String componentName;
	
	public EditComponent() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(200, 250);
		setTitle(componentName);
		
		contentPane = new JPanel();
		
		setContentPane(contentPane);
	}
}
