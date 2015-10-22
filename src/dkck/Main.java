package dkck;

public class Main {

	public static BombsArray bombsList;

	public static void main(String[] args) throws InterruptedException {
		
		bombsList = new BombsArray();
		Bomb b1 = new Bomb(2, 2, 2);
		Bomb b2 = new Bomb(8, 16, 5);		
		bombsList.bombsArray.add(b1);
		bombsList.bombsArray.add(b2);

		Sapper s1 = new Sapper(1, 1);

		b1.explode(s1);
		//s1.moveBomb(b1, 6, 9);
		//s1.moveBomb(b2, 6, 9);
		//s1.disarmBomb(b1);
		//s1.disarmBomb(b2);
		
	}

}
