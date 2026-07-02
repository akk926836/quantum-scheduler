/*

Program: Vehicle.java          Last Date of this Revision: April 16,2025

Purpose:  An abstract base class defining common vehicle attributes (fuel economy, seating, cargo) and requiring subclasses to specify their vehicle type.

Author: Aryan K, 
School: CHHS
Course: Computer Programming 30
 

*/
package Mastery.Vehicles;

abstract class Vehicle 
{
	private double fuelEconomyCity;
	private double fuelEconomyHwy;	// basic/general car information
	private int seatCapacity;
	private double cargoVolume;
	
	Vehicle(double fuelCity, double fuelHwy, int seatCap, double cargoVol) //constructor to declare & initialize the car information to a set value.
	{
		fuelEconomyCity = fuelCity;
		fuelEconomyHwy = fuelHwy; // initialization
		seatCapacity = seatCap;
		cargoVolume = cargoVol;
	}
	
	public double getFuelEconomyCity()//method to return the vehicle's fuel economy in the city.
	{
		return fuelEconomyCity;
	}
	
	public double getFuelEconomyHwy()//method to return the vehicle's fuel economy in the city.
	{
		return fuelEconomyHwy;
	}
	
	public int getSeatCapacity() //method to return the vehicle's seat capacity.
	{
		return seatCapacity;
	}
	
	public double getCargoVolume()//method to return the vehicle's cargo volume.
	{
		return cargoVolume;
	}
	
	
	public String toString() //method to return the vehicle's general information in a well organized string.
	{
        return "\nFuel Economy (City): " + fuelEconomyCity + " mpg" +
               "\nFuel Economy (Highway): " + fuelEconomyHwy + " mpg" +
               "\nSeating Capacity: " + seatCapacity +
               "\nCargo Volume: " + cargoVolume + " cubic ft";
        
   
	}
	abstract String getVehicleType(); //abstract method for different vehicle types to display what kind of vehicle they are.
}
