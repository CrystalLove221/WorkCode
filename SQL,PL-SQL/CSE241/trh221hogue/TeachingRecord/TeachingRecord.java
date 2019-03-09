
import java.io.*;
import java.sql.*;
import java.util.Scanner;
import java.util.InputMismatchException;

public class TeachingRecord{

	static ResultSet getResults(String query, Connection conn, String input, String msg)
	{
		ResultSet result = null;
		
		try
		{
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, input);
		
			try
			{
				result = stmt.executeQuery();
			}
			catch(SQLException sql)
			{
				System.out.println("Bad query. " + sql.getMessage());
				stmt.close();
				return null;
			}
			
			if(!result.next())
			{
				System.out.println(msg);
				return null;
			}
		}
		catch(Exception e)
		{
			System.out.println("An error occurred. " + e.getMessage());
			return null;
		}
		
		return result;
	}
	
	public static void main(String[] args){
		
		System.out.println("This is what you asked for, right Korth?");
		Scanner robogirl = new Scanner(System.in);
		
		//connect to database
		Connection slave = null;
		while(true)
		{
			System.out.print("Enter the name given to you at birth: ");
			String user = robogirl.nextLine();
			System.out.print("Now, tell me something personal, like your password: ");
			String pass = robogirl.nextLine();
			
			try
			{
				slave = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241",user,pass);
				System.out.println("Login success!");
				break;
			}
			
			catch(SQLException sql)
			{
				System.out.println("Bzzzzzt! Login failed. Try again! " + sql.getMessage());
			}
		}
			
		ResultSet result = null;
		String msg;
		String query;
		
		try
		{
			//look for instructors matching substring
			do
			{
				System.out.print("What shall the instructor name substring be, master? ");
				String substr = "%" + robogirl.nextLine() + "%";
				
				query = "select to_char(id, '00000') id, name from instructor where name like ?";
				msg = "No results for instructor matching substring " + substr;
				
				result = getResults(query, slave, substr, msg);
				
				if(result!=null)
				{
					System.out.println("Instructors matching substring: ");
					do
					{
						System.out.println(" " + result.getString("id") + " " + result.getString("name"));
					}
					while(result.next());
				}
			}
			while(result==null);
			
			//find instr with id
			int id = 0;
			while(result!=null)
			{
				System.out.print("Enter ID of instructor (between 1 and 99999 inclusive) ");
				
				try
				{
					id = Integer.parseInt(robogirl.nextLine());
				}
				catch(NumberFormatException e)
				{
					System.out.println("ID must be an integer between 1 and 99999! What did you think an ID was? A cow? 2.4? No!");
					continue;
				}
			
				if(id > 99999 || id <= 0)
				{
					System.out.println("ID must be between 1 and 99999.");
				}
				else
				{
					query = "select id from instructor where id = ?";
					msg = "No instructor found. Get a life and meet new people!";
					result = getResults(query, slave, Integer.toString(id), msg);
					
					//print teaching records
					if(result!=null)
					{
						query = "select dept_name Department, course_id CNO, title, sec_id sec, semester sem, year, count(*) Enrollment " + 
								"from teaches join takes using (course_id, sec_id, semester, year) natural join course " + 
								"where teaches.id = ?" +
								"group by dept_name, course_id, title, sec_id, semester, year " + 
								"order by dept_name, course_id, year, semester desc";
						msg = "Instructor never taught a course. Yay!";
						
						ResultSet teachResult = getResults(query, slave, Integer.toString(id), msg);
						if(teachResult!=null)
						{
							ResultSetMetaData meta = teachResult.getMetaData();
							System.out.printf("%-15s %-6s %-25s %-5s %-6s %-5s %-3s\n", 
												meta.getColumnName(1), meta.getColumnName(2), meta.getColumnName(3), 
												meta.getColumnName(4), meta.getColumnName(5), meta.getColumnName(6),
												meta.getColumnName(7));
							
							do
							{
								System.out.printf("%-15s %-6s %-25s %-5s %-6s %-5s %-3s\n", 
													teachResult.getString("department"), teachResult.getString("cno"),
													teachResult.getString("title"), teachResult.getString("sec"), 
													teachResult.getString("sem"), teachResult.getString("year"),
													teachResult.getString("enrollment"));
							}
							while(teachResult.next());
						}
					}
				}
			}
			System.out.println("Closed connection. Have fun binge-watching anime!");
			slave.close();
		}
		catch(Exception e)
		{
			System.out.println("An error occurred. " + e.getMessage());
		}
	}
}