package view.editComponents;

import javax.swing.JPanel;

import model.Components.ComponentAssign;
import model.Components.ComponentOperation;
import model.Components.ComponentOutput;
import model.Memory.OperationType;
import view.editComponents.EditAssign.ValuesAssignComponent;
import view.editComponents.EditIf.ValuesIfComponent;

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

/**
 * <p>This class handles the configuration dialog of the component {@link ComponentOperation}</p>
 * */
public class EditOperation extends EditComponent{
	
	private ValuesOperationComponent oldValues;
	private ValuesOperationComponent newValues;
	private JPanel panel;
	private JTextField textFieldFinalVar;
	private JTextField textFieldVar1;
	private JTextField textFieldVar2;
	private JComboBox<String> comboBoxOperator;
	
	private HashMap<String, OperationType> operators;

	/**<p>
	* The constructor of the class {@link EditOperation}
	* </p>
	* @param oldValues The values already set in the component
	* @param parent The parent frame
	*/
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
		
		JLabel lblNewLabel_1 = new JLabel("$");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(8, 43, 13, 13);
		panel.add(lblNewLabel_1);
		
		setPreviousValues();
	}
	
	/**
	 * <p>
	 * This method gets the values already set in the {@link ComponentOperation} component and updates
	 * the fields in the configuration dialog
	 * </p>
	 * */
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
	
	/**
	 * <p>
	 * This method shows the JDialog of the {@link ComponentOperation} configuration and returns the new values provided
	 * by the user when closed
	 * </p>
	 * @return {@link ValuesOperationComponent} The new values
	 * */
	public ValuesOperationComponent showEditWindow() {
		setVisible(true);
		
		return this.newValues;
	}
	
	/**
	 * <p>
	 * This method returns the name of the variable that will store the final result
	 * </p>
	 * @return {@link String} The name of the variable
	 * */
	public String getFinalVarName() {
		return textFieldFinalVar.getText();
	}
	
	/**
	 * <p>
	 * This method returns the first operand of the condition
	 * </p>
	 * @return {@link String} The first operand
	 * */
	public String getVar1Name() {
		return textFieldVar1.getText();
	}
	
	/**
	 * <p>
	 * This method returns the second operand of the condition
	 * </p>
	 * @return {@link String} The second operand
	 * */
	public String getVar2Name() {
		return textFieldVar2.getText();
	}
	
	/**
	 * <p>
	 * This method returns the operator of the condition
	 * </p>
	 * @return {@link OperationType} The operator
	 * */
	public OperationType getOperazione() {
		String selected = (String)comboBoxOperator.getSelectedItem();
		
		return operators.get(selected);
	}
	
	/**
	 * <p>This class handles the new values requested from the configuration dialog of the component {@link ComponentOperation}</p>
	 * */
	public static class ValuesOperationComponent{

		public String finalVar;
		public String var1;
		public String var2;
		public OperationType operation;
		
		/**<p>
		* The constructor of the class {@link ValuesOperationComponent}
		* </p>
		* @param finalVar The variable that will store the final result
		* @param var1 The first operand of the operation
		* @param var2 The second operand of the operation
		* @param operation The type of the operation that will be executed
		*/
		public ValuesOperationComponent(String finalVar, String var1, String var2, OperationType operation) {
			this.finalVar = finalVar;
			this.var1 = var1;
			this.var2 = var2;
			this.operation = operation;
		}

		/**
		 * <p>
		 * This method returns the name of the variable that will store the final result
		 * </p>
		 * @return {@link String} The variable name
		 * */
		public String getFinalVar() {
			return finalVar;
		}

		/**
		 * <p>
		 * This method sets the name of the variable that will store the final result
		 * </p>
		 * @param finalVar The new variable name
		 * */
		public void setFinalVar(String finalVar) {
			this.finalVar = finalVar;
		}

		/**
		 * <p>
		 * This method returns the first operand from the values class
		 * </p>
		 * @return {@link String} The first operand
		 * */
		public String getVar1() {
			return var1;
		}

		/**
		 * <p>
		 * This method sets the first operand in the values class
		 * </p>
		 * @param var1 The new first operand
		 * */
		public void setVar1(String var1) {
			this.var1 = var1;
		}

		/**
		 * <p>
		 * This method returns the second operand from the values class
		 * </p>
		 * @return {@link String} The second operand
		 * */
		public String getVar2() {
			return var2;
		}

		/**
		 * <p>
		 * This method sets the second operand in the values class
		 * </p>
		 * @param var2 The new second operand
		 * */
		public void setVar2(String var2) {
			this.var2 = var2;
		}

		/**
		 * <p>
		 * This method return the type of operation from the values class
		 * </p>
		 * @return {@link OperationType} The type of operation
		 * */
		public OperationType getOperation() {
			return operation;
		}

		/**
		 * <p>
		 * This method sets the operator of the condition in the values class
		 * </p>
		 * @param operation The new operator
		 * */
		public void setOperation(OperationType operation) {
			this.operation = operation;
		}
		
	}
}
