package dkck;

import java.util.ArrayList;
import java.util.List;

public class ItemsOperations {

	/**
	 * @return the itemsArray
	 */
	public List<Item> getItemsArray() {
		return itemsArray;
	}

	/**
	 * @param itemsArray the itemsArray to set
	 */
	public void setItemsArray(List<Item> itemsArray) {
		this.itemsArray = itemsArray;
	}

	private List<Item> itemsArray;

	public ItemsOperations() {
		super();
		this.itemsArray = new ArrayList<Item>();

	}

	public void actions() throws InterruptedException {
		itemsArray.add((Item) new Bomb(2, 2, 2, 1, 1, 10));
		itemsArray.add((Item) new Bomb(8, 16, 5, 2, 1, 30));
		itemsArray.add((Item) new Sapper(1, 1, 2, 0));

		((Bomb) itemsArray.get(0)).explode(((Sapper) itemsArray.get(2)));

		((Sapper) itemsArray.get(2)).moveBomb(((Bomb) itemsArray.get(0)), 6, 9);
		((Sapper) itemsArray.get(2)).moveBomb(((Bomb) itemsArray.get(1)), 6, 9);

		((Sapper) itemsArray.get(2)).disarmBomb(((Bomb) itemsArray.get(0)));

		((Sapper) itemsArray.get(2)).disarmBomb(((Bomb) itemsArray.get(1)));
	}
}
