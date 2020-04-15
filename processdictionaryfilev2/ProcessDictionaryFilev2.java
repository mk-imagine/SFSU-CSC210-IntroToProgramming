package processdictionaryfilev2;
/*************************************************
 * 
 *	File: ProcFilev2.java
 *	By: Kim, Mark
 *	Date: 12/14/2018
 *
 *	Compile: javac ProcFilev2.java
 *	Usage: java ProcFilev2
 *	System: Windows 10
 * 
 *	Description: This program processes a list within
 *	a text file.  It finds all the 3 or 4 letter words
 *	within the file and puts them in corresponding
 *	arrays.  It then outputs the list of words in
 *	two files, one containing the 3 letter words and
 *	the other containing the 4 letter words.
 *
 *************************************************/

import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
public class ProcessDictionaryFilev2 {
	public static void main(String[] args) throws Exception {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter name of dictionary file: ");
		
		// user enters file name
		String fName = input.nextLine();
		
		// close input scanner
		input.close();
		
		// call findWords method for 3 letter words
		String[] w3 = findWords(fName, 3);
		
		// call findWords method for 4 letter words
		String[] w4 = findWords(fName, 4);
		
		// call wordQty method to find the number
		// of entries in the file
		wordQty(fName);
		
		// create file objects to check if file names exist
		File w3file = new File("shorts3.txt");
		File w4file = new File("shorts4.txt");
		
		// check if file names exist
		if (w3file.exists() || w4file.exists()) {
			System.out.println("Files already exist");
			System.exit(1);
		}
		// create and write new files
		PrintWriter w3Output = new PrintWriter("shorts3.txt");
		PrintWriter w4Output = new PrintWriter("shorts4.txt");
		
		// add all elements in w4 array into file "shorts4.txt"
		for (int i = 0; i < w4.length; i++) {
			w4Output.println(w4[i]);
		}
		// add all elements in w3 array into file "shorts3.txt"
		for (int i = 0; i < w3.length; i++) {
			w3Output.println(w3[i]);
		}
		// close w3Output and w4Output objects
		w3Output.close();
		w4Output.close();
	}
	
	/******************************************
	 * wordQty method
	 * @param fileName
	 * @throws Exception
	 * 
	 * Counts number of words in text file
	 ******************************************/
	
	public static void wordQty(String fileName) throws Exception {
		
		// create file object from fileName
		File file = new File(fileName);
		
		// create fileScanner object to scan file object
		Scanner fileScanner = new Scanner(file);
		
		// scan through fileScanner object to count words in file
		int numWords = 0;
		while (fileScanner.hasNextLine()) { // continue until end of file
			fileScanner.nextLine(); // advance to next entry
			numWords++; // count the current entry number
		}
		
		// print the number of entries in the file
		System.out.println("Number of entries in file: " + numWords);
		fileScanner.close(); // close fileScanner object
	}
	
	/******************************************
	 * findWords method
	 * @param fileName
	 * @param wordLength
	 * @return wordList array
	 * @throws Exception
	 * 
	 * assembles an array of words from fileName
	 * matching the length of the word specified
	 * by wordLength
	 ******************************************/
	
	public static String[] findWords(String fileName, int wordLength)
			throws Exception {
		
		// create file object from fileName
		File file = new File(fileName);
		
		// create fileScanner object to scan file object
		Scanner fileScanner = new Scanner(file);
		
		// initialize and declare temporary string, word count,
		// and temporary string array to list words
		String tempWord;
		int numWords = 0;
		String[] tempList = new String[20000];
		
		// scan through fileScanner object to find words
		// matching wordLength
		while (fileScanner.hasNextLine()) { // continue until end of file
			tempWord = fileScanner.nextLine(); // store string value
			
			// check if the string value length matches wordLength
			if (tempWord.length() == wordLength) {
				tempList[numWords++] = tempWord; // put word in array
			}
		}
		fileScanner.close(); // close fileScanner
		
		// initialize and declare array with exact length of list
		String[] wordList = new String[numWords];
		
		// copy all values from tempList to wordList
		for (int i = 0; i < numWords; i++) {
			wordList[i] = tempList[i];
		}
		return wordList; // return array of words matching size
	}
}