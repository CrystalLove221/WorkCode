
public class SinglyLinkedList {
	Node head = null;
	
	void insertFront(Node p) {
		p.setNext(head);
		head = p;
	}
	
	void insertRear(Node p) {
		Node t;
		t = head;
		while (t.getNext()!=null)
			t = t.getNext();
		p.setNext(t.getNext()); //last node points to null. So, the last acts as the REAR, or first node
		t.setNext(p); // 2nd node in line points to node 4 which points to null
	}

	public void printList() {
		
	}
}
