package dkck.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;

public class LogPanel extends JTextArea {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LogPanel() {    
		super();	
		setBackground(Color.white);
		setPreferredSize(new Dimension(360, 250));
		setBorder(BorderFactory.createLineBorder(Color.black));
		setBounds(new Rectangle(new Point(180, 10), getPreferredSize()));	
		setEditable(false);
		setLineWrap(true);  
	}
}
