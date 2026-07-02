package Skillbuilders.Queue3;

public class Queue3Tester {
	public static void main(String[] args) {
		Queue3 q3 = new Queue3();
		
		System.out.println("Adding  \"green\", \"red\", "
				+ "\"yellow\", and \"orange\" to queue2.");
		
		q3.enqueue("green");
		q3.enqueue("red");
		q3.enqueue("yellow");
		q3.enqueue("orange");
		
		System.out.println("Front of queue3: "+ q3.front());
		System.out.println("Size of queue3: "+ q3.size());
		System.out.println("Remove an element from queue3: "+ q3.dequeue());
		System.out.println("Front of queue3: "+ q3.front());
		System.out.println("Size of queue2: "+ q3.size());
		
	}
}
