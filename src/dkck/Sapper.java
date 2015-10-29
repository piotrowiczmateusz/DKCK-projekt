package dkck;

import java.util.Random;

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
	public Sapper(int positionX, int positionY, int range, int id) {
		super(positionX, positionY, range, id);
		this.setSapperStatus(true);
		this.setHealthPoints(2);
		
		MainWindow.updateHPPanel("Sapper HP is: " + this.getHealthPoints());
		MainWindow.grid.drawSapper(0, 0, this.getPositionX(), this.getPositionY());
	}

	/**
	 * OTHER METHODS
	 */

	/**
	 * Zmienia pozycje sapera i sprawdza czy znajduje siê w zasiêgu ra¿enia
	 * bomby.
	 */

	public void go(int x, int y) throws InterruptedException {

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
			
			MainWindow.updatePositionPanel("Sapper position is: [" + this.getPositionX() + "][" + this.getPositionY() + "]");
			
			/*if (itemToMove instanceof Bomb && itemToMove.getPositionX() == this.getPositionX() && itemToMove.getPositionY() == this.getPositionY()) {
				itemToMove.setPositionX(this.getPositionX());
				itemToMove.setPositionY(this.getPositionY());
				System.out.println("Position of moving bomb is: [" + itemToMove.getPositionX() + "]["
						+ itemToMove.getPositionY() + "]");
			} else System.out.println("It is not possible to move bomb");*/

			for (int i = 0; i < MainWindow.itemsCollection.getItemsArray().size(); i++) {
				Item tempItem = MainWindow.itemsCollection.getItemsArray().get(i);
				if (tempItem instanceof Bomb) {
					if (this.checkItemsRange(tempItem)) {
						//MainWindow.updateLog("");
					}						
				}
			}
			MainWindow.grid.drawSapper(prevPositionX, prevPositionY, this.getPositionX(), this.getPositionY());

		Thread.sleep(500);
		}
	}

	/**
	 * Saper idzie na pozycjê bomby. Nastpênie na pozycje x,y i zmienia pozycjê
	 * bomby.
	 */
	public void moveBomb(Bomb bomb, int x, int y) throws InterruptedException {

		MainWindow.updateLog("The sapper at position [" + this.getPositionX() + "][" + this.getPositionY()
		+ "] will try to move bomb nr: " + bomb.getId() + " at the position [" + bomb.getPositionX() + "]["
		+ bomb.getPositionY() + "] to the position: [" + x + "][" + y + "]");
		
		this.go(bomb.getPositionX(), bomb.getPositionY());
			
		MainWindow.updateLog("The sapper picked up a bomb.");
		
		this.go(x, y);

		bomb.setPositionX(x);
		bomb.setPositionY(y);
	
		MainWindow.updateLog("The bomb was moved to [" + x + "][" + y + "]");	
		MainWindow.grid.drawBomb(x, y);
	}

	/**
	 * Wywo³uje metodê 'go', sprawdza status bomby, losowo decyduje, czy bomba
	 * wybuchnie.
	 */
	public void disarmBomb(Bomb bomb) throws InterruptedException {
		go(bomb.getPositionX(), bomb.getPositionY());

		if (bomb.getBombStatus() != 1) {
			
			MainWindow.updateLog("The bomb nr: " + bomb.getId() + " was already disarmed");
			
		} else {
			Random generator = new Random();
			int success = generator.nextInt(2);
			if (success == 1) {
				bomb.setBombStatus(2);
				
				MainWindow.updateLog("The bomb nr " + bomb.getId() + " is now disarmed");
				
			} else {
				bomb.explode(this);
			}
		}
	}
}
