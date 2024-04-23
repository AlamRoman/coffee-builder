package view.editComponents;

import javax.swing.JPanel;

import model.Components.ComponentAssign;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditAssign extends EditComponent{
	
	private ValuesAssignComponent previousValues;
	private ValuesAssignComponent newValues = null;
	private JPanel panel;
	private JTextField textFieldFinalVar;
	private JTextField textFieldValue;
	
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
				newValues = new ValuesAssignComponent(varName, varValue);
				
				dispose();
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
	
	public ValuesAssignComponent showEditWindow() {
		setVisible(true);
		return getNewValues();
	}
	
	private void setPreviousValues() {
		textFieldFinalVar.setText(previousValues.getFinalVarName());
		textFieldValue.setText(previousValues.getValue());
	}
	
	public ValuesAssignComponent getNewValues() {
		return this.newValues;
	}
	
	public static class ValuesAssignComponent{
		public String finalVarName;
		public String value;
		
		public ValuesAssignComponent(String finalVarName, String value) {
			this.finalVarName = finalVarName;
			this.value = value;
		}

		public String getFinalVarName() {
			return finalVarName;
		}

		public void setFinalVarName(String finalVarName) {
			this.finalVarName = finalVarName;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}
	
	public static void main(String[] args) {
		ValuesAssignComponent va = new ValuesAssignComponent(null , null);
		EditAssign ea = new EditAssign(va, null);
		
		ValuesAssignComponent s = ea.showEditWindow();
		
		System.out.println(s.getFinalVarName() + " = " + s.getValue());
	}

	@Override
	public String toString() {
		return "EditAssign [previousValues=" + previousValues + ", newValues=" + newValues + ", panel=" + panel
				+ ", textFieldFinalVar=" + textFieldFinalVar + ", textFieldValue=" + textFieldValue + "]";
	}
	
}
