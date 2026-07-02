/*

Program: Car.java          Last Date of this Revision: April 16,2025

Purpose: Extends Vehicle to model cars with sport/convertible features and horsepower, while inheriting core vehicle properties.

Author: Aryan K, 
School: CHHS
Course: Computer Programming 30
 

*/
package Mastery.Vehicles;

public class Car extends Vehicle
{
	private boolean isSportsCar;
	private boolean isConvertible;
	private int horsepower;
	
	public Car(boolean sportsCar,boolean convertible,int hp,double fuelCity, double fuelHwy, int seatCap, double cargoVol) // Car constructor
	{
		super(fuelCity,fuelHwy,seatCap,cargoVol);
		
		isSportsCar = sportsCar;
		isConvertible = convertible; //additional features
		horsepower = hp;
	}
	
	public boolean checkSportsCar()//method to check if car is a sports car.
	{
		return isSportsCar;
	}
	
	public boolean checkConvertible() //method to check if car is a convertible.
	{
		return isConvertible;
		
	}
	
	public int getHP() //method to return the car's horsepower.
	{
		return horsepower;
	}
	
	public String getVehicleType() // declaring the abstract method to get the vehicle type.
	{
		String str = "\nVehicle Type: Car";
		return str;
	}
	
	public String toString() //override the method in Vehicle to add additional car-specific details
	{
		String str = "\nHorsepower: " + horsepower + "hp";
		
		if(checkSportsCar()) 
		{
			str += "\nThe car IS a Sports Car";
		}
		else
		{
			str += "\nThe car is NOT a Sports Car";
		}
		
		if(checkConvertible())
		{
			str += "\nThe car IS a Convertible";
		}
		else
		{
			str += "\nThe car is NOT a Convertible";
		}
		
		str += super.toString();
		
		return str;
		
	}
	
}
