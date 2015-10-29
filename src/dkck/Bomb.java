package dkck;

public class Bomb extends Item {

	/**
	 * ATTRIBUTES
	 */

	private int bombStatus;// EXPLODED - 0, ACTIVE - 1, DISARMED - 2

	private int explosionLeftTime;// zmienna przechowujaca czas do wybuchu

	/**
	 * SETTERS AND GETTERS
	 */

	/**
	 * @return the bombStatus
	 */
	public int getBombStatus() {
		return bombStatus;
	}

	/**
	 * @param bombStatus
	 *            the bombStatus to set
	 */
	public void setBombStatus(int bombStatus) {
		this.bombStatus = bombStatus;
	}

	/**
	 * @return the explosionLeftTime
	 */
	public int getExplosionLeftTime() {
		return explosionLeftTime;
	}

	/**
	 * @param explosionLeftTime
	 *            the explosionLeftTime to set
	 */
	public void setExplosionLeftTime(int explosionLeftTime) {
		this.explosionLeftTime = explosionLeftTime;
	}

	/**
	 * CONSTRUCTORS
	 */

	/**
	 * @param positionX
	 * @param positionY
	 * @param range
	 * @param id
	 * @param bombStatus
	 */
	public Bomb(int positionX, int positionY, int range, int id, int bombStatus, int explosionLeftTime) {
		super(positionX, positionY, range, id);
		this.bombStatus = bombStatus;
		this.explosionLeftTime = explosionLeftTime;

		TimeTask timer1_task = new TimeTask(this);
		
		MainWindow.grid.drawBomb(positionX, positionY);

	}

	/**
	 * OTHER METHODS
	 */

	/**
	 * Sprawdza czy saper jest w zasi�gu ra�enia bomby.
	 */

	public boolean checkExplosionRange(Item sapper) {
		if (sapper instanceof Sapper) {
			if (Math.sqrt(Math.pow(this.getPositionX() - sapper.getPositionX(), 2)
					+ Math.pow(this.getPositionY() - sapper.getPositionY(), 2)) <= this.getRange()
							+ sapper.getRange()) {
				
				//System.out.println("Danger. The sapper is in the bomb nr: " + this.getId() + " explosion range");			
				MainWindow.updateLog("Danger. The sapper is in the bomb nr: " + this.getId() + " explosion range");
				
				return true;
			}
		}
		return false;
	}

	/**
	 * modyfikuje nieznacznie pola bomby i zmniejsza punkty �ycia dla Sapera
	 */
	
	public void explode(Sapper sapper) {
		
		this.setBombStatus(0);
		
		//System.out.println("The bomb nr: " + this.getId() + " exploded");
		MainWindow.updateLog("The bomb nr: " + this.getId() + " exploded");
		
		this.setExplosionLeftTime(0);
		if (this.checkExplosionRange(sapper) == true) {
			sapper.setHealthPoints(sapper.getHealthPoints() - 1);

			if (sapper.getHealthPoints() == 0) {
				sapper.setSapperStatus(false);
				
				//System.out.println("The sapper HP is: " + sapper.getHealthPoints() + " .The sapper is dead");
				MainWindow.updateLog("The sapper HP is: " + sapper.getHealthPoints() + " .The sapper is dead");
				
				MainWindow.updateHPPanel("Sapper HP is: " + sapper.getHealthPoints());
				
			} else {
				
				//System.out.println("The sapper HP is: " + sapper.getHealthPoints());
				MainWindow.updateLog("The sapper HP is: " + sapper.getHealthPoints());
				
				MainWindow.updateHPPanel("Sapper HP is: " + sapper.getHealthPoints());
			}
		}
	}
}
