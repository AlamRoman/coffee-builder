package view.editComponents;

import javax.swing.JPanel;

import model.Components.ComponentOutput;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFrame;

public class EditOutput extends EditComponent{
	
	private String oldOutputText;
	private String newOutputText;
	private JPanel panel;
	
	private JTextArea textArea;

	public EditOutput(String oldOutputText, JFrame frame) {
		super("Output", frame);
		panel = new JPanel();
		this.setContentPane(panel);
		this.oldOutputText = oldOutputText;
		
		newOutputText = null;
		
		setSize(329, 142);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
	
		JButton btnSave = new JButton("Save");
		getContentPane().add(btnSave, BorderLayout.SOUTH);
		
		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//result
				newOutputText = getOutputText();
				
				dispose();
			}
		});
		
		setPreviousValues();
	}
	
	public String showEditWindow() {
		setVisible(true);
		
		return this.newOutputText;
	}
	
	private void setPreviousValues() {
		textArea.setText(oldOutputText);
	}

	public void setText(String s) {
		textArea.setText(s);
	}
	
	public String getOutputText() {
		return textArea.getText();
	}
}
