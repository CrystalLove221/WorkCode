import java.util.Scanner;



public static int seclargest(int[] array)
{
	int maxindex = array[0];
	int maxindex2 = array[0];
	
	for(int x = 1;x < array.length;x++)
	{
		if(array[x] > maxindex)
		{
			maxindex = array[x];
		}
		for(int z = 1; z < array.length;z++)	
		if(array[x] > maxindex2 && array[x] < maxindex)
		{
			maxindex2 = array[x];
		}
		}
	}
	
	
}
public class SecondLargest {
	public static Scanner console = new Scanner(System.in);	
	public static void main(String[] args)
	{
		int[] nums = new int[6];
		
		for(int y = 0;y < nums.length;y++)
		{
			int input = console.nextInt();
			nums[y] = input;
		}
	}

}
