package dkck;

import java.util.ArrayList;
import java.util.List;

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

	public void actions() throws InterruptedException {
		itemsArray.add((Item) new Bomb(2, 2, 2, 1, 1, 10));//dodawanie do listy elementów dziedzicz¹cych z klasy Item z okreœlonymi parametrami
		itemsArray.add((Item) new Bomb(8, 16, 5, 2, 1, 30));
		itemsArray.add((Item) new Bomb(18, 10, 5, 2, 1, 30));
		itemsArray.add((Item) new Sapper(1, 1, 2, 0));
		//((Sapper) itemsArray.get(3)).go(6,10);
		((Sapper) itemsArray.get(3)).moveBomb(((Bomb) itemsArray.get(2)), 1, 1);
		//((Bomb) itemsArray.get(0)).explode(((Sapper) itemsArray.get(2)));//wywo³ywanie metod dla poszczególnych obiektów z lisy przy u¿yciu rzutowania (inteligentna funkcja wyszukuj¹ca obiekty po okreœlonym unikalnym dla obiektu ID jest jeszcze do napisania)

		//((Sapper) itemsArray.get(2)).moveBomb(((Bomb) itemsArray.get(0)), 6, 9);
		//((Sapper) itemsArray.get(2)).moveBomb(((Bomb) itemsArray.get(1)), 6, 9);

		//((Sapper) itemsArray.get(2)).disarmBomb(((Bomb) itemsArray.get(0)));

		//((Sapper) itemsArray.get(2)).disarmBomb(((Bomb) itemsArray.get(1)));
	}
}
