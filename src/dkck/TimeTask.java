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
	public TimeTask(Item itemReference, int frequency) {
		super();
		this.itemReference = itemReference;
		timer1 = new Timer();
		if (itemReference instanceof Bomb) {
			timer1.schedule(this, 0, frequency);
		}		
	}

	public void run() {
		if (itemReference instanceof Bomb) {
			Bomb tempBombReference = ((Bomb) itemReference);
			if (tempBombReference.getExplosionLeftTime() > 0) {
				tempBombReference.setExplosionLeftTime(tempBombReference.getExplosionLeftTime() - 1);
				MainWindow.updateTimerPanel("Bomb nr: " + tempBombReference.getId() + " has: "
						+ tempBombReference.getExplosionLeftTime() + " seconds left to explosion");
			} else {
				MainWindow.updateTimerPanel("Bomb nr: " + tempBombReference.getId() + " EXPLODED!");
				this.cancel();
			}
		}
	}
}