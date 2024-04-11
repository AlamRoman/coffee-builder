package view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JToggleButton;

public class Panel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextArea outputArea;
	private JToggleButton executeButton;
	private JTextArea debuggerText;

	public Panel() {

		this.setMaximumSize(getMaximumSize());
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{347, 0, 0};
		gridBagLayout.rowHeights = new int[]{32, 0, 71, -36, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		
		executeButton = new JToggleButton("avvia");
		panel.add(executeButton);
		
		JLabel lblNewLabel = new JLabel("Debugger");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		add(lblNewLabel, gbc_lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 4;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		add(scrollPane, gbc_scrollPane);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
		gbc_scrollPane_2.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_2.gridx = 1;
		gbc_scrollPane_2.gridy = 2;
		add(scrollPane_2, gbc_scrollPane_2);
		
		debuggerText = new JTextArea();
		debuggerText.setEditable(false);
		scrollPane_2.setViewportView(debuggerText);
		
		JLabel lblNewLabel_1 = new JLabel("Output");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 3;
		add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 1;
		gbc_scrollPane_1.gridy = 4;
		add(scrollPane_1, gbc_scrollPane_1);
		
		outputArea = new JTextArea();
		outputArea.setEditable(false);
		scrollPane_1.setViewportView(outputArea);
		
	}

	public String getOutputArea() {
		return outputArea.getText();
	}

	public void setOutputArea(String text) {
		outputArea.setText(text);
	}

	public String getDebuggerText() {
		return debuggerText.getText();
	}

	public void setDebuggerText(String text) {
		debuggerText.setText(text);
	}
	
	

}
