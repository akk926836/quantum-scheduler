
package Skillbuilders.MergeSort;

import java.util.Random;

import Skillbuilders.InsertionSort.Circle;

public class objectMergeSort 
{
	
	public static void main(String[] args)
	{
		Random rand = new Random();
		Circle circ;
		
		Circle[] list = new Circle[10];
		
		System.out.println("Unsorted: ");
		
		for(int i = 0; i < list.length ;i++)
		{
			int num = rand.nextInt(100);
			String radius = String.valueOf(num);
			circ = new Circle(radius);
			list[i] = circ;
			System.out.println(list[i]);
		}
		
		
		System.out.println("Sorted: ");
		
		mergeSort(list);
		
		for(int i = 0;i <list.length;i++)
		{
			System.out.println(list[i]);
		}
		
	}
	
	
	private static void mergeSort(Comparable[] inputArray) 
	{
		int length = inputArray.length;
		
		if(length < 2)
		{
			return;
		}
		
		int midIndex = length/2;
		
		Comparable[] leftHalf = new Comparable[midIndex];
		Comparable[] rightHalf = new Comparable[length - midIndex];
		
		
		for(int i = 0; i < midIndex;i++)
		{
			leftHalf[i] = inputArray[i];
		}
		
		for(int j= midIndex; j < length;j++)
		{
			rightHalf[j-midIndex] = inputArray[j];
		}
		
		mergeSort(leftHalf);
		mergeSort(rightHalf);
		
		merge(inputArray, leftHalf,rightHalf);
		
	}
	
	private static void merge(Comparable[] inputArray, Comparable[] leftHalf, Comparable[] rightHalf)
	{
		int leftLength = leftHalf.length;
		int rightLength = rightHalf.length;
		
		
		int i=0,j=0,k=0;
		while(i < leftLength && j < rightLength)
		{
			if(leftHalf[i].compareTo(rightHalf[j]) < 0)
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