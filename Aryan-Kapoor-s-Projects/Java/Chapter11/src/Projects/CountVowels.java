/*

Program: CountVowels.java          Last Date of this Revision: March 31, 2025

Purpose: An application that counts the total number of vowels in a file.

Author: Aryan K, 
School: CHHS
Course: Computer Programming 30
 

*/
package Mastery;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class CountVowels {
	
		public static void main(String[] args) 
		{
	       File textFile;
	       FileReader in;
	       BufferedReader readFile;
	       String fileName;
	       String lineInFile, lowercaseText, letter;
	       int vowelSum = 0;
	       
	       Scanner input = new Scanner(System.in);

	       /* prompt the user for the name of the file */
	       System.out.println("Enter the File Name: ");
	       fileName = input.nextLine();
	       
	       input.close();

	       /* count the vowels in the file */
	       try {
	                //new File object required
	    	   		textFile = new File(fileName);
	    	   		
	    	   		if(!textFile.exists()) 
	    	   		{
	    	   			System.out.println("File Creation was Unsuccessful.");
	    	   			return;
	    	   		}
	    	   			
	    	   		
	                //new File reader object required
	    	   		in = new FileReader(textFile);
	                //new BufferedReader object required
	    	   		readFile = new BufferedReader(in);
	    	   		
	                //read the lines from the file
	                    //as lines are read convert them to lower case
	                    //iterate through the lower case text
	    	   			//using the String class 
	                            //check if each character traverse through is a vowel
	                            //update total vowels
	    	   		while((lineInFile = readFile.readLine()) != null) 
	    	   		{
	    	   			lowercaseText = lineInFile.toLowerCase();
	    	   			
	    	   			for(int i = 0; i < lowercaseText.length(); i++ ) 
	    	   			{
	    	   				letter = String.valueOf(lowercaseText.charAt(i));
	    	   				if("aeiou".contains(letter))
	    	   				{
	    	   					vowelSum += 1;
	    	   				}
	    	   			}
	    	   			
	    	   		}
	    	   		
	    	   		//user wants to know the number of vowels inside a file name
	    	   		System.out.println("The total number of vowels are: " + vowelSum);
	    	   		
	    	   		//close BufferedReader object
	    	   		readFile.close();
	    	   		//close FileReader object 
	    	   		in.close();           
	                
	            }
	       
	       catch (FileNotFoundException e) //catch exceptions
	       {
	    	   System.out.println("File could not be found.");
	       } 
	       catch (IOException e) 
	       {
	    	   System.err.println("IO exception: " + e.getMessage());
	       }
		}
}
/* Screen Dump

Enter the File Name: 
C:\Users\Aryan Kapoor\git\cs30p32025-ak0122\Chapter11\src\Mastery\CountVowelsText
The total number of vowels are: 16

 
 */