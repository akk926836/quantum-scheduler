/*
Program: dllTester.java          Last Date of this Revision: May 25, 2025

Purpose: Demonstrates the functionality of the doubly linked list implementation
by adding/removing elements and displaying the list in both directions.

Author: Aryan K, 
School: CHHS
Course: Computer Programming 30
*/

package Mastery.DoublyLinkedList;

import java.util.List;

public class dllTester {
    public static void main(String[] args) {
        doublyLinkedList list = new doublyLinkedList();
        
        // Test add operations
        list.addAtEnd("A");  // List: A
        list.addAtEnd("B");  // List: A B
        list.addAtFront("C"); // List: C A B
        list.addAtFront("D"); // List: D C A B
        list.addAtEnd("E");  // List: D C A B E
        
        // Display list both ways
        list.display();    // Expected: D C A B E
        System.out.println();
        list.displayRev(); // Expected: E B A C D
        System.out.println();
        
        // Test remove operation
        list.remove("B");  // Removes first occurrence of "B"
        
        // Display updated list
        list.display();    // Expected: D C A E
        System.out.println();
        
        //list.remove("A");		Screen Dump Case 2
        list.displayRev(); // Expected: E A C D Case1/ ECD Case2
    }
}

/* Screen Dump
	Case 1:
	Forwards: 
	D C A B E 
	Reverse: 
	E B A C D 
	Forwards: 
	D C A E 
	Reverse: 
	E A C D 
	
	Case 2:
	Forwards: 
	D C A B E 
	Reverse: 
	E B A C D 
	Forwards: 
	D C A E 
	Reverse: 
	E C D 
 
 */
