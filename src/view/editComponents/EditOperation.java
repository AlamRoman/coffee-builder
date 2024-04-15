package view.editComponents;

import javax.swing.JPanel;

import model.Components.ComponentOperation;
import model.Memory.OperationType;

import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class EditOperation extends EditComponent{
	
	private ComponentOperation componentOperation;
	private JPanel panel;
	private JTextField textFieldFinalVar;
	private JTextField textFieldVar1;
	private JTextField textFieldVar2;
	private JComboBox<String> comboBoxOperator;
	
	private HashMap<String, OperationType> operators;

	public EditOperation(ComponentOperation componentOperation) {
		super("Operation");
		panel = new JPanel();
		this.setContentPane(panel);
		this.componentOperation = componentOperation;
		setSize(406, 156);
		getContentPane().setLayout(null);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(22, 94, 341, 19);
		getContentPane().add(btnSave);
		
		textFieldFinalVar = new JTextField();
		textFieldFinalVar.setBounds(22, 41, 86, 17);
		getContentPane().add(textFieldFinalVar);
		textFieldFinalVar.setColumns(10);
		
		textFieldVar1 = new JTextField();
		textFieldVar1.setColumns(10);
		textFieldVar1.setBounds(151, 41, 86, 17);
		getContentPane().add(textFieldVar1);
		
		//comboBox
		operators = new HashMap<>();
		
		for (OperationType op : OperationType.values()) {
			operators.put(op.symbol, op);
		}
		
		Set<String> operatorSymbols = operators.keySet();
		
		comboBoxOperator = new JComboBox<>();
		comboBoxOperator.setBounds(245, 40, 37, 19);
		getContentPane().add(comboBoxOperator);
		
		for (String symbol : operatorSymbols) {
			comboBoxOperator.addItem(symbol);
		}
		
		textFieldVar2 = new JTextField();
		textFieldVar2.setBounds(287, 41, 86, 17);
		getContentPane().add(textFieldVar2);
		textFieldVar2.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("=");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(103, 42, 50, 16);
		getContentPane().add(lblNewLabel);	
		
		setPreviousValues();
	}
	
	private void setPreviousValues() {
		textFieldFinalVar.setText(componentOperation.getVariableName());
		textFieldVar1.setText(componentOperation.getVariableFirstOperandName());
		textFieldVar2.setText(componentOperation.getVariableSecondOperandName());
		
		comboBoxOperator.setSelectedItem(componentOperation.getOperation().symbol);
	}
	
	public String getFinalVarName() {
		return textFieldFinalVar.getText();
	}
	
	public String getVar1Name() {
		return textFieldVar1.getText();
	}
	
	public String getVar2Name() {
		return textFieldVar2.getText();
	}
	
	public OperationType getOperazione() {
		String selected = (String)comboBoxOperator.getSelectedItem();
		
		return operators.get(selected);
	}
}
