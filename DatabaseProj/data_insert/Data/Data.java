/**
	
	Summary:
	
	@author Tyler Hogue
	@version 3-31-2019

*/

import java.util.ArrayList;
import java.io.*;
import java.sql.*;
import java.util.Scanner;
import java.util.Random;

public class Data {

	static ArrayList<String> readFile(BufferedReader file) {
		
		ArrayList<String> store = new ArrayList<String>();
		try{
			
			String line = null;
			while((line = file.readLine()) != null) {
				store.add(line);
			}
		}
		catch(Exception e){
			System.out.print("Error occurred: " + e.getMessage());
		}
		
		return store;
	}
	
	//retrieves table and makes a list of tuples representing it
	static ArrayList<ArrayList<String>> getData(Connection conn, String table){
		
		ResultSet res = null;
		String query = "select * from " + table;
		ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
		
		try{
			res = conn.createStatement().executeQuery(query);
			int cols = res.getMetaData().getColumnCount();
			
			if(res.next()){
				int index = 0; //tuple index
				do{
					data.add(new ArrayList<String>()); //new tuple
					for(int i = 1; i <= cols; i++){
						data.get(index).add(res.getString(i));
					}
					index++;
				}
				while(res.next());
			}
		}
		catch(Exception e){
			System.out.println("SQL error " + e.getMessage());
			return null;
		}
		
		return data;
	}


	//tuples: # of rows to make
	//pk: primary key column indices
	//table: list of input lists from files, each containing input for their corresponding column index
	static ArrayList<ArrayList<String>> makeData(ArrayList<ArrayList<String>> table, int[] pk, int tuples){
		
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		
		//primary key tracking
		ArrayList<ArrayList<String>> usedpk = new ArrayList<ArrayList<String>>();
		ArrayList<String> currpk = null;
		
		Random rand = new Random();
		
		//make a specified number of tuples for insert
		while(list.size() < tuples){
			
			ArrayList<String> tuple = new ArrayList<String>();
			currpk = new ArrayList<String>();
			
			//go through each column of table, getting random input for column j
			//insert into list if tuples primary key doesn't exist in usedpk already
			for(int j = 0; j < table.size(); j++){
				
				ArrayList<String> attrib = table.get(j); //
				int index = rand.nextInt(attrib.size());
				
				tuple.add(attrib.get(index));
				if(pk[j] == 1){
					currpk.add(attrib.get(index));
				}
			}
			
			if(!usedpk.contains(currpk)){
				list.add(tuple);
				if(currpk.size() > 0){
					usedpk.add(currpk);
				}
			}
		}
		
		return list;
		
	}
	
	//input: tuples to insert
	static void insertData(String query, Connection conn, ArrayList<ArrayList<String>> input)
	{
		
		int num_attribs = input.get(0).size();
		
		System.out.println(query);
		try{
			
			PreparedStatement stmt = conn.prepareStatement(query);
			
			//for every tuple, construct insert and ship it
			for(int i = 0; i < input.size(); i++){
				
				ArrayList<String> tuple = input.get(i);
				System.out.print("Tuple " + i + ": ");
				
				for(int j = 1; j <= num_attribs; j++){
					
					stmt.setString(j, tuple.get(j-1));
					
					if(j < num_attribs){
						System.out.print(tuple.get(j-1) + ", ");
					}
					else{
						System.out.println(tuple.get(j-1));
					}
				}
				//System.out.println();
				
				//send query
				try{
					stmt.executeQuery();
				}
				catch(SQLException sql){
					System.out.println("Query error occurred. " + sql.getMessage());
				}
			}
		}
		catch(Exception e){
			System.out.println("An error occurred. " + e.getMessage());
		}
	}


	public static void main(String[] args){
		
		Scanner robogirl = new Scanner(System.in);
		Connection slave = null;
		while(true){
			System.out.print("Enter the name given to you at birth: ");
			String user = robogirl.nextLine();
			System.out.print("Now, tell me something personal, like your password: ");
			String pass = robogirl.nextLine();
			
			try{
				slave = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241",user,pass);
				System.out.println("Login success!");
				break;
			}
			
			catch(SQLException sql){
				System.out.println("Bzzzzzt! Login failed. Try again! " + sql.getMessage());
			}
		}
		
		//supplier, generic_product
		BufferedReader suppIn;
		BufferedReader productIn;
		BufferedReader priceIn;
		
		//Touhou
		BufferedReader prodX;
		BufferedReader prodXX;
		BufferedReader prodXXX;
		BufferedReader touSupp;
		
		//shared
		BufferedReader buildingIn;
		BufferedReader streetIn;
		BufferedReader cityIn;
		BufferedReader stateIn;
		BufferedReader prodshare;
		
		//Doki
		BufferedReader prodLib;
		BufferedReader prodUni;
		BufferedReader prodNeural;
		
		try{
			
			slave.setAutoCommit(false);
			
			//supplier, generic_product
			suppIn = new BufferedReader(new FileReader("supplier.txt"));
			productIn = new BufferedReader(new FileReader("product.txt"));
			priceIn = new BufferedReader(new FileReader("shared\\price.txt"));
			
			//shared (factory, sexy products)
			buildingIn = new BufferedReader(new FileReader("shared\\building.txt"));
			streetIn = new BufferedReader(new FileReader("shared\\street.txt"));
			cityIn = new BufferedReader(new FileReader("shared\\city.txt"));
			stateIn = new BufferedReader(new FileReader("shared\\state.txt"));
			
			prodshare = new BufferedReader(new FileReader("shared\\product.txt"));
			
			//touhou
			prodX = new BufferedReader(new FileReader("Touhou\\productX.txt"));
			prodXX = new BufferedReader(new FileReader("Touhou\\productXX.txt"));
			prodXXX = new BufferedReader(new FileReader("Touhou\\productXXX.txt"));
			touSupp = new BufferedReader(new FileReader("Touhou\\supplier.txt"));
			
			//Doki
			prodLib = new BufferedReader(new FileReader("Doki\\productLib.txt"));
			prodUni = new BufferedReader(new FileReader("Doki\\productUni.txt"));
			prodNeural = new BufferedReader(new FileReader("Doki\\productNeural.txt"));
		
		}
		catch(Exception e){
			System.out.print("Not found: " + e.getMessage());
			return;
		}
		
		//read shared data into lists
		ArrayList<String> suppliers = readFile(suppIn);
		ArrayList<String> states = readFile(stateIn);
		ArrayList<String> products = readFile(productIn);
		ArrayList<String> prices = readFile(priceIn);
		ArrayList<String> buildings = readFile(buildingIn);
		ArrayList<String> streets = readFile(streetIn);
		ArrayList<String> cities = readFile(cityIn);
		
		/* System.out.println("Suppliers:");
		for(int i = 0; i < suppliers.size(); i++){
			System.out.println(suppliers.get(i));
		} */

		
		int[] pk = new int[5];
		ArrayList<ArrayList<String>> schema = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> tuples = null;
		String query = "";
		String search = "";
		
		//supplier
		query = "insert into supplier(supplier_name) values(?)";
		schema.add(suppliers);//copyList(suppliers));
		pk[0] = 1; // first attrib (index 0) is part of primary key
		tuples = makeData(schema, pk, suppliers.size());
		insertData(query, slave, tuples);
		
		//generic_product
		query = "insert into generic_product(product_name, curr_price) values(?, ?)";
		schema.clear();
		schema.add(products);//copyList(products));
		schema.add(prices);//copyList(prices));
		tuples = makeData(schema, pk, products.size());
		insertData(query, slave,  tuples);
		
		
		//factory
		query = "insert into factory(supplier_name, building_num, street, city, supp_state) values(?,?,?,?,?)";
		schema.clear();
		schema.add(suppliers);//schema.add(copyList(products));
		schema.add(buildings);//schema.add(copyList(products));
		schema.add(streets);//schema.add(copyList(products));
		schema.add(cities);//schema.add(copyList(products));
		schema.add(states);//schema.add(copyList(products));
		pk[0] = 0; pk[1] = 1; pk[2] = 1; pk[3] = 1; pk[4] = 1;
		
		tuples = makeData(schema, pk, 30);
		insertData(query, slave, tuples);
		
		
		//prodgroup (who makes what)
		query = "insert into prodgroup(supplier_name, product_name) values(?,?)";
		
		//shared (prodshare, suppliers)
		ArrayList<String> productshare = readFile(prodshare);
		schema.clear();
		schema.add(suppliers);
		schema.add(productshare);
		pk = new int[5];
		
		tuples = makeData(schema, pk, 30);
		insertData(query, slave, tuples);
		
		//specialized
		ArrayList<String> productX = readFile(prodX);
		ArrayList<String> productXX = readFile(prodXX);
		ArrayList<String> productXXX = readFile(prodXXX);
		ArrayList<String> touhouSupp = readFile(touSupp);
		
		/* System.out.println("ProductX:");
		for(int i = 0; i < productX.size(); i++){
			System.out.println(productX.get(i));
		}
		System.out.println("ProductXX:");
		for(int i = 0; i < productXX.size(); i++){
			System.out.println(productXX.get(i));
		}
		System.out.println("ProductXXX:");
		for(int i = 0; i < productXXX.size(); i++){
			System.out.println(productXXX.get(i));
		} */
		
		
		schema.clear();
		schema.add(new ArrayList<String>());
		
		//touhou X (productX)
		schema.get(0).add("Touhou X");
		schema.add(productX);
		
		tuples = makeData(schema, pk, 10);
		insertData(query, slave, tuples);
		
		//touhou XX (productX/XX)
		schema.get(0).set(0, "Touhou XX");
		schema.set(1, productXX);
		
		tuples = makeData(schema, pk, 10);
		insertData(query, slave, tuples);
		
		//touhou XXX (productX/XX/XXX)
		schema.get(0).set(0, "Touhou XXX");
		schema.set(1, productXXX);
		
		tuples = makeData(schema, pk, 10);
		insertData(query, slave, tuples);
		
		
		//product
		//ArrayList<String> cols = new ArrayList<String>();
		query = "insert into product(groupid, supplier_name, product_name) values(?,?,?)";
		
		//get prodgroups
		/* cols.add("groupid");
		cols.add("supplier_name");
		cols.add("product_name"); */
		ArrayList<ArrayList<String>> foreign = getData(slave, "prodgroup");
		
		//multiple product ids in each group
		ArrayList<ArrayList<String>> copyForeign = new ArrayList<ArrayList<String>>();
		for(int i = 0; i < 5; i++){		
			copyForeign.addAll(foreign);
		}
		insertData(query, slave, copyForeign);
		copyForeign = null;
		//cols.clear();
		
		//made_of (randomly paired)
		query = "insert into made_of(groupid, part_grpid) values(?,?)";
		
		//group ids
		ArrayList<String> groups = new ArrayList<String>();
		for(int i = 0; i < foreign.size(); i++){
			groups.add(foreign.get(i).get(0));
		}
		foreign.clear();
	
		int index = 0; //current tuple
		Random rand = new Random();
		for(int i = 0; i < groups.size(); i++){
			int parts = rand.nextInt(10); // # of parts for prodgroup
			int count = 0; //curr # of parts
			while(count < parts){
				int randindex = rand.nextInt(groups.size()); //part group id
				if(randindex != i){
					foreign.add(new ArrayList<String>()); //new tuple
					
					foreign.get(index).add(groups.get(i)); //groupid
					foreign.get(index).add(groups.get(randindex)); //part_grpid
					count++;
					index++;
				}
			}
		}
		insertData(query, slave, foreign);
		foreign.clear();
		
		
		//sells_to
		
		//among non-touhou suppliers (each sells to every other nontouhou supplier)
		query = "insert into sells_to(supplier_name, buyer_name) values(?,?)";
		suppliers.removeAll(touhouSupp);
		
		index = 0; //current tuple
		for(int i = 0; i < suppliers.size(); i++){
			for(int j = 0; j < suppliers.size(); j++){
				if(j != i){
					foreign.add(new ArrayList<String>()); //new tuple
					
					foreign.get(index).add(suppliers.get(i)); //supplier
					foreign.get(index).add(suppliers.get(j)); //buyer
					index++;
				}
			}
		}
		insertData(query, slave, foreign);
		suppliers.addAll(touhouSupp);
		foreign.clear();
		
		//among touhou suppliers
		index = 0;
		for(int i = 0; i < touhouSupp.size(); i++){
			for(int j = 0; j < touhouSupp.size(); j++){
				if(j != i){
					foreign.add(new ArrayList<String>()); //new tuple
					
					foreign.get(index).add(touhouSupp.get(i)); //supplier
					foreign.get(index).add(touhouSupp.get(j)); //buyer
					index++;
				}
			}
		}
		insertData(query, slave, foreign);
		foreign.clear();
		
		try{
			suppIn.close();
			buildingIn.close();
			streetIn.close();
			productIn.close();
			priceIn.close();
			
			prodX.close();
			prodXX.close();
			prodXXX.close();
			touSupp.close();
			
			System.out.print("Did populator run successfully? ");
			if(robogirl.nextLine().equals("n")){
				System.out.print("rollback");
				slave.rollback();
			}
			else{
				System.out.print("Commit");
				slave.commit();
			}
			slave.close();
		}
		catch(Exception e){
			System.out.print("Rollback failed. This isn't good. Shit... Now what??... " + e.getMessage());
		}
	}
}