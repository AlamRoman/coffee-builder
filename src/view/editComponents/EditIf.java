package view.editComponents;

import javax.swing.JPanel;

import model.Components.ComponentIf;
import model.Memory.RelationalOperators;
import model.Memory.TipoOperazioni;

import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class EditIf extends EditComponent{
	
	private ComponentIf componentIf;
	private JPanel panel;
	private JTextField textFieldTerm1;
	private JTextField textFieldTerm2;
	private JComboBox<String> comboBoxOperator;
	
	private HashMap<String, RelationalOperators> operators;

	public EditIf(ComponentIf componentIf) {
		super("If");
		panel = new JPanel();
		this.setContentPane(panel);
		panel.setLayout(null);
		setSize(312, 162);
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(0, 105, 297, 19);
		panel.add(btnSave);
		
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
		
		textFieldTerm2 = new JTextField();
		textFieldTerm2.setColumns(10);
		textFieldTerm2.setBounds(198, 42, 86, 17);
		panel.add(textFieldTerm2);
		this.componentIf = componentIf;
		
		setPreviousValues();
	}
	
	private void setPreviousValues() {
		textFieldTerm1.setText(componentIf.getTerm1());
		textFieldTerm2.setText(componentIf.getTerm2());
		
		comboBoxOperator.setSelectedItem(componentIf.getOperator().symbol);
	}
	
	public void controlOperator() {
		if (comboBoxOperator.getSelectedItem().equals("!")) {
			textFieldTerm2.setText("");
			textFieldTerm2.setEditable(false);
		}else {
			textFieldTerm2.setText(componentIf.getTerm2());
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
}
