package dkck;

import java.util.Timer;
import java.util.TimerTask;

import dkck.GUI.MainWindow;

public class TimeTask extends TimerTask {
	Item itemReference;

	private Timer timer1;

	/**
	 * @param itemReference
	 */
	public TimeTask(Item itemReference, int miliseconds) {
		super();
		this.itemReference = itemReference;
		timer1 = new Timer();
		timer1.schedule(this, 0, miliseconds);
	}

	public void run() {

		if (itemReference instanceof Bomb) {
			Bomb tempBombReference = ((Bomb) itemReference);
			if (tempBombReference.getExplosionLeftTime() > 0) {
				tempBombReference.setExplosionLeftTime(tempBombReference.getExplosionLeftTime() - 1);
				tempBombReference.getTimerLog().setText("Bomb nr: " + tempBombReference.getId() + " has: "
						+ tempBombReference.getExplosionLeftTime() + " seconds left to explosion");
			} else {
				tempBombReference.explode();
			}
		} else if (itemReference instanceof Sapper) {

			boolean continueStep = true;

			Sapper tempSapperReference = ((Sapper) itemReference);

			if (tempSapperReference.getTargetsArray().isEmpty() == false) {

				int initialSapperPositionX = itemReference.getPositionX();
				int initialSapperPositionY = itemReference.getPositionY();

				if (tempSapperReference.getTargetsArray().isEmpty() == false
						&& tempSapperReference.getTargetsArray().get(0).getPositionX() == itemReference.getPositionX()
						&& tempSapperReference.getTargetsArray().get(0).getPositionY() == itemReference
								.getPositionY()) {

					// run();
					// itemReference.setPositionX(prevPositionX);
					// itemReference.setPositionY(prevPositionY);
					continueStep = false;

				}

				if (tempSapperReference.getTargetsArray().get(1) instanceof Bomb) {
					Item tempItemReference = ((Sapper) itemReference).getTargetsArray().get(1);
					if (tempItemReference.getPositionX() != initialSapperPositionX
							&& tempItemReference.getPositionY() != initialSapperPositionY) {

						// run();
						// itemReference.setPositionX(prevPositionX);
						// itemReference.setPositionY(prevPositionY);
						MainWindow.updateLog("Sapper lost the bomb");
						continueStep = false;
					}
				}

				if (continueStep == false) {
					tempSapperReference.getTargetsArray().remove(1);
					tempSapperReference.getTargetsArray().remove(0);
				}

				if (continueStep) {

					if (tempSapperReference.getTargetsArray().get(0).getPositionX() > itemReference.getPositionX()) {
						itemReference.setPositionX(itemReference.getPositionX() + 1);
					} else if (tempSapperReference.getTargetsArray().get(0).getPositionX() < itemReference
							.getPositionX()) {
						itemReference.setPositionX(itemReference.getPositionX() - 1);
					}
					if (tempSapperReference.getTargetsArray().get(0).getPositionY() > itemReference.getPositionY()) {
						itemReference.setPositionY(itemReference.getPositionY() + 1);
					} else if (tempSapperReference.getTargetsArray().get(0).getPositionY() < itemReference
							.getPositionY()) {
						itemReference.setPositionY(itemReference.getPositionY() - 1);
					}

					MainWindow.updatePositionPanel("Sapper position is: [" + itemReference.getPositionX() + "]["
							+ itemReference.getPositionY() + "]");

					Item tempItemReference = ((Sapper) itemReference).getTargetsArray().get(1);

					if (tempSapperReference.getTargetsArray().get(1) instanceof Bomb) {
						tempItemReference = ((Sapper) itemReference).getTargetsArray().get(1);

						tempItemReference.setPositionX(itemReference.getPositionX());
						tempItemReference.setPositionY(itemReference.getPositionY());
						MainWindow.updateLog("Position of moving bomb is: [" + tempItemReference.getPositionX() + "]["
								+ tempItemReference.getPositionY() + "]");
						MainWindow.grid.drawBomb(tempItemReference.getPositionX(), tempItemReference.getPositionY());

					} else {
						MainWindow.updateLog("You are not moving antyhing");
					}

					MainWindow.grid.drawSapper(initialSapperPositionX, initialSapperPositionY, itemReference.getPositionX(),
							itemReference.getPositionY());

					for (int i = 0; i < MainWindow.itemsCollection.getItemsArray().size(); i++) {
						Item tempItem = MainWindow.itemsCollection.getItemsArray().get(i);
						int tempX = tempItem.getPositionX();
						int tempY = tempItem.getPositionY();
						if ((tempX == initialSapperPositionX) && (tempY == initialSapperPositionY)) {
							if (tempItem instanceof Bomb) {
								MainWindow.grid.drawBomb(tempX, tempY);

								itemReference.checkItemsRange(tempItem);
							} else if (tempItem instanceof Sapper) {
								MainWindow.grid.drawSapper(initialSapperPositionX, initialSapperPositionY, tempX, tempY);
							}
						}
						// }

						// System.out.println("test");
					}
				} else
					run();

			}
		} else {
			MainWindow.updateLog("Wrong timer argument!!!");
		}
	}
}