package dkck;

import java.util.Timer;
import java.util.TimerTask;
import dkck.ItemsOperations;

import dkck.GUI.Grid;
import dkck.GUI.MainWindow;

public class MovingTimer extends TimerTask {

	private Item itemReference;

	private Timer timer1;

	public Item getItemRef() {
		return itemReference;
	}

	public void setItemRef(Item itemReference) {
		this.itemReference = itemReference;
	}

	public Timer getTimer1() {
		return timer1;
	}

	public void setTimer1(Timer timer1) {
		this.timer1 = timer1;
	}

	public MovingTimer(Item itemReference) {
		super();
		this.setItemRef(itemReference);
		this.setTimer1(new Timer());
	}

	public void run() {

		if (this.getItemRef() instanceof Sapper || this.getItemRef() instanceof Rocket) {

			boolean continueStep = true;// important variable to state if item
										// is doing one step or not in one cycle
			Sapper tempSapper = null;

			if (this.getItemRef() instanceof Sapper) {

				tempSapper = ((Sapper) this.getItemRef());

				// checking sapper health points

				if (tempSapper.getHealthPoints() == 0) {

					MainWindow.updateLog("Saper nie ¿yje");
					tempSapper.getTargetsArray().clear();
					this.cancel();
					continueStep = false;
				}
			}
			if (this.getItemRef().getTargetsArray().isEmpty() == false) {

				// checking if sapper is already dead (if is, rocket choose
				// another
				// sapperg to hurt)

				if (this.getItemRef() instanceof Rocket
						&& this.getItemRef().getTargetsArray().get(0) instanceof Sapper) {
					tempSapper = ((Sapper) this.getItemRef().getTargetsArray().get(0));
					if (tempSapper.getHealthPoints() == 0) {
						continueStep = false;
					}
				}

				// checking position of item (mayby he reached the target)

				int initItemPositionX = this.getItemRef().getPositionX();
				int initItemPositionY = this.getItemRef().getPositionY();

				if (this.getItemRef().getTargetsArray().isEmpty() == false
						&& this.getItemRef().getTargetsArray().get(0).getPositionX() == this.getItemRef()
								.getPositionX()
						&& this.getItemRef().getTargetsArray().get(0).getPositionY() == this.getItemRef()
								.getPositionY()) {
					
					MainWindow.updateLog(this.getItemRef().nameOfItem(this.getItemRef()) + " dotar³ do celu");
					if (this.getItemRef() instanceof Rocket) {
						((Rocket) this.getItemRef()).explode();

					}

					continueStep = false;
				}

				// checking bomb loss

				if (this.getItemRef().getTargetsArray().get(1) instanceof Bomb
						&& this.getItemRef() instanceof Sapper) {
					Item tempItem = ((Sapper) this.getItemRef()).getTargetsArray().get(1);
					if (tempItem.getPositionX() != initItemPositionX && tempItem.getPositionY() != initItemPositionY) {

						MainWindow.updateLog(this.getItemRef().nameOfItem(this.getItemRef())+ "zgubi³ bombê");
						continueStep = false;
					}
				}

				// removing things form list if something was wrong

				if (continueStep == false) {
					this.getItemRef().getTargetsArray().remove(1);
					this.getItemRef().getTargetsArray().remove(0);
				}

				if (continueStep == true) {

					Item tempItem = this.getItemRef().getTargetsArray().get(0);

					// one step to target

					if (tempItem.getPositionX() > this.getItemRef().getPositionX()) {
						this.getItemRef().setPositionX(this.getItemRef().getPositionX() + 1);
					} else if (tempItem.getPositionX() < this.getItemRef().getPositionX()) {
						this.getItemRef().setPositionX(this.getItemRef().getPositionX() - 1);
					}
					if (tempItem.getPositionY() > this.getItemRef().getPositionY()) {
						this.getItemRef().setPositionY(this.getItemRef().getPositionY() + 1);
					} else if (tempItem.getPositionY() < this.getItemRef().getPositionY()) {
						this.getItemRef().setPositionY(this.getItemRef().getPositionY() - 1);
					}

					if( this.getItemRef() instanceof Sapper) {				
						((Sapper) this.getItemRef()).getPositionLog().setText("Pozycja sapera " + this.getItemRef().getId() + ": [" + (this.getItemRef().getPositionX() + 1)
								+ "][" + (this.getItemRef().getPositionY() + 1) + "]");
					}

					// moving bomb

					if (this.getItemRef().getTargetsArray().get(1) instanceof Bomb
							&& this.getItemRef() instanceof Sapper) {
						tempItem = ((Sapper) this.getItemRef()).getTargetsArray().get(1);

						tempItem.setPositionX(this.getItemRef().getPositionX());
						tempItem.setPositionY(this.getItemRef().getPositionY());

						MainWindow.grid.drawCircle(initItemPositionX, initItemPositionY, tempItem.getRange(), null);
						MainWindow.grid.drawCircle(tempItem.getPositionX(), tempItem.getPositionY(),
								tempItem.getRange(), tempItem);
						MainWindow.grid.drawSquare(initItemPositionX, initItemPositionY, tempItem.getPositionX(),
								tempItem.getPositionY(), tempItem);
					}

					// drawing moving object

					MainWindow.grid.drawCircle(initItemPositionX, initItemPositionY, this.getItemRef().getRange(),
							null);
					MainWindow.grid.drawCircle(this.getItemRef().getPositionX(),
							this.getItemRef().getPositionY(), this.getItemRef().getRange(), tempItem);
					MainWindow.grid.drawSquare(initItemPositionX, initItemPositionY,
							this.getItemRef().getPositionX(), this.getItemRef().getPositionY(), tempItem);

					Grid.repairCircles();
					Grid.repairSquares();
				} else
					run(); // recursive call
			} else if (this.getItemRef() instanceof Rocket) {
				ItemsOperations.dropItem(this.getItemRef());
			}

		} else {
			this.cancel();
		}
	}
}