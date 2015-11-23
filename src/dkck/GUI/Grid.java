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
import dkck.Item;
import dkck.Point;
import dkck.Rocket;
import dkck.Sapper;

public class Grid extends JPanel {

	private static final long serialVersionUID = 1L;

	private int rows;

	private int columns;

	public List<List<CellPane>> cellPanes = new ArrayList<List<CellPane>>();

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

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

	Color chooseObjectColor(Item itemReference) {

		if (itemReference instanceof Point) {
			return null;
		} else if (itemReference instanceof Rocket) {
			return MainWindow.rocketColor;
		} else if (itemReference instanceof Bomb && !(itemReference instanceof Rocket)) {
			return MainWindow.bombColor;
		} else if (itemReference instanceof Sapper) {
			return MainWindow.sapperColor;
		}
		return MainWindow.cellColor;
	}

	public void drawSquare(int prevX, int prevY, int x, int y, Item itemReference) {
		if (prevX > this.getRows())
			prevX = this.getRows();
		if (x > this.getRows())
			x = this.getRows();
		if (prevY > this.getColumns())
			prevY = this.getColumns();
		if (y > this.getColumns())
			y = this.getColumns();

		Color tempColor = chooseObjectColor(itemReference);

		this.cellPanes.get(prevX).get(prevY).setBackground(MainWindow.cellColor);
		if (tempColor != null) {
			this.cellPanes.get(x).get(y).setBackground(tempColor);
		}
		MainWindow.grid.cellPanes.get(prevX).get(prevY).label.setText("");
		if (tempColor != null && itemReference != null) {
			MainWindow.grid.cellPanes.get(x).get(y).label.setText(Integer.toString(itemReference.getId()));
		}
	}

	/*
	 * public void repairSquare(int positionX, int positionY) { for (int i = 0;
	 * i < MainWindow.itemsCollection.getItemsArray().size(); i++) { Item
	 * tempItem = MainWindow.itemsCollection.getItemsArray().get(i);
	 * 
	 * if ((tempItem.getPositionX() == positionX) && (tempItem.getPositionY() ==
	 * positionY)) { if (tempItem instanceof Bomb && (tempItem instanceof Rocket
	 * == false)) { MainWindow.grid.drawSquare(tempItem.getPositionX(),
	 * tempItem.getPositionY(), tempItem.getPositionX(),
	 * tempItem.getPositionY(), MainWindow.bombColor);
	 * 
	 * } else if (tempItem instanceof Sapper) {
	 * MainWindow.grid.drawSquare(positionX, positionY, tempItem.getPositionX(),
	 * tempItem.getPositionY(), MainWindow.sapperColor);
	 * 
	 * } else if (tempItem instanceof Rocket) {
	 * MainWindow.grid.drawSquare(positionX, positionY, tempItem.getPositionX(),
	 * tempItem.getPositionY(), MainWindow.rocketColor); } } } }
	 */

	// funkcja rysowania zakresów obiektów

	public void drawCircle(int x0, int y0, int radius, Item itemReference) {
		Color tempColor = chooseObjectColor(itemReference);

		if (tempColor != null) {
			int x = radius;
			int y = 0;
			int decisionOver2 = 1 - x;

			while (y <= x) {
				if ((x + x0 >= 0) && (x + x0 <= this.getRows()) && (y + y0 >= 0) && (y + y0 <= this.getColumns())) {
					this.cellPanes.get(x + x0).get(y + y0).setBackground(tempColor);
				}
				if ((y + x0 >= 0) && (y + x0 <= this.getRows()) && (x + y0 >= 0) && (x + y0 <= this.getColumns())) {
					this.cellPanes.get(y + x0).get(x + y0).setBackground(tempColor);
				}
				if ((-x + x0 >= 0) && (-x + x0 <= this.getRows()) && (y + y0 >= 0) && (y + y0 <= this.getColumns())) {
					this.cellPanes.get(-x + x0).get(y + y0).setBackground(tempColor);
				}
				if ((-y + x0 >= 0) && (-y + x0 <= this.getRows()) && (x + y0 >= 0) && (x + y0 <= this.getColumns())) {
					this.cellPanes.get(-y + x0).get(x + y0).setBackground(tempColor);
				}
				if ((-x + x0 >= 0) && (-x + x0 <= this.getRows()) && (-y + y0 >= 0) && (-y + y0 <= this.getColumns())) {
					this.cellPanes.get(-x + x0).get(-y + y0).setBackground(tempColor);
				}
				if ((-y + x0 >= 0) && (-y + x0 <= this.getRows()) && (-x + y0 >= 0) && (-x + y0 <= this.getColumns())) {
					this.cellPanes.get(-y + x0).get(-x + y0).setBackground(tempColor);
				}
				if ((x + x0 >= 0) && (x + x0 <= this.getRows()) && (-y + y0 >= 0) && (-y + y0 <= this.getColumns())) {
					this.cellPanes.get(x + x0).get(-y + y0).setBackground(tempColor);
				}
				if ((y + x0 >= 0) && (y + x0 <= this.getRows()) && (-x + y0 >= 0) && (-x + y0 <= this.getColumns())) {
					this.cellPanes.get(y + x0).get(-x + y0).setBackground(tempColor);
				}
				y++;
				if (decisionOver2 <= 0) {
					decisionOver2 += 2 * y + 1;
				} else {
					x--;
					decisionOver2 += 2 * (y - x) + 1;
				}
			}
		}
	}

	/*
	 * public void repairCircle(int x0, int y0, int radius) { for (int i = 0; i
	 * < MainWindow.itemsCollection.getItemsArray().size(); i++) { Item tempItem
	 * = MainWindow.itemsCollection.getItemsArray().get(i);
	 * 
	 * if ((tempItem.getPositionX() == x0) && (tempItem.getPositionY() == y0) &&
	 * (tempItem.getRange() == radius)) { if (tempItem instanceof Bomb &&
	 * (tempItem instanceof Rocket == false)) {
	 * MainWindow.grid.drawCircle(tempItem.getPositionX(),
	 * tempItem.getPositionY(), tempItem.getRange(), MainWindow.bombColor);
	 * 
	 * } else if (tempItem instanceof Sapper) {
	 * MainWindow.grid.drawCircle(tempItem.getPositionX(),
	 * tempItem.getPositionY(), tempItem.getRange(), MainWindow.sapperColor); }
	 * else if (tempItem instanceof Rocket) {
	 * MainWindow.grid.drawCircle(tempItem.getPositionX(),
	 * tempItem.getPositionY(), tempItem.getRange(), MainWindow.rocketColor); }
	 * } } }
	 */

	public void repairCircles() {
		for (int i = 0; i < MainWindow.itemsCollection.getItemsArray().size(); i++) {
			Item tempItem = MainWindow.itemsCollection.getItemsArray().get(i);

			MainWindow.grid.drawCircle(tempItem.getPositionX(), tempItem.getPositionY(), tempItem.getRange(), tempItem);
		}
	}

	public void repairSquares() {
		for (int i = 0; i < MainWindow.itemsCollection.getItemsArray().size(); i++) {
			Item tempItem = MainWindow.itemsCollection.getItemsArray().get(i);

			MainWindow.grid.drawSquare(tempItem.getPositionX(), tempItem.getPositionY(), tempItem.getPositionX(),
					tempItem.getPositionY(), tempItem);
		}
	}

	public void drawSapper(int x, int y) {
		this.cellPanes.get(x).get(y).image.img = new ImageIcon("images/sapper.png").getImage();
	}

	public void drawDeadSapper(int x, int y) {
		this.cellPanes.get(x).get(y).image.img = new ImageIcon("images/dead.png").getImage();
	}

	public void drawBomb(int x, int y) {
		this.cellPanes.get(x).get(y).image.img = new ImageIcon("images/bomb.png").getImage();
	}

	public void drawExplodedBomb(int x, int y) {
		this.cellPanes.get(x).get(y).image.img = new ImageIcon("images/exploded.png").getImage();
	}

	public void drawDisarmedBomb(int x, int y) {
		this.cellPanes.get(x).get(y).image.img = new ImageIcon("images/disarmed.png").getImage();
	}

	public void drawRocket(int x, int y) {
		this.cellPanes.get(x).get(y).image.img = new ImageIcon("images/disarmed.png").getImage();
	}

}