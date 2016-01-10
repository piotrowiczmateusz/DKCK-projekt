package dkck.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class RocketPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public RocketPanel() {
		super();
		setOpaque(true);
		setBackground(Color.white);
		setForeground(Color.black);
		setPreferredSize(new Dimension(70, 110));
		setBorder(BorderFactory.createLineBorder(Color.black));
		setBounds(new Rectangle(new Point(510, 10), getPreferredSize()));
		setFont(new Font("Arial", Font.PLAIN, 12));

	}

}
