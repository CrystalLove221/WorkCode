import java.util.Scanner;

public class ArraySum {

	
	static Scanner console=new Scanner(System.in);

	public static void main(String[] args)
	{
		int[][] table = { {1,2,3}, {1,2,3} };
		
		int sum = 0;
		int row = 0;
		int col = 0;
		
		for(col=0;col < table[row].length; col++)
		{
			sum += table[row][col];
		}
		System.out.print(sum+"\n \n");
		
		sum = 0;
		row = 0;
		col = 0;
		
		for(row=0;row < table.length;row++)
		{
			sum += table[row][col];
		}
		
		row = 0;
		col = 0;
		int maxindex = 0;
		
		for(row = 0;row < table.length;row++)
		{
			maxindex = table[row][0];
			for(col = 1;col < table[row].length;col++)
			{
				if(maxindex < table[row][col])
				{
					maxindex = table[row][col];
				}
				
			}
		}
					
		System.out.println("biggest number: "+maxindex);
				
	}
}
