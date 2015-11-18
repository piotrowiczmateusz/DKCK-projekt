package dkck;

import java.util.Timer;
import java.util.TimerTask;

public class BombTimer extends TimerTask {
	
	public Item itemReference;

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
				/*System.out.println("Bomb nr: " + tempBomb.getId() + " has: "
						+ tempBomb.getExplosionLeftTime() + " seconds left to explosion");*/
				
				tempBomb.getTimerLog().setText("Bomba nr: " + tempBomb.getId() + " ma: "
						+ tempBomb.getExplosionLeftTime() + " sekund do wybuchu");
				
				
			} else {
				tempBomb.explode();
			}

		} else {
			this.cancel();
		}
	}
}