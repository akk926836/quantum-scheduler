package Skillbuilders;

public class Puck extends Disk implements Comparable
{
	private double weight;
	
	public Puck(double r,double t,double wt)
	{
		super(r,t);
		weight = wt;	
	}
	
	public double getWeight()
	{
		return weight;
	}
	
	public String getDivision()
	{
		if(super.getRadius() == 1.5 && super.getThickness() == 1)
		{
			if(weight > 5 && weight < 5.5)
			{
				return "puck is a STANDARD";
			}
			
			else if (weight > 4 && weight < 4.5)
			{
				return "puck is a YOUTH";
			}
			
			else
			{
				
				return "puck is NOT OFFICIAL";
			}
		}
		
		else
		{
			
			return "puck is NOT OFFICIAL";
		}
	}
	
	public boolean equals(Object o)
	{
		Puck testObject = (Puck) o;
		
		if (testObject.getRadius() == super.getRadius() &&
			testObject.getThickness() == super.getThickness() &&
			testObject.getWeight() == weight)
		{
			return true;
		}
		
		else
		{
			return false;
		}
	}
	
	public String toString()
	{
		return "The puck has a radius of " + super.getRadius()+ "inches, a thickness of " + super.getThickness() 
				+ " inches and a weight of " + weight + " ounces, and the " + getDivision() ;
	}
	
	public int compareTo(Object o)
	{
		Puck testObject = (Puck) o;
		
		if(weight < testObject.getWeight())
		{
			return (-1);
		}
		
		else if (weight> testObject.getWeight())
		{
			return (1);
		}
		
		else
		{
			return 0;
		}
	}
}
