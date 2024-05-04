package view;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.FlowChartController;
import model.Components.ComponentDeclaration;
import model.Components.ComponentEnd;
import model.Components.ComponentIf;
import model.Components.ComponentOutput;
import model.File.ImageLoader;
import model.Memory.RelationalOperators;
import view.editComponents.AddComponent;
import view.flowChartComponents.ParallelogramPanel;

public class Frame extends JFrame implements WindowListener{

	private static final long serialVersionUID = 1L;
	private Panel contentPane;

	public Frame() {
		setTitle("Coffee builder");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
	    setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new Panel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.addWindowListener(this);
		setContentPane(contentPane);
		
		//icon
		ImageIcon icon;
		
		ImageLoader imageLoader = new ImageLoader();
		
		icon = imageLoader.getImageFrom("resources/icon.png");
		
		setIconImage(icon.getImage());
	}

	public Panel getContentPanel() {
		// TODO Auto-generated method stub
		return this.contentPane;
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		
		
		int result = JOptionPane.showConfirmDialog(this, "Are you sure?", "Exit", JOptionPane.OK_CANCEL_OPTION);
		
		if(result==JOptionPane.OK_OPTION) {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}else {
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		}
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
