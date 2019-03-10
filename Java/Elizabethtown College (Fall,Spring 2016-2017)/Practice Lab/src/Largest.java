import java.util.Scanner;

public class Largest {

	static  Scanner console = new Scanner(System.in);

public static void main(String[] args)
{
	int[] list = new int[6];
	
	for(int a = 0;a < list.length;a++)
	{
		list[a] = console.nextInt();
	}
	
	for(int x = 0; x < list.length;x++)
	{
		for(int y = x+1;y < list.length;y++)
		{
			if(list[x] < list[y])
			{
				int temp = list[x];
				list[x] = list[y];
				list[y]= temp;
			}
		}
	}
	
	for(int z = 0;z < list.length;z++)
		{
			System.out.print(list[z]);
		}
	System.out.print(" is the largest number using the string of integers.");
}
}