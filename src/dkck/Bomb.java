package dkck;

public class Bomb extends Item {

	/**
	 * ATTRIBUTES
	 */

	private int bombStatus;// EXPLODED - 0, ACTIVE - 1, DISARMED - 2

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
	 * CONSTRUCTORS
	 */

	/**
	 * @param positionX
	 * @param positionY
	 * @param range
	 * @param id
	 * @param bombStatus
	 */
	public Bomb(int positionX, int positionY, int range, int id, int bombStatus) {
		super(positionX, positionY, range, id);
		this.bombStatus = bombStatus;
	}

	/**
	 * OTHER METHODS
	 */

	/**
	 * Sprawdza czy saper jest w zasiêgu ra¿enia bomby.
	 */
	public boolean checkExplosionRange(Sapper sapper) {

		if (Math.sqrt(Math.pow(this.getPositionX() - sapper.getPositionX(), 2)
				+ Math.pow(this.getPositionY() - sapper.getPositionY(), 2)) <= this.getRange() + sapper.getRange()) {
			System.out.println("Danger. The sapper is in the bomb nr: " + this.getId() + " explosion range");
			return true;
		} else
			return false;
	}

	public void explode(Sapper sapper) {
		this.setBombStatus(0);
		System.out.println("The bomb nr: " + this.getId() + " exploded");
		if (this.checkExplosionRange(sapper) == true) {
			sapper.setHealthPoints(sapper.getHealthPoints() - 1);

			if (sapper.getHealthPoints() == 0) {
				sapper.setSapperStatus(false);
				System.out.println("The sapper HP is: " + sapper.getHealthPoints() + " .The sapper is dead");
			} else {
				System.out.println("The sapper HP is: " + sapper.getHealthPoints());
			}
		}

	}

}
