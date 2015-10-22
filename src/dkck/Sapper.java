package dkck;

import java.util.Random;

public class Sapper extends Item{
	
	/**
	 * ATTRIBUTES
	 */
	protected int healthPoints;
	
	protected boolean status;
	
	/**
	 * SETTERS AND GETTERS
	 */
	
	/**
	 * @return the status
	 */
	public boolean isStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	/**
	 * @return the status
	 */
	public int getHealthPoints() {
		return healthPoints;
	}

	/**
	 * @param status the status to set
	 */
	public void setHealthPoints(int healthPoints) {
		this.healthPoints = healthPoints;
	}
	
	/**
	 * CONSTRUCTORS
	 */
	
	public Sapper(int positionX, int positionY) {
		super(positionX, positionY);
		this.healthPoints = 10;
		this.status = true;
	}
	
	/**
	 * OTHER METHODS
	 */
	
	/**
	 * Zmienia pozycje sapera i sprawdza czy znajduje siê w zasiêgu ra¿enia bomby.
	 */
	public void go(int x, int y) throws InterruptedException 
	{
		while((x != this.getPositionX()) || (y != this.getPositionY())) 
		{
			if(x > this.getPositionX()) 
			{
				this.setPositionX(this.getPositionX() + 1);
			}
			else if(x < this.getPositionX()) 
			{
				this.setPositionX(this.getPositionX() - 1);	
			}
			if(y > this.getPositionY()) 
			{
				this.setPositionY(this.getPositionY() + 1);		
			}
			else if(y < this.getPositionY()) 
			{
				this.setPositionY(this.getPositionY() - 1);
			}
			System.out.println("Sapper position is: [" + this.getPositionX() + "][" + this.getPositionY() + "]");
			
			for(int i = 0; i < Main.bombsList.bombsArray.size(); i++) {
				((Bomb) Main.bombsList.bombsArray.get(i)).checkExplosionRange(this);
			}
			
			// Tu bêdzie metoda rysuj¹ca sapera na mapie;
			
			Thread.sleep(1000);
		}
	}
	
	/**
	 * Saper idzie na pozycjê bomby. Nastpênie na pozycje x,y i zmienia pozycjê bomby.
	 */
	public void moveBomb(Bomb bomb, int x, int y) throws InterruptedException {
		this.go(bomb.getPositionX(), bomb.getPositionY());		
		System.out.println("The sapper picked up a bomb.");	
		this.go(x, y);
		bomb.setPositionX(x);
		bomb.setPositionY(y);
		System.out.println("The bomb was moved to [" + x + "][" + y + "]");
	}
	
	/**
	 * Wywo³uje metodê 'go', sprawdza status bomby, losowo decyduje, czy bomba wybuchnie.
	 */
	public void disarmBomb(Bomb bomb) throws InterruptedException {
		go(bomb.getPositionX(), bomb.getPositionY());
		
		Random generator = new Random(); 
		int success = generator.nextInt(2);
		
		if(bomb.getStatus() == "disarmed") {
			System.out.println("The bomb was already disarmed");
		}
		else {
			if(success == 1) {
				bomb.setStatus("disarmed");
				System.out.println("The bomb is now disarmed");
			}		
			else {
				bomb.explode(this);
			}
		}
		
	}

}
