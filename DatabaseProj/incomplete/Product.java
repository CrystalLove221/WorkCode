//supplier recording purchases, may cancel the purchase
//if cancelled, shipment tuple is removed (if purchase is already there, it is removed also)
//check if shipment id entered exists and targets the suppliers factory

//most of the information will be auto-entered because the shipment should already exist
//change the holder of the quantity # of products in the product table



import java.util.ArrayList;
import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class Purchase{
	

	public static void main(String[] args){
		
		Scanner robogirl = new Scanner(System.in);
		Connection slave = null;
		while(true){
			System.out.print("Username: ");
			String user = robogirl.nextLine();
			System.out.print("Password: ");
			String pass = robogirl.nextLine();
			
			try{
				slave = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241",user,pass);
				break;
			}
			
			catch(SQLException sql){
				System.out.println("Login failed. Try again. " + sql.getMessage());
			}
		}
		
		
	}
}