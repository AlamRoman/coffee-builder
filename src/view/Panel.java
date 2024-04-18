package view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JLabel;
import javax.swing.JToggleButton;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.DefaultFormatter;

import controller.ContentPaneController;

import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;

import java.awt.Color;
import javax.swing.JRadioButton;

public class Panel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextArea outputArea;
	private CustomToggleButton executeButton;
	private JTextArea debuggerText;
	private JButton nextButton;
	private JSpinner millisecondi;
	private JRadioButton rdbtnAutoRun;
	private JButton endButton;

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
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{159, 55, 0, 0, 0, 74, 53, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{21, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		executeButton = new CustomToggleButton("start", Color.red, Color.green);
		executeButton.setActionCommand("START");
		executeButton.setFocusable(false);
		
		executeButton.setBackground(new Color(255, 255, 255));
		GridBagConstraints gbc_executeButton = new GridBagConstraints();
		gbc_executeButton.insets = new Insets(0, 0, 0, 5);
		gbc_executeButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_executeButton.gridx = 1;
		gbc_executeButton.gridy = 0;
		panel.add(executeButton, gbc_executeButton);
		
		endButton = new JButton("end");
		endButton.setActionCommand("END");
		endButton.setFocusable(false);
		GridBagConstraints gbc_endButton = new GridBagConstraints();
		gbc_endButton.insets = new Insets(0, 0, 0, 5);
		gbc_endButton.gridx = 2;
		gbc_endButton.gridy = 0;
		panel.add(endButton, gbc_endButton);
		
		JLabel lblNewLabel_2 = new JLabel("Millisecondi:");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_2.gridx = 5;
		gbc_lblNewLabel_2.gridy = 0;
		panel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		millisecondi = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 100));
		millisecondi.setToolTipText("");
		GridBagConstraints gbc_millisecondi = new GridBagConstraints();
		gbc_millisecondi.fill = GridBagConstraints.HORIZONTAL;
		gbc_millisecondi.insets = new Insets(0, 0, 0, 5);
		gbc_millisecondi.gridx = 6;
		gbc_millisecondi.gridy = 0;
		panel.add(millisecondi, gbc_millisecondi);
		
		rdbtnAutoRun = new JRadioButton("Auto run");
		rdbtnAutoRun.setFocusable(false);
		rdbtnAutoRun.setSelected(true);
		GridBagConstraints gbc_rdbtnAutoRun = new GridBagConstraints();
		gbc_rdbtnAutoRun.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnAutoRun.gridx = 7;
		gbc_rdbtnAutoRun.gridy = 0;
		panel.add(rdbtnAutoRun, gbc_rdbtnAutoRun);
		
//		rdbtnAutoRun.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				updateAutoRunState();
//				
//			}
//		});
		
		nextButton = new JButton("next");
		nextButton.setActionCommand("NEXT");
		GridBagConstraints gbc_nextButton = new GridBagConstraints();
		gbc_nextButton.gridx = 8;
		gbc_nextButton.gridy = 0;
		nextButton.setEnabled(false);
		panel.add(nextButton, gbc_nextButton);
		
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
		
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		//scroll pane velocity
		scrollPane.getVerticalScrollBar().setUnitIncrement(15);
        scrollPane.getHorizontalScrollBar().setUnitIncrement(15); 
		add(scrollPane, gbc_scrollPane);
		
		FlowChartContentPanel flowChartContenPane = new FlowChartContentPanel();
		scrollPane.setViewportView(flowChartContenPane);
		
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
		
//		updateAutoRunState();
	}
	
//	public void updateAutoRunState() {
//		if (rdbtnAutoRun.isSelected()) {
//			//auto run on
//			nextButton.setEnabled(false);
//			millisecondi.setEnabled(true);
//		}else {
//			//auto run off
//			nextButton.setEnabled(true);
//			millisecondi.setEnabled(false);
//		}
//	}

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
	
	public int getMilliseconds() {
		return (int)millisecondi.getValue();
	}
	
	public boolean isAutoRun() {
		return rdbtnAutoRun.isSelected();
	}

	public void registerEvents(ContentPaneController controller) {
		// TODO Auto-generated method stub
		executeButton.addActionListener(controller);
		endButton.addActionListener(controller);
		nextButton.addActionListener(controller);
		rdbtnAutoRun.addItemListener(controller);
	}

	public void setNextButtonUsable(boolean value) {
		// TODO Auto-generated method stub
		this.nextButton.setEnabled(value);
	}
	
	public void setMillisecondsUsable(boolean value) {
		// TODO Auto-generated method stub
		this.millisecondi.setEnabled(value);
	}

	public boolean getStartStatus() {
		return executeButton.isSelected();
	}
}
