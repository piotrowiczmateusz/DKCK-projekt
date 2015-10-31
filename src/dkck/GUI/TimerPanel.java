package dkck.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;

public class TimerPanel extends JTextArea {
	public TimerPanel() {	    
		super();	    
		setOpaque(true);
		setPreferredSize(new Dimension(360, 160));
		setBorder(BorderFactory.createLineBorder(Color.black));
		setBounds(new Rectangle(new Point(510, 10), getPreferredSize()));
		setEditable(false);
		setLineWrap(true);
	}
}
