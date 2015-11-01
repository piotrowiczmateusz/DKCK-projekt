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
		if (bombStatus != 1)
			bombTimer.cancel();
		if (bombStatus == 0)
			this.setExplosionLeftTime(0);
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
	 * @param timerLog the timerLog to set
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
		super(positionX, positionY, range);
		this.bombStatus = 0;
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

	/**
	 * Sprawdza czy saper jest w zasiêgu ra¿enia bomby.
	 */

	public boolean checkExplosionRange(Item sapper) {
		if (sapper instanceof Sapper) {
			if (Math.sqrt(Math.pow(this.getPositionX() - sapper.getPositionX(), 2)
					+ Math.pow(this.getPositionY() - sapper.getPositionY(), 2)) <= this.getRange()
							+ sapper.getRange()) {
						
				//MainWindow.updateLog("Danger. The sapper is in the bomb nr: " + this.getId() + " explosion range");
				
				return true;
			}
		}
		return false;
	}

	/**
	 * modyfikuje nieznacznie pola bomby i zmniejsza punkty ¿ycia dla Sapera
	 */
	public void explode(Sapper sapper) {
		
		this.setBombStatus(0);
		
		MainWindow.updateLog("The bomb nr: " + this.getId() + " exploded");
	
		// bombTimer.cancel();
		if (this.checkItemsRange(sapper) == true) {

			sapper.setHealthPoints(sapper.getHealthPoints() - 1);

			if (sapper.getHealthPoints() == 0) {
				sapper.setSapperStatus(false);
				
				MainWindow.updateLog("The sapper HP is: " + sapper.getHealthPoints() + " .The sapper is dead");			
				MainWindow.updateHPPanel("Sapper HP is: " + sapper.getHealthPoints());

			} else {
				
				MainWindow.updateLog("The sapper HP is: " + sapper.getHealthPoints());				
				MainWindow.updateHPPanel("Sapper HP is: " + sapper.getHealthPoints());
			}
		}
	}
}
