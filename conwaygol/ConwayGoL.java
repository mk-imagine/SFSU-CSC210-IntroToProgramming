package conwaygol;
/*************************************************
 * File: ConwayGoL.java
 * By: Mark Kim
 * Date: 11/15/2018
 * 
 * Compile: javac ConwayGoL.java
 * Usage: java ConwayGoL
 * System: Windows 10
 * 
 * Description: This program calculates and displays the growth
 * of cellular automatons in two dimensions on a 10 x 10 grid.
 * the user is prompted to enter the initial state of the grid
 * by entering the coordinates of populated cells.  The user is
 * then asked for the number of iterations (time steps) the model
 * is to calculate.  The program then displays the state of the
 * model at each iteration according to a set of rules provided
 * in the homework assignment.
 * 
 *************************************************/

import java.util.Scanner;

public class ConwayGoL {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		print("Please enter the size of the grid i vertical, j horizontal: ");
		int rows = input.nextInt();
		int columns = input.nextInt();
		int[][] grid = new int[rows][columns]; // initialize array
		
		print("Please enter list of (i,j) pairs for populated cells"
				+ " (negative i or j to quit): ");
		for (int i = 0; i < grid.length; i++) { // loop for input
			int row = input.nextInt();
			int column = input.nextInt();
			if (row < 0 || column < 0) break; // ends loop if a negative number is supplied
			grid[row][column] = 1; // changes array to reflect values supplied by user
		}
		print("Enter number of time steps: ");
		int timeSteps = input.nextInt(); // user input of time steps
		input.close();
		
		for (int i = 0; i <= timeSteps; i++) { // loop for time step iteration
			if (i < 1) { // statement to print initial grid
				print("Initial grid:\n");
			}
			else { // statement for subsequent time steps
				print("Time step " + (i));
				print("\n");
			}
			displayGrid(grid); // call to display grid
			print("\n"); // next line
			updateGrid(grid); // call to update grid for next time step
		}
	}
	/*****************************************
	 * displayGrid method
	 * @param a (array)
	 * 
	 * displays the grid including axis values
	 * first loop displays column values
	 * second loop displays cells and row
	 * values
	 *****************************************/
	public static void displayGrid(int[][] a) {
		for (int i = 0; i < a[0].length; i++) { // displays column numbers
			print(i % 10);
		}
		print("\n");
		for (int i = 0; i < a.length; i++) { // loop for row
			for (int j = 0; j < a[i].length; j++) { // loop for column
				print(a[i][j] != 0 ? "#" : " "); // display # if value in cell is 1
			}
			print((i % 10) + "\n"); // display row number and start new line
		}		
	}
	/*****************************************
	 * updateGrid method
	 * @param a (array)
	 * 
	 * finds the value of the sum of the
	 * neighbors to the cell, then updates
	 * the temporary array according to rules
	 * method.  After temporary array is completed,
	 * the initial array is updated from the temp
	 * array.
	 *****************************************/
	public static void updateGrid(int[][] a) {
		int[][] temp = new int[a.length][a[0].length]; // initialize temp array
		for (int i = 1; i < a.length - 1; i++) { // row loop
			for (int j = 1; j < a[i].length - 1; j++) { // column loop
				// find the sum of all the neighbors of the current cell
				int sumNeighbors = a[i-1][j-1] + a[i-1][j] + a[i-1][j+1] + a[i][j-1]
						+ a[i][j+1] + a[i+1][j-1] + a[i+1][j] + a[i+1][j+1];
				// update current cell of temp array with rules method
				temp[i][j] = rules(a[i][j], sumNeighbors);
			}
		}
		// copy temp array to initial array
		for (int i = 0; i < a.length; i++) { // row loop
			for (int j = 0; j < a[i].length; j++) { // column loop
				a[i][j] = temp[i][j]; // copy value
			}			
		}		
	}
	/*****************************************
	 * rules method
	 * @param cell
	 * @param sum
	 * @return 0 or 1
	 * 
	 * returns the new value for a cell, given
	 * the sum of its neighbors according to
	 * the rules provided for this assignment
	 *****************************************/
	public static int rules(int cell, int sum) {
		if (cell == 1) { // rules if cell is occupied
			if (sum <= 1 || sum >= 4) return 0; // neighbors in range, dies
			else return 1; // neighbors outside of range, lives
		}
		else {
			if (sum == 3) return 1; // grows only if neighbors equal exactly 3
			else return 0; // otherwise dies
		}
			
	}
	/*****************************************
	 * print method
	 * 
	 * reduces repetitive typing
	 *****************************************/
	public static void print(Object x) {
		System.out.print(x);
	}
}
