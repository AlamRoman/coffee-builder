package view.editComponents;

import javax.swing.JPanel;

import model.Components.ComponentDeclaration;
import model.Memory.VariableType;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.HashMap;
import java.util.Set;

import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class EditDeclaration extends EditComponent{
	
	private ComponentDeclaration componentDeclaration;
	private JPanel panel;
	private JTextField textFieldVarName;
	
	private JComboBox<String> comboBoxType;
	private HashMap<String, VariableType> varTypes;
	
	public EditDeclaration(ComponentDeclaration componentDeclaration, JFrame frame) {
		super("Declaration", frame);
		panel = new JPanel();
		this.setContentPane(panel);
		panel.setLayout(null);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(0, 70, 309, 19);
		panel.add(btnSave);
		
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
		this.componentDeclaration = componentDeclaration;
		setSize(323, 126);
		
		setPreviousValues();
	}
	
	private void setPreviousValues() {
		textFieldVarName.setText(componentDeclaration.getVariableName());
		comboBoxType.setSelectedItem(componentDeclaration.getTypeString());
	}
	
	public String getVarName() {
		return textFieldVarName.getText();
	}
	
	public VariableType getVarType() {
		String selected = (String)comboBoxType.getSelectedItem();
		
		return varTypes.get(selected);
	}
	
}