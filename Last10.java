// Uma Desai
// COMP 182
// 1/29/24
// Project 01: Last10

import java.util.Scanner;

public class Last10 {
	
	// This array holds the user input of numbers
	static int[] inputArrayNumbers;  
	
	// This number causes the program to stop processing input 
	static int exitNumberValue= -1;  
	
	// This keeps track of the count of entries that were processed
	static int numOfProcessedEntries=0;  
	
	// This is the max entries that will be held in the circular array
	static int maxCircularArrayEntries=10;
	
	// This circular array holds the list of numbers that will be printed
	static int[] circularArray = new int[maxCircularArrayEntries];
	
	public static void main(String args[]) {
        Last10 myClass = new Last10();
        myClass.getUserInput();
        myClass.processInput();
        myClass.printQueue();   
      }
	
	// Gets input from user and creates an array to be processed
	public void getUserInput() {
        Scanner scanner = new Scanner(System.in);

        // Prompts the user to enter numbers
        System.out.println("Enter a list of numbers separated by a space:");
        String inputstring = scanner.nextLine();
        scanner.close();

        // Splits the input string by space
        String[] arrayStrings = inputstring.split("\s+");

        // Converts the string array to an integer array
        inputArrayNumbers = new int[arrayStrings.length];
        for (int i = 0; i < arrayStrings.length; i++) {
            inputArrayNumbers[i] = Integer.parseInt(arrayStrings[i].trim());
        }
    } 

	
	// Gets input from user and creates an array to be processed
	public void processInput() {
		numOfProcessedEntries=0;
        for (int number : inputArrayNumbers) {
        	if (number == exitNumberValue) {
        		return;
        	}
        	else {
        		this.addtoQueue(number);
        		numOfProcessedEntries++;
        	}
        }
	}

	
	// Adds the latest number to the circular array
	public void addtoQueue(int latestNumber) {		
        circularArray[numOfProcessedEntries % maxCircularArrayEntries] = latestNumber;
	}

	
	// Prints the circular array
	public void printQueue() {		
       	// If we have not exceeded the size of the array, just print in order
		if (numOfProcessedEntries <= maxCircularArrayEntries) {
            for (int i = 0; i < numOfProcessedEntries; i++) {
                System.out.print(circularArray[i] + " ");
            } 
    	}
       	else {
       		// We have to find out if any of the first entries were removed and array adjusted
			// If so, we have to find out where the oldest values start
       		int oldestInitialEntryIndex = numOfProcessedEntries % maxCircularArrayEntries;
            for (int i = oldestInitialEntryIndex; i < maxCircularArrayEntries; i++) {
                System.out.print(circularArray[i] + " ");
            }
            // If we had started at the beginning of the array, we would have printed 10
			// So, just exit. This happens if the numbers are an even multiple of array size
            if (oldestInitialEntryIndex == 0) {
	            return;
       		}
       		else {
       			// Prints the starting ones up to the oldestInitialEntryIndex.  
				// These are the ones that had displaced some existing entries and came later than those.
				// Hence these have to printed after the earlier ones.
                for (int i = 0; i < oldestInitialEntryIndex; i++) {
                    System.out.print(circularArray[i] + " ");
                }       			
       		}
       	}
	}


}
