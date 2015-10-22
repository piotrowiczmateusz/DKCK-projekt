package dkck;

public abstract class Item {

	/**
	 * ATTRIBUTES
	 */
	protected int positionX;
	
	protected int positionY;
	
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
	 * CONSTRUCTORS
	 */
	public Item(int positionX, int positionY) {
		super();
		this.positionX = positionX;
		this.positionY = positionY;
	}
}
