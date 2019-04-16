package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class MonGUI extends JPanel{
	private String tenMon;
	private int gia;
	private String imgLink;
	private int productPosInArr;
	
	public MonGUI() {
		
	}
	
	public MonGUI(String tenMon, int gia, String imgLink) {
		this.tenMon = tenMon;
		this.gia = gia;
		this.imgLink = imgLink;
	}

	public void init() {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(150,180));
		setBackground(Color.decode("#f2f2f2"));
		
		//display product icon
		BufferedImage image;
		JLabel imgLb;
		try {
			image = ImageIO.read(new File("src/" + this.imgLink));
			imgLb = new JLabel(new ImageIcon(image));
			imgLb.setPreferredSize(new Dimension(150,112));
//			imgLb.getInsets(new Insets(20,20,20,20));
			add(imgLb,BorderLayout.NORTH);
		}catch(IOException e) {
			System.out.println("Read file error: ");
			e.printStackTrace();
		}
		
		//display product name
		JLabel tenMonLb = new JLabel(this.tenMon,SwingConstants.CENTER);
		tenMonLb.setPreferredSize(new Dimension(150,30));
		add(tenMonLb,BorderLayout.CENTER);
		
		//display product price
		JLabel giaLb = new JLabel(Integer.toString(this.gia)+"đ",SwingConstants.CENTER);
		giaLb.setPreferredSize(new Dimension(150,30));
		add(giaLb,BorderLayout.SOUTH);
		
	}
	
	public String getTenMon() {
		return tenMon;
	}

	public void setTenMon(String tenMon) {
		this.tenMon = tenMon;
	}

	public double getGia() {
		return gia;
	}

	public void setGia(int gia) {
		this.gia = gia;
	}

	public String getImgLink() {
		return imgLink;
	}

	public void setImgLink(String imgLink) {
		this.imgLink = imgLink;
	}

	public int getProductPosInArr() {
		return productPosInArr;
	}

	public void setProductPosInArr(int productPosInArr) {
		this.productPosInArr = productPosInArr;
	}
	

	
}
