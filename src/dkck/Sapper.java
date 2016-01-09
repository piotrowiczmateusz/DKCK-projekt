package dkck;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

import dkck.GUI.MainWindow;

public class Sapper extends Item {

	public static int id = 0;

	private int numberOfDisarmedBombs;

	private int healthPoints;
	
	private int numberOfRockets;

	private boolean sapperStatus;
	
	private JTextField HPLog;
	
	private JTextField positionLog;
	
	private JTextField rocketLog;
	
	private JTextField bombLog;

	
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
	
	public JTextField getRocketLog() {
		return rocketLog;
	}

	public void setRocketLog(JTextField rocketLog) {
		this.rocketLog = rocketLog;
	}
	
	public JTextField getBombLog() {
		return bombLog;
	}

	public void setBombLog(JTextField bombLog) {
		this.bombLog = bombLog;
	}
	
	Font font = new Font("Arial", Font.PLAIN,11);
	
	public Sapper() {
		super(id);
		id++;
		this.setSapperStatus(true);
		this.setHealthPoints(5);
		this.setNumberOfRockets(5);
		
		JTextField HP = new JTextField("Saper " + (this.getId()+1) + " HP: " + this.getHealthPoints());
		JTextField position = new JTextField("[" + (this.getPositionX()+1) + "][" + (this.getPositionY()+1) + "]");
		JTextField rockets = new JTextField("Rakiety: " + this.getNumberOfRockets());
		JTextField bombs = new JTextField("Rozbrojone bomby: " + this.getNumberOfDisarmedBombs());
		
		Random generator = new Random();

		int speed = 150 + generator.nextInt(120);

		this.setTargetsArray(new LinkedList<Item>());
		this.setMovingTimer(new MovingTimer(this));
		this.getMovingTimer().getTimer1().schedule(getMovingTimer(), 0, speed);
		
		HP.setFont(font);
		this.setHPLog(HP);
		this.getHPLog().setBorder(BorderFactory.createLineBorder(Color.white));
		MainWindow.HPPanel.add(this.getHPLog(), BorderLayout.EAST);
		
		position.setFont(font);
		this.setPositionLog(position);
		this.getPositionLog().setBorder(BorderFactory.createLineBorder(Color.white));
		MainWindow.positionPanel.add(this.getPositionLog(), BorderLayout.EAST);
		
		rockets.setFont(font);
		this.setRocketLog(rockets);
		this.getRocketLog().setBorder(BorderFactory.createLineBorder(Color.white));
		MainWindow.rocketPanel.add(this.getRocketLog(), BorderLayout.EAST);
		
		bombs.setFont(font);
		this.setBombLog(bombs);
		this.getBombLog().setBorder(BorderFactory.createLineBorder(Color.white));
		MainWindow.bombPanel.add(this.getBombLog(), BorderLayout.EAST);
	}

	public void hurt() {
		if (this.getHealthPoints() > 0) {
			this.setHealthPoints(this.getHealthPoints() - 1);
			this.getHPLog().setText("Saper " + (this.getId()+1) + " HP: " + this.getHealthPoints());
		}
	}

	
	// Saper idzie na pozycjê bomby. Nastpênie na pozycje x,y i zmienia pozycjê bomby.
	 
	public void moveBomb(Item itemArgument, int x, int y) throws InterruptedException {
		if (itemArgument instanceof Bomb) {

			this.addTaskToMove(itemArgument, null);
			this.addTaskToMove(new Point(x, y), itemArgument);

		}
	}

	
	//Wywo³uje metodê 'go', sprawdza status bomby, losowo decyduje, czy bomba wybuchnie.
	 
	public void disarmBomb(Item itemArgument) throws InterruptedException {
		if (this.getHealthPoints() > 0) {
			if (itemArgument instanceof Bomb) {
				Bomb tempBombArgument = (Bomb) itemArgument;
				if (this.checkItemsCenterDistance(tempBombArgument)) {
					tempBombArgument.disarm(this);
					System.out.println("Number of disarmed bombs: " + this.getNumberOfDisarmedBombs());
					this.getBombLog().setText("Rozbrojone bomby: " + this.getNumberOfDisarmedBombs());
					this.getRocketLog().setText("Rakiety: " + this.getNumberOfRockets());

				} else {
					MainWindow.updateLog("Za daleko, ¿eby rozbroiæ bombê");
				}
			}
		}
	}
}
