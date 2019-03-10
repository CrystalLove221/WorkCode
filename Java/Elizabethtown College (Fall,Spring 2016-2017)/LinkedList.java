
public class LinkedList {
	Node head=null;
	Node tail=null;

	void insertInOrder(Node p) {
		Node t=head,prev=null;

		while (t!=null && p.value.compareTo(t.value)>=0) {
			prev = t;
			t = t.next;
		}
		if (prev == null)
			head = p;
		else
			prev.next = p;
		p.next = t;
		if (t==null) tail = p;
	}

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
			tail.next = p;
		tail = p;
		p.next = null;
	}

	Node removeFront() {
		Node t = head;
		if (head!=null)
			head = head.next;
		if (head==null)
			tail = null;
		return t;
	}

	void append(LinkedList listb) {
		if (listb.head==null) return;
		if (head==null)
			head = listb.head;
		else
			tail.next = listb.head;
		tail = listb.tail;
		
		listb.head = null;
		listb.tail = null;
	}
}
