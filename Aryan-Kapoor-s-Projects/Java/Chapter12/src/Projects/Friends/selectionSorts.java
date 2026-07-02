/*

Program: selectionSorts.java          Last Date of this Revision: May 14, 2025

Purpose: Provides static methods for sorting ArrayList of ComparableNames objects
by first or last name using selection sort algorithm.

Author: Aryan K, 
School: CHHS
Course: Computer Programming 30
 
*/

package Mastery.Friends;

import java.util.ArrayList;
import Mastery.Friends.comparableNames.ComparableNames;

public class selectionSorts {
    // Sorts by first name
    public static void sortFirst(ArrayList items) {
        for (int index = 0; index < items.size(); index++) {
            for (int subIndex = index; subIndex < items.size(); subIndex++) {
                ComparableNames item1 = (ComparableNames) items.get(subIndex);
                ComparableNames item2 = (ComparableNames) items.get(index);

                if (item1.compareToFirstName(item2) < 0) {
                    ComparableNames temp = item2;
                    items.set(index, item1);
                    items.set(subIndex, temp);
                }
            }
        }
    }

    // Sorts by last name
    public static void sortLast(ArrayList items) {
        for (int index = 0; index < items.size(); index++) {
            for (int subIndex = index; subIndex < items.size(); subIndex++) {
                ComparableNames item1 = (ComparableNames) items.get(subIndex);
                ComparableNames item2 = (ComparableNames) items.get(index);

                if (item1.compareToLastName(item2) < 0) {
                    ComparableNames temp = item2;
                    items.set(index, item1);
                    items.set(subIndex, temp);
                }
            }
        }
    }
}