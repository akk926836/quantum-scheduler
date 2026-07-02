/*

Program: personalAcct.java          Last Date of this Revision: April 16,2025

Purpose: Extends Account to enforce a minimum balance rule and charge a fee for personal accounts falling below it.

Author: Aryan K, 
School: CHHS
Course: Computer Programming 30
 

*/
package Mastery.Accounts;

public class personalAcct extends Account{

	private static double FEE = 2; // fee for being under minimum balance is $2 for personal accounts
	private static double MINBALANCE = 100;// minimum balance is $100.00.
	
	personalAcct(double bal, String fName, String lName,String str, String city, String st, String zip) //constructor for personal Accounts.
	{
		super(bal,  fName,  lName, str,  city,  st, zip); //call and initialize the account class.
		
	}
	
	
	public void withdrawal(double amt) //overrider the withdrawal method from Account to include our deductions.
	{
		super.withdrawal(amt);
		
		if (super.getBalance() < MINBALANCE) 
		{
			System.out.println("Balance is less than the minimum... Deducting $2.00 Fee.");
			super.withdrawal(FEE);
		}
	}
	
}
