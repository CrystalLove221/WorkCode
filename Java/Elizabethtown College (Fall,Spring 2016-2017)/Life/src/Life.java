import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class Life extends JFrame {

	square[][] grid;
	int n = 100;

	JPanel board = new JPanel();
	JPanel controls = new JPanel();
	JButton move = new JButton("Move");
	JButton clear = new JButton("Clear");
	JButton run = new JButton("Run");

	Timer timer = new Timer(500, new moveListen());
	
	public boolean running = false;
	
	public Life()
	{
		grid = new square[n+2][n+2];
		setLayout(new BorderLayout());
		setTitle("Game of Life");
		setSize(n,n); //5 px by 5 px
		setLocationRelativeTo(null);

		board.setLayout(new GridLayout(n,n));

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		for(int i=0;i<grid.length;i++)
		{

			for(int c=0;c<grid[i].length;c++)
			{
				grid[i][c] = new square();
				grid[i][c].life();
			}
		}
		
		for(int i=1;i<grid.length-1;i++)
		{

			for(int c=1;c<grid[i].length-1;c++)
			{
				grid[i][c] = new square();
				board.add(grid[i][c]);
				grid[i][c].life();
			}
		}

		controls.add(move);
		move.addActionListener(new moveListen());

		controls.add(clear);
		clear.addActionListener(new clearListen());

		controls.add(run);
		run.addActionListener(new runListen());

		add(board, BorderLayout.CENTER);
		add(controls, BorderLayout.SOUTH);

		setVisible(true);
		
		
	}

	private class moveListen implements ActionListener
	{

		public void actionPerformed(ActionEvent arg0)
		{
			int neighbor;

			for(int r = 1;r < grid.length-1;r++)
			{
				for(int c = 1;c < grid[r].length-1;c++)
				{
					neighbor = 0;

					for(int i = -1;i <= 1; i++)
					{
						for(int j = -1; j <= 1;j++)
						{
							
							if(grid[r+i][c+j].alive == true)
								neighbor++;

/*							try
							{	
								if(grid[r+i][c+j].alive == true)
									neighbor++;
							}

							catch(ArrayIndexOutOfBoundsException x){}
*/
						}
					}

					if(grid[r][c].alive == true)
						neighbor--;

					if(neighbor >= 4 || neighbor < 2)
						grid[r][c].death = true;

					if(neighbor == 3 && grid[r][c].alive == false)
						grid[r][c].birth = true;
				}
			}

			for(int r = 1;r < grid.length-1;r++)
			{
				for(int c = 1;c < grid[r].length-1;c++)
				{
					if(grid[r][c].birth == true)
					{
						grid[r][c].alive = true;
						grid[r][c].birth = false;
						grid[r][c].setBackground(Color.RED);
					}

					if(grid[r][c].death == true)
					{
						grid[r][c].alive = false;
						grid[r][c].death = false;
						grid[r][c].setBackground(null);
					}
				}
			}
		}
	}

	private class clearListen implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {

			for(int i = 0;i < grid.length;i++)
			{
				for(int c = 0;c < grid[i].length;c++)
				{
					grid[i][c].alive = false;
					grid[i][c].birth = false;
					grid[i][c].death = false;

					grid[i][c].setBackground(null);
				}
			}
		}
	}

	private class runListen implements ActionListener
	{

		public void actionPerformed(ActionEvent e) {
			
			if(running == false)
			{
				running = true;
				run.setText("Stop");
				
				timer.start();
			}

			else
			{
				running = false;
				run.setText("Run");
				
				timer.stop();
			}
		}
	}

	public static void main(String[] args)
	{
		new Life();
	}
}