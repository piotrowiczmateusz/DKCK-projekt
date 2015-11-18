package dkck;

import java.awt.Color;
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

	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public MovingTimer getMovingTimer() {
		return movingTimer;
	}

	public void setMovingTimer(MovingTimer sapperTimer) {
		this.movingTimer = sapperTimer;
	}

	public List<Item> getTargetsArray() {
		return targetsArray;
	}

	public void setTargetsArray(List<Item> targetsArray) {
		this.targetsArray = targetsArray;
	}

	/**
	 * CONSTRUCTORS
	 */

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
		
		Color tempColor = null;

		if (this instanceof Rocket){
			tempColor = MainWindow.rocketColor;
		}
		else if (this instanceof Bomb){
			tempColor = MainWindow.bombColor;
		}
		else if (this instanceof Sapper){
			tempColor = MainWindow.sapperColor;
		}
		
		MainWindow.grid.drawCircle(this.getPositionX(), this.getPositionY(), this.getRange(), null);
		MainWindow.grid.drawCircle(this.getPositionX(), this.getPositionY(), this.getRange(), tempColor);
		MainWindow.grid.drawSquare(positionX, positionY, positionX, positionY, tempColor);

		if (speed == 0) {
			this.setTargetsArray(null);
			this.setMovingTimer(null);
		} else {
			this.setTargetsArray(new LinkedList<Item>());
			this.setMovingTimer(new MovingTimer(this, speed));
		}
		
	}

	protected void addTaskToMove(Item targetToReach, Item itemToMove) throws InterruptedException {
		if (this.getTargetsArray() != null && this.getMovingTimer() != null) {
			this.getTargetsArray().add(targetToReach);
			this.getTargetsArray().add(itemToMove);
		} 
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
	}

	public double distanceCalculation(Item itemArgument) {
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
			return true;
		} else {			
			return false;
		}
	}

	/**
	 * Sprawdza czy œrodek obiektu jest w zasiêgu itemu
	 */

	public boolean checkItemsCenterDistance(Item itemArgument) {
		if (distanceCalculation(itemArgument) <= this.getRange()) {	
			return true;
		} else {		
			return false;
		}
	}

}