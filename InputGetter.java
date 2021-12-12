import java.util.Scanner;

public class InputGetter {
	private Scanner sc;
	
	public InputGetter(){
		sc = new Scanner(System.in);
	}
	
	public String inputWord(String prompt) {
		System.out.print(prompt + "\n>");
		String word = sc.next();
		sc.nextLine(); 
		return word;
	}
	
	public int inputInt(String prompt, int minValue, int maxValue) {

		int number = inputInt(prompt);
		while (!(number >= minValue && number <= maxValue))
		{
			System.out.println("Please enter a valid number in the range (" + minValue + " and " + maxValue + "): ");
			number = inputInt(prompt);
		}
		return number;
	}
	
	public int inputInt(String prompt) {
		boolean gotInt = false;
		int num = 0;
		while(!gotInt){
			System.out.println (prompt);
			if(!sc.hasNextInt()){
				String garbage = sc.nextLine();
				System.out.println(garbage + " was not an int. Please enter a whole number");

			}
			else{
				gotInt = true;
				num = sc.nextInt();
				sc.nextLine();//clear
			}
		}


		return num;
	}

}
