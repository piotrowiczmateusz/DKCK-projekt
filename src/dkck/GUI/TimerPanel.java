package dkck.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class TimerPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TimerPanel() {	    
		super();	    
		setOpaque(true);
		setBackground(Color.white);
		setPreferredSize(new Dimension(360, 160));
		setBorder(BorderFactory.createLineBorder(Color.black));
		setBounds(new Rectangle(new Point(510, 10), getPreferredSize()));
	}
}
