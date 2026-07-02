package Skillbuilders;

import java.io.Serializable;

public class StuName implements Serializable	{
	
	String fname, lname;

	public StuName(String firstName, String lastName)
	{
		
		fname = firstName;
		lname = lastName;
		
		
	}
	
	public String toString() 
	{
		String name = fname + " " + lname;
		
		return name;
	}
}
