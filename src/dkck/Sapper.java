package dkck;

import java.util.Random;

public class Sapper {
	
	/**
	 * ATTRIBUTES
	 */
	protected int positionX;
	
	protected int positionY;
	
	protected boolean status;

	
	/**
	 * SETTERS AND GETTERS
	 */
	
	/**
	 * @return the positionX
	 */
	public int getPositionX() {
		return positionX;
	}

	/**
	 * @param positionX the positionX to set
	 */
	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	/**
	 * @return the positionY
	 */
	public int getPositionY() {
		return positionY;
	}

	/**
	 * @param positionY the positionY to set
	 */
	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

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
	 * CONSTRUCTORS
	 */
	
	public Sapper(int positionX, int positionY) {
		super();
		this.positionX = positionX;
		this.positionY = positionY;
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
