package cellautomaton;

/*************************************************
 * File: CellAutomaton.java
 * By: Mark Kim
 * Date: 11/15/2018
 * 
 * Compile: javac CellAutomaton.java
 * Usage: java CellAutomaton
 * System: Windows 10
 * 
 * Description: This program calculates and displays a one-dimensional
 * cellular automaton.  The user inputs the number of cells to model,
 * the number of time steps (iterations), and the starting parameters
 * (initial occupied cells) of the model.  From this, the program
 * calculates how the cells propagate according to rules provided in
 * the assignment.  The new state of the model is stored in a temporary
 * array, then copied into the original array, then displayed.
 * 
 *************************************************/

import java.util.Scanner;

public class CellAutomaton {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		/* Prompt user for parameters of program
		 * (number of cells, time steps, and occupied cells)
		 */
		
		print("Welcome to the cellular automaton simulation!\n");
		print("Enter number of cells (<= 80): ");
		int dataSize = input.nextInt(); // number of cells
		int[] dataSet = new int[dataSize]; // array of cells
		print("Enter number of time steps: ");
		int timeSteps = input.nextInt(); // number of iterations/time steps
		print("Enter the index of occupied cells (negative index to end): ");
		
		/* input for loop to set initial values for dataSet
		 * provided by user.
		 */
		for (int i = 0; i < dataSize; i++) {
			int j = input.nextInt();
			if (j < 0) // breaks loop if entry is -1
				break;
			dataSet[j] = 1;
		}
		input.close();
		print("\n"); // prints space before cells are displayed
		for (int i = 0; i < dataSize; i++) { // loop to print column index numbers
			print(i % 10);
		}
		print("\n"); // next line
		for (int i = 0; i <= timeSteps; i++) { // loop to display cells and update
			displayCells(dataSet); // call to displayCells method
			updateCells(dataSet); // call to updateCells method
			
		}
	}
	
	/*******************************************
	 * displayCells method
	 * 
         * @param a
         * 
	 * displays ' ' for a cell with value 0
	 * and '#' for a cell with any other value
	 *******************************************/
	
	public static void displayCells(int a[]) {
		// for loop to display entire array
		for (int i = 0; i < a.length; i++) {
			// evaluate array and display # or " " appropriately
			print(a[i] != 0 ? "#" : " ");
		}
		print("\n"); // next line
	}
	
	/******************************************
	 * updateCells method
	 * 
         * @param a
         * 
	 * evaluates array with rules method,
	 * then populates temp array with new values,
	 * and updates the initial array from temp array.
	 * end values are not updated
	 ******************************************/
	
	public static void updateCells(int a[]) {
		int[] temp = new int[a.length]; // declare temporary array
		// loop to apply rules method to entire array
		for (int i = 1; i < a.length - 1; i++) {
			// store new values to temporary array
			temp[i] = rules(a[i-1], a[i], a[i+1]);
		}
		// loop to update original array with temporary array
		for (int i = 0; i < a.length; i++) {
			a[i] = temp[i];
		}
	}
	
	/******************************************
	 * rules method
	 * 
         * @param left
         * @param mid
         * @param right
         * @return
         * 
	 * evaluates values left, mid, and right
	 * and returns new value as rules dictate
	 * seperate method to allow easy rule changes
	 ******************************************/
	
	public static int rules(int left, int mid, int right) {		// L,M,R,result
		if (left == 1 && mid == 1 && right == 1) return 0; 		// 1,1,1,0
		else if (left == 1 && mid == 1 && right == 0) return 1; // 1,1,0,1
		else if (left == 1 && mid == 0 && right == 1) return 1; // 1,0,1,1
		else if (left == 1 && mid == 0 && right == 0) return 0; // 1,0,0,0
		else if (left == 0 && mid == 1 && right == 1) return 1; // 0,1,1,1
		else if (left == 0 && mid == 1 && right == 0) return 1; // 0,1,0,1
		else if (left == 0 && mid == 0 && right == 1) return 1; // 0,0,1,1
		else return 0; 											// 0,0,0,0
	}

	/*****************************************
	 * print method
	 * 
         * @param x
         * 
	 * reduces repetitive typing
	 *****************************************/
	
	public static void print(Object x) {
		System.out.print(x);
		
	}
}
