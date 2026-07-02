/*

Program: ternarySearchLocations.java          Last Date of this Revision: May 14, 2025

Purpose: Demonstrates ternary search on a randomly generated array by first sorting
it and then allowing user to search for specific numbers. 

The position of a number is based on an index system starting from 1 rather than 0.

Author: Aryan K, 
School: CHHS
Course: Computer Programming 30
 
*/

package Mastery.TernarySearch;

import java.util.Random;
import java.util.Scanner;
import Skillbuilders.binarySearch.Search;
import Skillbuilders.binarySearch.intSorts;

public class ternarySearchLocations {
    public static void main(String[] args) {
        Random rand = new Random();
        Scanner input = new Scanner(System.in);
        
        // Generate random array
        int[] list = new int[50];//enter a different number for a larger or smaller set.
        for (int i = 0; i < list.length; i++) {
            list[i] = rand.nextInt(100);
        }
        
        // Sort the array
        ternaryMergeSort sort = new ternaryMergeSort();
        sort.mergeSort(list);
        
        // Display sorted array
        for (int i = 0; i < list.length; i++) {
            System.out.print(list[i] + " ");
        }
        System.out.print("\n");
        
        // Get search target from user
        System.out.println("Enter a number to search for: ");
        int numToFind = input.nextInt();
        
        // Perform ternary search
        Searches searches = new Searches();
        int location = searches.ternarySearch(list, 0, list.length - 1, numToFind);
        
        // Display results
        if (location < 0) {
            System.out.println("The number does not exist in the list"); //location not found
        } else {
            System.out.println("Number at position: " + (location + 1));
        }
    }
}

/* Screen Dump

Test Case 1 (list of 10):

6 10 16 16 19 31 33 52 74 96 
Enter a number to search for: 
33
Checking position at midpoint 1: 3
Checking position at midpoint 2: 6

Number at position: 7

Test Case 2 (list of 30):

0 4 10 16 17 30 33 37 44 46 52 53 55 57 58 62 64 66 66 69 69 72 80 82 85 86 88 96 97 99 
Enter a number to search for: 
97
Checking position at midpoint 1: 9
Checking position at midpoint 2: 20

Checking position at midpoint 1: 16
Checking position at midpoint 2: 27

Checking position at midpoint 1: 19
Checking position at midpoint 2: 29

Checking position at midpoint 1: 16
Checking position at midpoint 2: 26

Checking position at midpoint 1: 18
Checking position at midpoint 2: 28

Number at position: 29


Test Case 3 (list of 50):

0 0 1 2 3 10 10 16 24 26 26 29 31 32 32 35 37 38 43 44 52 52 54 57 60 63 64 65 66 68 68 71 72 74 77 78 79 79 80 81 87 88 89 89 89 91 93 96 96 98 
Enter a number to search for: 
93
Checking position at midpoint 1: 16
Checking position at midpoint 2: 33

Checking position at midpoint 1: 27
Checking position at midpoint 2: 44

Checking position at midpoint 1: 31
Checking position at midpoint 2: 48

Checking position at midpoint 1: 26
Checking position at midpoint 2: 42

Checking position at midpoint 1: 30
Checking position at midpoint 2: 46

Number at position: 47

 
 */