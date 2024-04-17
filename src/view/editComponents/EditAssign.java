package view.editComponents;

import javax.swing.JPanel;

import model.Components.ComponentAssign;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class EditAssign extends EditComponent{
	
	private ComponentAssign componentAssign;
	private JPanel panel;
	private JTextField textFieldFinalVar;
	private JTextField textFieldValue;
	
	public EditAssign(ComponentAssign componentAssign) {
		super("Assign");
		this.componentAssign = componentAssign;
		panel = new JPanel();
		this.setContentPane(panel);
		panel.setLayout(null);
		setSize(335, 131);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(0, 75, 321, 19);
		panel.add(btnSave);
		
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
	
	private void setPreviousValues() {
		textFieldFinalVar.setText(componentAssign.getVariableName());
		textFieldValue.setText(componentAssign.getValueString());
	}
	
	public String getFinalVar() {
		return textFieldFinalVar.getText();
	}
	
	public String getValue() {
		return textFieldValue.getText();
	}
}
