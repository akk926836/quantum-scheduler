package Skillbuilders;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;

public class StatsP1 
{

	public static void main(String[] args) 
	{
		File textFile = new File("C:\\Users\\1100102072\\git\\cs30p32025-ak0122\\Chapter11\\src\\Skillbuilders\\test1.txt");
		FileReader in;
		BufferedReader readFile;
		
		String stuName, score;
		double scoreValue;
		double lowScore = 100;
		double highScore = 0;
		double avgScore;
		double totalScore = 0;
		int numScores = 0;
		
		NumberFormat percent = NumberFormat.getPercentInstance();
		
		try
		{
			
			in = new FileReader(textFile);
			readFile = new BufferedReader(in);
			
			while((stuName = readFile.readLine()) != null)
			{
				score = readFile.readLine();
				System.out.println(stuName + " "+ score);
				
				numScores += 1;
				
				scoreValue = Double.parseDouble(score);
				totalScore += scoreValue;
				
				if(scoreValue < lowScore)
					lowScore = scoreValue;
				
				if(scoreValue > highScore)
					highScore = scoreValue;
				
				
			}
			
			avgScore = totalScore / numScores;
			
			System.out.println("Low score = "
							+ percent.format(lowScore/100));
			
			System.out.println("High score = "
					+ percent.format(highScore/100));
			
			System.out.println("Average =" 
					+ percent.format(avgScore/100));
			
			readFile.close();
			in.close();
		}
		catch (FileNotFoundException e) 
		{    
			System.out.println("File could not be found.");      System.err.println("FileNotFoundException: "             + e.getMessage());
		}
		catch (IOException e) 
		{   
			System.out.println("Problem writing to file.");   System.err.println("IOException: " + e.getMessage());  
		}
		

	}

}