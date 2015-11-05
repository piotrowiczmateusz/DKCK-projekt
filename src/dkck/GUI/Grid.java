package dkck.GUI;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

import dkck.Bomb;

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

	
	/*
	 * Drawing methods:
	 */
	
	public void drawSapper(int prevX, int prevY, int x, int y) {
		
		this.clearGrid();	
		
		if (prevX > this.getRows())
			prevX = this.getRows();
		if (x > this.getRows())
			x = this.getRows();
		if (prevY > this.getColumns())
			prevY = this.getColumns();
		if (y > this.getColumns())
			y = this.getColumns();

		this.cellPanes.get(prevX).get(prevY).setBackground(this.cellPanes.get(prevX).get(prevY).defaultBackground);
		
		this.cellPanes.get(x).get(y).image.img = new ImageIcon("images/sapper.png").getImage();
		this.cellPanes.get(prevX).get(prevY).image.img = new ImageIcon("images/blank.png").getImage();
		
		this.repaint();
	}
	
	public void drawDeadSapper(int x, int y) {
		this.cellPanes.get(x).get(y).image.img = new ImageIcon("images/dead.png").getImage();
	}

	public void drawBomb(int x, int y) {
		if (x > this.getRows())
			x = this.getRows();
		if (y > this.getColumns())
			y = this.getColumns();
		
		this.cellPanes.get(x).get(y).image.img = new ImageIcon("images/bomb.png").getImage();
		this.cellPanes.get(x).get(y).setBackground(new Color(255, 0, 0, 50));

	}
	
	public void drawExplodedBomb(int x, int y) {
		this.cellPanes.get(x).get(y).image.img = new ImageIcon("images/exploded.png").getImage();
	}
	
	public void drawDisarmedBomb(int x, int y) {
		this.cellPanes.get(x).get(y).image.img = new ImageIcon("images/disarmed.png").getImage();
	}
	
	public void drawRange() {
		
		for(int k = 0; k < MainWindow.itemsCollection.getItemsArray().size(); k++) {
			
			if(MainWindow.itemsCollection.getItemsArray().get(k) instanceof Bomb) {
				
				int x = MainWindow.itemsCollection.getItemsArray().get(k).getPositionX();
				int y = MainWindow.itemsCollection.getItemsArray().get(k).getPositionY();
				int range = MainWindow.itemsCollection.getItemsArray().get(k).getRange();
				
				if(range != 0) {
					for(int i = x-range; i < x+range+1; i++) {
						for(int j = y-range; j < y+range+1; j++) {
							if((i != 0) && (i != 25) && (j != 0) && (j != 25)) {
								this.cellPanes.get(i).get(j).setBackground(new Color(255, 0, 0, 55));
							}	
						}
					}
				}		
			}	
		}			
	}
	
	public void clearGrid() {
		for(int i = 0; i < this.getRows(); i++) {
			for(int j = 0; j < this.getColumns(); j++) {
				this.cellPanes.get(i).get(j).setBackground(this.cellPanes.get(i).get(j).defaultBackground);		
			}
		}
	}
}
