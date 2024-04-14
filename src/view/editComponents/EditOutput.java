package view.editComponents;

import javax.swing.JPanel;

import model.Components.ComponentOutput;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class EditOutput extends EditComponent{
	
	private ComponentOutput componentOutput;
	private JPanel panel;
	
	private JTextArea textArea;

	public EditOutput(ComponentOutput componentOutput) {
		super("Output");
		this.setContentPane(panel);
		this.componentOutput = componentOutput;
		
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		textArea.setText(componentOutput.getRawOutputString());
		
		JButton btnSave = new JButton("Save");
		getContentPane().add(btnSave, BorderLayout.SOUTH);
	}

	public void setText(String s) {
		textArea.setText(s);
	}
	
	public String getText() {
		return textArea.getText();
	}
}
