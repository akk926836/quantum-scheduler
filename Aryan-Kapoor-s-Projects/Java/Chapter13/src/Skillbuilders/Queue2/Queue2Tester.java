package Skillbuilders.Queue2;

public class Queue2Tester 
{

	public static void main(String[] args) 
	{
		Queue2 q2 = new Queue2(3);
		
		System.out.println("Adding \"red\", "
				+ "\"yellow\", and \"orange\" to queue2.");
		
		q2.enqueue("red");
		q2.enqueue("yellow");
		q2.enqueue("orange");
		
		System.out.println("Front of queue2: "+ q2.front());
		System.out.println("Size of queue2: "+ q2.size());
		System.out.println("Remove an element from queue2: "+ q2.dequeue());
		System.out.println("Front of queue2: "+ q2.front());
		System.out.println("Size of queue2: "+ q2.size());
		
	}

}
