package Skillbuilders.Stack3;

import Skillbuilders.Stack2.Stack2;

public class Stack3Tester {
	public static void main(String[] args) 
	{
		Stack3 s3 = new Stack3();
		s3.push("Red");
		s3.push("Green");
		s3.push("Yellow");
		System.out.println("Top of stack: "+ s3.top());
		System.out.println("stack size: " + s3.size());
		s3.pop();
		System.out.println("Top of stack: "+ s3.top());
		System.out.println("stack size: " + s3.size());
		s3.pop();
		System.out.println("Top of stack: "+ s3.top());
		System.out.println("stack size: " + s3.size());
	}

}

