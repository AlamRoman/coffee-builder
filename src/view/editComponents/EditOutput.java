package view.editComponents;

import javax.swing.JPanel;

import model.Components.ComponentAssign;
import model.Components.ComponentOutput;
import model.Components.ComponentWhile;
import view.editComponents.EditAssign.ValuesAssignComponent;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * <p>This class handles the configuration dialog of the component {@link ComponentOutput}</p>
 * */
public class EditOutput extends EditComponent{
	
	private String oldOutputText;
	private String newOutputText;
	private JPanel panel;
	
	private JTextArea textArea;

	/**<p>
	* The constructor of the class {@link EditOutput}
	* </p>
	* @param oldOutputText The output text already set in the component
	* @param frame The parent frame
	*/
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
		textArea.setMargin(new Insets(10, 10, 10, 10));
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
	
	/**
	 * <p>
	 * This method shows the JDialog of the {@link ComponentOutput} configuration and returns the new values provided
	 * by the user when closed
	 * </p>
	 * @return {@link String} The new value
	 * */
	public String showEditWindow() {
		setVisible(true);
		
		return this.newOutputText;
	}
	
	/**
	 * <p>
	 * This method gets the values already set in the {@link ComponentOutput} component and updates
	 * the fields in the configuration dialog
	 * </p>
	 * */
	private void setPreviousValues() {
		textArea.setText(oldOutputText);
	}

	/**
	 * <p>
	 * This method sets the text of the {@link TextArea} in the dialog
	 * </p>
	 * @param s The new text
	 * */
	public void setText(String s) {
		textArea.setText(s);
	}
	
	/**
	 * <p>
	 * This method returns the text of the {@link TextArea} in the dialog
	 * </p>
	 * @return {@link String} The text
	 * */
	public String getOutputText() {
		return textArea.getText();
	}
}
