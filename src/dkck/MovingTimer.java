package dkck;

import java.util.Timer;
import java.util.TimerTask;

import dkck.GUI.MainWindow;

public class MovingTimer extends TimerTask {

	private Item itemReference;

	private Timer timer1;

	public Item getItemReference() {
		return itemReference;
	}

	public void setItemReference(Item itemReference) {
		this.itemReference = itemReference;
	}

	public Timer getTimer1() {
		return timer1;
	}

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
			Sapper tempSapper = null;

			if (this.getItemReference() instanceof Sapper) {

				tempSapper = ((Sapper) this.getItemReference());

				// checking sapper health points

				if (tempSapper.getHealthPoints() == 0) {

					MainWindow.updateLog("Sapper " + tempSapper.getId() + " is dead!");
					tempSapper.getTargetsArray().clear();
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
					tempSapper = ((Sapper) this.getItemReference().getTargetsArray().get(0));
					if (tempSapper.getHealthPoints() == 0) {
						continueStep = false;
					}
				}

				// checking position of item (mayby he reached the target)

				int initItemPositionX = this.getItemReference().getPositionX();
				int initItemPositionY = this.getItemReference().getPositionY();

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
					Item tempItem = ((Sapper) this.getItemReference()).getTargetsArray().get(1);
					if (tempItem.getPositionX() != initItemPositionX
							&& tempItem.getPositionY() != initItemPositionY) {

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

					Item tempItem = this.getItemReference().getTargetsArray().get(0);

					// one step to target

					if (tempItem.getPositionX() > this.getItemReference().getPositionX()) {
						this.getItemReference().setPositionX(this.getItemReference().getPositionX() + 1);
					} else if (tempItem.getPositionX() < this.getItemReference().getPositionX()) {
						this.getItemReference().setPositionX(this.getItemReference().getPositionX() - 1);
					}
					if (tempItem.getPositionY() > this.getItemReference().getPositionY()) {
						this.getItemReference().setPositionY(this.getItemReference().getPositionY() + 1);
					} else if (tempItem.getPositionY() < this.getItemReference().getPositionY()) {
						this.getItemReference().setPositionY(this.getItemReference().getPositionY() - 1);
					}

					MainWindow.updatePositionPanel(this.getItemReference().nameOfItem(this.getItemReference()) + " nr: "
							+ this.getItemReference().getId() + " pozycja: ["
							+ (this.getItemReference().getPositionX() + 1) + "]["
							+ (this.getItemReference().getPositionY() + 1) + "]");

					// moving bomb

					if (this.getItemReference().getTargetsArray().get(1) instanceof Bomb
							&& this.getItemReference() instanceof Sapper) {
						tempItem = ((Sapper) this.getItemReference()).getTargetsArray().get(1);

						tempItem.setPositionX(this.getItemReference().getPositionX());
						tempItem.setPositionY(this.getItemReference().getPositionY());
						
						
						MainWindow.grid.drawCircle(initItemPositionX, initItemPositionY,
								tempItem.getRange(), null);
						MainWindow.grid.drawCircle(tempItem.getPositionX(), tempItem.getPositionY(),
								tempItem.getRange(), MainWindow.bombColor);
						MainWindow.grid.drawSquare(initItemPositionX, initItemPositionY,
								tempItem.getPositionX(), tempItem.getPositionY(),
								MainWindow.bombColor);

					} 

					// drawing moving object

					if (this.getItemReference() instanceof Sapper) {
						MainWindow.grid.drawCircle(initItemPositionX, initItemPositionY,
								this.getItemReference().getRange(), null);
						MainWindow.grid.drawCircle(this.getItemReference().getPositionX(),
								this.getItemReference().getPositionY(), this.getItemReference().getRange(),
								MainWindow.sapperColor);
						MainWindow.grid.drawSquare(initItemPositionX, initItemPositionY,
								this.getItemReference().getPositionX(), this.getItemReference().getPositionY(),
								MainWindow.sapperColor);
					} else if (this.getItemReference() instanceof Rocket) {
						MainWindow.grid.drawCircle(initItemPositionX, initItemPositionY,
								this.getItemReference().getRange(), null);
						MainWindow.grid.drawCircle(this.getItemReference().getPositionX(),
								this.getItemReference().getPositionY(), this.getItemReference().getRange(),
								MainWindow.rocketColor);
						MainWindow.grid.drawSquare(initItemPositionX, initItemPositionY,
								this.getItemReference().getPositionX(), this.getItemReference().getPositionY(),
								MainWindow.rocketColor);
					}

					
					MainWindow.grid.repairCircle(this.getItemReference().getPositionX(),
							this.getItemReference().getPositionY(), this.getItemReference().getRange());
					MainWindow.grid.repairSquare(initItemPositionX, initItemPositionY);
					MainWindow.grid.repairCircle2();
					MainWindow.grid.repairSquare2();
				} else
					run();// recursive call
			}

		} else {
			this.cancel();
		}
	}
}