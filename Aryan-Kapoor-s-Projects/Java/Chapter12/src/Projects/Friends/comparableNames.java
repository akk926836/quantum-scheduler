/*

zProgram: comparableNames.java          Last Date of this Revision: May 14, 2025

Purpose: Defines an interface for comparing objects by first and last names,
enabling consistent name-based comparison across different classes.

Author: Aryan K, 
School: CHHS
Course: Computer Programming 30
 
*/

package Mastery.Friends;

public class comparableNames {
    public interface ComparableNames {
        int compareToFirstName(Object obj);
        int compareToLastName(Object obj);
    }
}