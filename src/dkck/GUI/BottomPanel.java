package dkck.GUI;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

public class BottomPanel extends JPanel {
	public BottomPanel() {
		super();
		setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 0)));
		setPreferredSize(new Dimension(900, 42));
	}
}
