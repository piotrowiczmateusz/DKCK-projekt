package dkck.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class CellPane extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public JLabel label = new JLabel();

	public CellPane() {
		setLayout(new FlowLayout());
		((FlowLayout) getLayout()).setVgap(0);
		setBackground(Color.WHITE);

		label.setFont(label.getFont().deriveFont(7.0f));
		add(label);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(10, 10);
	}
}
