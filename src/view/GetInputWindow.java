package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import model.Components.ComponentInput;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GetInputWindow extends JDialog{
	private String inputVarName;
	private String inputValue;
	
	private JPanel panel;
	private JTextField textFieldValue;
	
	public GetInputWindow(String inputValueName, JFrame parent) {
		super(parent, "Input Request", true);
		panel = new JPanel();
		
		this.inputVarName = inputValueName;

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(parent);
		setSize(359, 126);
		setContentPane(panel);
		panel.setLayout(null);
		
		JButton btnOk = new JButton("Ok");
		btnOk.setBounds(0, 70, 345, 19);
		panel.add(btnOk);
		
		btnOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				inputValue = textFieldValue.getText();
				
				dispose();
			}
		});
		
		JLabel lblNewLabel = new JLabel("Give input :");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(40, 8, 262, 11);
		panel.add(lblNewLabel);
		
		textFieldValue = new JTextField();
		textFieldValue.setBounds(173, 37, 122, 17);
		panel.add(textFieldValue);
		textFieldValue.setColumns(10);
		
		JLabel lblVarName = new JLabel(inputValueName);
		lblVarName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVarName.setBounds(11, 40, 122, 11);
		panel.add(lblVarName);
		
		JLabel lblNewLabel_2 = new JLabel("=");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(135, 38, 30, 11);
		panel.add(lblNewLabel_2);

	}
	
	public String showInputRequestWindow() {
		setVisible(true);
		
		return this.inputValue;
	}
	
	public static void main(String[] args) {
		ComponentInput ci = new ComponentInput(null, null, null);
		
		ci.set("var");
		
		GetInputWindow giw = new GetInputWindow(ci.getNomeVariabile(), null);
		
		String ris = giw.showInputRequestWindow();
		
		System.out.println(ris);
	}
}
