package dkck.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class CellPane extends JPanel {
	
	private static final long serialVersionUID = 1L;
	//public Color defaultBackground;
	public ImagePanel image = new ImagePanel(new ImageIcon("images/blank.png").getImage());

	public CellPane() {
		setLayout(new FlowLayout());
		((FlowLayout) getLayout()).setVgap(0);
		setBackground(Color.WHITE);
		//add(image);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(10, 10);
	}
}
