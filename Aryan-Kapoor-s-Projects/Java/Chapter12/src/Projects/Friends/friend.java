/*

Program: friend.java          Last Date of this Revision: May 14, 2025

Purpose: Represents a friend object with personal details and provides methods for comparison,
display, and string representation. Implements serialization for object persistence.

Author: Aryan K, 
School: CHHS
Course: Computer Programming 30
 
*/

package Mastery.Friends;

import java.io.*;
import Mastery.Friends.comparableNames.ComparableNames;

public class friend implements ComparableNames, Serializable {
    // Friend attributes
    String firstName,lastName,phoneNo,email;

    // Primary constructor
    public friend(String fname, String lname, String phone, String emailAdd) {
        firstName = fname;
        lastName = lname;
        phoneNo = phone;
        email = emailAdd;
    }

    // Overloaded constructor for name-only initialization
    public friend(String fname, String lname) {
        firstName = fname;
        lastName = lname;
    }

    // Returns full name as "FirstName LastName"
    public String firstAndLast() {
        return firstName + " " + lastName;
    }

    // Getters
    public String getLast() {
        return lastName;
    }

    public String getFirst() {
        return firstName;
    }

    /**
     * Displays friend information with last name first
     * pre: none
     * post: Friend data has been displayed, last name first
     */
    public String displayByLast() {
        return lastName + ", " + firstName + "\n" + phoneNo + "\n" + email;
    }

    // Displays friend information with first name first
    public String displayByFirst() {
        return firstName + ", " + lastName + "\n" + phoneNo + "\n" + email;
    }

    // Comparison methods implementing ComparableNames interface
    public int compareToFirstName(Object f) {
        friend testObj = (friend) f;
        return (firstName.compareToIgnoreCase(testObj.getFirst()));
    }

    public int compareToLastName(Object f) {
        friend testObj = (friend)f;
        return (lastName.compareToIgnoreCase(testObj.getLast()));
    }

    // Equality check based on first and last names (case-insensitive)
    public boolean equals(Object f) {
        friend testObj = (friend)f;
        
        return firstName.compareToIgnoreCase(testObj.getFirst()) == 0 && 
               lastName.compareToIgnoreCase(testObj.getLast()) == 0;
    }

    // String representation of friend object
    public String toString() {
        return "Name: " + firstAndLast() + "\nEmail: " + email + "\nPhone No: " + phoneNo;
    }
}