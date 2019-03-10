/* 
 * This program will randomly place two checker pieces onto a "board".
 * 
 * K = king, r = rook, Q = queen, k = knight, p = pawn, b = bishop 
 * 
 * It will be a grid of 64 "squares [array of 8 by 8]
 * Kings can move in any direction one space
 * 
 * This program will determine if the random spots leave either one of the pieces to be eliminated.
 */

import java.util.Scanner;
public class Chess {
	
	public static void main(String[] args){
		
	
	char[][] grid = new char[8][8];
	
	int ctr = 0;
	for(int x=0;x < grid.length;x++)
	{
		for(int y = 0;y < grid[x].length;y++)
		{
			grid[x][y] = '+';
			ctr++;
		}
	}
	
	char[] pieces = {'K', 'k', 'p', 'Q', 'r', 'b'};
	
	// King = K, Queen = Q, knight = k, pawn = p. rook = r, bishoop = b
	
	int RowYou = (int) (Math.random()*8);
	int RowEne = (int) (Math.random()*8);
	int ColYou = (int) (Math.random()*8);
	int ColEne = (int) (Math.random()*8);
	
	int EnePieceIndex = (int) (Math.random()*pieces.length);
	int YouPieceIndex = (int) (Math.random()*pieces.length);
	
	char pieceYou = pieces[YouPieceIndex];
	char pieceEne = pieces[EnePieceIndex];
	
	grid[RowYou][ColYou] = pieceYou;
	grid[RowEne][ColEne] = pieceEne;
	
	int col = 0;
	for(int x = 0;x < grid.length; x++)
	{
		for(int y = 0;y < grid[col].length; y++)
		{
			System.out.print(grid[x][y]);
		}
		System.out.println();
	}
	}	
}	