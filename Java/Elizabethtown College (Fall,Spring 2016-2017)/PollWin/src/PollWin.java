import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class PollWin extends JFrame {

	
	JPanel panel = new JPanel();
	
	JLabel question = new JLabel("SHould Congess raise tax rate?");
	
	
	JButton yes= new JButton("Yes");
	JButton no = new JButton("No");
	
	
	JTextField msg = new JTextField(30);
	
	public PollWin()
	{
		setTitle("Poll");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(300, 500);
		
		setLocationRelativeTo(null);
		
		yes.addActionListener(new yesListen());
		no.addActionListener(new noListen());
		
		
		panel.add(question);
		panel.add(yes);
		panel.add(no);
		panel.add(msg);
		
		add(panel);
		
		setVisible(true);
	}
	
	private class yesListen implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
		
			
			msg.setText("You must be a socialist!");
		}
		
	}
	
	private class noListen implements ActionListener
	{

		
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
		
			no.setEnabled(false);
			
			msg.setText("You will bankrupt country.");
		}
		
	}
	
	public static void main(String[] arg)
	{
		new PollWin();
	}
}
