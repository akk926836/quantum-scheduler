/*

Program: numDigits.java          Last Date of this Revision: May 14, 2025

Purpose: Provides a recursive method to count the number of digits in an integer,
handling both positive and negative numbers by using absolute value.

Author: Aryan K, 
School: CHHS
Course: Computer Programming 30
 
*/

package Mastery.NumDigits;

public class numDigits {
    /**
     * Recursively counts the number of digits in an integer
     * @param num The integer to analyze (can be positive or negative)
     * @return The count of digits in the number
     */
    public int findDigits(int num) {
        int absValue = Math.abs(num);  // Handle negative numbers
        
        // Base case: single-digit number
        if (absValue < 10) {
            return 1;
        }
        // Recursive case: remove last digit and recurse
        else {
            return 1 + findDigits(absValue / 10);
        }
    }
}