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

	private void dropItem(int index) {
		Item tempItemReference = this.getItemsArray().get(index);
		this.getItemsArray().remove(index);
		MainWindow.grid.drawSquare(tempItemReference.getPositionX(), tempItemReference.getPositionY(),
				tempItemReference.getPositionX(), tempItemReference.getPositionY(), MainWindow.railColor);
		MainWindow.grid.repairSquare(tempItemReference.getPositionX(), tempItemReference.getPositionY());
	}

	protected void createBombs(int numberOfBombs) {

		Point pointReference = new Point(0, 0);

		int beginningArrayIndex = this.getItemsArray().size() - 1;

		for (int currentBombIndex = 0; currentBombIndex < numberOfBombs; currentBombIndex++) {
			boolean goodBombPosition = true;
			do {
				this.getItemsArray().add(new Bomb());

				boolean continueSearching = true;

				int maxCounter = 0;

				for (int tempX = pointReference.getPositionX(); tempX < MainWindow.gridRows; tempX++) {
					for (int tempY = pointReference.getPositionY(); tempY < MainWindow.gridColumns; tempY++) {
						if (continueSearching) {
							int counter = 0;
							for (int arrayIndex = beginningArrayIndex + 1; arrayIndex < currentBombIndex
									+ beginningArrayIndex; arrayIndex++) {
								if (pointReference.checkItemsCenterDistance(this.getItemsArray().get(arrayIndex))) {
									counter++;
								}
							}

							if (maxCounter < counter)
								maxCounter = counter;

							if (maxCounter == currentBombIndex + 1) {
								continueSearching = false;
								pointReference.setPositionX(tempX);
								pointReference.setPositionY(tempY);
							}

						}

					}
				}

				if (continueSearching) {
					this.dropItem(currentBombIndex + beginningArrayIndex);
				} else {
					goodBombPosition = true;
				}
				// System.out.println("jakiœ napis");
			} while (goodBombPosition == false);
		}
	}

	public void actions() throws InterruptedException {
		itemsArray.add((Item) new Bomb());// (2, 2, 40, 10));// dodawanie do
											// listy

		// elementów
		// dziedzicz¹cych z
		// klasy Item z
		// okreœlonymi
		// parametrami
		itemsArray.add(new Bomb());// 16, 8, 5, 30));
		itemsArray.add(new Bomb());// 7, 20, 20, 30));
		itemsArray.add(new Sapper());// 2, 7, 2, 40));
		itemsArray.add(new Sapper());// 8, 9, 2, 70));

		 for (int i = 0; i < 2; ++i)
		 {
			 itemsArray.add( new Bomb());// 8, 9, 2, 70));
			 itemsArray.add(new Rocket(itemsArray.get(3)));
				itemsArray.add(new Rocket(itemsArray.get(4)));
		 }



		//createBombs(3);

		for (int i = 0; i < itemsArray.size(); i++) {
			Item tempItem = itemsArray.get(i);
			if (tempItem instanceof Bomb) {
				MainWindow.timerPanel.add(((Bomb) tempItem).getTimerLog(), BorderLayout.WEST);
			}
		}

		// ((Sapper) itemsArray.get(3)).go(0, 0, null);

		((Sapper) itemsArray.get(3)).moveBomb(itemsArray.get(2), 2, 3);

		((Sapper)itemsArray.get(4)).moveBomb(itemsArray.get(1), 45, 49);
		((Sapper)itemsArray.get(4)).moveBomb(itemsArray.get(1), 1, 1);
		itemsArray.get(4).reachItem(itemsArray.get(1));
		itemsArray.get(4).reachItem(itemsArray.get(3));

		itemsArray.get(3).go(3, 7);

		((Sapper)itemsArray.get(4)).moveBomb(itemsArray.get(1), 6, 33);

		itemsArray.get(3).reachItem(itemsArray.get(2));

		itemsArray.get(3).go(30, 49);
		itemsArray.get(3).reachItem(itemsArray.get(4));
		itemsArray.get(3).reachItem(itemsArray.get(1));
		// itemsArray.get(5).reachItem(itemsArray.get(3));

		Thread.sleep(3000);

		((Bomb) itemsArray.get(0)).explode();

		 dropItem(6);

		Thread.sleep(8000);

		((Sapper) itemsArray.get(3)).disarmBomb(itemsArray.get(1));

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
