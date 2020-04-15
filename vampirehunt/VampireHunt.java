package vampirehunt;
/*************************************************

	File: VampireHunt.java
 	By: Kim, Mark
	Date: 10/18/2018

	Compile: javac VampireHunt.java
	Usage: java VampireHunt
	System: Windows 10

	Description: This program is a video game where the player is a vampire.
	The program assigns a random number of bloodpoints (health) from 5 to 10
	and a random location for a victim along an x and y axis (0 to 9).
	The user is asked if s/he wants to cheat.  If they decide to cheat, the
	victim's location is revealed.  After displaying the initial number of
	bloodpoints that the player has, the game asks the player to guess target
	coordinates.  If the player guesses right, the player wins and the program
	stops.  If the player guesses wrong, the distance from the target is
	displayed and a vampire hunter has a chance to hit you with an arrow.
	There is an equal chance of either being missed, grazed (1 bloodpoint loss),
	or hit (2 bloodpoint loss).  The player's remaining bloodpoints are displayed
	and the game continues.  If the player loses all his/her bloodpoints, the
	game ends.

*************************************************/

// import Scanner utility
import java.util.Scanner;

// declare class
public class VampireHunt {

	//declare main method
	public static void main(String[] args)  {
		
		// initialize Scanner object
		Scanner input = new Scanner(System.in);
		
		// initialize variables
		int bloodpoints = myRand(5, 10); // random bloodpoints from 5-10
		// victim's random location along x-axis
		int victimX = myRand(0, 9); 
		// victim's random location along y-axis
		int victimY = myRand(0, 9); 
		
		/* 
		 * Welcome user and ask user if they want to cheat
         * if user cheats, display victim coordinates
		 * display user's bloodpoints
		 */
		print("Welcome to the vampire hunt game!\n");
		print("Would you like to cheat? (1 for yes, 0 for no): ");
		int cheat = input.nextInt();
		if ( cheat == 1 )
		  print("Victim at " + victimX + " " + victimY + "\n");
		print("You start with " + bloodpoints + " blood points.\n\n");
		
		/* 
		 * Begin for loop (with a round counter) for the game
		 * Prompt user to enter attack coordinates
		 */
		for (int i = 1; bloodpoints > 0; i++)  {
			print("ROUND " + i + "\n");
			print("Enter your target x and y-coordinates (both 0-9): ");
			int targetX = input.nextInt();
			int targetY = input.nextInt();
			
			// End game if user guesses coordinates correctly
			if (targetX == victimX && targetY == victimY) {
				print("You bit your victim! S/he is now a vampire.");
				System.exit(0);
			}
			
			// Keep game running if user guesses coordinates incorrectly
			else {
				int hit = myRand(0, 2); // random number to determine hit
				bloodpoints -= hit; // update bloodpoints from hit
				
				// distance of guess from victim (calls findDistance method)
				System.out.printf("You are %.2f units from your victim.\n", 
						findDistance(targetX, targetY, victimX, victimY));
				
				hitDialog(hit); // displays result of hunter attack on player
				bloodpointDialog(bloodpoints); // display current bloodpoints
				
			}
		}
		input.close();
	}
	
	/*************************************************
	 * 
	 * void bloodpointDialog(int bp)
	 * 
	 * no return
	 * evaluates current bloodpoints and displays current bloodpoint
	 * count accordingly.
	 * 
	 *************************************************/
	
	public static void bloodpointDialog(int bp) {
		
		if (bp < 1)
			print("Sorry, you were shot too many times. Game Over.");
		else if (bp < 2)
			print("You only have 1 blood point left. No pressure.\n\n");
		else
			System.out.printf("You have %d blood points.\n\n", bp);
		
	}
	
	/*************************************************
	 * 
	 * void hitDialog(int h)
	 * 
	 * no return
	 * evaluates result of hunter attack and displays
	 * dialog accordingly
	 * 
	 *************************************************/
	
	public static void hitDialog(int h) {
		
		if (h < 1)
			print("Lucky you! The arrows miss you!\n");
		else if (h < 2)
			print("An arrow grazes you. Ouch.\n");
		else
			print("Oh no! You were hit by an arrow!\n");
	
	}
	
	/*************************************************
	 * 
	 * int myRand(int low, int high)
	 * 
	 * returns a random integer from low to high (inclusive)
	 * 
	 *************************************************/
	
	public static int myRand(int low, int high) {
	
	   int result = low + (int)((high - low + 1) * Math.random());
	   return result;
	
	}
	
	/*************************************************
	 * 
	 * double findDistance(int x1, int y1, int x2, int y2)
	 * 
	 * returns the distance between two points (x1, y1) and
	 * (x2, y2)
	 * uses pythagorean theorem
	 * 
	 *************************************************/
	
	public static double findDistance(int x1, int y1, int x2, int y2) {
	
	   double result = Math.sqrt((Math.pow((x1 - x2), 2)) 
			   + (Math.pow((y1 - y2), 2)));
	   return result;
	
	}
	
	/*************************************************
	 * 
	 * void print(Object x)
	 * 
	 * no return
	 * method to shorten System.out.print() to print()
	 * 
	 *************************************************/
	
	public static void print(Object x) {
	
	   System.out.print(x);
	
	}
	
}