package dkck;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

import dkck.GUI.MainWindow;

public class Sapper extends Item {

	/**
	 * ATTRIBUTES
	 */
	public static int id = 0;

	private int numberOfDisarmedBombs;

	private int healthPoints;
	
	private int numberOfRockets;

	private boolean sapperStatus;
	
	private JTextField HPLog;
	
	private JTextField positionLog;

	/**
	 * SETTERS AND GETTERS
	 */
	
	public int getNumberOfDisarmedBombs() {
		return numberOfDisarmedBombs;
	}

	public void setNumberOfDisarmedBombs(int numberOfDisarmedBombs) {
		this.numberOfDisarmedBombs = numberOfDisarmedBombs;
	}

	public int getHealthPoints() {
		return healthPoints;
	}

	public void setHealthPoints(int healthPoints) {
		this.healthPoints = healthPoints;
	}
	
	public int getNumberOfRockets() {
		return numberOfRockets;
	}

	public void setNumberOfRockets(int numberOfRockets) {
		this.numberOfRockets = numberOfRockets;
	}

	public boolean getSapperStatus() {
		return sapperStatus;
	}

	public void setSapperStatus(boolean sapperStatus) {
		this.sapperStatus = sapperStatus;
	}
	
	public JTextField getHPLog() {
		return HPLog;
	}

	public void setHPLog(JTextField HPLog) {
		this.HPLog = HPLog;
	}
	
	public JTextField getPositionLog() {
		return positionLog;
	}

	public void setPositionLog(JTextField positionLog) {
		this.positionLog = positionLog;
	}


	/**
	 * CONSTRUCTORS
	 */

	public Sapper() {
		super(id);
		id++;
		this.setSapperStatus(true);
		this.setHealthPoints(20);
		this.setNumberOfRockets(5);
		
		Random generator = new Random();

		int speed = 70 + generator.nextInt(50);

		this.setTargetsArray(new LinkedList<Item>());
		this.setMovingTimer(new MovingTimer(this));
		this.getMovingTimer().getTimer1().schedule(getMovingTimer(), 0, speed);
		
		this.setHPLog(new JTextField("Saper " + this.getId() + " HP: " + this.getHealthPoints()));
		this.getHPLog().setBorder(BorderFactory.createLineBorder(Color.white));
		MainWindow.HPPanel.add(this.getHPLog(), BorderLayout.EAST);
				
		this.setPositionLog(new JTextField("Pozycja sapera " + this.getId() + ": [" + this.getPositionX() + "][" + this.getPositionY() + "]"));
		this.getPositionLog().setBorder(BorderFactory.createLineBorder(Color.white));
		MainWindow.positionPanel.add(this.getPositionLog(), BorderLayout.EAST);
	}

	/**
	 * OTHER METHODS
	 */

	public void hurt() {
		if (this.getHealthPoints() > 0) {
			this.setHealthPoints(this.getHealthPoints() - 1);
			this.getHPLog().setText("Saper " + this.getId() + " HP: " + this.getHealthPoints());
		}
	}

	/**
	 * Saper idzie na pozycjê bomby. Nastpênie na pozycje x,y i zmienia pozycjê
	 * bomby.
	 */
	public void moveBomb(Item itemArgument, int x, int y) throws InterruptedException {
		if (itemArgument instanceof Bomb) {

			this.addTaskToMove(itemArgument, null);

			this.addTaskToMove(new Point(x, y), itemArgument);

			// IMPORTANT CODE TO USE IN THE FUTURE!!!

			// Sprawdza, czy przenosi bombe na krawedz planszy lub, czy na tym
			// miejscu nie ma innej bomby.

			// if ((x != 0) && (MainWindow.grid.cellPanes.get(x -
			// 1).get(y).getBackground() != Color.black)) {
			// itemArgument.setPositionX(x - 1);
			// itemArgument.setPositionY(y);
			//
			// MainWindow.grid.drawBomb(x - 1, y);
			// MainWindow.updateLog("The bomb was moved to [" + (x - 1) + "][" +
			// y + "]");
			// } else {
			// itemArgument.setPositionX(x);
			// if ((y != 0) && (MainWindow.grid.cellPanes.get(x).get(y -
			// 1).getBackground() != Color.black)) {
			// itemArgument.setPositionY(y - 1);
			//
			// MainWindow.grid.drawBomb(x, y - 1);
			// MainWindow.updateLog("The bomb was moved to [" + x + "][" + (y -
			// 1) + "]");
			// } else if ((y != 50) && (MainWindow.grid.cellPanes.get(x).get(y +
			// 1).getBackground() != Color.black)) {
			// itemArgument.setPositionY(y + 1);
			//
			// MainWindow.grid.drawBomb(x, y + 1);
			// MainWindow.updateLog("The bomb was moved to [" + x + "][" + (y +
			// 1) + "]");
			// } else {
			// MainWindow.updateLog("It was impossible to move bomb to [" + x +
			// "][" + (y + 1)
			// + "]. Choose different coordinates.");
			// }
			// }
		}

	}

	/**
	 * Wywo³uje metodê 'go', sprawdza status bomby, losowo decyduje, czy bomba
	 * wybuchnie.
	 */
	public void disarmBomb(Item itemArgument) throws InterruptedException {
		if (this.getHealthPoints() > 0) {
			if (itemArgument instanceof Bomb) {
				Bomb tempBombArgument = (Bomb) itemArgument;
				if (this.checkItemsCenterDistance(tempBombArgument)) {
					tempBombArgument.disarm(this);
					System.out.println("Number of disarmed bombs: " + this.getNumberOfDisarmedBombs());

				} else {
					MainWindow.updateLog("Za daleko, ¿eby rozbroiæ bombê");
				}
			}
		}
	}
}
