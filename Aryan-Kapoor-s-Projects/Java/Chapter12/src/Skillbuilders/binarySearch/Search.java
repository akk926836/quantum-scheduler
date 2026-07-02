package Skillbuilders.binarySearch;

public class Search 
{
	public static int binarySearch(int[] items, int start, int end, int goal)
	{
		if(start > end)
		{
			return (-1);
		}
		else
		{
			int midIndex = (start + end)/2;
			
			System.out.println("Examining: " + (midIndex+1));
			
			if(items[midIndex] == goal)
			{
				return midIndex;
			}
			
			else if(items[midIndex] < goal)
			{
				return(binarySearch(items, midIndex+1, end, goal));
			}
			else
			{
				return(binarySearch(items, 0, midIndex-1, goal));
			}
		}
	}
}
