package GUI;

import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.*;
public class ProductManagementPanel extends JPanel{
	public ProductManagementPanel() {
		init();
	}
	
	public void init() {
		setBounds(new Rectangle(0,0,1020,700));
		setBackground(Color.decode("#ffffff"));
		add(new JLabel("Product Management Panel"));
	}
}
