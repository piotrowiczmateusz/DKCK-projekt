/**
 * 
 */
package dkck;

/**
 * @author Dariusz
 *
 */
public class Point extends Item {
	
	
	public static int id = 0;

	/**
	 * @param positionX
	 * @param positionY
	 */
	public Point(int positionX, int positionY) {
		super(id);
		++id;
		this.setPositionX(positionX);
		this.setPositionY(positionY);
		
		// TODO Auto-generated constructor stub
	}

}
