
public class LinkedList {

	public static void main(String[] args) {
		SinglyLinkedList list = new SinglyLinkedList();
		MyNode temp = new MyNode();
		temp.setValue((int) Math.random()*100);
		list.insertFront(temp);
		temp = new MyNode();
		temp.setValue(54);
		list.insertFront(temp);
		temp = new MyNode();
		list.insertFront(temp);
		temp = new MyNode();
		list.insertRear(temp);
		list.printList();
	}

}
