package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
public class MainFrame extends JFrame{
	private final int DEFAULT_WIDTH = 1300;
	private final int DEFAULT_HEIGHT = 700;
	
	private JPanel navigationPanel;
	private JPanel serviceManagementMenu;
	private JPanel billManagementMenu;
	private JPanel productManagementMenu;
	private JPanel employeeManagementMenu;
	private JPanel statisticMenu;
	private JPanel closeMenu;
	private JPanel minimizeMenu;
	
	private JPanel displayZonePanel;
	private JPanel serviceManagementDisplay;
	private HoaDonGUI billManagementDisplay;
	private JPanel productManagementDisplay;
	private JPanel employeeManagementDisplay;
	private JPanel statisticDisplay;
	
	public MainFrame() {
		
		
		init();
	}
	
	public void init() {
		setLayout(null);
		
		//get size of frame
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		
		setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
		
		//create perfect location
		int xLocation = (screenWidth - DEFAULT_WIDTH) / 2;
		int yLocation = ((screenHeight - DEFAULT_HEIGHT) / 2) - 20;
		setLocation(xLocation,yLocation);
		
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		
		setTitle("Super Restaurant");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		displayZoneInit();
		navigationInit();
	}
	
	public void navigationInit() {
		
		
		
		navigationPanel = new JPanel();
		navigationPanel.setLayout(null);
		navigationPanel.setBounds(0,0,1300,54);
		navigationPanel.setBackground(Color.decode("#0a0623"));
		
		//FOR LOGO NAME
		JLabel logoName;
		BufferedImage logoImg;
		try {
			logoImg = ImageIO.read(new File("src/images/application_icon/logoname.png"));
			logoName = new JLabel(new ImageIcon(logoImg));
			logoName.setBounds(new Rectangle(10,0,150,50));
			navigationPanel.add(logoName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
//		//SERVICE MANAGEMENT PANEL
		serviceManagementMenu = new PanelLinkGUI("#0a0623",new Rectangle(170,4,170,50));
		serviceManagementMenu.setName("serviceMenu");
		JLabel serviceLb = new JLabel("Bán Hàng");
		formatNavigationLabel(serviceLb,new Font("Tahoma",Font.BOLD,14),"#fcfcfc",new Rectangle(60,0,100,50));
		JLabel homeIcon = null;
		BufferedImage homeImgIcon = null;
		createMenuIcon(homeIcon,homeImgIcon,"src/images/application_icon/home-icon-white.png",new Rectangle(5,0,50,50),serviceManagementMenu);
		serviceManagementMenu.add(serviceLb);
		addMouseListenerForMenu(serviceManagementMenu, displayZonePanel, serviceManagementDisplay);
		navigationPanel.add(serviceManagementMenu);
		serviceManagementMenu.setBorder(BorderFactory.createMatteBorder(0,0,5,0,Color.decode("#e22b31")));
		
		
//		//BILL MANAGEMENT PANEL
		billManagementMenu = new PanelLinkGUI("#0a0623",new Rectangle(350,4,170,50));
		billManagementMenu.setName("billMenu");
		JLabel billLb = new JLabel("Hóa Đơn");
		formatNavigationLabel(billLb,new Font("Tahoma",Font.BOLD,14),"#fcfcfc",new Rectangle(60,0,100,50));
		JLabel billIcon = null;
		BufferedImage billImgIcon = null;
		createMenuIcon(billIcon,billImgIcon,"src/images/application_icon/bill-icon-white.png",new Rectangle(5,0,50,50),billManagementMenu);
		billManagementMenu.add(billLb);
		navigationPanel.add(billManagementMenu);
		addMouseListenerForMenu(billManagementMenu, displayZonePanel, billManagementDisplay);
		
		
//		//PRODUCT MANAGEMENT PANEL
		productManagementMenu = new PanelLinkGUI("#0a0623",new Rectangle(530,4,170,50));
		productManagementMenu.setName("productMenu");
		JLabel productLb = new JLabel("Sản Phẩm");
		formatNavigationLabel(productLb,new Font("Tahoma",Font.BOLD,14),"#fcfcfc",new Rectangle(60,0,100,50));
		JLabel productIcon = null;
		BufferedImage productImgIcon = null;
		createMenuIcon(productIcon,productImgIcon,"src/images/application_icon/product-icon-white.png",new Rectangle(5,0,50,50),productManagementMenu);
		productManagementMenu.add(productLb);
		addMouseListenerForMenu(productManagementMenu, displayZonePanel, productManagementDisplay);
		navigationPanel.add(productManagementMenu);
		
		
//		//EMPLOYEE MANAGEMENT PANEL
		employeeManagementMenu = new PanelLinkGUI("#0a0623",new Rectangle(710,4,170,50));
		employeeManagementMenu.setName("employeeMenu");
		JLabel employeeLb = new JLabel("Nhân Viên");
		formatNavigationLabel(employeeLb,new Font("Tahoma",Font.BOLD,14),"#fcfcfc",new Rectangle(60,0,100,50));
		JLabel employeeIcon = null;
		BufferedImage employeeImgIcon = null;
		createMenuIcon(employeeIcon,employeeImgIcon,"src/images/application_icon/employee-icon-white.png",new Rectangle(5,0,50,50),employeeManagementMenu);
		employeeManagementMenu.add(employeeLb);
		addMouseListenerForMenu(employeeManagementMenu, displayZonePanel, employeeManagementDisplay);
		navigationPanel.add(employeeManagementMenu);
		
		//FOR STATISTIC PANEL
		statisticMenu = new PanelLinkGUI("#0a0623",new Rectangle(890,4,170,50));
		statisticMenu.setName("statisticMenu");
		JLabel statisticLb = new JLabel("Thống kê");
		formatNavigationLabel(statisticLb,new Font("Tahoma",Font.BOLD,14),"#fcfcfc",new Rectangle(60,0,100,50));
		JLabel statisticIcon = null;
		BufferedImage statisticImgIcon = null;
		createMenuIcon(statisticIcon,statisticImgIcon,"src/images/application_icon/statistic-white-icon.png",new Rectangle(5,0,50,50),statisticMenu);
		statisticMenu.add(statisticLb);
		addMouseListenerForMenu(statisticMenu, displayZonePanel, statisticDisplay);
		navigationPanel.add(statisticMenu);
		
		//FOR MINIMIZE AND CLOSE BUTTON
		minimizeMenu = new PanelLinkGUI("#0a0623",new Rectangle(1200,4,40,54));
		minimizeMenu.setName("minimize");
		JLabel minimizeIcon = null;
		BufferedImage minimizeImageIcon = null;
		createMenuIcon(minimizeIcon,minimizeImageIcon,"src/images/application_icon/minimize-icon-white.png",new Rectangle(5,0,30,50),minimizeMenu);
		navigationPanel.add(minimizeMenu);
		frameMiniCloseOperation(minimizeMenu);
		
		closeMenu = new PanelLinkGUI("#0a0623",new Rectangle(1240,4,40,54));
		closeMenu.setName("close");
		JLabel closeIcon = null;
		BufferedImage closeImageIcon = null;
		createMenuIcon(closeIcon,closeImageIcon,"src/images/application_icon/close-icon-white.png",new Rectangle(5,0,30,50),closeMenu);
		navigationPanel.add(closeMenu);
		frameMiniCloseOperation(closeMenu);
		
		add(navigationPanel);
	}
	
	
	public void displayZoneInit() {
		displayZonePanel = new JPanel();
		displayZonePanel.setLayout(null);
		displayZonePanel.setBackground(Color.WHITE);
		displayZonePanel.setBounds(new Rectangle(0,54,1300,646));
		
//		serviceManagementDisplay.setName("serviceDisplay");
//		billManagementDisplay.setName("billDisplay");
//		productManagementDisplay.setName("productDisplay");
		serviceManagementDisplay = new BanHangGUI();
		billManagementDisplay = new HoaDonGUI();
		productManagementDisplay = new SanPhamGUI();
		statisticDisplay = new ThongKeGUI();
		employeeManagementDisplay = new NhanVienGUI();
		
		displayZonePanel.add(serviceManagementDisplay);
//		displayZonePanel.add(billManagementDisplay);
//		displayZonePanel.add(productManagementDisplay);
		
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
	
	public void createMenuIcon(JLabel label, BufferedImage imgIcon, String imgLink, Rectangle rec, JPanel parentPanel) {
		try {
			imgIcon = ImageIO.read(new File(imgLink));
			label = new JLabel(new ImageIcon(imgIcon));
			label.setBounds(rec);
			parentPanel.add(label);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addMouseListenerForMenu(JPanel menuPanel, JPanel parentPanel, JPanel childPanel) {
		menuPanel.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				String menuPanelName = menuPanel.getName();
				
				switch(menuPanelName) {
					case "serviceMenu":{
						
						serviceManagementMenu.setBorder(BorderFactory.createMatteBorder(0,0,5,0,Color.decode("#e22b31")));
						billManagementMenu.setBorder(BorderFactory.createMatteBorder(0,0,0,0,Color.decode("#0a0623")));
						productManagementMenu.setBorder(BorderFactory.createMatteBorder(0,0,0,0,Color.decode("#0a0623")));
						employeeManagementMenu.setBorder(BorderFactory.createMatteBorder(0,0,0,0,Color.decode("#0a0623")));
						statisticMenu.setBorder(BorderFactory.createMatteBorder(0,0,0,0,Color.decode("#0a0623")));
						break;
					}
					case "billMenu":{
						
						serviceManagementMenu.setBorder(BorderFactory.createMatteBorder(0,0,0,0,Color.decode("#0a0623")));
						billManagementMenu.setBorder(BorderFactory.createMatteBorder(0,0,5,0,Color.decode("#e22b31")));
						productManagementMenu.setBorder(BorderFactory.createMatteBorder(0,0,0,0,Color.decode("#0a0623")));
						employeeManagementMenu.setBorder(BorderFactory.createMatteBorder(0,0,0,0,Color.decode("#0a0623")));
						statisticMenu.setBorder(BorderFactory.createMatteBorder(0,0,0,0,Color.decode("#0a0623")));
						break;
					}
					case "productMenu":{
						
						serviceManagementMenu.setBorder(BorderFactory.createMatteBorder(0,0,0,0,Color.decode("#0a0623")));
						billManagementMenu.setBorder(BorderFactory.createMatteBorder(0,0,0,0,Color.decode("#0a0623")));
						productManagementMenu.setBorder(BorderFactory.createMatteBorder(0,0,5,0,Color.decode("#e22b31")));
						employeeManagementMenu.setBorder(BorderFactory.createMatteBorder(0,0,0,0,Color.decode("#0a0623")));
						statisticMenu.setBorder(BorderFactory.createMatteBorder(0,0,0,0,Color.decode("#0a0623")));
						break;
					}
					case "employeeMenu":{
						serviceManagementMenu.setBorder(BorderFactory.createMatteBorder(0,0,0,0,Color.decode("#0a0623")));
						billManagementMenu.setBorder(BorderFactory.createMatteBorder(0,0,0,0,Color.decode("#0a0623")));
						productManagementMenu.setBorder(BorderFactory.createMatteBorder(0,0,0,0,Color.decode("#0a0623")));
						employeeManagementMenu.setBorder(BorderFactory.createMatteBorder(0,0,5,0,Color.decode("#e22b31")));
						statisticMenu.setBorder(BorderFactory.createMatteBorder(0,0,0,0,Color.decode("#0a0623")));
						break;
					}
					
					case "statisticMenu":{
						statisticMenu.setBorder(BorderFactory.createMatteBorder(0,0,0,0,Color.decode("#e22b31")));
						serviceManagementMenu.setBorder(BorderFactory.createMatteBorder(0,0,0,0,Color.decode("#0a0623")));
						billManagementMenu.setBorder(BorderFactory.createMatteBorder(0,0,0,0,Color.decode("#0a0623")));
						productManagementMenu.setBorder(BorderFactory.createMatteBorder(0,0,0,0,Color.decode("#0a0623")));
						employeeManagementMenu.setBorder(BorderFactory.createMatteBorder(0,0,5,0,Color.decode("#0a0623")));
						break;
					}
				}
				switchPanel(parentPanel,childPanel);
				menuPanel.setBorder(BorderFactory.createMatteBorder(0,0,5,0,Color.decode("#e22b31")));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				menuPanel.setBackground(Color.decode("#4f4c60"));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				menuPanel.setBackground(Color.decode("#0a0623"));
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
	
	public void frameMiniCloseOperation(JPanel buttonPanel) {
		buttonPanel.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				String buttonName = buttonPanel.getName();
				if(buttonName.equals("close")) {
					System.exit(0);
				}else if(buttonName.equals("minimize")) {
					setState(Frame.ICONIFIED);
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				buttonPanel.setBackground(Color.decode("#4f4c60"));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				buttonPanel.setBackground(Color.decode("#0a0623"));
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
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
