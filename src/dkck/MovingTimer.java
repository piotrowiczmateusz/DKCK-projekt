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

		if (this.getItemReference() instanceof Sapper || this.getItemReference() instanceof Rocket) {

			boolean continueStep = true;// important variable to state if item
										// is doing one step or not in one cycle
			Sapper tempSapperReference = null;

			if (this.getItemReference() instanceof Sapper) {

				tempSapperReference = ((Sapper) this.getItemReference());

				// checking sapper health points

				if (tempSapperReference.getHealthPoints() == 0) {

					MainWindow.updateLog("Sapper " + tempSapperReference.getId() + " is dead!");
					tempSapperReference.getTargetsArray().clear();
					this.cancel();
					continueStep = false;
				}
			}
			if (this.getItemReference().getTargetsArray().isEmpty() == false) {

				// checking if sapper is already dead (if is, rocket choose
				// another
				// sapperg to hurt)

				if (this.getItemReference() instanceof Rocket
						&& this.getItemReference().getTargetsArray().get(0) instanceof Sapper) {
					tempSapperReference = ((Sapper) this.getItemReference().getTargetsArray().get(0));
					if (tempSapperReference.getHealthPoints() == 0) {
						System.out.println("Rocket won't reach dead Sapper");
						continueStep = false;
					}
				}

				// checking position of item (mayby he reached the target)

				int initialItemPositionX = this.getItemReference().getPositionX();
				int initialItemPositionY = this.getItemReference().getPositionY();

				if (this.getItemReference().getTargetsArray().isEmpty() == false
						&& this.getItemReference().getTargetsArray().get(0).getPositionX() == this.getItemReference()
								.getPositionX()
						&& this.getItemReference().getTargetsArray().get(0).getPositionY() == this.getItemReference()
								.getPositionY()) {

					MainWindow.updateLog(this.getItemReference().nameOfItem(this.getItemReference()) + " nr: "
							+ this.getItemReference().getId() + " reached the target.");

					if (this.getItemReference() instanceof Rocket)
						((Rocket) this.getItemReference()).explode();

					continueStep = false;
				}

				// checking bomb loss

				if (this.getItemReference().getTargetsArray().get(1) instanceof Bomb
						&& this.getItemReference() instanceof Sapper) {
					Item tempItemReference = ((Sapper) this.getItemReference()).getTargetsArray().get(1);
					if (tempItemReference.getPositionX() != initialItemPositionX
							&& tempItemReference.getPositionY() != initialItemPositionY) {

						// run();
						// itemReference.setPositionX(prevPositionX);
						// itemReference.setPositionY(prevPositionY);
						MainWindow.updateLog("Sapper nr: " + this.getItemReference().getId() + "lost the bomb");
						continueStep = false;
					}
				}

				// removing things form list if something was wrong

				if (continueStep == false) {
					this.getItemReference().getTargetsArray().remove(1);
					this.getItemReference().getTargetsArray().remove(0);
				}

				if (continueStep == true) {

					Item tempItemReference = this.getItemReference().getTargetsArray().get(0);

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

					MainWindow.updatePositionPanel(this.getItemReference().nameOfItem(this.getItemReference()) + " nr: "
							+ this.getItemReference().getId() + " pozycja: ["
							+ (this.getItemReference().getPositionX() + 1) + "]["
							+ (this.getItemReference().getPositionY() + 1) + "]");

					// moving bomb

					if (this.getItemReference().getTargetsArray().get(1) instanceof Bomb
							&& this.getItemReference() instanceof Sapper) {
						tempItemReference = ((Sapper) this.getItemReference()).getTargetsArray().get(1);

						tempItemReference.setPositionX(this.getItemReference().getPositionX());
						tempItemReference.setPositionY(this.getItemReference().getPositionY());
						MainWindow.updateLog("Position of moving bomb is: [" + tempItemReference.getPositionX() + "]["
								+ tempItemReference.getPositionY() + "]");
						
						MainWindow.grid.drawCircle(initialItemPositionX, initialItemPositionY,
								tempItemReference.getRange(), null);
						MainWindow.grid.drawCircle(tempItemReference.getPositionX(), tempItemReference.getPositionY(),
								tempItemReference.getRange(), MainWindow.bombColor);
						MainWindow.grid.drawSquare(initialItemPositionX, initialItemPositionY,
								tempItemReference.getPositionX(), tempItemReference.getPositionY(),
								MainWindow.bombColor);

					} else {
						MainWindow.updateLog("Sapper is not moving antyhing");
					}

					// drawing moving object

					if (this.getItemReference() instanceof Sapper) {
						MainWindow.grid.drawCircle(initialItemPositionX, initialItemPositionY,
								this.getItemReference().getRange(), null);
						MainWindow.grid.drawCircle(this.getItemReference().getPositionX(),
								this.getItemReference().getPositionY(), this.getItemReference().getRange(),
								MainWindow.sapperColor);
						MainWindow.grid.drawSquare(initialItemPositionX, initialItemPositionY,
								this.getItemReference().getPositionX(), this.getItemReference().getPositionY(),
								MainWindow.sapperColor);
					} else if (this.getItemReference() instanceof Rocket) {
						MainWindow.grid.drawCircle(initialItemPositionX, initialItemPositionY,
								this.getItemReference().getRange(), null);
						MainWindow.grid.drawCircle(this.getItemReference().getPositionX(),
								this.getItemReference().getPositionY(), this.getItemReference().getRange(),
								MainWindow.rocketColor);
						MainWindow.grid.drawSquare(initialItemPositionX, initialItemPositionY,
								this.getItemReference().getPositionX(), this.getItemReference().getPositionY(),
								MainWindow.rocketColor);
					}

					
					MainWindow.grid.repairCircle(this.getItemReference().getPositionX(),
							this.getItemReference().getPositionY(), this.getItemReference().getRange());
					MainWindow.grid.repairSquare(initialItemPositionX, initialItemPositionY);
					MainWindow.grid.repairCircle2();
					MainWindow.grid.repairSquare2();
				} else
					run();// recursive call
			}

		} else {
			System.out.println("Wrong timer argument!!!");
			this.cancel();
		}
	}
}