package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
public class MainFrame extends JFrame{
	private JPanel navigationPanel;
	private JPanel serviceManagementMenu;
	private JPanel billManagementMenu;
	private JPanel productManagementMenu;
	private JPanel employeeManagementMenu;
	private JPanel logoutMenu;
	
	private JPanel displayZonePanel;
	private JPanel serviceManagementDisplay;
	private JPanel billManagementDisplay;
	private JPanel productManagementDisplay;
	private JPanel employeeManagementDisplay;
	
	public MainFrame() {
		init();
	}
	
	public void init() {
		setLayout(null);
		setTitle("Super Restaurant");
		setSize(1300,700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		displayZoneInit();
		navigationInit();
		
		
	}
	
	public void navigationInit() {
		navigationPanel = new JPanel();
		navigationPanel.setLayout(null);
		navigationPanel.setBounds(0,0,280,700);
		navigationPanel.setBackground(Color.decode("#ad1501"));

		//BRANDING
		JPanel brandingPanel = new JPanel();
		createBranding(brandingPanel,"#fcfcfc",new Rectangle(0,0,280,100));
		JLabel brandingLb = new JLabel("Super Team");
		formatNavigationLabel(brandingLb, new Font("helvectica", Font.PLAIN,25),"#000000", new Rectangle(50,10,180,100));
		brandingPanel.add(brandingLb);
		brandingPanel.setBorder(BorderFactory.createMatteBorder(0,0,0,1,Color.decode("#ad1501")));
		navigationPanel.add(brandingPanel);
		
		//SERVICE MANAGEMENT PANEL
		serviceManagementMenu = new PanelLink("#ad1501",new Rectangle(0,100,280,50));
		JLabel serviceLb = new JLabel("Service Management");
		formatNavigationLabel(serviceLb,new Font("tahoma",Font.PLAIN,14),"#fcfcfc",new Rectangle(50,0,180,50));
		serviceManagementMenu.add(serviceLb);
		addMouseListenerForMenu(serviceManagementMenu, displayZonePanel, serviceManagementDisplay);
		navigationPanel.add(serviceManagementMenu);
		
		//BILL MANAGEMENT PANEL
		billManagementMenu = new PanelLink("#ad1501",new Rectangle(0,150,280,50));
		JLabel billLb = new JLabel("Bill Management");
		formatNavigationLabel(billLb,new Font("tahoma",Font.PLAIN,14),"#fcfcfc",new Rectangle(50,0,180,50));
		billManagementMenu.add(billLb);
		navigationPanel.add(billManagementMenu);
		addMouseListenerForMenu(billManagementMenu, displayZonePanel, billManagementDisplay);
		
		//PRODUCT MANAGEMENT PANEL
		productManagementMenu = new PanelLink("#ad1501",new Rectangle(0,200,280,50));
		JLabel productLb = new JLabel("Product Management");
		formatNavigationLabel(productLb,new Font("tahoma",Font.PLAIN,14),"#fcfcfc",new Rectangle(50,0,180,50));
		productManagementMenu.add(productLb);
		addMouseListenerForMenu(productManagementMenu, displayZonePanel, productManagementDisplay);
		navigationPanel.add(productManagementMenu);
		
		//BILL MANAGEMENT PANEL
		employeeManagementMenu = new PanelLink("#ad1501",new Rectangle(0,250,280,50));
		JLabel employeeLb = new JLabel("Employee Management");
		formatNavigationLabel(employeeLb,new Font("tahoma",Font.PLAIN,14),"#fcfcfc",new Rectangle(50,0,180,50));
		employeeManagementMenu.add(employeeLb);
		navigationPanel.add(employeeManagementMenu);
		
		//BILL MANAGEMENT PANEL
		logoutMenu = new PanelLink("#ad1501",new Rectangle(0,300,280,50));
		JLabel logoutLb = new JLabel("Logout");
		formatNavigationLabel(logoutLb,new Font("tahoma",Font.PLAIN,14),"#fcfcfc",new Rectangle(50,0,180,50));
		logoutMenu.add(logoutLb);
		navigationPanel.add(logoutMenu);
		
		add(navigationPanel);
	}
	
	public void displayZoneInit() {
		displayZonePanel = new JPanel();
		displayZonePanel.setLayout(null);
		displayZonePanel.setBackground(Color.WHITE);
		displayZonePanel.setBounds(new Rectangle(280,0,1020,700));
		
		serviceManagementDisplay = new ServiceManagementPanel();
		billManagementDisplay = new BillManagementPanel();
		productManagementDisplay = new ProductManagementPanel();
		
		displayZonePanel.add(serviceManagementDisplay);
		displayZonePanel.add(billManagementDisplay);
		displayZonePanel.add(productManagementDisplay);
		
		add(displayZonePanel);
		
	}
	
	public void formatNavigationLabel(JLabel label, Font font, String foreground, Rectangle rec) {
		label.setBounds(rec);
		label.setForeground(Color.decode(foreground));
		label.setFont(font);
	}
	public void createBranding(JPanel branding,String backgroundColor,Rectangle rec) {
		branding.setLayout(null);
		branding.setBackground(Color.decode(backgroundColor));
		branding.setBounds(rec);
	}
	
	public void addMouseListenerForMenu(JPanel menuPanel, JPanel parentPanel, JPanel childPanel) {
		menuPanel.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				switchPanel(parentPanel,childPanel);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				menuPanel.setBackground(Color.decode("#c4584a"));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				menuPanel.setBackground(Color.decode("#ad1501"));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	
	public void switchPanel(JPanel parentPanel, JPanel childPanel) {
		//removing panel
		parentPanel.removeAll();
		parentPanel.repaint();
		parentPanel.revalidate();
		
		//adding panel
		parentPanel.add(childPanel);
		parentPanel.repaint();
		parentPanel.revalidate();
	}
	
}
