package gui;

import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.*;
public class PanelLinkGUI extends JPanel{

	public PanelLinkGUI(String backgroundColor, Rectangle rec) {
		setLayout(null);
		setBackground(Color.decode(backgroundColor));
		setBounds(rec);
	}
}
