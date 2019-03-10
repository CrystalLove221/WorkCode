import java.awt.*;
import javax.swing.*;

public class MineSweeper extends JFrame {

	int count; // number of mines

	Grid[][] grid;

	JPanel stats = new JPanel();
	JPanel board = new JPanel();
	JLabel label = new JLabel("MInes: ");
	JTextField mines = new JTextField(4);

	public MineSweeper(int size)
	{

		grid = new Grid[size+2][size+2];
		setSize(size*50,size*50); // 50 px dimensions
		setTitle("Mine Death");

		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		board.setLayout(new GridLayout(size, size));

		stats.add(label);
		stats.add(mines);

		add(stats, BorderLayout.NORTH);

		for(int r = 0;r < size+2;r++)
			for(int c = 0;c < size+2;c++)
				grid[r][c] = new Grid(true, r, c, size, grid, count, mines); // pass parameters to run click and update operations

		for(int r = 1;r < size+1;r++)
		{
			for(int c = 1;c < size+1;c++)
			{

				grid[r][c].exposed = false;

				double n = Math.random();

				if(n < 0.2)
				{
					grid[r][c].mine = true;
					count++;
				}

				board.add(grid[r][c]);

			}
		}

		add(board, BorderLayout.CENTER);
		mines.setText(""+count);

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setVisible(true);

	}

	public static void NewGame(Grid[][] grid, int count, JTextField mines) // pass parameters to be reset
	{

		count = 0;

		for(int r = 1;r < grid.length-1;r++)
		{
			for(int c = 1;c < grid[r].length-1;c++)
			{

				grid[r][c].marked = false;
				grid[r][c].mine = false;
				grid[r][c].setText("");
				grid[r][c].setBackground(null);
				grid[r][c].neighbor = 0;
				grid[r][c].exposed = false;

				//below is redo of initialization of mines

				double n = Math.random();

				if(n < 0.2)
				{
					grid[r][c].mine = true;
					count++;
				}
			}
		}

		mines.setText(""+count);

	}

	public static void main(String[] args)
	{
		int size = Integer.parseInt(JOptionPane.showInputDialog("How big is the board?"));

		if(size < 5)
			size = 5;

		if(size > 50)
			size = 50;

		new MineSweeper(size);

	}
}