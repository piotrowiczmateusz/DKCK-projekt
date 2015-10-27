package dkck;

import java.util.TimerTask;

public class TimeTask extends TimerTask {
	Item itemReference;

	/**
	 * @param itemReference
	 */
	public TimeTask(Item itemReference) {
		super();
		this.itemReference = itemReference;
	}

	public void run() {
		if (itemReference instanceof Bomb) {
			Bomb tempBombReference = ((Bomb) itemReference);
			if (tempBombReference.getExplosionLeftTime() > 0) {
				tempBombReference.setExplosionLeftTime(tempBombReference.getExplosionLeftTime() - 1);
				System.out.println("Bomb nr: " + tempBombReference.getId() + " has: "
						+ tempBombReference.getExplosionLeftTime() + " seconds left to explosion");
			}else System.out.println("Bomb nr: " + tempBombReference.getId() + " EXPLODED!");

		}
	}
}