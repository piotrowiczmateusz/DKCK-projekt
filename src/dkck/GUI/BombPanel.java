package dkck.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class BombPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public BombPanel() {
		super();
		setOpaque(true);
		setBackground(Color.white);
		setForeground(Color.black);
		setPreferredSize(new Dimension(130, 110));
		setBorder(BorderFactory.createLineBorder(Color.black));
		setBounds(new Rectangle(new Point(510, 10), getPreferredSize()));
		setFont(new Font("Arial", Font.PLAIN, 12));
		
	}

}
