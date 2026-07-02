/*

Program: businessAcct.java          Last Date of this Revision: April 16,2025

Purpose: Extends Account to enforce a higher minimum balance and fee for business accounts, tailored for commercial use.

Author: Aryan K, 
School: CHHS
Course: Computer Programming 30
 

*/
package Mastery.Accounts;

public class businessAcct extends Account {

	private static double FEE = 10; //business accounts will charge a fee of $10 instead
	private static double MINBALANCE = 500; //higher minimum balance for business accounts.
	
	public businessAcct(double bal, String fName, String lName,String str, String city, String st, String zip)//business account constructor
	{
		super(bal,  fName,  lName, str,  city,  st, zip); //call and initialize the Account class
		
	}
	
	public void withdrawal(double amt)// override the withdrawal amount to include our deduction fee.
	{
		super.withdrawal(amt);
		
		if (super.getBalance() < MINBALANCE) 
		{
			System.out.println("Balance is less than the minimum... Deducting $10.00 Fee.");
			super.withdrawal(FEE);
		}
	}
	

}
