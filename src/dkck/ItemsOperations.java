package dkck;

import java.util.ArrayList;
import java.util.List;

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
		
<<<<<<< HEAD
		itemsArray.add((Item) new Bomb(2, 2, 2, 10));// dodawanie do listy
=======
		bombsArray.add(new Bomb(2, 2, 2, 10));
		bombsArray.add(new Bomb(8, 16, 5, 30));
		bombsArray.add(new Bomb(18, 10, 5, 30));
		sappersArray.add(new Sapper(5, 5, 2));
		
		sappersArray.get(0).go(10, 14);
		sappersArray.get(0).moveBomb(bombsArray.get(0), 14, 15);
		sappersArray.get(0).go(10, 16);
		sappersArray.get(0).moveBomb(bombsArray.get(1), 13, 6);
		sappersArray.get(0).go(5, 3);
		
		/*itemsArray.add((Item) new Bomb(2, 2, 2, 10));// dodawanie do listy
>>>>>>> 8dc121a9ea79da1b18fa5147dda78d990b947efd
															// elementów
															// dziedzicz¹cych z
															// klasy Item z
															// okreœlonymi
															// parametrami
		itemsArray.add((Item) new Bomb(8, 16, 5, 30));
		itemsArray.add((Item) new Bomb(18, 10, 5, 30));
		itemsArray.add((Item) new Sapper(5, 5, 2));
		//((Sapper) itemsArray.get(3)).go(0, 0, null);
		((Sapper) itemsArray.get(3)).moveBomb(((Bomb) itemsArray.get(1)), 4, 4);
		((Sapper) itemsArray.get(3)).go(7, 7, null);
		//((Bomb) itemsArray.get(0)).explode(((Sapper) itemsArray.get(2)));//wywo³ywanie metod dla poszczególnych obiektów z lisy przy u¿yciu rzutowania (inteligentna funkcja wyszukuj¹ca obiekty po okreœlonym unikalnym dla obiektu ID jest jeszcze do napisania)


		//((Sapper) itemsArray.get(2)).moveBomb(((Bomb) itemsArray.get(0)), 6, 9);
		//((Sapper) itemsArray.get(2)).moveBomb(((Bomb) itemsArray.get(1)), 6, 9);

		//((Bomb) itemsArray.get(0)).explode(((Sapper) itemsArray.get(2)));// wywo³ywanie
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


		//((Sapper) itemsArray.get(3)).disarmBomb(((Bomb) itemsArray.get(0)));

		//((Sapper) itemsArray.get(2)).disarmBomb(((Bomb) itemsArray.get(1)));
	}
}
