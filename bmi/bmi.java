package bmi;

/*************************************************

	File: Bmi.java
 	By: Kim, Mark
	Date: 09/13/2018

	Compile: javac Bmi.java
	Usage: java Bmi
	System: Windows 10

	Description: This program asks for the user to input their height and weight, 
	then outputs the user's BMI and interprets whether the user is overweight or not.

*************************************************/

import java.util.Scanner; // This is the scanner package to allow for user input

public class bmi
{
	// This is the declaration of the main method
	public static void main(String[] args)
	{
		// Initialize Scanner input
		Scanner input = new Scanner(System.in);

		// Ask for user to input height in feet and inches
		System.out.println("Please enter your height in feet and inches:");
		int height_part_ft = input.nextInt(); // portion of height in feet
		int height_part_in = input.nextInt(); // portion of height in inches

		// Ask for user to input weight
		System.out.println("Please enter your weight in pounds:");
		int weight = input.nextInt();
		input.close();

		// Calculate height in inches only for BMI calculation purposes
		int height_in = height_part_ft * 12 + height_part_in;

		// Declare the constant for BMI, then calculate
		final float a_constant = 703.0f;
		double bmi = a_constant * weight / (Math.pow(height_in, 2));

		// Display BMI result
		System.out.println("BMI = " + bmi);

		// Display interpretation of BMI
		if (bmi >= 25)
			System.out.println("You are overweight.");
		else
			System.out.println("You are not overweight.");
	}
}