package dkck;

public class Bomb {

	/**
	 * ATTRIBUTES
	 */
	
	protected int positionX;
	
	protected int positionY;
	
	protected int explosionRange;
	
	protected String status;

	/**
	 * SETTERS AND GETTERS
	 */
	
	/**
	 * @return the positionX
	 */
	public int getPositionX() {
		return positionX;
	}
	
	/**
	 * @param positionX the positionX to set
	 */
	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	/**
	 * @return the positionY
	 */
	public int getPositionY() {
		return positionY;
	}

	/**
	 * @param positionY the positionY to set
	 */
	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
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
	 * @param positionX the explosionRange to set
	 */
	public void setExplosionRange(int explosionRange) {
		this.explosionRange = explosionRange;
	}
	
	/**
	 * CONSTRUCTORS
	 */

	public Bomb(int positionX, int positionY, int explosionRange) {
		super();
		this.positionX = positionX;
		this.positionY = positionY;
		this.explosionRange = explosionRange;
		this.status = "active";
	}

	/**
	 * OTHER METHODS
	 */
	
	/**
	 * Sprawdza czy saper jest w zasi�gu ra�enia bomby.
	 */
	public boolean checkExplosionRange(Sapper sapper) {
		for(int x = this.getPositionX() - this.getExplosionRange(); x < this.getPositionX() + this.getExplosionRange(); x++) {
			for(int y = this.getPositionY() - this.getExplosionRange(); y < this.getPositionY() + this.getExplosionRange(); y++) {
				if((sapper.getPositionX() == x) && (sapper.getPositionY() == y)) {
					System.out.println("Danger. The saper is in the bomb explosion range");	
					return true;
				}
			}
		}
		return false;
	}

	
	public void explode(Sapper sapper) {
		this.setStatus("afterExplosion");
		System.out.println("The bomb exploded");
		if(this.checkExplosionRange(sapper) == true) {
			sapper.setHealthPoints(sapper.getHealthPoints() - 1);
			
			if(sapper.getHealthPoints() == 0) {
				sapper.setStatus(false);
				System.out.println("The sapper HP is: " + sapper.getHealthPoints() + ".The sapper is dead");			
			}
			else {
				System.out.println("The sapper HP is: " + sapper.getHealthPoints());
			}			
		};			
	}
	
}
