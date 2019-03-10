/* Tyler Hogue (hoguet@etown.edu)
 * Dr. Leap, CS122A
 * 
 * 1-25-2017
 */


public class Primes {
	
	static boolean[] primes = new boolean[1000]; //create primes array
	static int count;
	static DisplayWindow console = new DisplayWindow();
	
	public static void main (String[] args)
	{
		
		for(int i = 0; i< primes.length;i++)
			primes[i] = true;
		
		//manually set first two primes
		primes[0] = false;
		primes[1] = false;
		
		console.addInt(2);
		console.addInt(3);

		
		for(int n = 2;n < 1000;n++) //check # (up to desired #) for primality
		{
			if(primes[n] == true)
			{
				for(int i = n*n;i < primes.length;i+=n)
				{
					primes[i] = false;
				}
					
					count++;
	
			}
			
		}
	
	for(int i = 0; i < primes.length; i++)
	{
		if(primes[i] == true)
			console.addInt(i);
	}
	
	}
}