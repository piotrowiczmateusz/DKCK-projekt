package dkck;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class CellPane extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
