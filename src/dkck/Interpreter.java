package dkck;

import dkck.GUI.MainWindow;

public class Interpreter {
	
	public Interpreter() {
		super();
	}
	
	public void interpret(String input) throws InterruptedException {
		
		/* Przemieszczanie siê */
		
		if(input.matches("|(.*)idŸ(.*)|(.*)pójdŸ(.*)")) {
			
			int x = ((Sapper) MainWindow.itemsCollection.getItemsArray().get(3)).getPositionX();
			int y = ((Sapper) MainWindow.itemsCollection.getItemsArray().get(3)).getPositionY();
			
			if(input.matches("(.*)[0-9](.*)")) {
				for(int i = 0; i < input.length()-1; i++) {
					if(Character.isDigit(input.charAt(i))) {
						x = input.charAt(i) - 48;			
						
						System.out.println(x);
					}
		
				}			
			}
			
		
			if(input.matches("(.*)pó³noc(.*)|(.*)pó³nocny(.*)")) {
				x = 0;		
			}
			else if(input.matches("(.*)po³udnie(.*)|(.*)po³udniowy(.*)")) {
				x = 49;			
			}
			if(input.matches("(.*)zachód(.*)|(.*)zachodni(.*)|(.*)lewo(.*)|(.*)lewy(.*)")) {
				y = 0;			
			}		
			else if(input.matches("(.*)wschód(.*)|(.*)wschodni(.*)|(.*)prawo(.*)|(.*)prawy(.*)")) {
				y = 49;				
			}
			
			
			((Sapper) MainWindow.itemsCollection.getItemsArray().get(3)).go(x, y);
			
		}
		else {
		}
	}
	
}
