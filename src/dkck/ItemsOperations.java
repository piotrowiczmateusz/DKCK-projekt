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
	

	public void actions() throws InterruptedException {

		itemsArray.add((Item) new Bomb());//(2, 2, 40, 10));// dodawanie do listy

		// elementów
		// dziedziczących z
		// klasy Item z
		// określonymi
		// parametrami
		itemsArray.add((Item) new Bomb());//16, 8, 5, 30));
		itemsArray.add((Item) new Bomb());//7, 20, 20, 30));
		itemsArray.add((Item) new Sapper());//2, 7, 2, 40));
		itemsArray.add((Item) new Sapper());//8, 9, 2, 70));

		for (int i = 0; i < itemsArray.size(); i++) {
			Item tempItem = itemsArray.get(i);
			if (tempItem instanceof Bomb) {
				MainWindow.timerPanel.add(((Bomb) tempItem).getTimerLog(), BorderLayout.WEST);
			}
		}

		// ((Sapper) itemsArray.get(3)).go(0, 0, null);

		((Sapper) itemsArray.get(3)).moveBomb(itemsArray.get(2), 2, 3);

		((Sapper) itemsArray.get(4)).moveBomb(itemsArray.get(1), 45, 49);
		((Sapper) itemsArray.get(4)).moveBomb(itemsArray.get(1), 1, 1);
		((Sapper) itemsArray.get(4)).reachItem(itemsArray.get(1));
		((Sapper) itemsArray.get(4)).reachItem(itemsArray.get(3));

		((Sapper) itemsArray.get(3)).go(3, 7);

		((Sapper) itemsArray.get(3)).moveBomb(itemsArray.get(1), 6, 33);

		((Sapper) itemsArray.get(3)).reachItem(itemsArray.get(2));

		((Sapper) itemsArray.get(3)).go(30, 49);
		((Sapper) itemsArray.get(3)).reachItem(itemsArray.get(4));
		((Sapper) itemsArray.get(3)).reachItem(itemsArray.get(1));

		Thread.sleep(3000);

		((Bomb) itemsArray.get(0)).explode();

		Thread.sleep(8000);

		((Sapper) itemsArray.get(3)).disarmBomb(itemsArray.get(1));

		// ((Sapper) itemsArray.get(3)).go(7, 7, null);
		// ((Bomb) itemsArray.get(0)).explode(((Sapper)
		// itemsArray.get(2)));//wywoływanie metod dla poszczególnych obiektów z
		// lisy przy użyciu rzutowania (inteligentna funkcja wyszukująca obiekty
		// po określonym unikalnym dla obiektu ID jest jeszcze do napisania)

		// ((Sapper) itemsArray.get(2)).moveBomb(((Bomb) itemsArray.get(0)), 6,
		// 9);
		// ((Sapper) itemsArray.get(2)).moveBomb(((Bomb) itemsArray.get(1)), 6,
		// 9);

		// ((Bomb) itemsArray.get(0)).explode(((Sapper) itemsArray.get(2)));//
		// wywoływanie
		// metod
		// dla
		// poszczególnych
		// obiektów
		// z
		// lisy
		// przy
		// użyciu
		// rzutowania
		// (inteligentna
		// funkcja
		// wyszukująca
		// obiekty
		// po
		// określonym
		// unikalnym
		// dla
		// obiektu
		// ID
		// jest
		// jeszcze
		// do
		// napisania)

		// ((Sapper) itemsArray.get(3)).disarmBomb(((Bomb) itemsArray.get(0)));

		// ((Sapper) itemsArray.get(2)).disarmBomb(((Bomb) itemsArray.get(1)));
	}
}
