package view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.AbstractButton;
import javax.swing.plaf.basic.BasicButtonUI;

import model.Memory.MemoryStorage;

/**
 * A custom toggle button used for the Start/End button in the {@link Panel}
 * */
public class CustomToggleButton extends JToggleButton {

	private Color selectedColor;
	private Color defaultColor;
	
	/**<p>
	* The constructor of the class {@link CustomToggleButton}
	* </p>
	* @param text The text contained in the button
	* @param defaultColor The default colorm (when not selected)
	* @param selectedColor The color when the button is selected
	*/
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
	
	/**
	 * <p>
	 * This method toggles the selection status of the button
	 * </p>
	 * */
	public void toggleSelected() {
		this.setSelected(!this.isSelected());
//		super.revalidate();
		super.repaint();
	}
	
}
