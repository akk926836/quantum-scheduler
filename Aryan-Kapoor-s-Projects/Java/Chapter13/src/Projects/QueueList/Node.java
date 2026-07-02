/*
Program: Node.java          Last Date of this Revision: May 25, 2025

Purpose: Defines a Node class used in a queue data structure, storing an object and 
a reference to the next node.

Author: Aryan K, 
School: CHHS
Course: Computer Programming 30
*/

package Mastery.QueueList;

public class Node 
{
	private Object data;     // Holds the data of the node
	private Node next;       // Reference to the next node
	
	public Node(Object item)
	{
		data = item;
		next = null;
	}
	
	public Node getNext()
	{
		return(next);         // Returns the next node
	}

	public void setNext(Node newNode)
	{
		next = newNode;      // Sets the next node reference
	}
	
	public Object getData()
	{
		return data;         // Returns the data stored in the node
	}
}
