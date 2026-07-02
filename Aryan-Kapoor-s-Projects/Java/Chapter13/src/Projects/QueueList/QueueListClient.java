/*
Program: QueueListClient.java          Last Date of this Revision: May 25, 2025

Purpose: Provides a menu-driven interface for users to interact with the QueueList class, 
allowing enqueue, dequeue, and inspection operations via console input.

Author: Aryan K, 
School: CHHS
Course: Computer Programming 30
*/

package Mastery.QueueList;

import java.util.Scanner;

public class QueueListClient 
{
	public static void main(String[] args)
	{
		QueueList ql = new QueueList();       // Create a new QueueList
		Scanner input = new Scanner(System.in);
		
		int choice = -1;
		
		do 
		{
			// Display the menu
			System.out.println("============================== Queue List ==============================");
			System.out.println("Choose one of the following choices.");
			System.out.println("(1)Enqueue Item \n(2)Dequeue Item \n(3)See Front Item \n(4)Display List Size \n(0)Quit");
			
			choice = input.nextInt();
			
			if (choice > 4 || choice < 0)
			{
				System.out.println("Error. Select a valid choice.");
			}
			else
			{
				switch(choice)
				{
					case 0: 
						System.out.println("Successfully quit the application");
						System.exit(0); // Exit the program
						break;
						
					case 1: 
						System.out.println("Enter a value:");
						int item = input.nextInt();
						System.out.println(ql.enqueue(item) + " was added"); // Enqueue item
						break;
						
					case 2: 
						System.out.println(ql.dequeue() + " was removed"); // Dequeue item
						break;
						
					case 3: 
						System.out.println(ql.getFront()); // Display front item
						break;
						
					case 4: 
						System.out.println(ql.getSize() + "Items"); // Display queue size
				}
			}
		}
		while (choice != 0);
	}
}
/* Screen Dump

	============================== Queue List ==============================
	Choose one of the following choices.
	(1)Enqueue Item 
	(2)Dequeue Item 
	(3)See Front Item 
	(4)Display List Size 
	(0)Quit
	1
	Enter a value:
	87
	87 was added
	============================== Queue List ==============================
	Choose one of the following choices.
	(1)Enqueue Item 
	(2)Dequeue Item 
	(3)See Front Item 
	(4)Display List Size 
	(0)Quit
	1
	Enter a value:
	7
	7 was added
	============================== Queue List ==============================
	Choose one of the following choices.
	(1)Enqueue Item 
	(2)Dequeue Item 
	(3)See Front Item 
	(4)Display List Size 
	(0)Quit
	1
	Enter a value:
	90
	90 was added
	============================== Queue List ==============================
	Choose one of the following choices.
	(1)Enqueue Item 
	(2)Dequeue Item 
	(3)See Front Item 
	(4)Display List Size 
	(0)Quit
	3
	87
	============================== Queue List ==============================
	Choose one of the following choices.
	(1)Enqueue Item 
	(2)Dequeue Item 
	(3)See Front Item 
	(4)Display List Size 
	(0)Quit
	4
	3 Items
	============================== Queue List ==============================
	Choose one of the following choices.
	(1)Enqueue Item 
	(2)Dequeue Item 
	(3)See Front Item 
	(4)Display List Size 
	(0)Quit
	2
	87 was removed
	============================== Queue List ==============================
	Choose one of the following choices.
	(1)Enqueue Item 
	(2)Dequeue Item 
	(3)See Front Item 
	(4)Display List Size 
	(0)Quit
	0
	Successfully quit the application
	
	 
 */