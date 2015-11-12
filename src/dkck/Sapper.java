package dkck;

//import java.awt.Color;

//import java.util.Random;

import java.util.Random;

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
	public Sapper(int positionX, int positionY, int range, int speed) {
		super(positionX, positionY, range, id++, speed);
		
		// targetsArray.add(new Point(14, 45));
		// targetsArray.add(new Point(8,3));
		this.setSapperStatus(true);
		this.setHealthPoints(2);

		

		MainWindow.updateHPPanel("Sapper HP: " + this.getHealthPoints());
		MainWindow.grid.drawSquare(this.getPositionX(), this.getPositionY(), this.getPositionX(), this.getPositionY(), MainWindow.sapperColor);
	}

	
	static Random generator = new Random();
	
	private static int positionX = generator.nextInt(MainWindow.gridRows);
	
	private static int positionY = generator.nextInt(MainWindow.gridColumns);
	
	private static int range = generator.nextInt(30);
	
	private static int speed = generator.nextInt(50);
	
	public Sapper(){
		super(positionX, positionY , range, id++, speed);

		// targetsArray.add(new Point(14, 45));
		// targetsArray.add(new Point(8,3));
		this.setSapperStatus(true);
		this.setHealthPoints(2);

		MainWindow.updateHPPanel("Sapper HP: " + this.getHealthPoints());
		MainWindow.grid.drawSquare(this.getPositionX(), this.getPositionY(), this.getPositionX(), this.getPositionY(), MainWindow.sapperColor);
		
	}
	
	
	
	
	
	/**
	 * OTHER METHODS
	 */

	/**
	 * Zmienia pozycje sapera i sprawdza czy znajduje siê w zasiêgu ra¿enia
	 * bomby.
	 */

	// private void step()
	// {
	//
	// }



	/**
	 * Wywo³uje metodê 'go', sprawdza status bomby, losowo decyduje, czy bomba
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
					System.out.println("wrong position!");
			} else
				MainWindow.updateLog("You can't disarm something else than bomb");
		} else
			MainWindow.updateLog("Dead sapper can't disarm anything");
	}
}
