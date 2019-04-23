package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

import dto.NhaCungCapDTO;
import util.ComponentFormat;
import dto.HoaDonNhapDTO;
import dto.ChiTietHoaDonXuatDTO;


public class ThongKeGUI extends JPanel{
	private JPanel optionZone;
	private JPanel statisticDisplay;
	
	private JPanel mainOption;
	private JPanel dynamicOption;
	
	private JRadioButton monRadio;
	private JRadioButton nguyenLieuRadio;
	private JRadioButton nhanVienRadio;
	private JRadioButton hoaDonNhapRadio;
	private JRadioButton hoaDonXuatRadio;
	private ButtonGroup categoryGroup;
	
	private JRadioButton dateRadio;
	private JRadioButton threeMonthRadio;
	private ButtonGroup timeGroup;
	
	private JPanel categoryIdPanel;
	private JTextField categoryField;
	
	private JPanel filterPanel;
	
	private JPanel filterByDatePanel;
	private JComboBox fromDayBox;
	private JComboBox fromMonthBox;
	private JComboBox fromYearBox;
	private JComboBox toDayBox;
	private JComboBox toMonthBox;
	private JComboBox toYearBox;
	
	private JPanel filterByThreeMonthPanel;
	private JComboBox threeMonthBox;
	private JComboBox yearOfThreeMonth;
	
	private JPanel categoryStatisticChoice;
	private JPanel monStatisticChoice;
	private JPanel nguyenLieuStatisticChoice;
	private JPanel nhanVienStatisticChoice;
	private JPanel hdnStatisticChoice;
	private JPanel hdxStatisticChoice;
	
	public ThongKeGUI() {
		init();
	}
	
	public void init() {
		setLayout(null);
		setBounds(new Rectangle(0,0,1300,650));
		setBackground(Color.decode("#ffffff"));
		
		optionZoneInit();
	}
	
	public void optionZoneInit() {
		optionZone = new JPanel();
		optionZone.setLayout(null);
		optionZone.setBounds(new Rectangle(0,0,600,650));
		optionZone.setBackground(Color.decode("#ffffff"));
		optionZone.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.decode("#f0f0f0")));
		
		mainOptionInit();
		categoryPanelInit();
		filterPanelInit();
		
		add(optionZone);
	}
	
	public void mainOptionInit() {
		mainOption = new JPanel();
		mainOption.setLayout(null);
		mainOption.setBounds(new Rectangle(0,0,600,180));
		mainOption.setBackground(Color.decode("#f0f0f0"));
		
		JLabel idChoiceLb = new JLabel("Chọn mã: ");
		ComponentFormat.formatLabel(idChoiceLb,"#000000",new Rectangle(10,10,150,30), new Font("Tahoma", Font.BOLD,14));
		
		JLabel timeChoiceLb = new JLabel("Chọn thời gian: ");
		ComponentFormat.formatLabel(timeChoiceLb,"#000000",new Rectangle(10,110,150,30), new Font("Tahoma", Font.BOLD,14));
		
		 categoryOptionRadioButtonInit();
		 timeOptionInit();
		 dynamicOptionInit();
		 
		 mainOption.add(idChoiceLb);
		 mainOption.add(timeChoiceLb);
		
		optionZone.add(mainOption);	
	}
	
	public void categoryOptionRadioButtonInit() {
		monRadio = new JRadioButton("monRadio",true);
		nguyenLieuRadio = new JRadioButton("nguyenLieuRadio",false);
		nhanVienRadio = new JRadioButton("nhanVienRadio",false);
		hoaDonNhapRadio = new JRadioButton("hoaDonNhapRadio",false);
		hoaDonXuatRadio = new JRadioButton("hoaDonXuatRadio",false);
		
		monRadio.setBounds(new Rectangle(160,10,20,30));
		nguyenLieuRadio.setBounds(new Rectangle(300,10,20,30));
		nhanVienRadio.setBounds(new Rectangle(440,10,20,30));
		hoaDonNhapRadio.setBounds(new Rectangle(160,50,20,30));
		hoaDonXuatRadio.setBounds(new Rectangle(300,50,20,30));
		
		categoryGroup = new ButtonGroup();
		categoryGroup.add(monRadio);
		categoryGroup.add(nguyenLieuRadio);
		categoryGroup.add(nhanVienRadio);
		categoryGroup.add(hoaDonNhapRadio);
		categoryGroup.add(hoaDonXuatRadio);
		
		JLabel monLb = new JLabel("Món ăn");
		JLabel nguyenLieuLb = new JLabel("Nguyên Liệu");
		JLabel nhanVienLb = new JLabel("Nhân Viên");
		JLabel hoaDonNhapLb = new JLabel("Hóa đơn nhập");
		JLabel hoaDonXuatLb = new JLabel("Hóa đơn Xuất");
		
		ComponentFormat.formatLabel(monLb, "#000000", new Rectangle(190,10,100,30), new Font("Tahoma",Font.PLAIN,14));
		ComponentFormat.formatLabel(nguyenLieuLb, "#000000", new Rectangle(330,10,100,30), new Font("Tahoma",Font.PLAIN,14));
		ComponentFormat.formatLabel(nhanVienLb, "#000000", new Rectangle(470,10,100,30), new Font("Tahoma",Font.PLAIN,14));
		ComponentFormat.formatLabel(hoaDonNhapLb, "#000000", new Rectangle(190,50,100,30), new Font("Tahoma",Font.PLAIN,14));
		ComponentFormat.formatLabel(hoaDonXuatLb, "#000000", new Rectangle(330,50,100,30), new Font("Tahoma",Font.PLAIN,14));
		
		mainOption.add(monRadio);
		mainOption.add(nguyenLieuRadio);
		mainOption.add(nhanVienRadio);
		mainOption.add(hoaDonNhapRadio);
		mainOption.add(hoaDonXuatRadio);
		
		mainOption.add(monLb);
		mainOption.add(nguyenLieuLb);
		mainOption.add(nhanVienLb);
		mainOption.add(hoaDonNhapLb);
		mainOption.add(hoaDonXuatLb);
		
		
	}
	
	public void timeOptionInit() {
		dateRadio = new JRadioButton("dateRadio",true);
		threeMonthRadio = new JRadioButton("threeMonthRadio",false);
		
		dateRadio.setBounds(new Rectangle(160,110,20,30));
		dateRadio.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				filterByDatePanelInit();
				switchPanel(filterPanel, filterByDatePanel);
			}
			
		});
		
		threeMonthRadio.setBounds(new Rectangle(390,110,20,30));
		threeMonthRadio.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				filterByThreeMonthPanelInit();
				switchPanel(filterPanel, filterByThreeMonthPanel);
			}
			
		});
		
		timeGroup = new ButtonGroup();
		timeGroup.add(dateRadio);
		timeGroup.add(threeMonthRadio);
		
		JLabel dateLb = new JLabel("Khoảng thời gian");
		JLabel threeMonthLb = new JLabel("Quý");
		
		ComponentFormat.formatLabel(dateLb, "#000000", new Rectangle(190,110,200,30), new Font("Tahoma",Font.PLAIN,14));
		ComponentFormat.formatLabel(threeMonthLb, "#000000", new Rectangle(430,110,100,30), new Font("Tahoma",Font.PLAIN,14));
		
		mainOption.add(dateRadio);
		mainOption.add(threeMonthRadio);
		mainOption.add(dateLb);
		mainOption.add(threeMonthLb);
	}
	
	public void categoryPanelInit() {
		categoryIdPanel =  new JPanel();
		categoryIdPanel.setLayout(null);
		categoryIdPanel.setBackground(Color.decode("#ffffff"));
		categoryIdPanel.setBounds(new Rectangle(0,180,600,50));
		
		
		JLabel categoryLb = new JLabel("Mã món ăn");
		ComponentFormat.formatLabel(categoryLb, "#000000", new Rectangle(10,10,150,30), new Font("Tahoma",Font.PLAIN,14));
		
		categoryField = new JTextField();
		categoryField.setBounds(new Rectangle(160,10,280,30));
		
		categoryIdPanel.add(categoryLb);
		categoryIdPanel.add(categoryField);
		
		optionZone.add(categoryIdPanel);
	}
	
	public void dynamicOptionInit() {
		dynamicOption = new JPanel();
		dynamicOption.setLayout(null);
		dynamicOption.setBounds(new Rectangle(0,220,600,430));
		dynamicOption.setBackground(Color.decode("#ffffff"));
		
		filterPanelInit();
		
		optionZone.add(dynamicOption);
	}
	
	public void filterPanelInit() {
		filterPanel = new JPanel();
		filterPanel.setLayout(null);
		filterPanel.setBounds(new Rectangle(0,0,600,100));
		
		filterByDatePanelInit();
		
		dynamicOption.add(filterPanel);
	}
	
	public void filterByDatePanelInit() {
		filterByDatePanel = new JPanel();
		filterByDatePanel.setLayout(null);
		filterByDatePanel.setBounds(new Rectangle(0,0,600,100));
		filterByDatePanel.setBackground(Color.decode("#fffffff"));
		
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
		
		fromDayBox = new JComboBox(dayList.toArray());
		toDayBox = new JComboBox(dayList.toArray());
		fromMonthBox = new JComboBox(monthList.toArray());
		toMonthBox = new JComboBox(monthList.toArray());
		fromYearBox = new JComboBox(yearList.toArray());
		toYearBox = new JComboBox(yearList.toArray());
		
		JLabel fromDateLb = new JLabel("Từ ngày");
		ComponentFormat.formatLabel(fromDateLb, "#000000", new Rectangle(10,10,150,30), new Font("Tahoma",Font.PLAIN,14));
		
		ComponentFormat.formatComboBox(fromDayBox, new Rectangle(160,10,80,30) , "#ffffff");
		ComponentFormat.formatComboBox(fromMonthBox, new Rectangle(260,10,80,30) , "#ffffff");
		ComponentFormat.formatComboBox(fromYearBox, new Rectangle(360,10,80,30) , "#ffffff");
		
		JLabel toDateLb = new JLabel("Đến ngày");
		ComponentFormat.formatLabel(toDateLb, "#000000", new Rectangle(10,60,150,30), new Font("Tahoma",Font.PLAIN,14));
		
		ComponentFormat.formatComboBox(toDayBox, new Rectangle(160,60,80,30) , "#ffffff");
		ComponentFormat.formatComboBox(toMonthBox, new Rectangle(260,60,80,30) , "#ffffff");
		ComponentFormat.formatComboBox(toYearBox, new Rectangle(360,60,80,30) , "#ffffff");
		
		filterByDatePanel.add(fromDateLb);
		filterByDatePanel.add(fromDayBox);
		filterByDatePanel.add(fromMonthBox);
		filterByDatePanel.add(fromYearBox);
		
		filterByDatePanel.add(toDateLb);
		filterByDatePanel.add(toDayBox);
		filterByDatePanel.add(toMonthBox);
		filterByDatePanel.add(toYearBox);
		
		filterPanel.add(filterByDatePanel);
	}
	
	public void filterByThreeMonthPanelInit() {
		filterByThreeMonthPanel = new JPanel();
		filterByThreeMonthPanel.setBounds(new Rectangle(0,0,600,100));
		filterByThreeMonthPanel.setLayout(null);
		filterByThreeMonthPanel.setBackground(Color.decode("#ffffff"));
		
		ArrayList<String> threeMonthList = new ArrayList<String>();
		threeMonthList.add("Không");
		threeMonthList.add("Quý 1");
		threeMonthList.add("Quý 2");
		threeMonthList.add("Quý 3");
		threeMonthList.add("Quý 4");
		
		ArrayList<String> yearList = new ArrayList<String>();
		for(int i = Calendar.getInstance().get(Calendar.YEAR); i >= 1999; i--) {
			yearList.add(Integer.toString(i));
		}
		
		JLabel threeMonthLb = new JLabel("Theo quý");
		ComponentFormat.formatLabel(threeMonthLb, "#000000", new Rectangle(10,10,150,30), new Font("Tahoma", Font.PLAIN, 14));
		
		threeMonthBox = new JComboBox(threeMonthList.toArray());
		ComponentFormat.formatComboBox(threeMonthBox, new Rectangle(160,10,100,30), "#ffffff");
		
		
		JLabel yearLb = new JLabel("Chọn năm");
		ComponentFormat.formatLabel(yearLb, "#000000", new Rectangle(300,10,80,30), new Font("Tahoma", Font.PLAIN, 14));
		
		yearOfThreeMonth = new JComboBox(yearList.toArray());
		ComponentFormat.formatComboBox(yearOfThreeMonth, new Rectangle(400,10,100,30), "#ffffff");
		
		filterByThreeMonthPanel.add(threeMonthLb);
		filterByThreeMonthPanel.add(threeMonthBox);
		filterByThreeMonthPanel.add(yearLb);
		filterByThreeMonthPanel.add(yearOfThreeMonth);
		
		filterPanel.add(filterByThreeMonthPanel);

	}
	
	public void categoryStatisticChoicePanelInit() {
		categoryStatisticChoice.setBounds(new Rectangle(0,100,600,200));
		
	}
	
	public void switchPanel(JPanel parentPanel, JPanel childPanel) {
		parentPanel.removeAll();
		parentPanel.repaint();
		parentPanel.revalidate();
		
		parentPanel.add(childPanel);
		parentPanel.repaint();
		parentPanel.revalidate();
	}
}
