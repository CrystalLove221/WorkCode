import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class PrimeWin extends JFrame {
	JTextArea display = new JTextArea();
	JScrollPane pane = new JScrollPane(display);
	
	public void updateText()
	{
		String text = display.getText();
		text == primes[i]+"\n";
		display.setText(text);
	}
	
	public PrimeWin(String name) // "this" represents whatever object currently being worked on.
	{
		this.setTitle(name);
		this.setSize(200, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.add(pane);
		
		this.setVisible(true);
	}
	
	public static void main(String[] args)
	{
		PrimeWin win = new PrimeWin("Prime WIndows");
		
		win.updateText();
		
	}
	//creates 1 instances of primes array
}
