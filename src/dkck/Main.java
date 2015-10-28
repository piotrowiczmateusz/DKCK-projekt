package dkck;

public class Main {

	public static ItemsOperations itemsCollection;

	public static void main(String[] args) throws InterruptedException {

		itemsCollection = new ItemsOperations();// utworzenie obiektu klasy
												// przechowiuj¹cej wszystkie
												// elementy znajduj¹ce siê na
												// polu minowym

		itemsCollection.actions();// wywo³anie przyk³adowych czynnoœci dla
									// obiektów w polu minowym

	}

}
