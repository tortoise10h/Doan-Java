package gui;

import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.*;
public class SanPhamGUI extends JPanel{
	public SanPhamGUI() {
		init();
	}
	
	public void init() {
		setBounds(new Rectangle(0,0,1300,650));
		setBackground(Color.decode("#ffffff"));
		add(new JLabel("Product Management Panel"));
	}
}
