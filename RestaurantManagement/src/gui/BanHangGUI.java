package gui;
import dto.BanDTO;
import dto.ChiTietHoaDonXuatDTO;
import dto.HoaDonTamDTO;
import dto.HoaDonXuatDTO;
import dto.MonDTO;
import dto.MonTamDTO;
import bus.BanBUS;
import bus.ChiTietHoaDonXuatBUS;
import bus.HoaDonXuatBUS;
import bus.MonBUS;
import util.DateHandle;
import util.PdfExportHelper;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
public class BanHangGUI extends JPanel{
	private PdfExportHelper pdf = new PdfExportHelper();
	
	private ArrayList<BanDTO> tableList = new ArrayList<BanDTO>();
	private ArrayList<BanGUI> tablePanelList = new ArrayList<BanGUI>();
	private ArrayList<MonDTO> productList = new ArrayList<MonDTO>();
	private ArrayList<MonGUI> productPanelList = new ArrayList<MonGUI>();
	private ArrayList<HoaDonTamDTO> hdTamList = new ArrayList<HoaDonTamDTO>();
	private ArrayList<String> productInBillDetailInfo = new ArrayList<String>();
	private MonBUS monBus = new MonBUS();
	
	private int currentTablePos;
	
	
	private JPanel zoneBrandPanel;
	private JPanel billZonePanel;
	private JPanel mainZonePanel;
	private JPanel paginationPanel;
	
	private JPanel zoneA;
	private JPanel zoneB;
	private JPanel zoneC;
	
	private JTextField searchProductBox;
	
	private JPanel noodlePanel;
	private JPanel ricePanel;
	private JPanel drinkPanel;
	private JPanel searchResult;
	
	private JPanel billBrandPanel;
	private JPanel billContentPanel;
	private JPanel billFootPanel;
	
	private JPanel tablePagination;
	private JPanel productPagination;
	
	private JLabel zoneLb;
	private JButton backToTableZoneBtn;
	private JLabel totalPrice;
	private JLabel timeIn;
	
	private ChiTietHoaDonXuatBUS chiTietHoaDonXuatBus;
	
	public BanHangGUI() {
		init();
	}
	
	public void init() {
		setLayout(null);
		setBounds(new Rectangle(0,0,1300,646));
		setBackground(Color.decode("#ffffff"));
		
		tableZoneInit();
		zoneBrandPanelInit();
		paginationPanelInit();
		menuZoneInit();
		
		
	}
	
	public void zoneBrandPanelInit() {
		zoneBrandPanel = new JPanel();
		zoneBrandPanel.setLayout(null);
		zoneBrandPanel.setBounds(new Rectangle(0,0,900,50));
		zoneBrandPanel.setBackground(Color.decode("#f2f2f2"));
	
		zoneLb =  new JLabel("Khu \"A\"");
		zoneLb.setFont(new Font("Tahoma",Font.BOLD,30));
		zoneLb.setForeground(Color.decode("#e22b31"));
		zoneLb.setBounds(new Rectangle(400,0,150,50));
		
		backToTableZoneBtn = new JButton("Quay Lại");
		backToTableZoneBtn.setBackground(Color.decode("#f2f2f2"));
		backToTableZoneBtn.setBounds(new Rectangle(10,10,100,30));
		backToTableZoneBtn.setFocusPainted(false);
		backToTableZoneAction(backToTableZoneBtn);
		
		searchProductBox = new JTextField("Nhập tên món cần tìm");
		searchProductBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		searchProductBox.setHorizontalAlignment(JTextField.CENTER);
		searchProductBox.setOpaque(false);
		searchProductBox.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.decode("#000000")));
		searchProductBox.setBounds(new Rectangle(660,10,150,30));
		searchProductBox.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				if(searchProductBox.getText().equals("Nhập tên món cần tìm")) {
					searchProductBox.setText("");
					searchProductBox.setForeground(Color.decode("#000000"));
				}
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				if(searchProductBox.getText().equals("")) {
					searchProductBox.setText("Nhập tên món cần tìm");
					searchProductBox.setForeground(Color.decode("#000000"));
				}
			}
			
		});
		searchProductBox.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent ke) {
				if(ke.getKeyCode() == KeyEvent.VK_ENTER) {
					ArrayList<MonDTO> productResultList = searchProduct(searchProductBox.getText());
					displaySearchProduct(productResultList);
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		zoneBrandPanel.add(zoneLb);
//		zoneBrandPanel.add(searchProductBox);
		
		add(zoneBrandPanel);
	}
	
	public void menuZoneInit() {
		billZonePanel = new JPanel();
		billZonePanel.setLayout(null);
		billZonePanel.setBackground(Color.decode("#ffffff"));
		billZonePanel.setBounds(new Rectangle(900,0,400,650));
		
		add(billZonePanel);
	}
	
	
	public void tableZoneInit() {
		mainZonePanel = new JPanel();
		mainZonePanel.setBounds(new Rectangle(0,50,900,556));
		mainZonePanel.setBackground(Color.decode("#f2f2f2"));
		mainZonePanel.setLayout(null);
		
		initTableList();
		initTablePanelList();
		
		productListInit();
		productPanelListInit();
		
		zoneInit();
		productPanelInit();
		
		mainZonePanel.add(zoneA);
		
		JScrollPane scroll = new JScrollPane(mainZonePanel);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setBounds(0,50,900,556);
		scroll.setBorder(new EmptyBorder(0,0,0,0));
		
		add(scroll);
		
	}
	
	public void paginationPanelInit() {
		paginationPanel = new JPanel();
		paginationPanel.setLayout(null);
		paginationPanel.setBounds(new Rectangle(0,606,900,40));
		paginationPanel.setBackground(Color.decode("#f2f2f2"));
		
		tablePaginationInit();
		productPaginationInit();
		
		paginationPanel.add(tablePagination);
		
		add(paginationPanel);
	}
	
	public void initTableList() {	
		tableList = BanBUS.tableAll();
	}
	
	public void initTablePanelList() {
		for(int i = 0; i < tableList.size(); i++){
			tablePanelList.add(new BanGUI());
			tablePanelList.get(i).setPosInArrList(i);
			addMouseListenerForTable(tablePanelList.get(i),tablePanelList.get(i).getPosInArrList());
			
			//init hoa don tam for follow by tableList
			hdTamList.add(new HoaDonTamDTO());
			hdTamList.get(i).setGioVao(null);
			hdTamList.get(i).setTongTien(0);
			hdTamList.get(i).setIs_billExport(false);
		}
	}
	
	public void setBoundsForTable(JPanel zonePanel, String zoneID) {	
		int tableWidth = 100;
		int tableHeight = 110;
		
		int currentX = 50;
		int currentY = 15;
	
		//Just add all table panel to mainZonePanel
		for(int i = 0; i < tablePanelList.size(); i++) {
			if(tableList.get(i).getmaKhuVuc().equals(zoneID)) {
				//enough number of table panel per row -> move to next row
				if(currentX > 840) {
					currentX = 50;
					currentY = currentY + tableHeight + 10;
				}
				tablePanelList.get(i).setBackgroundColor("#f2f2f2");
				tablePanelList.get(i).setTableName(tableList.get(i).getmaBan());
				tablePanelList.get(i).setRec(new Rectangle(currentX,currentY,tableWidth,tableHeight));
				tablePanelList.get(i).setImgLink("src/images/application_icon/table-black-icon.png");
				tablePanelList.get(i).init();
				
				zonePanel.add(tablePanelList.get(i));
				currentX = currentX + tableWidth + 40;
			
			}
		}
		
	}
	
	public void zoneInit() {
		zoneA = new JPanel();
		zoneA.setName("Khu \"A\"");
		zoneA.setBounds(new Rectangle(0,0,900,556));
		zoneA.setLayout(null);
		zoneA.setBackground(Color.decode("#f2f2f2"));
		
		zoneB = new JPanel();
		zoneB.setName("Khu \"B\"");
		zoneB.setBounds(new Rectangle(0,0,900,556));
		zoneB.setLayout(null);
		zoneB.setBackground(Color.decode("#f2f2f2"));
		
		zoneC = new JPanel();
		zoneC.setName("Khu \"C\"");
		zoneC.setBounds(new Rectangle(0,0,900,556));
		zoneC.setLayout(null);
		zoneC.setBackground(Color.decode("#f2f2f2"));
		
		setBoundsForTable(zoneA,"kva");
		setBoundsForTable(zoneB,"kvb");
		setBoundsForTable(zoneC,"kvc");
		
	}
	
	public void tablePaginationInit() {
		tablePagination = new JPanel();
		tablePagination.setLayout(null);
		tablePagination.setBounds(new Rectangle(300,0,300,40));
		tablePagination.setBackground(Color.decode("#0a0623"));
		
		JButton kAButton = new JButton("Khu A");
		formatPaginationButton(kAButton,new Rectangle(0,0,100,40));
		addActionListenerForPaginationButton(kAButton,mainZonePanel,zoneA);
		
		JButton kBButton = new JButton("Khu B");
		formatPaginationButton(kBButton,new Rectangle(100,0,100,40));
		addActionListenerForPaginationButton(kBButton,mainZonePanel,zoneB);
		
		JButton kCButton = new JButton("Khu C");
		formatPaginationButton(kCButton,new Rectangle(200,0,100,40));
		addActionListenerForPaginationButton(kCButton,mainZonePanel,zoneC);
		
		tablePagination.add(kAButton);
		tablePagination.add(kBButton);
		tablePagination.add(kCButton);
		
		
//		paginationPanel.add(tablePagination);
	}
	
	public void productListInit() {
		productList = monBus.getMonList();
	}
	
	public void productPanelListInit() {
		for(int i = 0; i < productList.size(); i++) {
			productPanelList.add(new MonGUI());
			productPanelList.get(i).setProductPosInArr(i);
			addMouseListenerForProduct(productPanelList.get(i),productPanelList.get(i).getProductPosInArr());
		}
	}
	
	public void createProductPanel(JPanel productPanel, String maLoaiMon) {
		GridBagConstraints constraints = new GridBagConstraints();
		Insets inset = new Insets(20,20,20,20);
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = inset;
		for(int i = 0; i < productPanelList.size(); i++) {
			if(productList.get(i).getMaLoaiMon().equals(maLoaiMon)) {
				if(constraints.gridx > 3) {
					constraints.gridx = 0;
					constraints.gridy++;
				}
				productPanelList.get(i).setTenMon(productList.get(i).getTenMon());
				productPanelList.get(i).setGia((int)productList.get(i).getGia());
				productPanelList.get(i).setImgLink(productList.get(i).getImgLink());
				productPanelList.get(i).init();
				productPanelList.get(i).setPreferredSize(new Dimension(150,180));
				
				productPanel.add(productPanelList.get(i), constraints);
				constraints.gridx++;
			}
		}
	}
	
	public void productPanelInit() {
		noodlePanel = new JPanel();
		noodlePanel.setName("Món Mì");
		noodlePanel.setBounds(new Rectangle(0,0,900,556));
		noodlePanel.setLayout(new GridBagLayout());
		noodlePanel.setBackground(Color.decode("#f2f2f2"));
		
		ricePanel = new JPanel();
		ricePanel.setName("Món Cơm");
		ricePanel.setBounds(new Rectangle(0,0,900,556));
		ricePanel.setLayout(new GridBagLayout());
		ricePanel.setBackground(Color.decode("#f2f2f2"));
		
		createProductPanel(noodlePanel,"nod");
		createProductPanel(ricePanel,"ric");
		
		
		
	}
	
	public void productPaginationInit() {
		productPagination = new JPanel();
		productPagination.setLayout(null);
		productPagination.setBounds(new Rectangle(300,0,300,40));
		productPagination.setBackground(Color.decode("#0a0623"));
		
		JButton noodleButton = new JButton("Mì");
		formatPaginationButton(noodleButton,new Rectangle(0,0,100,40));
		addActionListenerForPaginationButton(noodleButton,mainZonePanel,noodlePanel);
		
		JButton riceButton = new JButton("Cơm");
		formatPaginationButton(riceButton,new Rectangle(100,0,100,40));
		addActionListenerForPaginationButton(riceButton,mainZonePanel,ricePanel);
		
		JButton drinkButton = new JButton("Nước");
		formatPaginationButton(drinkButton,new Rectangle(200,0,100,40));
		addActionListenerForPaginationButton(drinkButton,mainZonePanel,drinkPanel);
		
		productPagination.add(noodleButton);
		productPagination.add(riceButton);
		productPagination.add(drinkButton);
	}
	
	public ArrayList<MonDTO> searchProduct(String productNameInput) {
		ArrayList<MonDTO> productResultList = new ArrayList<MonDTO>();
		productNameInput = productNameInput.toLowerCase();
		for(int i = 0; i < productList.size(); i++) {
			if(productList.get(i).getTenMon().toLowerCase().indexOf(productNameInput) > -1) {
				productResultList.add(productList.get(i));
			}
		}
		return productResultList;
	}
	
	public void displaySearchProduct(ArrayList<MonDTO> productResultList) {
		ArrayList<MonGUI> productPanelResult = new ArrayList<MonGUI>();
		
		for(int i = 0; i < productResultList.size(); i++) {
			productPanelResult.add(new MonGUI());
			productPanelResult.get(i).setProductPosInArr(i);
			addMouseListenerForProduct(productPanelResult.get(i),productPanelResult.get(i).getProductPosInArr());
		}
		
		//change title
		zoneLb.setText("Kết quả");
		
		searchResult = new JPanel();
		searchResult.setName("Kết quả tìm");
		searchResult.setLayout(new GridBagLayout());
		searchResult.setBackground(Color.decode("#f2f2f2"));
		JScrollPane scroll = new JScrollPane(searchResult);
		scroll.setBorder(new EmptyBorder(0,0,0,0));
		
		GridBagConstraints constraints = new GridBagConstraints();
		Insets inset = new Insets(20,20,20,20);
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = inset;
		for(int i = 0; i < productPanelResult.size(); i++) {
			if(constraints.gridx > 3) {
				constraints.gridx = 0;
				constraints.gridy++;
			}
			productPanelResult.get(i).setTenMon(productResultList.get(i).getTenMon());
			productPanelResult.get(i).setGia((int)productResultList.get(i).getGia());
			productPanelResult.get(i).setImgLink(productResultList.get(i).getImgLink());
			productPanelResult.get(i).init();
			productPanelResult.get(i).setPreferredSize(new Dimension(150,180));
			
			searchResult.add(productPanelResult.get(i), constraints);
			constraints.gridx++;
		}
		
		scroll.setBounds(new Rectangle(0,0,900,556));
		scroll.getVerticalScrollBar().setPreferredSize(new Dimension(8,100));
		
		mainZonePanel.removeAll();
		mainZonePanel.add(scroll);
		mainZonePanel.repaint();
		mainZonePanel.revalidate();
	}
	
	public void billBrandInit(String maBan, String time) {
		billBrandPanel = new JPanel();
		billBrandPanel.setBounds(new Rectangle(0,0,400,60));
		billBrandPanel.setBackground(Color.decode("#ffffff"));
		billBrandPanel.setLayout(null);
		billBrandPanel.setBorder(BorderFactory.createMatteBorder(0,0,3,0,Color.decode("#000000")));
		
		JLabel maBanLb = new JLabel("Bàn " + maBan);
		maBanLb.setFont(new Font("Tahoma",Font.BOLD,25));
		maBanLb.setForeground(Color.decode("#e22b31"));
		maBanLb.setBounds(new Rectangle(10,10,150,40));
		
		timeIn = new JLabel(time);
		timeIn.setBounds(new Rectangle(340,20,60,30));
		timeIn.setForeground(Color.decode("#000000"));
		
		billBrandPanel.add(maBanLb);
		billBrandPanel.add(timeIn);
		
		billZonePanel.add(billBrandPanel);
	}
	
	public void billContentInit(ArrayList<MonTamDTO> monTamArr) {
		billContentPanel = new JPanel();
		billContentPanel.setLayout(null);
		billContentPanel.setBackground(Color.decode("#ffffff"));
		billContentPanel.setBounds(new Rectangle(0,60,400,480));
		
		JTable contentTable = new JTable();
		addListSelectionListenerForBillTable(contentTable);
		
		contentTable.getTableHeader().setOpaque(false);
		contentTable.getTableHeader().setPreferredSize(new Dimension(800,40));
		contentTable.setRowHeight(40);
        contentTable.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {{
        	setBackground(Color.decode("#dddddd"));
            setBorder(new EmptyBorder(0,0,0,0));
        }});
		contentTable.setShowVerticalLines(false);
		JScrollPane scroll = new JScrollPane(contentTable);
		
		Vector header = new Vector();
		header.add("Tên món");
		header.add("SL");
		header.add("ĐVT");
		header.add("Đơn giá");
		header.add("Thành tiền");
		
		DefaultTableModel model = new DefaultTableModel(header,0) {
			@Override
		   public boolean isCellEditable(int row, int column) {
		       //Only the third column
		       return column == 100;
		   }
		};
		Vector row;
		int i = 0;
		while(i < monTamArr.size()) {
			row = new Vector();
			row.add(monTamArr.get(i).getMon().getTenMon());
			row.add(monTamArr.get(i).getSoLuong());
			row.add(monTamArr.get(i).getMon().getDonViTinh());
			row.add(monTamArr.get(i).getMon().getGia());
			int thanhTien = monTamArr.get(i).getMon().getGia() * monTamArr.get(i).getSoLuong();
			row.add(thanhTien);
			model.addRow(row);
			i++;
		}
		contentTable.setModel(model);
		
		TableColumnModel columnModel = contentTable.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(150);
		columnModel.getColumn(1).setPreferredWidth(30);
		columnModel.getColumn(2).setPreferredWidth(50);
		columnModel.getColumn(3).setPreferredWidth(85);
		columnModel.getColumn(4).setPreferredWidth(85);
		
		scroll.setBounds(new Rectangle(0,0,400,500));
		scroll.setBackground(Color.decode("#ffffff"));
		scroll.setBorder(new EmptyBorder(0,0,0,0));
		scroll.getVerticalScrollBar().setPreferredSize(new Dimension(8,100));
		scroll.getVerticalScrollBar().setOpaque(false);
		billContentPanel.add(scroll);
		
		billZonePanel.add(billContentPanel);
		
	}
	
	public void billFootInit() {
		billFootPanel = new JPanel();
		billFootPanel.setLayout(null);
		billFootPanel.setBounds(new Rectangle(0,540,400,106));
		billFootPanel.setBackground(Color.decode("#ffffff"));
		
		//calculate total price of seleted table
		int totalPriceOfBill = 0;
		for(int i = 0; i < hdTamList.get(currentTablePos).getDsMonTam().size(); i++) {
			int pricePerProduct = hdTamList.get(currentTablePos).getDsMonTam().get(i).getMon().getGia() * hdTamList.get(currentTablePos).getDsMonTam().get(i).getSoLuong();
			totalPriceOfBill += pricePerProduct;
		}
		
		JLabel totalPriceLb =  new JLabel("Tổng Tiền: ");
		totalPriceLb.setBounds(new Rectangle(5,10,150,40));
		totalPriceLb.setFont(new Font("Tahoma", Font.PLAIN, 23));
		
		totalPrice = new JLabel(totalPriceOfBill + "đ");
		totalPrice.setBounds(new Rectangle(160,10,300,40));
		totalPrice.setFont(new Font("Tahoma", Font.BOLD, 23));
		totalPrice.setForeground(Color.decode("#e22b31"));
		
		billFootPanel.add(totalPriceLb);
		billFootPanel.add(totalPrice);
		
		JPanel processingPanel = new JPanel();
		formatBillFootPanelButton(processingPanel,"In Chế Biến","#ffffff",new Rectangle(0,66,133,40),"src/images/application_icon/export-icon-x16.png");
		processingPanel.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.decode("#000000")));
		addMouseListenerForProcessing(processingPanel);
		
		JPanel billExportPanel = new JPanel();
		formatBillFootPanelButton(billExportPanel,"Xuất Hóa Đơn","#ECECEC",new Rectangle(133,66,133,40),"src/images/application_icon/pay-icon-x16.png");
		billExportPanel.setBorder(BorderFactory.createMatteBorder(1,0,1,1,Color.decode("#000000")));
		addMoustListenerForBillExport(billExportPanel);
		
		JPanel closeTablePanel = new JPanel();
		formatBillFootPanelButton(closeTablePanel,"Đóng Bàn","#e22b31",new Rectangle(266,66,133,40),"src/images/application_icon/table-close-icon-x16.png");
		closeTablePanel.setBorder(BorderFactory.createMatteBorder(1,0,1,1,Color.decode("#000000")));
		addMouseListenerForCloseTable(closeTablePanel);
		
		billFootPanel.add(processingPanel);
		billFootPanel.add(billExportPanel);
		billFootPanel.add(closeTablePanel);
		
		billZonePanel.add(billFootPanel);
	}
	
	public void addActionListenerForPaginationButton(JButton button, JPanel parentPanel, JPanel childPanel) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//CHANGE ZONE BRANDING
				zoneLb.setText(childPanel.getName());
				
				//CHANGE ZONE PANEL
				JScrollPane scroll = new JScrollPane(childPanel);
				scroll.setBounds(new Rectangle(0,0,900,556));
				scroll.setBorder(new EmptyBorder(0,0,0,0));
				scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
				scroll.getVerticalScrollBar().setPreferredSize(new Dimension(8,100));
				switchScrollPanel(parentPanel,scroll);
			}
			
		});
	}
	
	public void addMouseListenerForTable(JPanel tablePanel, int posInArrList) {
		tablePanel.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					currentTablePos = posInArrList;
					
					//CHANGE BRAND NAME
					zoneLb.setText("Món Mì");
					
					//CHANGE TO PRODUCT ZONE -> SPECIFICLLY NOODLEPANEL
					JScrollPane scroll = new JScrollPane(noodlePanel);
					//scroll.getVerticalScrollBar().setUnitIncrement(50);
					scroll.setBounds(new Rectangle(0,0,900,556));
					scroll.setBorder(new EmptyBorder(0,0,0,0));
					scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
					switchScrollPanel(mainZonePanel,scroll);
					
					//CHANGE PAGINATION BUTTON TO PRODUCT PAGINATION
					switchPanel(paginationPanel,productPagination);
					
					//MAKE THE backToTableZoneBtn and searchProductBox APPEAR
					zoneBrandPanel.add(backToTableZoneBtn);
					zoneBrandPanel.add(searchProductBox);
					zoneBrandPanel.repaint();
					
					//INITIALIZE BILL ZONE
					//bill brand init
					billZonePanel.removeAll();
					
					billBrandInit(tableList.get(posInArrList).getmaBan(),getTableTime(hdTamList.get(currentTablePos).getGioVao()));
					
					billContentInit(hdTamList.get(currentTablePos).getDsMonTam());
					
					billFootInit();
					
					billZonePanel.repaint();
					
					
			    }
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
//				tablePanel.setBackground(Color.decode("#bdcccc"));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
//				tablePanel.setBackground(Color.decode("#abb7b7"));
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
	
	public void backToTableZoneAction(JButton button) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//CHANGE BRAND ZONE
				zoneLb.setText("Khu \"A\"");
				//BACK TO ZONE A 
				switchPanel(mainZonePanel,zoneA);
				//CHANGE TO TABLE PAGINATION
				switchPanel(paginationPanel,tablePagination);
				//REMOVE backToTableZoneBtn and searchProductBox
				zoneBrandPanel.remove(backToTableZoneBtn);
				zoneBrandPanel.remove(searchProductBox);
				zoneBrandPanel.repaint();
			}
			
		});
	}
	
	public void addMouseListenerForProduct(JPanel productPanel, int productPosInArr) {
		productPanel.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e.getClickCount() == 2) {
					if(hdTamList.get(currentTablePos).isIs_billExport() == true) {
						JOptionPane.showMessageDialog(null,"Bàn này đã xuất hóa đơn, bạn không thể order thêm món mới!");
					}else {
						//get product from user click and save as monTam for hdTamList
						MonTamDTO monTam = new MonTamDTO(productList.get(productPosInArr),1);
						monTam.setIs_process(false);
						monTam.setProcessingQuantity(1);
						//check if mon was chosen before
						boolean is_chosen = false;	
						for(int i = 0; i < hdTamList.get(currentTablePos).getDsMonTam().size(); i++) {
							if(hdTamList.get(currentTablePos).getDsMonTam().get(i).getMon().getMaMon().equals(monTam.getMon().getMaMon())) {
								//if matched, get total quantity and processing quantity and increase
								int oldQuantity = hdTamList.get(currentTablePos).getDsMonTam().get(i).getSoLuong();
								oldQuantity++;
								int oldProcessingQuantity = hdTamList.get(currentTablePos).getDsMonTam().get(i).getProcessingQuantity();
								oldProcessingQuantity++;
								//re-set total quantity and processing quantity
								hdTamList.get(currentTablePos).getDsMonTam().get(i).setSoLuong(oldQuantity);
								hdTamList.get(currentTablePos).getDsMonTam().get(i).setProcessingQuantity(oldProcessingQuantity);
								is_chosen = true;
								break;
							}
						}
						if(is_chosen == false) {
							hdTamList.get(currentTablePos).getDsMonTam().add(monTam);
						}
						
						int totalPriceOfBill = 0;
						for(int i = 0; i < hdTamList.get(currentTablePos).getDsMonTam().size(); i++) {
							int pricePerProduct = hdTamList.get(currentTablePos).getDsMonTam().get(i).getMon().getGia() * hdTamList.get(currentTablePos).getDsMonTam().get(i).getSoLuong();
							totalPriceOfBill += pricePerProduct;
						}
						
						hdTamList.get(currentTablePos).setTongTien(totalPriceOfBill);
						
						billZonePanel.removeAll();
						billBrandInit(tableList.get(currentTablePos).getmaBan(),getTableTime(hdTamList.get(currentTablePos).getGioVao()));
						billContentInit(hdTamList.get(currentTablePos).getDsMonTam());
						billFootInit();
						totalPrice.setText(Integer.toString(totalPriceOfBill)+"đ");
						billZonePanel.repaint();
					}
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
//				productPanel.setBackground(Color.decode("#f7f7f7"));
				productPanel.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.decode("#bbbbbb")));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				productPanel.setBackground(Color.decode("#f2f2f2"));
				productPanel.setBorder(BorderFactory.createMatteBorder(0,0,0,0,Color.decode("#f2f2f2")));
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
	
	public void addMoustListenerForBillExport(JPanel exportButton) {
		exportButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(JOptionPane.showConfirmDialog(null,"Tiến hành xuất hóa đơn") == JOptionPane.YES_OPTION) {
					if(tablePanelList.get(currentTablePos).isStatus() == true) {
						if(hdTamList.get(currentTablePos).isIs_billExport() == false) {
							//export bill
							HoaDonXuatBUS.exportBill(hdTamList.get(currentTablePos),tableList.get(currentTablePos).getmaBan(),"nv001");
							
							//print bill details
							ArrayList<HoaDonXuatDTO> hoaDonXuatList = HoaDonXuatBUS.getDsHoaDonXuat();
							chiTietHoaDonXuatBus = new ChiTietHoaDonXuatBUS();
							chiTietHoaDonXuatBus.saveChiTietHoaDonXuat(hdTamList.get(currentTablePos).getDsMonTam(), hoaDonXuatList.get(hoaDonXuatList.size() - 1).getMaHoaDon());
							//mark that bill was printed
							hdTamList.get(currentTablePos).setIs_billExport(true);
							
							//print pdf bill
							ArrayList<String> currentTime = DateHandle.getCurrentTime();
							SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
							Date exportTime = null;
							try {
								exportTime = dateFormat.parse(currentTime.get(0) + "-" + currentTime.get(1) + "-" + currentTime.get(2) + " " + currentTime.get(3) + ":" + currentTime.get(4) + ":" + currentTime.get(5));
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							pdf.exportPayBill(hdTamList.get(currentTablePos).getDsMonTam(),hdTamList.get(currentTablePos).getGioVao(), exportTime, tableList.get(currentTablePos).getmaBan());
							
							//Have handle problem export bill twice or three times => just save to database at the first time we export
							//next time and later we won't save that bill to database again (TASK LEFT)
							JOptionPane.showMessageDialog(null,"Xuất hóa đơn thành công!");
							
							tablePanelList.get(currentTablePos).setBorder(BorderFactory.createDashedBorder(null,5,5));
							tablePanelList.get(currentTablePos).removeAll();
							tablePanelList.get(currentTablePos).init();
							tablePanelList.get(currentTablePos).repaint();
						}else {
							//print pdf bill
							ArrayList<String> currentTime = DateHandle.getCurrentTime();
							SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
							Date exportTime = null;
							try {
								exportTime = dateFormat.parse(currentTime.get(0) + "-" + currentTime.get(1) + "-" + currentTime.get(2) + " " + currentTime.get(3) + ":" + currentTime.get(4) + ":" + currentTime.get(5));
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							pdf.exportPayBill(hdTamList.get(currentTablePos).getDsMonTam(),hdTamList.get(currentTablePos).getGioVao(), exportTime, tableList.get(currentTablePos).getmaBan());
							JOptionPane.showMessageDialog(null,"Xuất hóa đơn thành công!");
						}
					}else {
						JOptionPane.showMessageDialog(null,"Bàn này chưa được in chế biến món ăn!");
					}
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
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
	
	
	public void addMouseListenerForProcessing(JPanel processingButton) {
		processingButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(JOptionPane.showConfirmDialog(null,"Tiến hành in bill chế biến?") == JOptionPane.YES_OPTION) {
					//TURN ACTIVE OF TABLE ON
					tablePanelList.get(currentTablePos).setStatus(true);
					tablePanelList.get(currentTablePos).setImgLink("src/images/application_icon/table-color-icon.png");
					tablePanelList.get(currentTablePos).removeAll();
					tablePanelList.get(currentTablePos).init();
					tablePanelList.get(currentTablePos).repaint();
					
					//get products that need to print processing bill
					ArrayList<MonTamDTO> exportProcessingList = new ArrayList<MonTamDTO>();
					for(int i = 0; i < hdTamList.get(currentTablePos).getDsMonTam().size(); i++) {
						if(hdTamList.get(currentTablePos).getDsMonTam().get(i).getProcessingQuantity() != 0) {
							exportProcessingList.add(hdTamList.get(currentTablePos).getDsMonTam().get(i));
						}
					}
					
					//print pdf bill
					ArrayList<String> currentTime = DateHandle.getCurrentTime();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					Date exportTime = null;
					try {
						exportTime = dateFormat.parse(currentTime.get(0) + "-" + currentTime.get(1) + "-" + currentTime.get(2) + " " + currentTime.get(3) + ":" + currentTime.get(4) + ":" + currentTime.get(5));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					pdf.exportProcessingBill(exportProcessingList, exportTime, tableList.get(currentTablePos).getmaBan());
					JOptionPane.showMessageDialog(null,"In chế biến thành công");
					
					//set processing quantity to 0 again
					for(int i = 0; i < hdTamList.get(currentTablePos).getDsMonTam().size(); i++) {
						hdTamList.get(currentTablePos).getDsMonTam().get(i).setProcessingQuantity(0);
					}
					
				
					//print time of table since when it open and save open time
					hdTamList.get(currentTablePos).setGioVao(util.DateHandle.StringToDate(currentTime.get(0) + "-" + currentTime.get(1) + "-" + currentTime.get(2) + " " + currentTime.get(3) + ":" + currentTime.get(4) + ":" + currentTime.get(5)));
					billZonePanel.remove(billBrandPanel);
					billBrandInit(tableList.get(currentTablePos).getmaBan(),getTableTime(hdTamList.get(currentTablePos).getGioVao()));
					billZonePanel.repaint();
					
					JOptionPane.showMessageDialog(null,"In chế biến thành công");
				}
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
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
	
	public void addMouseListenerForCloseTable(JPanel closeTableButton) {
		closeTableButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(JOptionPane.showConfirmDialog(null,"Bạn có chắc chắn là muốn đóng bàn này hay không?") == JOptionPane.YES_OPTION) {
					tablePanelList.get(currentTablePos).setStatus(false);
					tablePanelList.get(currentTablePos).setImgLink("src/images/application_icon/table-black-icon.png");
					tablePanelList.get(currentTablePos).removeAll();
					tablePanelList.get(currentTablePos).setBorder(new EmptyBorder(0,0,0,0));
					tablePanelList.get(currentTablePos).init();
					tablePanelList.get(currentTablePos).repaint();
					
					hdTamList.get(currentTablePos).getDsMonTam().clear();
					hdTamList.get(currentTablePos).setGioVao(null);
					hdTamList.get(currentTablePos).setIs_billExport(false);
					hdTamList.get(currentTablePos).setTongTien(0);
					
					JOptionPane.showMessageDialog(null,"Đóng bàn thành công");
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
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
	
	public void formatPaginationButton(JButton button, Rectangle rec) {
		button.setBackground(Color.decode("#0a0623"));
		button.setForeground(Color.decode("#fcfcfc"));
		button.setFocusPainted(false);
		button.setBorder(BorderFactory.createMatteBorder(0,0,0,1,Color.decode("#fcfcfc")));
		button.setBounds(rec);
	}
	
	public void formatBillFootPanelButton(JPanel panelButton, String panelName, String backgroundColor, Rectangle rec, String imgLink) {
		panelButton.setBackground(Color.decode(backgroundColor));
		panelButton.setLayout(null);
		panelButton.setBounds(rec);
		JLabel panelNameLb  = new JLabel(panelName);
		panelNameLb.setBounds(new Rectangle(40,0,100,40));
		panelButton.add(panelNameLb);
		BufferedImage image;
		JLabel imgLb;
		try {
			image = ImageIO.read(new File(imgLink));
			imgLb = new JLabel(new ImageIcon(image));
			imgLb.setBounds(new Rectangle(10,0,20,40));
			panelButton.add(imgLb);
		}catch(IOException e) {
			System.out.println("Read file error: ");
			e.printStackTrace();
		}
		
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
	public void switchScrollPanel(JPanel parentPanel, JScrollPane childPanel) {
		//removing panel
		parentPanel.removeAll();
		parentPanel.repaint();
		parentPanel.revalidate();
		
		//adding panel
		parentPanel.add(childPanel);
		parentPanel.repaint();
		parentPanel.revalidate();
	}
	
	public String getTableTime(Date gioVao) {
		String result = "";
		int hours;
		int minutes;
		if(gioVao == null) {
			result += "0h0'";
		}else {
			SimpleDateFormat hourFormatter = new SimpleDateFormat("hh");
			SimpleDateFormat minuteFormatter = new SimpleDateFormat("mm");
			//Simple algorithm: current hour - "gioVao" hour = a | current minute - "gioVao" minute = b
			//Then: (ax60) + b = minute from begin to current => Then parse to hh:mm
			hours = Calendar.getInstance().get(Calendar.HOUR) - Integer.parseInt(hourFormatter.format(gioVao));
			minutes = Calendar.getInstance().get(Calendar.MINUTE) - Integer.parseInt(minuteFormatter.format(gioVao));
			int totalMinutes = (hours * 60) + minutes;
			//format to hh:mm
			result += (totalMinutes / 60) + "h" + (totalMinutes % 60) + "'";
		}
		
		return result;
	}
	
	public void addListSelectionListenerForBillTable(JTable contentTable) {
		ListSelectionModel select = contentTable.getSelectionModel();
		select.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				
				if(e.getValueIsAdjusting()) {
					int rowIndex = contentTable.getSelectedRow();
					String productName = contentTable.getModel().getValueAt(rowIndex,0).toString();
					
					//get selected product from product list of selected table
					MonTamDTO selectedProduct = new MonTamDTO();
					
					for(int i = 0; i < hdTamList.get(currentTablePos).getDsMonTam().size(); i++) {
						if(hdTamList.get(currentTablePos).getDsMonTam().get(i).getMon().getTenMon().equals(productName)) {
							selectedProduct = hdTamList.get(currentTablePos).getDsMonTam().get(i);
						}
					}
					String maMon = selectedProduct.getMon().getMaMon();
					
					//CREATE POP-UP WHEN CLICK ON PRODUCT LIST AT BILL ZONE
					JDialog dialog = new JDialog();
					dialog.setPreferredSize(new Dimension(400,400));
					dialog.add(new JLabel("hello"));
					dialog.setLocationByPlatform(true);
					
					
					JPanel productInfo = new JPanel();
					productInfo.setLayout(null);
					productInfo.setBackground(Color.decode("#f2f2f2"));
					
					JLabel maBanTitle = new JLabel("Bàn " + tableList.get(currentTablePos).getmaBan());
					maBanTitle.setFont(new Font("Tahoma",Font.BOLD,28));
					maBanTitle.setForeground(Color.decode("#e22b31"));
					maBanTitle.setBounds(new Rectangle(130,10,150,30));
					
					
					BufferedImage thumbnailImg;
					JLabel imgHolder;
					try {
						thumbnailImg = ImageIO.read(new File("src/" + selectedProduct.getMon().getImgLink()));
						imgHolder = new JLabel(new ImageIcon(thumbnailImg));
						imgHolder.setBounds(10, 70, 150, 112);
						productInfo.add(imgHolder);
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					JLabel productNameLb = new JLabel(productName);
					productNameLb.setFont(new Font("Tahoma",Font.PLAIN,18));
					productNameLb.setBounds(new Rectangle(180,70,200,30));
					
					JLabel priceLb = new JLabel("Giá: " + selectedProduct.getMon().getGia() + "đ");
					priceLb.setFont(new Font("Tahoma",Font.PLAIN,18));
					priceLb.setBounds(new Rectangle(180,110,200,30));
					
					JLabel quantityLb = new JLabel("Số lượng: ");
					quantityLb.setFont(new Font("Tahoma",Font.PLAIN,18));
					quantityLb.setBounds(new Rectangle(180,150,90,30));
					
					JTextField quantityField = new JTextField(Integer.toString(selectedProduct.getSoLuong()),SwingConstants.CENTER);
					quantityField.setFont(new Font("Tahoma", Font.PLAIN,15));
					quantityField.setOpaque(false);
					quantityField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("#000000")));
					quantityField.setBounds(new Rectangle(270,150,50,30));
					
					
					
					JButton editMonBtn = new JButton("Chỉnh sửa");
					formatButton(editMonBtn,"#ffffff",new Rectangle(80,200,100,30));
					
					//add edit action for editMonBtn
					editMonBtn.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							if(JOptionPane.showConfirmDialog(null,"Bạn có chắc là muốn chỉnh sửa hay không?") == JOptionPane.YES_OPTION) {
								if(isParseStringToInteger(quantityField.getText())) {
									//loop through hdTamList to find out monTam to edit
									for(int i = 0; i < hdTamList.get(currentTablePos).getDsMonTam().size(); i++) {
										if(hdTamList.get(currentTablePos).getDsMonTam().get(i).getMon().getMaMon().equals(maMon)) {
											//if monTam matched
											//update quantity
											hdTamList.get(currentTablePos).getDsMonTam().get(i).setSoLuong(Integer.parseInt(quantityField.getText()));
											
											//repaint billZone
											int totalPriceOfBill = 0;
											for(int j = 0; j < hdTamList.get(currentTablePos).getDsMonTam().size(); j++) {
												int pricePerProduct = hdTamList.get(currentTablePos).getDsMonTam().get(j).getMon().getGia() * hdTamList.get(currentTablePos).getDsMonTam().get(j).getSoLuong();
												totalPriceOfBill += pricePerProduct;
											}
											
											hdTamList.get(currentTablePos).setTongTien(totalPriceOfBill);
											
											billZonePanel.removeAll();
											billBrandInit(tableList.get(currentTablePos).getmaBan(),getTableTime(hdTamList.get(currentTablePos).getGioVao()));
											billContentInit(hdTamList.get(currentTablePos).getDsMonTam());
											billFootInit();
											totalPrice.setText(Integer.toString(totalPriceOfBill)+"đ");
											billZonePanel.repaint();
											break;
										}
									}
									JOptionPane.showMessageDialog(null, "Chỉnh sửa thành công");
									dialog.dispose();
									
								}else {
									JOptionPane.showMessageDialog(null, "Vui lòng chỉ nhập số vào trường số lượng!");
								}
								
							}
							
						}
						
					});
					
					//add delete action for deleteMonBtn
					JButton deleteMonBtn = new JButton("Xóa");
					deleteMonBtn.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							if(JOptionPane.showConfirmDialog(null,"Bạn có chắc là muốn xóa món này hay không?") == JOptionPane.YES_OPTION) {
								//loop through hdTamList to find out monTam to delete
								for(int i = 0; i < hdTamList.get(currentTablePos).getDsMonTam().size(); i++) {
									if(hdTamList.get(currentTablePos).getDsMonTam().get(i).getMon().getMaMon().equals(maMon)) {
										//if monTam matched
										//remove that monTam
										hdTamList.get(currentTablePos).getDsMonTam().remove(i);
										
										//repaint billZone
										int totalPriceOfBill = 0;
										for(int j = 0; j < hdTamList.get(currentTablePos).getDsMonTam().size(); j++) {
											int pricePerProduct = hdTamList.get(currentTablePos).getDsMonTam().get(j).getMon().getGia() * hdTamList.get(currentTablePos).getDsMonTam().get(j).getSoLuong();
											totalPriceOfBill += pricePerProduct;
										}
										
										hdTamList.get(currentTablePos).setTongTien(totalPriceOfBill);
										
										billZonePanel.removeAll();
										billBrandInit(tableList.get(currentTablePos).getmaBan(),getTableTime(hdTamList.get(currentTablePos).getGioVao()));
										billContentInit(hdTamList.get(currentTablePos).getDsMonTam());
										billFootInit();
										totalPrice.setText(Integer.toString(totalPriceOfBill)+"đ");
										billZonePanel.repaint();
										break;
									}
								}
								JOptionPane.showMessageDialog(null, "Xóa món thành công");
								dialog.dispose();
							}
						}
						
					});
					formatButton(deleteMonBtn,"#ef2839",new Rectangle(200,200,100,30));
					
					productInfo.add(maBanTitle);
					productInfo.add(productNameLb);
					productInfo.add(priceLb);
					productInfo.add(quantityLb);
					productInfo.add(quantityField);
					productInfo.add(editMonBtn);
					productInfo.add(deleteMonBtn);
					
					dialog.add(productInfo);
					
					dialog.pack();
					dialog.setVisible(true);
					
				}
				
			}
			
		});
		
	}
	
	public void editMonTam() {
		
	}
	
	public boolean isParseStringToInteger(String intString) {
		int num;
		try {
			num = Integer.parseInt(intString);
			return true;
		}catch(NumberFormatException nfe) {
			return false;
		}
	}
	
	public void editMonAction(JButton button, ArrayList<String> dialogResult, JTextField quantityField) {
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(JOptionPane.showConfirmDialog(null,"Bạn có chắc là muốn chỉnh sửa hay không?") == JOptionPane.YES_OPTION) {
					if(isParseStringToInteger(quantityField.getText())) {
						dialogResult.clear();
						dialogResult.add(quantityField.getText());
						dialogResult.add("true");
						dialogResult.add("false");
						JOptionPane.showMessageDialog(null, "Chỉnh sửa thành công");
					}else {
						JOptionPane.showMessageDialog(null, "Vui lòng chỉ nhập số vào trường số lượng!");
					}
					
				}
			}
			
		});
	}
	
	public void formatButton(JButton button, String backgroundColor, Rectangle rec) {
		button.setBackground(Color.decode(backgroundColor));
		button.setBounds(rec);
		button.setFocusPainted(false);
	}
}
