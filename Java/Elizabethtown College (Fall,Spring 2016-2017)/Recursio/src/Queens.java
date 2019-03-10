
public class Queens {

	static char[][] board = new char[8][8];
	static int solutions =   0;


	public static void placequeen(int row)
	{

		if(row == 8)
		{
			solutions++;
			Prnsolutions();
			return;
		}

		int col;

		for(col = 0; col < 8; col++)
		{

			if(notattacked(row, col))
			{
				board[row][col] = 'Q';

				placequeen(row+1);

				board[row][col] = ' '; //if an attack is possible, reset square to blank

			}
		}
	}
	
	public static void  print()
	{
		int r, c;
		
		for(r = 0; r < board.length;r++)
		{
			for(c= 0; c < board.length; c++)
			{
				System.out.print(" "+ board[r][c]);
				
			}
	}
		System.out.println();
	}


	public static boolean notattacked(int r, int c)
	{
		int i = 0;
		int j = 1;
		
		for (i = r-1; i >= 0; i--) //always looks up because squares are searched top_left to bottom-right
		{

			if(c-j >=0 && board[i][c-j] == 'Q')
			{
				return false;
			}

			if(c+j <= 7 && board[i][c+j] == 'Q')
			{
				return false;
			}

			if(board[i][c] == 'Q')
			{
				return false;
			}
			
			j++;
		}

		return true;
		
		
	/*	int i = 0;
		int j = 1;

		for (i = r-1; i >= 0; i--)
		{

			if(c-j >=0 && board[i][c-j] == 'Q')
			{
				return false;
			}

			if(c+j <= 7 && board[i][c+j] == 'Q')
			{
				return false;
			}

			if(board[i][c] == 'Q')
			{
				return false;
			}
			
			j++;
		}

		return true; */
	}
	
	public static void clear
	{
	int r, c;
		
		for(r = 0; r < board.length;r++)
		{
			for(c= 0; c < board.length; c++)
			{
				board[r][c] = '\0';
				
			}
	}
	}

	public static void main(String[] arg)
	{
		clearBoard
		placequeen(0);


		System.out.println("Total number of solutions="+solutions);

	}

}


//between rows: ---+---+---+---

//spaced between queens: | " " Q | " " | 

// coree arnswer: 92 solutions

//vertical bar to close line

//beginning of row: +---