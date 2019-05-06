package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import dto.ChiTietHoaDonXuatDTO;
import dto.ChiTietMonDTO;
import dto.MonDTO;
import dto.NguyenLieuDTO;
import dto.NhaCungCapDTO;
import util.ComponentFormat;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import bus.ChiTietMonBUS;
import bus.MonBUS;
import bus.NguyenLieuBUS;
import bus.NhaCungCapBUS;
public class SanPhamGUI extends JPanel{
	private ArrayList<NhaCungCapDTO> nhaCungCapList = new ArrayList<NhaCungCapDTO>();
	private ArrayList<NguyenLieuDTO> nguyenLieuList = new ArrayList<NguyenLieuDTO>();
	private ArrayList<MonDTO> monList = new ArrayList<MonDTO>();
	
	private NhaCungCapBUS nhaCungCapBus = new NhaCungCapBUS();
	private NguyenLieuBUS nguyenLieuBus = new NguyenLieuBUS();
	private MonBUS monBus = new MonBUS();
	private ChiTietMonBUS chiTietMonBus = new ChiTietMonBUS();
	
	private JPanel leftSide;
	private JPanel rightSide;
	
	private JPanel leftSideTableZone;
	
	private JButton nhaCungCapSwitchButton;
	private JButton nguyenLieuSwitchButton;
	private JButton monSwitchButton;
	
	private JPanel nguyenLieuTablePanel;
	private JPanel nhaCungCapTablePanel;
	private JPanel monTablePanel;
	
	private JTable nguyenLieuTable;
	private JTable nhaCungCapTable;
	private JTable monTable;
	
	private DefaultTableModel nguyenLieuModel;
	private DefaultTableModel nhaCungCapModel;
	private DefaultTableModel monModel;
	
	private String currentTable;
	
	private JPanel monFormManagement;
	private JPanel nguyenLieuFormManagement;
	private JPanel nhaCungCapFormManagement;
	
	
	private JTextField maMonField;
	private JTextField tenMonField;
	private JTextField loaiMonField;
	private JTextField monGiaField;
	private JTextField monDonViTinhField;
	private JCheckBox[] nguyenLieuChoices;
	private JTextField[] nguyenLieuChoicesQuantity;
	private ArrayList<NguyenLieuDTO> userNguyenLieuChoose = new ArrayList<NguyenLieuDTO>();
	private ArrayList<Double> userNguyenLieuChosenQuantity = new ArrayList<Double>();
	
	private JTextField maNguyenLieuField;
	private JTextField tenNguyenLieuField;
	private JTextField nguyenLieuSoLuongField;
	private JTextField nguyenLieuDonViTinhField;
	private JTextField nguyenLieuGiaField;
	private JTextField maNhaCungCapField;
	
	private JTextField maNccField;
	private JTextField tenNhaCungCapField;
	
	private BufferedImage userImageChoose;
	private String imageChooseFormat;
	private File userSelectedFile;
	
	private JPanel editButton;
	private JPanel deleteButton;
	private JPanel resetButton;
	private JPanel addButton;
	private final JFileChooser fileChooser = new JFileChooser();
	
	public SanPhamGUI() {
		init();
	}
	
	public void init() {
		setBounds(new Rectangle(0,0,1300,650));
		setBackground(Color.decode("#ffffff"));
		setLayout(null);
		
		arrayListInit();
		
		currentTable = "monTable";
		
		leftSideInit();
		rightSideInit();
	}
	
	public void arrayListInit() {
		nhaCungCapList = nhaCungCapBus.getNhaCungCapList();
		nguyenLieuList = nguyenLieuBus.getNguyenLieuList();
		monList = monBus.getMonList();
		
	}
	
	public void leftSideInit() {
		leftSide = new JPanel();
		leftSide.setLayout(null);
		leftSide.setBounds(new Rectangle(0,0,900,650));
		leftSide.setBackground(Color.decode("#f2f2f2"));
		
		leftSideTableZoneInit();
		switchButtonInit();
		
		add(leftSide);
	}
	
	public void rightSideInit() {
		rightSide = new JPanel();
		rightSide.setLayout(null);
		rightSide.setBounds(new Rectangle(900,0,400,650));
		rightSide.setBackground(Color.decode("#ffffff"));
		
		MonDTO mon = new MonDTO();
		mon.setImgLink("images/products/default-product-image.png");
		
		monFormManagementInit(mon);
		
		add(rightSide);
	}
	
	public void leftSideTableZoneInit() {
		leftSideTableZone = new JPanel();
		leftSideTableZone.setLayout(null);
		leftSideTableZone.setBounds(new Rectangle(0,200,900,450));
		
		monTableInit(monList);
		nguyenLieuTableInit(nguyenLieuList);
		nhaCungCapTableInit(nhaCungCapList);
		
		leftSide.add(leftSideTableZone);
	}
	
	public void switchButtonInit() {
		nhaCungCapSwitchButton = new JButton("Nhà cung cấp");
		nguyenLieuSwitchButton = new JButton("Nguyên liệu");
		monSwitchButton = new JButton("Món");
		
		nhaCungCapSwitchButton.setName("nhaCungCapSwitchButton");
		nguyenLieuSwitchButton.setName("nguyenLieuSwitchButton");
		monSwitchButton.setName("monSwitchButton");
		
		
		ComponentFormat.formatButton(monSwitchButton,"#e22b31", "#000000", new Rectangle(0,170,100,30));
		monSwitchButton.setBorder(BorderFactory.createMatteBorder(1,1,0,1,Color.decode("#000000")));
		
		ComponentFormat.formatButton(nguyenLieuSwitchButton, "#ffffff", "#000000", new Rectangle(100,170,100,30));
		nguyenLieuSwitchButton.setBorder(BorderFactory.createMatteBorder(1,0,0,1,Color.decode("#000000")));
		
		ComponentFormat.formatButton(nhaCungCapSwitchButton, "#ffffff", "#000000", new Rectangle(200,170,100,30));
		nhaCungCapSwitchButton.setBorder(BorderFactory.createMatteBorder(1,0,0,1,Color.decode("#000000")));
		
		switchTablePanelAction(monSwitchButton,leftSideTableZone,monTablePanel);
		switchTablePanelAction(nguyenLieuSwitchButton,leftSideTableZone,nguyenLieuTablePanel);
		switchTablePanelAction(nhaCungCapSwitchButton,leftSideTableZone,nhaCungCapTablePanel);
		
		leftSide.add(monSwitchButton);
		leftSide.add(nguyenLieuSwitchButton);
		leftSide.add(nhaCungCapSwitchButton);
	}
	
	public void switchTablePanelAction(JButton button, JPanel parentPanel, JPanel childPanel) {
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				switchPanel(parentPanel,childPanel);
				userChosenReset();
				if(button.getName().equals("monSwitchButton")) {
					currentTable = "monTable";
					monSwitchButton.setBackground(Color.decode("#e22b31"));
					nguyenLieuSwitchButton.setBackground(Color.decode("#ffffff"));
					nhaCungCapSwitchButton.setBackground(Color.decode("#ffffff"));
					
					MonDTO mon = new MonDTO();
					mon.setImgLink("images/products/default-product-image.png");
					monFormManagementInit(mon);
					switchPanel(rightSide,monFormManagement);
				}else if(button.getName().equals("nguyenLieuSwitchButton")) {
					currentTable = "nguyenLieuTable";
					nguyenLieuSwitchButton.setBackground(Color.decode("#5ac6f4"));
					monSwitchButton.setBackground(Color.decode("#ffffff"));
					nhaCungCapSwitchButton.setBackground(Color.decode("#ffffff"));
					
					NguyenLieuDTO nguyenLieu = new NguyenLieuDTO();
					nguyenLieuFormManagementInit(nguyenLieu);
					switchPanel(rightSide,nguyenLieuFormManagement);
				}else if(button.getName().equals("nhaCungCapSwitchButton")) {
					currentTable = "nhaCungCapTable";
					nhaCungCapSwitchButton.setBackground(Color.decode("#47e084"));
					nguyenLieuSwitchButton.setBackground(Color.decode("#ffffff"));
					monSwitchButton.setBackground(Color.decode("#ffffff"));
					
					NhaCungCapDTO nhaCungCap = new NhaCungCapDTO();
					nhaCungCapFormManagementInit(nhaCungCap);
					switchPanel(rightSide,nhaCungCapFormManagement);
				}
			}
			
		});
	}
	
	public void monTableInit(ArrayList<MonDTO> monList) {
		monTablePanel = new JPanel();
		monTablePanel.setLayout(null);
		monTablePanel.setBounds(new Rectangle(0,0,900,450));
		
		monTable = new JTable();
		tableFormat(monTable,"#e22b31","#ffffff");
		JScrollPane scroll = new JScrollPane(monTable);
		
		Vector header = new Vector();
		header.add("Mã món");
		header.add("Tên món");
		header.add("Loại món");
		header.add("Giá tiền");
		header.add("Đơn vị tính");
		
		int i = 0;
		Vector row;
		monModel = new DefaultTableModel(header,0);
		while(i < monList.size()) {
			row = new Vector();
			row.add(monList.get(i).getMaMon());
			row.add(monList.get(i).getTenMon());
			row.add(monList.get(i).getMaLoaiMon());
			row.add(monList.get(i).getGia());
			row.add(monList.get(i).getDonViTinh());
			monModel.addRow(row);
			i++;
		}
		
		monTable.setModel(monModel);
		addListSelectionListenerForTable(monTable);
		
		//for table sort by type of search field
//		hdnRowSorter = new TableRowSorter<>(hdnTable.getModel());
//		tableSortFilter(hdnTable,hdnModel);
		
		
//		addListSelectionListenerForHoaDonNhapTable(hdnTable, chiTietHoaDonNhapList);	
		scroll.setBounds(new Rectangle(0,0,900,450));
		monTablePanel.add(scroll);
		leftSideTableZone.add(monTablePanel);
	}
	
	
	public void nguyenLieuTableInit(ArrayList<NguyenLieuDTO> nguyenLieuList) {
		nguyenLieuTablePanel = new JPanel();
		nguyenLieuTablePanel.setLayout(null);
		nguyenLieuTablePanel.setBounds(new Rectangle(0,0,900,450));
		
		nguyenLieuTable = new JTable();
		tableFormat(nguyenLieuTable,"#5ac6f4","#ffffff");
		JScrollPane scroll = new JScrollPane(nguyenLieuTable);
		
		Vector header = new Vector();
		header.add("Mã");
		header.add("Tên nguyên liệu");
		header.add("Số lượng");
		header.add("Đvi Tính");
		header.add("Giá");
		header.add("Mã nhà cung cấp");
		
		int i = 0;
		Vector row;
		nguyenLieuModel = new DefaultTableModel(header,0);
		while(i < nguyenLieuList.size()) {
			row = new Vector();
			row.add(nguyenLieuList.get(i).getMaNguyenLieu());
			row.add(nguyenLieuList.get(i).getTen());
			row.add(nguyenLieuList.get(i).getSoLuong());
			row.add(nguyenLieuList.get(i).getDonViTinh());
			row.add(nguyenLieuList.get(i).getGia());
			row.add(nguyenLieuList.get(i).getMaNhaCungCap());
			
			nguyenLieuModel.addRow(row);
			i++;
		}
		
		nguyenLieuTable.setModel(nguyenLieuModel);
		addListSelectionListenerForTable(nguyenLieuTable);
		
		//for table sort by type of search field
//			hdnRowSorter = new TableRowSorter<>(hdnTable.getModel());
//			tableSortFilter(hdnTable,hdnModel);
		
		
//			addListSelectionListenerForHoaDonNhapTable(hdnTable, chiTietHoaDonNhapList);	
		scroll.setBounds(new Rectangle(0,0,900,450));
		nguyenLieuTablePanel.add(scroll);
		leftSideTableZone.add(nguyenLieuTablePanel);
	}
	
	public void nhaCungCapTableInit(ArrayList<NhaCungCapDTO> nhaCungCapList) {
		nhaCungCapTablePanel = new JPanel();
		nhaCungCapTablePanel.setLayout(null);
		nhaCungCapTablePanel.setBounds(new Rectangle(0,0,900,450));
		
		nhaCungCapTable = new JTable();
		tableFormat(nhaCungCapTable,"#47e084","#ffffff");
		JScrollPane scroll = new JScrollPane(nhaCungCapTable);
		
		Vector header = new Vector();
		header.add("Mã nhà cung cấp");
		header.add("Tên nhà cung cấp");
		
		int i = 0;
		Vector row;
		nhaCungCapModel = new DefaultTableModel(header,0);
		while(i < nhaCungCapList.size()) {
			row = new Vector();
			row.add(nhaCungCapList.get(i).getMaNhaCungCap());
			row.add(nhaCungCapList.get(i).getTenNhaCungCap());
			nhaCungCapModel.addRow(row);
			i++;
		}
		
		nhaCungCapTable.setModel(nhaCungCapModel);
		addListSelectionListenerForTable(nhaCungCapTable);
		
		//for table sort by type of search field
//			hdnRowSorter = new TableRowSorter<>(hdnTable.getModel());
//			tableSortFilter(hdnTable,hdnModel);
		
		
//			addListSelectionListenerForHoaDonNhapTable(hdnTable, chiTietHoaDonNhapList);	
		scroll.setBounds(new Rectangle(0,0,900,450));
		nhaCungCapTablePanel.add(scroll);
		leftSideTableZone.add(nhaCungCapTablePanel);
	}
	
	public void addListSelectionListenerForTable(JTable table) {
		ListSelectionModel select = table.getSelectionModel();
		select.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(currentTable.equals("monTable")) {
					monFormDisplay(table);
				}else if(currentTable.equals("nguyenLieuTable")) {
					nguyenLieuFormDisplay(table);
				}else if(currentTable.equals("nhaCungCapTable")) {
					
				}
			}
			
		});
	}
	
	public void monFormDisplay(JTable table) {
		int rowIndex = table.getSelectedRow();
		String idAtRow = table.getValueAt(rowIndex,0).toString();
//		currentBillID = maHoaDonAtRow;
		
		MonDTO selectedMon = new MonDTO();
		for(int i = 0; i < monList.size(); i++) {
			if(monList.get(i).getMaMon().equals(idAtRow)) {
				selectedMon = monList.get(i);
				break;
			}
		}
		
		
		rightSide.removeAll();
		monFormManagementInit(selectedMon);
		maMonField.setEditable(false);
		rightSide.repaint();
		rightSide.revalidate();
	}
	
	
	public void nguyenLieuFormDisplay(JTable table) {
		int rowIndex = table.getSelectedRow();
		String maNguyenLieuAtRow = table.getValueAt(rowIndex,0).toString();
//		currentBillID = maHoaDonAtRow;
		
		NguyenLieuDTO selectedNguyenLieu = new NguyenLieuDTO();
		for(int i = 0; i < nguyenLieuList.size(); i++) {
			if(nguyenLieuList.get(i).getMaNguyenLieu().equals(maNguyenLieuAtRow)) {
				selectedNguyenLieu = nguyenLieuList.get(i);
				break;
			}
		}
		
		
		rightSide.removeAll();
		nguyenLieuFormManagementInit(selectedNguyenLieu);
		maNguyenLieuField.setEditable(false);
		rightSide.repaint();
		rightSide.revalidate();
	}
	
	public void tableFormat(JTable table, String headerColor, String backgroundColor) {
		table.getTableHeader().setOpaque(false);
		table.getTableHeader().setPreferredSize(new Dimension(800,40));
		table.setRowHeight(40);
		table.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {{
        	setBackground(Color.decode(headerColor));
            setBorder(new EmptyBorder(0,0,0,0));
        }});
		table.setShowVerticalLines(false);
		table.setBackground(Color.decode("#ffffff"));
		table.setForeground(Color.decode("#000000"));
	}
	
	public void monFormManagementInit(MonDTO mon) {
		monFormManagement = new JPanel();
		monFormManagement.setLayout(null);
		monFormManagement.setBounds(new Rectangle(0,0,400,650));
		monFormManagement.setBackground(Color.decode("#ffffff"));
		
		String imgLink = "src/" + mon.getImgLink(); 
		
		monFormManagementImagePanelInit(imgLink);
		monFormManagementFieldInit(mon);
		formManagementHandleButtonInit(monFormManagement);
		
		rightSide.add(monFormManagement);
		
	}
	
	
	public void monFormManagementImagePanelInit(String imgLink) {
		JPanel imgPanel = new JPanel();
		imgPanel.setLayout(null);
		imgPanel.setBounds(new Rectangle(30,30,340,152));
		imgPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode("#000000")));
		imgPanel.setBackground(Color.decode("#ffffff"));
		
		JLabel imgLb;
		BufferedImage image;
		try {
			image = ImageIO.read(new File(imgLink));
			imgLb = new JLabel(new ImageIcon(image));
			imgLb.setBounds(new Rectangle(95,20,150,112));
			imgPanel.add(imgLb);
		}catch(IOException e) {
			System.out.println("Read file error: ");
			e.printStackTrace();
		}
		
		monFormManagement.add(imgPanel);
	}
	
	public void monFormManagementFieldInit(MonDTO mon) {
		JLabel maMonLb = new JLabel("Mã món");
		ComponentFormat.formatLabel(maMonLb, "#000000", new Rectangle(30,220,100,30), new Font("Tahoma",Font.PLAIN,16));
		maMonField = new JTextField(mon.getMaMon());
		ComponentFormat.formatTextField(maMonField, new Rectangle(140,220,230,30), "#e22b31", "#ffffff", new Font("Tahoma",Font.BOLD, 16));
		
		JLabel tenMonLb = new JLabel("Tên món");
		ComponentFormat.formatLabel(tenMonLb, "#000000", new Rectangle(30,280,100,30), new Font("Tahoma",Font.PLAIN,16));
		tenMonField = new JTextField(mon.getTenMon());
		ComponentFormat.formatTextField(tenMonField, new Rectangle(140,280,230,30), "#e22b31", "#ffffff", new Font("Tahoma",Font.BOLD, 16));
		
		JLabel loaiMonLb = new JLabel("Loại món");
		ComponentFormat.formatLabel(loaiMonLb, "#000000", new Rectangle(30,340,100,30), new Font("Tahoma",Font.PLAIN,16));
		loaiMonField = new JTextField(mon.getMaLoaiMon());
		ComponentFormat.formatTextField(loaiMonField, new Rectangle(140,340,230,30), "#e22b31", "#ffffff", new Font("Tahoma",Font.BOLD, 16));
		
		JLabel giaLb = new JLabel("Giá tiền");
		ComponentFormat.formatLabel(giaLb, "#000000", new Rectangle(30,400,100,30), new Font("Tahoma",Font.PLAIN,16));
		monGiaField = new JTextField(Integer.toString(mon.getGia()));
		ComponentFormat.formatTextField(monGiaField, new Rectangle(140,400,230,30), "#e22b31", "#ffffff", new Font("Tahoma",Font.BOLD, 16));
		
		JLabel donViTinhLb = new JLabel("Đơn vị tính");
		ComponentFormat.formatLabel(donViTinhLb, "#000000", new Rectangle(30,460,100,30), new Font("Tahoma",Font.PLAIN,16));
		monDonViTinhField = new JTextField(mon.getDonViTinh());
		ComponentFormat.formatTextField(monDonViTinhField, new Rectangle(140,460,230,30), "#e22b31", "#ffffff", new Font("Tahoma",Font.BOLD, 16));
		
		JButton chooseMonMaterial = new JButton("Chọn nguyên liệu cho món");
		ComponentFormat.formatButton(chooseMonMaterial, "#ffffff", "#000000", new Rectangle(30,510,340,30));
		addChooseMaterialAction(chooseMonMaterial);
		
		monFormManagement.add(maMonLb);
		monFormManagement.add(maMonField);
		monFormManagement.add(tenMonLb);
		monFormManagement.add(tenMonField);
		monFormManagement.add(loaiMonLb);
		monFormManagement.add(loaiMonField);
		monFormManagement.add(giaLb);
		monFormManagement.add(monGiaField);
		monFormManagement.add(donViTinhLb);
		monFormManagement.add(monDonViTinhField);
		monFormManagement.add(chooseMonMaterial);
	}
	
	public void addChooseMaterialAction(JButton button) {
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String maMonFromField;
				if(maMonField.isEditable() == false) {
					//if maMonField edit table is false that's mean user click on given mon then we will get that mon id
					// and we will check nguyen lieu suitable with that mon
					maMonFromField = maMonField.getText();
				}else {
					//if maMonField edit table is true that's mean user is creating new mon
					//so we won't check any nguyen lieu
					maMonFromField = "";
				}
				createMaterialDashboard(maMonFromField);
			}
			
		});
	}
	
	public void createMaterialDashboard(String maMon) {
		JDialog materialDashBoard = new JDialog();
		materialDashBoard.setPreferredSize(new Dimension(450,400));
		materialDashBoard.setLocationByPlatform(true);
		
		nguyenLieuList = nguyenLieuBus.getAllNguyenLieu();
		
		JPanel dashBoardPanel = new JPanel();
		dashBoardPanel.setLayout(null);
		dashBoardPanel.setBackground(Color.decode("#ffffff"));
		
		JLabel dashBoaradTitle = new JLabel("CHỌN NGUYÊN LIỆU CHO MÓN ĂN");
		ComponentFormat.formatLabel(dashBoaradTitle, "#e22b31", new Rectangle(50,10,430,30), new Font("Tahoma", Font.BOLD, 20));
		
		nguyenLieuChoices = new JCheckBox[nguyenLieuList.size()];
		nguyenLieuChoicesQuantity = new JTextField[nguyenLieuList.size()];
		nguyenLieuChoicesInit(dashBoardPanel,40,70,maMon);
		
		JButton saveNguyenLieuChosen = new JButton("Lưu nguyên liệu cho món ăn");
		ComponentFormat.formatButton(saveNguyenLieuChosen, "#ffffff", "#000000", new Rectangle(50,320,350,30));
		addSaveActionForSaveNguyenLieuChosenButton(saveNguyenLieuChosen,materialDashBoard);
		
		
		dashBoardPanel.add(dashBoaradTitle);
		dashBoardPanel.add(saveNguyenLieuChosen);
		
		materialDashBoard.add(dashBoardPanel);
		materialDashBoard.pack();
		materialDashBoard.setVisible(true);
	}
	
	public void nguyenLieuChoicesInit(JPanel dashBoard, int x, int y, String maMon) {
		ArrayList<ChiTietMonDTO> chiTietMonList = chiTietMonBus.getChiTietOfMon(maMon);
		for(int i = 0; i < nguyenLieuList.size(); i++) {
			if(x > 410) {
				x = 40;
				y += 30;
			}
			nguyenLieuChoices[i] = new JCheckBox(nguyenLieuList.get(i).getTen());
			ComponentFormat.formatCheckBox(nguyenLieuChoices[i], "#ffffff", "#000000", new Rectangle(x,y,100,30), new Font("Tahoma", Font.PLAIN,14));
			x += 110;
			nguyenLieuChoicesQuantity[i] = new JTextField();
			ComponentFormat.formatTextField(nguyenLieuChoicesQuantity[i], new Rectangle(x,y,50,30), "#000000", "#ffffff", new Font("Tahoma", Font.PLAIN,14));
			nguyenLieuChoicesQuantity[i].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("#000000")));
			x += 80;
			
			for(int j = 0; j < chiTietMonList.size(); j++) {
				if(nguyenLieuList.get(i).getMaNguyenLieu().equals(chiTietMonList.get(j).getMaNguyenLieu())) {
					nguyenLieuChoices[i].setSelected(true);
					nguyenLieuChoicesQuantity[i].setText(Double.toString(chiTietMonList.get(j).getSoNguyenLieu()));
					break;
				}
			}
			
			dashBoard.add(nguyenLieuChoices[i]);
			dashBoard.add(nguyenLieuChoicesQuantity[i]);
		}
		
	}
	
	public void addSaveActionForSaveNguyenLieuChosenButton(JButton button, JDialog dialog) {
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Bạn có chắc chắc là chọn các nguyên liệu này cho món ăn mới hay không?") == JOptionPane.YES_OPTION) {
					boolean is_nguyenLieu_valid = true;
					for(int i = 0; i < nguyenLieuChoices.length; i++) {
						if(nguyenLieuChoices[i].isSelected()) {
							if(nguyenLieuChoicesQuantity[i].getText().equals("")) {
								JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng cho nguyên liệu mà bạn đã chọn");
								is_nguyenLieu_valid = false;
								break;
							}else {
								if(isParseStringToDouble(nguyenLieuChoicesQuantity[i].getText())) {
									userNguyenLieuChoose.add(nguyenLieuList.get(i));
									userNguyenLieuChosenQuantity.add(Double.parseDouble(nguyenLieuChoicesQuantity[i].getText()));
								}else {
									JOptionPane.showMessageDialog(null, "Vui lòng chỉ nhập vào số ở phần số lượng nguyên liệu");
									is_nguyenLieu_valid = false;
									break;
								}
							}
						}
					}
					
					if(is_nguyenLieu_valid) {
						dialog.dispose();
					}else {
						userNguyenLieuChoose.clear();
						userNguyenLieuChosenQuantity.clear();
					}
				}
				
			}
			
		});
	}
	
	public void nguyenLieuFormManagementInit(NguyenLieuDTO nguyenLieu) {
		nguyenLieuFormManagement = new JPanel();
		nguyenLieuFormManagement.setLayout(null);
		nguyenLieuFormManagement.setBounds(new Rectangle(0,0,400,650));
		nguyenLieuFormManagement.setBackground(Color.decode("#ffffff"));
		
		nguyenLieuFormManagementFieldInit(nguyenLieu);
		formManagementHandleButtonInit(nguyenLieuFormManagement);
		
		rightSide.add(nguyenLieuFormManagement);
	}
	
	public void nguyenLieuFormManagementFieldInit(NguyenLieuDTO nguyenLieu) {
		JLabel nguyenLieuFormTitle = new JLabel("NGUYÊN LIỆU");
		ComponentFormat.formatLabel(nguyenLieuFormTitle, "#5ac6f4", new Rectangle(100,30,340,50), new Font("Tahoma",Font.BOLD,30));
		
		JLabel maNguyenLieuLb = new JLabel("Mã");
		ComponentFormat.formatLabel(maNguyenLieuLb, "#000000", new Rectangle(30,160,100,30), new Font("Tahoma",Font.PLAIN,16));
		maNguyenLieuField = new JTextField(nguyenLieu.getMaNguyenLieu());
		ComponentFormat.formatTextField(maNguyenLieuField, new Rectangle(140,160,230,30), "#5ac6f4", "#ffffff", new Font("Tahoma",Font.BOLD, 16));
		
		JLabel tenNguyenLieuLb = new JLabel("Tên");
		ComponentFormat.formatLabel(tenNguyenLieuLb, "#000000", new Rectangle(30,220,100,30), new Font("Tahoma",Font.PLAIN,16));
		tenNguyenLieuField = new JTextField(nguyenLieu.getTen());
		ComponentFormat.formatTextField(tenNguyenLieuField, new Rectangle(140,220,230,30), "#5ac6f4", "#ffffff", new Font("Tahoma",Font.BOLD, 16));
		
		JLabel soLuongLb = new JLabel("Số lượng");
		ComponentFormat.formatLabel(soLuongLb, "#000000", new Rectangle(30,280,100,30), new Font("Tahoma",Font.PLAIN,16));
		nguyenLieuSoLuongField = new JTextField(Double.toString(nguyenLieu.getSoLuong()));
		nguyenLieuSoLuongField.setEditable(false);
		ComponentFormat.formatTextField(nguyenLieuSoLuongField, new Rectangle(140,280,230,30), "#5ac6f4", "#ffffff", new Font("Tahoma",Font.BOLD, 16));
		
		JLabel donViTinhLb = new JLabel("Đvi tính");
		ComponentFormat.formatLabel(donViTinhLb, "#000000", new Rectangle(30,340,100,30), new Font("Tahoma",Font.PLAIN,16));
		nguyenLieuDonViTinhField = new JTextField(nguyenLieu.getDonViTinh());
		ComponentFormat.formatTextField(nguyenLieuDonViTinhField, new Rectangle(140,340,230,30), "#5ac6f4", "#ffffff", new Font("Tahoma",Font.BOLD, 16));
		
		JLabel giaLb = new JLabel("Giá tiền");
		ComponentFormat.formatLabel(giaLb, "#000000", new Rectangle(30,400,100,30), new Font("Tahoma",Font.PLAIN,16));
		nguyenLieuGiaField = new JTextField(Integer.toString(nguyenLieu.getGia()));
		ComponentFormat.formatTextField(nguyenLieuGiaField, new Rectangle(140,400,230,30), "#5ac6f4", "#ffffff", new Font("Tahoma",Font.BOLD, 16));
		
		JLabel maNhaCungCapLb = new JLabel("Mã NCC");
		ComponentFormat.formatLabel(maNhaCungCapLb, "#000000", new Rectangle(30,460,100,30), new Font("Tahoma",Font.PLAIN,16));
		maNhaCungCapField = new JTextField(nguyenLieu.getMaNhaCungCap());
		ComponentFormat.formatTextField(maNhaCungCapField, new Rectangle(140,460,230,30), "#5ac6f4", "#ffffff", new Font("Tahoma",Font.BOLD, 16));
		
		nguyenLieuFormManagement.add(nguyenLieuFormTitle);		
		nguyenLieuFormManagement.add(maNguyenLieuLb);
		nguyenLieuFormManagement.add(maNguyenLieuField);
		nguyenLieuFormManagement.add(tenNguyenLieuLb);
		nguyenLieuFormManagement.add(tenNguyenLieuField);
		nguyenLieuFormManagement.add(soLuongLb);
		nguyenLieuFormManagement.add(nguyenLieuSoLuongField);
		nguyenLieuFormManagement.add(giaLb);
		nguyenLieuFormManagement.add(nguyenLieuGiaField);
		nguyenLieuFormManagement.add(donViTinhLb);
		nguyenLieuFormManagement.add(nguyenLieuDonViTinhField);
		nguyenLieuFormManagement.add(maNhaCungCapLb);
		nguyenLieuFormManagement.add(maNhaCungCapField);
	}
	
	public void nhaCungCapFormManagementInit(NhaCungCapDTO nhaCungCap) {
		nhaCungCapFormManagement = new JPanel();
		nhaCungCapFormManagement.setLayout(null);
		nhaCungCapFormManagement.setBounds(new Rectangle(0,0,400,650));
		nhaCungCapFormManagement.setBackground(Color.decode("#ffffff"));
		
		nhaCungCapFormManagementFieldInit(nhaCungCap);
		formManagementHandleButtonInit(nhaCungCapFormManagement);
		
		rightSide.add(nhaCungCapFormManagement);
		
	}
	
	public void nhaCungCapFormManagementFieldInit(NhaCungCapDTO nhaCungCap) {
		JLabel nhaCungCapFormTitle = new JLabel("NHÀ CUNG CẤP");
		ComponentFormat.formatLabel(nhaCungCapFormTitle, "#47e084", new Rectangle(100,30,340,50), new Font("Tahoma",Font.BOLD,30));
		
		JLabel maNhaCungCapLb = new JLabel("Mã");
		ComponentFormat.formatLabel(maNhaCungCapLb, "#000000", new Rectangle(30,260,100,30), new Font("Tahoma",Font.PLAIN,16));
		maNccField = new JTextField(nhaCungCap.getMaNhaCungCap());
		ComponentFormat.formatTextField(maNccField, new Rectangle(140,260,230,30), "#47e084", "#ffffff", new Font("Tahoma",Font.BOLD, 16));
		
		JLabel tenNhaCungCapLb = new JLabel("Tên");
		ComponentFormat.formatLabel(tenNhaCungCapLb, "#000000", new Rectangle(30,320,100,30), new Font("Tahoma",Font.PLAIN,16));
		tenNhaCungCapField = new JTextField(nhaCungCap.getTenNhaCungCap());
		ComponentFormat.formatTextField(tenNhaCungCapField, new Rectangle(140,320,230,30), "#47e084", "#ffffff", new Font("Tahoma",Font.BOLD, 16));
		
		nhaCungCapFormManagement.add(nhaCungCapFormTitle);
		nhaCungCapFormManagement.add(maNhaCungCapLb);
		nhaCungCapFormManagement.add(maNccField);
		nhaCungCapFormManagement.add(tenNhaCungCapLb);
		nhaCungCapFormManagement.add(tenNhaCungCapField);
	}
	
	public void formManagementHandleButtonInit(JPanel parentPanel) {
		addButton = new JPanel();
		deleteButton = new JPanel();
		editButton = new JPanel();
		resetButton = new JPanel();
		JButton chooseFileButton = new JButton("Chọn ảnh cho món ăn");
		
		createHandleButton(addButton,"Thêm",new Rectangle(30,550,74,30),"#43e864","src/images/application_icon/add-icon-x16.png");
		createHandleButton(deleteButton,"Xóa",new Rectangle(120,550,74,30),"#f24f65","src/images/application_icon/trash-icon-x16.png");
		createHandleButton(editButton,"Sửa",new Rectangle(204,550,74,30),"#5bb6ef","src/images/application_icon/edit-icon-x16.png");
		createHandleButton(resetButton,"Reset",new Rectangle(289,550,74,30),"#ffffff","src/images/application_icon/reset-icon-x16.png");
		
		ComponentFormat.formatButton(chooseFileButton, "#ffffff", "#000000", new Rectangle(30,600,340,30));
		addChooseImageAction(chooseFileButton);
		
		addMouseListenerForAddButton(addButton);
		addMouseListenerForEditButton(editButton);
		addMouseListenerForDeleteButton(deleteButton);
		addMouseListenerForResetButton(resetButton);
		
		parentPanel.add(addButton);
		parentPanel.add(deleteButton);
		parentPanel.add(editButton);
		parentPanel.add(resetButton);
		parentPanel.add(chooseFileButton);
	}
	
	public void addMouseListenerForAddButton(JPanel panel) {
		panel.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn thêm hay không?") == JOptionPane.YES_OPTION) {
					if(currentTable.equals("monTable")) {
						addNewMon();
						monTableRepaint();
					}else if(currentTable.equals("nguyenLieuTable")){
						addNewNguyenLieu();
						nguyenLieuTableRepaint();
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
	
	public MonDTO getMonFromField() {
		MonDTO mon = new MonDTO();
		mon.setMaMon(maMonField.getText());
		mon.setTenMon(tenMonField.getText());
		mon.setMaLoaiMon(loaiMonField.getText());
		mon.setGia(Integer.parseInt(monGiaField.getText())); 
		mon.setDonViTinh(monDonViTinhField.getText());
		mon.setTrangThai(true);
		
		return mon;
	}
	
	public boolean checkMonField() {
		MonDTO mon = getMonFromField();
		
		if(mon.getMaMon().equals("") || mon.getTenMon().equals("") || mon.getMaLoaiMon().equals("") || mon.getGia() == 0 || mon.getDonViTinh().equals("")) {
			JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin để thêm sản phẩm mới");
			return false;
		}else {
			if( userNguyenLieuChoose.size() == 0) {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn các nguyên liệu của món ăn");
				return false;
			}
		}
		return true;	
	}
	
	public void getNewMonImageFile() {
		if(userSelectedFile == null) {
			try {
				userImageChoose = ImageIO.read(new File("src/images/products/default-product-image.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			try {
				userImageChoose = ImageIO.read(new File(userSelectedFile.getAbsolutePath()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void addNewMon() {
		MonDTO mon = getMonFromField();
		
		if(checkMonField()) {
			
			getNewMonImageFile();
			
			String imgLink = "images/products/" + mon.getMaMon() + "-img-x150.png";
			mon.setImgLink(imgLink);
			if(monBus.checkAvailableMon(mon.getMaMon())) {
				JOptionPane.showMessageDialog(null, "Xin mời đổi lại mã món vì mã đã tồn tại");
			}else {
				
				ArrayList<ChiTietMonDTO> chiTietMonList = new ArrayList<ChiTietMonDTO>();
				
				for(int i = 0; i < userNguyenLieuChoose.size(); i++) {
					ChiTietMonDTO chiTietMon = new ChiTietMonDTO(mon.getMaMon(),userNguyenLieuChoose.get(i).getMaNguyenLieu(),userNguyenLieuChosenQuantity.get(i),true);
					chiTietMonList.add(chiTietMon);
				}
				
				chiTietMonBus.addChiTietMonOfMon(chiTietMonList);
				
				monBus.addNewMon(mon);
				try {
					ImageIO.write(userImageChoose, "PNG", new File("src/" + imgLink));
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				userChosenReset();
				JOptionPane.showMessageDialog(null, "Thêm món mới thành công");
			}
		}
	}
	
	
	public NguyenLieuDTO getNguyenLieuFromField() {
		NguyenLieuDTO nguyenLieu = new NguyenLieuDTO();
		nguyenLieu.setMaNguyenLieu(maNguyenLieuField.getText());
		nguyenLieu.setTen(tenNguyenLieuField.getText());
		nguyenLieu.setLoai(false);
		nguyenLieu.setMaNhaCungCap(maNhaCungCapField.getText());
		
		if(isParseStringToInteger(nguyenLieuGiaField.getText()) == true) {
			nguyenLieu.setGia(Integer.parseInt(nguyenLieuGiaField.getText()));
		}else {
			nguyenLieu.setGia(0);
		}
		
		
		if(isParseStringToDouble(nguyenLieuSoLuongField.getText()) == true) {
			nguyenLieu.setSoLuong(Double.parseDouble(nguyenLieuSoLuongField.getText()));
		}else {
			nguyenLieu.setSoLuong(0);
		}
		nguyenLieu.setTrangThai(true);
		nguyenLieu.setDonViTinh(nguyenLieuDonViTinhField.getText());
		
		return nguyenLieu;
	}
	
	public boolean checkNguyenLieuField() {
		NguyenLieuDTO nguyenLieu = getNguyenLieuFromField();
		
		if(nguyenLieu.getMaNguyenLieu().equals("") || nguyenLieu.getTen().equals("") || nguyenLieu.getDonViTinh().equals("") || nguyenLieu.getMaNhaCungCap().equals("")) {
			JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin để thêm nguyên liệu mới");
			return false;
		}else {
			if(isParseStringToDouble(nguyenLieuSoLuongField.getText()) == false) {
				JOptionPane.showMessageDialog(null, "Vui lòng chỉ điền số vào ô số lượng");
				return false;
			}
			if(isParseStringToInteger(nguyenLieuGiaField.getText()) == false) {
				JOptionPane.showMessageDialog(null, "Vui lòng chỉ điền số vào ô giá tiền");
				return false;
			}else {
				if(nguyenLieu.getGia() == 0) {
					JOptionPane.showMessageDialog(null, "Vui lòng thêm giá tiền cho nguyên liệu");
					return false;
				}
			}
			
		}
		return true;	
	}
	
	public void addNewNguyenLieu() {
		
		if(checkNguyenLieuField()) {
			NguyenLieuDTO nguyenLieu = getNguyenLieuFromField();
	
			if(nguyenLieuBus.isNguyenLieuDuplicated(nguyenLieu.getMaNguyenLieu())) {
				JOptionPane.showMessageDialog(null, "Xin mời đổi lại mã nguyên liệu vì mã đã tồn tại");
			}else {
				nguyenLieuBus.addNewNguyenLieu(nguyenLieu);
				JOptionPane.showMessageDialog(null, "Thêm món mới thành công");
			}
		}
	}
	
	public void addMouseListenerForEditButton(JPanel panel) {
		panel.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn chỉnh sửa hay không?") == JOptionPane.YES_OPTION) {
					if(currentTable.equals("monTable")) {
						editMon();
						monTableRepaint();
					}else if(currentTable.equals("nguyenLieuTable")) {
						editNguyenLieu();
						nguyenLieuTableRepaint();
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
	
	public void editMon() {
		MonDTO mon = getMonFromField();
		
		if(checkMonField()) {
			getNewMonImageFile();
			String imgLink = "images/products/" + mon.getMaMon() + "-img-x150.png";
			mon.setImgLink(imgLink);
			
			ArrayList<ChiTietMonDTO> chiTietMonList = new ArrayList<ChiTietMonDTO>();
			
			for(int i = 0; i < userNguyenLieuChoose.size(); i++) {
				ChiTietMonDTO chiTietMon = new ChiTietMonDTO(mon.getMaMon(),userNguyenLieuChoose.get(i).getMaNguyenLieu(),userNguyenLieuChosenQuantity.get(i),true);
				chiTietMonList.add(chiTietMon);
			}
			
			chiTietMonBus.deleteParamentlyChiTietMonByMaMon(mon.getMaMon());
			chiTietMonBus.addChiTietMonOfMon(chiTietMonList);
			
			monBus.updateMon(mon);
			try {
				ImageIO.write(userImageChoose, "PNG", new File("src/" + imgLink));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			userChosenReset();
			JOptionPane.showMessageDialog(null, "Chỉnh sửa thành công");
		}
	}
	
	public void editNguyenLieu() {
		if(checkNguyenLieuField()) {
			NguyenLieuDTO nguyenLieu = getNguyenLieuFromField();
			
			nguyenLieuBus.updateNguyenLieu(nguyenLieu);
		}
	}
	
	public void addChooseImageAction(JButton button) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fileChooser.setDialogTitle("Chọn hình ảnh cho món ăn");
				int returnVal = fileChooser.showOpenDialog(null);
				
				 if (returnVal == JFileChooser.APPROVE_OPTION) {
		            userSelectedFile = fileChooser.getSelectedFile();
				 }
			}
			
		});
	}
	
	public void addMouseListenerForDeleteButton(JPanel panel) {
		panel.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa hay không?") == JOptionPane.YES_OPTION) {
					if(currentTable.equals("monTable")) {
						deleteMon();
						monTableRepaint();
					}else if(currentTable.equals("nguyenLieuTable")) {
						deleteNguyenLieu();
						nguyenLieuTableRepaint();
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
	
	public void deleteMon() {
		if(maMonField.isEditable() == false) {
			String maMon = maMonField.getText();
			MonDTO mon = monBus.getMon(maMon);
			
			//delete mon
			monBus.deleteMon(maMon);
			
			//delete chiTietMon
			chiTietMonBus.deleteChiTietMonByMaMon(maMon);
			
			//delete image
			File file = new File("src/" + mon.getImgLink());
			file.delete();
			
			JOptionPane.showMessageDialog(null, "Xóa thành công");
			userChosenReset();

			
		}
	}
	
	public void deleteNguyenLieu() {
		if(maNguyenLieuField.isEditable() == false) {
			String maNguyenLieu = maNguyenLieuField.getText();
			
			//if nguyen lieu was used by another mon
			if(nguyenLieuBus.isNguyenLieuUsed(maNguyenLieu)) {
				JOptionPane.showMessageDialog(null,"Không thể xóa nguyên liệu này vì hiện tại đã có món đang sử dụng nguyên liệu này");
			}else {
				nguyenLieuBus.deleteNguyenLieu(maNguyenLieu);
				
				JOptionPane.showMessageDialog(null, "Xóa thành công");
				
			}
		}
	}
	
	public void addMouseListenerForResetButton(JPanel panel) {
		panel.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn reset lại các field hay không?") == JOptionPane.YES_OPTION) {
					if(currentTable.equals("monTable")) {
						monFormManagementReset();
					}else if(currentTable.equals("nguyenLieuTable")) {
						nguyenLieuFormManagementReset();
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
	
	public void monFormManagementReset() {
		MonDTO mon = new MonDTO();
		mon.setImgLink("images/products/default-product-image.png");
		
		monFormManagementInit(mon);
		rightSide.removeAll();
		rightSide.add(monFormManagement);
		maMonField.setEditable(true);
		rightSide.repaint();
		rightSide.revalidate();
	}
	
	public void nguyenLieuFormManagementReset() {
		NguyenLieuDTO nguyenLieu = new NguyenLieuDTO();
		nguyenLieuFormManagementInit(nguyenLieu);
		rightSide.removeAll();
		rightSide.add(nguyenLieuFormManagement);
		maNguyenLieuField.setEditable(true);
		rightSide.repaint();
		rightSide.revalidate();
	}
	
	public void userChosenReset() {
		userNguyenLieuChoose.clear();
		userNguyenLieuChosenQuantity.clear();
		userImageChoose = null;
	}
	
	public void monTableRepaint() {
		monList = monBus.getMonListFromDAO();
		monTableInit(monList);
		switchPanel(leftSideTableZone,monTablePanel);
	}
	
	public void monFormManagementRepaint(MonDTO mon) {
		rightSide.removeAll();
		monFormManagementInit(mon);
		maMonField.setEditable(false);
		rightSide.repaint();
		rightSide.revalidate();
	}
	
	public void nguyenLieuTableRepaint() {
		nguyenLieuList = nguyenLieuBus.getAllNguyenLieu();
		nguyenLieuTableInit(nguyenLieuList);
		switchPanel(leftSideTableZone,nguyenLieuTablePanel);
	}
	
	public void createHandleButton(JPanel button, String buttonName, Rectangle rec, String backgroundColor, String iconLink) {
		formatPanelToButton(button,backgroundColor, rec);
		JLabel imgLb;
		try {
			userImageChoose = ImageIO.read(new File(iconLink));
			imgLb = new JLabel(new ImageIcon(userImageChoose));
			imgLb.setBounds(new Rectangle(5,7,16,16));
			button.add(imgLb);
		}catch(IOException e) {
			System.out.println("Read file error: ");
			e.printStackTrace();
		}
		
		JLabel buttonNameLb = new JLabel(buttonName);
		buttonNameLb.setBounds(new Rectangle(25,0,40,30));
		buttonNameLb.setFont(new Font("Tahoma", Font.PLAIN, 13));
		button.add(buttonNameLb);
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
	
	public void formatPanelToButton(JPanel panel, String backgroundColor, Rectangle rec) {
		panel.setBackground(Color.decode(backgroundColor));
		panel.setBounds(rec);
		panel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode("#000000")));
		panel.setLayout(null);
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
	
	public boolean isParseStringToInteger(String intString) {
		int num;
		try {
			num = Integer.parseInt(intString);
			return true;
		}catch(NumberFormatException nfe) {
			return false;
		}
	}
}
