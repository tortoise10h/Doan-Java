package gui;

import dto.ChiTietHoaDonXuatDTO;
import dto.HoaDonNhapDTO;
import dto.HoaDonXuatDTO;
import dto.MonDTO;
import util.MyRegex;
import bus.ChiTietHoaDonXuatBUS;
import bus.HoaDonNhapBUS;
import bus.HoaDonXuatBUS;
import bus.MonBUS;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class HoaDonGUI extends JPanel{
	private MonBUS monBus;
	
	private JPanel rightSide;
	private JPanel leftSide;
	
	private JPanel hdProccessPanel;
	
	private JComboBox fromDay;
	private JComboBox fromMonth;
	private JComboBox fromYear;
	private JComboBox toDay;
	private JComboBox toMonth;
	private JComboBox toYear;
	private JTextField fromPriceField;
	private JTextField toPriceField;
	private JButton searchButton;
	
	private JPanel hdPanel;
	private JPanel hdnPanel;
	private JPanel hdxPanel;
	
	private JPanel hdnBtn;
	private JPanel hdxBtn;
	
	private JTable hdnTable;
	private JTable hdxTable;
	private DefaultTableModel model;
	
	private JPanel hdxFormManagement;
	
	private JPanel chiTietHoaDonXuatBrand;
	private JPanel chiTietHoaDonXuatContentHeader;
	private JPanel chiTietHoaDonXuatContent;
	private JPanel chiTietHoaDonXuatFoot;
	
	private HoaDonXuatBUS hdxBus;
	private ChiTietHoaDonXuatBUS chiTietHoaDonXuatBus;
	
	private ArrayList<HoaDonNhapDTO> hdnList;
	private ArrayList<HoaDonXuatDTO> hdxList;
	private ArrayList<MonDTO> monList;
	
	
	private String currentTable;
	private int currentBillID;
	

	
	public JPanel getLeftSide() {
		return leftSide;
	}

	public void setLeftSide(JPanel leftSide) {
		this.leftSide = leftSide;
	}

	public ArrayList<HoaDonNhapDTO> getHdnList() {
		return hdnList;
	}

	public void setHdnList(ArrayList<HoaDonNhapDTO> hdnList) {
		this.hdnList = hdnList;
	}

	public ArrayList<HoaDonXuatDTO> getHdxList() {
		return hdxList;
	}

	public void setHdxList(ArrayList<HoaDonXuatDTO> hdxList) {
		this.hdxList = hdxList;
	}
	
	public JPanel getHdPanel() {
		return hdPanel;
	}

	public void setHdPanel(JPanel hdPanel) {
		this.hdPanel = hdPanel;
	}
	
	public HoaDonGUI() {
		init();
	}

	public void init() {
		setBounds(new Rectangle(0,0,1300,650));
		setLayout(null);
		setBackground(Color.decode("#ffffff"));
		
		monBus = new MonBUS();
		monList = monBus.getMonList();
		hdxBus = new HoaDonXuatBUS();
		hdxList = hdxBus.getHoaDonXuatList();
		
		initLeftSidePanel();
		initRightSidePanel();
	}
	
	public void initLeftSidePanel() {
		leftSide = new JPanel();
		leftSide.setLayout(null);
		leftSide.setBounds(0,0,800,650);
		
		hdPanelInit();
		switchButtonInit();
		formManagementInit();
		
		add(leftSide);
		
	}
	
	public void initRightSidePanel() {
		rightSide = new JPanel();
		rightSide.setLayout(null);
		rightSide.setBounds(new Rectangle(800,0,500,650));
		rightSide.setBackground(Color.decode("#ffffff"));
		
		add(rightSide);
	}
	
	public void hdProccessPanelInit() {
		hdProccessPanel = new JPanel();
		
	}
	
	/*CREATE TWO BUTTON TO SWITCH BETWEEN hoadonnhap TABLE and hoadonxuat TABLE*/
	public void switchButtonInit() {
		hdnBtn = new JPanel();
		hdxBtn = new JPanel();	
	    currentTable = "hdnTable";
		
		hdnBtn.setBounds(new Rectangle(0,170,100,30));
		hdnBtn.setName("hdnBtn");
		JLabel hdnBtnLb = new JLabel("Hóa đơn nhập");
		hdnBtnLb.setFont(new Font("Tahoma",Font.PLAIN,13));
		hdnBtn.add(hdnBtnLb);
		hdnBtn.setBackground(Color.decode("#47e084"));
		hdnBtn.setBorder(BorderFactory.createMatteBorder(1,1,0,1,Color.decode("#000000")));
		//ADD MOUST LISTENER, CLICK THIS BUTTON TO SWITCH TO hoadonnhap management
		addMouseListenerForSwitchBtn(hdnBtn,hdPanel, hdnPanel);
		
		hdxBtn.setBounds(new Rectangle(100,170,100,30));
		hdxBtn.setName("hdxBtn");
		JLabel hdxBtnLb = new JLabel("Hóa đơn xuất");
		hdxBtnLb.setFont(new Font("Tahoma",Font.PLAIN,13));
		hdxBtn.add(hdxBtnLb);
		hdxBtn.setBackground(Color.decode("#fcfcfc"));
		hdxBtn.setBorder(BorderFactory.createMatteBorder(1,0,0,1,Color.decode("#000000")));
		//ADD MOUST LISTENER, CLICK THIS BUTTON TO SWITCH TO hoadonxuat management
		addMouseListenerForSwitchBtn(hdxBtn,hdPanel, hdxPanel);
		
		leftSide.add(hdnBtn);
		leftSide.add(hdxBtn);
	}
	
	public void hdPanelInit() {
		hdPanel = new JPanel();
		hdPanel.setLayout(null);
		hdPanel.setBounds(0,200,800,450);
		
		hdnPanelInit();
		hdxPanelInit(hdxList);
		
		leftSide.add(hdPanel);
		
	}
	
	public void hdnPanelInit() {
		hdnPanel = new JPanel();
		hdnPanel.setLayout(null);
		hdnPanel.setBounds(new Rectangle(0,0,800,450));
		hdnList =  HoaDonNhapBUS.getDsHoaDonNhap();
		
		hdnTable = new JTable();
		hdnTable.getTableHeader().setOpaque(false);
		hdnTable.getTableHeader().setPreferredSize(new Dimension(800,40));
		hdnTable.setRowHeight(40);
        hdnTable.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {{
        	setBackground(Color.decode("#47e084"));
            setBorder(new EmptyBorder(0,0,0,0));
        }});
		hdnTable.setShowVerticalLines(false);
		JScrollPane scroll = new JScrollPane(hdnTable);
		
		Vector header = new Vector();
		header.add("Ma Hoa Don Nhap");
		header.add("Gio Nhap");
		header.add("Nhan Vien Xuat");
		header.add("Tong Tien");
		header.add("Thoi Gian Xuat");
		
		int i = 0;
		Vector row;
		model = new DefaultTableModel(header,0);
		while(i < hdnList.size()) {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
			row = new Vector();
			row.add(hdnList.get(i).getMaHoaDon());
			row.add(hdnList.get(i).getMaNcc());
			row.add(hdnList.get(i).getMaNv());
			row.add(hdnList.get(i).getTongTien());
			row.add(formatter.format(hdnList.get(i).getThoiGianXuat()));
			model.addRow(row);
			i++;
		}
		
		hdnTable.setModel(model);
		hdnTable.setBackground(Color.decode("#ffffff"));
		scroll.setBounds(new Rectangle(0,0,800,450));
		hdnPanel.add(scroll);
		hdPanel.add(hdnPanel);
	}
	
	public void hdxPanelInit(ArrayList<HoaDonXuatDTO> hoaDonXuatList) {
		hdxPanel = new JPanel();
		hdxPanel.setLayout(null);
		hdxPanel.setBounds(new Rectangle(0,0,800,450));
		
		chiTietHoaDonXuatBus = new ChiTietHoaDonXuatBUS();
		ArrayList<ChiTietHoaDonXuatDTO> chiTietHoaDonXuatList =  chiTietHoaDonXuatBus.getDsChiTietHoaDonXuat();
		
		
		hdxTable = new JTable();
		addListSelectionListenerForHoaDonXuatTable(hdxTable, chiTietHoaDonXuatList);
		hdxTable.getTableHeader().setOpaque(false);
		hdxTable.getTableHeader().setPreferredSize(new Dimension(800,40));
		hdxTable.setRowHeight(40);
        hdxTable.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {{
        	setBackground(Color.decode("#f93b64"));
            setBorder(new EmptyBorder(0,0,0,0));
        }});
		hdxTable.setShowVerticalLines(false);
		JScrollPane scroll = new JScrollPane(hdxTable);
		
		Vector header = new Vector();
		header.add("Mã hóa đơn xuất");
		header.add("Mã bàn");
		header.add("Mã nhân viên");
		header.add("Tổng tiền");
		header.add("Giờ nhập");
		header.add("Giờ xuất");
		
		int i = 0;
		Vector row;
		model = new DefaultTableModel(header,0);
		while(i < hoaDonXuatList.size()) {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
			row = new Vector();
			
			row.add(hoaDonXuatList.get(i).getMaHoaDon());
			row.add(hoaDonXuatList.get(i).getMaBan());
			row.add(hoaDonXuatList.get(i).getMaNv());
			row.add(hoaDonXuatList.get(i).getTongTien());
			row.add(formatter.format(hoaDonXuatList.get(i).getGioNhap()));
			row.add(formatter.format(hoaDonXuatList.get(i).getGioXuat()));
			model.addRow(row);
			i++;
		}
		
		hdxTable.setModel(model);
		hdxTable.setBackground(Color.decode("#ffffff"));
		scroll.setBounds(new Rectangle(0,0,800,450));
		hdxPanel.add(scroll);
		hdPanel.add(hdxPanel);
	}
	

	public void chiTietHoaDonXuatBrandInit(String maHoaDonXuat, String maBan, String maNv) {
		chiTietHoaDonXuatBrand = new JPanel();
		chiTietHoaDonXuatBrand.setBounds(new Rectangle(0,0,500,50));
		chiTietHoaDonXuatBrand.setLayout(null);
		chiTietHoaDonXuatBrand.setBackground(Color.decode("#ffffff"));
		chiTietHoaDonXuatBrand.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.decode("#f2f2f2")));
		
		JLabel hoaDonIcon;
		hoaDonIcon = addImageIconToLabel("src/images/application_icon/bill-detail-icon-x32.png",new Rectangle(5,5,32,32));
		
		JLabel maHoaDonXuatLb = new JLabel(maHoaDonXuat);
		maHoaDonXuatLb.setBounds(new Rectangle(40,5,30,30));
		maHoaDonXuatLb.setFont(new Font("Tahoma",Font.BOLD,20));
		maHoaDonXuatLb.setForeground(Color.decode("#f93b64"));
		
		JLabel banIcon;
		banIcon = addImageIconToLabel("src/images/application_icon/bill-detail-table-icon-x32.png", new Rectangle(200,5,32,32));
		
		JLabel tenBanLb = new JLabel(maBan);
		tenBanLb.setBounds(new Rectangle(240,5,50,30));
		tenBanLb.setFont(new Font("Tahoma",Font.BOLD,20));
		tenBanLb.setForeground(Color.decode("#f93b64"));
		
		JLabel nvienIcon;
		nvienIcon = addImageIconToLabel("src/images/application_icon/bill-detail-user-icon-x32.png", new Rectangle(380,5,32,32));
		
		JLabel nvienLb = new JLabel(maNv);
		nvienLb.setBounds(new Rectangle(420,5,70,30));
		nvienLb.setFont(new Font("Tahoma",Font.BOLD,20));
		nvienLb.setForeground(Color.decode("#f93b64"));
		
		chiTietHoaDonXuatBrand.add(hoaDonIcon);
		chiTietHoaDonXuatBrand.add(maHoaDonXuatLb);
		chiTietHoaDonXuatBrand.add(banIcon);
		chiTietHoaDonXuatBrand.add(tenBanLb);
		chiTietHoaDonXuatBrand.add(nvienIcon);
		chiTietHoaDonXuatBrand.add(nvienLb);
		
		rightSide.add(chiTietHoaDonXuatBrand);
	}
	
	public void chiTietHoaDonXuatContentHeaderInit() {
		chiTietHoaDonXuatContentHeader = new JPanel();
		chiTietHoaDonXuatContentHeader.setLayout(null);
		chiTietHoaDonXuatContentHeader.setBounds(new Rectangle(0,50,500,40));
		chiTietHoaDonXuatContentHeader.setBackground(Color.decode("#ffffff"));
		chiTietHoaDonXuatContentHeader.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.decode("#f2f2f2")));
		
		//FOR HEADER OF CHITIETHOADONXUAT PRODUCTS
		JLabel imgLb = new JLabel("Hình ảnh sản phẩm");
		formatCTHDXContentHeaderLabel(imgLb,new Rectangle(20,0,150,30));
		
		JLabel tenLb = new JLabel("Tên");
		formatCTHDXContentHeaderLabel(tenLb,new Rectangle(200,0,50,30));
		
		JLabel sluongLb = new JLabel("SL");
		formatCTHDXContentHeaderLabel(sluongLb,new Rectangle(330,0,50,30));
		
		JLabel donGiaLb = new JLabel("Đơn giá");
		formatCTHDXContentHeaderLabel(donGiaLb,new Rectangle(360,0,60,30));
		
		JLabel thanhTienLb = new JLabel("TT");
		formatCTHDXContentHeaderLabel(thanhTienLb,new Rectangle(450,0,50,30));
		
		chiTietHoaDonXuatContentHeader.add(imgLb);
		chiTietHoaDonXuatContentHeader.add(tenLb);
		chiTietHoaDonXuatContentHeader.add(sluongLb);
		chiTietHoaDonXuatContentHeader.add(donGiaLb);
		chiTietHoaDonXuatContentHeader.add(thanhTienLb);
		
		chiTietHoaDonXuatContentHeader.repaint();
		chiTietHoaDonXuatContentHeader.revalidate();
		
		rightSide.add(chiTietHoaDonXuatContentHeader);
	}
	
	public void chiTietHoaDonXuatContentInit(ArrayList<ChiTietHoaDonXuatDTO> chiTietHoaDonXuatList, ArrayList<MonDTO> monList) {
		chiTietHoaDonXuatContent = new JPanel();
		chiTietHoaDonXuatContent.setBounds(new Rectangle(0,95,500,455));
		chiTietHoaDonXuatContent.setBackground(Color.decode("#ffffff"));
		chiTietHoaDonXuatContent.setLayout(new GridBagLayout());
		JScrollPane scroll = new JScrollPane(chiTietHoaDonXuatContent);
		scroll.getVerticalScrollBar().setPreferredSize(new Dimension(8,100));
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setBorder(new EmptyBorder(0,0,0,0));
		
		GridBagConstraints constraints = new GridBagConstraints();
		Insets inset = new Insets(10,10,10,10);
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = inset;
		
		for(int i = 0; i < chiTietHoaDonXuatList.size(); i++) {
			constraints.gridx = 0;
			BufferedImage image;
			JLabel productImg;
			try {
				image = ImageIO.read(new File("src/" + monList.get(i).getImgLink()));
				productImg = new JLabel(new ImageIcon(image));
				chiTietHoaDonXuatContent.add(productImg,constraints);
				constraints.gridx++;
			}catch(IOException e) {
				System.out.println("Read file error: ");
				e.printStackTrace();
			}
			
			JLabel tenMon = new JLabel(monList.get(i).getTenMon(), SwingConstants.LEFT);
			chiTietHoaDonXuatContent.add(tenMon,constraints);
			tenMon.setPreferredSize(new Dimension(115,30));
			constraints.gridx++;
			
			JLabel soLuong = new JLabel(Integer.toString(chiTietHoaDonXuatList.get(i).getSoLuong()) + " " + monList.get(i).getDonViTinh());
			chiTietHoaDonXuatContent.add(soLuong,constraints);
			constraints.gridx++;
			
			JLabel donGia = new JLabel(Integer.toString(chiTietHoaDonXuatList.get(i).getDonGia()) + "đ");
			chiTietHoaDonXuatContent.add(donGia,constraints);
			donGia.setPreferredSize(new Dimension(30,30));
			constraints.gridx++;
			
			JLabel thanhTien = new JLabel(Integer.toString(chiTietHoaDonXuatList.get(i).getThanhTien()) + "đ");
			chiTietHoaDonXuatContent.add(thanhTien,constraints);
			donGia.setPreferredSize(new Dimension(50,30));
			constraints.gridx++;
			
			constraints.gridy++;
			
		}
		
		chiTietHoaDonXuatContent.repaint();
		chiTietHoaDonXuatContent.revalidate();
		scroll.setBounds(new Rectangle(0,95,500,455));
		
		
		rightSide.add(scroll);
	}
	
	public void chiTietHoaDonXuatFootInit(ArrayList<ChiTietHoaDonXuatDTO> chiTietHoaDonXuatList) {
		chiTietHoaDonXuatFoot = new JPanel();
		chiTietHoaDonXuatFoot.setLayout(null);
		chiTietHoaDonXuatFoot.setBounds(new Rectangle(0,550,500,100));
		chiTietHoaDonXuatFoot.setBackground(Color.decode("#ffffff"));
		
		JLabel tongTienLb = new JLabel("Tổng tiền: ");
		tongTienLb.setFont(new Font("Tahoma", Font.PLAIN,25));
		tongTienLb.setBounds(new Rectangle(20,10,150,50));
		
		int tongTien = 0;
		for(ChiTietHoaDonXuatDTO cthdx : chiTietHoaDonXuatList) {
			tongTien += cthdx.getThanhTien();
		}
		
		JLabel tongTienNum = new JLabel(Integer.toString(tongTien) + "đ");
		tongTienNum.setForeground(Color.decode("#f93b64"));
		tongTienNum.setFont(new Font("Tahoma",Font.BOLD, 25));
		tongTienNum.setBounds(new Rectangle(180,10,200,50));
		
		
		JPanel delButton = new JPanel();
		delButton.setBackground(Color.decode("#f93b64"));
		delButton.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.decode("#f93b64")));
		delButton.setBounds(new Rectangle(420,15,80,35));
		delButton.setLayout(null);
		BufferedImage delBtnImage;
		JLabel delBtnIcon;
		try {
			delBtnImage = ImageIO.read(new File("src/images/application_icon/trash-icon-white-x16.png"));
			delBtnIcon = new JLabel(new ImageIcon(delBtnImage));
			delBtnIcon.setBounds(new Rectangle(10,10,16,16));
			delButton.add(delBtnIcon);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JLabel delButtonName = new JLabel("Xóa");
		delButtonName.setForeground(Color.decode("#ffffff"));
		delButtonName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		delButtonName.setBounds(new Rectangle(30,7,50,20));
		delButton.add(delButtonName);
		addMouseListenerForDelButton(delButton);
		
		chiTietHoaDonXuatFoot.add(tongTienLb);
		chiTietHoaDonXuatFoot.add(tongTienNum);
		chiTietHoaDonXuatFoot.add(delButton);
		
		rightSide.add(chiTietHoaDonXuatFoot);
	}
	
	public void formManagementInit() {
		hdxFormManagement = new JPanel();
		hdxFormManagement.setBounds(new Rectangle(0,0,800,250));
		hdxFormManagement.setBackground(Color.decode("#f2f2f2"));
		
		ArrayList<String> dayList = new ArrayList<String>();
		ArrayList<String> monthList = new ArrayList<String>();
		ArrayList<String> yearList = new ArrayList<String>();
		
		//Initialize day, month and year for combo box
		//day
		for(int i = 0; i <= 31; i++) {
			dayList.add(Integer.toString(i));
		}
		//month
		for(int i = 0; i <= 12; i++) {
			monthList.add(Integer.toString(i));
		}
		//year
		for(int i = Calendar.getInstance().get(Calendar.YEAR); i >= 1999; i--) {
			yearList.add(Integer.toString(i));
		}
		
		fromPriceField = new JTextField("Nhập vào giá tiền");
		toPriceField = new JTextField("Nhập vào giá tiền");
		fromDay = new JComboBox(dayList.toArray());
		toDay = new JComboBox(dayList.toArray());
		fromMonth = new JComboBox(monthList.toArray());
		toMonth = new JComboBox(monthList.toArray());
		fromYear = new JComboBox(yearList.toArray());
		toYear = new JComboBox(yearList.toArray());
		searchButton = new JButton("Lọc hóa đơn");
		
		
		
		hdxFormManagement.setLayout(null);
			
		//FILTER BY DATE
		
		JLabel fromDayLb = new JLabel("Ngày");
		fromDayLb.setBounds(new Rectangle(20,10,50,30));
		fromDay.setBounds(new Rectangle(70,10,50,30));
		fromDay.setBackground(Color.decode("#f2f2f2"));
		
		JLabel fromMonthLb = new JLabel("Tháng");
		fromMonthLb.setBounds(new Rectangle(130,10,50,30));
		fromMonth.setBounds(new Rectangle(180,10,50,30));
		fromMonth.setBackground(Color.decode("#f2f2f2"));
		
		JLabel fromYearLb = new JLabel("Năm");
		fromYearLb.setBounds(new Rectangle(240,10,50,30));
		fromYear.setBounds(new Rectangle(290,10,80,30));
		fromYear.setBackground(Color.decode("#f2f2f2"));
		
		JLabel bridgeSymbol = new JLabel(" - ",SwingConstants.CENTER);
		bridgeSymbol.setBounds(new Rectangle(370,10,50,30));
		bridgeSymbol.setFont(new Font("Tahoma",Font.PLAIN,25));
		
		JLabel toDayLb = new JLabel("Ngày");
		toDayLb.setBounds(new Rectangle(420,10,50,30));
		toDay.setBounds(new Rectangle(470,10,50,30));
		toDay.setBackground(Color.decode("#f2f2f2"));
		
		JLabel toMonthLb = new JLabel("Tháng");
		toMonthLb.setBounds(new Rectangle(530,10,50,30));
		toMonth.setBounds(new Rectangle(580,10,50,30));
		toMonth.setBackground(Color.decode("#f2f2f2"));
		
		JLabel toYearLb = new JLabel("Năm");
		toYearLb.setBounds(new Rectangle(640,10,50,30));
		toYear.setBounds(new Rectangle(690,10,80,30));
		toYear.setBackground(Color.decode("#f2f2f2"));
		
		
		
		//FILTER BY PRICE
		JLabel fromPriceLb = new JLabel("Từ giá");
		fromPriceLb.setBounds(new Rectangle(100,60,60,30));
		formatTextField(fromPriceField,"Nhập vào giá tiền");
		fromPriceField.setBounds(new Rectangle(190,60,200,30));
		
		JLabel toPriceLb = new JLabel("Đến giá");
		toPriceLb.setBounds(new Rectangle(410,60,70,30));
		formatTextField(toPriceField,"Nhập vào giá tiền");
		toPriceField.setBounds(new Rectangle(490,60,200,30));
		
		searchButton.setBounds(350,110,150,30);
		addActionListenerForSearchButton(searchButton);
		formatButton(searchButton);
		
		
		
		hdxFormManagement.add(fromPriceLb);
		hdxFormManagement.add(fromPriceField);
		hdxFormManagement.add(toPriceLb);
		hdxFormManagement.add(toPriceField);
		
		hdxFormManagement.add(fromDayLb);
		hdxFormManagement.add(fromDay);
		hdxFormManagement.add(fromMonthLb);
		hdxFormManagement.add(fromMonth);
		hdxFormManagement.add(fromYearLb);
		hdxFormManagement.add(fromYear);
		hdxFormManagement.add(bridgeSymbol);
		hdxFormManagement.add(toDayLb);
		hdxFormManagement.add(toDay);
		hdxFormManagement.add(toMonthLb);
		hdxFormManagement.add(toMonth);
		hdxFormManagement.add(toYearLb);
		hdxFormManagement.add(toYear);
		
		hdxFormManagement.add(searchButton);
		
		
		leftSide.add(hdxFormManagement);
	}
	
	public void hoaDonXuatFilter() {
		ArrayList<HoaDonXuatDTO> hoaDonXuatList;
		hoaDonXuatList = filterByDate();
	    hoaDonXuatList = filterByPrice(hoaDonXuatList);
		
		if(currentTable.equals("hdxTable")) {
			//repaint table
			hdPanel.removeAll();
			hdxPanelInit(hoaDonXuatList);
			hdPanel.repaint();
			hdPanel.revalidate();
		}
	}
	
	public ArrayList<HoaDonXuatDTO> filterByDate() {
		String fromDayValue = fromDay.getSelectedItem().toString();
		String fromMonthValue = fromMonth.getSelectedItem().toString();
		String fromYearValue = fromYear.getSelectedItem().toString();
		
		String toDayValue = toDay.getSelectedItem().toString();
		String toMonthValue = toMonth.getSelectedItem().toString();
		String toYearValue = toYear.getSelectedItem().toString();

		//Make sure every month and day all have 2 characters (dd or MM)
		if(fromDayValue.length() < 2) {
			fromDayValue = "0" + fromDayValue;
		}
		if(fromMonthValue.length() < 2) {
			fromMonthValue = "0" + fromMonthValue;
		}
		if(toDayValue.length() < 2) {
			toDayValue = "0" + toDayValue;
		}
		if(toMonthValue.length() < 2) {
			toMonthValue = "0" + toMonthValue;
		}
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date fromDate = null;
		Date toDate = null;
		
		//parse time from string to date type
		try {
			fromDate = dateFormat.parse(fromDayValue + "/" + fromMonthValue + "/" + fromYearValue);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			toDate = dateFormat.parse(toDayValue + "/" + toMonthValue + "/" + toYearValue);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//This array list hold all bill that sastify date filter
		ArrayList<HoaDonXuatDTO> hoaDonXuatListTemp = new ArrayList<HoaDonXuatDTO>();
	
		for(int i = 0; i < hdxList.size(); i++) {
			//parse dd/mm/yyyy hh:mm:ss of gioXuat instance to just dd/mm/yyyy to compare with date from user input
			String str = dateFormat.format(hdxList.get(i).getGioXuat());
			Date temp = null;
			try {
				temp = dateFormat.parse(str);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(temp.equals(fromDate) || temp.after(fromDate) && temp.before(toDate) || temp.equals(toDate)) {
				hoaDonXuatListTemp.add(hdxList.get(i));
			}
		}
		
		return hoaDonXuatListTemp;
	}
	
	public ArrayList<HoaDonXuatDTO> filterByPrice(ArrayList<HoaDonXuatDTO> hoaDonXuatList){
		ArrayList<HoaDonXuatDTO> hoaDonXuatListTemp = new ArrayList<HoaDonXuatDTO>();
		
		int fromPriceValue;
		int toPriceValue;
		boolean is_changed = false;
		
		if(fromPriceField.getText().equals("Nhập vào giá tiền") == false && toPriceField.getText().equals("Nhập vào giá tiền") == false) {
			fromPriceValue = Integer.parseInt(fromPriceField.getText());
			toPriceValue = Integer.parseInt(toPriceField.getText());
			for(int i = 0; i < hoaDonXuatList.size(); i++) {
				if(hoaDonXuatList.get(i).getTongTien() >= fromPriceValue && hoaDonXuatList.get(i).getTongTien() <= toPriceValue) {
					hoaDonXuatListTemp.add(hoaDonXuatList.get(i));
					is_changed = true;
				}
			}
		}else if(fromPriceField.getText().equals("Nhập vào giá tiền") == false && toPriceField.getText().equals("Nhập vào giá tiền") == true) {
			fromPriceValue = Integer.parseInt(fromPriceField.getText());
			for(int i = 0; i < hoaDonXuatList.size(); i++) {
				if(hoaDonXuatList.get(i).getTongTien() >= fromPriceValue) {
					hoaDonXuatListTemp.add(hoaDonXuatList.get(i));
					is_changed = true;
				}
			}
		}else if(fromPriceField.getText().equals("Nhập vào giá tiền") == true && toPriceField.getText().equals("Nhập vào giá tiền") == false) {
			toPriceValue = Integer.parseInt(toPriceField.getText());
			for(int i = 0; i < hoaDonXuatList.size(); i++) {
				if(hoaDonXuatList.get(i).getTongTien() <= toPriceValue) {
					hoaDonXuatListTemp.add(hoaDonXuatList.get(i));
					is_changed = true;
				}
			}
		}else {
			hoaDonXuatListTemp = hoaDonXuatList;
		}
		
		
//		JOptionPane.showMessageDialog(null,fromPriceValue + " - " + toPriceValue);
		return hoaDonXuatListTemp;
	}
	
	public void addActionListenerForSearchButton(JButton btn) {
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
//				JOptionPane.showMessageDialog(null,"You got it");
				hoaDonXuatFilter();
			}
			
		});
	}
	
	public void addMouseListenerForDelButton(JPanel delButton) {
		delButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(JOptionPane.showConfirmDialog(null,"Bạn có chắc muốn xóa hóa đơn này hay không") == JOptionPane.YES_OPTION) {
					if(currentTable.equals("hdxTable")) {
						for(int i = 0; i < hdxList.size(); i++) {
							if(hdxList.get(i).getMaHoaDon() == currentBillID) {
								hdxList.remove(i);
								hdPanel.removeAll();
								hdxPanelInit(hdxList);
								hdPanel.repaint();
								hdPanel.revalidate();
								break;
							}
						}
						hdxBus.deleteBill(currentBillID);
						chiTietHoaDonXuatBus.deleteBillDetail(currentBillID);
					}
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				
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
	
	public void addMouseListenerForSwitchBtn(JPanel switchBtnPanel, JPanel parentPanel, JPanel childPanel) {
		switchBtnPanel.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				switchTable(parentPanel,childPanel);
				if(switchBtnPanel.getName().equals("hdnBtn")) {
					currentTable = "hdnTable";
					hdnBtn.setBackground(Color.decode("#47e084"));
					hdxBtn.setBackground(Color.decode("#ffffff"));
				}else if(switchBtnPanel.getName().equals("hdxBtn")){
					currentTable = "hdxTable";
					hdnBtn.setBackground(Color.decode("#ffffff"));
					hdxBtn.setBackground(Color.decode("#f93b64"));
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
	
	
	
	
	public void switchTable(JPanel parentPanel, JPanel childPanel) {
		//removing panel
		parentPanel.removeAll();
		parentPanel.repaint();
		parentPanel.revalidate();
		
//		//adding panel
		parentPanel.add(childPanel);
		parentPanel.repaint();
		parentPanel.revalidate();
	}
	
	public void formatButton(JButton button) {
		button.setBackground(Color.decode("#ffffff"));
	}
	
	public void formatTextField(JTextField txt, String placeholderString) {
		txt.setBorder(new EmptyBorder(0,0,0,0));
		txt.setOpaque(false);
		txt.setBorder(BorderFactory.createMatteBorder(0,0,2,0,Color.decode("#000000")));
		txt.setFont(new Font("Tahomo", Font.PLAIN, 14));
		txt.setHorizontalAlignment(JTextField.CENTER);
		
		txt.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				if(txt.getText().equals(placeholderString)) {
					txt.setText("");
					txt.setForeground(Color.decode("#000000"));
					txt.repaint();
					txt.revalidate();
				}
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				if(txt.getText().equals("")) {
					txt.setText(placeholderString);
					txt.setForeground(Color.decode("#000000"));
					txt.repaint();
					txt.revalidate();
				}
			}
			
		});
	}
		
	public JLabel addImageIconToLabel(String imgLink, Rectangle rec) {
		BufferedImage image;
		JLabel label = null;
		try {
			image = ImageIO.read(new File(imgLink));
			label = new JLabel(new ImageIcon(image));
			label.setBounds(rec);
		}catch(IOException e) {
			System.out.println("Read file error: ");
			e.printStackTrace();
		}
		
		return label;
	}
	
	public void formatCTHDXContentHeaderLabel(JLabel label, Rectangle rec) {
		label.setBounds(rec);
		label.setFont(new Font("Tahoma",Font.BOLD,14));
		label.setForeground(Color.decode("#f93b64"));
	}
	
	public void addListSelectionListenerForHoaDonXuatTable(JTable hdxTable, ArrayList<ChiTietHoaDonXuatDTO> chiTietHoaDonXuatList) {
		ListSelectionModel select = hdxTable.getSelectionModel();
		select.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				int rowIndex = hdxTable.getSelectedRow();
				int maHoaDonAtRow = Integer.parseInt(hdxTable.getModel().getValueAt(rowIndex,0).toString());
				currentBillID = maHoaDonAtRow;
				
				//for test
				ArrayList<ChiTietHoaDonXuatDTO> chiTietHoaDonXuatTempList = new ArrayList<ChiTietHoaDonXuatDTO>();
				ArrayList<MonDTO> monTempList = new ArrayList<MonDTO>();
				
				for(ChiTietHoaDonXuatDTO cthdx : chiTietHoaDonXuatList) {
					if(maHoaDonAtRow == cthdx.getMaHoaDonXuat()) {
						chiTietHoaDonXuatTempList.add(cthdx);
					}
				}
				
				for(ChiTietHoaDonXuatDTO cthdx : chiTietHoaDonXuatTempList) {
					for(MonDTO mon : monList) {
						if(cthdx.getMaMon().equals(mon.getMaMon())) {
							monTempList.add(mon);
							break;
						}
					}
				}
				
//				System.out.println(monTempList.size() + "--" + chiTietHoaDonXuatTempList.size());
				
				rightSide.removeAll();
				chiTietHoaDonXuatBrandInit(hdxTable.getModel().getValueAt(rowIndex,0).toString(),hdxTable.getModel().getValueAt(rowIndex,1).toString(),hdxTable.getModel().getValueAt(rowIndex,2).toString());
				chiTietHoaDonXuatContentHeaderInit();
				chiTietHoaDonXuatContentInit(chiTietHoaDonXuatTempList, monTempList);
				chiTietHoaDonXuatFootInit(chiTietHoaDonXuatTempList);
				rightSide.repaint();
				rightSide.revalidate();
				
			}
			
		});
	}
}
