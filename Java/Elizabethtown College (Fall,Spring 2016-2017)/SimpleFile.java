import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class SimpleFile {

	public static void main(String[] args) {
		long n;
		PrintWriter myfile=null;
		try {
			myfile = new PrintWriter("MyNumbers.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}

		for (n=40000000000L; n<40000002000L; n++)
			myfile.println(n);
		
		myfile.close();
	}

}
