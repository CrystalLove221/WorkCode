import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Tictactoe extends JFrame {
	
	JButton[][] board1 = new JButton[3][3]; // reference array of buttons
	JButton newGame = new JButton("New Game"); // reference new game button
	
	JTextField wins = new JTextField(4); //reference win count
	JTextField status = new JTextField(10); // reference another status field
	
	JPanel controlpanel = new JPanel(); // create new control window in memory
	JPanel board = new JPanel(); //create new board JPAnel to customize board inside controlpanel
	
	char player = 'X';
	String name = "";


	public Tictactoe()
	{ 
		board.setLayout(new GridLayout(3,3));
		
		setTitle("Tic Tac Toe");
		setSize(400, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		for(int r = 0;r < 3;r++)
		{
			for(int c = 0; c < 3;c++)
			{
				board1[r][c] = new JButton(name);
				board1[r][c].addActionListener(new SquareListener(r,c));
				board.add(board1[r][c]);
			}
		}
		

		
		newGame.addActionListener(new Listener());
		
		add(board, BorderLayout.CENTER);
		add(controlpanel, BorderLayout.SOUTH);

		controlpanel.add(newGame);
		controlpanel.add(wins);
		controlpanel.add(status);
		
		setVisible(true);
	}
	
	private class SquareListener implements ActionListener{
		
		boolean taken = false;
		int row, col;
		
		public SquareListener(int r, int c)
		{
			row = r;
			col = c;
		}	
			
	
		public void actionPerformed(ActionEvent e)
		{	
			if(taken == false)
			{
				taken = true;
				
				if(player == 'O')
				{
					board1[row][col].setText("O");
					System.out.println(player+" gets this square.");
					
					player = 'X';
				}
				
				else
				{
					board1[row][col].setText("X");
					System.out.println(player+" gets this square.");
					
					player = 'O';
				}
			}
		}
	}
			
	
	public static void main(String[] args)
	{
		new Tictactoe();
		
	}
	}