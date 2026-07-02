/*

Program: Staff.java          Last Date of this Revision: April 16,2025

Purpose: Extends UEmployee to add a job title, representing non-faculty university staff members.

Author: Aryan K, 
School: CHHS
Course: Computer Programming 30
 

*/
package Mastery.UEmployee;

public class Staff extends UEmployee //staff class inherits UEmployee
{
	private String job_name; //new variable for job name/position.
	
	public Staff(String job, String fname, String lname, double sal)//constructor for Staff subclass
	{
		super(fname,lname,sal); //call and initialize superclass
		
		job_name = job; //initalize job position using additional parameter
	}
	
	public String getJobName() //method to return job name.
	{
		return job_name;
	}
}
