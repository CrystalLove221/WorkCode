import java.util.Scanner;

public class Factorial {

	static int product = 1;
	
	public static int fact(int nums)
	{
		
		if(nums > 1)
		{
			product  *= nums;
			fact(nums-1);
		}
		
		return product;
	}
	static Scanner console = new Scanner(System.in);
	
	public static void main(String[] args)
	{
		int nums = console.nextInt();
		System.out.print(fact(nums));
	}
}
