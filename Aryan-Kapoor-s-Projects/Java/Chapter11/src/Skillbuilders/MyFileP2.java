package Skillbuilders;

import java.io.*;
import java.util.Scanner;

public class MyFileP2 
{

	public static void main(String[] args)
	{
		File textFile;
		Scanner input = new Scanner(System.in);
		String response;
		
		textFile = new File("C:\\Users\\1100102072\\git\\cs30p32025-ak0122\\Chapter11\\src\\Skillbuilders\\zzz.txt");
		
		if(textFile.exists())
		{
			System.out.println("zzz.txt file exists.");
		}
		else
		{
			try
			{
				textFile.createNewFile();
				System.out.println("zzz.txt file has been created.");
			}
			catch (IOException e) 
			{
				System.out.println("File could not be created.");
    			System.err.println("IOException: " + e.getMessage());
    		}
		}
		
		//Delete if user chooses
		System.out.println("Would you like to (K)eep or (D)elete the file?");
		response = input.next();
		
		if(response.equalsIgnoreCase("D"))
		{
			if(textFile.delete())
			{
				System.out.println("File has been deleted.");
			}
		}
		else
		{
			System.out.println("File is kept and stays the same");
		}

	}

}