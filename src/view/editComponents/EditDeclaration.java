package view.editComponents;

import javax.swing.JPanel;

import model.Components.ComponentAssign;
import model.Components.ComponentDeclaration;
import model.Components.ComponentIf;
import model.Memory.VariableType;
import view.editComponents.EditAssign.ValuesAssignComponent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Set;

import javax.swing.SwingConstants;
import javax.swing.JTextField;

/**
 * <p>This class handles the configuration dialog of the component {@link ComponentDeclaration}</p>
 * */
public class EditDeclaration extends EditComponent{
	
	private ValuesDeclarationComponent oldValues;
	private ValuesDeclarationComponent newValues;
	private JPanel panel;
	private JTextField textFieldVarName;
	
	private JComboBox<String> comboBoxType;
	private HashMap<String, VariableType> varTypes;
	
	/**<p>
	* The constructor of the class {@link EditDeclaration}
	* </p>
	* @param oldValues The values already set in the component
	* @param frame The parent frame
	*/
	public EditDeclaration(ValuesDeclarationComponent oldValues, JFrame frame) {
		super("Declaration", frame);
		panel = new JPanel();
		this.oldValues = oldValues;
		
		newValues = null;
		
		this.setContentPane(panel);
		panel.setLayout(null);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(0, 70, 309, 19);
		panel.add(btnSave);
		
		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//result
				newValues = new ValuesDeclarationComponent(getVarName(), getVarType());
				
				dispose();
			}
		});
		
		//comboBox
		varTypes = new HashMap<>();
		
		for (VariableType t: VariableType.values()) {
			varTypes.put(t.name, t);
		}
		
		Set<String> varTypesNames = varTypes.keySet();
		
		comboBoxType = new JComboBox();
		comboBoxType.setBounds(44, 27, 83, 19);
		panel.add(comboBoxType);
		
		for (String name : varTypesNames) {
			comboBoxType.addItem(name);
		}
		
		JLabel lblNewLabel = new JLabel("$");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(124, 26, 31, 17);
		panel.add(lblNewLabel);
		
		textFieldVarName = new JTextField();
		textFieldVarName.setBounds(157, 28, 86, 17);
		panel.add(textFieldVarName);
		textFieldVarName.setColumns(10);
		
		setSize(323, 126);
		
		setPreviousValues();
	}
	
	/**
	 * <p>
	 * This method shows the JDialog of the {@link ComponentDeclaration} configuration and returns the new values provided
	 * by the user when closed
	 * </p>
	 * @return {@link ValuesDeclarationComponent} The new values
	 * */
	public ValuesDeclarationComponent showEditWindow() {
		setVisible(true);
		
		return this.newValues;
	}
	
	/**
	 * <p>
	 * This method gets the values already set in the {@link ComponentDeclaration} component and updates
	 * the fields in the configuration dialog
	 * </p>
	 * */
	private void setPreviousValues() {
		textFieldVarName.setText(oldValues.getVarName());
		
		if (oldValues.getVarTypeString() != null) {
			comboBoxType.setSelectedItem(oldValues.getVarTypeString());
		}else {
			comboBoxType.setSelectedIndex(0);
		}
		
	}
	
	/**
	 * <p>
	 * This method returns the name of the variable that will be declared
	 * </p>
	 * @return {@link String} The name
	 * */
	public String getVarName() {
		return textFieldVarName.getText();
	}
	
	/**
	 * <p>
	 * This method returns the type of the variable that will be declared
	 * </p>
	 * @return {@link VariableType} The type
	 * */
	public VariableType getVarType() {
		String selected = (String)comboBoxType.getSelectedItem();
		
		return varTypes.get(selected);
	}
	
	/**
	 * <p>This class handles the new values requested from the configuration dialog of the component {@link ComponentDeclaration}</p>
	 * */
	public static class ValuesDeclarationComponent{
		public String varName;
		public VariableType varType;
		
		/**<p>
		* The constructor of the class {@link ValuesDeclarationComponent}
		* </p>
		* @param varName The name of the variable that will be declared
		* @param varType The type of the variable that will be declared
		*/
		public ValuesDeclarationComponent(String varName, VariableType varType) {
			this.varName = varName;
			this.varType = varType;
		}

		/**
		 * <p>
		 * This method returns the name of the variable that will be declared from the values class
		 * </p>
		 * @return {@link String} The name
		 * */
		public String getVarName() {
			return varName;
		}

		/**
		 * <p>
		 * This method sets the name of the variable that will be declared
		 * </p>
		 * @param varName The new name
		 * */
		public void setVarName(String varName) {
			this.varName = varName;
		}

		/**
		 * <p>
		 * This method returns the type of the variable that will be declared from the values class
		 * </p>
		 * @return {@link String} The type
		 * */
		public VariableType getVarType() {
			return varType;
		}

		/**
		 * <p>
		 * This method sets the type of the variable that will be declared 
		 * </p>
		 * @param varType The new type
		 * */
		public void setVarType(VariableType varType) {
			this.varType = varType;
		}
		
		/**
		 * <p>
		 * This method returns the type of the variable that will be declared as a string from the values class
		 * </p>
		 * @return {@link String} The type as a string
		 * */
		public String getVarTypeString() {
			
			if (varType != null) {
				return varType.name;
			}
			
			return null;
		}
		
	}
	
}