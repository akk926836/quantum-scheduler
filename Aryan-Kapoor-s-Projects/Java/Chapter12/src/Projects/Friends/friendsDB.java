/*

Program: friendsDB.java          Last Date of this Revision: May 14, 2025

Purpose: Manages a database of friend objects with capabilities to add, delete,
find, and display friends. Handles file persistence for friend records.

Author: Aryan K, 
School: CHHS
Course: Computer Programming 30
 
*/

package Mastery.Friends;

import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

public class friendsDB {
    private ArrayList<friend> list;  // Stores friend objects
    private int numFriends;          // Tracks number of friends

    // Constructor initializes database from file
    public friendsDB() {
        File friendsFile = new File("C:\\Users\\Aryan Kapoor\\git\\cs30p32025-ak0122\\Chapter12\\src\\Mastery\\Friends\\friendsTextFile");
        list = new ArrayList<>();
        friend aFriend;

        /* Create new file if none exists */
        if (!(friendsFile.exists())) {
            try {
                friendsFile.createNewFile();
                System.out.println("There are no friend records in your database.");
            } catch (IOException e) {
                System.out.println("File could not be created.");
                System.err.println("IOException: " + e.getMessage());
            }
            numFriends = 0;
        } else {    
            /* Load existing friends from file */
            try {
                if(friendsFile.length() > 0) {
                    FileInputStream in = new FileInputStream(friendsFile);
                    ObjectInputStream readFriends = new ObjectInputStream(in);
                    numFriends = (int)readFriends.readInt();

                    if (numFriends == 0) {
                        System.out.println("There are no existing friend records in your database.");
                    } else {
                        for (int i = 0; i < numFriends; i++) {
                            aFriend = (friend) readFriends.readObject();
                            list.add(aFriend);
                        }
                    }
                    readFriends.close();
                    in.close();
                }
            } catch (FileNotFoundException e) {
                System.out.println("File could not be found.");
                System.err.println("FileNotFoundException: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("Problem with input/output.");
                System.err.println("IOException: " + e.getMessage());
            } catch (ClassNotFoundException e) {
                System.out.println("Class could not be used to cast object.");
                System.err.println("ClassNotFoundException: " + e.getMessage());
            }
        }
    }

    // Adds a new friend to the database
    public void addFriend() {
        String fname, lname, email, phoneNo;
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter your friend's first name: ");
        fname = input.next();
        
        System.out.println("Enter your friend's last name: ");
        lname = input.next();
        
        System.out.println("Enter your friend's email: ");
        email = input.next();
        
        System.out.println("Enter your friend's phone number: ");
        phoneNo = input.next();
        
        friend newFriend = new friend(fname, lname, phoneNo, email);
        list.add(newFriend);
        numFriends++;
    }

    // Removes a friend from the database
    public void deleteFriend(String fName, String lName) {
        int deleteInd;
        friend removeFriend = new friend(fName, lName);
        deleteInd = list.indexOf(removeFriend);
        
        if(deleteInd > -1) {
            list.remove(deleteInd);
            System.out.println(removeFriend.firstAndLast().toUpperCase() + " was removed from your friend's list.");
            numFriends--;
        } else {
            System.out.println("This friend does not exist");
        }
    }

    // Displays friends sorted by specified order (0=last name, 1=first name)
    public void displayFriends(int order) {
        friend retrievedFriend;

        if (order == 0) {
            selectionSorts.sortLast(list);
            for (int friendData = 0; friendData < list.size(); friendData++) {
                retrievedFriend = list.get(friendData);
                System.out.println(retrievedFriend.displayByLast().toUpperCase() + "\n");
            }
        } else {
            selectionSorts.sortFirst(list);
            for (int friendData = 0; friendData < list.size(); friendData++) {
                retrievedFriend = list.get(friendData);
                System.out.println(retrievedFriend.displayByFirst().toUpperCase() + "\n");
            }
        }
    }

    // Finds a friend in the database
    public void findFriend(String fName, String lName) {
        int finderInd;
        friend foundFriend = new friend(fName, lName);
        finderInd = list.indexOf(foundFriend);
        
        if(finderInd > -1) {
            System.out.println(foundFriend.firstAndLast().toUpperCase() + " was found in your friend's list.");
            System.out.println(list.get(finderInd).toString());
        } else {
            System.out.println("The friend was not found on the list.");
        }
    }

    /**
     * Saves friend records to file
     * pre: none
     * post: friend records have been written to file
     */
    public void update() {
        File updateFile = new File("C:\\Users\\Aryan Kapoor\\git\\cs30p32025-ak0122\\Chapter12\\src\\Mastery\\Friends\\friendsTextFile");
                
        try {
            FileOutputStream out = new FileOutputStream(updateFile);
            ObjectOutputStream writeFriends = new ObjectOutputStream(out);
            writeFriends.writeInt(numFriends);
            
            for (int i = 0; i < list.size(); i++) {
                writeFriends.writeObject(list.get(i));
            }
            writeFriends.close();
            out.close();
        } catch (FileNotFoundException e) {
            System.out.println("File could not be found.");
            System.err.println("FileNotFoundException: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Problem with input/output.");
            System.err.println("IOException: " + e.getMessage());
        }
    }
}