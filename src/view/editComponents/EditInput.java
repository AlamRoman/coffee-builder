package view.editComponents;

import javax.swing.JPanel;

import model.Components.ComponentInput;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;

public class EditInput extends EditComponent{

	private String oldVarName;
	private String newVarName;
	private JPanel panel;
	private JTextField textFieldVarName;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	
	private String inputVarName;
	
	public EditInput(String oldVarName, JFrame frame) {
		super("Input", frame);
		panel = new JPanel();
		this.setContentPane(panel);
		this.oldVarName = oldVarName;
		
		newVarName = null;
		
		getContentPane().setLayout(null);
		setSize(335, 130);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(0, 72, 323, 19);
		getContentPane().add(btnSave);
		
		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//result
				newVarName = textFieldVarName.getText();
				
				dispose();
			}
		});
		
		textFieldVarName = new JTextField();
		textFieldVarName.setBounds(103, 52, 122, 17);
		getContentPane().add(textFieldVarName);
		textFieldVarName.setColumns(10);
		
		lblNewLabel = new JLabel("$");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(60, 54, 40, 11);
		getContentPane().add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Insert the variable:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(0, 31, 323, 11);
		getContentPane().add(lblNewLabel_1);
		
		setPreviousValues();
	}
	
	public String showEditWindow() {
		setVisible(true);
		
		return this.newVarName;
	}
	
	private void setPreviousValues() {
		textFieldVarName.setText(oldVarName);
	}

	public String getInputVarName() {
		return inputVarName;
	}

	public void setInputVarName(String inputVarName) {
		this.inputVarName = inputVarName;
	}
	
}
