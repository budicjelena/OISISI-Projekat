package gui;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
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
import model.Employee;
import model.Software;

public class EmployeeTab extends JSplitPane {
	
	private EmployeeData employeeData;
	private JTable jTable;
	private DefaultTableModel tableModel;
	private JPanel tablePanel;
	private JPanel infoPanel;
	
	public EmployeeTab(EmployeeData employeeData) {
		this.employeeData = employeeData;

		this.tablePanel = new JPanel(new FlowLayout());
		this.infoPanel = new JPanel(new FlowLayout());

		this.setLeftComponent(tablePanel);
		this.setRightComponent(infoPanel);
		this.setOneTouchExpandable(false);
		this.setResizeWeight(0.25);

		fillEmployeeTable();
	}
	
	public void fillEmployeeTable() {
		String[] header = new String[] { "First Name", "Last Name", "EMAIL", "Position" };
		Object[][] employees = new Object[this.employeeData.getEmployees().size()][header.length];

		for (int i = 0; i < this.employeeData.getEmployees().size(); i++) {

			Employee employee = this.employeeData.getEmployees().get(i);
			employees[i][0] = employee.getFirstName();
			employees[i][1] = employee.getLastName();
			employees[i][2] = employee.getEmail();
			employees[i][3] = employee.getWorkplace();
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
					fillInfoPanel(employeeData.getEmployees().get(row));
				}
			}
		});
	}
	
	private void fillInfoPanel(Employee employee) {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(7, 2));

		JLabel firstName, lastName, email, adress, workplace, jmbg, dateOfBirth;
		JTextField tfirstName, tlastName, temail, tadress, tworkplace, tjmbg, tdateOfBirth;

		firstName = new JLabel("FIRST NAME");
		tfirstName = new JTextField(20);
		tfirstName.setEditable(false);
		tfirstName.setText(employee.getFirstName());

		lastName = new JLabel("LAST NAME");
		tlastName = new JTextField(20);
		tlastName.setEditable(false);
		tlastName.setText(employee.getLastName());

		email = new JLabel("EMAIL");
		temail = new JTextField(20);
		temail.setEditable(false);
		temail.setText(employee.getEmail());

		adress = new JLabel("ADRESS");
		tadress = new JTextField(20);
		tadress.setEditable(false);
		tadress.setText(employee.getAdress().toString());

		workplace = new JLabel("WORKPLACE");
		tworkplace = new JTextField(20);
		tworkplace.setEditable(false);
		tworkplace.setText(employee.getWorkplace().toString());

		jmbg = new JLabel("JMBG");
		tjmbg = new JTextField(20);
		tjmbg.setEditable(false);
		tjmbg.setText(String.valueOf(employee.getJmbg()));

		dateOfBirth = new JLabel("DATE OF BIRTH");
		tdateOfBirth = new JTextField(20);
		tdateOfBirth.setEditable(false);
		tdateOfBirth.setText(employee.getDateOfBirth().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));

		p.add(firstName);
		p.add(tfirstName);

		p.add(lastName);
		p.add(tlastName);

		p.add(email);
		p.add(temail);

		p.add(adress);
		p.add(tadress);

		p.add(workplace);
		p.add(tworkplace);

		p.add(jmbg);
		p.add(tjmbg);

		p.add(dateOfBirth);
		p.add(tdateOfBirth);

		JScrollPane sp = fillSoftwareTable(employee.getSoftwares());

		this.infoPanel.add(p);
		this.infoPanel.add(sp);
		this.setSize(400, 180);
	}
	
	private JScrollPane fillSoftwareTable(ArrayList<Software> software) {
		JTable jTable = new JTable();
		String[] header = new String[] { "SOFTWARE NAME" };
		Object[][] employees = new Object[software.size()][header.length];

		for (int i = 0; i < software.size(); i++) {
			employees[i][0] = software.get(i).getName();
		}

		DefaultTableModel tableModelSoftware;

		tableModelSoftware = (DefaultTableModel) jTable.getModel();
		tableModelSoftware.setDataVector(employees, header);
		jTable.setColumnSelectionAllowed(false);
		jTable.setRowSelectionAllowed(false);
		jTable.setDefaultEditor(Object.class, null);

		JScrollPane scrollPane = new JScrollPane(jTable);
		return scrollPane;

	}
	
	public void deleteEmployee() {
		if (jTable.getSelectedRow() >= 0) { // Checking if row is selected
			int choice = JOptionPane.showConfirmDialog(null, "Do you want to proceed?", "Warning.",
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);// 0 =yes , 1 =no

			if (choice == 0) {
				this.employeeData.removeEmployeeFromList(jTable.getSelectedRow()); // pass selected row index that
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
	
	public int getSelectedEmployeeIndex() {
		return this.jTable.getSelectedRow();
	}
	
	public JTable getTable() {
		return this.jTable;
	}
	

}
