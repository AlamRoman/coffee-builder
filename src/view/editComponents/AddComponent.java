package view.editComponents;

import java.awt.Container;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class AddComponent extends JDialog{

	private JList list;
	private DefaultListModel<String> model;
	private ArrayList<String> componentNames;
	
	private String result=null;
	
	public AddComponent(JFrame parent) {
		super(parent, "Add new", true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		componentNames = new ArrayList<String>();
		
		componentNames.add("Assign");
		componentNames.add("Declaration");
		componentNames.add("Operation");
		componentNames.add("Input");
		componentNames.add("Output");
		componentNames.add("If");
		componentNames.add("While");
		componentNames.add("Comment");
		
		model = new DefaultListModel<>();
		model.clear();
		model.addAll(componentNames);
		list = new JList(model);
		
		list.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				
				if (!e.getValueIsAdjusting()) {
					result = (String) list.getSelectedValue();
					dispose();
				}
				
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(list);
		
		getContentPane().add(scrollPane);
		
		setLocationRelativeTo(parent);
		pack();
	}
	
	public String showAddWindow() {
		setVisible(true);
		return result;
	}
	
	public static void main(String[] args) {
		AddComponent ac = new AddComponent(null);
		
		String r = ac.showAddWindow();
		
		System.out.println(r);
	}
}
