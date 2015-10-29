package dkck;

public class Bomb extends Item {

	/**
	 * ATTRIBUTES
	 */

	TimeTask bombTimer;

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
		if (bombStatus != 1)
			bombTimer.cancel();
		if (bombStatus == 0)
			this.setExplosionLeftTime(0);
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

		bombTimer = new TimeTask(this, 1000);

	}

	/**
	 * OTHER METHODS
	 */

	/**
	 * modyfikuje nieznacznie pola bomby i zmniejsza punkty ¿ycia dla Sapera
	 */

	public void explode(Sapper sapper) {
		this.setBombStatus(0);
		System.out.println("The bomb nr: " + this.getId() + " exploded");
		// bombTimer.cancel();
		if (this.checkItemsRange(sapper) == true) {
			sapper.setHealthPoints(sapper.getHealthPoints() - 1);

			if (sapper.getHealthPoints() == 0) {
				sapper.setSapperStatus(false);
				System.out.println("The sapper HP is: " + sapper.getHealthPoints() + ". The sapper is dead");
			} else {
				System.out.println("The sapper HP is: " + sapper.getHealthPoints());
			}
		}

	}

}
