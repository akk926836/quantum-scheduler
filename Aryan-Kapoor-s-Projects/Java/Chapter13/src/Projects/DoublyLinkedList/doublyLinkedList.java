/*
Program: doublyLinkedList.java          Last Date of this Revision: May 25, 2025

Purpose: Implements a doubly linked list data structure with operations to add/remove
nodes at both ends and display the list in forward/reverse order.

Author: Aryan K, 
School: CHHS
Course: Computer Programming 30
*/

package Mastery.DoublyLinkedList;

public class doublyLinkedList {
    dllNode head;  // Reference to first node in the list
    
    // Constructor creates empty list
    public doublyLinkedList() {
        head = null;
    }
    
    // Adds new node at end of list
    public void addAtEnd(String str) {
        dllNode newNode = new dllNode(str);
        dllNode current = head;
        
        if(head == null) {  // If list is empty
            head = newNode; // New node becomes head
        }
        else {
            // Traverse to last node
            while(current.getNext() != null) {
                current = current.getNext();
            }
            // Link new node after last node
            current.setNext(newNode);
            newNode.setPrev(current);
        }
    }
    
    // Adds new node at front of list
    public void addAtFront(String str) {
        dllNode newNode = new dllNode(str);
        
        newNode.setNext(head);  // New node points to current head
        
        if (head != null) {
            head.setPrev(newNode);  // Current head points back to new node
        }
        
        head = newNode;  // New node becomes new head
    }
    
    // Removes first node containing specified data
    public void remove(String str) {
        dllNode toRemove = head;
        dllNode prev = head;
        
        // Case 1: Remove head node
        if(head.getData().equals(str)) {
            head = head.getNext();
            if (head != null) {
                head.setPrev(null);  // New head has no previous
            }
        }
        else {
            // Search for node to remove
            while(toRemove.getNext() != null) {
                prev = toRemove;
                toRemove = toRemove.getNext();
                
                if(toRemove.getData().equals(str)) {
                    // Bypass node to be removed
                    prev.setNext(toRemove.getNext());
                    if(toRemove.getNext() != null) {
                        // Update next node's previous reference
                        toRemove.getNext().setPrev(prev);
                    }
                    break;  // Exit after first match
                }
            }
        }
    }
    
    // Displays list in forward order
    public void display() {
        if(head == null) {
            System.out.println("List is empty.");
        }
        else {
            System.out.println("Forwards: ");
            dllNode current = head;
            
            while(current != null) {
                System.out.print(current.getData() + " ");
                current = current.getNext();
            }
        }
    }
    
    // Displays list in reverse order
    public void displayRev() {
        if(head == null) {
            System.out.println("List is empty.");
        }
        else {
            System.out.println("Reverse: ");
            dllNode current = head;
            
            // Traverse to last node
            while (current.getNext() != null) {
                current = current.getNext();
            }
            
            // Traverse backwards
            while(current != null) {
                System.out.print(current.getData() + " ");
                current = current.getPrev();
            }
        }
    }
}