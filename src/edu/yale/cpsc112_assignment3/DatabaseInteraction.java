package edu.yale.cpsc112_assignment3;

import java.io.*;
import java.util.Scanner;

public class DatabaseInteraction {
	
	public static void addEntry (String name, double amount) throws IOException
	{
		String addedAmount = new String();
		addedAmount = String.valueOf(amount);
		 FileWriter saveFile = new FileWriter("Entries.txt");
		 saveFile.write(name + "," + addedAmount + "\n");
		 
		 saveFile.close();
	}
	
	public static double sumPersonEntries(String name) throws IOException {
		
		double sum = 0;
		BufferedReader saveFile = new BufferedReader(new FileReader("Entries.txt"));
		while(saveFile.readLine() != null){
			String line = new String();
			line = saveFile.readLine();
			if(line.substring(0,line.indexOf(",")) == name)
			{
				double addAmount = Double.parseDouble(line.substring(line.indexOf(",")+1,line.length()));
				if (addAmount >= 0)
				{
					sum =+ addAmount;
				}
				else if (addAmount < 0)
				{
					sum =- addAmount;
				}
				else
				{
					System.out.println("Please enter a valid number");
				}
			}
		}
		saveFile.close();
		return sum;
	}

}
