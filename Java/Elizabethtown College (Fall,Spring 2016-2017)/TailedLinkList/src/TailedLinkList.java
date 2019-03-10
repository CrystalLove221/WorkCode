import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class TailedLinkList {

	public static void main(String[] args) throws FileNotFoundException {

		LinkedList list = new LinkedList();

		Scanner bookfile = null;
		String book;

		//handle errors where the file is not found
		
		try
		{
			File books = new File("c://users//hoguet7260//desktop//book.txt");
			bookfile  = new Scanner(books);
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File not Found");
			e.printStackTrace();
			System.exit(1);
		}

		//set book titles for each new node
		
		Node p = null;
		while(bookfile.hasNext()) //run through code while there is a nother line that can be processed
		{
			book = bookfile.nextLine(); //read a line of the file
			p = new Node();
			p.value = book.substring(45).toLowerCase(); //starting at index 45 (46th character); read to the end of the line.
			list.insertRear(p); // put node that p references into the list at the rear
		}
		bookfile.close();

		radixsort(list);
		
		//print out the sorted list
		
		p = list.RemoveFront();
		while(p != null)
		{
			System.out.println(p.value);
			p = list.RemoveFront();
		}
	}

	public static void radixsort(LinkedList list)
	{
		int i, n;
		Node p;

		//create bins to hold different chars
		LinkedList[] bin = new LinkedList[128];
		for(i = 0;i < 128;i++)
			bin[i] = new LinkedList();
		
		//sort each char (char 9 to char 0), appending the bins onto the original list
		for(n = 9;n >=0;n--)
		{
			 p = list.RemoveFront();
			while(p!=null)
			{
				int binNumber;
				binNumber = p.value.charAt(n)%128;
				bin[binNumber].insertRear(p);
				p = list.RemoveFront();
			}
			for(i = 0; i < 128; i++)
				list.Append(bin[i]);
		}
	}
}