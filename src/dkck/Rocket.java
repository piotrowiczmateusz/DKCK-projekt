/**
 * 
 */
package dkck;

import dkck.GUI.MainWindow;

/**
 * @author Dariusz
 *
 */
public class Rocket extends Bomb {

	/**
	 * @throws InterruptedException
	 * 
	 */
	public Rocket(Item itemArgument) throws InterruptedException {
		if (itemArgument instanceof Sapper) {
			for (int i = 0; i < MainWindow.itemsCollection.getItemsArray().size(); i++) {
				Item tempItem = MainWindow.itemsCollection.getItemsArray().get(i);
				if (tempItem instanceof Sapper && tempItem != itemArgument) {
					this.reachItem(tempItem);
				}
			}
		}
		// this.reachItem(itemToReach);
		// TODO Auto-generated constructor stub
	}

}
