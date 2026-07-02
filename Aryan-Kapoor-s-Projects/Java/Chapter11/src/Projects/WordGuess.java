/*

Program: WordGuess.java          Last Date of this Revision: March 31, 2025

Purpose: A guessing game where you guess a randomly selected word from a file.

Author: Aryan K, 
School: CHHS
Course: Computer Programming 30
 

*/
package Mastery;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class WordGuess 
{
	static ArrayList<Integer> indexList = new ArrayList<>();
	
	public static void main(String[] args) 
	{
		final String FLAG = "!";
		File WordGuessFile;
		FileReader in;
		BufferedReader readWord;
		Random rand = new Random();
		int numWords=0, wordToGuess;
		String secretWord = "";
		String wordSoFar = "";
		String letterGuess, wordGuess = "";
		int numGuesses = 0;
		int occurence,index =0;
		
		

		//Create a File object with the name of your file is the parameter
		WordGuessFile = new File("C:\\Users\\Aryan Kapoor\\git\\cs30p32025-ak0122\\Chapter11\\src\\Mastery\\wordlist");
		
		Scanner input = new Scanner(System.in);


		/* select secret word */
		try {
				//initialize the file reader object to name of the file object
				in = new FileReader(WordGuessFile);	
                
                //initialize the BufferedReader object to the name of the file reader as a parameter
				readWord = new BufferedReader(in);
				
				//Get the number of words in the file using readWord
				while((readWord.readLine()) != null) 
				{
					numWords++;
				}
				readWord.close();//close your BufferedReader object
                in.close();//close your FileReader object
                
                
				in = new FileReader(WordGuessFile);
				readWord = new BufferedReader(in);
				
                //update the word to guess to the random object and number of words read plus one
				wordToGuess = rand.nextInt(numWords) + 1;
			
                //iterate through the word to guess slots
				for(int a = 0; a < wordToGuess; a++) 
				{
					secretWord = readWord.readLine();
				}
				
                readWord.close();//close your BufferedReader object
                in.close();//close your FileReader object
				
    	}
		catch (FileNotFoundException e) 
		{    
			System.out.println("File could not be found.");      System.err.println("FileNotFoundException: "+ e.getMessage());
		}
		catch (IOException e) 
        {
			System.err.println("IO exception: " + e.getMessage());
		}
		
		/* begin the game */
		System.out.println("WordGuess game.\n");

        //iterate through the secret word, and update the word so far variable to represent using dashes
		for(int b=0; b < secretWord.length(); b++) 
		{
			wordSoFar += "-";
		}
				
        //output the word so far using dashes
		System.out.println(wordSoFar);

		/* allow player to make guesses*/

		do {
			System.out.println("Please enter a letter ("+ FLAG + " to guess the entire word)");
				letterGuess = input.next();
				letterGuess = letterGuess.toUpperCase();

			/* increment number of guesses */
				numGuesses += 1;

			/* player correctly guessed a letter--extract string in wordSoFar up to the letter
			 * guessed and then append guessed letter to that string. Next, extract rest of
			 * wordSoFar and append after the guessed letter
			 */
			
			//check if guess occurs multiple times: 
				
			if(secretWord.indexOf(letterGuess) >= 0)
			{
				indexList.clear();	//clear the list of indexes for previous words
				
				occurence = countChar(secretWord,letterGuess); //method to count occurrences of guess in secret word
				
					
					StringBuilder updatedWord = new StringBuilder(wordSoFar); //StringBuilder to replace characters in string
					
					for(int j=0;j<occurence ;j++) //iterate for total occurrences of word.
					{
						index = indexList.get(j); //get index of j'th occurrence
							
						updatedWord.setCharAt(index, letterGuess.charAt(0)); //replace relevant '-' with the guess
						
					}
					
					wordSoFar = updatedWord.toString(); //wordSoFar is updated
				
			}

			/* display guessed letter instead of dash */
			System.out.println(wordSoFar + "\n");


		} while (!letterGuess.equals(FLAG) && !wordSoFar.equals(secretWord));

		/* finish game and display message and number of guesses */
		
		if(letterGuess.equals(FLAG))
		{
			System.out.println("Please enter your WORD guess: ");
			wordGuess = input.next();
			wordGuess = wordGuess.toUpperCase();
		}
		
		if(wordGuess.equals(secretWord))
		{
			System.out.println("You Won!");
		}
		else
		{
			System.out.println("You Lose!");
		}
		
		System.out.println("The secret word is " + secretWord);
		System.out.println("You made " + numGuesses + " guesses.");
	}
	
	//method to count character occurrences
	public static int countChar(String str, String c) //two parameters: a string and a letter guess
	{
	    int count = 0; //start count at 0
	    indexList.clear();	//clear the index list
	    
	    for(int i=0; i < str.length(); i++) //iterate through the length of the given string
	    {    
	    	if(str.charAt(i) == c.charAt(0)) // if character found in string
	    	{
	    		
	    		indexList.add(i); // save index value to list
	            count++; //increase count by 1
	            
	    	}
	    }
	    
	    return count; //return total occurrences
	}
}

/* Screen Dump

	Case 1:
	WordGuess game.

--------
Please enter a letter (! to guess the entire word)
a
---A----

Please enter a letter (! to guess the entire word)
t
-T-AT---

Please enter a letter (! to guess the entire word)
y
-T-AT--Y

Please enter a letter (! to guess the entire word)
!
-T-AT--Y

Please enter your WORD guess: 
Strategy
You Won!
The secret word is STRATEGY
You made 4 guesses.

Case 2:
 WordGuess game.

-------
Please enter a letter (! to guess the entire word)
a
-------

Please enter a letter (! to guess the entire word)
l
-------

Please enter a letter (! to guess the entire word)
p
-------

Please enter a letter (! to guess the entire word)
e
-E-E---

Please enter a letter (! to guess the entire word)
t
-E-E--T

Please enter a letter (! to guess the entire word)
!
-E-E--T

Please enter your WORD guess: 
memellt
You Lose!
The secret word is BENEFIT
You made 6 guesses.

	Case 3:
WordGuess game.

-------
Please enter a letter (! to guess the entire word)
a
A-A----

Please enter a letter (! to guess the entire word)
z
A-A--Z-

Please enter a letter (! to guess the entire word)
e
A-A--ZE

Please enter a letter (! to guess the entire word)
n
ANA--ZE

Please enter a letter (! to guess the entire word)
y
ANA-YZE

Please enter a letter (! to guess the entire word)
l
ANALYZE

You Lose!
The secret word is ANALYZE
You made 6 guesses.

 */
