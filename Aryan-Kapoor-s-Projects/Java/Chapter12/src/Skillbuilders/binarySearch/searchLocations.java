package Skillbuilders.binarySearch;

import java.util.Random;
import java.util.Scanner;

public class searchLocations 
	{
		public static void main(String[] args)
		{
			Random rand = new Random();
			Scanner input = new Scanner(System.in);
			
			int[] list = new int[10];
			
			for(int i = 0; i < list.length;i++)
			{
				list[i] = rand.nextInt(100);
			}
			
			intSorts sort = new intSorts();
			
			sort.intMergeSort(list);
			
			for(int i = 0; i < (list.length);i++)
			{
				System.out.print(list[i] + " ");
			}
			
			System.out.print("\n");
			
			System.out.println("Enter a number to search for: ");
			int numToFind = input.nextInt();
			
			Search searches = new Search();
			
			
			int location = searches.binarySearch(list, 0, list.length -1, numToFind);
			
			if(location < 0)
			{
				System.out.println("The number does not exist in the list");
			}
			
			else 
			{
				System.out.println("Number at position: " + (location+1));
			}
			
			
			
		}
	}
