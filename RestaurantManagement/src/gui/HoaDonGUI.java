package gui;

import dto.ChiTietHoaDonNhapDTO;
import dto.ChiTietHoaDonXuatDTO;
import dto.HoaDonNhapDTO;
import dto.HoaDonXuatDTO;
import dto.MonDTO;
import dto.NhaCungCapDTO;
import dto.NguyenLieuDTO;
import util.ComponentFormat;
import util.MyRegex;
import bus.ChiTietHoaDonNhapBUS;
import bus.ChiTietHoaDonXuatBUS;
import bus.HoaDonNhapBUS;
import bus.HoaDonXuatBUS;
import bus.MonBUS;
import bus.NguyenLieuBUS;
import bus.NhaCungCapBUS;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class HoaDonGUI extends JPanel{
	private MonBUS monBus;
	
	private JPanel rightSide;
	private JPanel leftSide;
	
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
	
	private JTextField searchTable;
	private JComboBox hdxSearchTableChoices;
	private JComboBox hdnSearchTableChoices;
	
	private DefaultTableModel hdxModel;
	private TableRowSorter<TableModel> hdxRowSorter;
	
	private JPanel hdxFormManagement;
	
	private JButton createHoaDonNhap;
	private JPanel chiTietHoaDonXuatBrand;
	private JPanel chiTietHoaDonXuatContentHeader;
	private JPanel chiTietHoaDonXuatContent;
	private JPanel chiTietHoaDonXuatFoot;
	
	private JPanel hoaDonNhapForm;
	private JPanel chiTietHoaDonNhapBrand;
	private JPanel chiTietHoaDonNhapContent;
	private JPanel chiTietHoaDonNhapFoot;
	
	private HoaDonXuatBUS hdxBus;
	private HoaDonNhapBUS hdnBus;
	private ChiTietHoaDonXuatBUS chiTietHoaDonXuatBus;
	private ChiTietHoaDonNhapBUS chiTietHoaDonNhapBus;
	private NguyenLieuBUS nguyenLieuBus;
	private NhaCungCapBUS nhaCungCapBus;
	
	private ArrayList<HoaDonNhapDTO> hdnList;
	private ArrayList<HoaDonXuatDTO> hdxList;
	private ArrayList<NguyenLieuDTO> nguyenLieuList;
	private ArrayList<NhaCungCapDTO> nhaCungCapList;
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
		hdxBus = new HoaDonXuatBUS();
		hdnBus = new HoaDonNhapBUS();
		nguyenLieuBus = new NguyenLieuBUS();
		nhaCungCapBus = new NhaCungCapBUS();
		
		monList = monBus.getMonList();
		hdxList = hdxBus.getHoaDonXuatList();
		hdnList = hdnBus.getHoaDonNhapList();
		chiTietHoaDonXuatBus = new ChiTietHoaDonXuatBUS();
		chiTietHoaDonNhapBus = new ChiTietHoaDonNhapBUS();
		nguyenLieuList = nguyenLieuBus.getNguyenLieuList();
		nhaCungCapList = nhaCungCapBus.getNhaCungCapList();
		
		initLeftSidePanel();
		initRightSidePanel();
	}
	
	public void initLeftSidePanel() {
		leftSide = new JPanel();
		leftSide.setLayout(null);
		leftSide.setBounds(0,0,800,650);
		
		searchTableInit();
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
	
	/*CREATE TEXTFIELD TO SEARCH ON TABLE*/
	public void searchTableInit() {
		searchTable = new JTextField();
		searchTable.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				//when focus on text field jtable will be repainted to avoid bug cause by list selection listener row table
				if(currentTable.equals("hdxTable")) {
					hdPanel.removeAll();
					hdxPanelInit(hdxList);
					hdPanel.repaint();
					hdPanel.revalidate();
				}
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		searchTable.setBounds(new Rectangle(600,170,200,30));
		
		ArrayList<String> hdnChoices = new ArrayList<String>();
		hdnChoices.add("Mã hóa đơn");
		hdnChoices.add("Mã nhà cung cấp");
		hdnChoices.add("Mã nhân viên");
		
		ArrayList<String> hdxChoices = new ArrayList<String>();
		hdxChoices.add("Mã hóa đơn");
		hdxChoices.add("Mã bàn");
		hdxChoices.add("Mã nhân viên");
		
		hdnSearchTableChoices = new JComboBox(hdnChoices.toArray());
		hdnSearchTableChoices.setBounds(new Rectangle(500,170,100,30));
		hdnSearchTableChoices.setBackground(Color.decode("#f2f2f2"));
		
		
		hdxSearchTableChoices = new JComboBox(hdxChoices.toArray());
		hdxSearchTableChoices.setBounds(new Rectangle(500,170,100,30));
		hdxSearchTableChoices.setBackground(Color.decode("#f2f2f2"));
		hdxSearchTableChoices.setVisible(false);
		
		leftSide.add(searchTable);
		leftSide.add(hdxSearchTableChoices);
		leftSide.add(hdnSearchTableChoices);
	}
	
	public void hdPanelInit() {
		hdPanel = new JPanel();
		hdPanel.setLayout(null);
		hdPanel.setBounds(0,200,800,450);
		
		hdnPanelInit(hdnList);
		hdxPanelInit(hdxList);
		
		leftSide.add(hdPanel);
		
	}
	
	public void hdnPanelInit(ArrayList<HoaDonNhapDTO> hoaDonNhapList) {
		hdnPanel = new JPanel();
		hdnPanel.setLayout(null);
		hdnPanel.setBounds(new Rectangle(0,0,800,450));
		
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
		
		ArrayList<ChiTietHoaDonNhapDTO> chiTietHoaDonNhapList =  chiTietHoaDonNhapBus.getAllChiTietHoaDonNhap();
		
		Vector header = new Vector();
		header.add("Mã hóa đơn nhập");
		header.add("Mã nhà cung cấp");
		header.add("Nhân viên");
		header.add("Tổng tiền");
		header.add("Giờ Xuất");
		
		int i = 0;
		Vector row;
		hdxModel = new DefaultTableModel(header,0);
		while(i < hdnList.size()) {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
			row = new Vector();
			row.add(hoaDonNhapList.get(i).getMaHoaDonNhap());
			row.add(hoaDonNhapList.get(i).getMaNhaCungCap());
			row.add(hoaDonNhapList.get(i).getNhanVien());
			row.add(hoaDonNhapList.get(i).getTongTien());
			row.add(formatter.format(hoaDonNhapList.get(i).getGioXuat()));
			hdxModel.addRow(row);
			i++;
		}
		
		hdnTable.setModel(hdxModel);
		hdnTable.setBackground(Color.decode("#ffffff"));
		addListSelectionListenerForHoaDonNhapTable(hdnTable, chiTietHoaDonNhapList);
		
		scroll.setBounds(new Rectangle(0,0,800,450));
		hdnPanel.add(scroll);
		hdPanel.add(hdnPanel);
	}
	
	public void chiTietHoaDonNhapInit() {
		hoaDonNhapForm = new JPanel();
		hoaDonNhapForm.setLayout(null);
		hoaDonNhapForm.setBounds(new Rectangle(0,0,500,700));
		hoaDonNhapForm.setBackground(Color.decode("#ffffff"));

		
		rightSide.add(hoaDonNhapForm);
	}
	
	public void chiTietHoaDonNhapBrandInit() {
		chiTietHoaDonNhapBrand = new JPanel();
		chiTietHoaDonNhapBrand.setBounds(new Rectangle(0,0,500,150));
		chiTietHoaDonNhapBrand.setLayout(null);
		chiTietHoaDonNhapBrand.setBackground(Color.decode("#ffffff"));
		chiTietHoaDonNhapBrand.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("#f2f2f2")));
		
		
		JLabel headerLb = new JLabel("CHI TIẾT PHIẾU NHẬP");
		formatLabel(headerLb,"#000000", new Rectangle(120,20,300,50), new Font("Tahoma",Font.BOLD,25));
		
		JLabel hoaDonIcon;
		hoaDonIcon = addImageIconToLabel("src/images/application_icon/bill-detail-icon-x32.png",new Rectangle(5,90,32,32));
		
		JLabel maHoaDonNhapLb = new JLabel("49");
		formatLabel(maHoaDonNhapLb,"#39d376", new Rectangle(40,90,30,30), new Font("Tahoma",Font.BOLD,20));
		
		JLabel nvienIcon;
		nvienIcon = addImageIconToLabel("src/images/application_icon/bill-detail-user-icon-x32.png", new Rectangle(330,90,32,32));
		
		JLabel nvienLb = new JLabel("tortoise10h");
		formatLabel(nvienLb,"#39d376", new Rectangle(370,90,170,30), new Font("Tahoma",Font.BOLD,20));
		
		chiTietHoaDonNhapBrand.add(headerLb);
		chiTietHoaDonNhapBrand.add(hoaDonIcon);
		chiTietHoaDonNhapBrand.add(maHoaDonNhapLb);
		chiTietHoaDonNhapBrand.add(nvienIcon);
		chiTietHoaDonNhapBrand.add(nvienLb);
		
		rightSide.add(chiTietHoaDonNhapBrand);
	}
	
	public void chiTietHoaDonNhapContentInit(HoaDonNhapDTO hoaDonNhap, ChiTietHoaDonNhapDTO chiTietHoaDonNhap, NguyenLieuDTO nguyenLieu, NhaCungCapDTO nhaCungCap) {
		chiTietHoaDonNhapContent = new JPanel();
		chiTietHoaDonNhapContent.setLayout(null);
		chiTietHoaDonNhapContent.setBounds(new Rectangle(0,150,500,400));
		chiTietHoaDonNhapContent.setBackground(Color.decode("#ffffff"));
		chiTietHoaDonNhapContent.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("#f2f2f2")));
		
		JLabel nhaCungCapLb = new JLabel("Nhà cung cấp:");
		formatLabel(nhaCungCapLb,"#000000",new Rectangle(10,30,150,30),new Font("Tahoma", Font.PLAIN,18));
		JLabel nhaCungCapName =  new JLabel(nhaCungCap.getTenNhaCungCap());
		formatLabel(nhaCungCapName,"#39d376",new Rectangle(160,30,250,30),new Font("Tahoma", Font.BOLD,18));
		
		JLabel nhapHangLb = new JLabel("Nhập vào:");
		formatLabel(nhapHangLb,"#000000",new Rectangle(10,80,150,30),new Font("Tahoma", Font.PLAIN,18));
		JLabel hangName = new JLabel(chiTietHoaDonNhap.getSoLuong() + " " + chiTietHoaDonNhap.getDonViTinh() + " " + nguyenLieu.getTen());
		formatLabel(hangName,"#39d376",new Rectangle(160,80,300,30),new Font("Tahoma", Font.BOLD,18));
		
		JLabel nhanVienLb = new JLabel("Nhân viên:");
		formatLabel(nhanVienLb,"#000000",new Rectangle(10,130,150,30),new Font("Tahoma", Font.PLAIN,18));
		JLabel username = new JLabel("tortoise10h");
		formatLabel(username,"#39d376",new Rectangle(160,130,300,30),new Font("Tahoma", Font.BOLD,18));
		
		JLabel gioXuatLb = new JLabel("Giờ xuất: ");
		formatLabel(gioXuatLb,"#000000",new Rectangle(10,180,150,30),new Font("Tahoma", Font.PLAIN,18));
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		JLabel gioXuat = new JLabel(dateFormat.format(hoaDonNhap.getGioXuat()));
		formatLabel(gioXuat,"#39d376",new Rectangle(160,180,300,30),new Font("Tahoma", Font.BOLD,18));
		
		chiTietHoaDonNhapContent.add(nhaCungCapLb);
		chiTietHoaDonNhapContent.add(nhaCungCapName);
		chiTietHoaDonNhapContent.add(nhapHangLb);
		chiTietHoaDonNhapContent.add(hangName);
		chiTietHoaDonNhapContent.add(nhanVienLb);
		chiTietHoaDonNhapContent.add(username);
		chiTietHoaDonNhapContent.add(gioXuatLb);
		chiTietHoaDonNhapContent.add(gioXuat);
		
		rightSide.add(chiTietHoaDonNhapContent);
	}
	
	public void chiTietHoaDonNhapFootInit(ChiTietHoaDonNhapDTO chiTietHoaDonNhap) {
		chiTietHoaDonNhapFoot = new JPanel();
		chiTietHoaDonNhapFoot.setLayout(null);
		chiTietHoaDonNhapFoot.setBounds(new Rectangle(0,550,500,100));
		chiTietHoaDonNhapFoot.setBackground(Color.decode("#ffffff"));
		
		JLabel tongTienLb = new JLabel("Tổng tiền: ");
		formatLabel(tongTienLb,"#000000", new Rectangle(20,10,150,50), new Font("Tahoma", Font.PLAIN,25));
		
		JLabel tongTienNum = new JLabel(Integer.toString(chiTietHoaDonNhap.getThanhTien()) + "đ");
		formatLabel(tongTienNum,"#39d376", new Rectangle(180,10,200,50), new Font("Tahoma",Font.BOLD, 25));
		
		
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
//		
		JLabel delButtonName = new JLabel("Xóa");
		delButtonName.setForeground(Color.decode("#ffffff"));
		delButtonName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		delButtonName.setBounds(new Rectangle(30,7,50,20));
		delButton.add(delButtonName);
		addMouseListenerForDelButton(delButton);
		
		chiTietHoaDonNhapFoot.add(tongTienLb);
		chiTietHoaDonNhapFoot.add(tongTienNum);
		chiTietHoaDonNhapFoot.add(delButton);
		
		rightSide.add(chiTietHoaDonNhapFoot);
	}
	
	public void hdxPanelInit(ArrayList<HoaDonXuatDTO> hoaDonXuatList) {
		hdxPanel = new JPanel();
		hdxPanel.setLayout(null);
		hdxPanel.setBounds(new Rectangle(0,0,800,450));
		
		ArrayList<ChiTietHoaDonXuatDTO> chiTietHoaDonXuatList =  chiTietHoaDonXuatBus.getDsChiTietHoaDonXuat();
		
		//Create table view
		hdxTable = new JTable();
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
		hdxModel = new DefaultTableModel(header,0);
		while(i < hoaDonXuatList.size()) {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
			row = new Vector();
			
			row.add(hoaDonXuatList.get(i).getMaHoaDon());
			row.add(hoaDonXuatList.get(i).getMaBan());
			row.add(hoaDonXuatList.get(i).getMaNv());
			row.add(hoaDonXuatList.get(i).getTongTien());
			row.add(formatter.format(hoaDonXuatList.get(i).getGioNhap()));
			row.add(formatter.format(hoaDonXuatList.get(i).getGioXuat()));
			hdxModel.addRow(row);
			i++;
		}
		
		hdxTable.setModel(hdxModel);
		
		//for table sort by type on searchTable text field
		hdxRowSorter = new TableRowSorter<>(hdxTable.getModel());
		tableSortFilter(hdxTable,hdxModel);
		addListSelectionListenerForHoaDonXuatTable(hdxTable, chiTietHoaDonXuatList);
		
		hdxTable.setBackground(Color.decode("#ffffff"));
		scroll.setBounds(new Rectangle(0,0,800,450));
		hdxPanel.add(scroll);
		hdPanel.add(hdxPanel);
	}
	
	public void tableSortFilter(JTable table, DefaultTableModel tableModel) {
		table.setRowSorter(hdxRowSorter);
		
		searchTable.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				String text = searchTable.getText();
				int choice = hdxSearchTableChoices.getSelectedIndex();
				if(text.trim().length() == 0) {
					hdxRowSorter.setRowFilter(null);
				}else {
					hdxRowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text,choice));
				}
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				int choice = hdxSearchTableChoices.getSelectedIndex();
				String text = searchTable.getText();

                if (text.trim().length() == 0) {
                    hdxRowSorter.setRowFilter(null);
                } else {
                    hdxRowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text,choice));
                }
			}
			
		});
	}
	
	
	//CREATE chiTietHoaDonXuat DISPLAY ON THE RIGHT OF PANEL
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
	
	
	//CREATE FILTER BY DATE AND FILTER BY PRICE VIEW
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
	
		addActionListenerForSearchButton(searchButton);
		ComponentFormat.formatButton(searchButton, "#ffffff", "#000000", new Rectangle(350,110,150,30));
		
		
		//CREATE hoaDonNhap BUTTON
		createHoaDonNhap =  new JButton("Tạo hóa đơn nhập");
		createHoaDonNhap.setBackground(Color.decode("#ffffff"));
		createHoaDonNhap.setForeground(Color.decode("#34bc6a"));
		createHoaDonNhap.setBounds(new Rectangle(0,130,200,30));
		createHoaDonNhapAction(createHoaDonNhap);
		
		
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
		
		hdxFormManagement.add(createHoaDonNhap);
		
		
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
								//reload table after delete row
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
				switchPanel(parentPanel,childPanel);
				if(switchBtnPanel.getName().equals("hdnBtn")) {
					currentTable = "hdnTable";
					hdnBtn.setBackground(Color.decode("#47e084"));
					hdxBtn.setBackground(Color.decode("#ffffff"));
					chiTietHoaDonNhapInit();
					switchPanel(rightSide, hoaDonNhapForm);
					
					hdnSearchTableChoices.setVisible(true);
					hdxSearchTableChoices.setVisible(false);
					createHoaDonNhap.setVisible(true);
				}else if(switchBtnPanel.getName().equals("hdxBtn")){
					currentTable = "hdxTable";
					
					hdnBtn.setBackground(Color.decode("#ffffff"));
					hdxBtn.setBackground(Color.decode("#f93b64"));
					
					rightSide.removeAll();
					rightSide.repaint();
					rightSide.revalidate();
					
					hdnSearchTableChoices.setVisible(false);
					hdxSearchTableChoices.setVisible(true);
					createHoaDonNhap.setVisible(false);
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
	
	public void createHoaDonNhapAction(JButton createHoaDonNhapBtn) {
		createHoaDonNhap.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				createHoaDonNhapForm();
			}
			
		});
	}
	
	public void createHoaDonNhapForm() {
		JDialog taoHoaDonNhapDiaglog = new JDialog();
		taoHoaDonNhapDiaglog.setPreferredSize(new Dimension(450,400));
		taoHoaDonNhapDiaglog.setLocationByPlatform(true);
		
		//init an array of nhaCungCap name
		ArrayList<String> nhaCungCapNameList = new ArrayList<String>();
		nhaCungCapNameList.add("Không");
		for(int i = 0; i < nhaCungCapList.size(); i++) {
			nhaCungCapNameList.add(nhaCungCapList.get(i).getTenNhaCungCap());
		}
		
		//init an array of nguyenLieu name
		ArrayList<String> nguyenLieuNameList = new ArrayList<String>();
		nguyenLieuNameList.add("Không");
		for(int i = 0; i < nguyenLieuList.size(); i++) {
			nguyenLieuNameList.add(nguyenLieuList.get(i).getTen());
		}
		
		//CREATE GUI FOR hoaDonNhap FORM
		JPanel hoaDonNhapForm = new JPanel();
		hoaDonNhapForm.setLayout(null);
		hoaDonNhapForm.setBackground(Color.decode("#ffffff"));
		
		JLabel headerLb = new JLabel("TẠO PHIẾU NHẬP");
		formatLabel(headerLb, "#34bc6a", new Rectangle(125,20,300,30), new Font("Tahoma",Font.BOLD, 25));
		
		
		JLabel nguyenLieuLb = new JLabel("Nguyên liệu");
		formatLabel(nguyenLieuLb, "#000000", new Rectangle(10,140,110,30), new Font("Tahoma",Font.PLAIN, 16));
		
		JLabel donViTinhLb = new JLabel("");
		formatLabel(donViTinhLb, "#000000", new Rectangle(400,140,50,30), new Font("Tahoma",Font.PLAIN, 16));
		
		JComboBox nguyenLieuGroup = new JComboBox(nguyenLieuNameList.toArray());
		nguyenLieuGroup.setEnabled(false);
		nguyenLieuGroup.setBackground(Color.decode("#ffffff"));
		nguyenLieuGroup.setBounds(new Rectangle(120,140,100,30));
		
		JLabel nhaCungCapLb = new JLabel("Nhà cung cấp");
		formatLabel(nhaCungCapLb, "#000000", new Rectangle(10,80,110,30), new Font("Tahoma",Font.PLAIN, 16));
		
		JComboBox nhaCungCapGroup = new JComboBox(nhaCungCapNameList.toArray());
		nhaCungCapGroup.setBackground(Color.decode("#ffffff"));
		nhaCungCapGroup.setBounds(new Rectangle(120,80,290,30));
		nhaCungCapGroup.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				/* set text for nguyenLieu combobox suit for nhaCungCap */
				NhaCungCapDTO nhaCungCap = nhaCungCapList.get(nhaCungCapGroup.getSelectedIndex() - 1);
				
				//find position of nguyenLieu index
				int nguyenLieuComboBoxIndex = 0;
				for(int i = 0; i < nguyenLieuList.size(); i++) {
					if(nguyenLieuList.get(i).getMaNhaCungCap().equals(nhaCungCap.getMaNhaCungCap())) {
						nguyenLieuComboBoxIndex = i;
					}
				}
				
				nguyenLieuGroup.setSelectedIndex(nguyenLieuComboBoxIndex + 1); //+1 because 0 is default value "Không"
				donViTinhLb.setText(nguyenLieuList.get(nguyenLieuComboBoxIndex).getDonViTinh());
			}
			
		});
		
		
		//add listener when choose nguyenLieu will display suitable donViTinh
//		nguyenLieuGroup.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				donViTinhLb.setText(nguyenLieuList.get(nguyenLieuGroup.getSelectedIndex() - 1).getDonViTinh());
//			}
//			
//		});
		
		JLabel soLuongLb = new JLabel("Số lượng");
		formatLabel(soLuongLb, "#000000", new Rectangle(240,140,70,30), new Font("Tahoma",Font.PLAIN, 16));
		
		JTextField soLuongField = new JTextField();
		soLuongField.setOpaque(false);
		soLuongField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("#000000")));
		soLuongField.setBounds(new Rectangle(330,140,50,30));
		soLuongField.setFont(new Font("Tahoma",Font.PLAIN, 16));
		
		
		
		JButton exportBillButton = new JButton("XUẤT HÓA ĐƠN");
		ComponentFormat.formatButton(exportBillButton, "#34bc6a", "#ffffff", new Rectangle(125,200,200,30));
		exportBillButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null,"Bạn có chắc là muốn xuất hóa đơn hay không?") == JOptionPane.YES_OPTION) {
					if(nhaCungCapGroup.getSelectedItem().equals("Không") || nguyenLieuGroup.getSelectedItem().equals("Không") || soLuongField.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Vui lòng chọn và điền đầy đủ các thông tin trước khi xuất hóa đơn");
					}else {
						if(isParseStringToDouble(soLuongField.getText())) {
							//Get object equivalent with data from JComboBox
							NhaCungCapDTO nhaCungCap = nhaCungCapList.get(nhaCungCapGroup.getSelectedIndex() - 1);
							NguyenLieuDTO nguyenLieu = nguyenLieuList.get(nguyenLieuGroup.getSelectedIndex() - 1);
							double soLuong = Double.parseDouble(soLuongField.getText());
							int tongTien = (int)(soLuong * nguyenLieu.getGia());
							
							//create hoaDonNhap with data from JComboBox and JTextField
							HoaDonNhapDTO hoaDonNhap = new HoaDonNhapDTO();
							hoaDonNhap.setMaNhaCungCap(nhaCungCap.getMaNhaCungCap());
							hoaDonNhap.setNhanVien("tortoise10h");
							hoaDonNhap.setTongTien(tongTien);
							
							
							//export import bill
							hdnBus.exportBill(hoaDonNhap);
							
							ArrayList<HoaDonNhapDTO> hoaDonNhapList = new ArrayList<HoaDonNhapDTO>();
							hoaDonNhapList = hdnBus.getAllHoaDonNhap();
							int lastMaHoaDonNhap = hoaDonNhapList.get(hoaDonNhapList.size() - 1).getMaHoaDonNhap();
							
							//export chiTietHoaDonNhap
							chiTietHoaDonNhapBus.saveChiTietHoaDonNhap(lastMaHoaDonNhap,nguyenLieu.getMaNguyenLieu(),nguyenLieu.getGia(),soLuong,nguyenLieu.getDonViTinh(),tongTien);
							
							//Update soLuong for nguyenLieu table
							nguyenLieuBus.updateSoluong(nguyenLieu.getMaNguyenLieu(), soLuong);
							
							hdnList.add(hoaDonNhapList.get(hoaDonNhapList.size() - 1));
							
							hdPanel.removeAll();
							hdnPanelInit(hdnList);
							hdPanel.repaint();
							hdPanel.revalidate();
							
							
							JOptionPane.showMessageDialog(null,"Xuất hóa đơn nhập thành công");
							taoHoaDonNhapDiaglog.dispose();
						}else {
							JOptionPane.showMessageDialog(null, "Vui lòng chỉ nhập vào số vào ô số lượng");
						}
					}
				}
			}
			
		});
		
		hoaDonNhapForm.add(headerLb);
		hoaDonNhapForm.add(nhaCungCapLb);
		hoaDonNhapForm.add(nhaCungCapGroup);
		hoaDonNhapForm.add(nguyenLieuLb);
		hoaDonNhapForm.add(nguyenLieuGroup);
		hoaDonNhapForm.add(soLuongLb);
		hoaDonNhapForm.add(soLuongField);
		hoaDonNhapForm.add(donViTinhLb);
		hoaDonNhapForm.add(exportBillButton);
		
		
		taoHoaDonNhapDiaglog.add(hoaDonNhapForm);
		taoHoaDonNhapDiaglog.pack();
		taoHoaDonNhapDiaglog.setVisible(true);
	}
	
	
	public void switchPanel(JPanel parentPanel, JPanel childPanel) {
		//removing panel
		parentPanel.removeAll();
		parentPanel.repaint();
		parentPanel.revalidate();
		
//		//adding panel
		parentPanel.add(childPanel);
		parentPanel.repaint();
		parentPanel.revalidate();
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
	
	public void formatLabel(JLabel label, String foreground, Rectangle rec, Font font) {
		label.setFont(font);
		label.setBounds(rec);
		label.setForeground(Color.decode(foreground));
		
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
				int rowIndex = hdxTable.getSelectedRow();
//				int modelRow = hdxTable.convertRowIndexToModel(rowIndex);
//				int maHoaDonAtRow = Integer.parseInt(hdxTable.getModel().getValueAt(modelRow,0).toString());
				int maHoaDonAtRow = Integer.parseInt(hdxTable.getValueAt(rowIndex,0).toString());
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
	
	public void addListSelectionListenerForHoaDonNhapTable(JTable hdnTable, ArrayList<ChiTietHoaDonNhapDTO> chiTietHoaDonNhapList) {
		ListSelectionModel select = hdnTable.getSelectionModel();
		select.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				int rowIndex = hdnTable.getSelectedRow();
				int maHoaDonAtRow = Integer.parseInt(hdnTable.getValueAt(rowIndex,0).toString());
				String maNhaCungCap = hdnTable.getValueAt(rowIndex,1).toString();
				currentBillID = maHoaDonAtRow;
				
				//for test
				ChiTietHoaDonNhapDTO chiTietHoaDonNhap = new ChiTietHoaDonNhapDTO();
				
				for(ChiTietHoaDonNhapDTO cthdn : chiTietHoaDonNhapList) {
					if(maHoaDonAtRow == cthdn.getMaHoaDonNhap()) {
						chiTietHoaDonNhap = cthdn;
					}
				}
				
				//get nguyenLieu from maNguyenLieu from chiTietHoaDonNhap
				String maNguyenLieu = chiTietHoaDonNhap.getMaNguyenLieu();
				
				//Create object to get information
				NguyenLieuDTO nguyenLieu = nguyenLieuBus.getNguyenLieu(maNguyenLieu);
				NhaCungCapDTO nhaCungCap = nhaCungCapBus.getNhaCungCap(maNhaCungCap);
				HoaDonNhapDTO hoaDonNhap = hdnBus.getHoaDonNhap(maHoaDonAtRow);
				
				
				
				rightSide.removeAll();
				chiTietHoaDonNhapBrandInit();
				chiTietHoaDonNhapContentInit(hoaDonNhap, chiTietHoaDonNhap, nguyenLieu, nhaCungCap);
				chiTietHoaDonNhapFootInit(chiTietHoaDonNhap);
				rightSide.repaint();
				rightSide.revalidate();
				
			}
			
		});
	}
	
	public boolean isParseStringToDouble(String doubleString) {
		double num;
		try {
			num = Double.parseDouble(doubleString);
			return true;
		}catch(NumberFormatException nfe) {
			return false;
		}
	}
}
