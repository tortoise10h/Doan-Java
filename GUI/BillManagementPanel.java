package GUI;

import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.*;
public class BillManagementPanel extends JPanel{
	public BillManagementPanel() {
		init();
	}
	
	public void init() {
		setBounds(new Rectangle(0,0,1020,700));
		setBackground(Color.decode("#ffffff"));
		add(new JLabel("Bill Management Panel"));
	}
}
