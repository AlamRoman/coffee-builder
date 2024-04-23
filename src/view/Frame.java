package view;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.FlowChartController;
import model.Components.ComponentDeclaration;
import model.Components.ComponentEnd;
import model.Components.ComponentIf;
import model.Components.ComponentOutput;
import model.Memory.RelationalOperators;
import view.editComponents.AddComponent;
import view.flowChartComponents.ParallelogramPanel;

public class Frame extends JFrame implements WindowListener{

	private static final long serialVersionUID = 1L;
	private Panel contentPane;

//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Frame frame = new Frame();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	public Frame() {
		setTitle("Coffee builder");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
	    setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new Panel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.addWindowListener(this);
		setContentPane(contentPane);
	}
	
	public static void main1(String[] args) {
		
		Frame ciao = new Frame();
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
		
		
		int scelta= JOptionPane.showConfirmDialog(this, "Are you sure?", "Exit", JOptionPane.OK_CANCEL_OPTION);
		
		if(scelta==JOptionPane.OK_OPTION) {
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
