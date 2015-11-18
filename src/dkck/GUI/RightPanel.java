package dkck.GUI;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;

public class RightPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public RightPanel() {
		super();
		setBackground(UIManager.getColor("Panel.background"));
		setPreferredSize(new Dimension(385, 170));
		setBorder(new MatteBorder(0, 1, 0, 0, (Color) new Color(0, 0, 0)));
	}
}
