package dkck;

import java.util.Arrays;
import java.util.List;

import dkck.GUI.MainWindow;

public class Interpreter {

	public Interpreter() {
		super();
	}
	
	// sprawdzanie czy w wejściu jest spójnik
	public List<String> checkConjuction(String input) {
		
		List<String> inputParts = null;
		
		inputParts = Arrays.asList(input.split(" i "));
	
		return inputParts;
	}

	public Sapper getSapper(String input) {

		Class<?> sapperClass = Sapper.class;

		Item tempSapper = null;
		
		if (input.matches("(.*)saper(.*)|(.*)sapera(.*)")) {
			if (input.matches("|(.*)pierwszego(.*)|(.*)jeden(.*)|(.*)pierwszy(.*)")) {
				tempSapper = (Sapper) MainWindow.findElementByID(0, sapperClass);
			}

			else if (input.matches("|(.*)drugiego(.*)|(.*)dwa(.*)|(.*)drugi(.*)")) {
				tempSapper = (Sapper) MainWindow.findElementByID(1, sapperClass);
			}

			else if (input.matches("|(.*)trzeciego(.*)|(.*)trzy(.*)|(.*)trzeci(.*)")) {
				tempSapper = (Sapper) MainWindow.findElementByID(2, sapperClass);
			}

			else if (input.matches("|(.*)czwartego(.*)|(.*)cztery(.*|(.*)czwarty(.*))")) {
				tempSapper = (Sapper) MainWindow.findElementByID(3, sapperClass);
			}

			else if (input.matches("|(.*)piątego(.*)|(.*)pięć(.*)|(.*)piąty(.*)")) {
				tempSapper = (Sapper) MainWindow.findElementByID(4, sapperClass);
			}

			else if (input.matches("|(.*)szóstego(.*)|(.*)sześć(.*)|(.*)szósty(.*)")) {
				tempSapper = (Sapper) MainWindow.findElementByID(5, sapperClass);
			}
			
			else {
				// gdy użytkownik nie poda numeru sapera wybiera pierwszego		
				tempSapper = (Sapper) MainWindow.findElementByID(0, sapperClass);
			}
		}

		else {		
			// gdy uzytkownik nie odnosi się do sapera, program wykonuje polecenia dla pierwszego sapera
			tempSapper = (Sapper) MainWindow.findElementByID(0, sapperClass);
		}

		return (Sapper) tempSapper;
	}

	// metoda zwracająca konkretną bombę w zależności od wejścia
	public Bomb getBomb(String input, Sapper tempSapper) {

		Class<?> bombClass = Bomb.class;

		Item tempBomb = null;

		if (input.matches("|(.*)pierwsza(.*)|(.*)pierwszą(.*)|(.*)pierwszej(.*)|(.*)jeden(.*)")) {
			tempBomb = MainWindow.findElementByID(0, bombClass);
		}

		else if (input.matches("|(.*)druga(.*)|(.*)drugą(.*)|(.*)drugiej(.*)|(.*)dwa(.*)")) {
			tempBomb = MainWindow.findElementByID(1, bombClass);
		}

		else if (input.matches("|(.*)trzecia(.*)|(.*)trzecią(.*)|(.*)trzeciej(.*)|(.*)trzy(.*)")) {
			tempBomb = MainWindow.findElementByID(2, bombClass);
		}

		else if (input.matches("|(.*)czwarta(.*)|(.*)czwartą(.*)|(.*)czwartej(.*)|(.*)cztery(.*)")) {
			tempBomb = MainWindow.findElementByID(3, bombClass);
		}

		else if (input.matches("|(.*)piąta(.*)|(.*)piątą(.*)|(.*)piątej(.*)|(.*)pięć(.*)")) {
			tempBomb = MainWindow.findElementByID(4, bombClass);
		}

		else if (input.matches("|(.*)szósta(.*)|(.*)szóstą(.*)|(.*)szóstej(.*)|(.*)sześć(.*)")) {
			tempBomb = MainWindow.findElementByID(5, bombClass);
		}

		else if (input.matches("|(.*)najbliższą(.*)|(.*)najbliższej(.*)|(.*)najbliżej(.*)|(.*)blisko(.*)")) {

			double min = 50;

			for (Item tempItem : MainWindow.itemsCollection.getItemsArray()) {

				if ((tempItem instanceof Bomb) && !(tempItem instanceof Rocket)) {
					double value = tempItem.distanceCalculation(tempSapper);
					if (value < min) {
						min = value;
						tempBomb = (Bomb) tempItem;
					}
				}
			}
		}

		else if (input.matches("|(.*)najdalszą(.*)|(.*)najdalszej(.*)|(.*)najdalej(.*)|(.*)daleko(.*)")) {

			double max = 0;

			for (Item tempItem : MainWindow.itemsCollection.getItemsArray()) {

				if ((tempItem instanceof Bomb) && !(tempItem instanceof Rocket)) {
					double value = tempItem.distanceCalculation(tempSapper);
					if (value > max) {
						max = value;
						tempBomb = (Bomb) tempItem;
					}
				}
			}
		}

		else {
			
			// gdy użytkownik nie sprecyzuje numeru bomby wybiera najbliższą
			double min = 50;

			for (Item tempItem : MainWindow.itemsCollection.getItemsArray()) {

				if ((tempItem instanceof Bomb) && !(tempItem instanceof Rocket)) {
					double value = tempItem.distanceCalculation(tempSapper);
					if (value < min) {
						min = value;
						tempBomb = (Bomb) tempItem;
					}
				}
			}
		}

		return (Bomb) tempBomb;
	}

	public Tree getTree(String input) {

		Class<?> treeClass = Tree.class;

		Item tempTree = null;

		if (input.matches("|(.*)pierwszego(.*)")) {
			tempTree = MainWindow.findElementByID(0, treeClass);
		}

		else if (input.matches("|(.*)drugiego(.*)")) {
			tempTree = MainWindow.findElementByID(1, treeClass);
		}

		else if (input.matches("|(.*)trzeciego(.*)")) {
			tempTree = MainWindow.findElementByID(2, treeClass);
		}

		else if (input.matches("|(.*)czwartego(.*)")) {
			tempTree = MainWindow.findElementByID(3, treeClass);
		}

		else if (input.matches("|(.*)piątego(.*)")) {
			tempTree = MainWindow.findElementByID(4, treeClass);
		}

		else if (input.matches("|(.*)szóstego(.*)")) {
			tempTree = MainWindow.findElementByID(5, treeClass);
		}

		else {
			tempTree = MainWindow.findElementByID(0, treeClass);
		}

		return (Tree) tempTree;
	}

	public void interpret(String input) throws InterruptedException {

		List<String> inputParts = checkConjuction(input);
		
		for(int i = 0; i < inputParts.size(); i++) {
			
			input = inputParts.get(i);
			
			Sapper tempSapper = getSapper(input);

			/* Przemieszczanie siê */

			if (input.matches("|(.*)idź(.*)|(.*)pójdź(.*)")) {	
				
				int x = tempSapper.getPositionX();
				int y = tempSapper.getPositionY();

				if (input.matches("(.*)bomba(.*)|(.*)bomby(.*)|(.*)bombę(.*)")) {

					Bomb tempBomb = getBomb(input, tempSapper);

					x = tempBomb.getPositionX();
					y = tempBomb.getPositionY();

				}

				else if (input.matches("(.*)drzewo(.*)|(.*)drzewa(.*)")) {

					Tree tempTree = getTree(input);

					x = tempTree.getPositionX();
					y = tempTree.getPositionY();

				}

				else {
					if (input.matches("(.*)północ(.*)|(.*)północny(.*)|(.*)góra(.*)|(.*)górę(.*)")) {
						x = 0;
					} else if (input.matches("(.*)południe(.*)|(.*)południowy(.*)|(.*)dół(.*)")) {
						x = 49;
					}
					if (input.matches("(.*)zachód(.*)|(.*)zachodni(.*)|(.*)lewo(.*)|(.*)lewy(.*)")) {
						y = 0;
					} else if (input.matches("(.*)wschód(.*)|(.*)wschodni(.*)|(.*)prawo(.*)|(.*)prawy(.*)")) {
						y = 49;
					}
				}

				tempSapper.go(x, y);
			}

			else if (input.matches("(.*)rozbrój(.*)|(.*)rozbroić(.*)")) {

				Bomb tempBomb = getBomb(input, tempSapper);
				tempSapper.disarmBomb(tempBomb);

			}

			else if (input.matches("(.*)przenieś(.*)|(.*)przenieść(.*)")) {

				Bomb tempBomb = getBomb(input, tempSapper);
				if (input.matches("(.*)północ(.*)|(.*)północny(.*)|(.*)góra(.*)|(.*)górę(.*)|(.*)górny(.*)")
						&& input.matches("(.*)wschód(.*)|(.*)wschodni(.*)|(.*)prawo(.*)|(.*)prawy(.*)")) {
					tempSapper.moveBomb(tempBomb, 0, MainWindow.gridRows - 1);
				} else if (input.matches("(.*)północ(.*)|(.*)północny(.*)|(.*)góra(.*)|(.*)górę(.*)|(.*)górny(.*)")
						&& input.matches("(.*)zachód(.*)|(.*)zachodni(.*)|(.*)lewo(.*)|(.*)lewy(.*)")) {
					tempSapper.moveBomb(tempBomb, 0, 0);
				}
				if (input.matches("(.*)południe(.*)|(.*)południowy(.*)|(.*)dół(.*)|(.*)dolny(.*)")
						&& input.matches("(.*)wschód(.*)|(.*)wschodni(.*)|(.*)prawo(.*)|(.*)prawy(.*)")) {
					tempSapper.moveBomb(tempBomb, MainWindow.gridColumns - 1, MainWindow.gridRows - 1);
				} else if (input.matches("(.*)południe(.*)|(.*)południowy(.*)|(.*)dół(.*)|(.*)dolny(.*)")
						&& input.matches("(.*)zachód(.*)|(.*)zachodni(.*)|(.*)lewo(.*)|(.*)lewy(.*)")) {
					tempSapper.moveBomb(tempBomb, MainWindow.gridColumns - 1, 0);
				}

				if (input.matches("(.*)drzewo(.*)|(.*)drzewa(.*)")) {

					Tree tempTree = getTree(input);
					
					int x = tempTree.getPositionX();
					int y = tempTree.getPositionY();

					tempSapper.moveBomb(tempBomb, x, y);
				}
			}

			else {
			}
			
		}
		
		
	}

}