package view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.AbstractButton;
import javax.swing.plaf.basic.BasicButtonUI;

public class CustomToggleButton extends JToggleButton {

	private Color selectedColor;
	private Color defaultColor;
	
	public CustomToggleButton(String text, Color defaultColor ,Color selectedColor) {
		super(text);
		this.selectedColor = selectedColor;
		this.defaultColor = defaultColor;
		setBackground(defaultColor);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		if(isSelected()) {
			setForeground(selectedColor);
		}else {
			setForeground(defaultColor);
		}
		super.paintComponent(g);
	}
	
	
}
