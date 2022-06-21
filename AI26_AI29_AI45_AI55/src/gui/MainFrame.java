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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import data.BrushData;
import data.EmployeeData;
import data.RenderData;
import data.SoftwareData;

public class MainFrame extends JFrame{
	
	private static MainFrame instance = null;
	
	private JToolBar mainToolbar;
	private JButton addButton;
	private JButton editButton;
	private JButton deleteButton;
	private JMenuBar menuBar;
	private JTabbedPane tabHolder;
	
	private EmployeeData employeeData;
	private SoftwareData softwareData;
	private BrushData brushData;
	private RenderData renderData;
	
	private SoftwareTab softwareTab;
	private EmployeeTab employeeTab;
	private BrushTab brushTab;
	private RenderTab renderTab;
	
	private MainFrame() {
		this.brushData = new BrushData();
		this.renderData = new RenderData();
		this.softwareData = new SoftwareData(this.renderData.getRenders(), this.brushData.getBrushes());
		this.employeeData = new EmployeeData(this.softwareData.getSoftwares());
		
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
				if (tabHolder.getSelectedIndex() == 1) {

					//System.out.println("Add Software");
					AddOrEditSoftwareForm addOrEditSoftwareForm = new AddOrEditSoftwareForm(softwareData.getSoftwares(),
							softwareTab.getTableModel(), brushData.getBrushes(), renderData.getRenders(), null, 0);

				} 
				else if (tabHolder.getSelectedIndex() == 0) {
					AddEmployeeForm addEmployeeForm = new AddEmployeeForm(softwareData.getSoftwares(),
							employeeTab.getTableModel(), employeeData.getEmployees(), null, 0);
					addEmployeeForm.setVisible(true);
				}
			}
		});
		
		
		ImageIcon editIcon = createImageIcon("images/edit2.png", true, 20, 20);
		editButton = new JButton();
		editButton.setIcon(editIcon);
		editButton.setToolTipText("Edit");
		editButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (tabHolder.getSelectedIndex() == 1) {
					if (softwareTab.getSelectedSoftwareIndex() >= 0) {
						AddOrEditSoftwareForm addOrEditSoftwareForm = new AddOrEditSoftwareForm(
								softwareData.getSoftwares(), softwareTab.getTableModel(), brushData.getBrushes(),
								renderData.getRenders(),
								softwareData.getSoftwares().get(softwareTab.getSelectedSoftwareIndex()),
								softwareTab.getSelectedSoftwareIndex());
					} else {
						JOptionPane.showMessageDialog(null, "You need to select row!", "ERROR",
								JOptionPane.ERROR_MESSAGE);
					}
				} else if (tabHolder.getSelectedIndex() == 0) {
					if (employeeTab.getSelectedEmployeeIndex() >= 0) {
						AddEmployeeForm addEmployeeForm = new AddEmployeeForm(softwareData.getSoftwares(),
								employeeTab.getTableModel(), employeeData.getEmployees(),
								employeeData.getEmployees().get(employeeTab.getSelectedEmployeeIndex()),
								employeeTab.getSelectedEmployeeIndex());
						addEmployeeForm.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "You need to select row!", "ERROR",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		ImageIcon deleteIcon = createImageIcon("images/trash.png", true, 20, 20);
		deleteButton = new JButton();
		deleteButton.setIcon(deleteIcon);
		deleteButton.setToolTipText("Delete");
		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (tabHolder.getSelectedIndex() == 1) {
					softwareTab.deleteSoftware();
				} else if (tabHolder.getSelectedIndex() == 0) {
					employeeTab.deleteEmployee();
				} else if (tabHolder.getSelectedIndex() == 2) {
					brushTab.deleteBrush();
				} else if (tabHolder.getSelectedIndex() == 3) {
					renderTab.deleteRender();
				}
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
		
		menuItemNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tabHolder.getSelectedIndex() == 1) {
					AddOrEditSoftwareForm addOrEditSoftwareForm = new AddOrEditSoftwareForm(softwareData.getSoftwares(),
							softwareTab.getTableModel(), brushData.getBrushes(), renderData.getRenders(), null, 0);
				} else if (tabHolder.getSelectedIndex() == 0) {
					AddEmployeeForm addEmployeeForm = new AddEmployeeForm(softwareData.getSoftwares(),
							employeeTab.getTableModel(), employeeData.getEmployees(), null, 0);
					addEmployeeForm.setVisible(true);
				}
			}
		});
		
		
		
		
		//create submenu for open
		ImageIcon openIcon = createImageIcon("images/open.png", true, 20,20);
		JMenu open = new JMenu("Open");
		file.add(open);
		JMenuItem subMenuItemEmployees = new JMenuItem("Employees", openIcon);
		JMenuItem subMenuItemSoftware = new JMenuItem("Software", openIcon);
		JMenuItem subMenuItemBrushes = new JMenuItem("Brushes", openIcon);
		JMenuItem subMenuItemRender = new JMenuItem("Render", openIcon);
		open.add(subMenuItemEmployees);
		open.add(subMenuItemSoftware);
		open.add(subMenuItemBrushes);
		open.add(subMenuItemRender);
		
		subMenuItemEmployees.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tabHolder.setSelectedIndex(0);

			}
		});

		subMenuItemSoftware.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tabHolder.setSelectedIndex(1);

			}
		});

		subMenuItemBrushes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tabHolder.setSelectedIndex(2);

			}
		});

		subMenuItemRender.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tabHolder.setSelectedIndex(3);

			}
		});
		
		
		ImageIcon crossIcon = createImageIcon("images/cross.png", true, 20,20);
		JMenuItem menuItemExit = new JMenuItem("Exit", crossIcon);
		file.add(menuItemExit);
		
		menuItemExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		
		//add items in Edit menu
		ImageIcon editIcon = createImageIcon("images/edit2.png", true, 20,20);
		JMenuItem menuItemEdit = new JMenuItem("Edit", editIcon);
		edit.add(menuItemEdit);
		
		menuItemEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tabHolder.getSelectedIndex() == 1) {
					if (softwareTab.getSelectedSoftwareIndex() >= 0) {
						AddOrEditSoftwareForm addOrEditSoftwareForm = new AddOrEditSoftwareForm(
								softwareData.getSoftwares(), softwareTab.getTableModel(), brushData.getBrushes(),
								renderData.getRenders(),
								softwareData.getSoftwares().get(softwareTab.getSelectedSoftwareIndex()),
								softwareTab.getSelectedSoftwareIndex());
					} else {
						JOptionPane.showMessageDialog(null, "You need to select row!", "ERROR",
								JOptionPane.ERROR_MESSAGE);
					}
				} else if (tabHolder.getSelectedIndex() == 0) {
					if (employeeTab.getSelectedEmployeeIndex() >= 0) {
						AddEmployeeForm addEmployeeForm = new AddEmployeeForm(softwareData.getSoftwares(),
								employeeTab.getTableModel(), employeeData.getEmployees(),
								employeeData.getEmployees().get(employeeTab.getSelectedEmployeeIndex()),
								employeeTab.getSelectedEmployeeIndex());
						addEmployeeForm.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "You need to select row!", "ERROR",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		ImageIcon deleteIcon = createImageIcon("images/delete.png", true, 20,20);
		JMenuItem menuItemDelete = new JMenuItem("Delete", deleteIcon);
		edit.add(menuItemDelete);
		
		menuItemDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tabHolder.getSelectedIndex() == 1) {
					softwareTab.deleteSoftware();
				} else if (tabHolder.getSelectedIndex() == 0) {
					employeeTab.deleteEmployee();
				} else if (tabHolder.getSelectedIndex() == 2) {
					brushTab.deleteBrush();
				} else if (tabHolder.getSelectedIndex() == 3) {
					renderTab.deleteRender();
				}
			}
		});
	
		//add items in Help menu
		ImageIcon aboutIcon = createImageIcon("images/about.png", true, 20,20);
		JMenuItem menuItemAbout = new JMenuItem("About", aboutIcon);
		help.add(menuItemAbout);
		
		menuItemAbout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, "OISISI Project aplication :)", "About",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		menuItemAbout.setAccelerator(KeyStroke.getKeyStroke('O', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
		menuItemEdit.setAccelerator(KeyStroke.getKeyStroke('E', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
		menuItemDelete.setAccelerator(KeyStroke.getKeyStroke('D', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
		menuItemExit.setAccelerator(KeyStroke.getKeyStroke('W', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
		menuItemNew.setAccelerator(KeyStroke.getKeyStroke('N', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
		subMenuItemEmployees.setAccelerator(KeyStroke.getKeyStroke('1', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
		subMenuItemSoftware.setAccelerator(KeyStroke.getKeyStroke('2', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
		subMenuItemBrushes.setAccelerator(KeyStroke.getKeyStroke('3', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
		subMenuItemRender.setAccelerator(KeyStroke.getKeyStroke('4', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
		
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

		this.employeeTab = new EmployeeTab(this.employeeData);
		this.tabHolder.add("Employees", employeeTab);

		this.softwareTab = new SoftwareTab(this.softwareData);
		this.tabHolder.add("Software", softwareTab);

		this.brushTab = new BrushTab(this.brushData);
		this.tabHolder.add("Brushes", brushTab);

		this.renderTab = new RenderTab(this.renderData);
		this.tabHolder.add("Renders", renderTab);

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
