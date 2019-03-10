import javax.swing.*;

public class Node extends JFrame{
	private Node next = null;
	private int nodeNumber;
	private static int nodeCount=0;
	public JPanel panel = new JPanel();
	private JTextField nextNode = new JTextField(3);
	private JLabel nextLabel = new JLabel("Next:");
	
	public Node() {
		panel.add(nextLabel);
		panel.add(nextNode);
		add(panel);
		nodeCount++;
		nodeNumber = nodeCount;
		nextNode.setText(" ~");
		setSize(250,200);
		setTitle("Node "+nodeNumber);
		setVisible(true);
	}
	
	public void setNext(Node p) {
		next = p;
		if (next==null)
			nextNode.setText(" ~");
		else
			nextNode.setText(" "+next.nodeNumber);
	}
	
	public Node getNext() {
		return next;
	}
}
