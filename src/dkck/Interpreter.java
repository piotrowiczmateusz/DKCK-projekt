package dkck;

import dkck.GUI.MainWindow;

public class Interpreter {
	
	public Interpreter() {
		super();
	}
	
	public void interpret(String input) throws InterruptedException {
		if(input.matches("(.*)go(.*)|(.*)id�(.*)|(.*)p�jd�(.*)")) {
			
			int x = ((Sapper) MainWindow.itemsCollection.getItemsArray().get(3)).getPositionX();
			int y = ((Sapper) MainWindow.itemsCollection.getItemsArray().get(3)).getPositionY();
			
			if(input.matches("(.*)p�noc(.*)|(.*)north(.*)")) {
				x = 0;
				((Sapper) MainWindow.itemsCollection.getItemsArray().get(3)).go(x, y);
			}
			if(input.matches("(.*)zach�d(.*)|(.*)lewo(.*)")) {
				y = 0;
				((Sapper) MainWindow.itemsCollection.getItemsArray().get(3)).go(x, y);
			}
			if(input.matches("(.*)po�udnie(.*)|(.*)south(.*)")) {
				x = 49;
				((Sapper) MainWindow.itemsCollection.getItemsArray().get(3)).go(x, y);
			}
			if(input.matches("(.*)wsch�d(.*)|(.*)prawo(.*)")) {
				y = 49;
				((Sapper) MainWindow.itemsCollection.getItemsArray().get(3)).go(x, y);
			}
			
			
		}
		else {
		}
	}
	
}
