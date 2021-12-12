public class Ship {
	int numHoles;
	String name;
	String shipType;	
	
	public Ship(int num, String s, String type) {
		numHoles = num;
		name = s;
		shipType = type;
	}

	public int getNumHoles() {
		return numHoles;
	}

	public String getName() {
		return name;
	}
	

}
