package gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import data.BrushData;
import model.Brush;


public class BrushTab extends JSplitPane {
	
	private BrushData brushData;
	private JTable jTable;
	private DefaultTableModel tableModel;
	private JPanel tablePanel;
	private JPanel infoPanel;
	
	public BrushTab(BrushData brushData) {
		this.brushData = brushData;
		
		this.tablePanel = new JPanel(new FlowLayout());
		this.infoPanel = new JPanel(new FlowLayout());
		
		this.setLeftComponent(tablePanel);
		this.setRightComponent(infoPanel);
		this.setOneTouchExpandable(false);
		this.setResizeWeight(0.25);
		
		fillBrushTable();
	}
	
	public void fillBrushTable() {
		String[] header = new String[] {"Name", "Purpose"};
		Object[][] brushes = new Object[this.brushData.getBrushes().size()][header.length];
		
		for(int i = 0; i < this.brushData.getBrushes().size(); i++) {
			
			Brush brush = this.brushData.getBrushes().get(i);
			brushes[i][0] = brush.getName();
			brushes[i][1] = brush.getPurpose();
			
		}
		
		jTable = new JTable();
		tableModel = (DefaultTableModel) jTable.getModel();
		tableModel.setDataVector(brushes, header);
		jTable.setRowSelectionAllowed(true);
		jTable.setColumnSelectionAllowed(false);
		jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jTable.setDefaultEditor(Object.class, null);
		JScrollPane scrollPane = new JScrollPane(jTable);
		this.tablePanel.add(scrollPane);
		
		jTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					JTable target = (JTable) e.getSource();
					int row = target.getSelectedRow();
					infoPanel.removeAll();
					fillInfoPanel(brushData.getBrushes().get(row));
				}
			}
		});
		
	}
	
	private void fillInfoPanel(Brush brush) {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(3,2));
		
		JLabel name, purpose, color;
		JTextField tname, tpurpose, tcolor;
		
		name = new JLabel("NAME");
		tname = new JTextField(20);
		tname.setEditable(false);
		tname.setText(brush.getName());
		
		purpose = new JLabel("PURPOSE");
		tpurpose = new JTextField(20);
		tpurpose.setEditable(false);
		tpurpose.setText(brush.getPurpose());
		
		color = new JLabel("COLOR");
		tcolor = new JTextField(20);
		tcolor.setEditable(false);
		tcolor.setText("");
		tcolor.setBackground(Brush.convertColor(brush.getColor()));
		
		p.add(name);
		p.add(tname);
		
		p.add(purpose);
		p.add(tpurpose);
		
		p.add(color);
		p.add(tcolor);
		
		this.infoPanel.add(p);
		this.setSize(400, 180);
		
	}
	
	public void deleteBrush() {
		if(jTable.getSelectedRow() >= 0) { //checking if row is selected
			int choice = JOptionPane.showConfirmDialog(null, "Do you want to proceed?", "Warning.", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE); // 0 = yes 1 = no
			
			if(choice == 0) {
				this.brushData.removeBrushFromList(jTable.getSelectedRow()); //pass selected row index that
																				//corresponds to index of list
				tableModel.removeRow(jTable.getSelectedRow()); //remove row from table
				this.infoPanel.removeAll(); //remove info panel of deleted software
				this.repaint();//need this to make visual change
				
			}
		} else {
			//show error message if no row is selected
			JOptionPane.showMessageDialog(null, "You need to select a row!", "ERROR", JOptionPane.ERROR_MESSAGE);
		} 
	}
	
	public DefaultTableModel getTableModel() {
		return this.tableModel;
	}
	
	public int getSelectedBrushIndex() {
		return this.jTable.getSelectedRow();
	}

	public JTable getTable() {
		return this.jTable;
	}
}
