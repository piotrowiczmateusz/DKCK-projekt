package dkck.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class HPPanel extends JLabel {

	private static final long serialVersionUID = 1L;

	public HPPanel() {	    
		super();	    
		setOpaque(true);
		setBackground(Color.white);
		setForeground(Color.black);
		setPreferredSize(new Dimension(176, 25));
		setBorder(BorderFactory.createLineBorder(Color.black));
		setHorizontalAlignment(SwingConstants.CENTER);
		setBounds(new Rectangle(new Point(10, 10), getPreferredSize()));
		setFont(new Font("Arial", Font.PLAIN, 12));
	}
}
