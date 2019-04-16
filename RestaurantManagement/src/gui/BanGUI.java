package gui;
import images.*;
import bus.BanBUS;
import dto.BanDTO;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
public class BanGUI extends JPanel{
	private Rectangle rec;
	private String backgroundColor;
	private String tableName;
	private int posInArrList;
	private String imgLink;
	private boolean status;
	
	public BanGUI() {
		
	}
	
	public BanGUI(Rectangle rec, String backgroundColor, String tableName, int posInArrList, String imgLink, boolean status){
		this.rec = rec;
		this.backgroundColor = backgroundColor;
		this.tableName = tableName;
		this.posInArrList = posInArrList;
		this.imgLink = imgLink;
		this.status = status;
	}
	
	public void init() {
		setLayout(null);
		setBounds(rec);
		setBackground(Color.decode(backgroundColor));
		
		//create name for table
		int tableWidth = (int)rec.getWidth();
		int tableHeight = (int)rec.getHeight();
	
		BufferedImage image;
		JLabel imgLb;
		try {
			image = ImageIO.read(new File(this.imgLink));
			imgLb = new JLabel(new ImageIcon(image));
			imgLb.setBounds(new Rectangle(0,0,100,100));
			add(imgLb);
		}catch(IOException e) {
			System.out.println("Read file error: ");
			e.printStackTrace();
		}
		
		JLabel tableNameLb = new JLabel(this.tableName);
		tableNameLb.setBounds(new Rectangle((tableWidth/2) - 10,85,tableWidth,20));
		tableNameLb.setForeground(Color.decode("#000000"));
		tableNameLb.setFont(new Font("arial",Font.PLAIN,14));
		
		add(tableNameLb);

	}

	public Rectangle getRec() {
		return rec;
	}

	public void setRec(Rectangle rec) {
		this.rec = rec;
	}

	public String getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public int getPosInArrList() {
		return posInArrList;
	}

	public void setPosInArrList(int posInArrList) {
		this.posInArrList = posInArrList;
	}

	
	
	public String getImgLink() {
		return imgLink;
	}

	public void setImgLink(String imgLink) {
		this.imgLink = imgLink;
	}

	public void printTableList() {
		ArrayList<BanDTO> arr = new ArrayList<BanDTO>();
		arr = BanBUS.tableAll();
		for(int i = 0; i < arr.size(); i++) {
			System.out.println(arr.get(i).getmaBan());
		}
	}
}
