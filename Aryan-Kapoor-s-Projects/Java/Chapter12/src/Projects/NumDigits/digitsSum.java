/*

Program: digitsSum.java          Last Date of this Revision: May 14, 2025

Purpose: Main application class that provides a user interface for counting digits
in an integer using the numDigits class functionality.

Author: Aryan K, 
School: CHHS
Course: Computer Programming 30
 
*/

package Mastery.NumDigits;

import java.util.Scanner;

public class digitsSum {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int userNum; // variable for number given by user.
        numDigits finder = new numDigits(); //object that will be used to calculate number of digits.
        
        // Display program header
        System.out.println("============================================== DIGITS SUM ==============================================");
        System.out.println("Hello User. This program finds and returns the total number of digits for a given integer value.");
        
        // Get user input
        System.out.println("\nPlease enter an integer: ");
        userNum = input.nextInt();
        
        // Display results
        System.out.println("Total Number of Digits in " + userNum + ": " + 
                          finder.findDigits(userNum) + " Digits");
    }
}

/* Screen Dump

Test Case 1:

============================================== DIGITS SUM ==============================================
Hello User. This program finds and returns the total number of digits for a given integer value.

Please enter an integer: 
89084
Total Number of Digits in 89084: 5 Digits

Test Case 2:

============================================== DIGITS SUM ==============================================
Hello User. This program finds and returns the total number of digits for a given integer value.

Please enter an integer: 
1
Total Number of Digits in 1: 1 Digits

Test Case 3:

============================================== DIGITS SUM ==============================================
Hello User. This program finds and returns the total number of digits for a given integer value.

Please enter an integer: 
1000000
Total Number of Digits in 1000000: 7 Digits

 
 */