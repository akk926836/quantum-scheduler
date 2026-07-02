/*

Program: ternaryMergeSort.java          Last Date of this Revision: May 14, 2025

Purpose: Implements merge sort algorithm for integer arrays, dividing the array into
two halves recursively and merging them back in sorted order.

Author: Aryan K, 
School: CHHS
Course: Computer Programming 30
 
*/

package Mastery.TernarySearch;

public class ternaryMergeSort {
    /**
     * Sorts the input array using merge sort algorithm
     * @param inputArray The array to be sorted
     */
    public static void mergeSort(int[] inputArray) {
        int length = inputArray.length;
        
        // Base case: array with 0 or 1 elements is already sorted
        if (length < 2) {
            return;
        }
        
        // Split array into left and right halves
        int midIndex = length / 2;
        int[] leftHalf = new int[midIndex];
        int[] rightHalf = new int[length - midIndex];
        
        // Populate left half
        for (int i = 0; i < midIndex; i++) {
            leftHalf[i] = inputArray[i];
        }
        
        // Populate right half
        for (int j = midIndex; j < length; j++) {
            rightHalf[j - midIndex] = inputArray[j];
        }
        
        // Recursively sort both halves
        mergeSort(leftHalf);
        mergeSort(rightHalf);
        
        // Merge the sorted halves
        merge(inputArray, leftHalf, rightHalf);
    }
    
    /**
     * Merges two sorted arrays into one sorted array
     * @param inputArray The target array for merged result
     * @param leftHalf The left sorted array
     * @param rightHalf The right sorted array
     */
    private static void merge(int[] inputArray, int[] leftHalf, int[] rightHalf) {
        int leftLength = leftHalf.length;
        int rightLength = rightHalf.length;
        
        int i = 0, j = 0, k = 0;  // Pointers for left, right, and merged arrays
        
        // Merge while both arrays have elements
        while (i < leftLength && j < rightLength) {
            if (leftHalf[i] < rightHalf[j]) {
                inputArray[k] = leftHalf[i];
                i++;
            } else {
                inputArray[k] = rightHalf[j];
                j++;
            }
            k++;
        }
        
        // Copy remaining elements from left array
        while (i < leftLength) {
            inputArray[k] = leftHalf[i];
            i++;
            k++;
        }
        
        // Copy remaining elements from right array
        while (j < rightLength) {
            inputArray[k] = rightHalf[j];
            j++;
            k++;
        }
    }
}