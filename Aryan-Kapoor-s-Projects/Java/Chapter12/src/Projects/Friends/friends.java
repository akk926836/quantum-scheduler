/*

Program: friends.java          Last Date of this Revision: May 14, 2025

Purpose: Main application class that provides a user interface for managing
a friends database through console-based menu system.

Author: Aryan K, 
School: CHHS
Course: Computer Programming 30
 
*/

package Mastery.Friends;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class friends {
    public static void main(String[] args) {
        friendsDB friendsList = new friendsDB();
        Scanner input = new Scanner(System.in);
        String firstName, lastName;
        int choice = 0, order = 0;

        /* Main application loop */
        do {
            System.out.println("============================================== FRIENDS LIST ==============================================");
            System.out.println("Select one of the following options");
            System.out.println("(1) Display Friends\n(2) Add Friend\n(3) Remove Friend\n(4) Find Friend\n(0) Quit Application");
            
            choice = input.nextInt();

            if (choice == 1) {
                System.out.println("\nLast Name (0)\nFirst Name (1)");
                order = input.nextInt();
                friendsList.displayFriends(order);
            } else if (choice == 2) {
                friendsList.addFriend();
            } else if (choice == 3) {
                System.out.println("Enter your friend's first name: ");
                firstName = input.next();
                System.out.println("Enter your friend's last name: ");
                lastName = input.next();
                friendsList.deleteFriend(firstName, lastName);
            } else if (choice == 4) {
                System.out.println("Enter your friend's first name: ");
                firstName = input.next();
                System.out.println("Enter your friend's last name: ");
                lastName = input.next();
                friendsList.findFriend(firstName, lastName);
            } else if(choice == 0) {
                System.out.println("You have successfully quit the application");
            }
        } while (choice != 0);

        // Save changes before exiting
        friendsList.update();
    }
}
/* Screen Dump

There are no existing friend records in your database.
============================================== FRIENDS LIST ==============================================
Select one of the following options
(1) Display Friends
(2) Add Friend
(3) Remove Friend
(4) Find Friend
(0) Quit Application
2
Enter your friend's first name: 
Aryan
Enter your friend's last name: 
Kapoor
Enter your friend's email: 
aryank@gmail.com
Enter your friend's phone number: 
741982764
============================================== FRIENDS LIST ==============================================
Select one of the following options
(1) Display Friends
(2) Add Friend
(3) Remove Friend
(4) Find Friend
(0) Quit Application
2
Enter your friend's first name: 
Justin
Enter your friend's last name: 
Bhalla
Enter your friend's email: 
justinb@outlook.com
Enter your friend's phone number: 
987474214
============================================== FRIENDS LIST ==============================================
Select one of the following options
(1) Display Friends
(2) Add Friend
(3) Remove Friend
(4) Find Friend
(0) Quit Application
1

Last Name (0)
First Name (1)
1
ARYAN, KAPOOR
741982764
ARYANK@GMAIL.COM

JUSTIN, BHALLA
987474214
JUSTINB@OUTLOOK.COM

============================================== FRIENDS LIST ==============================================
Select one of the following options
(1) Display Friends
(2) Add Friend
(3) Remove Friend
(4) Find Friend
(0) Quit Application
2
Enter your friend's first name: 
Rishi
Enter your friend's last name: 
Fung
Enter your friend's email: 
rishif@cbe.ca
Enter your friend's phone number: 
747497479
============================================== FRIENDS LIST ==============================================
Select one of the following options
(1) Display Friends
(2) Add Friend
(3) Remove Friend
(4) Find Friend
(0) Quit Application
1

Last Name (0)
First Name (1)
0
BHALLA, JUSTIN
987474214
JUSTINB@OUTLOOK.COM

FUNG, RISHI
747497479
RISHIF@CBE.CA

KAPOOR, ARYAN
741982764
ARYANK@GMAIL.COM

============================================== FRIENDS LIST ==============================================
Select one of the following options
(1) Display Friends
(2) Add Friend
(3) Remove Friend
(4) Find Friend
(0) Quit Application
3
Enter your friend's first name: 
Aryan
Enter your friend's last name: 
Kapoor
ARYAN KAPOOR was removed from your friend's list.
============================================== FRIENDS LIST ==============================================
Select one of the following options
(1) Display Friends
(2) Add Friend
(3) Remove Friend
(4) Find Friend
(0) Quit Application
4
Enter your friend's first name: 
aryan
Enter your friend's last name: 
kapoor
The friend was not found on the list.
============================================== FRIENDS LIST ==============================================
Select one of the following options
(1) Display Friends
(2) Add Friend
(3) Remove Friend
(4) Find Friend
(0) Quit Application
4
Enter your friend's first name: 
rishi
Enter your friend's last name: 
fung
RISHI FUNG was found in your friend's list.
Name: Rishi Fung
Email: rishif@cbe.ca
Phone No: 747497479
============================================== FRIENDS LIST ==============================================
Select one of the following options
(1) Display Friends
(2) Add Friend
(3) Remove Friend
(4) Find Friend
(0) Quit Application
1

Last Name (0)
First Name (1)
0
BHALLA, JUSTIN
987474214
JUSTINB@OUTLOOK.COM

FUNG, RISHI
747497479
RISHIF@CBE.CA

============================================== FRIENDS LIST ==============================================
Select one of the following options
(1) Display Friends
(2) Add Friend
(3) Remove Friend
(4) Find Friend
(0) Quit Application
0
You have successfully quit the application

 
 */
