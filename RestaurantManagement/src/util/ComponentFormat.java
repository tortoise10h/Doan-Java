package util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.*;

public class ComponentFormat {
	public static void formatButton(JButton button, String backgroundColor, String foreground, Rectangle rec) {
		button.setBackground(Color.decode(backgroundColor));
		button.setForeground(Color.decode(foreground));
		button.setBounds(rec);
		button.setFocusPainted(false);
	}
	
	public static void formatLabel(JLabel label, String foreground, Rectangle rec, Font font) {
		label.setForeground(Color.decode(foreground));
		label.setFont(font);
		label.setBounds(rec);
	}
	
	public static void formatComboBox(JComboBox comboBox, Rectangle rec, String backgroundColor) {
		comboBox.setBackground(Color.decode(backgroundColor));
		comboBox.setBounds(rec);
	}
}
