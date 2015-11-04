package dkck;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import dkck.GUI.MainWindow;

public class Bomb extends Item {

	/**
	 * ATTRIBUTES
	 */

	public static int id = 0;

	TimeTask bombTimer;

	private int bombStatus;// EXPLODED - 0, ACTIVE - 1, DISARMED - 2

	private int explosionLeftTime;// zmienna przechowujaca czas do wybuchu

	private JTextField timerLog;

	/**
	 * SETTERS AND GETTERS
	 */

	/**
	 * @return the bombStatus
	 */
	public int getBombStatus() {
		return bombStatus;
	}

	/**
	 * @param bombStatus
	 *            the bombStatus to set
	 */
	public void setBombStatus(int bombStatus) {
		this.bombStatus = bombStatus;
	}

	/**
	 * @return the explosionLeftTime
	 */
	public int getExplosionLeftTime() {
		return explosionLeftTime;
	}

	/**
	 * @param explosionLeftTime
	 *            the explosionLeftTime to set
	 */
	public void setExplosionLeftTime(int explosionLeftTime) {
		this.explosionLeftTime = explosionLeftTime;
	}

	/**
	 * @return the timerLog
	 */
	public JTextField getTimerLog() {
		return timerLog;
	}

	/**
	 * @param timerLog
	 *            the timerLog to set
	 */
	public void setTimerLog(JTextField timerLog) {
		this.timerLog = timerLog;
	}

	/**
	 * CONSTRUCTORS
	 */

	/**
	 * @param positionX
	 * @param positionY
	 * @param range
	 * @param id
	 * @param bombStatus
	 */
	public Bomb(int positionX, int positionY, int range, int explosionLeftTime) {
		super(positionX, positionY, range, id++);
		this.bombStatus = 1;
		this.explosionLeftTime = explosionLeftTime;
		this.timerLog = new JTextField();

		timerLog.setOpaque(true);
		timerLog.setBackground(Color.white);
		timerLog.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		timerLog.setPreferredSize(new Dimension(358, 14));
		timerLog.setEditable(false);

		bombTimer = new TimeTask(this, 1000);

		MainWindow.grid.drawBomb(positionX, positionY);

	}

	/**
	 * OTHER METHODS
	 */

	public void burn() {
		if (this.bombStatus == 1 && this.explosionLeftTime > 10)
			explosionLeftTime = 10;
	}

	public void disarm() {
		this.bombTimer.cancel();
		this.setBombStatus(2);
	}

	/**
	 * modyfikuje nieznacznie pola bomby i zmniejsza punkty ¿ycia dla Sapera
	 */
	public void explode() {
		// this.bombTimer.cancel();
		this.setBombStatus(0);
		this.setExplosionLeftTime(0);
		this.bombTimer.cancel();
		timerLog.setText("Bomb nr: " + this.getId() + " EXPLODED!");

		MainWindow.updateLog("The bomb nr: " + this.getId() + " exploded");

		for (int i = 0; i < MainWindow.itemsCollection.getItemsArray().size(); i++) {
			Item tempItem = MainWindow.itemsCollection.getItemsArray().get(i);

			if (tempItem instanceof Bomb) {
				Bomb tempBombReference = (Bomb) tempItem;
				if (this.checkItemsCenterDistance(tempBombReference) == true) {
					tempBombReference.burn();

				}
			} else if (tempItem instanceof Sapper) {
				Sapper tempSapperReference = (Sapper) tempItem;
				if (this.checkItemsRange(tempSapperReference) == true) {
					tempSapperReference.hurt();

				}

			}

			// }

			// System.out.println("test");
		}

		// if (itemArgument instanceof Sapper) {
		// Sapper tempItemArgument = (Sapper) itemArgument;
		// if (this.checkItemsRange(tempItemArgument)) {
		// tempItemArgument.setHealthPoints(tempItemArgument.getHealthPoints() -
		// 1);
		// if (tempItemArgument.getHealthPoints() == 0) {
		// tempItemArgument.setSapperStatus(false);
		// MainWindow.updateLog(
		// "The sapper HP is: " + tempItemArgument.getHealthPoints() + " .The
		// sapper is dead");
		// MainWindow.updateHPPanel("Sapper HP is: " +
		// tempItemArgument.getHealthPoints());
		//
		// } else {
		// MainWindow.updateLog("The sapper HP is: " +
		// tempItemArgument.getHealthPoints());
		// MainWindow.updateHPPanel("Sapper HP is: " +
		// tempItemArgument.getHealthPoints());
		//
		// }
		// }
		// } else
		// MainWindow.updateLog("THIS IS NOT A SAPPER!");

	}

}
