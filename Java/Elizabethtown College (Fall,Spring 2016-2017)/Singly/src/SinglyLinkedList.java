import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class SinglyLinkedList extends JFrame {

	JPanel panel = new JPanel();
	JTextField text = new JTextField(5);
	myNode head = null; // node in chain that points to null
	myNode current = null;


	JButton reorder = new JButton("Reorder");
	JButton hide = new JButton("Hide all");
	JButton show = new JButton("Show all");
	JButton removefront = new JButton("Remove front");
	JButton removerear = new JButton("Remove rear");
	JButton newnode = new JButton("new");
	JButton insertfront = new JButton("insert at front");
	JButton insertorder = new JButton("in order");

	public SinglyLinkedList()
	{
		setLocation(500, 0);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.add(new JLabel("Head: "));
		panel.add(text);
		panel.add(show);
		panel.add(hide);
		panel.add(newnode);
		panel.add(insertfront);
		panel.add(insertorder);
		panel.add(reorder);
		panel.add(removefront);
		panel.add(removerear);
		add(panel);
		setSize(400, 150);
		setTitle("Link List");
		newnode.addActionListener(new NewNode());
		insertorder.addActionListener(new Inorder());
		reorder.addActionListener(new Reorder());
		removefront.addActionListener(new RemoveFront());
		removerear.addActionListener(new RemoveRear());
		show.addActionListener(new Show());
		hide.addActionListener(new Hide());
		reorder.addActionListener(new Reorder());
		setVisible(true);
	}

	void insertOrder(myNode p)
	{	
		myNode t = head;
		myNode prev = null;

		while(t != null && p.value >= t.value)
		{
			prev = t;
			t = (myNode) t.nextNode();
		}

		if(prev == null)
			head = p;
		else
			prev.setnext(p);
		
		p.setnext(t);
	}

	void insertFront(myNode p)
	{
		p.setnext(head);
		head = p;
		text.setText(""+head.nodenum);
	}

	void insertRear(myNode p) // makes last node become the rear that points to null, as the head node originally did. Node that points to that head node now points to node p.
	{

		Node t = head; // make reference to "head"; node that references ndoe that points to null

		if(head == null) // do for empty list where head points to null
		{
			head = p;
			text.setText(""+head.nodenum);
		}

		else //do for list where head points to node 1
		{
			while(t.nextNode() != null) //go back to beginning of chain to the head node that points to null
				t = t.nextNode();

			p.setnext(t.nextNode()); // points node p
			t.setnext(p); // 2nd node in chain points to node that points to null.
		}
	}

	//abandon/remove references to first and last nodes

	Node Removefirst()
	{
		Node t = head;

		if(head == null)
			return t;

		if(t.nextNode() == null)
		{
			t = (myNode) t.nextNode();
			text.setText(""+t);
		}

		else
		{
			head = (myNode) (head.nextNode());
			t.setnext(null);
			text.setText(""+head.nodenum);
		}

		return t;
	}

	Node RemoveLast()
	{
		Node t = head; //reference Node to 1st node.
		Node prev = null;

		if(head == null)
			return t;

		if(head.nextNode() == null) 
		{
			t = t.nextNode(); //(glitched)if list is empty, make head point to null to detach from program
			text.setText(""+t);
		}

		else
		{
			while(t.nextNode() != null)
			{
				prev = t;
				t = t.nextNode();
			}

			prev.setnext(null);
		}

		return t;
	}

	private class NewNode implements ActionListener
	{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			current = new myNode();
			current.disp.setEnabled(true);

		}
	}

	private class Inorder implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(current == null) return;
			current.value = Integer.parseInt(current.disp.getText());
			insertOrder(current);


		}
	}

	private class InsertFront implements ActionListener
	{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(current == null) return;
			insertFront(current);
			current = null;
		}
	}

	private class RemoveFront implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			Node t = Removefirst();
		}
	}

	private class RemoveRear implements ActionListener
	{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Node t = RemoveLast();
		}
	}

	private class Reorder implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int x = 5; int y = 5;
			Node t = head;
			while(t != null)
			{
				t.setLocation(x,y);
				t.setVisible(true);
				t = t.nextNode();
				y+= 105;
				if(y > 800)
				{
					y = 5;
					x+=155;
				}
			}
		}
	}


	private class Hide implements ActionListener
	{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Node t = head;
			while(t != null)
			{
				t.setVisible(false);
				t = t.nextNode();
			}
		}
	}


	private class Show implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Node t = head;
			while(t != null)
			{
				t.setVisible(true);
				t = t.nextNode();
			}
		}

	}

	public void printList(myNode p)
	{
		myNode t = head;
		while(t != null)
		{
			System.out.println(t.value); //print value in each node
			t = (myNode) t.nextNode();
		}
	}
}