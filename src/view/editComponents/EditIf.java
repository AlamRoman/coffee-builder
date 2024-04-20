package view.editComponents;

import javax.swing.JPanel;

import model.Components.ComponentIf;
import model.Memory.RelationalOperators;
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

public class EditIf extends EditComponent{
	
	private ValuesIfComponent oldValues;
	private ValuesIfComponent newValues;
	private JPanel panel;
	private JTextField textFieldTerm1;
	private JTextField textFieldTerm2;
	private JComboBox<String> comboBoxOperator;
	
	private HashMap<String, RelationalOperators> operators;

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
				
				controlOperator();
			}
		});
		
		textFieldTerm2 = new JTextField();
		textFieldTerm2.setColumns(10);
		textFieldTerm2.setBounds(198, 42, 86, 17);
		panel.add(textFieldTerm2);

		setPreviousValues();
	}
	
	public ValuesIfComponent showEditWindow() {
		setVisible(true);
		
		return this.newValues;
	}
	
	private void setPreviousValues() {
		textFieldTerm1.setText(oldValues.getTerm1());
		textFieldTerm2.setText(oldValues.getTerm2());
		
		
		if (oldValues.getOperator() != null) {
			comboBoxOperator.setSelectedItem(oldValues.getOperator().symbol);
		}else {
			comboBoxOperator.setSelectedIndex(0);
		}	
	}
	
	public void controlOperator() {
		if (comboBoxOperator.getSelectedItem().equals("!")) {
			oldValues.setTerm2(textFieldTerm2.getText());
			textFieldTerm2.setText("");
			textFieldTerm2.setEditable(false);
		}else {
			textFieldTerm2.setText(oldValues.getTerm2());
			textFieldTerm2.setEditable(true);
		}
	}
	
	public String getTerm1() {
		return textFieldTerm1.getText();
	}
	
	public String getTerm2() {
		return textFieldTerm2.getText();
	}
	
	public RelationalOperators getOperator() {
		String selected = (String)comboBoxOperator.getSelectedItem();
		
		return operators.get(selected);
	}
	
	public static class ValuesIfComponent {
		public String term1;
		public String term2;
		public RelationalOperators operator;
		
		public ValuesIfComponent(String term1, String term2, RelationalOperators operator) {
			this.term1 = term1;
			this.term2 = term2;
			this.operator = operator;
		}

		public String getTerm1() {
			return term1;
		}

		public void setTerm1(String term1) {
			this.term1 = term1;
		}

		public String getTerm2() {
			return term2;
		}

		public void setTerm2(String term2) {
			this.term2 = term2;
		}

		public RelationalOperators getOperator() {
			return operator;
		}

		public void setOperator(RelationalOperators operator) {
			this.operator = operator;
		}
		
	}
	
}
