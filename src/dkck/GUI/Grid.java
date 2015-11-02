package dkck.GUI;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

public class Grid extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected int ROWS = 50;

	protected int COLUMNS = 50;

	public List<List<CellPane>> cellPanes = new ArrayList<List<CellPane>>();

	public Grid() {

		setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		for (int row = 0; row < ROWS; row++) {

			this.cellPanes.add(new ArrayList<CellPane>());

			for (int col = 0; col < COLUMNS; col++) {
				gbc.gridx = col;
				gbc.gridy = row;

				this.cellPanes.get(row).add(new CellPane());

				Border border = null;
				if (row < ROWS - 1) {
					if (col < COLUMNS - 1) {
						border = new MatteBorder(1, 1, 0, 0, Color.GRAY);
					} else {
						border = new MatteBorder(1, 1, 0, 1, Color.GRAY);
					}
				} else {
					if (col < COLUMNS - 1) {
						border = new MatteBorder(1, 1, 1, 0, Color.GRAY);
					} else {
						border = new MatteBorder(1, 1, 1, 1, Color.GRAY);
					}
				}
				this.cellPanes.get(row).get(col).setBorder(border);
				add(this.cellPanes.get(row).get(col), gbc);
			}
		}
	}

	public void drawSapper(int prevX, int prevY, int x, int y) {
		this.cellPanes.get(prevX).get(prevY).setBackground(this.getBackground());
		this.cellPanes.get(x).get(y).setBackground(Color.gray);
	}

	public void drawBomb(int x, int y) {
		this.cellPanes.get(x).get(y).setBackground(Color.black);
	}

}
