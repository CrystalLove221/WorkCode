import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Test1 {

	
public static void main(String[] args) throws FileNotFoundException
{
	File file  = new File("c:\\Users\\hoguet7260\\Desktop\\test.txt");
	Scanner in = new Scanner(file);
	String line;
	
	while(in.hasNextLine())
	{
		line = in.nextLine();
		System.out.println(line);
	}
	in.close();
	
	}
	}