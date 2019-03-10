import java.util.Scanner;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class writing {


	public static void main(String[] args) throws FileNotFoundException
	{
		File file = new File("c:\\users\\hoguet7260\\Desktop\\test.txt");
		
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(new FileOutputStream(file));
		
				for(int x = 0;x < 10;x++)
				{
					out.println(in.nextInt());
				}
				
				in.close();
				out.close();
	}		
	}
