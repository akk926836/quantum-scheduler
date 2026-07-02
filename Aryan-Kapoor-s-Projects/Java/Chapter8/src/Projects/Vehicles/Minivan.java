/*

Program: Minivan.java          Last Date of this Revision: April 16,2025

Purpose: Extends Vehicle to describe minivans, adding a DVD system flag to the base attributes.

Author: Aryan K, 
School: CHHS
Course: Computer Programming 30
 

*/package Mastery.Vehicles;

public class Minivan extends Vehicle
{
	private boolean hasDVDSystem;
	
	public Minivan(boolean DVDSystem,double fuelCity, double fuelHwy, int seatCap, double cargoVol) // minivan constructor
	{
		super(fuelCity,fuelHwy,seatCap,cargoVol);
		hasDVDSystem = DVDSystem;
	}
	
	public boolean checkDVDSystem() //method to check if minivan has a DVD system.
	{
		return hasDVDSystem;
	}
	
	public String getVehicleType() // declaring the abstract method to get the vehicle type.
	{
		String str = "\nVehicle Type: Minivan";
		return str;
	}
	
	public String toString() //override the method in Vehicle to add additional minivan-specific details
	{
		String str = "";
		if(checkDVDSystem()) 
		{
			str += "\nThe Minivan HAS a DVD Entertainment System, ";
		}
		else
		{
			str += "\nThe Minivan does NOT have a DVD Entertainment System, ";
		}
		
		str += super.toString();
		
		return str;
	}
}
