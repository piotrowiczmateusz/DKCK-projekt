package dkck.GUI;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;

public class GridPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3637758474364417635L;

	public GridPanel() {
		super();
		setBackground(UIManager.getColor("Panel.background"));
		setBorder(new MatteBorder(0, 0, 0, 1, (Color) new Color(0, 0, 0)));
	}
}
