package Skillbuilders.SelectionSort;

import java.util.ArrayList;

public class ArrayListSort 
{
	
	public static void displayArray(ArrayList<Double> array)
	{
		for(Double num: array)
			System.out.println(num);
	}
	
	public static void sortArrayListArray()
	{
		final int NUMOBJECTS = 7;
		
		ArrayList<Double> doubleArray = new ArrayList<Double>();
		
		//Populate the arrayList
		for(int i = 0; i < NUMOBJECTS; i++)
		{
			doubleArray.add(new Double(Math.random()));
		}
		System.out.println("Unsorted");
		displayArray(doubleArray);
		
		Sorts.selectionSort(doubleArray);
		System.out.println("Sorted");
		displayArray(doubleArray);
	}
	

	public static void main(String[] args) 
	{
		sortArrayListArray();
	}

}
