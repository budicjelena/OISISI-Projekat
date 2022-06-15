package gui;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class SoftwareTab extends JSplitPane {

	public SoftwareTab() {
		JPanel buttonPanel = new JPanel(new FlowLayout());
		JPanel textBoxPanel = new JPanel(new FlowLayout());
		
		this.setLeftComponent(buttonPanel);
		this.setRightComponent(textBoxPanel);
		this.setOneTouchExpandable(false);
		this.setResizeWeight(0.5);// this is to make it 50%/50% split left-right side

		String[][] data = { { "TEST 1" }, { "TEST 2" } };
		String[] columnNames = { "Name"};
		
		
		JTable j = new JTable(data, columnNames);
        j.setBounds(30, 40, 200, 300);
        
        JScrollPane sp = new JScrollPane(j);
        buttonPanel.add(sp);
	}

}
