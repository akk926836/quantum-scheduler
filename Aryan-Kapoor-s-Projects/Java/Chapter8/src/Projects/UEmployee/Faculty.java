/*

Program: Faculty.java          Last Date of this Revision: April 16,2025

Purpose: Extends UEmployee to include a department name, representing faculty members at a university.

Author: Aryan K, 
School: CHHS
Course: Computer Programming 30
 

*/
package Mastery.UEmployee;

public class Faculty extends UEmployee //Faculty class inherits UEmployee 
{
	private String dep_name; //new variable for department name
	
	public Faculty(String dep, String fname,String lname, double sal)
	{
		super(fname,lname,sal); //call and initalize superclass
		dep_name = dep;//initalize department name
		
	}
	
	public String getDepName()//method to return department name
	{
		return dep_name;
	}
}
