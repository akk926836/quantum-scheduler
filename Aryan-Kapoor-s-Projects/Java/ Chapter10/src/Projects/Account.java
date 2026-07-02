/*

Program: Account.java          Last Date of this Revision: 23rd February 2025

Purpose: An application that sets up and initializes an account to be used by the Bank Class in the the LocalBankGUI assignment

Author: Aryan K, 
School: CHHS
Course: Computer Programming 30
 

*/
package Mastery;

import java.util.ArrayList;

public class Account {

    String fName;
    String lName;
    double balance;
    String id;


    // Constructor to initialize an Account
    public Account(String fname,String lname, double bal) {
    	fName = fname;
    	lName = lname;
    	balance = bal;
    	id = fName.charAt(0)+lName;
    	
    }
    
    //method to retrieve the first name
    public String getFName() {

     return fName;
    }
    
  //method to retrieve the last name
    public String getLName() {

        return lName;
       }
    
  //method to retrieve the bank balance
    public double getBalance() {

        return balance;
       }
    
  //method to retrieve the ID
    public String getID() {

        return id;
       }
}