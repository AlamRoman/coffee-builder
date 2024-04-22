package view.editComponents;

import javax.swing.JPanel;

import model.Components.ComponentDeclaration;
import model.Memory.VariableType;

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

public class EditDeclaration extends EditComponent{
	
	private ValuesDeclarationComponent oldValues;
	private ValuesDeclarationComponent newValues;
	private JPanel panel;
	private JTextField textFieldVarName;
	
	private JComboBox<String> comboBoxType;
	private HashMap<String, VariableType> varTypes;
	
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
	
	public ValuesDeclarationComponent showEditWindow() {
		setVisible(true);
		
		return this.newValues;
	}
	
	private void setPreviousValues() {
		textFieldVarName.setText(oldValues.getVarName());
		
		if (oldValues.getVarTypeString() != null) {
			comboBoxType.setSelectedItem(oldValues.getVarTypeString());
		}else {
			comboBoxType.setSelectedIndex(0);
		}
		
	}
	
	public String getVarName() {
		return textFieldVarName.getText();
	}
	
	public VariableType getVarType() {
		String selected = (String)comboBoxType.getSelectedItem();
		
		return varTypes.get(selected);
	}
	
	public static class ValuesDeclarationComponent{
		public String varName;
		public VariableType varType;
		
		public ValuesDeclarationComponent(String varName, VariableType varType) {
			this.varName = varName;
			this.varType = varType;
		}

		public String getVarName() {
			return varName;
		}

		public void setVarName(String varName) {
			this.varName = varName;
		}

		public VariableType getVarType() {
			return varType;
		}

		public void setVarType(VariableType varType) {
			this.varType = varType;
		}
		
		public String getVarTypeString() {
			
			if (varType != null) {
				return varType.name;
			}
			
			return null;
		}
		
	}
	
}