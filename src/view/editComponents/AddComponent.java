package view.editComponents;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.File.ImageLoader;

public class AddComponent extends JDialog{

	private JList list;
	private DefaultListModel<String> model;
	private ArrayList<String> componentNames;
	
	private String result=null;
	
	public AddComponent(JFrame parent) {
		super(parent, "Add new", true);
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
		list = new JList<>(model);
		MyListCellRenderer cellRenderer = new MyListCellRenderer();
		list.setCellRenderer(cellRenderer);
		list.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int index = list.locationToIndex(e.getPoint());
                cellRenderer.setHoverIndex(index); // Imposta l'indice della cella sopra cui si trova il mouse
                list.repaint(); // Ridisegna la lista per aggiornare l'aspetto delle celle
            }
        });

		
		list.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				
				if (!e.getValueIsAdjusting()) {
					result = (String) list.getSelectedValue();
					dispose();
				}
				
			}
		});
		
		ImageLoader imageLoader = new ImageLoader();
		ImageIcon icon;
		
		icon = imageLoader.getImageFrom("resources/add.png");
		
		setIconImage(icon.getImage());
		
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
	
	private class MyListCellRenderer extends DefaultListCellRenderer {
        private static final long serialVersionUID = 1L;

        private int hoverIndex = -1; // Indice della cella sopra cui si trova il mouse

        public void setHoverIndex(int index) {
            hoverIndex = index;
        }

        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
                boolean cellHasFocus) {
            Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            // Imposta il colore di sfondo delle celle
            if (index == hoverIndex) {
                c.setBackground(new Color(230, 230, 255)); // Colore di sfondo quando il mouse è sopra la cella
            } else if (index % 2 == 0) {
                c.setBackground(new Color(240, 240, 240)); // Colore sfondo per le righe pari
            } else {
                c.setBackground(Color.WHITE); // Colore sfondo per le righe dispari
            }

            // Imposta il font e l'allineamento del testo
            setFont(new Font("Arial", Font.BOLD, 14));
            setHorizontalAlignment(SwingConstants.CENTER);

            // Aggiungi bordo inferiore a tutte le celle tranne all'ultima
            MatteBorder border = BorderFactory.createMatteBorder(0, 0, index == list.getModel().getSize() - 1 ? 0 : 1, 0,
                    Color.LIGHT_GRAY);
            setBorder(BorderFactory.createCompoundBorder(getBorder(), border));

            return c;
        }
    }
}
