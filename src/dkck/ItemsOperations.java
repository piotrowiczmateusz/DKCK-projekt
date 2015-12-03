package dkck;

import java.util.ArrayList;
import java.util.List;

import dkck.GUI.Grid;
import dkck.GUI.MainWindow;

public class ItemsOperations {

	private List<Item> itemsArray;

	/**
	 * SETTERS AND GETTERS
	 */

	public List<Item> getItemsArray() {
		return itemsArray;
	}

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

	static void dropItem(Item itemReference) {

		if (itemReference.getMovingTimer() != null) {
			((Bomb) itemReference).getMovingTimer().cancel();
			((Bomb) itemReference).getMovingTimer().setItemReference(null);
			((Bomb) itemReference).setMovingTimer(null);
		}

		if (itemReference instanceof Bomb && ((Bomb) itemReference).getBombTimer() != null) {
			((Bomb) itemReference).getBombTimer().cancel();
			((Bomb) itemReference).getBombTimer().setItemReference(null);
			((Bomb) itemReference).setBombTimer(null);
		}

		if (itemReference instanceof Bomb && !(itemReference instanceof Rocket)) {
			Bomb bombReference = (Bomb) itemReference;
			MainWindow.timerPanel.remove(((Bomb) bombReference).getTimerLog());
			bombReference.setTimerLog(null);
		}

		MainWindow.itemsCollection.getItemsArray().remove(itemReference);

		MainWindow.grid.drawCircle(itemReference.getPositionX(), itemReference.getPositionY(), itemReference.getRange(),
				null);
		MainWindow.grid.drawSquare(itemReference.getPositionX(), itemReference.getPositionY(),
				itemReference.getPositionX(), itemReference.getPositionY(), null);

		Grid.repairCircles();
		Grid.repairSquares();
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
					dropItem(this.getItemsArray().get(this.getItemsArray().size() - 1));
				} else {
					goodBombPosition = true;
				}
			} while (goodBombPosition == false);
		}
	}

	// nowa metoda służąca do dodawania itemów
	public void addItems() throws InterruptedException {

		itemsArray.add(new Tree());

		itemsArray.add(new Sapper()); // WAŻNE proponuje, żeby został 1 saper,
										// zawsze dodawnay jako pierwszy, ułatwi
										// to pracę

		itemsArray.add(new Sapper());
		itemsArray.add(new Sapper());

		itemsArray.add(new Tree());

		itemsArray.add(new Sapper());

		// itemsArray.add(new Sapper());
		itemsArray.add(new Bomb());
		itemsArray.add(new Bomb());
		itemsArray.add(new Bomb());

		dropItem(itemsArray.get(itemsArray.size() - 1));

		for (int i = 0; i < 2; ++i) {
			itemsArray.add(new Bomb());
			//itemsArray.add(new Rocket(MainWindow.findElementByID(0, Sapper.class)));
		}

		for (int j = 0; j < 5; ++j) {
			//itemsArray.add(new Rocket(MainWindow.findElementByID(0, Sapper.class)));		
		}
	}

	public void actions() throws InterruptedException {

		/*
		 * testowanie metod, w końcowej wersji wszystko musi być wykonywane
		 * przez interpreter
		 * 
		 * itemsArray.get(3).go(3, 7); ((Sapper)
		 * itemsArray.get(3)).disarmBomb(itemsArray.get(0)); ((Sapper)
		 * itemsArray.get(3)).moveBomb(itemsArray.get(2), 2, 3);
		 * itemsArray.get(3).go(3, 7);
		 * itemsArray.get(3).reachItem(itemsArray.get(1));
		 * itemsArray.get(3).go(30, 49);
		 * itemsArray.get(3).reachItem(itemsArray.get(1)); ((Bomb)
		 * itemsArray.get(0)).explode();
		 * 
		 */
	}
}