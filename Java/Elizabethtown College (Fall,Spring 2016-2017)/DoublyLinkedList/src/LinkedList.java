
public class LinkedList {

	
	Node q = new Node(); //holds head and tail pointers
	
	public LinkedList() //empty list
	{
		q.next = q; //intiialize looped list
		q.prev = q;
	}
	
	public void insertFront(Node p)
	{
		p.prev = q;
		p.next = q.next;
		
		p.prev.next = p;
		p.next.prev = p;
		
		
	}
	
	public void insertRear(Node p)
	{
		p.prev = q.prev;
		p.next = q;
		p.prev.next = p;
		
		p.next.prev = p;
	}
	
	public Node RemoveFront(Node q)
	{
		Node temp = q.next;
		if(temp == q)
			return null;
		
		temp.next.prev = temp.prev;
		temp.prev.next = temp.next;
		
		temp.prev = temp.next = null;
		return temp;
	}
	
	public void insertOrder(Node p)
	{
		Node t = q.next;
		while(t != q &&p.title.compareTo(t.title) >= 0)
			t = t.next;
		
		insertBeforeT(t, p);		
	}
	
	public void insertBeforeT(Node t, Node p)
	{
		t.prev.next = p;
		t.prev = p.prev;
		p.next = t;
		t.prev = p;
	}
}