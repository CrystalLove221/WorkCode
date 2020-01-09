/**
	
	Summary:
	
	user is asked whether they want to ship a product or record the purchase of a product.
	
	shipment: User enters information about the shipment. Checks against data in the database to make sure data already exists.
	purchase: asked to enter shipment_id. Checked if it exists and if shipment targets the particular supplier
		user may revoke a purchase (before/after receiving package: before/after entering a purchase,
		in which case the shipment and/or purchase tuple is(are) removed (and products put back)
	
	@author Tyler Hogue
	@version 4-28-2019

*/

import java.util.ArrayList;
//import java.util.Date;
import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class Shipment{
	
	
	//we might not need findRow because getData can result null for empty tables and for errors
	
	
	static ResultSet getData(Connection conn, String query){
		
		ResultSet data = null;
		
		try{
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
													ResultSet.CONCUR_READ_ONLY);
			data = stmt.executeQuery(query);
			
			if(!data.next()){
				return null;
			}
		}
		catch(Exception e){
			System.out.println("SQL error " + e.getMessage());
			return null;
		}
		
		return data;
	}
	
	//check multiple columns (cols) in an existing ResultSet for attrib combination (input)
	static boolean findRow(ResultSet data, ArrayList<String> cols, ArrayList<String> input){
		
		try{
			data.first();
			do{
				ArrayList<String> combo = new ArrayList<String>();
				for(int i = 0; i < cols.size(); i++){
					combo.add(data.getString(cols.get(i)));
				}
				
				if(combo.equals(input)){
					return true;
				}
			} while(data.next());
		}
		catch(Exception e){
			System.out.println("Read error. " + e.getMessage());
		}
		
		return false;
	}
	
	//check if any tuple results from query
	//use regular statment, unprepared
	static boolean findRow(Connection conn, String query){
		
		ResultSet res = null;
		
		try{
			Statement stmt = conn.createStatement();
			res = stmt.executeQuery(query);
			
			if(!res.next()){
				return false;
			}
		}
		catch(Exception e){
			System.out.println("Query error: " + e.getMessage());
			return false;
		}
		
		return true;
	}
	
	//check if a tuple resulting from the query contains the set of sttribute values (input)
	//use prepared statements
	static boolean findRow(Connection conn, String query, ArrayList<String> input){
		
		ResultSet res = null;
		
		try{
			PreparedStatement stmt = conn.prepareStatement(query);
			
			for(int i = 0; i < input.size(); i++){
				stmt.setString(i+1, input.get(i));
			}
			
			res = stmt.executeQuery();
			
			if(!res.next()){
				return false;
			}
		}
		catch(Exception e){
			System.out.println("Query error: " + e.getMessage());
			return false;
		}
		
		return true;
	}
	
	//determine if table contains some value in some column
	static boolean findRow(ResultSet table, int col, String val){
		try{
			table.first();
			do{
				if(table.getString(col).equals(val)){
					return true;
				}
			} while(table.next());
		}
		catch(Exception e){
			System.out.println("Query error: " + e.getMessage());
		}
		
		return false;
	}
	
	//count # of rows returned from the query
	static int count(Connection conn, String query){
		
		ResultSet res = null;
		int cnt = 0;
		try{
			Statement stmt = conn.createStatement();
			res = stmt.executeQuery(query);
			
			if(!res.next()){
				return 0;
			}
			
			cnt = Integer.parseInt(res.getString(1));
		}
		catch(Exception e){
			System.out.println("Error: " + e.getMessage());
		}
		
		return cnt;
	}
	
	//print table in a clean format
	static void printResult(ResultSet table){
		
		
		if(table != null){
			try{
				ResultSetMetaData meta = table.getMetaData();
				int cols = meta.getColumnCount();
				//System.out.println("# of Columns: " + cols);
				
				ArrayList<Integer> max = new ArrayList<Integer>(cols);
				
				//set max len to size of column names if all values are small strings
				for(int i = 0; i < cols; i++){
					max.add(meta.getColumnName(i+1).length());
					//System.out.println("Initial Column " + i + " size: " + max.get(i));
				}
				
				table.first();
				//find max length tuple col for proper col alignment
				do{
					for(int i = 0; i < cols; i++){
						String val = table.getString(i+1);
						if(max.get(i) < val.length()){
							max.set(i, val.length());
							
						}
						//System.out.println("Current Column " + i + " size: " + max.get(i));
					}
				} while(table.next());
				
				table.first();
				
				//print table columns
				for(int i = 1; i <= cols; i++){
					String name = meta.getColumnName(i);
					if(i < cols){
						System.out.printf("%-" + (max.get(i-1) + 3) + "s", name);
					}
					
					else{
						System.out.println(name);
					}
				}
				
				do{
					
					for(int i = 1; i <= cols; i++){
						String val = table.getString(i);
						if(i < cols){
							//String fmt = "%-" + align + "";
							System.out.printf("%-" + (max.get(i-1) + 3) +  "s", val);
						}
						
						else{
							System.out.println(val);
						}
					}
				} while(table.next());
			}
			catch(Exception e){
				System.out.println("Error: " + e.getMessage());
			}
		}
		else{
			System.out.println("Empty");
		}
	}
	
	//ask if the user would like to have some set of tables printed for reference
	static boolean printHelp(ArrayList<ResultSet> tables, Scanner read){
		
		String[] opt = new String[2];
		opt[0] = "Y";
		opt[1] = "N";
		int resp = prompt(opt, read);
		if(resp == 1){
			System.out.println();
			for(int i = 0; i < tables.size(); i++){
				printResult(tables.get(i));
				System.out.println();
			}
			return true;
		}
		
		return false;
	}
	
	//ask user what they want to do
	static int prompt(String[] opt, Scanner read){
		
		while(true){
			System.out.print("--> ");
			String ans = read.nextLine().toUpperCase();
			if(ans.length() != 1){
				System.out.println("What the fuck is this? A joke? Well, I can tell a joke, too.");
				System.out.println("How many programmers does it take to fix a bug? ... " + (Integer.MAX_VALUE+1));
			}
			
			for(int i = 0; i < opt.length; i++){
				if(ans.equals(opt[i])){
					return i;
				}
			}
		}
	}
	
	//user integer input (for ids)
	static String input(int min, int max, Scanner read){
		
		while(true){
			System.out.print("--> ");
			try{
				String str = read.nextLine();
				
				//empty input always cancels the current task (updates to the database)
				if(str.isEmpty()){
					return str;
				}
				
				int id = Integer.parseInt(str);
				if(id < min || id > max){
					System.out.println("Number is out of range. " + min + "-" + max);
				}
				else{
					return str;
				}
				
			}
			catch(Exception e){
				System.out.println("Value not a number");
			}
		}
	}
	

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
		
		ArrayList<String> shipment = new ArrayList<String>();
		
		boolean go = true; //whether or not to keep program running
		String query;
		String msg;
		boolean[] printed = new boolean[1];
		
		String[] opt = null;
		while(go){
			
			boolean quit = false; // whether or not to quit current interface (purchase or shipment)
			boolean ship = false; //false: record purchases (receipts), true: record shipments
			
			opt = new String[3];
			opt[0] = "S";
			opt[1] = "P";
			opt[2] = "Q";
			System.out.println("Manage shipments or purchases? Or quit and continue grading other projects (S/P/Q) ");
			int resp = prompt(opt, robogirl);
			if(resp == 0){
				ship = true;
			}
			if(resp == 2){
				break;
			}
		
			ArrayList<String> cols = new ArrayList<String>();
			ArrayList<String> input = new ArrayList<String>();
			ArrayList<ResultSet> tables = new ArrayList<ResultSet>(2);
			
			//record shipments
			if(ship){
				
				shipment.clear();
				
				query = "select * from supplier";
				ResultSet suppliers = getData(slave, query);
				tables.add(getData(slave, query));
				
				query = "select * from sells_to";
				ResultSet sells_to = getData(slave, query);
				tables.add(getData(slave, query));
				
				if(!printed[0]){
					printed[0] = true;
					printResult(suppliers);
					System.out.println();
					printResult(sells_to);
				}
				
				while(!quit && ship){
					
					//supplier, target
					
					int fail = 0;
					cols.add("supplier_name");
					ResultSet prodgrps = null;
					ResultSet addresses = null;
					boolean done = false;
					while(!quit && !done){
						
						System.out.print("Enter supplier name ");
						String supplier = robogirl.nextLine();
						input.clear();
						
						if(supplier.isEmpty()){
							quit = true;
							break;
							
						}
						else{
							//figure out if input exists in a tuple in current database
							
							input.add(supplier);
							query = "select * from supplier";
							if(!findRow(suppliers, 1, supplier)){
								System.out.println("Supplier not found. What the hell?!");
								continue;
							}
							else{
								//check if supplier made/holds a product. If not, force choosing a new supplier.
								query = "select * from prodgroup where supplier_name = '" + input.get(0) + "'";
								prodgrps = getData(slave, query);
								if(prodgrps == null){
									System.out.println("Supplier made/holds no products. Choose another supplier.");
									continue;
								}
								else{
									shipment.add(supplier); //index 0
								}
							}
						}
						input.clear();
						
						while(true){
							System.out.print("Enter target of shipment ");
							String target = robogirl.nextLine();
							input.clear();
							
							if(target.isEmpty()){
								quit = true;
								break;
								
							}
							else if(target.equals(shipment.get(0))){
								System.out.println("Why would any supplier sell to itself?");
							}
							else
							{
								input.add(target);
								
								if(!findRow(suppliers, 1, target)){
									System.out.println("Supplier not found. Check again. Or else...");
								}
								else{
									query = "select * from factory where supplier_name = '" + input.get(0) + "'";
									addresses = getData(slave, query);
									if(addresses == null){
										System.out.println("Unfortunately, the supplier does not yet have a factory for its operations.");
									}
									else{
										//check if supplier sells to target
										query = "select * from sells_to where supplier_name = '"
												+ shipment.get(0) + "' and buyer_name = '" + target + "'";
										if(!findRow(slave, query)){
											System.out.println(shipment.get(0) + " does not sell to " + target + "... :/");
										}
										else{
											shipment.add(target);
											done = true;
											break;
										}
									}
								}
							}
						}
						input.clear();
					}
					if(quit){
						break;
					}
					cols.clear();
					fail = 0;
					
					//addresses
					tables.clear();
					printResult(addresses);
					
					while(true){
						
						System.out.println("Enter building number (1-999) ");
						String building = input(1, 999, robogirl);
						if(building.isEmpty()){
							quit = true;
							break;
						}
						
						
						System.out.print("Enter street ");
						String street = robogirl.nextLine();
						
						System.out.print("Enter city ");
						String city = robogirl.nextLine();
						
						System.out.print("Enter state ");
						String state = robogirl.nextLine();
						
						if(state.isEmpty() && city.isEmpty() && street.isEmpty()){
							quit = true;
							break;
						}
						
						//does target supplier occupy address?
						query = "select * from factory where supplier_name = '" + shipment.get(1) + "' and building_num = '" + building
								+ "' and street = ? and city = ? and supp_state = ?";
								
						input.add(street);
						input.add(city);
						input.add(state);
						
						if(!findRow(slave, query, input)){
							System.out.println("Supplier does not occupy the address.");
						}
						else{
							shipment.add(building); //index 2
							shipment.add(street); //3
							shipment.add(city); //4
							shipment.add(state); //index 5
							input.clear();
							break;
						}
						input.clear();
					}
					fail = 0;
					if(quit){
						break;
					}
					
					
					// product group id
					tables.add(prodgrps);
					printResult(prodgrps);
					
					cols.add("groupid");
					cols.add("supplier_name");
					while(true){
						
						System.out.println("Master, please enter group id of products we are selling (1-999) ");
						String grpid = input(1, 999, robogirl);
						if(grpid.isEmpty()){
							quit = true;
							break;
						}
						
						//did supplier make product?
						input.add(grpid);
						input.add(shipment.get(0));
						if(!findRow(prodgrps, cols, input)){
							System.out.println("Your supplier did not make this product group. Wanna steal it from others? :)");
						}
						else{
							shipment.add(grpid); //index 6
							break;
						}
						input.clear();
					}
					fail = 0;
					if(quit){
						break;
					}
					
					//count # of products in database (product table)
					query = "select count(*) from product where groupid = '" + shipment.get(6) + "' group by groupid";
					int supply = count(slave, query);
					
					//quantity input
					String amt = "";
					
					System.out.println("How much are we selling, master? We have " + supply + " in stock ");
					amt = input(1, supply, robogirl);
					if(amt.isEmpty()){
						quit = true;
						System.out.println("          .,\n"
										+ ".      _,'f----.._\n"
										+ "|\\ ,-'\"" + "/  |     ,'\n"
										+ "|,_  ,--.      /\n"
										+ "/,-. ,'`.     (_\n"
										+ "f  o|  o|__     \"" + "`-.\n"
										+ ",-._.,--'_ `.   _.,-`\n"
										+ "`\"" + "' ___.,'` j,-'\n"
										+ "  `-.__.,--'");
						
						break;
					}
					shipment.add(amt); //index 7
					
					
					//*********may need to add input function for doubles and floats
					//unit_price
					Double price = 0.00;
					while(true){
						try{
							System.out.print("Price of a unit of product ");
							String str = robogirl.nextLine();
							if(str.isEmpty()){
								quit = true;
								break;
							}
							
							price = Double.parseDouble(str);
							
							if(price == 0.00){
								System.out.println("Nothing is free.");
							}
							else if(price < 0.00){
								System.out.println("What the hell? We're losing money selling at this price!");
							}
							else{
								shipment.add(str); //index 8
								break;
							}
							
						}
						catch(Exception e){
							System.out.println("Price must be a decimal value.");
						}
					}
					if(quit){
						break;
					}
					//record shipment
					
					query = "insert into shipment(supplier_name, target_name, building_num, street, "
							+ "city, supp_state, groupid, quantity, unit_price, time_sent) "
							+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?, systimestamp)";
					
					try{
						PreparedStatement stmt = slave.prepareStatement(query);
						stmt.setString(1, shipment.get(0));
						stmt.setString(2, shipment.get(1));
						stmt.setString(3, shipment.get(2));
						stmt.setString(4, shipment.get(3));
						stmt.setString(5, shipment.get(4));
						stmt.setString(6, shipment.get(5));
						stmt.setString(7, shipment.get(6));
						stmt.setString(8, shipment.get(7));
						stmt.setString(9, shipment.get(8));
						
						int cnt = stmt.executeUpdate();
						System.out.println(cnt + " tuples inserted.");
					}
					catch(Exception e){
						System.out.println("Insert failed. " + e.getMessage());
					}
					
					//ask user if they want to continue entering shipments
					opt = new String[3];
					opt[0] = "Y";
					opt[1] = "N";
					opt[2] = "Q";
					
					System.out.println("Do you want to continue entering shipments, Yan-chan? (Y/N/Q)? ");
					resp = prompt(opt, robogirl);
					if(resp == 0){
						shipment.clear();
					}
					else{
						if(resp == 2){
							System.out.println("Thank you so much for killing me! I always wanted to be killed by my master!");
							go = false;
						}
						ship = false;
					}
				}
			}
			
			//record or remove purchases
			else{
				boolean flog = false;
				while(!ship){
					
					flog = false;
					
					//add or remove
					opt = new String[2];
					opt[0] = "A";
					opt[1] = "R";
					
					System.out.println("Add or remove purchase id? (A/R) ");
					resp = prompt(opt, robogirl);
					if(resp == 1){
						flog = true;
					}
					
					//remove purchase
					if(flog){
						
						//print Purchases for reference
						//System.out.println("Purchase:");
						query = "select * from purchase";
						printResult(getData(slave, query));
						
						//enter purchase id
						String id = "";
						while(true){
							
							System.out.println("Enter id of purchase (3000-3999) ");
							id = input(3000, 3999, robogirl);
							if(id.isEmpty()){
								//quit
								quit = true;
								break;
							}
							
							query = "select * from purchase where purchase_id = '" + id + "'";
							if(!findRow(slave, query)){
								System.out.println("Purchase id not found.");
							}
							else{
								break;
							}
						}
						if(quit){
							break;
						}
						
						int cnt = 0;
						try{
							
							//get shipment id to delete it
							query = "select * from shipment natural join purchase";
							ResultSet ships = getData(slave, query);
							
							String ship_id = "";
							do{
								String purc_id = ships.getString("purchase_id");
								if(purc_id.equals(id)){
									ship_id = ships.getString("shipment_id");
									break;
								}
							} while(ships.next());
							
							Statement stmt = slave.createStatement();
							
							//remove purchase
							//query = "delete from purchase where purchase_id = '" + id + "'";
							//cnt = stmt.executeUpdate(query);
							
							//remove shipment
							query = "delete from shipment where shipment_id = '" + ship_id + "'";
							cnt = stmt.executeUpdate(query);
							
							System.out.println(cnt + " tuples updated");
						}
						catch(Exception e){
							System.out.println("SQL error. " + e.getMessage());
						}
					}
					
					//add purchase
					else{
						
						//print shipments for reference
						//System.out.println("Shipments:");
						query = "select * from shipment";
						printResult(getData(slave, query));
						
						String ship_id = "";
						while(true){
							
							System.out.println("Enter shipment id (2000-2999) ");
							ship_id = input(2000,2999,robogirl);
							if(ship_id.isEmpty()){
								quit = true;
								break;
							}
							
							
							query = "select * from shipment where shipment_id = '" + ship_id + "'";
							if(findRow(slave, query)){
								//check for existing shipment_id in current purchases table
                            	query = "select * from purchase where shipment_id = '" + ship_id + "'";
                            	if(findRow(slave, query)){
									System.out.println("Ha! Thought you could buy something twice! Think again!");
								}
								else{
									break;
								}
							}
							else{
								System.out.println("Shipment_id not found.");
							}
						}
						if(quit){
							break;
						}
						
						
						//insert purchase
						try{
							int cnt = 0;
							query = "select * from shipment where shipment_id = '" + ship_id + "'";
							ResultSet shipments = getData(slave, query);
							
							query = "insert into purchase(shipment_id, source_name, groupid, quantity, unit_cost, time_recvd) "
									+ "values('" + ship_id + "', '" + shipments.getString(2) + "', '" + shipments.getString(8)
									+ "', '" + shipments.getString(9) + "', '" + shipments.getString(10) + "', "
									+ "systimestamp)";
									
							Statement stmt = slave.createStatement();
							cnt = stmt.executeUpdate(query);
							System.out.println(cnt + " updates");
						}
						catch(Exception e){
							System.out.println("Error " + e.getMessage());
						}
					}
					
					opt = new String[3];
					opt[0] = "Y";
					opt[1] = "N";
					opt[2] = "Q";
					System.out.println("Will you continue with current management? Or, will you go psycho and kill me? (Y/N/Q) ");
					resp = prompt(opt, robogirl);
					
					if(resp == 1 || resp == 2){
						if(resp == 2){
							System.out.println("Yes... yes.... YES!!! The pain is great!! Do it more! My ciruits are frying to the pleasure!");
							go = false;
						}
						break;
					}
				}
			}
		}
		try{
			slave.close();
		}
		catch(Exception e){
			System.out.println("Commit or Connection close failed: " + e.getMessage());
		}
	}
}
