
public class FindPrime extends Thread {

	public static long[] bigprimes = new long[410000];
	int mycount = 0; //# of primes each thread finds


	static Object lock = new Object(); // lets only 1 thread access "next" at once
	long n; // local "next" that each thread indepently calculates

	public static int bigindex = 0;


	public FindPrime(long next)
	{
		this.n = ThreadPrimes.next+=2; // store next into n and increment 2 to next for next thread
	}

	public void run()
	{
		while(ThreadPrimes.next <= ThreadPrimes.end)
		{

			if(isPrime(n) == true)
			{
				mycount++;

				storePrime(n);
				synchronized(lock)
				{
					ThreadPrimes.next+=2;
					n = ThreadPrimes.next;
				}
			}
			else // if number is composite, access next and grab next number
			{
				synchronized(lock)
				{
					ThreadPrimes.next+=2;
					n = ThreadPrimes.next;
				}
			}
		}
	}

	public boolean isPrime(long n) // test each number against list of primes from 2 to 1 million
	{
		for(int i = 1; ThreadPrimes.smallprimes[i] <= Math.sqrt(n); i++)
		{
			if(n%ThreadPrimes.smallprimes[i] == 0)
				return false;
		}
		return true;
	}

	public static synchronized void storePrime(long n)
	{
		bigprimes[bigindex] = n;
		System.out.println("Index "+bigindex+": "+bigprimes[bigindex]);
		bigindex++;
		ThreadPrimes.count++;
	}
}  