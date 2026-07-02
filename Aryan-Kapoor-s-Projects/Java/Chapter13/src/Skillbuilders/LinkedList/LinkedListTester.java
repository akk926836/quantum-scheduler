package Skillbuilders.LinkedList;

public class LinkedListTester {
	public static void main(String[] args) 
	{
		LinkedList list = new LinkedList();
		
		list.addAtFront("Gurdeep");
		list.addAtFront("Li");
		list.addAtFront("Kevin");
		list.addAtFront("Ahmed");
		
		
		
		list.addAtEnd("Crescent");
		list.addAtEnd("Blue");
		
		System.out.println(list);
		
		System.out.println("list size: "
				+ list.size() + " items.");
		
		list.makeEmpty();
		
		System.out.println("list size: "
				+ list.size() + " items.");
	}
}
