package Skillbuilders.binarySearch;

public class intSorts 
{
	public static void intMergeSort(int[] inputArray) 
	{
		int length = inputArray.length;
		
		if(length < 2)
		{
			return;
		}
		
		int midIndex = length/2;
		
		int[] leftHalf = new int[midIndex];
		int[] rightHalf = new int[length - midIndex];
		
		
		for(int i = 0; i < midIndex;i++)
		{
			leftHalf[i] = inputArray[i];
		}
		
		for(int j= midIndex; j < length;j++)
		{
			rightHalf[j-midIndex] = inputArray[j];
		}
		
		intMergeSort(leftHalf);
		intMergeSort(rightHalf);
		
		intMerge(inputArray, leftHalf,rightHalf);
		
	}
	
	private static void intMerge(int[] inputArray, int[] leftHalf, int[] rightHalf)
	{
		int leftLength = leftHalf.length;
		int rightLength = rightHalf.length;
		
		
		int i=0,j=0,k=0;
		while(i < leftLength && j < rightLength)
		{
			if(leftHalf[i] < rightHalf[j])
			{
				inputArray[k] = leftHalf[i];
				i++;
			}
			
			else
			{
				inputArray[k] = rightHalf[j];
				j++;
			}
			
			k++;
		}
		
		while(i < leftLength)
		{
			inputArray[k] = leftHalf[i];
			i++;
			k++;
		}
		
		while(j < rightLength)
		{
			inputArray[k] = rightHalf[j];
			j++;
			k++;
		}
	}
}
