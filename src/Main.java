//Matt Fein
//Project 1
//Algorithms

//Extra Credit:
//I added the ability to clear the tree and the ability to start a fresh tree
//The tree can be printed at anytime with a persistent menu
//Lastly the trees degree can be changed in the node class with variable K

import java.util.Scanner;

public class Main {
	private static final int PRINT = 1;
	private static final int SEARCH = 2;	// Used for menu.
	private static final int INSERT = 3;
	private static final int CLEAR = 4;
	private static final int NEW = 5;
	
    private int quit;
    private static Tree tree;

    public static void main(String[] args) {
        // Construct tree

        //btree.createDefaultTree();
        tree = new Tree();
        userEnterKey();

        // while (user not exiting)
        // Prompt the user for a command (insert/delete/...)
        // If insert
        // Get the number to be inserted:
        //btree.putValue(insertVal);
        // else if delete
        // else if search
        // else if exit
        //isExiting = true;
        // end while
        //userEnterKey();
    }

    public static void userEnterKey() {
        boolean boolSwitch = true;
        while (boolSwitch == true) {
            System.out.println("\nType your selection \n1:Print Tree   2:Search for a Value  3:Insert a Number 4:Clear Tree 5:New Tree");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            if (choice == PRINT) {
                System.out.print('\n');
                tree.printTree();
            }

            if (choice == SEARCH) {
                System.out.print("Enter the the value you are looking for\n");
                System.out.print("Find: ");
                Scanner insertScanner = new Scanner(System.in);
                int searchVal = insertScanner.nextInt();
                tree.defSearch(searchVal);
            }

            if (choice == INSERT) {
                System.out.print("Enter the the value you want to insert\n");
                System.out.print("Insert: \n");
                Scanner insertScanner = new Scanner(System.in);
                int insertVal = insertScanner.nextInt();
                tree.defAddValue(insertVal);

            }
            if (choice == CLEAR) {
                tree.clearTree();
                System.out.println("Tree Cleared!");
               
            }
            if(choice == NEW){
                tree.clearTree();
                System.out.print("Enter the the value you want to insert\n");
                System.out.print("Insert: ");
                Scanner insertScanner = new Scanner(System.in);
                int insertVal = insertScanner.nextInt();
                tree.defAddValue(insertVal);
                
            }
        }
    }
}
