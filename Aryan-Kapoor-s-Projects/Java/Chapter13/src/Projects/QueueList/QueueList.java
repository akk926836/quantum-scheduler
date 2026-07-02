/*
Program: QueueList.java          Last Date of this Revision: May 25, 2025

Purpose: Implements a basic queue using linked nodes, with enqueue, dequeue, and 
inspection methods including size and front item retrieval.

Author: Aryan K, 
School: CHHS
Course: Computer Programming 30
*/

package Mastery.QueueList;

public class QueueList 
{
	private Node front;    // Points to the front of the queue
	private Node rear;     // Points to the rear of the queue
	private int size;      // Tracks the number of elements in the queue
	
	public QueueList()
	{
		front = null;
		rear = null;
		size = 0;
	}
	
	public Object dequeue()
	{
		// Removes and returns the front item of the queue
		if (isEmpty()) 
		{
			throw new IllegalStateException("Your Queue is empty.");
		}
		else 
		{
			Object item = front.getData();    // Get the front data
			front = front.getNext();          // Move front to the next node
			size--;
			return(item);
		}
	}
	
	public Object enqueue(Object item) 
	{
		// Adds a new item to the rear of the queue
		Node newNode = new Node(item);
		
		if (isEmpty()) 
		{
			front = newNode;
		} 
		else 
		{
			rear.setNext(newNode);   // Link old rear to new node
		}
		
		rear = newNode;             // Update rear to new node
		size++;
		
		return rear.getData();      // Return the added item
	}
	
	public boolean isEmpty()
	{
		// Checks if the queue is empty
		if (front == null && rear == null) 
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public int getSize()
	{
		return size;   // Returns the number of items in the queue
	}
	
	public Object getFront()
	{
		return front.getData();   // Returns the data at the front of the queue
	}
}
