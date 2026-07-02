/*

Program: CreateVehicle.java          Last Date of this Revision: April 16,2025

Purpose: A demo class that instantiates and prints details of a car, truck, and minivan to showcase their inherited and specialized features.

Author: Aryan K, 
School: CHHS
Course: Computer Programming 30
 

*/package Mastery.Vehicles;

public class CreateVehicle 
{
	public static void main(String[] args)
	{
		Car coolCar = new Car(true,false,250,25,35,2,15); //create car object
		
		Truck coolTruck = new Truck(true,1000,5.5,20,25,6,20);//create vehicle object
		
		Minivan coolMinivan = new Minivan(false,17,22,8,30);//create minivan object

		for(int i = 0;i<200;i++)
		{
			System.out.print("=");	//add dividers
		}
		
		System.out.println(coolCar.getVehicleType()); //display vehicle type as car.
		System.out.println(coolCar);//display car-specific details
		
		for(int i = 0;i<200;i++)
		{
			System.out.print("=");
		}
		
		System.out.println(coolTruck.getVehicleType());//display vehicle type as truck.
		System.out.println(coolTruck);//display truck-specific details
		
		for(int i = 0;i<200;i++)
		{
			System.out.print("=");
		}
		
		System.out.println(coolMinivan.getVehicleType());//display vehicle type as minivan.
		System.out.println(coolMinivan);//display minivan-specific details
	}
}

/*
 * ScreenDump:
 * ========================================================================================================================================================================================================
Vehicle Type: Car

Horsepower: 250hp
The car IS a Sports Car
The car is NOT a Convertible
Fuel Economy (City): 25.0 mpg
Fuel Economy (Highway): 35.0 mpg
Seating Capacity: 2
Cargo Volume: 15.0 cubic ft
========================================================================================================================================================================================================
Vehicle Type: Truck

Towing Capacity: 1000.0lbs
Bed Length: 5.5ft
The Truck HAS Four-Wheel-Drive
Fuel Economy (City): 20.0 mpg
Fuel Economy (Highway): 25.0 mpg
Seating Capacity: 6
Cargo Volume: 20.0 cubic ft
========================================================================================================================================================================================================
Vehicle Type: Minivan

The Minivan does NOT have a DVD Entertainment System, 
Fuel Economy (City): 17.0 mpg
Fuel Economy (Highway): 22.0 mpg
Seating Capacity: 8
Cargo Volume: 30.0 cubic ft

*/


