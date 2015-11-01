package dkck;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import dkck.GUI.MainWindow;

public class ItemsOperations {

	public static int id = 0;

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

		itemsArray.add((Item) new Bomb(2, 2, 2, 10));// dodawanie do listy

		// elementów
		// dziedzicz¹cych z
		// klasy Item z
		// okreœlonymi
		// parametrami
		itemsArray.add((Item) new Bomb(16, 8, 5, 30));
		itemsArray.add((Item) new Bomb(7, 20, 5, 30));
		itemsArray.add((Item) new Sapper(2, 2, 2));
		itemsArray.add((Item) new Sapper(8, 9, 2));

		for (int i = 0; i < itemsArray.size(); i++) {
			Item tempItem = itemsArray.get(i);
			if (tempItem instanceof Bomb) {
				MainWindow.timerPanel.add(((Bomb) tempItem).getTimerLog(), BorderLayout.WEST);
			}
		}

		// ((Sapper) itemsArray.get(3)).go(0, 0, null);

		((Sapper) itemsArray.get(3)).moveBomb(((Bomb) itemsArray.get(2)), 2, 3);

		((Bomb) itemsArray.get(0)).explode(itemsArray.get(3));

		((Sapper) itemsArray.get(4)).moveBomb(((Bomb) itemsArray.get(1)), 2, 3);
		((Sapper) itemsArray.get(4)).go(3, 8, null);

		((Sapper) itemsArray.get(3)).go(3, 9, null);

		((Sapper) itemsArray.get(3)).moveBomb(((Bomb) itemsArray.get(1)), 6, 35);

		((Sapper) itemsArray.get(3)).go(49, 49, null);

		// ((Sapper) itemsArray.get(3)).go(7, 7, null);
		// ((Bomb) itemsArray.get(0)).explode(((Sapper)
		// itemsArray.get(2)));//wywo³ywanie metod dla poszczególnych obiektów z
		// lisy przy u¿yciu rzutowania (inteligentna funkcja wyszukuj¹ca obiekty
		// po okreœlonym unikalnym dla obiektu ID jest jeszcze do napisania)

		// ((Sapper) itemsArray.get(2)).moveBomb(((Bomb) itemsArray.get(0)), 6,
		// 9);
		// ((Sapper) itemsArray.get(2)).moveBomb(((Bomb) itemsArray.get(1)), 6,
		// 9);

		// ((Bomb) itemsArray.get(0)).explode(((Sapper) itemsArray.get(2)));//
		// wywo³ywanie
		// metod
		// dla
		// poszczególnych
		// obiektów
		// z
		// lisy
		// przy
		// u¿yciu
		// rzutowania
		// (inteligentna
		// funkcja
		// wyszukuj¹ca
		// obiekty
		// po
		// okreœlonym
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
