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
				tempBombReference.explode(null);
			}
		} else if (itemReference instanceof Sapper) {

			Sapper tempSapperReference = ((Sapper) itemReference);

			if (tempSapperReference.getTargetsArray().isEmpty() == false) {

				if ((tempSapperReference.getTargetsArray().get(0).getPositionX() == itemReference.getPositionX())
						&& (tempSapperReference.getTargetsArray().get(0).getPositionY() == itemReference
								.getPositionY())) {
					tempSapperReference.getTargetsArray().remove(0);
					tempSapperReference.getTargetsArray().remove(0);
				}

				if (tempSapperReference.getTargetsArray().isEmpty() == false) {

					int prevPositionX = itemReference.getPositionX();
					int prevPositionY = itemReference.getPositionY();

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

					if (tempSapperReference.getTargetsArray().get(1) instanceof Bomb) {
						Item tempItemReference = ((Sapper) itemReference).getTargetsArray().get(1);
						tempItemReference.setPositionX(itemReference.getPositionX());
						tempItemReference.setPositionY(itemReference.getPositionY());
						MainWindow.updateLog("Position of moving bomb is: [" + tempItemReference.getPositionX() + "]["
								+ tempItemReference.getPositionY() + "]");
						MainWindow.grid.drawBomb(tempItemReference.getPositionX(), tempItemReference.getPositionY());
					} else {
						MainWindow.updateLog("You are not moving antyhing");
					}

					MainWindow.grid.drawSapper(prevPositionX, prevPositionY, itemReference.getPositionX(),
							itemReference.getPositionY());

					for (int i = 0; i < MainWindow.itemsCollection.getItemsArray().size(); i++) {
						Item tempItem = MainWindow.itemsCollection.getItemsArray().get(i);
						int tempX = tempItem.getPositionX();
						int tempY = tempItem.getPositionY();
						if ((tempX == prevPositionX) && (tempY == prevPositionY)) {
							if (tempItem instanceof Bomb) {
								MainWindow.grid.drawBomb(tempX, tempY);

								itemReference.checkItemsRange(tempItem);
							} else if (tempItem instanceof Sapper) {
								MainWindow.grid.drawSapper(prevPositionX, prevPositionY, tempX, tempY);
							}
						}
					}
					// }

					// System.out.println("test");
				}
			}
		} else {
			MainWindow.updateLog("Wrong timer argument!!!");
		}
	}
}