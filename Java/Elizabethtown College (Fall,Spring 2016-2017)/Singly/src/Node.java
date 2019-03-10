import javax.swing.*;


public class Node extends JFrame {

	private Node next = null; //points to previous node
	static int nodecount = 0;
	protected int nodenum; // protected (b/t public and private -- can't change value of var)

	public JPanel panel = new JPanel();
	private JTextField nodenext = new JTextField(3);
	private JLabel nextLabel = new JLabel("Next");

	public Node()
	{
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		nodenum = ++nodecount;
		setSize(300,300);
		nodenext.setText("null");
		setTitle("Node "+nodenum);

		panel.add(nextLabel);
		panel.add(nodenext);
		add(panel);

		setVisible(true);
	}

	public void setnext(Node node) //points each new node to the previous
	{
		next = node;
		if(next == null)
			nodenext.setText(" null ");
		else
			nodenext.setText(""+next.nodenum);
	}

	public Node nextNode()
	{
		return next; //returns node pointed at
	}
}