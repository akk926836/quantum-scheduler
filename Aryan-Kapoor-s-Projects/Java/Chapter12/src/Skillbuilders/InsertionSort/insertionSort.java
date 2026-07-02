package Skillbuilders.InsertionSort;

public class insertionSort {

		public static void insertionSort(Comparable[] items)
		{
			Comparable temp;
			int previousIndex;
			
			for(int index = 1; index < items.length; index++)
			{
				temp = items[index];
				previousIndex = index - 1;
				
				while(previousIndex >= 0 && (items[previousIndex].compareTo(temp) > 0))
				{
					items[previousIndex + 1] = items[previousIndex];
					previousIndex --;
				}
				items[previousIndex + 1] = temp;
			}
		}
}
