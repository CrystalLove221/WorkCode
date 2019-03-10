
public class LinkedList {

	public static void main(String[] args)
	{
		SinglyLinkedList list = new SinglyLinkedList();

		myNode temp = null;
		for(int i = 0; i < 4;i++)
		{
			temp = new myNode();
			temp.setV((int) (Math.random()*100));
			list.insertOrder(temp);
			
		}
		list.printList(temp); // print values in each node
	}
}