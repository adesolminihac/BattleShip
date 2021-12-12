public class Person {
	Board b;
	Ship carrier;
	Ship battleship;
	Ship cruiser;
	Ship submarine;
	Ship destroyer;
	
	public Person() {
		b = new Board();
		carrier = new Ship(5, "carrier", "1");
		battleship = new Ship(4, "battleship", "2");
		cruiser = new Ship(3, "cruiser", "3");
		submarine = new Ship(3, "submarine", "4");
		destroyer = new Ship(2, "destroyer", "5");
	}

	public Board getB() {
		return b;
	}
	
	public Ship getCarrier() {
		return carrier;
	}

	public Ship getBattleship() {
		return battleship;
	}

	public Ship getCruiser() {
		return cruiser;
	}

	public Ship getSubmarine() {
		return submarine;
	}

	public Ship getDestroyer() {
		return destroyer;
	}

	
	
	

}
