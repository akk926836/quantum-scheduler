/*

Program: UEmployee.java          Last Date of this Revision: April 16,2025

Purpose: A base class representing a university employee with basic attributes like name and salary.

Author: Aryan K, 
School: CHHS
Course: Computer Programming 30
 

*/
package Mastery.UEmployee;

public class UEmployee {
	private String fName; //instance variables for name + pay rate.
	private String lName;
	private double pay;

	public UEmployee(String fname, String lname, double sal) //constructor to form an employee object
	{
		fName = fname;
		lName = lname; //initalize variable using parameters
		pay = sal;
	}
	
	public String getName() //method to return university employee's name
	{
		return (fName + " " + lName);
	}
	
	public double getSalary() //method to return employee's salary
	{
		return pay;
	}
}
