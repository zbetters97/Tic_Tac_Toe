import java.util.ArrayList;
import java.util.Scanner;

public class OnePlayer {
	
	static Scanner input = new Scanner(System.in);
	public static String[][] array;
	public static int choice;
	public static ArrayList<Integer> spots;
	public static int winner;
	
	public static void play() throws InterruptedException {
		
		System.out.println("\nYou have chosen ONE PLAYER MODE");
		
		array = new String[3][3]; //two dimensional array created to hold board values
		int slot = 1; //created to display board values
		
		for(int i = 0; i < array.length; i++) {				
	        for( int j = 0; j < array[i].length; j++) {
	              array[i][j] = "" + slot;  
	              slot++; //assign each spot to numbers from 1-9
	        }    
		}
		
		printBoard();

		spots = new ArrayList<>(); //different array to hold what spots have been entered	
		winner = 0; //0 = no one, 1 = player 1, 2 = computer 
		
		for (int i = 0; i < 9; i++) { //loop for all 9 spots, or until there is a winner
			
			if (i % 2 == 0) //player 1 is even, computer is odd
				playerTurn();			
			else 
				computerTurn();
			
			printBoard();	
			spots.add(choice); //add choices to list to keep track of symbols on board
			
			isWinner(); //ask if there is a winner after each turn
			
			if (winner != 0) //if there is, stop the loop
				break;
		}
		
		if (winner == 0)
			System.out.println("It's a draw!");
		else if (winner == 1)
			System.out.println("Player 1, you are the winner!");
		else if (winner == 2)
			System.out.println("Computer, you are the winner!");
		
		input.nextLine(); input.nextLine();
	}
	
	public static void playerTurn() {
		
		System.out.println("TURN: Player 1");
		choice = input.nextInt();
		
		while (!isValid()) 	//loop until answer is valid 		
			choice = input.nextInt();		
		
		fillBoard("X");
	}
	
	public static void computerTurn() throws InterruptedException {
		
		System.out.println("TURN: Computer");
		
		Thread.sleep(1000); //simulate computer thinking 
		
		if (newSelection() != 0) //if there is a winning spot, choice is that spot
			choice = newSelection();
		
		else {
			choice = 1 + (int) (Math.random() * ((9 - 1) + 1)); //random selection between 1-9
		
			while (spots.contains(choice) || 0 > choice || choice > 9) //same as isValid() but without print statement 			
				choice = 1 + (int) (Math.random() * ((9 - 1) + 1)); 	
		}
		fillBoard("O");
	}
	
	public static boolean isValid() {
		
		if (spots.contains(choice) || 0 > choice || choice > 9) { //if choice previously done, it's not valid
			System.out.println("This is not a valid selection");
			return false;
		}
		else
			return true;	
	}
	
	public static void fillBoard(String value) {
		
		switch (choice) { //1-9 correspond to array slots	
			case (1): array[0][0] = value; break;
			case (2): array[0][1] = value; break;
			case (3): array[0][2] = value; break;
			case (4): array[1][0] = value; break;
			case (5): array[1][1] = value; break;
			case (6): array[1][2] = value; break;
			case (7): array[2][0] = value; break;
			case (8): array[2][1] = value; break;
			case (9): array[2][2] = value; break;					
		}
	}
	
	/** Method for computer player to fill winning spot **/
	public static int newSelection() {
			
		String result = "";
		
		//check for all 8 winning combinations
			
		/** CHECK FOR ACROSS **/
		result = ""; result += array[0][0]; result += array[0][1]; 
		result += array[0][2];
		
		if (canWin(result) && containsDigit(result)) //check if someone can win in next turn...
			return whichNumber(result);			     //by calling the two methods (they both must be true)
		
		result = ""; result += array[1][0]; result += array[1][1]; 
		result += array[1][2]; 
		
		if (canWin(result) && containsDigit(result)) 
			return whichNumber(result);				
		
		result = ""; result += array[2][0]; result += array[2][1]; 
		result += array[2][2]; 
		
		if (canWin(result) && containsDigit(result)) 
			return whichNumber(result);	
		
		/** CHECK FOR DOWN **/
		result = ""; result += array[0][0]; result += array[1][0]; 
		result += array[2][0]; 
		
		if (canWin(result) && containsDigit(result)) 
			return whichNumber(result);	
		
		result = ""; result += array[0][1]; result += array[1][1]; 
		result += array[2][1]; 
		
		if (canWin(result) && containsDigit(result)) 
			return whichNumber(result);	
		
		result = ""; result += array[0][2]; result += array[1][2]; 
		result += array[2][2]; 
		
		if (canWin(result) && containsDigit(result)) 
			return whichNumber(result);	
		
		/** CHECK FOR DIAGONAL **/
		result = ""; result += array[0][0]; result += array[1][1]; 
		result += array[2][2];
		
		if (canWin(result) && containsDigit(result)) 
			return whichNumber(result);	
		
		result = ""; result += array[0][2]; result += array[1][1]; 
		result += array[2][0]; 
		
		if (canWin(result) && containsDigit(result)) 
			return whichNumber(result);	
		
		return 0;
	}
	
	/** Method to check if a player can win on their turn **/
	public static boolean canWin(String result) {
		
		if (result.matches(".*O.*O.*") || result.matches(".*X.*X.*"))
			return true; //if given string contains two symbols, they can win and return true
		else
			return false;
	}
	
	/** Method to check if space to win is empty **/
	public static boolean containsDigit(String result) {
		
	    boolean containsDigit = false;

	    if (result != null && !result.isEmpty()) {
	        for (char c : result.toCharArray()) {
	        	
	            if (containsDigit = Character.isDigit(c)) 
	                break;       
	        }
	    }

	    return containsDigit;
	}
	
	/** Method to check which number space is the winning one **/
	public static int whichNumber(String result) {
	
		if (containsDigit(result)) {		
			for (int i = 1; i <= 9; i++) {				
				for (char c : result.toCharArray()) {
					
		            if (Character.getNumericValue(c) == i) {System.out.println(i);
		            	return i;            }
		        }
			}
		}
		
		return 0;
	}
	
	/** Method to check if a winner has been found **/
	public static void isWinner() {
		
		String result = "";
		
		boolean go = true;
		
		while (go) { //check for all 8 winning combinations
			
			/** CHECK FOR ACROSS **/
			result = ""; result += array[0][0]; result += array[0][1]; 
			result += array[0][2]; if (checkWin(result) != 0) break;
			
			result = ""; result += array[1][0]; result += array[1][1]; 
			result += array[1][2]; if (checkWin(result) != 0) break;
			
			result = ""; result += array[2][0]; result += array[2][1]; 
			result += array[2][2]; if (checkWin(result) != 0) break;
			
			/** CHECK FOR DOWN **/
			result = ""; result += array[0][0]; result += array[1][0]; 
			result += array[2][0]; if (checkWin(result) != 0) break;
			
			result = ""; result += array[0][1]; result += array[1][1]; 
			result += array[2][1]; if (checkWin(result) != 0) break;
			
			result = ""; result += array[0][2]; result += array[1][2]; 
			result += array[2][2]; if (checkWin(result) != 0) break;
			
			/** CHECK FOR DIAGONAL **/
			result = ""; result += array[0][0]; result += array[1][1]; 
			result += array[2][2]; if (checkWin(result) != 0) break;
			
			result = ""; result += array[0][2]; result += array[1][1]; 
			result += array[2][0]; if (checkWin(result) != 0) break;
			
			if (checkWin(result) == 0)
				break;
		}
		
		winner = checkWin(result);
	}
	
	public static int checkWin(String result) {
		
		if (result.equals("XXX")) //if result adds up to all X's or all O's, there is a winner
			return 1;
		else if (result.equals("OOO"))
			return 2;
		else
			return 0;
	}
	
	public static void printBoard() { //print all values of array
		
		System.out.println();
		
		for (int i = 0; i < array.length; i++) {

	        for (int j = 0; j < array[i].length; j++) 
	            System.out.print(array[i][j] + "\t");		    
	        	        
	        System.out.println("\n");
		}
	}
}