package Skillbuilders;

public class Rectangle implements Comparable<Rectangle>, ComparableArea
{
	private double width;
	private double height;
	private double perimeter;
	private double area;
	
	public Rectangle(double w, double h)
	{
		width = w;
		height = h;
	}
	
	//part 1 start
	public double getWidth()
	{
		return width;
	}
	
	public double getHeight()
	{
		return height;
	}
	
	public double getPerimeter()
	{
		 perimeter = 2*height + 2*width;
		
		return perimeter;
	}
	
	public double getArea()
	{
		area = height*width;
		
		return area;
	}
	//part 1 end
	
	//part 2 start
	public String displayAreaFormula()
	{
		return "The rectangle area formula is: π*r*r";
	}
	//part 2 end
	
	//part 3 start
	public boolean equals(Object o)
	{
		Rectangle testObj = (Rectangle) o;
		
		if(height == testObj.getHeight() && width == testObj.getWidth())
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
		return "The rectangle has a height of " + height + ", a width of " + width + ", a perimeter of" + getPerimeter() + ", and an area of " + getArea();
	}
	//part 3 end
	
	//part 4 start
	@Override
	public int compareTo(Rectangle r)
	{
		Rectangle testObj = r;
		
		if(height == testObj.getHeight() && width == testObj.getWidth())
		{
			return 0;
		}
		
		else
		{
			return -1;
		}
	//part 4 end
		
	}


	//part 5 start
	@Override
	public int compareToArea(Rectangle r2) {
		Rectangle rect2 = r2;
		
		if(area == rect2.getArea())
		{
			return 0;
		}
		
		else if (area < rect2.getArea()) 
		{
			return -1;
		}
		
		else
		{
			return 1;
		}
		//part 5 end
	}
	
	
	
}
