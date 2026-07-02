/*

Program: stack.java          Last Date of this Revision: May 25, 2025

Purpose: Implements a stack data structure using an ArrayList to store integer values.
Provides basic stack operations (push, pop, top) and utility methods (isEmpty, size).

Author: Aryan K, 
School: CHHS
Course: Computer Programming 30

*/

package Mastery.ParenChecker;

import java.util.ArrayList;

public class stack 
{
    // Stores the stack elements using ArrayList
    private ArrayList<Integer> data;
    
    // Tracks the index of the top element (-1 when empty)
    private int top;
    
    // Constructor initializes empty stack
    public stack() {
        data = new ArrayList<>();
        top = -1;  // -1 indicates empty stack
    }
    
    // Returns top element without removing it
    public int top() {
        return(data.get(top));  // Gets element at top index
    }
    
    // Removes and returns top element
    public int pop() {
        int item = data.remove(top);  // Retrieve top element
        top -= 1;                 // Decrement top pointer
        return(item);              // Return popped element
    }
    
    // Adds new element to top of stack
    public void push(int item) {
        top += 1;          // Move top pointer up
        data.add(item);    // Add to underlying ArrayList
    }
    
    // Checks if stack is empty
    public boolean isEmpty() {
        if(top == -1) {    // Condition for empty stack
            return true;
        }
        else {
            return false;
        }
    }
    
    // Returns current number of elements in stack
    public int size() {
        if(isEmpty()) {
            return 0;      // Empty stack has size 0
        }
        else {
            return (top+1); // Size is top index + 1
        }
    }
    
    // Clears all elements from stack
    public void makeEmpty() {
        data.clear();     // Empty the ArrayList
        top = -1;         // Reset top pointer
    }
}