package view.editComponents;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFrame;

public class EditComment extends EditComponent{
	
	private String oldComment;
	private String newComment;
	private JPanel panel;
	
	private JTextArea textArea;

	public EditComment(String oldOutputText, JFrame frame) {
		super("Comment", frame);
		panel = new JPanel();
		this.setContentPane(panel);
		this.oldComment = oldOutputText;
		
		newComment = null;
		
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
				newComment = getOutputText();
				
				dispose();
			}
		});
		
		setPreviousValues();
	}
	
	public String showEditWindow() {
		setVisible(true);
		
		return this.newComment;
	}
	
	private void setPreviousValues() {
		textArea.setText(oldComment);
	}

	public void setText(String s) {
		textArea.setText(s);
	}
	
	public String getOutputText() {
		return textArea.getText();
	}
}

