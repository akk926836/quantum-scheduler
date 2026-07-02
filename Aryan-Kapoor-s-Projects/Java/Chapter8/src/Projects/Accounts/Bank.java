/*

Program: Bank.java          Last Date of this Revision: April 16,2025

Purpose: The main class that interacts with users to create and manage personal/business accounts via a console menu system.

Author: Aryan K, 
School: CHHS
Course: Computer Programming 30
 

*/
package Mastery.Accounts;

import java.util.Scanner;

public class Bank {

	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		
		// Display bank application header
		System.out.println("\n========== Bank ==========");
		
		// Prompt user to create an account or quit
		System.out.println("Do you want to create an Account: (Y)es or (N)o");
		
		String createAcct = null;
		// Input validation loop for Y/N response
		do
		{
			createAcct = input.next().toUpperCase();
			
			// Check for invalid input
			if(!(createAcct.equals("Y")) && !(createAcct.equals("N")))
			{
				System.out.println("Invalid Input, Try Again.");
			}
			
		}
		while(!(createAcct.equals("Y")) && !(createAcct.equals("N")));
		
		// Exit if user chooses not to create account
		if(createAcct.equals("N"))
		{
			System.out.println("Quitting the Application");
			System.exit(0);;
		}
		
		//Get Information
		// Collect customer personal information
		System.out.print("Enter your First Name: ");
		String fname = input.next();
		
		System.out.print("Enter your Last Name: ");
		String lname = input.next();
		
		input.nextLine(); // Clear buffer
		
		// Collect customer address information
		System.out.print("Enter your Street Address: ");
		String street = input.nextLine();
		
		System.out.print("Enter your City: ");
		String city = input.next();
		
		System.out.print("Enter your State: ");
		String state = input.next();
		
		System.out.print("Enter your ZIP Code: ");
		String zip = input.next();
		
		// Get initial account balance
		System.out.print("Enter your initial Balance: ");
		double bal = input.nextDouble();
		
		// Account type selection
		System.out.println("Please Select the type of Account you want to create: ");
		System.out.println("(P)ersonal or (B)usiness");
		
		String choice = null;
		// Input validation loop for account type selection
		do {
			
			choice = input.next().toUpperCase();
			
			if(!(choice.equals("P")) && !(choice.equals("B")))
			{
				System.out.println("Invalid Input, Try Again.");
			}
		}
		while(!(choice.equals("P")) && !(choice.equals("B")));
		
		 int selection = -1;
		 
		// Personal Account creation and management
		if(choice.equals("P"))
		{
			// Create personal account object
			personalAcct personal = new personalAcct(bal,fname,lname,street,city,state,zip);
			
			// Account management menu loop
			do {
				System.out.print("\n========== Personal Account ==========\n");
				System.out.println(personal); // Display account info
				System.out.println("Choose an Option: ");
				System.out.println("(1) Deposit \n(2) Withdrawal \n(0) Quit");
				selection = input.nextInt();
				
				// Process user selection
				switch(selection)
				{
				case 0: // Quit application
						System.out.println("Quitting the Application.");
						System.exit(0); break;
						
				case 1: // Deposit money
						System.out.println("Enter the Deposit Amount");
						double cashIn = input.nextDouble();
						personal.deposit(cashIn); break;
						
				case 2: // Withdraw money
						System.out.println("Enter the Withdrawal Amount");
						double cashOut = input.nextDouble();
						personal.withdrawal(cashOut); break;
				}
			}
			while(selection != 0);
		}
		
		// Business Account creation and management
		else if(choice.equals("B"))
		{
			// Create business account object
			businessAcct business = new businessAcct(bal,fname,lname,street,city,state,zip);
			
			// Account management menu loop
			do {
				System.out.print("\n========== Business Account ==========\n");
				System.out.println(business); // Display account info
				System.out.println("Choose an Option: ");
				System.out.println("(1) Deposit \n(2) Withdrawal\n(0) Quit");
				selection = input.nextInt();
				
				// Process user selection
				switch(selection)
				{
				case 0: // Quit application
						System.out.println("Quitting the Application.");
						System.exit(0); break;
						
				case 1: // Deposit money
						System.out.println("Enter the Deposit Amount");
						double cashIn = input.nextDouble();
						business.deposit(cashIn); break;
						
				case 2: // Withdraw money
						System.out.println("Enter the Withdrawal Amount");
						double cashOut = input.nextDouble();
						business.withdrawal(cashOut); break;
				}
			}
			while(selection != 0);
		}
	}
}
/*
 * 
 * TestCase1:
 * 
========== Bank ==========
Do you want to create an Account: (Y)es or (N)o
t
Invalid Input, Try Again.
y
Enter your First Name: Aryan
Enter your Last Name: K
Enter your Street Address: 1567 18A Street SW
Enter your City: Calgary
Enter your State: AB
Enter your ZIP Code: P9P7K7
Enter your initial Balance: 1000
Please Select the type of Account you want to create: 
(P)ersonal or (B)usiness
p

========== Personal Account ==========
Aryan K
1567 18A Street SW
Calgary, AB P9P7K7
Current balance is $1,000.00
Choose an Option: 
(1) Deposit 
(2) Withdrawal 
(0) Quit
2
Enter the Withdrawal Amount
800

========== Personal Account ==========
Aryan K
1567 18A Street SW
Calgary, AB P9P7K7
Current balance is $200.00
Choose an Option: 
(1) Deposit 
(2) Withdrawal 
(0) Quit
1
Enter the Deposit Amount
40

========== Personal Account ==========
Aryan K
1567 18A Street SW
Calgary, AB P9P7K7
Current balance is $240.00
Choose an Option: 
(1) Deposit 
(2) Withdrawal 
(0) Quit
2
Enter the Withdrawal Amount
200
Balance is less than the minimum... Deducting $2.00 Fee.

========== Personal Account ==========
Aryan K
1567 18A Street SW
Calgary, AB P9P7K7
Current balance is $38.00
Choose an Option: 
(1) Deposit 
(2) Withdrawal 
(0) Quit
0
Quitting the Application.

TestCase2:

========== Bank ==========
Do you want to create an Account: (Y)es or (N)o
y
Enter your First Name: Justin
Enter your Last Name: Rishi
Enter your Street Address: Bhalla Street
Enter your City: Calgary
Enter your State: AB
Enter your ZIP Code: L9LK8H
Enter your initial Balance: 20000
Please Select the type of Account you want to create: 
(P)ersonal or (B)usiness
b

========== Business Account ==========
Justin Rishi
Bhalla Street
Calgary, AB L9LK8H
Current balance is $20,000.00
Choose an Option: 
(1) Deposit 
(2) Withdrawal
(0) Quit
1
Enter the Deposit Amount
2000

========== Business Account ==========
Justin Rishi
Bhalla Street
Calgary, AB L9LK8H
Current balance is $22,000.00
Choose an Option: 
(1) Deposit 
(2) Withdrawal
(0) Quit
2
Enter the Withdrawal Amount
21800
Balance is less than the minimum... Deducting $10.00 Fee.

========== Business Account ==========
Justin Rishi
Bhalla Street
Calgary, AB L9LK8H
Current balance is $190.00
Choose an Option: 
(1) Deposit 
(2) Withdrawal
(0) Quit
0
Quitting the Application.

*/