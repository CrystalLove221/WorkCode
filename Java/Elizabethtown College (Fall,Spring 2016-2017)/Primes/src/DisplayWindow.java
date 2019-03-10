import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class DisplayWindow extends JFrame {
	
	
	static JTextArea display = new JTextArea();
	JScrollPane pane = new JScrollPane(display);
	JButton clear = new JButton("Clear");
	
	public DisplayWindow() // "this" represents whatever object currently being worked on.
	{
		this.setTitle("Primes");
		this.setSize(200, 600);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.add(pane);
		
		this.add(clear, BorderLayout.SOUTH);
		clear.addActionListener(new clearListen());
		
		this.setVisible(true);
	}
	
	public void addInt(int n)
	{
		String text = display.getText();
		text = text+"\n"+n;
		display.setText(text);
	}
	
	public void addMsg(int n)
	{
		String text = display.getText();
		text += n+"\n";
		display.setText(text);
	}
	
	
	private class clearListen implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
			display.setText("");
		}
	}
	
	public static void main(String[] args)
	{
		
		new DisplayWindow();
		
		
	}
	
}
