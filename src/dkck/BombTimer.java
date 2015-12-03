package dkck;

import java.util.Timer;
import java.util.TimerTask;

public class BombTimer extends TimerTask {

	public Item itemReference;

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

	public BombTimer(Item itemReference, int miliseconds) {
		super();
		this.itemReference = itemReference;
		timer1 = new Timer();
		timer1.schedule(this, 0, miliseconds);
	}

	public void run() {

		if (itemReference instanceof Bomb && (itemReference instanceof Rocket == false)) {
			Bomb tempBomb = ((Bomb) itemReference);
			if (tempBomb.getExplosionLeftTime() > 0) {
				tempBomb.setExplosionLeftTime(tempBomb.getExplosionLeftTime() - 1);

				tempBomb.getTimerLog().setText("Bomba nr: " + (tempBomb.getId() + 1) + " ma: "
						+ tempBomb.getExplosionLeftTime() + " sekund do wybuchu");

			} else {
				tempBomb.explode();
			}

		} else {
			this.cancel();
		}
	}
}