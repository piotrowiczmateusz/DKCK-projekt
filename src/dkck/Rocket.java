package dkck;

import java.util.LinkedList;
import java.util.Random;

import dkck.GUI.MainWindow;

/**
 * @author Dariusz
 */

public class Rocket extends Bomb {

	public Rocket(Item itemArgument) throws InterruptedException {
		super();

		Random generator = new Random();

		int speed = 50 + generator.nextInt(200);
		this.setTargetsArray(new LinkedList<Item>());
		this.setMovingTimer(new MovingTimer(this));

		if (itemArgument instanceof Sapper) {
			Sapper sapperReference = (Sapper) itemArgument;
			if (sapperReference.getNumberOfRockets() > 0) {
				sapperReference.setNumberOfRockets(sapperReference.getNumberOfRockets() - 1);

				for (Item tempItem : MainWindow.itemsCollection.getItemsArray()) {

					if (tempItem instanceof Sapper && tempItem != itemArgument) {
						this.reachItem(tempItem);
					}
				}
			}
		}
		this.getMovingTimer().getTimer1().schedule(getMovingTimer(), 0, speed);
	}
}
