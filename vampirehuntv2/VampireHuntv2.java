package vampirehuntv2;
/*************************************************
 * File: VampireHunt2.java
 * By: Mark Kim
 * 		built from v2skeleton.java provided
 * Date: 12/12/2018
 * 
 * Compile: javac VampireHunt2.java
 * Usage: java VampireHunt2
 * System: Windows 10
 * 
 * Description: This program is a vampire hunt game
 * that uses a 2 dimensional array as a game field.
 * The user inputs the coordinates for the vampire
 * and coordinates for the human. The human moves
 * randomly in each direction. The goal is for the
 * user to move to the human and bite him/her.  The
 * user can control the movement of the vampire by
 * choosing 1 for left, 2 for down, 3 for up, and 4
 * for right.
 * 
 *************************************************/
        
import java.util.Scanner;
public class VampireHuntv2 {
    public static void main(String [] args) {
	Scanner input = new Scanner(System.in);
	char [][] grid = new char [10][10];

	// initialize vampire
	System.out.print("Enter (i, j) for vampire: ");
	int newI = input.nextInt();
	int newJ = input.nextInt();
	Creature vampire = new Creature('V', newI, newJ);

	// initialize human
	System.out.print("Enter (i, j) for human: ");
	int newIH = input.nextInt();
	int newJH = input.nextInt();
	Creature human = new Creature('H', newIH, newJH);

	// check whether human can move
	System.out.print("Would you like human to move? (0: no, 1: yes): ");
	int isMove = input.nextInt();
	human.setMoving(isMove); // sets whether human can move from input

	// update and display grid
	clearGrid(grid); // clears grid to be updated with inputted values
	vampire.display(grid); // calls method to place vampire in array
	human.display(grid); // call method to place human in array
	drawGrid(grid); // draws out grid from array
	System.out.println("Vampire at: " + vampire.getI() +
			   " " + vampire.getJ()); // display vampire coordinates
	System.out.println("Human at: " + human.getI() +
			   " " + human.getJ()); // display human coordinates

	// get next user command
	System.out.print("Enter command (0 to quit): ");
	int command = input.nextInt(); // input command
	
	while (command != 0) { // while not quit
	    clearGrid(grid); // clear grid for next move
	    vampire.update(command); // update vampire location from command
	    vampire.display(grid); // place vampire in array
	    // if vampire and human are on same square,
	    // vampire bites human, game ends
	    if (sameSquare(vampire, human)) {
	    	System.out.print("You bit the Human. Game Over!");
	    	System.exit(0);
	    }
	    // if game does not end
	    else {
	    // human makes random move
	    	human.update();  // random move from no parameter method
	    	// display human on grid
	    	human.display(grid); // place vampire in array
	    	// if vampire and human are on same square,
	    	// human sacrificed himself, game ends
	    	if (sameSquare(vampire, human) == true) {
	    		System.out.print("The Human sacrificed him/herself. Game Over!");
	    		System.exit(0);
	    	}
	    }
	    drawGrid(grid); // draw grid from array
	    System.out.println("Vampire at: " + vampire.getI() +
			       " " + vampire.getJ()); // display vampire coordinates
	    System.out.println("Human at: " + human.getI() +
				   " " + human.getJ()); // display human coordinates
	    System.out.print("Enter command (0 to quit): ");
	    command = input.nextInt();
	    }
		input.close();
	} // while (command != 0)
    
    /**********************************************
     * clearGrid method
     * @param g
     * 
     * resets grid to blank with no creature
     * nested for loop to enter '.' character for all
     * array fields
     **********************************************/
    public static void clearGrid(char [][] g) {
    	for (int i=0; i<g.length; i++) {
    		for (int j=0; j<g.length; j++) {
    			g[i][j] = '.';
    		}
    	}
    }
    
    /**********************************************
     * drawGrid method
     * @param g
     * 
     * draw array as a grid
     * nested for loop to draw grid, line by line
     **********************************************/
    public static void drawGrid(char [][] g) {
	System.out.println("0123456789");
		for (int i=0; i<g.length; i++) {
			for (int j=0; j<g.length; j++) {
				System.out.print(g[i][j]);
			}
			System.out.println(i);
		}
    }

    /**********************************************
     * sameSquare method
     * @param c1, c2
     * 
     * check to see if both objects have the same
     * i and j coordinates
     **********************************************/
    public static boolean sameSquare(Creature c1, Creature c2) {
	// if c1 and c2 have identical (i, j) coordinates, return true
    	if (c1.getI() == c2.getI() && c1.getJ() == c2.getJ())
    		return true;
	// else return false
    	else return false;
    }
}

class Creature {
    // display character for creature
    private char pic;

    // (i, j) coordinates for creature
    private int i = 0;
    private int j = 0;

    private boolean canMove = true; // can creature move?

  
    Creature(char c, int nI, int nJ) {
	// set display character to c
	pic = c;
	// set position to (nI, nJ)
	i = nI;
	j = nJ;
    }

    public void setIJ(int nI, int nJ) {
	// set (i, j) coordinates for creature
	// if new coordinates are invalid, leave current position unchanged
    	if (nI >= 0 && nI <= 9 && nJ >= 0 && nJ <= 9) {
    		i = nI;
    		j = nJ;
    	}
    	else {
    		System.out.println("Invalid position change; position set to (" + i + ", " + j + ")");
    		return;
    	}
    }

    public void setMoving(int n) {
    	// set canMove variable
    	if (n == 0)
    		canMove = false;
    	else
    		canMove = true;
    }
    
    // coordinates private, get method required
    public int getI() {
	return i;
    }
    
    // coordinates private, get method required
    public int getJ() {
	return j;
    }

    public void update() {
    // no parameter method for random movement
    // random position update; call update with random argument 1-4
    	int direction = 1 + (int)(Math.random() * 4);
    	update(direction); // update with direction
    }

    public void update(int c) {
	// if canMove, update position according to user command c
    	int nI = i, nJ = j;
    	if (canMove == false) { // check if canMove == false
    		return;
    	}
    	else { // movement statement if canMove == true
    		switch (c) { // switch statement for direction from c
    		case 1: setIJ(i, --nJ); break; // 1: j-- (left)
    		case 2: setIJ(++nI, j); break; // 2: i++ (down)
    		case 3: setIJ(--nI, j); break; // 3: i-- (up)
    		case 4: setIJ(i, ++nJ); break; // 4: j++ (right)
    		}
    	}
    }
    
    // replace '.' char with pic of creature at location i,j
    public void display(char [][] g) {
	g[i][j] = pic;
    }
    
}