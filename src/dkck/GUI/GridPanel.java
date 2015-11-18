package dkck.GUI;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

public class GridPanel extends JPanel{

	private static final long serialVersionUID = 1L;

	public GridPanel() {
		super();
		setBackground(Color.WHITE);
		setBorder(new MatteBorder(0, 0, 0, 1, (Color) new Color(0, 0, 0)));
	}
}
