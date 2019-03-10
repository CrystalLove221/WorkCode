
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
public class IOWrite {

	public static void main(String[] args) throws IOException
	
	{
		File file = new File("c://users//hoguet7260//Desktop//info-lec.pdf");
		FileOutputStream output = new FileOutputStream(file);
		ObjectOutputStream out = new ObjectOutputStream(output);
		
		for(int i = 0;i < 100;i++)
		{
			out.writeInt(i);
		}
		
		out.close();
		output.close();
	}
}
