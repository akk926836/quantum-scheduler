
/*

Program: Truck.java          Last Date of this Revision: April 16,2025

Purpose: Extends Vehicle to represent trucks with 4WD capability, towing capacity, and bed length.

Author: Aryan K, 
School: CHHS
Course: Computer Programming 30
 

*/package Mastery.Vehicles;

public class Truck extends Vehicle
{
	private boolean has4WD;
	private double towCapacity;
	private double bedLength;
	
	public Truck(boolean fourWD,double towCap,double bLength,double fuelCity, double fuelHwy, int seatCap, double cargoVol)
	{
		super(fuelCity,fuelHwy,seatCap,cargoVol);			// Truck constructor
		
		has4WD = fourWD;
		towCapacity = towCap;
		bedLength = bLength;
	}
	
	public boolean check4WD() //method to check if Truck has four wheel drive.
	{
		return has4WD;
	}
	
	public double getTowCapacity() //method to return truck towing capacity.
	{
		return towCapacity;
	}
	
	public double getBedLength() //method to return the length of the truck's bed.
	{
		return bedLength;
	}
	
	public String getVehicleType() // declaring the abstract method to get the vehicle type.
	{
		String str = "\nVehicle Type: Truck";
		return str;
	}
	
	public String toString() //override the method in Vehicle to add additional truck-specific details
	{
		String str = "\nTowing Capacity: " + towCapacity+ "lbs\nBed Length: " + bedLength + "ft";
		if(check4WD()) 
		{
			str += "\nThe Truck HAS Four-Wheel-Drive";
		}
		else
		{
			str += "\nThe Truck does NOT have Four-Wheel-Drive";
		}
		
		str += super.toString();
		
		return str;
		
	}
}
