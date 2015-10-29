package dkck;

import java.util.ArrayList;
import java.util.List;

public class ItemsOperations {

	public static int id = 0;
	
	public static int bombsId = 0;
	
	public static int sappersId = 0;
	
	private List<Item> itemsArray;
	private List<Bomb> bombsArray;
	private List<Sapper> sappersArray;
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
		this.bombsArray = new ArrayList<Bomb>();
		this.sappersArray = new ArrayList<Sapper>();
	}

	public void actions() throws InterruptedException {
		
		bombsArray.add(new Bomb(2, 2, 2, 10));
		bombsArray.add(new Bomb(8, 16, 5, 30));
		bombsArray.add(new Bomb(18, 10, 5, 30));
		sappersArray.add(new Sapper(5, 5, 2));
		
		sappersArray.get(0).go(18, 18);
		/*itemsArray.add((Item) new Bomb(2, 2, 2, 10));// dodawanie do listy
															// elementów
															// dziedzicz¹cych z
															// klasy Item z
															// okreœlonymi
															// parametrami
		itemsArray.add((Item) new Bomb(8, 16, 5, 30));
		itemsArray.add((Item) new Bomb(18, 10, 5, 30));
		itemsArray.add((Item) new Sapper(5, 5, 2));
		((Sapper) itemsArray.get(3)).go(6, 10);
		((Sapper) itemsArray.get(3)).moveBomb(((Bomb) itemsArray.get(2)), 2, 2);*/
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
