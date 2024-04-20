package view.editComponents;

import javax.swing.JPanel;

import model.Components.ComponentOperation;
import model.Memory.OperationType;

import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class EditOperation extends EditComponent{
	
	private ValuesOperationComponent oldValues;
	private ValuesOperationComponent newValues;
	private JPanel panel;
	private JTextField textFieldFinalVar;
	private JTextField textFieldVar1;
	private JTextField textFieldVar2;
	private JComboBox<String> comboBoxOperator;
	
	private HashMap<String, OperationType> operators;

	public EditOperation(ValuesOperationComponent oldValues, JFrame parent) {
		super("Operation", parent);
		panel = new JPanel();
		this.setContentPane(panel);
		this.oldValues = oldValues;
		
		newValues = null;
		
		setSize(406, 156);
		getContentPane().setLayout(null);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(22, 94, 341, 19);
		getContentPane().add(btnSave);
		
		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//result
				newValues = new ValuesOperationComponent(getFinalVarName(), getVar1Name(), getVar2Name(), getOperazione());
				
				dispose();
			}
		});
		
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
		textFieldFinalVar.setText(oldValues.getFinalVar());
		textFieldVar1.setText(oldValues.getVar1());
		textFieldVar2.setText(oldValues.getVar2());
		
		if (oldValues.getOperation() != null) {
			comboBoxOperator.setSelectedItem(oldValues.getOperation().symbol);
		}else {
			comboBoxOperator.setSelectedIndex(0);
		}

	}
	
	public ValuesOperationComponent showEditWindow() {
		setVisible(true);
		
		return this.newValues;
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
	
	public static class ValuesOperationComponent{

		public String finalVar;
		public String var1;
		public String var2;
		public OperationType operation;
		
		public ValuesOperationComponent(String finalVar, String var1, String var2, OperationType operation) {
			this.finalVar = finalVar;
			this.var1 = var1;
			this.var2 = var2;
			this.operation = operation;
		}

		public String getFinalVar() {
			return finalVar;
		}

		public void setFinalVar(String finalVar) {
			this.finalVar = finalVar;
		}

		public String getVar1() {
			return var1;
		}

		public void setVar1(String var1) {
			this.var1 = var1;
		}

		public String getVar2() {
			return var2;
		}

		public void setVar2(String var2) {
			this.var2 = var2;
		}

		public OperationType getOperation() {
			return operation;
		}

		public void setOperation(OperationType operation) {
			this.operation = operation;
		}
		
	}
	
	
}
