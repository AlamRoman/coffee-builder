package view.editComponents;

import javax.swing.JPanel;

import model.Components.ComponentInput;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.Font;
import javax.swing.SwingConstants;

public class EditInput extends EditComponent{

	private ComponentInput componentInput;
	private JPanel panel;
	private JTextField textFieldVarName;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	
	private String inputVarName;
	
	public EditInput(ComponentInput componentInput, JFrame frame) {
		super("Input", frame);
		panel = new JPanel();
		this.setContentPane(panel);
		this.componentInput = componentInput;
		getContentPane().setLayout(null);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(0, 107, 323, 19);
		getContentPane().add(btnSave);
		
		textFieldVarName = new JTextField();
		textFieldVarName.setBounds(94, 51, 122, 17);
		getContentPane().add(textFieldVarName);
		textFieldVarName.setColumns(10);
		
		inputVarName = componentInput.getNomeVariabile();
		
		textFieldVarName.setText(inputVarName);
		
		lblNewLabel = new JLabel("$");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(51, 54, 40, 11);
		getContentPane().add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Inserisci il nome della variabile da prendere in input : ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(19, 31, 277, 11);
		getContentPane().add(lblNewLabel_1);
	}

	public String getInputVarName() {
		return inputVarName;
	}

	public void setInputVarName(String inputVarName) {
		this.inputVarName = inputVarName;
	}
	
}
