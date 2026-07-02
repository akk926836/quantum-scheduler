package Skillbuilders;

import java.io.File;
import java.util.Scanner;

public class MyFileP1 
{

	public static void main(String[] args) 
	{
		File textFile;
		String fileName;
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter the file name: ");
		fileName = input.next();
		
		textFile = new File(fileName);
		
		if(textFile.exists())
		{
			System.out.println("File exists.");
		}
		else
		{
			System.out.println("File does not exists.");
		}

	}

}