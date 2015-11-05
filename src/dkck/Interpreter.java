package dkck;

import dkck.GUI.MainWindow;

public class Interpreter {
	
	public Interpreter() {
		super();
	}
	
	public void interpret(String input) throws InterruptedException {
		if(input.matches("(.*)go(.*)|(.*)idŸ(.*)|(.*)pójdŸ(.*)")) {
			
			int x = ((Sapper) MainWindow.itemsCollection.getItemsArray().get(3)).getPositionX();
			int y = ((Sapper) MainWindow.itemsCollection.getItemsArray().get(3)).getPositionY();
			
			if(input.matches("(.*)pó³noc(.*)|(.*)north(.*)")) {
				x = 0;
				((Sapper) MainWindow.itemsCollection.getItemsArray().get(3)).go(x, y);
			}
			if(input.matches("(.*)zachód(.*)|(.*)lewo(.*)")) {
				y = 0;
				((Sapper) MainWindow.itemsCollection.getItemsArray().get(3)).go(x, y);
			}
			if(input.matches("(.*)po³udnie(.*)|(.*)south(.*)")) {
				x = 49;
				((Sapper) MainWindow.itemsCollection.getItemsArray().get(3)).go(x, y);
			}
			if(input.matches("(.*)wschód(.*)|(.*)prawo(.*)")) {
				y = 49;
				((Sapper) MainWindow.itemsCollection.getItemsArray().get(3)).go(x, y);
			}
			
			
		}
		else {
		}
	}
	
}
