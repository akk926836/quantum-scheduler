/*

Program: parenChecker.java          Last Date of this Revision: May 25, 2025

Purpose: Application that validates parenthesis balancing in expressions using a stack.
Identifies matching pairs and reports unmatched parentheses with their positions.

Author: Aryan K, 
School: CHHS
Course: Computer Programming 30

*/

package Mastery.ParenChecker;

import java.util.Scanner;

public class parenChecker 
{
    public static void main(String[] args) 
    {
        Scanner input = new Scanner(System.in);
        stack stacks = new stack();  // Create stack instance
        
        System.out.print("Enter an expression: ");
        String exp = input.nextLine();  // Get user input
        input.close();
        
        char ch;  // Current character being processed
        
        // Process each character in the expression
        for(int i = 0; i < exp.length(); i++)
        {
            ch = exp.charAt(i);
            
            // When finding an opening parenthesis
            if(ch == '(')
            {
                stacks.push(i);  // Push position onto stack
            }
            // When finding a closing parenthesis
            else if (ch == ')')
            {
                if(stacks.isEmpty())
                {
                    // Error: No matching opening parenthesis
                    System.out.println("Error. Unmatched \")\" at position " + i);
                }
                else 
                {
                    // Valid pair found
                    int openBracketIndex = stacks.top();  // Get position of '('
                    int closeBracketIndex = i;            // Current position is ')'
                    stacks.pop();                         // Remove matched '(' from stack
                    
                    // Print matching pair positions
                    System.out.println("Matched pair: '(' at " + openBracketIndex 
                                     + " with ')' at " + closeBracketIndex);
                }
            }
        }
        
        // After processing all characters
        if(!(stacks.isEmpty()))
        {
            // Error: Unmatched opening parentheses remain
            System.out.println("Error. Found " + stacks.size() 
                             + " unmatched \"(\" parentheses");
        }
    }
}
/* Screen Dump

Case 1: 
	Enter an expression: (a-(b*cd))
	Matched pair: '(' at 3 with ')' at 8
	Matched pair: '(' at 0 with ')' at 9

Case 2:
	Enter an expression: (ab)-(c+d)
	Matched pair: '(' at 0 with ')' at 3
	Matched pair: '(' at 5 with ')' at 9

 */