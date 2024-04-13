package view.editComponents;

import javax.swing.JPanel;

import model.Components.ComponentComment;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class EditComment extends EditComponent{
	
	private ComponentComment componentComment;
	private JPanel panel;
	private JTextArea textArea;

	public EditComment(ComponentComment componentComment) {
		super("Comment");
		panel = new JPanel();
		this.setContentPane(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JButton btnSave = new JButton("Save");
		panel.add(btnSave, BorderLayout.SOUTH);
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		this.componentComment = componentComment;
		
		textArea.setText(componentComment.getCommentString());
	}

	public String getCommentString() {
		return textArea.getText();
	}

}
