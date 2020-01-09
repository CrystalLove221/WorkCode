
/*
Prices, quantities, building numbers

*quantities and buildings are the same file


*/


import java.util.Random;
import java.io.*;

public class MakeData{
	
	public static void main(String[] args){
		
		BufferedWriter out = null;
		Random rand = new Random();
		int lines = 0;
		
		try{
			
			//prices
			
			
			lines = 10000;
			out = new BufferedWriter(new FileWriter("price.txt", true));
			for(int i = 1; i <= lines; i++){
				String data = Double.toString(i / 100.00);
				out.write(data);
				if(i < lines){
					out.newLine();
				}
			}
			out.close();
			
			//numbers
			/*lines = 1000;
			out = new BufferedWriter(new FileWriter("building.txt", true));
			for(int i = 1; i <= lines; i++){
				String data = Integer.toString(i);
				out.write(data);
				if(i < lines){
					out.newLine();
				}
			}
			out.close();*/
			
		}
		catch(Exception e){
			System.out.print("Error: " + e.getMessage());
		}
	}
}