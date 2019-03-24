package GUI;

import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.*;
public class PanelLink extends JPanel{

	public PanelLink(String backgroundColor, Rectangle rec) {
		setLayout(null);
		setBackground(Color.decode(backgroundColor));
		setBounds(rec);
	}
}
