package Skillbuilders;

public class Hockey {

	public static void main(String[] args)
	{
		Puck puck1 = new Puck(1.5,1,5.2);
		
		System.out.println(puck1.toString()); //part 1
		
		Puck puck2 = new Puck(1,2,5.5);
		System.out.println(puck2.toString());
		
		if(puck1.equals(puck2)) // part 1
		{
			System.out.println("Pucks are the same");
		}
		else
		{
			System.out.println("Pucks are not the same");
		}
		
		
		
		if(puck1.compareTo(puck2) == 0)//part 2 --> test comparable interface
		{
			System.out.println("Pucks are the same weight");
		}
		
		else if(puck1.compareTo(puck2) < 0)
		{
			System.out.println("Puck 1 is lighter than Puck 2");
		}
			
		else
		{
			System.out.println("Puck 1 is heavier than Puck 2");
		}
		
	}
}
