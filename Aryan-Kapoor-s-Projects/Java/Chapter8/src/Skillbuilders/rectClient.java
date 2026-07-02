package Skillbuilders;

public class rectClient 
{
	public static void main(String[] args)
	{
		Rectangle rect1 = new Rectangle(4,7);
		Rectangle rect2 = new Rectangle(2,14);
		
		System.out.println(rect1.toString());
		System.out.println(rect2.toString());
		
		/* part 3
		if(rect1.equals(rect2))
		{
			System.out.println("Both rectangles are equal");
		}
		else
		{
			System.out.println("Rectangles are not equal");
		}
		*/
		
		//part 4
		if(rect1.compareTo(rect2) == 0)
		{
			System.out.println("Both rectangles are equal");
		}
		else
		{
			System.out.println("Rectangles are not equal");
		}
		
		//part 5
		if(rect1.compareToArea(rect2) == 0)
		{
			System.out.println("Both rectangles have equal areas");
		}
		else if(rect1.compareToArea(rect2) < 0)
		{
			System.out.println("Rectangle 1 has a smaller area than Rectangle 2");
		}
		
		else
		{
			System.out.println("Rectangle 1 has a larger area than Rectangle 2");
		}
	}
}
