package dkck;

import java.util.Timer;
import java.util.TimerTask;

import dkck.GUI.MainWindow;

public class MovingTimer extends TimerTask {

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
	 * @param itemReference
	 *            the itemReference to set
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
	 * @param timer1
	 *            the timer1 to set
	 */
	public void setTimer1(Timer timer1) {
		this.timer1 = timer1;
	}

	public MovingTimer(Item itemReference, int miliseconds) {
		super();
		this.setItemReference(itemReference);
		this.setTimer1(new Timer());
		this.getTimer1().schedule(this, 0, miliseconds);
	}

	public void run() {

		if (this.getItemReference() instanceof Sapper) {

			boolean continueStep = true;// important variable to state if sapper
										// is doing one step or not in one cycle

			Sapper tempSapperReference = ((Sapper) this.getItemReference());

			// checking health points

			if (tempSapperReference.getHealthPoints() == 0) {

				MainWindow.updateLog("Sapper " + tempSapperReference.getId() + " is dead!");
				tempSapperReference.getTargetsArray().clear();
				this.cancel();
				continueStep = false;
			}

			// checking position of sapper (mayby he reached the target)

			if (tempSapperReference.getTargetsArray().isEmpty() == false) {

				int initialSapperPositionX = this.getItemReference().getPositionX();
				int initialSapperPositionY = this.getItemReference().getPositionY();

				if (tempSapperReference.getTargetsArray().isEmpty() == false
						&& tempSapperReference.getTargetsArray().get(0).getPositionX() == this.getItemReference()
								.getPositionX()
						&& tempSapperReference.getTargetsArray().get(0).getPositionY() == this.getItemReference()
								.getPositionY()) {

					MainWindow.updateLog("Sapper nr: " + tempSapperReference.getId() + " reached the target.");
					continueStep = false;
				}

				// checking bomb loss

				if (tempSapperReference.getTargetsArray().get(1) instanceof Bomb) {
					Item tempItemReference = ((Sapper) this.getItemReference()).getTargetsArray().get(1);
					if (tempItemReference.getPositionX() != initialSapperPositionX
							&& tempItemReference.getPositionY() != initialSapperPositionY) {

						// run();
						// itemReference.setPositionX(prevPositionX);
						// itemReference.setPositionY(prevPositionY);
						MainWindow.updateLog("Sapper nr: " + tempSapperReference.getId() + "lost the bomb");
						continueStep = false;
					}
				}

				// removing things form list if something was wrong

				if (continueStep == false) {
					tempSapperReference.getTargetsArray().remove(1);
					tempSapperReference.getTargetsArray().remove(0);
				}

				if (continueStep == true) {

					Item tempItemReference = tempSapperReference.getTargetsArray().get(0);

					// one step to target

					if (tempItemReference.getPositionX() > this.getItemReference().getPositionX()) {
						this.getItemReference().setPositionX(this.getItemReference().getPositionX() + 1);
					} else if (tempItemReference.getPositionX() < this.getItemReference().getPositionX()) {
						this.getItemReference().setPositionX(this.getItemReference().getPositionX() - 1);
					}
					if (tempItemReference.getPositionY() > this.getItemReference().getPositionY()) {
						this.getItemReference().setPositionY(this.getItemReference().getPositionY() + 1);
					} else if (tempItemReference.getPositionY() < this.getItemReference().getPositionY()) {
						this.getItemReference().setPositionY(this.getItemReference().getPositionY() - 1);
					}

					MainWindow.updatePositionPanel("Sapper nr: " + tempSapperReference.getId() + " pozycja: ["
							+ (this.getItemReference().getPositionX()+1) + "][" + (this.getItemReference().getPositionY()+1)
							+ "]");

					// moving bomb

					if (tempSapperReference.getTargetsArray().get(1) instanceof Bomb) {
						tempItemReference = ((Sapper) this.getItemReference()).getTargetsArray().get(1);

						tempItemReference.setPositionX(this.getItemReference().getPositionX());
						tempItemReference.setPositionY(this.getItemReference().getPositionY());
						MainWindow.updateLog("Position of moving bomb is: [" + tempItemReference.getPositionX() + "]["
								+ tempItemReference.getPositionY() + "]");
						MainWindow.grid.drawSquare(initialSapperPositionX, initialSapperPositionY,
								tempItemReference.getPositionX(), tempItemReference.getPositionY(),
								MainWindow.bombColor);

					} else {
						MainWindow.updateLog("You are not moving antyhing");
					}

					// drawing everything

					MainWindow.grid.drawSquare(initialSapperPositionX, initialSapperPositionY,
							this.getItemReference().getPositionX(), this.getItemReference().getPositionY(),
							MainWindow.sapperColor);

					for (int i = 0; i < MainWindow.itemsCollection.getItemsArray().size(); i++) {
						Item tempItem = MainWindow.itemsCollection.getItemsArray().get(i);
						int tempX = tempItem.getPositionX();
						int tempY = tempItem.getPositionY();
						if ((tempX == initialSapperPositionX) && (tempY == initialSapperPositionY)) {
							if (tempItem instanceof Bomb) {
								MainWindow.grid.drawSquare(tempX, tempY, tempX, tempY, MainWindow.bombColor);

							} else if (tempItem instanceof Sapper) {
								MainWindow.grid.drawSquare(initialSapperPositionX, initialSapperPositionY, tempX, tempY,
										MainWindow.sapperColor);
							}
						}
					}
				} else
					run();// recursive call
			}
		} else {
			System.out.println("Wrong timer argument!!!");
			this.cancel();
		}
	}
}