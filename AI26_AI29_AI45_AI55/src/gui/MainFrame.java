package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import data.EmployeeData;

public class MainFrame extends JFrame{
	
	private static MainFrame instance = null;
	
	private JToolBar mainToolbar;
	private JButton addButton;
	private JButton editButton;
	private JButton deleteButton;
	private JMenuBar menuBar;
	private JTabbedPane tabHolder;
	private EmployeeData employeeData;
	
	
	
	private MainFrame() {
		this.employeeData = new EmployeeData();
		this.createMenubar();
		this.createToolbar();
		this.createStatusBar();
		this.createCentralPanel();
		this.initPosition();
	}
	
	//singleton pattern sa vezbi
	public static MainFrame getInstance() {
		if(instance == null) {
			instance = new MainFrame();
		}
		return instance;
	}
	
	private void initPosition() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = (int) (screenSize.getWidth() * 0.75); // 3/4
		int screenHeight = (int) (screenSize.getHeight() * 0.75);
		this.setSize(screenWidth, screenHeight);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	private void createToolbar() {

		this.mainToolbar = new JToolBar(JToolBar.HORIZONTAL);
		this.mainToolbar.setFloatable(false);

		ImageIcon plusIcon = createImageIcon("images/plus.png", true, 20, 20);
		addButton = new JButton();
		addButton.setIcon(plusIcon);
		addButton.setToolTipText("Add");
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Add");
			}
		});
		
		
		ImageIcon editIcon = createImageIcon("images/edit2.png", true, 20, 20);
		editButton = new JButton();
		editButton.setIcon(editIcon);
		editButton.setToolTipText("Edit");
		editButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Edit");
			}
		});
		
		ImageIcon deleteIcon = createImageIcon("images/trash.png", true, 20, 20);
		deleteButton = new JButton();
		deleteButton.setIcon(deleteIcon);
		deleteButton.setToolTipText("Delete");
		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Delete");
			}
		});
		
		this.mainToolbar.add(addButton);
		this.mainToolbar.add(editButton);
		this.mainToolbar.add(deleteButton);
		this.add(this.mainToolbar, BorderLayout.NORTH);

	}
	
	private void createMenubar() {
		//create menu bar
		this.menuBar = new JMenuBar();
		
		//create menus on the bar
		JMenu file = new JMenu("File");
		this.menuBar.add(file);
		
		JMenu edit = new JMenu("Edit");
		this.menuBar.add(edit);
		
		JMenu help = new JMenu("Help");
		this.menuBar.add(help);
		
		//add items in File menu
		ImageIcon plusIcon = createImageIcon("images/plus.png", true, 20,20);
		JMenuItem menuItemNew = new JMenuItem("New", plusIcon);
		file.add(menuItemNew);
		
		//create submenu for open
		ImageIcon openIcon = createImageIcon("images/open.png", true, 20,20);
		JMenu open = new JMenu("Open");
		file.add(open);
		JMenuItem subMenuItemEmployees = new JMenuItem("Employees", openIcon);
		JMenuItem subMenuItemSoftware = new JMenuItem("Software", openIcon);
		open.add(subMenuItemEmployees);
		open.add(subMenuItemSoftware);
		
		ImageIcon crossIcon = createImageIcon("images/cross.png", true, 20,20);
		JMenuItem menuItemExit = new JMenuItem("Exit", crossIcon);
		file.add(menuItemExit);
		
		//add items in Edit menu
		ImageIcon editIcon = createImageIcon("images/edit2.png", true, 20,20);
		JMenuItem menuItemEdit = new JMenuItem("Edit", editIcon);
		edit.add(menuItemEdit);
		
		ImageIcon deleteIcon = createImageIcon("images/delete.png", true, 20,20);
		JMenuItem menuItemDelete = new JMenuItem("Delete", deleteIcon);
		edit.add(menuItemDelete);
		
		//add items in Help menu
		ImageIcon aboutIcon = createImageIcon("images/about.png", true, 20,20);
		JMenuItem menuItemAbout = new JMenuItem("About", aboutIcon);
		help.add(menuItemAbout);
		
		menuItemAbout.setAccelerator(KeyStroke.getKeyStroke('N', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
		setJMenuBar(this.menuBar);
		
	}
	
	private void createStatusBar() {

		JPanel statusPanel = new JPanel();
		this.add(statusPanel, BorderLayout.SOUTH);
		statusPanel.setPreferredSize(new Dimension(this.getWidth(), 25));
		statusPanel.setLayout(new BorderLayout());
		
		LocalDate date = LocalDate.now();
		JLabel statusLabel = new JLabel("Date:  " + date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")).toString()+"  ",SwingConstants.LEFT);
		statusLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		statusPanel.add(statusLabel,BorderLayout.EAST);
	}
	
	
	private void createCentralPanel() {
		this.tabHolder = new JTabbedPane();
		
		EmployeeTab employeeTab = new EmployeeTab(this.employeeData);
		this.tabHolder.add("Employees",employeeTab);

		SoftwareTab softwareTab1 = new SoftwareTab();		
		this.tabHolder.add("Software",softwareTab1);
		
		this.add(this.tabHolder, BorderLayout.CENTER);
		
	}
	
	
	protected static ImageIcon createImageIcon(String path, boolean scaleImage, int width, int height) {
		if (scaleImage) {
			//how to scale an image
			ImageIcon imageIcon = new ImageIcon(path); //load the image to a imageIcon
			Image image = imageIcon.getImage(); //transform
			Image newimg = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH); //scale it smoothly
			
			imageIcon = new ImageIcon(newimg); //transform it back
			return imageIcon;
			
		}else {
			return new ImageIcon(path);
		}
	}

}
