package view.editComponents;

import javax.swing.JPanel;

import model.Components.ComponentAssign;
import model.Components.ComponentComment;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <p>This class handles the configuration dialog of the component {@link ComponentAssign}</p>
 * */
public class EditAssign extends EditComponent{
	
	private ValuesAssignComponent previousValues;
	private ValuesAssignComponent newValues = null;
	private JPanel panel;
	private JTextField textFieldFinalVar;
	private JTextField textFieldValue;
	
	/**<p>
	* The constructor of the class {@link EditAssign}
	* </p>
	* @param previousValues The values already set in the component
	* @param parent The parent frame
	*/
	public EditAssign(ValuesAssignComponent previousValues, JFrame parent) {
		super("Assign", parent);
		this.previousValues = previousValues;
		panel = new JPanel();
		this.setContentPane(panel);
		
		panel.setLayout(null);
		setSize(335, 131);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(0, 75, 321, 19);
		panel.add(btnSave);
		
		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String varName = textFieldFinalVar.getText();
				String varValue = textFieldValue.getText();
				
				//result
				if (!varName.equals("") && !varValue.equals("")) {
					newValues = new ValuesAssignComponent(varName, varValue);
					
					dispose();
				}
			}
		});
		
		textFieldFinalVar = new JTextField();
		textFieldFinalVar.setBounds(58, 35, 86, 17);
		panel.add(textFieldFinalVar);
		textFieldFinalVar.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("=");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(141, 38, 40, 11);
		panel.add(lblNewLabel);
		
		textFieldValue = new JTextField();
		textFieldValue.setBounds(180, 35, 86, 17);
		panel.add(textFieldValue);
		textFieldValue.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("$");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 38, 40, 11);
		panel.add(lblNewLabel_1);
		
		setPreviousValues();
	}
	
	/**
	 * <p>
	 * This method shows the JDialog of the {@link ComponentAssign} configuration and returns the new values provided
	 * by the user when closed
	 * </p>
	 * @return {@link ValuesAssignComponent} The new values
	 * */
	public ValuesAssignComponent showEditWindow() {
		setVisible(true);
		return getNewValues();
	}
	
	/**
	 * <p>
	 * This method gets the values already set in the {@link ComponentAssign} component and updates
	 * the fields in the configuration dialog
	 * </p>
	 * */
	private void setPreviousValues() {
		textFieldFinalVar.setText(previousValues.getFinalVarName());
		textFieldValue.setText(previousValues.getValue());
	}
	
	/**
	 * <p>
	 * This method returns the new values set by the user
	 * </p>
	 * @return {@link ValuesAssignComponent} The new values
	 * */
	public ValuesAssignComponent getNewValues() {
		return this.newValues;
	}
	
	/**
	 * <p>This class handles the new values requested from the configuration dialog of the component {@link ComponentAssign}</p>
	 * */
	public static class ValuesAssignComponent{
		public String finalVarName;
		public String value;
		
		/**<p>
		* The constructor of the class {@link ValuesAssignComponent}
		* </p>
		* @param finalVarName The name of the variable that will be assigned
		* @param value The value that is going to be assigned to the variable
		*/
		public ValuesAssignComponent(String finalVarName, String value) {
			this.finalVarName = finalVarName;
			this.value = value;
		}

		/**
		 * <p>
		 * This method returns the name of the variable that will be assigned
		 * </p>
		 * @return {@link String} The variable name
		 * */
		public String getFinalVarName() {
			return finalVarName;
		}

		/**
		 * <p>
		 * This method sets the name of the variable that will be assigned
		 * </p>
		 * @param finalVarName The new name
		 * */
		public void setFinalVarName(String finalVarName) {
			this.finalVarName = finalVarName;
		}

		/**
		 * <p>
		 * This method returns the value that will be assigned to the variable
		 * </p>
		 * @return {@link String} The value
		 * */
		public String getValue() {
			return value;
		}

		/**
		 * <p>
		 * This method sets the value that will be assigned to the variable
		 * </p>
		 * @param value The new value
		 * */
		public void setValue(String value) {
			this.value = value;
		}
	}

	@Override
	public String toString() {
		return "EditAssign [previousValues=" + previousValues + ", newValues=" + newValues + ", panel=" + panel
				+ ", textFieldFinalVar=" + textFieldFinalVar + ", textFieldValue=" + textFieldValue + "]";
	}
	
}
