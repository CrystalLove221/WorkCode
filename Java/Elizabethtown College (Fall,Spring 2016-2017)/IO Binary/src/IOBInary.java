import java.io.FileInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class IOBInary {

	public static void main(String[] args) throws IOException

	{
		FileInputStream in = new FileInputStream(new File("c://users/hoguet7260//Desktop//info-lec.pdf"));
		
		int count = 0;
		while(in.read() != -1)
		{
			count++;
		}
		System.out.print("File size: "+count+" bytes");
	}
}
