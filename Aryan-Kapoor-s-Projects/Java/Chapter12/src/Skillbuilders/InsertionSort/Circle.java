package Skillbuilders.InsertionSort;

public class Circle implements Comparable<Circle>
{
	private static final String PI = "3.14";
	private String radius;
	
	public Circle()
	{
		radius = "1";
	}
	public Circle(String r)
	{
		radius = r;
	}
	public String getRadius()
	{
		return radius;
	}
	public void setRadius(String newR)
	{
		radius = newR;
	}
	
	public String area()
	{
		double r = Double.parseDouble(radius);
		double area = Math.PI * r * r;
		
		return String.valueOf(area);
	}
	
	public boolean equals(Object obj) 
	{
				
	 	if (obj instanceof Circle) 
	 	{
	 		Circle other = (Circle)obj;
	 		return radius.equals(other.radius);
		} 
	 	
	 	return(false);
		
	}
	
	public int compareTo(Circle other)
	{
		double thisRadius = Double.parseDouble(radius);
		double otherRadius = Double.parseDouble(other.radius);
		return Double.compare(thisRadius, otherRadius);
	}
	
	
	
	public String toString() 
	{
		return "Circle has radius " + radius;
	 	
	}
}