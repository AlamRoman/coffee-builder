package view.editComponents;

import javax.swing.JPanel;

import model.Components.ComponentAssign;
import model.Components.ComponentIf;
import model.Components.ComponentInput;
import model.Memory.RelationalOperators;
import view.editComponents.EditAssign.ValuesAssignComponent;
import view.editComponents.EditDeclaration.ValuesDeclarationComponent;
import model.Memory.OperationType;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFrame;

/**
 * <p>This class handles the configuration dialog of the component {@link ComponentIf}</p>
 * */
public class EditIf extends EditComponent{
	
	private ValuesIfComponent oldValues;
	private ValuesIfComponent newValues;
	private JPanel panel;
	private JTextField textFieldTerm1;
	private JTextField textFieldTerm2;
	private JComboBox<String> comboBoxOperator;
	
	private HashMap<String, RelationalOperators> operators;

	/**<p>
	* The constructor of the class {@link EditIf}
	* </p>
	* @param oldValues The values already set in the component
	* @param parent The parent frame
	*/
	public EditIf(ValuesIfComponent oldValues, JFrame parent) {
		super("If", parent);
		panel = new JPanel();
		this.setContentPane(panel);
		this.oldValues = oldValues;
		
		newValues = null;
		
		panel.setLayout(null);
		setSize(312, 162);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(0, 105, 297, 19);
		panel.add(btnSave);
		
		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//result
				newValues = new ValuesIfComponent(getTerm1(), getTerm2(), getOperator());
				
				dispose();
			}
		});
		
		textFieldTerm1 = new JTextField();
		textFieldTerm1.setBounds(24, 42, 86, 17);
		panel.add(textFieldTerm1);
		textFieldTerm1.setColumns(10);
		
		//comboBox
		operators = new HashMap<>();
		
		for (RelationalOperators op : RelationalOperators.values()) {
			operators.put(op.symbol, op);
		}
		
		Set<String> operatorSymbols = operators.keySet();
		
		comboBoxOperator = new JComboBox<>();
		comboBoxOperator.setBounds(128, 41, 51, 19);
		panel.add(comboBoxOperator);
		
		for (String symbol : operatorSymbols) {
			comboBoxOperator.addItem(symbol);
		}
		
		comboBoxOperator.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				checkOperator();
			}
		});
		
		textFieldTerm2 = new JTextField();
		textFieldTerm2.setColumns(10);
		textFieldTerm2.setBounds(198, 42, 86, 17);
		panel.add(textFieldTerm2);

		setPreviousValues();
	}
	
	/**
	 * <p>
	 * This method gets the values already set in the {@link ComponentIf} component and updates
	 * the fields in the configuration dialog
	 * </p>
	 * */
	public ValuesIfComponent showEditWindow() {
		setVisible(true);
		
		return this.newValues;
	}
	
	/**
	 * <p>
	 * This method shows the JDialog of the {@link ComponentIf} configuration and returns the new values provided
	 * by the user when closed
	 * </p>
	 * @return {@link ValuesIfComponent} The new values
	 * */
	private void setPreviousValues() {
		textFieldTerm1.setText(oldValues.getTerm1());
		textFieldTerm2.setText(oldValues.getTerm2());
		
		
		if (oldValues.getOperator() != null) {
			comboBoxOperator.setSelectedItem(oldValues.getOperator().symbol);
		}else {
			comboBoxOperator.setSelectedIndex(0);
		}	
	}
	
	/**
	 * <p>
	 * This method checks if the operator, selected by the user, is a not operator
	 * </p>
	 * <ul>
	 * <li>If yes it changes the editing status of the {@link JTextField}s</li>
	 * <li>If not it wont change the editing status of the {@link JTextField}s</li>
	 * </ul>
	 * */
	public void checkOperator() {
		if (comboBoxOperator.getSelectedItem().equals("!")) {
			oldValues.setTerm2(textFieldTerm2.getText());
			textFieldTerm2.setText("");
			textFieldTerm2.setEditable(false);
		}else {
			textFieldTerm2.setText(oldValues.getTerm2());
			textFieldTerm2.setEditable(true);
		}
	}
	
	/**
	 * <p>
	 * This method returns the first term of the condition
	 * </p>
	 * @return {@link String} The second term
	 * */
	public String getTerm1() {
		return textFieldTerm1.getText();
	}
	
	/**
	 * <p>
	 * This method returns the second term of the condition
	 * </p>
	 * @return {@link String} The second term
	 * */
	public String getTerm2() {
		return textFieldTerm2.getText();
	}
	
	/**
	 * <p>
	 * This method returns the operator of the condition
	 * </p>
	 * @return {@link RelationalOperators} The operator
	 * */
	public RelationalOperators getOperator() {
		String selected = (String)comboBoxOperator.getSelectedItem();
		
		return operators.get(selected);
	}
	
	/**
	 * <p>This class handles the new values requested from the configuration dialog of the component {@link ComponentIf}</p>
	 * */
	public static class ValuesIfComponent {
		public String term1;
		public String term2;
		public RelationalOperators operator;
		
		/**<p>
		* The constructor of the class {@link ValuesIfComponent}
		* </p>
		* @param term1 The first term of the condition
		* @param term2 The second term of the condition
		* @param operator The operator of the condition
		*/
		public ValuesIfComponent(String term1, String term2, RelationalOperators operator) {
			this.term1 = term1;
			this.term2 = term2;
			this.operator = operator;
		}

		/**
		 * <p>
		 * This method returns the first term from the values class
		 * </p>
		 * @return {@link String} The second term
		 * */
		public String getTerm1() {
			return term1;
		}

		/**
		 * <p>
		 * This method sets the first term in the values class
		 * </p>
		 * @param term1 The new first term
		 * */
		public void setTerm1(String term1) {
			this.term1 = term1;
		}

		/**
		 * <p>
		 * This method returns the second term from the values class
		 * </p>
		 * @return {@link String} The second term
		 * */
		public String getTerm2() {
			return term2;
		}
		
		/**
		 * <p>
		 * This method sets the second term in the values class
		 * </p>
		 * @param term2 The new second term
		 * */
		public void setTerm2(String term2) {
			this.term2 = term2;
		}

		/**
		 * <p>
		 * This method returns the operator in the values class
		 * </p>
		 * @return {@link RelationalOperators} The operator
		 * */
		public RelationalOperators getOperator() {
			return operator;
		}

		/**
		 * <p>
		 * This method sets the operator in the values class
		 * </p>
		 * @param operator The new operator
		 * */
		public void setOperator(RelationalOperators operator) {
			this.operator = operator;
		}
		
	}
	
}
