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

public class MainFrame extends JFrame{
	
	private static MainFrame instance = null;
	
	private JToolBar mainToolBar;
	private JButton addButton;
	private JButton editButton;
	private JButton deleteButton;
	private JMenuBar menuBar;
	private JTabbedPane tabHolder;
	
	private MainFrame() {
		this.createMenubar();
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
