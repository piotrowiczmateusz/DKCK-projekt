package dkck;

public class Main {

	public static ItemsOperations itemsCollection;

	public static void main(String[] args) throws InterruptedException {

		itemsCollection = new ItemsOperations();// utworzenie obiektu klasy
												// przechowiuj�cej wszystkie
												// elementy znajduj�ce si� na
												// polu minowym

		itemsCollection.actions();// wywo�anie przyk�adowych czynno�ci dla
									// obiekt�w w polu minowym

	}

}
