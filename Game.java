public class Game {
	private Person player1;
	private Person player2;
	private InputGetter bob;
	
	public Game() {
		player1 = new Person();
		player2 = new Person();
		bob = new InputGetter();
	}
	
	
	public void run() {
		intro();
		placeShips();
		guess();
		outro();
	}
	
	public void outro() {
		System.out.println("Here are the final game boards:\n");
		System.out.println("Player 1's Board");
		player1.getB().displayBoard();
		System.out.println("Player 2's Board");
		player2.getB().displayBoard();
		System.out.println("Thanks for playing! Bye :)");
	}
	
	// intro message at the start of game
	public void intro() {
		System.out.println("Welcome to Battleship!");
		System.out.println("Players take turns trying to sink each other's ships.");
		System.out.println("The game ends when one player has no ships left.");
		System.out.println("");
	}
	
	// players take turn guessing
	public void guess() {
		String winner = ""; 
		while(player1.getB().checkLose() == false && player2.getB().checkLose() == false) {
			// player 1 turn
			System.out.println("Player 1, chose a point to fire at: ");
			player1.getB().displayGuesses();
			boolean validG = false;
			int rowNum1 = 100;
			int colNum1 = 100;
			while(validG == false) {
				int row = bob.inputInt("Enter a row number:", 0, 9);
				int col = bob.inputInt("Enter a column number:", 0, 9);
				validG = player1.getB().validGuess(row, col);
				if (!validG) {
					System.out.println("You can't guess the same spot twice, try again.");
				} else {
					rowNum1 = row;
					colNum1 = col;
				}
			} 
			boolean hit = player2.getB().checkHit(rowNum1, colNum1);
			String updateG = "o";
			if(hit) {
				updateG = "x";
				System.out.println("\nHIT");
			} else {
				System.out.println("\nMISS");
			}
			player1.getB().updateGuesses(rowNum1, colNum1, updateG);
			
			// player 2 turn
			System.out.println("Player 2, chose a point to fire at: ");
			player2.getB().displayGuesses();
			boolean validG2 = false;
			int rowNum2 = 100;
			int colNum2 = 100;
			while(validG2 == false) {
				int row2 = bob.inputInt("Enter a row number:", 0, 9);
				int col2 = bob.inputInt("Enter a column number:", 0, 9);
				validG2 = player2.getB().validGuess(row2, col2);
				if (!validG2) {
					System.out.println("You can't guess the same spot twice, try again.");
				} else {
					rowNum2 = row2;
					colNum2 = col2;
				}
			}
			boolean hit2 = player1.getB().checkHit(rowNum2, colNum2);
			String updateG2 = "o";
			if(hit2) {
				updateG2 = "x";
				System.out.println("\nHIT");
			} else {
				System.out.println("\nMISS");
			}
			player2.getB().updateGuesses(rowNum2, colNum2, updateG2);
			
			if(player1.getB().checkLose() == false && player2.getB().checkLose() == true) {
				winner = "Player 1";
			} else if (player1.getB().checkLose() == true && player2.getB().checkLose() == false) {
				winner = "Player 2";
			} else if(player1.getB().checkLose() == true && player2.getB().checkLose() == true) {
				winner = "tie";
			}
		}
		System.out.println("\nThe winner is: " + winner);
	}
	
	
 
	public boolean placeShip(Person p, String ship, int numHoles) {
		boolean placedShip = false;
		int carrierRow = bob.inputInt("What row would you like to place your the top of your " + ship + "?", 0, 9);
		int carrierCol = bob.inputInt("What column would you like to place the top of your " + ship + "?", 0, 9);
		boolean valid = p.getB().isValid(carrierRow, carrierCol, numHoles);
		if (valid) {
			while (placedShip == false) {
				int direction = bob.inputInt("What direction would you like to place your " + ship + "? (up = 1, down = 2, left = 3, right = 4)" , 1, 4);
				if (direction == 1) {
					if (p.getB().checkUp(numHoles, carrierRow, carrierCol)) {
						placedShip = true;
						placeSpecifiedShip(ship, p, carrierRow, carrierCol, direction);
				 		p.getB().displayBoard();
					} else {
						System.out.println("That direction is invalid, try another one.");
					}
				} if (direction == 2) {
					if (p.getB().checkDown(numHoles, carrierRow, carrierCol)) {
						placedShip = true;
						placeSpecifiedShip(ship, p, carrierRow, carrierCol, direction);
				 		p.getB().displayBoard();
					} else {
						System.out.println("That direction is invalid, try another one.");
					}
				} if (direction == 3) {
					if (p.getB().checkLeft(numHoles, carrierRow, carrierCol)) {
						placedShip = true;
						placeSpecifiedShip(ship, p, carrierRow, carrierCol, direction);
				 		p.getB().displayBoard();
					} else {
						System.out.println("That direction is invalid, try another one.");
					}
				} if (direction == 4) {
					if (p.getB().checkRight(numHoles, carrierRow, carrierCol)) {
						placedShip = true;
						placeSpecifiedShip(ship, p, carrierRow, carrierCol, direction);
				 		p.getB().displayBoard();
					} else {
						System.out.println("That direction is invalid, try another one.");
					}
				}
				
			} 
		} else {
			System.out.println("That point is not valid, try again.");
		}
		return placedShip;
	}
	
	public void placeSpecifiedShip(String ship, Person p, int row, int col, int direction) {
		if(ship.equals("carrier")) {
			p.getB().carrier(row, col, direction);
		}
		if(ship.equals("battleship")) {
			p.getB().battleship(row, col, direction);
		}
		if(ship.equals("cruiser")) {
			p.getB().cruiser(row, col, direction);
		}
		if(ship.equals("submarine")) {
			p.getB().submarine(row, col, direction);
		}
		if(ship.equals("destroyer")) {
			p.getB().destroyer(row, col, direction);	
		}
	}
	
	// players place their ships on their boards respectively
	public void placeShips() {
		System.out.println("Player 1 place, all your ships first: \n" );
		player1.getB().displayBoard();
		// player 1 ships
		boolean placedCarrier = false;
		boolean placedBattleship = false;
		boolean placedCruiser = false;
		boolean placedSubmarine = false;
		boolean placedDestroyer = false;
		
		// player 2 ships
		boolean placedCarrier2 = false;
		boolean placedBattleship2 = false;
		boolean placedCruiser2 = false;
		boolean placedSubmarine2 = false;
		boolean placedDestroyer2 = false;
		while (placedCarrier == false) {
			int carrierHoles = player1.getCarrier().getNumHoles();
			String carrierName = player1.getCarrier().getName();
			placedCarrier = placeShip(player1, carrierName, carrierHoles);	
		} 
		while (placedBattleship == false) {
			int battleHoles = player1.getBattleship().getNumHoles();
			String battleName = player1.getBattleship().getName();
			placedBattleship = placeShip(player1, battleName, battleHoles);
			
		}
		while (placedCruiser == false) {
			int cruzHoles = player1.getCruiser().getNumHoles();
			String cruzName = player1.getCruiser().getName();
			placedCruiser = placeShip(player1, cruzName, cruzHoles);
			
		}
		while (placedSubmarine == false) {
			int subHoles = player1.getSubmarine().getNumHoles();
			String subName = player1.getSubmarine().getName();
			placedSubmarine = placeShip(player1, subName, subHoles);
			
		}
		while (placedDestroyer == false) {
			int dHoles = player1.getDestroyer().getNumHoles();
			String dName = player1.getDestroyer().getName();
			placedDestroyer = placeShip(player1, dName, dHoles);
			
		}
		for (int i = 0; i<20; i++) {
			System.out.println("");
		}
		System.out.println("Player 2 place all your ships: \n" );
		player2.getB().displayBoard();
		while (placedCarrier2 == false) {
			int carrierHoles2 = player2.getCarrier().getNumHoles();
			String carrierName2 = player2.getCarrier().getName();
			placedCarrier2 = placeShip(player2, carrierName2, carrierHoles2);	
		} 
		while (placedBattleship2 == false) {
			int battleHoles2 = player2.getBattleship().getNumHoles();
			String battleName2 = player2.getBattleship().getName();
			placedBattleship2 = placeShip(player2, battleName2, battleHoles2);
			
		}
		while (placedCruiser2 == false) {
			int cruzHoles2 = player2.getCruiser().getNumHoles();
			String cruzName2 = player2.getCruiser().getName();
			placedCruiser2 = placeShip(player2, cruzName2, cruzHoles2);
			
		}
		while (placedSubmarine2 == false) {
			int subHoles2 = player2.getSubmarine().getNumHoles();
			String subName2 = player2.getSubmarine().getName();
			placedSubmarine2 = placeShip(player2, subName2, subHoles2);
			
		}
		while (placedDestroyer2 == false) {
			int dHoles2 = player2.getDestroyer().getNumHoles();
			String dName2 = player2.getDestroyer().getName();
			placedDestroyer2 = placeShip(player2, dName2, dHoles2);
			
		}	
		
		for (int i = 0; i<20; i++) {
			System.out.println("");
		}
	}
	
	

}
