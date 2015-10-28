import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class CellPane extends JPanel {
	private Color defaultBackground;
	
	public CellPane() {
		defaultBackground = getBackground();
		setBackground(defaultBackground);
	}
	
	@Override
	public Dimension getPreferredSize() {
		
		return new Dimension(15, 15);
	}
}
