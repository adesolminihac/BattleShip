public class Board {
	private String[][] map;
	private String[][] guesses;
	
	
	public Board() {
		map = new String[10][10];
		guesses = new String[10][10];
		for (int row = 0; row < map.length; row++) {
			for (int col = 0; col < map[row].length; col++) {
				map[row][col] = ".";
			}
		}
		for (int row = 0; row < guesses.length; row++) {
			for (int col = 0; col < guesses[row].length; col++) {
				guesses[row][col] = ".";
			}
		}
	}
	
	// returns true or false to indicate if a guess is valid (not already been guessed)
	public boolean validGuess(int row, int col) {
		boolean b = true;
		if (guesses[row][col].equals("o") || guesses[row][col].equals("x")) {
			b = false;
		}
		return b;
	}
	
	public void displayBoard() {
		System.out.print("   ");
		for(int i = 0; i<10; i ++) {
			System.out.print(i + " ");
		}
		System.out.print("\n");
		System.out.print("  ");
		for(int i = 0; i<20; i ++) {
			System.out.print("-");
		}
		System.out.print("\n");
		for (int row = 0; row < map.length; row++) {
			System.out.print(row + "| ");
			for (int col = 0; col < map[row].length; col++) {
				System.out.print(map[row][col] + " ");
			}
			System.out.println();
		}
		System.out.print("\n");
	}
	
	// checks if the spot that the player guessed was a ship on the map and changes it to an "x" if it was
	public boolean checkHit(int row, int col) {
		boolean b = false;
		if(map[row][col].equals("1") || map[row][col].equals("2") || map[row][col].equals("3") || map[row][col].equals("4") || map[row][col].equals("5")) {
			b = true;
			map[row][col] = "x";
		}
		return b;
	}
	
	// checks if the game is over and no ships reamain on the board
	public boolean checkLose() {
		boolean lose = true;
		for (int row = 0; row < guesses.length; row++) {
			for (int col = 0; col < guesses[row].length; col++) {
				if(map[row][col].equals("1") || map[row][col].equals("2") || map[row][col].equals("3") || map[row][col].equals("4") || map[row][col].equals("5")) {
					lose = false;
				}
			}
		}
		return lose;
	}

	public String[][] getMap() {
		return map;
	}


	public void displayGuesses() {
		System.out.print("   ");
		for(int i = 0; i<10; i ++) {
			System.out.print(i + " ");
		}
		System.out.print("\n");
		System.out.print("  ");
		for(int i = 0; i<20; i ++) {
			System.out.print("-");
		}
		System.out.print("\n");
		for (int row = 0; row < guesses.length; row++) {
			System.out.print(row + "| ");
			for (int col = 0; col < guesses[row].length; col++) {
				System.out.print(guesses[row][col] + " ");
			}
			System.out.println();
		}
		System.out.print("\n");
	}
	
	
	// changes the guest map 
	public void updateGuesses(int row, int col, String s) {
		guesses[row][col] = s;
	}

	// checks if the point that the head of the ship goes has enough room next to it in one direction for the rest of the ship
	public boolean isValid(int row, int col, int size) {
		boolean period = false;
		boolean b = false;
		boolean canPlace = false;
		if (map[row][col] == ".") {
			period = true;
		}
		if (checkUp(size, row, col) || checkDown(size, row, col) || checkLeft(size, row, col) || checkRight(size, row, col)) {
			canPlace = true;
		}
		if (period && canPlace) {
			b = true;
		}
		return b;
	}
	
	public boolean checkUp(int num, int roww, int coll) {
		boolean b = false;
		int spaceCounter = 0;
		for (int row = 0; row < map.length; row++) {
			if (map[row][coll] == "." && row >= roww-num && row<roww) {
				spaceCounter ++;		
			}
		}
		if (spaceCounter == num) {
			b = true;
		}
		return b;	
		
	}
	
	public boolean checkDown(int num, int roww, int coll) {
		boolean b = false;
		int spaceCounter = 0;
		for (int row = 0; row < map.length; row++) {
			if (map[row][coll] == "." && row <= roww+num && row>roww) {
				spaceCounter ++;		
			}
		}
		if (spaceCounter == num) {
			b = true;
		}
		return b;	
		
	}
	
	public boolean checkLeft(int num, int roww, int coll) {
		boolean b = false;
		int spaceCounter = 0;
		for (int col = 0; col < map.length; col++) {
			if (map[roww][col] == "." && col >= coll-num && col<coll) {
				spaceCounter ++;		
			}
		}
		if (spaceCounter == num) {
			b = true;
		}
		return b;	
		
	}
	
	public boolean checkRight(int num, int roww, int coll) {
		boolean b = false;
		int spaceCounter = 0;
		for (int col = 0; col < map.length; col++) {
			if (map[roww][col] == "." && col <= coll+num && col>coll) {
				spaceCounter ++;		
			}
		}
		if (spaceCounter == num) {
			b = true;
		}
		return b;	
		
	}
	
	// places the carrier on the map
	public void carrier(int row, int col, int direction) {
		if (direction == 1) {
			for(int i = 0; i<5; i ++) {
				map[row-i][col] = "1";
			}
		} if (direction == 2) {
			for(int i = 0; i<5; i ++) {
				map[row+i][col] = "1";
			}
		} if (direction == 3) {
			for(int i = 0; i<5; i ++) {
				map[row][col-i] = "1";
			}
		} if (direction == 4) {
			for(int i = 0; i<5; i ++) {
				map[row][col+i] = "1";
			}
		}
	}

	public void battleship(int row, int col, int direction) {
		if (direction == 1) {
			for(int i = 0; i<4; i ++) {
				map[row-i][col] = "2";
			}
		} if (direction == 2) {
			for(int i = 0; i<4; i ++) {
				map[row+i][col] = "2";
			}
		} if (direction == 3) {
			for(int i = 0; i<4; i ++) {
				map[row][col-i] = "2";
			}
		} if (direction == 4) {
			for(int i = 0; i<4; i ++) {
				map[row][col+i] = "2";
			}
		}
		
	}
	
	public void cruiser(int row, int col, int direction) {
		if (direction == 1) {
			for(int i = 0; i<3; i ++) {
				map[row-i][col] = "3";
			}
		} if (direction == 2) {
			for(int i = 0; i<3; i ++) {
				map[row+i][col] = "3";
			}
		} if (direction == 3) {
			for(int i = 0; i<3; i ++) {
				map[row][col-i] = "3";
			}
		} if (direction == 4) {
			for(int i = 0; i<3; i ++) {
				map[row][col+i] = "3";
			}
		}
		
	}
	
	public void submarine(int row, int col, int direction) {
		if (direction == 1) {
			for(int i = 0; i<3; i ++) {
				map[row-i][col] = "4";
			}
		} if (direction == 2) {
			for(int i = 0; i<3; i ++) {
				map[row+i][col] = "4";
			}
		} if (direction == 3) {
			for(int i = 0; i<3; i ++) {
				map[row][col-i] = "4";
			}
		} if (direction == 4) {
			for(int i = 0; i<3; i ++) {
				map[row][col+i] = "4";
			}
		}
		
	}
	
	public void destroyer(int row, int col, int direction) {
		if (direction == 1) {
			for(int i = 0; i<2; i ++) {
				map[row-i][col] = "5";
			}
		} if (direction == 2) {
			for(int i = 0; i<2; i ++) {
				map[row+i][col] = "5";
			}
		} if (direction == 3) {
			for(int i = 0; i<2; i ++) {
				map[row][col-i] = "5";
			}
		} if (direction == 4) {
			for(int i = 0; i<2; i ++) {
				map[row][col+i] = "5";
			}
		}
		
	}
	
	
	
	
	
	/*
	
	sample board map 
	11111.....
	..........
	...2222...
	..........
	..333.....
	....444...
	.........5
	.........5
	..........
	..........
	
	*/
	
	/*
	
	sample guess map 
	..........
	...oo.....
	..........
	..........
	....xxx...
	..........
	..........
	..........
	..........
	..........
	
	*/
	
	
	

}
