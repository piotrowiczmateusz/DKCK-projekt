package dkck;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Random;

import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import dkck.GUI.MainWindow;

public class Bomb extends Item {

	/**
	 * ATTRIBUTES
	 */

	private static int id = 0;

	private BombTimer bombTimer;

	private int bombStatus;// INACTIVE - 0, ACTIVE - 1, DISARMED - 2

	private int explosionLeftTime;// zmienna przechowujaca czas do wybuchu

	private JTextField timerLog;

	/**
	 * SETTERS AND GETTERS
	 */

	/**
	 * @return the bombTimer
	 */
	public BombTimer getBombTimer() {
		return bombTimer;
	}

	/**
	 * @param bombTimer
	 *            the bombTimer to set
	 */
	public void setBombTimer(BombTimer bombTimer) {
		this.bombTimer = bombTimer;
	}

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
	// public Bomb(int positionX, int positionY, int range, int
	// explosionLeftTime) {
	// super(positionX, positionY, range, id++, 0);
	// this.bombStatus = 1;
	// this.explosionLeftTime = explosionLeftTime;
	// this.timerLog = new JTextField();
	//
	// timerLog.setOpaque(true);
	// timerLog.setBackground(Color.white);
	// timerLog.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0,
	// 0)));
	// timerLog.setPreferredSize(new Dimension(358, 14));
	// timerLog.setEditable(false);
	//
	// this.setBombTimer(new BombTimer(this, 1000));
	//
	// MainWindow.grid.drawSquare(positionX, positionY, positionX, positionY,
	// MainWindow.bombColor);
	// }

	public Bomb() {
		super(id);
		id++;

		if (this instanceof Rocket)
			this.setBombTimer(null);
		else
			this.setBombTimer(new BombTimer(this, 1000));

		if (this.getBombTimer() == null) {
			this.setExplosionLeftTime(0);
		} else {
			Random generator = new Random();
			this.setExplosionLeftTime(10 + generator.nextInt(300));
		}

		this.setBombStatus(1);
		this.timerLog = new JTextField();

		timerLog.setOpaque(true);
		timerLog.setBackground(Color.white);
		timerLog.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		timerLog.setPreferredSize(new Dimension(358, 14));
		timerLog.setEditable(false);
	}

	/**
	 * OTHER METHODS
	 */

	public void launch() {

		if (this.getBombStatus() == 1 && this.getBombTimer() != null) {
			int launchTime = 5;
			if (this.getExplosionLeftTime() <= launchTime)
				this.explode();
			else
				this.setExplosionLeftTime(launchTime);
		}
	}

	public void disarm(Item itemArgument) {
		if (itemArgument instanceof Sapper) {

			if (this.getBombStatus() != 0) {

				Sapper tempSapperReference = (Sapper) itemArgument;
				System.out.println("Disarming...");

				Random generator = new Random();
				int success = generator.nextInt(2);

				if (success == 1) {
					this.getBombTimer().cancel();
					this.setBombStatus(2);
					tempSapperReference.setNumberOfDisarmedBombs(tempSapperReference.getNumberOfDisarmedBombs() + 1);
					MainWindow.updateLog("The bomb nr " + this.getId() + " is now disarmed");

				} else {
					this.explode();
				}
			} else
				System.out.println("The bomb nr: " + this.getId() + " is already disarmed");
		} else
			System.out.println("This is not a Sapper!!");
	}

	/**
	 * modyfikuje nieznacznie pola bomby i zmniejsza punkty ¿ycia dla Sapera
	 */
	public void explode() {
		if (this.getBombStatus() == 1 && this.getBombTimer() != null) {
			this.getBombTimer().cancel();
			this.setExplosionLeftTime(0);
			this.setBombStatus(0);
		}

		if (this instanceof Bomb && (this instanceof Rocket) == false)
			timerLog.setText(this.nameOfItem(this) + " nr: " + this.getId() + " EXPLODED!");

		MainWindow.updateLog(this.nameOfItem(this) + " nr: " + this.getId() + " exploded");

		for (int i = 0; i < MainWindow.itemsCollection.getItemsArray().size(); i++) {
			Item tempItem = MainWindow.itemsCollection.getItemsArray().get(i);

			if (tempItem instanceof Bomb) {
				Bomb tempBombReference = (Bomb) tempItem;
				if (this.checkItemsCenterDistance(tempBombReference) == true) {
					tempBombReference.launch();

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
		// tempItemArgument.setHealthPoints(tempItemArgument.getHealthPoints()
		// -
		// 1);
		// if (tempItemArgument.getHealthPoints() == 0) {
		// tempItemArgument.setSapperStatus(false);
		// MainWindow.updateLog(
		// "The sapper HP is: " + tempItemArgument.getHealthPoints() + "
		// .The
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
