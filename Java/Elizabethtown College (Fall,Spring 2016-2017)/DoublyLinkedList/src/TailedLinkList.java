import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class TailedLinkList {

	public static void main(String[] args) throws FileNotFoundException {

		LinkedList list = new LinkedList();

		Scanner bookfile = null;
		String book;
		
		try
		{
			File books = new File("c://users//hoguet7260//desktop//book.txt");
			bookfile  = new Scanner(books);
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File not Found");
			System.out.println(e);
			System.exit(1);
		}

		Node p;
		while(bookfile.hasNext()) //run through code while there is a nother line that can be processed
		{
			book = b ookfile.nextLine(); //set string to reference value at next line in file.
			p = new Node(); //make a new node in memory and make p reference it (values of node copy to p)
			p.value = book.substring(45); //starting at index 45 (46th character), read to the end of the line.
			list.insertFront(p); // put node that p references into the list from the front (copying the memory into references head and head.next.
		}

		bookfile.close();
		
		p = list.RemoveFront(); //remove all but one reference to the 1st node in the chain that head references

		while(p != null)
		{
			System.out.println(list.head.value);
			p = list.RemoveFront();
		}
	}
}