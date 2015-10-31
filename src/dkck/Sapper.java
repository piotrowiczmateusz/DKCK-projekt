package dkck;

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
	 * Zmienia pozycje sapera i sprawdza czy znajduje siê w zasiêgu ra¿enia
	 * bomby.
	 */

	public void go(int x, int y, Item itemToMove) throws InterruptedException {

		while ((x != this.getPositionX()) || (y != this.getPositionY())) {
				
			int prevPositionX = this.getPositionX();
			int prevPositionY = this.getPositionY();
			
			int bombX;
			int bombY;
					
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
			
			if (itemToMove instanceof Bomb) {
				itemToMove.setPositionX(this.getPositionX());
				itemToMove.setPositionY(this.getPositionY());
				MainWindow.updateLog("Position of moving bomb is: [" + itemToMove.getPositionX() + "]["
						+ itemToMove.getPositionY() + "]");
			} else {
				MainWindow.updateLog("It is not possible to move bomb");
			}

			MainWindow.grid.drawSapper(prevPositionX, prevPositionY, this.getPositionX(), this.getPositionY());
			
			for (int i = 0; i < MainWindow.itemsCollection.getItemsArray().size(); i++) {
				Item tempItem = MainWindow.itemsCollection.getItemsArray().get(i);
				if (tempItem instanceof Bomb) {
					
					bombX = tempItem.getPositionX();
					bombY = tempItem.getPositionY();
					
					MainWindow.grid.drawBomb(bombX, bombY);
					
					if((bombX == this.getPositionX()) && (bombY == this.getPositionY())) {
						MainWindow.grid.drawSapper(prevPositionX, prevPositionY, this.getPositionX(), this.getPositionY());
					}
										
					if (this.checkItemsRange(tempItem)) {
						//MainWindow.updateLog("");
					}						
				}
			}		
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
		
		this.go(bomb.getPositionX(), bomb.getPositionY(), null);
			
		MainWindow.updateLog("The sapper picked up a bomb.");
		
		this.go(x, y, bomb);
		
		if(x != 0) {
			bomb.setPositionX(x-1);
			bomb.setPositionY(y);
			
			MainWindow.grid.drawBomb(x-1, y);
			MainWindow.updateLog("The bomb was moved to [" + (x-1) + "][" + y + "]");
		}
		else {
			bomb.setPositionX(x);
			if(y != 0) {
				bomb.setPositionY(y-1);
				
				MainWindow.grid.drawBomb(x, y-1);
				MainWindow.updateLog("The bomb was moved to [" + x + "][" + (y-1) + "]");
			}
			else if(y != 50) {
				bomb.setPositionY(y+1);
				
				MainWindow.grid.drawBomb(x, y+1);
				MainWindow.updateLog("The bomb was moved to [" + x + "][" + (y+1) + "]");
			}
		}
	}

	/**
	 * Wywo³uje metodê 'go', sprawdza status bomby, losowo decyduje, czy bomba
	 * wybuchnie.
	 */
	public void disarmBomb(Bomb bomb) throws InterruptedException {
		go(bomb.getPositionX(), bomb.getPositionY(), null);

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
