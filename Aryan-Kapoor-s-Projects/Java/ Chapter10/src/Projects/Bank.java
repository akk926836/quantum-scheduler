/*

Program: Bank.java          Last Date of this Revision: 23rd February 2025

Purpose: An application that makes the methods which allow for the various functions in the LocalBankGUI application

Author: Aryan K, 
School: CHHS
Course: Computer Programming 30
 

*/

package Mastery;

import java.util.ArrayList;

public class Bank {
	// ArrayLists to store account details
    private ArrayList<String> idList;
    private ArrayList<String> fnameList;
    private ArrayList<String> lnameList;
    private ArrayList<Double> balanceList;
    
    public Bank() {
    	//declare all lists
        idList = new ArrayList<>();
        fnameList = new ArrayList<>();
        lnameList = new ArrayList<>();
        balanceList = new ArrayList<>();
        
    }
    
    //method to add accounts
    public String AddAccount(String fname, String lname, double bal) {
    	
    	Account acct = new Account(fname,lname,bal);
    	
    	String id = acct.getID();
    	
    	idList.addLast(acct.getID());
    	fnameList.addLast(acct.getFName());		//add all information from account class to its relevant list
    	lnameList.addLast(acct.getLName());
    	balanceList.addLast(acct.getBalance());
    	
    	String message = "Your id is " + id +"\n Your Name is " + fname +" "+ lname + "\n Your Balance is $" +bal;
    	return message;
    }
    
    
    //method to remove an account
    public String removeAccount(String id) {
        int index = getIndex(id);
        if (index != -1) {
            String name = fnameList.get(index) + " " + lnameList.get(index);
            idList.remove(index);
            fnameList.remove(index);					//remove all information at the relevant list index
            lnameList.remove(index);
            balanceList.remove(index);
            return name + "'s Account has been Removed.";
        } else {
            return "ID Not found. Try again.";
        }
    }

    // Method to deposit money into an account
    public String deposit(String id, double cashIn) {
        int index = getIndex(id);
        if (index != -1) {
            double newBal = balanceList.get(index) + cashIn;						
            balanceList.set(index, newBal);
            return "$" + cashIn + " was successfully DEPOSITED into your account.";
        } else {
            return "ID Not found. Try again.";
        }
    }

    // Method to withdraw money from an account
    public String withdraw(String id, double cashOut) {
        int index = getIndex(id);
        if (index != -1) {
            if (cashOut <= balanceList.get(index)) {
                double newBal = balanceList.get(index) - cashOut;			//only able to withdraw money if you have more or equal
                balanceList.set(index, newBal);								// amount in your bank account
                
                return "$" + cashOut + " was successfully WITHDRAWN from your account.";
            } else {
                return "Unsuccessful Transaction. NOT enough funds in your account."; //if not enough money in account
            }
        } else {
            return "ID Not found. Try again.";
        }
    }

    // Method to check the balance of an account
    public String checkBalance(String id) {
        int index = getIndex(id);
        if (index != -1) {
            return "Name: " + fnameList.get(index) + " " + lnameList.get(index) +
                   "\nID: " + idList.get(index) +									//Display bank information and balance
                   "\nBalance: $" + balanceList.get(index);
        } else {
            return "ID Not found. Try again.";
        }
    }
    
    //method to get index
    private int getIndex(String id) {
        if (idList.contains(id)) {
            return idList.indexOf(id);
        } else {
            return -1;			// for invalid id cases
        }
    }
    
}