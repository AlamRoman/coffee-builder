package view.editComponents;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.Components.ComponentAssign;
import model.Components.ComponentComment;
import model.Components.ComponentDeclaration;
import view.editComponents.EditAssign.ValuesAssignComponent;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * <p>This class handles the configuration dialog of the component {@link ComponentComment}</p>
 * */
public class EditComment extends EditComponent{
	
	private String oldComment;
	private String newComment;
	private JPanel panel;
	
	private JTextArea textArea;

	/**<p>
	* The constructor of the class {@link EditComment}
	* </p>
	* @param oldOutputText The output text already set in the component
	* @param frame The parent frame
	*/
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
		textArea.setMargin(new Insets(10, 10, 10, 10));
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
	
	/**
	 * <p>
	 * This method shows the JDialog of the {@link ComponentComment} configuration and returns the new values provided
	 * by the user when closed
	 * </p>
	 * @return {@link String} The new value
	 * */
	public String showEditWindow() {
		setVisible(true);
		
		return this.newComment;
	}
	
	/**
	 * <p>
	 * This method gets the values already set in the {@link ComponentComment} component and updates
	 * the fields in the configuration dialog
	 * </p>
	 * */
	private void setPreviousValues() {
		textArea.setText(oldComment);
	}

	/**
	 * <p>
	 * This method sets the text of the comment
	 * </p>
	 * @param s The new text
	 * */
	public void setText(String s) {
		textArea.setText(s);
	}
	
	/**
	 * <p>
	 * This method returns the text of the comment
	 * </p>
	 * @return {@link String} The text
	 * */
	public String getOutputText() {
		return textArea.getText();
	}
}

