/*
Program: dllNode.java          Last Date of this Revision: May 25, 2025

Purpose: Defines a node for a doubly linked list that stores String data and maintains
references to both next and previous nodes in the list.

Author: Aryan K, 
School: CHHS
Course: Computer Programming 30
*/

package Mastery.DoublyLinkedList;

public class dllNode {
    private String data;     // The data stored in this node
    private dllNode next;    // Reference to the next node in the list
    private dllNode prev;    // Reference to the previous node in the list
    
    // Constructor creates a new node with given data
    public dllNode(String str) {
        data = str;
        next = null;  // Initially not connected
        prev = null;  // Initially not connected
    }
    
    // Returns reference to next node
    public dllNode getNext() {
        return next;
    }
    
    // Sets reference to next node
    public void setNext(dllNode newNode) {
        next = newNode;
    }
    
    // Returns reference to previous node
    public dllNode getPrev() {
        return prev;
    }
    
    // Sets reference to previous node
    public void setPrev(dllNode newNode) {
        prev = newNode;
    }
    
    // Returns the data stored in this node
    public String getData() {
        return data;
    }
}