package dkck;

import java.awt.Color;
import java.util.Random;

import dkck.GUI.MainWindow;

public class Sapper extends Item {

	/**
	 * ATTRIBUTES
	 */
	private int healthPoints;

	private boolean SapperStatus;

	/**
	 * SETTERS AND GETTERS
	 */

	/**
	 * @return the status
	 */
	public int getHealthPoints() {
		return healthPoints;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setHealthPoints(int healthPoints) {
		this.healthPoints = healthPoints;
	}

	/**
	 * @return the sapperStatus
	 */
	public boolean getSapperStatus() {
		return SapperStatus;
	}

	/**
	 * @param sapperStatus
	 *            the sapperStatus to set
	 */
	public void setSapperStatus(boolean sapperStatus) {
		SapperStatus = sapperStatus;
	}

	/**
	 * CONSTRUCTORS
	 */
	public Sapper(int positionX, int positionY, int range) {
		super(positionX, positionY, range);
		this.setSapperStatus(true);
		this.setHealthPoints(2);

		MainWindow.updateHPPanel("Sapper HP is: " + this.getHealthPoints());
		MainWindow.grid.drawSapper(0, 0, this.getPositionX(), this.getPositionY());
	}

	/**
	 * OTHER METHODS
	 */

	/**
	 * Zmienia pozycje sapera i sprawdza czy znajduje si� w zasi�gu ra�enia
	 * bomby.
	 */

	public void go(int x, int y, Item itemToMove) throws InterruptedException {

		while ((x != this.getPositionX()) || (y != this.getPositionY())) {

			int prevPositionX = this.getPositionX();
			int prevPositionY = this.getPositionY();

			if (x > this.getPositionX()) {
				this.setPositionX(this.getPositionX() + 1);
			} else if (x < this.getPositionX()) {
				this.setPositionX(this.getPositionX() - 1);
			}
			if (y > this.getPositionY()) {
				this.setPositionY(this.getPositionY() + 1);
			} else if (y < this.getPositionY()) {
				this.setPositionY(this.getPositionY() - 1);
			}

			MainWindow.updatePositionPanel(
					"Sapper position is: [" + this.getPositionX() + "][" + this.getPositionY() + "]");

			if (itemToMove instanceof Bomb) {
				itemToMove.setPositionX(this.getPositionX());
				itemToMove.setPositionY(this.getPositionY());
				MainWindow.updateLog("Position of moving bomb is: [" + itemToMove.getPositionX() + "]["
						+ itemToMove.getPositionY() + "]");
				MainWindow.grid.drawBomb(itemToMove.getPositionX(), itemToMove.getPositionY());
			} else {
				MainWindow.updateLog("You are not moving antyhing");
			}

			MainWindow.grid.drawSapper(prevPositionX, prevPositionY, this.getPositionX(), this.getPositionY());

			for (int i = 0; i < MainWindow.itemsCollection.getItemsArray().size(); i++) {
				Item tempItem = MainWindow.itemsCollection.getItemsArray().get(i);
				int tempX = tempItem.getPositionX();
				int tempY = tempItem.getPositionY();
				if ((tempX == prevPositionX) && (tempY == prevPositionY)) {
					if (tempItem instanceof Bomb) {
						MainWindow.grid.drawBomb(tempX, tempY);

						this.checkItemsRange(tempItem);
					} else if (tempItem instanceof Sapper) {
						MainWindow.grid.drawSapper(prevPositionX, prevPositionY, tempX, tempY);
					}
				}
			}
			Thread.sleep(100);
		}
	}

	/**
	 * Saper idzie na pozycj� bomby. Nastp�nie na pozycje x,y i zmienia pozycj�
	 * bomby.
	 */
	public void moveBomb(Item itemArgument, int x, int y) throws InterruptedException {
		if (itemArgument instanceof Bomb) {
			MainWindow.updateLog("The sapper at position [" + this.getPositionX() + "][" + this.getPositionY()
					+ "] will try to move bomb nr: " + itemArgument.getId() + " at the position ["
					+ itemArgument.getPositionX() + "][" + itemArgument.getPositionY() + "] to the position: [" + x
					+ "][" + y + "]");

			this.go(itemArgument.getPositionX(), itemArgument.getPositionY(), null);

			MainWindow.updateLog("The sapper picked up a bomb.");

			this.go(x, y, itemArgument);

			// Sprawdza, czy przenosi bombe na krawedz planszy lub, czy na tym
			// miejscu nie ma innej bomby.

			if ((x != 0) && (MainWindow.grid.cellPanes.get(x - 1).get(y).getBackground() != Color.black)) {
				itemArgument.setPositionX(x - 1);
				itemArgument.setPositionY(y);

				MainWindow.grid.drawBomb(x - 1, y);
				MainWindow.updateLog("The bomb was moved to [" + (x - 1) + "][" + y + "]");
			} else {
				itemArgument.setPositionX(x);
				if ((y != 0) && (MainWindow.grid.cellPanes.get(x).get(y - 1).getBackground() != Color.black)) {
					itemArgument.setPositionY(y - 1);

					MainWindow.grid.drawBomb(x, y - 1);
					MainWindow.updateLog("The bomb was moved to [" + x + "][" + (y - 1) + "]");
				} else if ((y != 50) && (MainWindow.grid.cellPanes.get(x).get(y + 1).getBackground() != Color.black)) {
					itemArgument.setPositionY(y + 1);

					MainWindow.grid.drawBomb(x, y + 1);
					MainWindow.updateLog("The bomb was moved to [" + x + "][" + (y + 1) + "]");
				} else {
					MainWindow.updateLog("It was impossible to move bomb to [" + x + "][" + (y + 1)
							+ "]. Choose different coordinates.");
				}
			}
		} else
			MainWindow.updateLog("THIS IS NOT A BOMB!");

	}

	/**
	 * Wywo�uje metod� 'go', sprawdza status bomby, losowo decyduje, czy bomba
	 * wybuchnie.
	 */
	public void disarmBomb(Item itemArgument) throws InterruptedException {
		if (itemArgument instanceof Bomb) {
			Bomb tempItemArgument = (Bomb) itemArgument;
			go(itemArgument.getPositionX(), itemArgument.getPositionY(), null);

			if (tempItemArgument.getBombStatus() != 1) {

				MainWindow.updateLog("The bomb nr: " + itemArgument.getId() + " was already disarmed");

			} else {
				Random generator = new Random();
				int success = generator.nextInt(2);
				if (success == 1) {
					tempItemArgument.setBombStatus(2);

					MainWindow.updateLog("The bomb nr " + itemArgument.getId() + " is now disarmed");

				} else {
					tempItemArgument.explode(this);
				}
			}
		} else
			System.out.println("You can't disarm something else than bomb");
	}
}
