package dkck;

import java.awt.BorderLayout;

import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JTextField;

import dkck.GUI.Grid;
import dkck.GUI.MainWindow;

public class Bomb extends Item {

	private static int id = 0;

	private BombTimer bombTimer;

	private int bombStatus;// INACTIVE - 0, ACTIVE - 1, DISARMED - 2

	private int explosionLeftTime;// zmienna przechowujaca czas do wybuchu

	private JTextField timerLog;
	
	public JLabel label;


	public BombTimer getBombTimer() {
		return bombTimer;
	}

	public void setBombTimer(BombTimer bombTimer) {
		this.bombTimer = bombTimer;
	}

	public int getBombStatus() {
		return bombStatus;
	}

	public void setBombStatus(int bombStatus) {
		this.bombStatus = bombStatus;
	}

	public int getExplosionLeftTime() {
		return explosionLeftTime;
	}

	public void setExplosionLeftTime(int explosionLeftTime) {
		this.explosionLeftTime = explosionLeftTime;
	}

	public JTextField getTimerLog() {
		return timerLog;
	}

	public void setTimerLog(JTextField timerLog) {
		this.timerLog = timerLog;
	}

	public Bomb() {
		super(id);
		id++;
		
		if (this instanceof Bomb && !(this instanceof Rocket)) {
			MainWindow.timerPanel.add(this.getTimerLog(), BorderLayout.WEST);
			this.setBombTimer(new BombTimer(this, 1000));
			Random generator = new Random();
			this.setExplosionLeftTime(10 + generator.nextInt(300));

		} else {
			this.setBombTimer(null);
			this.setExplosionLeftTime(0);
		}	
	}


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

				Sapper tempSapper = (Sapper) itemArgument;
				MainWindow.updateLog("Rozbrajam...");

				Random generator = new Random();
				int success = generator.nextInt(2);

				if (success == 1) {
					this.getBombTimer().cancel();
					this.setBombStatus(2);
					tempSapper.setNumberOfDisarmedBombs(tempSapper.getNumberOfDisarmedBombs() + 1);
					tempSapper.setNumberOfRockets(tempSapper.getNumberOfRockets() + 1);
					MainWindow.updateLog("Bomba nr " + (this.getId() + 1) + " jest teraz rozbrojona");

				} else {
					MainWindow.updateLog("Nie uda³o siê rozbroiæ bomby");
					this.explode();
				}
			} else
				MainWindow.updateLog("Bomba nr: " + (this.getId() + 1) + " jest ju¿ rozbrojona");
			Grid.repairCircles();
			Grid.repairSquares();
		}
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

		if (this instanceof Bomb && !(this instanceof Rocket)) {
			timerLog.setText("Bomba nr: " + (this.getId() + 1) + " wybuch³a");

			MainWindow.updateLog("Bomba nr: " + (this.getId() + 1) + " wybuch³a");
		}

		for (int i = 0; i < MainWindow.itemsCollection.getItemsArray().size(); i++) {
			Item tempItem = MainWindow.itemsCollection.getItemsArray().get(i);

			if (tempItem instanceof Bomb) {
				Bomb tempBomb = (Bomb) tempItem;
				if (this.checkItemsCenterDistance(tempBomb) == true) {
					tempBomb.launch();

				}
			} else if (tempItem instanceof Sapper) {
				Sapper tempSapper = (Sapper) tempItem;
				if (this.checkItemsRange(tempSapper) == true) {
					tempSapper.hurt();
				}
			}
		}
		Grid.repairSquares();
		Grid.repairCircles();
	}
}
