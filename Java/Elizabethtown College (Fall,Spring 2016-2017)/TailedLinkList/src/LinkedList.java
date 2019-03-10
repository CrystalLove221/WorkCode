
public class LinkedList {

	Node head = null;
	Node tail = null; //tells where back of list is 

	Node RemoveFront() // removes all but one reference to node that head references
	{
		Node t = head;
		
		//if head becomes null, still set tail to null
		if(head != null) //empty list special case
			head = head.next;
		if(head==null)
			tail = null;
		return t;
	}

	void insertFront(Node p)
	{
		p.next = head;
		if(head == null) //empty list
			tail = p;
		head = p;
	}

	void insertRear(Node p) //insert node at the rear of the chain
	{
		if(tail == null) // empty list
			head= p;
		//general case...
		else
			tail.next = p;
		tail = p;
		p.next = null;
	}

	public void insertInOrder(Node p)
	{
		Node t = head;
		Node prev = null;

		//search down list for node value > t.value to the end of the list
		while(t != null && p.value.compareTo(t.value) > 0) 
		{
			prev = t; //maintain reference to node that comes before t.
			t = t.next; // move reference of t down chain.
		}

		if(prev == null) //empty list
			head = p;
		else		// t is at the end and = null
			prev.next = p;

		//general case: insert in middle of the list
		p.next = t; 
		if(t == null) //reached end of chain
			tail = p;
	}

	void Append(LinkedList bin)
	{
		
		//reconnect bin's list to original list (attach master list to bin.head and bin.tail)
		if(bin.head == null) // empty bin
			return;
		if(head == null) // empty list
			head = bin.head; 
		else // list w/ nodes
			tail.next = bin.head;
		tail = bin.tail;
		bin.head = bin.tail = null;
	}
}