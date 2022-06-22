package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import model.Adress;
import model.Employee;
import model.Software;
import model.enums.WorkPlace;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class AddEmployeeForm extends JFrame implements ActionListener {
	
	private Container c;
	private JLabel firstName, lastName, email, jmbg, street, number, city, dateOfBirth, lworkPlace, lsoftwares;
	private JTextField tfirstName, tlastName, temail, tjmbg, tstreet, tnumber, tcity;
	private JComboBox date, month, year, workPlace;
	private JList softwareList;
	private JButton createOrEdit, cancel;

	private String validationMessage = "";
	private ArrayList<Software> softwares;
	private DefaultTableModel employeTable;
	private ArrayList<Employee> employees;
	
	private Employee employeeToEdit;
	int employeeIndex;

	private String dates[] = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15",
			"16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
	private String months[] = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
	private String years[] = { "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991",
			"1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004",
			"2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017",
			"2018", "2019", "2020", "2021", "2022" };
	
	
	public AddEmployeeForm(ArrayList<Software> softwares, DefaultTableModel employeTable, ArrayList<Employee> employees,
			Employee employeeToEdit, int employeeIndex) {
		
		this.softwares = softwares; 
		this.employeTable = employeTable; 
		this.employees = employees;
		
		this.employeeToEdit = employeeToEdit; 
		this.employeeIndex = employeeIndex;
		
		String title = "Add Employee";
		if (this.employeeToEdit != null) {
			title = "Edit employee";
		}
		
		setTitle(title);
		setBounds(300, 90, 600, 850);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
	
		c = getContentPane();
		c.setLayout(null);
		
		firstName = new JLabel("First Name");	
		firstName.setSize(100, 20); 			
		firstName.setLocation(100, 100); 		 
		c.add(firstName);						

		tfirstName = new JTextField();			
		tfirstName.setSize(190, 20);			
		tfirstName.setLocation(200, 100);		
		c.add(tfirstName);
		
		lastName = new JLabel("Last Name");
		lastName.setSize(100, 20);
		lastName.setLocation(100, 150); 
		c.add(lastName);

		tlastName = new JTextField();
		tlastName.setSize(190, 20);
		tlastName.setLocation(200, 150);
		c.add(tlastName);

		jmbg = new JLabel("JMBG");
		jmbg.setSize(100, 20);
		jmbg.setLocation(100, 200);
		c.add(jmbg);

		tjmbg = new JTextField();
		tjmbg.setSize(190, 20);
		tjmbg.setLocation(200, 200);
		c.add(tjmbg);

		email = new JLabel("Email");
		email.setSize(100, 20);
		email.setLocation(100, 250);
		c.add(email);

		temail = new JTextField();
		temail.setSize(190, 20);
		temail.setLocation(200, 250);
		c.add(temail);

		lworkPlace = new JLabel("Work place");
		lworkPlace.setSize(100, 20);
		lworkPlace.setLocation(100, 300);
		c.add(lworkPlace);

		workPlace = new JComboBox(WorkPlace.values()); 
		workPlace.setSize(150, 20);
		workPlace.setLocation(200, 300);
		c.add(workPlace);
		
		dateOfBirth = new JLabel("Date of Birth");
		dateOfBirth.setSize(100, 20);
		dateOfBirth.setLocation(100, 350);
		c.add(dateOfBirth);

		date = new JComboBox(dates);
		date.setSize(50, 20);
		date.setLocation(200, 350);
		c.add(date);

		month = new JComboBox(months);
		month.setSize(60, 20);
		month.setLocation(250, 350);
		c.add(month);

		year = new JComboBox(years);
		year.setSize(60, 20);
		year.setLocation(320, 350);
		c.add(year);

		street = new JLabel("Street");
		street.setSize(100, 20);
		street.setLocation(100, 400);
		c.add(street);

		tstreet = new JTextField();
		tstreet.setSize(190, 20);
		tstreet.setLocation(200, 400);
		c.add(tstreet);

		number = new JLabel("Number");
		number.setSize(100, 20);
		number.setLocation(100, 450);
		c.add(number);

		tnumber = new JTextField();
		tnumber.setSize(190, 20);
		tnumber.setLocation(200, 450);
		c.add(tnumber);

		city = new JLabel("City");
		city.setSize(100, 20);
		city.setLocation(100, 500);
		c.add(city);

		tcity = new JTextField();
		tcity.setSize(190, 20);
		tcity.setLocation(200, 500);
		c.add(tcity);
		
		lsoftwares = new JLabel("Softwares (Hold CTRL for multi select)");
		lsoftwares.setSize(400, 20);
		lsoftwares.setLocation(100, 550);
		c.add(lsoftwares);
		
		softwareList = new JList(softwares.toArray());
		softwareList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		softwareList.setVisibleRowCount(4);
		JScrollPane sp = new JScrollPane(softwareList);
		sp.setSize(190, 100);
		sp.setLocation(150, 575);
		c.add(sp);
		
		
		createOrEdit = new JButton("OK");
		createOrEdit.setSize(200, 20);
		createOrEdit.setLocation(175, 700);
		createOrEdit.addActionListener(this);
		c.add(createOrEdit);

		cancel = new JButton("Cancel");
		cancel.setSize(200, 20);
		cancel.setLocation(175, 750);
		cancel.addActionListener(this);
		c.add(cancel);
		
		if (this.employeeToEdit != null) {
			fillFieldsWithEmployeeData(this.employeeToEdit);
		}
		
		
		setVisible(true);
		
	}
	
	private void fillFieldsWithEmployeeData(Employee employee) {
		this.tcity.setText(employee.getAdress().getCity());
		this.tstreet.setText(employee.getAdress().getStreet());
		this.tnumber.setText(employee.getAdress().getNumber());
		this.tfirstName.setText(employee.getFirstName());
		this.tlastName.setText(employee.getLastName());
		this.tjmbg.setText(String.valueOf(employee.getJmbg()));
		this.workPlace.setSelectedItem(employee.getWorkplace());
		this.temail.setText(employee.getEmail());
		
		int [] selectedSoftwareIndices = new int[employee.getSoftwares().size()];
		for (int i=0; i< employee.getSoftwares().size(); i++) {
			for (int j = 0; j < softwares.size(); j++) {
				if(softwares.get(j)==employee.getSoftwares().get(i)) {
					selectedSoftwareIndices[i] = j;
				}
			}
		}
		this.softwareList.setSelectedIndices(selectedSoftwareIndices);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == createOrEdit) { 
			
			validateFields();
		}
		
		if(e.getSource() == cancel) {
			this.setVisible(false);
			this.dispose();
		}

	}
	
	private void createOrEditEmployee() {
		
		Employee employee;
		
		if (this.employeeToEdit != null) {
			employee = this.employeeToEdit;
		} else {
			employee = new Employee();
		}
		
		Adress adress = new Adress();

		adress.setCity(tcity.getText());
		adress.setStreet(tstreet.getText());
		adress.setNumber(tnumber.getText());

		employee.setAdress(adress);
		employee.setEmail(temail.getText());
		employee.setFirstName(tfirstName.getText());
		employee.setLastName(tlastName.getText());
		employee.setJmbg(Integer.parseInt(tjmbg.getText()));
		employee.setWorkplace(WorkPlace.values()[workPlace.getSelectedIndex()]);
		String dayString = dates[date.getSelectedIndex()];
		String monthString = months[month.getSelectedIndex()];
		String yearString = years[year.getSelectedIndex()];
		String dateOfBirthString = dayString + "-" + monthString + "-" + yearString;
		LocalDate dateOfBirth = LocalDate.parse(dateOfBirthString, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		employee.setDateOfBirth(dateOfBirth);

		ArrayList<Software> softwares = new ArrayList<>();
		
		int[] softwareIndices = softwareList.getSelectedIndices();
		for (int i : softwareIndices) {
			softwares.add(this.softwares.get(i));
		}
		employee.setSoftwares(softwares);
		
		if (!isJmbgUnique(employee)) {
			this.validationMessage = "Employee with that JMBG already exists !";
			JOptionPane.showMessageDialog(null, this.validationMessage, "Invalid data", JOptionPane.WARNING_MESSAGE);
			this.validationMessage = "";
			return;

		}
		
		if (this.employeeToEdit == null) {
			
			this.employeTable.addRow(new Object[] { employee.getFirstName(), employee.getLastName(),
					employee.getEmail(), employee.getWorkplace() });
			
			this.employees.add(employee);
		} else {
			
			this.employeTable.removeRow(this.employeeIndex);
			
			this.employeTable.addRow(new Object[] { employee.getFirstName(), employee.getLastName(),
					employee.getEmail(), employee.getWorkplace() });
			
			this.employees.remove(this.employeeIndex);
			
			this.employees.add(employee);
		}
		
		setVisible(false); 
		dispose(); 
		
	}
	
	private void validateFields() {
		// check if fields are empty
		if (tfirstName.getText().trim().isEmpty())
			this.validationMessage = this.validationMessage + "You must enter first name ! \r\n";
		if (tlastName.getText().trim().isEmpty())
			this.validationMessage = this.validationMessage + "You must enter last name ! \r\n";
		if (tjmbg.getText().trim().isEmpty())
			this.validationMessage = this.validationMessage + "You must enter JMBG ! \r\n";
		if (!isJmbgNumber())
			this.validationMessage = this.validationMessage + "JMBG must be a number ! \r\n";
		if (tstreet.getText().trim().isEmpty())
			this.validationMessage = this.validationMessage + "You must enter street Name ! \r\n";
		if (tnumber.getText().trim().isEmpty())
			this.validationMessage = this.validationMessage + "You must enter street number ! \r\n";
		if (tcity.getText().trim().isEmpty())
			this.validationMessage = this.validationMessage + "You must enter city Name ! \r\n";
		if (softwareList.getSelectedIndices().length < 1)
			this.validationMessage = this.validationMessage + "You must choose at least 1 software for employee ! \r\n";
		if (this.validationMessage != "") { // check if we have any text in validationMessage and show errors if that is
											// the case
			JOptionPane.showMessageDialog(null, this.validationMessage, "Neispravni podaci",
					JOptionPane.WARNING_MESSAGE);
		} else {
			createOrEditEmployee();
		}
		this.validationMessage = ""; // reset for next try
	}
	
	private boolean isJmbgUnique(Employee employeeToCheck) {
		for (Employee employee : this.employees) {
			
			if(employee != employeeToCheck && employee.getJmbg()==employeeToCheck.getJmbg()) {
				return false;
			}
		}
		return true;
	}
	
	private boolean isJmbgNumber() {
		try {
			int jmbg = Integer.parseInt(tjmbg.getText());
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	

}
