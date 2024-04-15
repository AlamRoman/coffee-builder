package view.editComponents;

import java.util.HashMap;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Components.ComponentWhile;
import model.Memory.RelationalOperators;

public class EditWhile extends EditComponent{
	
	private ComponentWhile componentWhile;
	private JPanel panel;
	private JTextField textFieldTerm1;
	private JTextField textFieldTerm2;
	private JComboBox<String> comboBoxOperator;
	
	private HashMap<String, RelationalOperators> operators;

	public EditWhile(ComponentWhile componentWhile) {
		super("While");
		panel = new JPanel();
		this.setContentPane(panel);
		this.componentWhile  = componentWhile;
		setSize(312, 162);
		panel.setLayout(null);
		
		setVisible(true);
		
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
		
		setPreviousValues();
	}
	
	private void setPreviousValues() {
		textFieldTerm1.setText(componentWhile.getTerm1());
		textFieldTerm2.setText(componentWhile.getTerm2());
		
		comboBoxOperator.setSelectedItem(componentWhile.getOperator().symbol);
	}
	
	public void controlOperator() {
		if (comboBoxOperator.getSelectedItem().equals("!")) {
			textFieldTerm2.setText("");
			textFieldTerm2.setEditable(false);
		}else {
			textFieldTerm2.setText(componentWhile.getTerm2());
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

	public static void main(String[] args) {
		
		ComponentWhile comp = new ComponentWhile(null, null);
		comp.set("$var1", RelationalOperators.GREATER_THAN_EQUAL_TO, "$var2");

		EditWhile ed = new EditWhile(comp);
	}
}
