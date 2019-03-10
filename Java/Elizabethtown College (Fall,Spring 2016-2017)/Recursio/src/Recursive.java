
public class Recursive {

	
	public static int factorial(int x)
	{
		
		int y = 1;
		
		System.out.println("In: x = "+x);
		
		if(x != 0)
		{
			y = x * factorial(x-1);
			System.out.println("x="+x+" x! = "+y);
		}
		
			return y;
	}
	
	public static void main(String[] arg)
	{
		
		
	
	int n = 5;
	
	int f = factorial(n);
	}
}
