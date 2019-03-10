
public class LinkedList {
	Node head=null;
	Node tail=null;
	
	void insertFront(Node p) {
		p.next = head;
		if (head==null)
			tail = p;
		head = p;
	}
	
	void insertRear(Node p) {
		if (head==null)
			head = p;
		else
			`tail.next = p;
		tail = p;
		p.next = null;
	}
}
