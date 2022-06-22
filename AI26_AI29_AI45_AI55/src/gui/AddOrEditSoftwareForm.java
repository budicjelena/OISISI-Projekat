package gui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import model.Brush;
import model.Employee;
import model.Render;
import model.Software;
import model.enums.FileFormat;

public class AddOrEditSoftwareForm extends JFrame implements ActionListener {
	
	private Container c;
	private JLabel lname, lfileFormat, lrender, lbrushes;
	private JTextField tname;

	private JComboBox fileFormat, render;
	private JList brushesList;
	private JButton create, cancel;

	private ArrayList<Software> softwares;
	private ArrayList<Brush> brushes;
	private ArrayList<Render> renders;
	private DefaultTableModel softwareTable;
	private String validationMessage = "";
	
	private Software softwareToEdit;
	int softwareIndex;
	
	public AddOrEditSoftwareForm(ArrayList<Software> softwares, DefaultTableModel softwareTable,
			ArrayList<Brush> brushes, ArrayList<Render> renders, Software softwareToEdit, int softwareIndex) {
		
		this.softwares = softwares; 
		this.softwareTable = softwareTable; 
		this.brushes = brushes;
		this.renders = renders;
		
		this.softwareToEdit = softwareToEdit; 
		this.softwareIndex = softwareIndex;
		
		String title = "Add Software";
		if (this.softwareToEdit != null) {
			title = "Edit Software";
		}
		
		setTitle(title);
		setBounds(300, 90, 600, 600);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		
		c = getContentPane();
		c.setLayout(null);

		lname = new JLabel("Name"); 
		lname.setSize(100, 20); 
		lname.setLocation(100, 100); 
		c.add(lname); 

		tname = new JTextField();
		tname.setSize(190, 20); 
		tname.setLocation(200, 100); 
		c.add(tname); 

		lfileFormat = new JLabel("File format");
		lfileFormat.setSize(100, 20);
		lfileFormat.setLocation(100, 150);
		c.add(lfileFormat);
		
		fileFormat = new JComboBox(FileFormat.values());
		fileFormat.setSize(150, 20);
		fileFormat.setLocation(200, 150);
		c.add(fileFormat);

		lrender = new JLabel("Render");
		lrender.setSize(100, 20);
		lrender.setLocation(100, 200);
		c.add(lrender);
		
		ArrayList<String> renderNames = new ArrayList<>();
		for (Render render : this.renders) {
			renderNames.add(render.getName());
		}
		render = new JComboBox(renderNames.toArray()); 
		render.setSize(150, 20);
		render.setLocation(200, 200);
		c.add(render);

		lbrushes = new JLabel("Brushes (Hold CTRL for multi select)");
		lbrushes.setSize(400, 20);
		lbrushes.setLocation(100, 250);
		c.add(lbrushes);

		brushesList = new JList(brushes.toArray());
		brushesList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		brushesList.setVisibleRowCount(4);
		JScrollPane sp = new JScrollPane(brushesList);
		sp.setSize(190, 100);
		sp.setLocation(100, 275);
		c.add(sp);
		
		create = new JButton("OK");
		create.setSize(200, 20);
		create.setLocation(175, 400);
		create.addActionListener(this);
		c.add(create);

		cancel = new JButton("Cancel");
		cancel.setSize(200, 20);
		cancel.setLocation(175, 450);
		cancel.addActionListener(this);
		c.add(cancel);

		
		if (this.softwareToEdit != null) {
			fillFieldsWithSoftwareData(this.softwareToEdit);
		}
		
		
		setVisible(true);
	}
	
	
	private void fillFieldsWithSoftwareData(Software software) {
		this.tname.setText(software.getName());
		this.fileFormat.setSelectedItem(software.getFileFormat());
		int selectedRenderIndex = -1;
		for (int i = 0; i < renders.size(); i++) {
			if (renders.get(i) == software.getRender()) {
				selectedRenderIndex = i;
			}
		}
		this.render.setSelectedIndex(selectedRenderIndex);
		int[] selectedBrushesIndices = new int[software.getBrushes().size()];
		for (int i = 0; i < software.getBrushes().size(); i++) {
			for (int j = 0; j < brushes.size(); j++) {
				if (brushes.get(j) == software.getBrushes().get(i)) {
					selectedBrushesIndices[i] = j;
				}
			}
		}
		this.brushesList.setSelectedIndices(selectedBrushesIndices);
	}
	
	
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == create) { 
			validateFields();
		}

		if (e.getSource() == cancel) {
			this.setVisible(false);
			this.dispose();
		}
	}
	
	private void createOrEditSoftware() {
		
		Software software;
		if (this.softwareToEdit != null) {
			software = this.softwareToEdit;
		} else {
			software = new Software();
		}
		
		software.setName(this.tname.getText());
		software.setFileFormat(FileFormat.values()[fileFormat.getSelectedIndex()]);
		software.setRender(renders.get(render.getSelectedIndex()));

		ArrayList<Brush> brushes = new ArrayList<>();
		
		int[] brushIndices = brushesList.getSelectedIndices();
		for (int i : brushIndices) {
			brushes.add(this.brushes.get(i));
		}
		software.setBrushes(brushes);
		
		if (!isNameUnique(software)) {
			this.validationMessage = "Software with that name already exists !";
			JOptionPane.showMessageDialog(null, this.validationMessage, "Invalid data",
					JOptionPane.WARNING_MESSAGE);
			this.validationMessage = "";
			return;
		}

		if (this.softwareToEdit == null) {
			
			this.softwareTable.addRow(new Object[] { software.getName(), software.getFileFormat().toString(),
					software.getRender().getName() });
		
			this.softwares.add(software); 
		} else {
			
			this.softwareTable.removeRow(this.softwareIndex);
			
			this.softwareTable.addRow(new Object[] { software.getName(), software.getFileFormat().toString(),
					software.getRender().getName() });
			
			this.softwares.remove(this.softwareIndex);
			
			this.softwares.add(software);
		}
		
		setVisible(false);
		dispose();
	
	}	
	
	private void validateFields() {
		
		if (tname.getText().trim().isEmpty())
			this.validationMessage = this.validationMessage + "You must enter name ! \r\n";
		if (this.validationMessage != "") { 
											
			JOptionPane.showMessageDialog(null, this.validationMessage, "Invalid data",
					JOptionPane.WARNING_MESSAGE);
		} else {
			createOrEditSoftware();
		}
		this.validationMessage = ""; 
	}
		
		
		
	private boolean isNameUnique(Software softwareToCheck) {
		for (Software software : this.softwares) {

			if (software != softwareToCheck && software.getName().equals(softwareToCheck.getName())) {
				return false;
			}
		}
		return true;
	}

}
