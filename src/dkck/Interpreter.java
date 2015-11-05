package dkck;

import dkck.GUI.MainWindow;

public class Interpreter {
	
	public Interpreter() {
		super();
	}
	
	public void interpret(String input) throws InterruptedException {
		
		/* Przemieszczanie si� */
		
		if(input.matches("|(.*)id�(.*)|(.*)p�jd�(.*)")) {
			
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
			
		
			if(input.matches("(.*)p�noc(.*)|(.*)p�nocny(.*)")) {
				x = 0;		
			}
			else if(input.matches("(.*)po�udnie(.*)|(.*)po�udniowy(.*)")) {
				x = 49;			
			}
			if(input.matches("(.*)zach�d(.*)|(.*)zachodni(.*)|(.*)lewo(.*)|(.*)lewy(.*)")) {
				y = 0;			
			}		
			else if(input.matches("(.*)wsch�d(.*)|(.*)wschodni(.*)|(.*)prawo(.*)|(.*)prawy(.*)")) {
				y = 49;				
			}
			
			
			((Sapper) MainWindow.itemsCollection.getItemsArray().get(3)).go(x, y);
			
		}
		else {
		}
	}
	
}
