package dkck;

public abstract class Item {

	/**
	 * ATTRIBUTES
	 */
	private int positionX;
	
	private int positionY;
	
	private int range;
	
	private int id;
	
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
	 * @return the range
	 */
	public int getRange() {
		return range;
	}

	/**
	 * @param range the range to set
	 */
	public void setRange(int range) {
		this.range = range;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * CONSTRUCTORS
	 */
	

	/**
	 * @param positionX
	 * @param positionY
	 * @param range
	 * @param id
	 */
	public Item(int positionX, int positionY, int range, int id) {
		super();
		this.positionX = positionX;
		this.positionY = positionY;
		this.range = range;
		this.id = id;
	}

	

}
