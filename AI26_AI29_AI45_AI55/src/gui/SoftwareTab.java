package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.format.DateTimeFormatter;
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

import data.EmployeeData;
import data.SoftwareData;
import model.Brush;
import model.Employee;
import model.Software;

public class SoftwareTab extends JSplitPane {

	private SoftwareData softwareData;
	private JTable jTable;
	private DefaultTableModel tableModel;
	private JPanel tablePanel;
	private JPanel infoPanel;

	public SoftwareTab(SoftwareData softwareData) {
		this.softwareData = softwareData;

		this.tablePanel = new JPanel(new FlowLayout());
		this.infoPanel = new JPanel(new FlowLayout());

		this.setLeftComponent(tablePanel);
		this.setRightComponent(infoPanel);
		this.setOneTouchExpandable(false);
		this.setResizeWeight(0.25);// play with values to adjust split rate of left and right pannel

		fillSoftwareTable();
	}

	private void fillSoftwareTable() {

		String[] header = new String[] { "Name", "File Format", "Render" };
		Object[][] employees = new Object[this.softwareData.getSoftwares().size()][header.length];

		for (int i = 0; i < this.softwareData.getSoftwares().size(); i++) {

			Software software = this.softwareData.getSoftwares().get(i);
			employees[i][0] = software.getName();
			employees[i][1] = software.getFileFormat().toString();
			employees[i][2] = software.getRender().getName();
		}

		jTable = new JTable();
		tableModel = (DefaultTableModel) jTable.getModel();
		tableModel.setDataVector(employees, header);
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
					fillInfoPanel(softwareData.getSoftwares().get(row));
				}
			}

		});
	}

	private void fillInfoPanel(Software software) {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(3, 2));

		JLabel name, fileFormat, render;
		JTextField tname, tfileFormat, trender, tcolor;

		name = new JLabel("NAME");
		tname = new JTextField(20);
		tname.setEditable(false);
		tname.setText(software.getName());

		fileFormat = new JLabel("FILE FORMAT");
		tfileFormat = new JTextField(20);
		tfileFormat.setEditable(false);
		tfileFormat.setText(software.getFileFormat().toString());

		render = new JLabel("RENDER");
		trender = new JTextField(20);
		trender.setEditable(false);
		trender.setText(software.getRender().getName());

		tcolor = new JTextField(20);
		tcolor.setEditable(false);

		p.add(name);
		p.add(tname);

		p.add(fileFormat);
		p.add(tfileFormat);

		p.add(render);
		p.add(trender);

//		JScrollPane animationToolsPane = fillSoftwareTable(software.getAnimationTools());
		JScrollPane brushesPane = fillBrushesTable(software.getBrushes(), tcolor);

		this.infoPanel.add(p);
//		this.infoPanel.add(animationToolsPane);
		this.infoPanel.add(brushesPane);
		this.infoPanel.add(tcolor);

		this.setSize(400, 180);

	}

	private JScrollPane fillBrushesTable(ArrayList<Brush> brushes, JTextField colorField) {
		JTable jTable = new JTable();
		String[] header = new String[] { "BRUSHES" };
		Object[][] brushesData = new Object[brushes.size()][header.length];

		for (int i = 0; i < brushes.size(); i++) {
			brushesData[i][0] = brushes.get(i).getName();
		}

		DefaultTableModel tableModelSoftware;

		tableModelSoftware = (DefaultTableModel) jTable.getModel();
		tableModelSoftware.setDataVector(brushesData, header);
		jTable.setColumnSelectionAllowed(false);
		jTable.setRowSelectionAllowed(false);
		jTable.setDefaultEditor(Object.class, null);
		JScrollPane scrollPane = new JScrollPane(jTable);

		jTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					JTable target = (JTable) e.getSource();
					int row = target.getSelectedRow();
					int column = target.getSelectedColumn();
					colorField.setBackground(Brush.convertColor(brushes.get(row).getColor()));

				}
			}
		});

		Dimension d = new Dimension(500, 100);
		scrollPane.setPreferredSize(d);
		return scrollPane;
	}

	private JScrollPane fillSoftwareTable(ArrayList<String> animationTools) {
		JTable jTable = new JTable();
		String[] header = new String[] { "TOOL NAME" };
		Object[][] tools = new Object[animationTools.size()][header.length];

		for (int i = 0; i < animationTools.size(); i++) {
			tools[i][0] = animationTools.get(i);
		}

		DefaultTableModel tableModelSoftware;

		tableModelSoftware = (DefaultTableModel) jTable.getModel();
		tableModelSoftware.setDataVector(tools, header);
		jTable.setColumnSelectionAllowed(false);
		jTable.setRowSelectionAllowed(false);
		jTable.setDefaultEditor(Object.class, null);

		JScrollPane scrollPane = new JScrollPane(jTable);

		Dimension d = new Dimension(500, 100);
		scrollPane.setPreferredSize(d);
		return scrollPane;
	}

	public void deleteSoftware() {
		if (jTable.getSelectedRow() >= 0) { // Checking if row is selected
			int choice = JOptionPane.showConfirmDialog(null, "Do you want to proceed?", "Warning.",
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);// 0 =yes , 1 =no
			if (choice == 0) {
				this.softwareData.removeSoftwareFromList(jTable.getSelectedRow()); // pass selected row index that  corresponds to index of list
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
	
	public int getSelectedSoftwareIndex() {
		return this.jTable.getSelectedRow();
	}
}