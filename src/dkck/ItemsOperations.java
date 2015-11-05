package dkck;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import dkck.GUI.MainWindow;

public class ItemsOperations {

	private List<Item> itemsArray;

	/**
	 * SETTERS AND GETTERS
	 */

	/**
	 * @return the itemsArray
	 */
	public List<Item> getItemsArray() {
		return itemsArray;
	}

	/**
	 * @param itemsArray
	 *            the itemsArray to set
	 */
	public void setItemsArray(List<Item> itemsArray) {
		this.itemsArray = itemsArray;
	}

	/**
	 * CONSTRUCTORS
	 */

	public ItemsOperations() {
		super();
		this.itemsArray = new ArrayList<Item>();
	}

	
	public void addItems() {

		itemsArray.add((Item) new Bomb(2, 2, 1, 10));
		itemsArray.add((Item) new Bomb(16, 8, 5, 20));
		itemsArray.add((Item) new Bomb(7, 20, 2, 20));
		itemsArray.add((Item) new Sapper(8, 9, 2, 100));
		
		for (int i = 0; i < itemsArray.size(); i++) {
			Item tempItem = itemsArray.get(i);
			if (tempItem instanceof Bomb) {
				MainWindow.timerPanel.add(((Bomb) tempItem).getTimerLog(), BorderLayout.WEST);
			}
		}
		
	}

	public void actions() throws InterruptedException {

		((Sapper) itemsArray.get(3)).disarmBomb(itemsArray.get(1));
		
		((Sapper) itemsArray.get(3)).go(3, 7);

		((Sapper) itemsArray.get(3)).moveBomb(itemsArray.get(1), 6, 18);

		((Sapper) itemsArray.get(3)).addTaskTomove(itemsArray.get(2),null);

		((Sapper) itemsArray.get(3)).go(10, 13);
	
		((Sapper) itemsArray.get(3)).addTaskTomove(itemsArray.get(1), null);
		((Sapper) itemsArray.get(3)).go(5, 5);
		((Sapper) itemsArray.get(3)).go(7, 18);
		
		Thread.sleep(3000);
		
		((Bomb) itemsArray.get(0)).explode();
		
		//Thread.sleep(8000);

		((Sapper) itemsArray.get(3)).disarmBomb(itemsArray.get(1));
		
		
	}
}