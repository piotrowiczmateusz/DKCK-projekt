package dkck;

import dkck.GUI.MainWindow;

public class Interpreter {
	
	public Interpreter() {
		super();
	}
	
	public void interpret(String input) throws InterruptedException {
		
		/* Przemieszczanie siê */
		
		if(input.matches("|(.*)idź(.*)|(.*)pójdź(.*)")) {
			
			int x = ((Sapper) MainWindow.itemsCollection.getItemsArray().get(3)).getPositionX();
			int y = ((Sapper) MainWindow.itemsCollection.getItemsArray().get(3)).getPositionY();
			
			if(input.matches("(.*)bomba(.*)|(.*)bomby(.*)")) {
				if(input.matches("(.*)najbliższej(.*)|(.*)najbliższa(.*)")) {
					
					//int max;
					//int bombID;
					
					for(int i = 0; i < MainWindow.itemsCollection.getItemsArray().size(); i++) {
						
						if(MainWindow.itemsCollection.getItemsArray().get(i) instanceof Bomb) {
							//Bomb tempBomb = (Bomb) MainWindow.itemsCollection.getItemsArray().get(i);
						}
					}
					
					
					x = MainWindow.itemsCollection.getItemsArray().get(0).getPositionX();
					y = MainWindow.itemsCollection.getItemsArray().get(0).getPositionY();
				}	
			}
			else {
				if(input.matches("(.*)północ(.*)|(.*)północny(.*)|(.*)góra(.*)|(.*)górę(.*)")) {
					x = 0;		
				}
				else if(input.matches("(.*)południe(.*)|(.*)południowy(.*)|(.*)dół(.*)")) {
					x = 49;			
				}
				if(input.matches("(.*)zachód(.*)|(.*)zachodni(.*)|(.*)lewo(.*)|(.*)lewy(.*)")) {
					y = 0;			
				}		
				else if(input.matches("(.*)wschód(.*)|(.*)wschodni(.*)|(.*)prawo(.*)|(.*)prawy(.*)")) {
					y = 49;				
				}
			}
			
			
			
			
			((Sapper) MainWindow.itemsCollection.getItemsArray().get(3)).go(x, y);
			
			/*if(input.matches("(.*)[0-9](.*)")) {
			for(int i = 0; i < input.length()-1; i++) {
				if(Character.isDigit(input.charAt(i))) {
					x = input.charAt(i) - 23;			
					
					System.out.println(x);
				}
	
			}			
		}*/
			
		}
		
		else if(input.matches("(.*)rozbrój(.*)|(.*)rozbroić(.*)")) { 
		}
		else if(input.matches("(.*)przenieś(.*)|(.*)przenieść(.*)")) {
			
		}
		
		else {
		}
	}
	
}