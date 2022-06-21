package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;


import data.RenderData;
import model.Camera;
import model.Material;
import model.Render;

public class RenderTab extends JSplitPane {

	private RenderData renderData;
	private JTable jTable;
	private DefaultTableModel tableModel;
	private JPanel tablePanel;
	private JPanel infoPanel;

	public RenderTab(RenderData renderData) {
		this.renderData = renderData;

		this.tablePanel = new JPanel(new FlowLayout());
		this.infoPanel = new JPanel(new FlowLayout());

		this.setLeftComponent(tablePanel);
		this.setRightComponent(infoPanel);
		this.setOneTouchExpandable(false);
		this.setResizeWeight(0.25);// play with values to adjust split rate of left and right pannel

		fillRenderTable();
	}

	public void fillRenderTable() {
		String[] header = new String[] { "Name" };
		Object[][] renders = new Object[this.renderData.getRenders().size()][header.length];

		for (int i = 0; i < this.renderData.getRenders().size(); i++) {

			Render render = this.renderData.getRenders().get(i);
			renders[i][0] = render.getName();

		}

		jTable = new JTable();
		tableModel = (DefaultTableModel) jTable.getModel();
		tableModel.setDataVector(renders, header);
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
					int column = target.getSelectedColumn();
					infoPanel.removeAll();
					fillInfoPanel(renderData.getRenders().get(row));
				}
			}
		});
	}

	private void fillInfoPanel(Render  render) {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(1, 2));

		JLabel name;
		JTextField tname;

		name = new JLabel("NAME");
		tname = new JTextField(20);
		tname.setEditable(false);
		tname.setText(render.getName());



		p.add(name);
		p.add(tname);

		JScrollPane spCameras = fillCamerasTable(render.getCameras());
		JScrollPane spMaterials = fillMaterialsTable(render.getMaterials());
		JScrollPane spObjects = fillObjectsTable(render.getObjects());


		this.infoPanel.add(p);
		this.infoPanel.add(spCameras);
		this.infoPanel.add(spMaterials);
		this.infoPanel.add(spObjects);

		this.setSize(400, 540);
	}

	private JScrollPane fillMaterialsTable(ArrayList<Material> materials) {
		JTable jTable = new JTable();
		String[] header = new String[] { "MATERIALS" };
		Object[][] employees = new Object[materials.size()][header.length];

		for (int i = 0; i < materials.size(); i++) {
			employees[i][0] = materials.get(i).toString();
		}

		DefaultTableModel tableModelSoftware;

		tableModelSoftware = (DefaultTableModel) jTable.getModel();
		tableModelSoftware.setDataVector(employees, header);
		jTable.setColumnSelectionAllowed(false);
		jTable.setRowSelectionAllowed(false);
		jTable.setDefaultEditor(Object.class, null);
		jTable.setPreferredScrollableViewportSize(new Dimension(400, 50));
		JScrollPane scrollPane = new JScrollPane(jTable);
		return scrollPane;

	}
	
	private JScrollPane fillObjectsTable(ArrayList<model.Object> objects) {
		JTable jTable = new JTable();
		String[] header = new String[] { "OBJECTS" };
		Object[][] employees = new Object[objects.size()][header.length];

		for (int i = 0; i < objects.size(); i++) {
			employees[i][0] = objects.get(i).toString();
		}

		DefaultTableModel tableModelSoftware;

		tableModelSoftware = (DefaultTableModel) jTable.getModel();
		tableModelSoftware.setDataVector(employees, header);
		jTable.setColumnSelectionAllowed(false);
		jTable.setRowSelectionAllowed(false);
		jTable.setDefaultEditor(Object.class, null);
		jTable.setPreferredScrollableViewportSize(new Dimension(400, 50));

		JScrollPane scrollPane = new JScrollPane(jTable);
		return scrollPane;

	}
	
	private JScrollPane fillCamerasTable(ArrayList<Camera> cameras) {
		JTable jTable = new JTable();
		String[] header = new String[] { "CAMERAS" };
		Object[][] employees = new Object[cameras.size()][header.length];

		for (int i = 0; i < cameras.size(); i++) {
			employees[i][0] = cameras.get(i).toString();
		}

		DefaultTableModel tableModelSoftware;

		tableModelSoftware = (DefaultTableModel) jTable.getModel();
		tableModelSoftware.setDataVector(employees, header);
		jTable.setColumnSelectionAllowed(false);
		jTable.setRowSelectionAllowed(false);
		jTable.setDefaultEditor(Object.class, null);
		jTable.setPreferredScrollableViewportSize(new Dimension(400, 50));

		JScrollPane scrollPane = new JScrollPane(jTable);
		return scrollPane;

	}
	
	
	public void deleteRender() {
		if (jTable.getSelectedRow() >= 0) { // Checking if row is selected
			int choice = JOptionPane.showConfirmDialog(null, "Do you want to proceed?", "Warning.",
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);// 0 =yes , 1 =no

			if (choice == 0) {
				this.renderData.removeRenderFromList(jTable.getSelectedRow()); // pass selected row index that
																				// corresponds to index of list
				tableModel.removeRow(jTable.getSelectedRow());// remove row from table
				this.infoPanel.removeAll(); // remove info panel of deleted software
				this.repaint(); // need this to make visual change
			}
		} else {
			// show error message if no row is selected
			JOptionPane.showMessageDialog(null, "You need to select row!", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}

	public DefaultTableModel getTableModel() {
		return this.tableModel;
	}

	public int getSelectedRenderIndex() {
		return this.jTable.getSelectedRow();
	}

	public JTable getTable() {
		return this.jTable;
	}
}