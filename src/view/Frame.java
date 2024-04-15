package view;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.FlowChartController;
import model.Components.ComponentDeclaration;
import model.Components.ComponentEnd;
import model.Components.ComponentIf;
import model.Components.ComponentOutput;
import model.Memory.RelationalOperators;
import view.flowChartComponents.ParallelogramPanel;

public class Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame frame = new Frame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Frame() {
		setTitle("Coffee builder");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
	    setExtendedState(JFrame.MAXIMIZED_BOTH);
//	    ComponentOutput output = new ComponentOutput(null, null, null);
//	    output.set("ciao");
//		contentPane = new ParallelogramPanel(output, new FlowChartController());
		contentPane = new Panel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
	}
	
	public static void main1(String[] args) {
		
		Frame ciao = new Frame();
		}

}
