import java.awt.Color;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class Grid extends JButton
{

	Grid[][] grid;
	boolean marked = false;
	boolean exposed;
	boolean mine;
	int row, col;
	int neighbor;
	int size; //pass for referencing size of grid

	int count;
	JTextField mines;

	//pass in order to update in NewGame()

	public Grid(boolean exposed, int row, int col, int size, Grid[][] grid, int count, JTextField mines)
	{
		this.exposed = exposed;
		this.row = row;
		this.col = col;
		this.size = size;
		this.grid = grid;

		this.count = count;
		this.mines = mines;

		addMouseListener(new MainListen());
	}

	public void exposemine()
	{

		for(int a = 1;a < size+1;a++)
		{
			for(int b = 1; b < size+1;b++)
			{
				if(grid[a][b].mine == true)
				{
					grid[a][b].setBackground(Color.RED);
					grid[a][b].setText("X");
					grid[a][b].exposed  = true;
				}
			}

		}

		JOptionPane.showMessageDialog(null, "Game Over!", "May Day! May Day!", 0);
		MineSweeper.NewGame(grid, count, mines);
	}

	public void markflag()
	{

		if(exposed == false)
		{
			marked = true;
			exposed = true;

			setText("|>");
			setBackground(Color.YELLOW);
		}

		else if(marked == true)
		{
			marked = false;
			exposed = false;

			setText("");
			setBackground(null);
		}

	}

	public void expose(int row, int col)
	{

		grid[row][col].neighbor = 0; // ensures that accidental clicks don't increase # in square

		if(grid[row][col].mine == true)
			exposemine();

		else
		{
			grid[row][col].setBackground(Color.WHITE);
			grid[row][col].exposed = true;

			//search around clicked square
			for(int r = -1; r <= 1; r++)
			{
				for(int c = -1;c <= 1;c++)
				{
					if(grid[row+r][col+c].mine == true)
						grid[row][col].neighbor++;

					if(grid[row+r][col+c].exposed == true)
						continue;		
				}
			}

			if(grid[row][col].neighbor == 0)
			{

				for(int r = -1;r <= 1; r++)
				{
					for(int c = -1; c <= 1; c++)
					{

						if(grid[row+r][col+c].exposed == true)
							continue;

						//each non-exposed adjacent square is exposed and then searched around for mines
						else
							expose(row+r, col+c);

					}
				}
			}

			//set text of squares with mines
			else
				grid[row][col].setText(""+grid[row][col].neighbor);
		}

		wincheck();
	}

	public void wincheck() //check, after each click, whether all non-mines are exposed
	{
		boolean win = true;

		for(int r = 1; r < size+1;r++)
		{
			for(int c = 1; c < size+1;c++)
			{
				if(grid[r][c].mine == true)
					continue;

				else if(grid[r][c].exposed == false)
				{
					win = false;
					break;
				}

				else
					continue;
			}

			if(win == false)
				break;

		}

		if(win == true)
		{
			JOptionPane.showMessageDialog(null, "Winner", "You win", 1);
			MineSweeper.NewGame(grid, count, mines);
		}
	}

	private class MainListen implements MouseListener
	{

		@Override
		public void mouseClicked(MouseEvent e) {

			if(e.getButton() == 3) //right click
				markflag();

			else if(marked == false)
				expose(row, col);

		}

		@Override
		public void mouseEntered(MouseEvent e) {

		}

		@Override
		public void mouseExited(MouseEvent e) {

		}

		@Override
		public void mousePressed(MouseEvent e) {

		}

		@Override
		public void mouseReleased(MouseEvent e) {

		}
	}
}