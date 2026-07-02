package Skillbuilders.Recursion;

import java.util.Scanner;

public class recursiveFactorial 
{
	private static int doRecursion(int num)
	{
		int value;
		
		if(num == 0)
		{
			return 1;
		}
		
		else
		{
			value = num * doRecursion(num-1);
			return value;
		}
	}
	
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter a number: ");
		int userNum = input.nextInt();
		
		int result = doRecursion(userNum);
		
		System.out.println(userNum + "! = " + result);
		
	}
}
