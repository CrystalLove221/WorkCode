import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LinkedListwithTail {

	public static void main(String[] args) {
		int i;
		Node p;
		String book;
		LinkedList list = new LinkedList();
		File books = new File("book.txt");
		Scanner bookfile = null;
	
		try {
			bookfile = new Scanner(books);
		} catch (FileNotFoundException e) {
			System.out.println("Could not find the file "+"books.txt");
			e.printStackTrace();
			System.exit(1);
		}
		
		while (bookfile.hasNext())
		 {
			book = bookfile.nextLine();
			p = new Node();
			p.value = book.substring(45);
			list.insertRear(p);
		}
		bookfile.close();
		
		radixSort(list);
		
		p = list.removeFront();
		while (p!=null ) {
			System.out.println(p.value);
			p = list.removeFront();
		}
	}

	public static void radixSort(LinkedList list) {
		int i,n;
		Node p;
		LinkedList[] bins = new LinkedList[128];
		for (i=0; i<128; i++)
			bins[i] = new LinkedList();

		for (n=9; n>=0; n--) {
			while ((p = list.removeFront())!=null) {
				int binNumber;
				binNumber = p.value.charAt(n)%128;
				bins[binNumber].insertRear(p);
			}
			for (i=0; i<128; i++)
				list.append(bins[i]);
		}
	}
	
}
