package dkck;

public class Bomb extends Item {

	/**
	 * ATTRIBUTES
	 */

	protected int explosionRange;

	protected String status;

	/**
	 * SETTERS AND GETTERS
	 */

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the explosionRange
	 */
	public int getExplosionRange() {
		return explosionRange;
	}

	/**
	 * @param positionX
	 *            the explosionRange to set
	 */
	public void setExplosionRange(int explosionRange) {
		this.explosionRange = explosionRange;
	}

	/**
	 * CONSTRUCTORS
	 */

	public Bomb(int positionX, int positionY, int explosionRange) {
		super(positionX, positionY);
		this.explosionRange = explosionRange;
		this.status = "active";
	}

	/**
	 * OTHER METHODS
	 */

	/**
	 * Sprawdza czy saper jest w zasiêgu ra¿enia bomby.
	 */
	public boolean checkExplosionRange(Sapper sapper) {

		if (Math.sqrt(Math.pow(this.positionX - sapper.positionX, 2)
				+ Math.pow(this.positionY - sapper.positionY, 2)) <= this.explosionRange + sapper.safeZone) {
			System.out.println("Danger. The sapper is in the bomb explosion range");
			return true;
		} else
			return false;
	}

	public void explode(Sapper sapper) {
		this.setStatus("afterExplosion");
		System.out.println("The bomb exploded");
		if (this.checkExplosionRange(sapper) == true) {
			sapper.setHealthPoints(sapper.getHealthPoints() - 1);

			if (sapper.getHealthPoints() == 0) {
				sapper.setStatus(false);
				System.out.println("The sapper HP is: " + sapper.getHealthPoints() + ".The sapper is dead");
			} else {
				System.out.println("The sapper HP is: " + sapper.getHealthPoints());
			}
		}
		;
	}

}
