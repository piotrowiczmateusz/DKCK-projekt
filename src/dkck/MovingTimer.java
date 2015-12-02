package dkck;

import java.util.Timer;
import java.util.TimerTask;
import dkck.ItemsOperations;

import dkck.GUI.Grid;
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

	public MovingTimer(Item itemReference) {
		super();
		this.setItemReference(itemReference);
		this.setTimer1(new Timer());
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

					MainWindow.updateLog("Saper nie ¿yje");
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
					
					MainWindow.updateLog("Obiekt " +
							this.getItemReference().nameOfItem(this.getItemReference()) + " dotar³ do celu");
					if (this.getItemReference() instanceof Rocket) {
						((Rocket) this.getItemReference()).explode();

					}

					continueStep = false;
				}

				// checking bomb loss

				if (this.getItemReference().getTargetsArray().get(1) instanceof Bomb
						&& this.getItemReference() instanceof Sapper) {
					Item tempItem = ((Sapper) this.getItemReference()).getTargetsArray().get(1);
					if (tempItem.getPositionX() != initItemPositionX && tempItem.getPositionY() != initItemPositionY) {

						MainWindow.updateLog("Obiekt " + this.getItemReference().nameOfItem(this.getItemReference())+ "zgubi³ bombê");
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

					if( this.getItemReference() instanceof Sapper) MainWindow.updatePositionPanel("Pozycja sapera: [" + (this.getItemReference().getPositionX() + 1)
							+ "][" + (this.getItemReference().getPositionY() + 1) + "]");

					// moving bomb

					if (this.getItemReference().getTargetsArray().get(1) instanceof Bomb
							&& this.getItemReference() instanceof Sapper) {
						tempItem = ((Sapper) this.getItemReference()).getTargetsArray().get(1);

						tempItem.setPositionX(this.getItemReference().getPositionX());
						tempItem.setPositionY(this.getItemReference().getPositionY());

						MainWindow.grid.drawCircle(initItemPositionX, initItemPositionY, tempItem.getRange(), null);
						MainWindow.grid.drawCircle(tempItem.getPositionX(), tempItem.getPositionY(),
								tempItem.getRange(), tempItem);
						MainWindow.grid.drawSquare(initItemPositionX, initItemPositionY, tempItem.getPositionX(),
								tempItem.getPositionY(), tempItem);
						//MainWindow.grid.cellPanes.get(tempItem.getPositionX()).get(tempItem.getPositionY()).label
								//.setText((tempItem.getId() + 1) + "");

					}

					// drawing moving object

					MainWindow.grid.drawCircle(initItemPositionX, initItemPositionY, this.getItemReference().getRange(),
							null);
					MainWindow.grid.drawCircle(this.getItemReference().getPositionX(),
							this.getItemReference().getPositionY(), this.getItemReference().getRange(), tempItem);
					MainWindow.grid.drawSquare(initItemPositionX, initItemPositionY,
							this.getItemReference().getPositionX(), this.getItemReference().getPositionY(), tempItem);

					// MainWindow.grid.repairCircle(this.getItemReference().getPositionX(),
					// this.getItemReference().getPositionY(),
					// this.getItemReference().getRange());
					// MainWindow.grid.repairSquare(initItemPositionX,
					// initItemPositionY);
					Grid.repairCircles();
					Grid.repairSquares();
				} else
					run();// recursive call
			} else if (this.getItemReference() instanceof Rocket) {
				ItemsOperations.dropItem(this.getItemReference());
			}

		} else {
			this.cancel();

		}
	}
}