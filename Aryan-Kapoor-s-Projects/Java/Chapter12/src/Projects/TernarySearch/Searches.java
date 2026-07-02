/*

Program: Searches.java          Last Date of this Revision: May 14, 2025

Purpose: Implements ternary search algorithm to find elements in a sorted array by
recursively dividing the search space into three parts.

Author: Aryan K, 
School: CHHS
Course: Computer Programming 30
 
*/

package Mastery.TernarySearch;

public class Searches {
    /**
     * Performs ternary search on a sorted array
     * @param items The sorted array to search
     * @param start The starting index of search range
     * @param end The ending index of search range
     * @param goal The value to search for
     * @return Index of found element or -1 if not found
     */
    public static int ternarySearch(int[] items, int start, int end, int goal) {
        if (start > end) {
            return -1;  // Base case: element not found
        } else {
            // Calculate two midpoints dividing array into thirds
            int midIndex1 = (start + end) / 3;
            int midIndex2 = end - (end - start) / 3;
            
            System.out.println("Checking position at midpoint 1: " + midIndex1+1);
            System.out.println("Checking position at midpoint 2: " + midIndex2+1 + "\n");
            
            // Check if either midpoint contains the goal
            if (items[midIndex1] == goal) {
                return midIndex1;
            } else if (items[midIndex2] == goal) {
                return midIndex2;
            }
            
            // Recursively search appropriate third
            else if (items[midIndex1] > goal && items[midIndex2] > 0) {
                return ternarySearch(items, start, midIndex1, goal);
            } else if (items[midIndex1] < goal && items[midIndex2] > goal) {
                return ternarySearch(items, midIndex1 + 1, midIndex2 - 1, goal);
            } else {
                return ternarySearch(items, midIndex2 + 1, end, goal);
            }
        }
    }
}