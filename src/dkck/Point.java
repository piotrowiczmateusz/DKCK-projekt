package dkck;

/**
 * @author Dariusz
 *
 */
public class Point extends Item {
	
	public static int id = 0;

	public Point(int positionX, int positionY) {
		super(id);
		++id;
		this.setPositionX(positionX);
		this.setPositionY(positionY);
	}
}
