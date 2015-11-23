package dkck;

import dkck.GUI.MainWindow;

public class Sapper extends Item {

	/**
	 * ATTRIBUTES
	 */
	public static int id = 0;

	private int numberOfDisarmedBombs;

	private int healthPoints;

	private boolean SapperStatus;

	/**
	 * SETTERS AND GETTERS
	 */
	public int getNumberOfDisarmedBombs() {
		return numberOfDisarmedBombs;
	}

	public void setNumberOfDisarmedBombs(int numberOfDisarmedBombs) {
		this.numberOfDisarmedBombs = numberOfDisarmedBombs;
	}

	public int getHealthPoints() {
		return healthPoints;
	}

	public void setHealthPoints(int healthPoints) {
		this.healthPoints = healthPoints;
	}

	public boolean getSapperStatus() {
		return SapperStatus;
	}

	public void setSapperStatus(boolean sapperStatus) {
		SapperStatus = sapperStatus;
	}

	/**
	 * CONSTRUCTORS
	 */

	public Sapper() {
		super(id);
		id++;
		this.setSapperStatus(true);
		this.setHealthPoints(20);
		MainWindow.updateHPPanel("Sapper HP: " + this.getHealthPoints());
		MainWindow.updatePositionPanel("Pozycja sapera: [" + this.getPositionX() + "][" + this.getPositionY() + "]");
	}

	/**
	 * OTHER METHODS
	 */

	public void hurt() {
		if (this.getHealthPoints() > 0) {
			this.setHealthPoints(this.getHealthPoints() - 1);
			MainWindow.updateHPPanel("Saper HP: " + this.getHealthPoints());
		}
	}

	/**
	 * Saper idzie na pozycjê bomby. Nastpênie na pozycje x,y i zmienia pozycjê
	 * bomby.
	 */
	public void moveBomb(Item itemArgument, int x, int y) throws InterruptedException {
		if (itemArgument instanceof Bomb) {

			this.addTaskToMove(itemArgument, null);

			this.addTaskToMove(new Point(x, y), itemArgument);

			// IMPORTANT CODE TO USE IN THE FUTURE!!!

			// Sprawdza, czy przenosi bombe na krawedz planszy lub, czy na tym
			// miejscu nie ma innej bomby.

			// if ((x != 0) && (MainWindow.grid.cellPanes.get(x -
			// 1).get(y).getBackground() != Color.black)) {
			// itemArgument.setPositionX(x - 1);
			// itemArgument.setPositionY(y);
			//
			// MainWindow.grid.drawBomb(x - 1, y);
			// MainWindow.updateLog("The bomb was moved to [" + (x - 1) + "][" +
			// y + "]");
			// } else {
			// itemArgument.setPositionX(x);
			// if ((y != 0) && (MainWindow.grid.cellPanes.get(x).get(y -
			// 1).getBackground() != Color.black)) {
			// itemArgument.setPositionY(y - 1);
			//
			// MainWindow.grid.drawBomb(x, y - 1);
			// MainWindow.updateLog("The bomb was moved to [" + x + "][" + (y -
			// 1) + "]");
			// } else if ((y != 50) && (MainWindow.grid.cellPanes.get(x).get(y +
			// 1).getBackground() != Color.black)) {
			// itemArgument.setPositionY(y + 1);
			//
			// MainWindow.grid.drawBomb(x, y + 1);
			// MainWindow.updateLog("The bomb was moved to [" + x + "][" + (y +
			// 1) + "]");
			// } else {
			// MainWindow.updateLog("It was impossible to move bomb to [" + x +
			// "][" + (y + 1)
			// + "]. Choose different coordinates.");
			// }
			// }
		}

	}

	/**
	 * Wywo³uje metodê 'go', sprawdza status bomby, losowo decyduje, czy bomba
	 * wybuchnie.
	 */
	public void disarmBomb(Item itemArgument) throws InterruptedException {
		if (this.getHealthPoints() > 0) {
			if (itemArgument instanceof Bomb) {
				Bomb tempBombArgument = (Bomb) itemArgument;
				if (this.checkItemsCenterDistance(tempBombArgument)) {
					tempBombArgument.disarm(this);
					System.out.println("Number of disarmed bombs: " + this.getNumberOfDisarmedBombs());

				} else {
					System.out.println("too far to disarm bomb!");
					MainWindow.updateLog("Za daleko, ¿eby rozbroiæ bombê");
				}
			}
		}
	}
}
