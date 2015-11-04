package dkck;

//import java.awt.Color;

import java.util.Random;
import java.util.LinkedList;
import java.util.List;

import dkck.GUI.MainWindow;

public class Sapper extends Item {

	/**
	 * ATTRIBUTES
	 */
	
	public static int id = 0;
	
	TimeTask sapperTimer;

	private int healthPoints;

	private boolean SapperStatus;

	private List<Item> targetsArray;

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
	 * @return the targetsArray
	 */
	public List<Item> getTargetsArray() {
		return targetsArray;
	}

	/**
	 * @param targetsArray
	 *            the targetsArray to set
	 */
	public void setTargetsArray(List<Item> targetsArray) {
		this.targetsArray = targetsArray;
	}
	
	public void hurt()
	{
		--this.healthPoints;
		MainWindow.updateHPPanel("Sapper HP is: " + this.healthPoints);
	}

	/**
	 * CONSTRUCTORS
	 */
	public Sapper(int positionX, int positionY, int range, int speed) {
		super(positionX, positionY, range, id++);
		targetsArray = new LinkedList<Item>();
		// targetsArray.add(new Point(14, 45));
		// targetsArray.add(new Point(8,3));
		this.setSapperStatus(true);
		this.setHealthPoints(2);

		sapperTimer = new TimeTask(this, speed);

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

	// private void step()
	// {
	//
	// }

	public void go(Item targetToReach, Item itemToMove) throws InterruptedException {
		targetsArray.add(targetToReach);
		targetsArray.add(itemToMove);

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

			this.go(itemArgument, null);

			MainWindow.updateLog("The sapper picked up a bomb.");

			this.go(new Point(x, y), itemArgument);
			
			//IMPORTANT CODE TO USE IN THE FUTURE!!!

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
		} else
			MainWindow.updateLog("THIS IS NOT A BOMB!");

	}

	/**
	 * Wywo�uje metod� 'go', sprawdza status bomby, losowo decyduje, czy bomba
	 * wybuchnie.
	 */
	public void disarmBomb(Item itemArgument) throws InterruptedException {
		if (itemArgument instanceof Bomb) {
			Bomb tempBombArgument = (Bomb) itemArgument;
			if (this.getPositionX() == tempBombArgument.getPositionX()
					&& this.getPositionY() == tempBombArgument.getPositionY()) {

				// go(itemArgument, null);

				if (tempBombArgument.getBombStatus() != 1) {

					MainWindow.updateLog("The bomb nr: " + itemArgument.getId() + " was already disarmed");
					
				} else {
					Random generator = new Random();
					int success = generator.nextInt(2);
					success = 1;
					if (success == 1) {
						tempBombArgument.setBombStatus(2);
						tempBombArgument.disarm();
						MainWindow.updateLog("The bomb nr " + itemArgument.getId() + " is now disarmed");

					} else {
						tempBombArgument.explode();
					}
				}
			}
		} else
			MainWindow.updateLog("You can't disarm something else than bomb");
	}
}
