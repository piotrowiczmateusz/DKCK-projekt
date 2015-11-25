package dkck;

import java.util.LinkedList;
import java.util.Random;

import dkck.GUI.MainWindow;

/**
 * @author Dariusz
 *
 */
public class Rocket extends Bomb {

	public Rocket(Item itemArgument) throws InterruptedException {
		super();
		Random generator = new Random();

		int speed = 25 + generator.nextInt(25);
		this.setTargetsArray(new LinkedList<Item>());
		this.setMovingTimer(new MovingTimer(this));

		if (itemArgument instanceof Sapper) {
			for (int i = 0; i < MainWindow.itemsCollection.getItemsArray().size(); i++) {
				Item tempItem = MainWindow.itemsCollection.getItemsArray().get(i);
				if (tempItem instanceof Sapper && tempItem != itemArgument) {
					this.reachItem(tempItem);
				}
			}
		}

		
		this.getMovingTimer().getTimer1().schedule(getMovingTimer(), 0, speed);
	}
}
