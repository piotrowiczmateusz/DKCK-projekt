package dkck;

//import java.awt.Color;

//import java.util.Random;

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



	public void hurt() {
		if (this.getHealthPoints() > 0) {
			this.setHealthPoints(this.getHealthPoints() - 1);
			MainWindow.updateHPPanel("Sapper HP: " + this.getHealthPoints());
		}
	}

	/**
	 * CONSTRUCTORS
	 */
//	public Sapper(int positionX, int positionY, int range, int speed) {
//		super(positionX, positionY, range, id, speed);
//		id++;
//		
//		// targetsArray.add(new Point(14, 45));
//		// targetsArray.add(new Point(8,3));
//		this.setSapperStatus(true);
//		this.setHealthPoints(2);
//
//		MainWindow.updateHPPanel("Sapper HP: " + this.getHealthPoints());
//		MainWindow.grid.drawSquare(this.getPositionX(), this.getPositionY(), this.getPositionX(), this.getPositionY(), MainWindow.sapperColor);
//	}

	public Sapper(){
		super(id);
		id++;

		// targetsArray.add(new Point(14, 45));
		// targetsArray.add(new Point(8,3));
		this.setSapperStatus(true);
		this.setHealthPoints(20);

		MainWindow.updateHPPanel("Sapper HP: " + this.getHealthPoints());
		MainWindow.grid.drawSquare(this.getPositionX(), this.getPositionY(), this.getPositionX(), this.getPositionY(), MainWindow.sapperColor);
		
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
	
	
	/**
	 * Saper idzie na pozycj� bomby. Nastp�nie na pozycje x,y i zmienia pozycj�
	 * bomby.
	 */
	public void moveBomb(Item itemArgument, int x, int y) throws InterruptedException {
		if (itemArgument instanceof Bomb) {
			// MainWindow.updateLog("The sapper at position [" +
			// this.getPositionX() + "][" + this.getPositionY()
			// + "] will try to move bomb nr: " + itemArgument.getId() + " at
			// the position ["
			// + itemArgument.getPositionX() + "][" +
			// itemArgument.getPositionY() + "] to the position: [" + x
			// + "][" + y + "]");

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
		} else
			MainWindow.updateLog("THIS IS NOT A BOMB!");

	}




	/**
	 * Wywo�uje metod� 'go', sprawdza status bomby, losowo decyduje, czy bomba
	 * wybuchnie.
	 */
	public void disarmBomb(Item itemArgument) throws InterruptedException {
		if (this.getHealthPoints() > 0) {
			if (itemArgument instanceof Bomb) {
				Bomb tempBombArgument = (Bomb) itemArgument;
				if (this.checkItemsCenterDistance(tempBombArgument)) {

					// go(itemArgument, null);

					tempBombArgument.disarm(this);
					System.out.println("Number of disarmed bombs: " + this.getNumberOfDisarmedBombs());

				} else
					System.out.println("too far to disarm bomb!");
			} else
				MainWindow.updateLog("You can't disarm something else than bomb");
		} else
			MainWindow.updateLog("Dead sapper can't disarm anything");
	}
}
