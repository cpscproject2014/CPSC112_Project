package edu.yale.cpsc112_assignment3;

import java.io.*;
import java.util.Scanner;

public class DatabaseInteraction {
	
	public static void addEntry (String name, double amount) throws IOException
	{
		String addedAmount = new String();
		addedAmount = String.valueOf(amount);
		 File entries = new File("Entries.txt");
		 File people = new File("People.txt");
		
		//Adds the value to the entries file
		 FileWriter saveFile = new FileWriter(entries);
		 saveFile.write(name + "," + addedAmount + "\n");
		 
		
		 
		 //Adds the name to the People.txt file, to make creating a list of people with balances easier
		 //Unlike saveFile, addName won't add the person's name to the register unless it doesn't exists yet.
		 Scanner scanner = new Scanner(entries);
		 FileWriter addName = new FileWriter(people);
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
			try {
				sums[i] = sumPersonEntries(x);
			} catch (IOException e) {
				System.out.println("There are no entries!");
				
			}
			i++;
		}
		return sums;
	}
	
	public static String[] listNames()
	{
		String[] names = null;
		File entries = new File("People.txt");
		Scanner scanner = null;
		try {
			scanner = new Scanner(entries);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			return null;
		}
		String namesString = new String();
		while (scanner.hasNextLine())
		{
			namesString = scanner.nextLine() + ",";
		}
		names = namesString.split(",");
		
		scanner.close();
		
		return names;
	}
	
	public static double sumPersonEntries(String name) throws IOException {
		File entries = new File("Entries.txt");
		
		double sum = 0;
		BufferedReader saveFile = new BufferedReader(new FileReader(entries));
		if(saveFile.readLine() == null)
		{
			
		}
		while(saveFile.readLine() != null){
			String line = new String();
			line = saveFile.readLine();
			if(line.substring(0,line.indexOf(",")).equals(name))
			{
				double addAmount = Double.parseDouble(line.substring(line.indexOf(",")+1,line.length()));
				if (addAmount >= 0)
				{
					sum =+ addAmount;
				}
				else if (addAmount < 0)
				{
					sum =- Math.abs(addAmount);
				}
				else
				{
					//Some kind of error message;
				}
			}
		}
		saveFile.close();
		return sum;
	}

}