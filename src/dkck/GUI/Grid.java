package dkck.GUI;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.synth.ColorType;

public class Grid extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int rows;

	private int columns;

	public List<List<CellPane>> cellPanes = new ArrayList<List<CellPane>>();

	/**
	 * @return the rows
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * @param rows
	 *            the rows to set
	 */
	public void setRows(int rows) {
		this.rows = rows;
	}

	/**
	 * @return the columns
	 */
	public int getColumns() {
		return columns;
	}

	/**
	 * @param columns
	 *            the columns to set
	 */
	public void setColumns(int columns) {
		this.columns = columns;
	}

	public Grid(int rows, int columns) {
		this.rows = rows - 1;
		this.columns = columns - 1;

		setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		for (int row = 0; row < rows; row++) {

			this.cellPanes.add(new ArrayList<CellPane>());

			for (int col = 0; col < columns; col++) {
				gbc.gridx = col;
				gbc.gridy = row;

				this.cellPanes.get(row).add(new CellPane());

				Border border = null;
				if (row < rows - 1) {
					if (col < columns - 1) {
						border = new MatteBorder(1, 1, 0, 0, Color.GRAY);
					} else {
						border = new MatteBorder(1, 1, 0, 1, Color.GRAY);
					}
				} else {
					if (col < columns - 1) {
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

	public void drawSquare(int prevX, int prevY, int x, int y, Color oolor) {
		if (prevX > this.getRows())
			prevX = this.getRows();
		if (x > this.getRows())
			x = this.getRows();
		if (prevY > this.getColumns())
			prevY = this.getColumns();
		if (y > this.getColumns())
			y = this.getColumns();

		this.cellPanes.get(prevX).get(prevY).setBackground(this.getBackground());
		this.cellPanes.get(x).get(y).setBackground(oolor);
	}


}
