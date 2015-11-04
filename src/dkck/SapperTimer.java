package dkck;

import java.util.Timer;
import java.util.TimerTask;

import dkck.GUI.MainWindow;

public class SapperTimer extends TimerTask {
	

	private Item itemReference;

	private Timer timer1;

	/**
	 * @param itemReference
	 */
	
	/**
	 * @return the itemReference
	 */
	public Item getItemReference() {
		return itemReference;
	}

	/**
	 * @param itemReference the itemReference to set
	 */
	public void setItemReference(Item itemReference) {
		this.itemReference = itemReference;
	}

	/**
	 * @return the timer1
	 */
	public Timer getTimer1() {
		return timer1;
	}

	/**
	 * @param timer1 the timer1 to set
	 */
	public void setTimer1(Timer timer1) {
		this.timer1 = timer1;
	}
	
	public SapperTimer(Item itemReference, int miliseconds) {
		super();
		this.setItemReference(itemReference);
		this.setTimer1(new Timer());
		this.getTimer1().schedule(this, 0, miliseconds);
	}

	public void run() {

		if (this.getItemReference() instanceof Sapper) {

			boolean continueStep = true;

			Sapper tempSapperReference = ((Sapper) this.getItemReference());

			if (tempSapperReference.getTargetsArray().isEmpty() == false) {

				int initialSapperPositionX = this.getItemReference().getPositionX();
				int initialSapperPositionY = this.getItemReference().getPositionY();

				if (tempSapperReference.getTargetsArray().isEmpty() == false
						&& tempSapperReference.getTargetsArray().get(0).getPositionX() == this.getItemReference().getPositionX()
						&& tempSapperReference.getTargetsArray().get(0).getPositionY() == this.getItemReference()
								.getPositionY()) {

					MainWindow.updateLog("Sapper reached the target.");
					continueStep = false;

				}

				if (tempSapperReference.getTargetsArray().get(1) instanceof Bomb) {
					Item tempItemReference = ((Sapper) this.getItemReference()).getTargetsArray().get(1);
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

					if (tempSapperReference.getTargetsArray().get(0).getPositionX() > this.getItemReference().getPositionX()) {
						this.getItemReference().setPositionX(this.getItemReference().getPositionX() + 1);
					} else if (tempSapperReference.getTargetsArray().get(0).getPositionX() < itemReference
							.getPositionX()) {
						this.getItemReference().setPositionX(this.getItemReference().getPositionX() - 1);
					}
					if (tempSapperReference.getTargetsArray().get(0).getPositionY() > this.getItemReference().getPositionY()) {
						this.getItemReference().setPositionY(this.getItemReference().getPositionY() + 1);
					} else if (tempSapperReference.getTargetsArray().get(0).getPositionY() < this.getItemReference()
							.getPositionY()) {
						this.getItemReference().setPositionY(this.getItemReference().getPositionY() - 1);
					}

					MainWindow.updatePositionPanel("Sapper position is: [" + this.getItemReference().getPositionX() + "]["
							+ this.getItemReference().getPositionY() + "]");

					Item tempItemReference = ((Sapper) this.getItemReference()).getTargetsArray().get(1);

					if (tempSapperReference.getTargetsArray().get(1) instanceof Bomb) {
						tempItemReference = ((Sapper) this.getItemReference()).getTargetsArray().get(1);

						tempItemReference.setPositionX(this.getItemReference().getPositionX());
						tempItemReference.setPositionY(this.getItemReference().getPositionY());
						MainWindow.updateLog("Position of moving bomb is: [" + tempItemReference.getPositionX() + "]["
								+ tempItemReference.getPositionY() + "]");
						MainWindow.grid.drawBomb(tempItemReference.getPositionX(), tempItemReference.getPositionY());

					} else {
						MainWindow.updateLog("You are not moving antyhing");
					}

					MainWindow.grid.drawSapper(initialSapperPositionX, initialSapperPositionY,
							this.getItemReference().getPositionX(), this.getItemReference().getPositionY());

					for (int i = 0; i < MainWindow.itemsCollection.getItemsArray().size(); i++) {
						Item tempItem = MainWindow.itemsCollection.getItemsArray().get(i);
						int tempX = tempItem.getPositionX();
						int tempY = tempItem.getPositionY();
						if ((tempX == initialSapperPositionX) && (tempY == initialSapperPositionY)) {
							if (tempItem instanceof Bomb) {
								MainWindow.grid.drawBomb(tempX, tempY);

							} else if (tempItem instanceof Sapper) {
								MainWindow.grid.drawSapper(initialSapperPositionX, initialSapperPositionY, tempX,
										tempY);
							}
						}
					}
				} else
					run();

			}
		} else {
			MainWindow.updateLog("Wrong timer argument!!!");
		}
	}
}