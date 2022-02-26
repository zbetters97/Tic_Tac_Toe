import java.util.Scanner;

public class Instructions {
	
	static Scanner input = new Scanner (System.in);
	
	public static void instructions() {
		
		System.out.println("\nABOUT:\nThis game is called Tic Tac Toe. "
				
				+ "This is a computer only version of the game 'Tic Tac Toe.'\n"
				+ "Players are presented with a 3x3 board. Each board spot is assigned a number.\n"
				+ "Players will take turns entering their assigned symbol ('X' or 'O') onto the board.\n"
				+ "If, at any point in the game, a straight line can connect three of the same symbol "
						+ "than the player with that symbol wins.\n"
						+ "For example, 3 X's across the middle results in a victory.\n"
				+ "If all 9 slots on the board are filled without this happening, then the game is over without a winner\n\n"
			
				+ "ONE PLAYER MODE:\nThe user goes first and types in a number from 1-9 to decide "
					+ "which slot their symbol goes in (the player to go first will always use symbol 'X').\n"
				+ "After the player decides, the computer will perform the same action.\n"
				+ "Both the player and the computer will swap turns until either "
					+ "three sybmols align or all slots are filled.\n\n"
			
				+ "TWO PLAYER MODE:\nThe user to go first is decided by the players themselves.\n"
				+ "Each player will take a turn entering a number between 1-9 to fill the slots.\n"
				+ "Whoever aligns 3 of their their symbols wins, and if all slots are "
					+ "filled without this happening, it's a draw.\n"
				
				+ "\nHIT THE ENTER KEY TO EXIT INSTRUCTIONS");
		
		input.nextLine();
	}
}