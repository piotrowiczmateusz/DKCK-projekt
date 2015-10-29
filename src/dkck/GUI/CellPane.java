package dkck.GUI;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class CellPane extends JPanel {
	public Color defaultBackground;
	
	public CellPane() {
		defaultBackground = getBackground();
		setBackground(defaultBackground);
	}
	
	@Override
	public Dimension getPreferredSize() {		
		return new Dimension(10, 10);
	}
}
