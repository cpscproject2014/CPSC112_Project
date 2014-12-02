package edu.yale.cpsc112_assignment3;

import java.io.*;
import java.util.Scanner;

public class DatabaseInteraction {
	
	public static void addEntry (String name, double amount) throws IOException
	{
		String addedAmount = new String();
		addedAmount = String.valueOf(amount);
		
		//Adds the value to the entries file
		 FileWriter saveFile = new FileWriter("Entries.txt");
		 saveFile.write(name + "," + addedAmount + "\n");
		 
		 //Adds the name to the People.txt file, to make creating a list of people with balances easier
		 //Unlike saveFile, addName won't add the person's name to the register unless it doesn't exists yet.
		 Scanner scanner = new Scanner("Entries.txt");
		 FileWriter addName = new FileWriter("People.txt");
		 boolean nameExists = false;
		 while(scanner.hasNextLine())
		 {
			 String nameSubstring = new String(scanner.nextLine().substring(0,scanner.nextLine().indexOf(",")));
			 if(nameSubstring.equals(name))
			 {
				 nameExists = true;
			 }
		 }
		 
		 if(nameExists == true)
		 {
			 addName.write(name + ",");
		 }
		 
		 
		 scanner.close();
		 addName.close();
		 saveFile.close();
	}
	
	public static double[] listSums(String[] names)
	{
		double[] sums = new double[names.length];
		int i = 0;
		for(String x: names)
		{
	// this creates an error -->	sums[i] = sumPersonEntries(x);
			//Exception
			i++;
		}
		return sums;
	}
	
	public static String[] listNames()
	{
		String[] names = null;
		Scanner scanner = new Scanner("People.txt");
		String namesString = scanner.nextLine();
		names = namesString.split(",");
		
		scanner.close();
		return names;
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