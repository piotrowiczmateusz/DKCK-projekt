package dkck;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import dkck.GUI.MainWindow;

public abstract class Item {

	/**
	 * ATTRIBUTES
	 */
	private int positionX;

	private int positionY;

	private int range;

	private int id;

	MovingTimer movingTimer;

	private List<Item> targetsArray;

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
	 * @param positionX
	 *            the positionX to set
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
	 * @param positionY
	 *            the positionY to set
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
	 * @param range
	 *            the range to set
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
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the movingTimer
	 */
	public MovingTimer getMovingTimer() {
		return movingTimer;
	}

	public void setMovingTimer(MovingTimer sapperTimer) {
		this.movingTimer = sapperTimer;
	}

	/**
	 * @return the targetsArray
	 */
	public List<Item> getTargetsArray() {
		return targetsArray;
	}

	/**
	 * @param targetsArray
	 *            the targetsArray to set
	 */
	public void setTargetsArray(List<Item> targetsArray) {
		this.targetsArray = targetsArray;
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
	// public Item(int positionX, int positionY, int range, int id, int speed) {
	// super();
	// this.positionX = positionX;
	// this.positionY = positionY;
	// this.range = range;
	// this.id = id;
	//
	// if (speed == 0) {
	// this.setTargetsArray(null);
	// this.setMovingTimer(null);
	// } else {
	// this.setTargetsArray(new LinkedList<Item>());
	// this.setMovingTimer(new MovingTimer(this, speed));
	// }
	//
	// // this.setMovingTimer(new MovingTimer(this, 50));
	//
	// }

	public Item(int id) {
		super();
		Random generator = new Random();

		int positionX = 0;

		int positionY = 0;

		if (this instanceof Point == false) {
			positionX = generator.nextInt(MainWindow.gridRows);

			positionY = generator.nextInt(MainWindow.gridColumns);
		}

		int range = 2 + generator.nextInt(10);

		int speed = 0;

		if (this instanceof Sapper)
			speed = 70 + generator.nextInt(50);

		if (this instanceof Rocket)
			speed = 25 + generator.nextInt(25);

		this.positionX = positionX;
		this.positionY = positionY;
		this.range = range;
		this.id = id;

		if (this instanceof Rocket){
			MainWindow.grid.drawSquare(positionX, positionY, positionX, positionY, MainWindow.rocketColor);
			MainWindow.grid.drawCircle(this.getPositionX(), this.getPositionY(), this.getRange(), null);
			MainWindow.grid.drawCircle(this.getPositionX(), this.getPositionY(), this.getRange(), MainWindow.rocketColor);
		}
		else if (this instanceof Bomb){

			MainWindow.grid.drawSquare(positionX, positionY, positionX, positionY, MainWindow.bombColor);
			MainWindow.grid.drawCircle(this.getPositionX(), this.getPositionY(), this.getRange(), null);
			MainWindow.grid.drawCircle(this.getPositionX(), this.getPositionY(), this.getRange(), MainWindow.bombColor);
		}
		else if (this instanceof Sapper){
			MainWindow.grid.drawSquare(positionX, positionY, positionX, positionY, MainWindow.sapperColor);
			MainWindow.grid.drawCircle(this.getPositionX(), this.getPositionY(), this.getRange(), null);
			MainWindow.grid.drawCircle(this.getPositionX(), this.getPositionY(), this.getRange(), MainWindow.sapperColor);
		}

		if (speed == 0) {
			this.setTargetsArray(null);
			this.setMovingTimer(null);
		} else {
			this.setTargetsArray(new LinkedList<Item>());
			this.setMovingTimer(new MovingTimer(this, speed));
		}
		// this.setMovingTimer(new MovingTimer(this, 50));
	}

	protected void addTaskToMove(Item targetToReach, Item itemToMove) throws InterruptedException {
		if (this.getTargetsArray() != null && this.getMovingTimer() != null) {
			this.getTargetsArray().add(targetToReach);
			this.getTargetsArray().add(itemToMove);
		} else
			System.out.println("Nothing happens");
	}

	public void reachItem(Item itemToReach) throws InterruptedException {
		addTaskToMove(itemToReach, null);
	}

	public void go(int x, int y) throws InterruptedException {
		this.addTaskToMove(new Point(x, y), null);
	}

	public String nameOfItem(Item itemArgument) {

		Class<?> cls = null;
		String tempText = "";
		Item tempItem = itemArgument;

		for (int i = 0; i < 4; ++i) {
			if (i == 0) {
				cls = Bomb.class;
				tempText = "Bomb";
			} else if (i == 1) {
				cls = Sapper.class;
				tempText = "Sapper";
			} else if (i == 2) {
				cls = Point.class;
				tempText = "Point";
			} else if (i == 3) {
				cls = Point.class;
				tempText = "Rocket";
			}
			if (cls.isInstance(tempItem) && tempItem.getClass().equals(cls)) {
				return tempText;
			}
		}

		return tempText;
		// return text;
	}

	private double distanceCalculation(Item itemArgument) {
		System.out.println("DISTANCE CALCULATION:");

		Item tempItem = null;

		for (int i = 0; i < 2; ++i) {
			if (i == 0)
				tempItem = this;
			else if (i == 1)
				tempItem = itemArgument;

			System.out.println(nameOfItem(tempItem) + " with id: " + tempItem.id + " and position: ["
					+ (tempItem.getPositionX() + 1) + "][" + (tempItem.getPositionY() + 1) + "]");

		}
		double tempDistance = Math.sqrt(Math.pow(this.getPositionX() - itemArgument.getPositionX(), 2)
				+ Math.pow(this.getPositionY() - itemArgument.getPositionY(), 2));
		System.out.println("Distance is: " + tempDistance);
		return tempDistance;
	}

	/**
	 * Sprawdza czy item jest w zasiêgu innego itemu
	 */

	public boolean checkItemsRange(Item itemArgument) {

		if (distanceCalculation(itemArgument) <= this.getRange() + itemArgument.getRange()) {
			MainWindow.updateLog("Items are in their range!");
			return true;
		} else {
			MainWindow.updateLog("Items are NOT in their range!");
			return false;
		}
	}

	/**
	 * Sprawdza czy œrodek obiektu jest w zasiêgu itemu
	 */

	public boolean checkItemsCenterDistance(Item itemArgument) {
		if (distanceCalculation(itemArgument) <= this.getRange()) {

			MainWindow.updateLog("Center of another Item is included in this range!");
			return true;
		} else {
			MainWindow.updateLog("Center of another Item is NOT included in this range!!");
			return false;
		}
	}

}
