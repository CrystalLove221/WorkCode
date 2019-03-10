/* Tyler Hogue (hoguet@etown.edu)
 * Dr. Leap (CSC 122B)
 * 3-18-2017, 6:37 p.m.
 * 
 * This program calculates primes from 40 billion to 40 billion 10 million by building a list of primes
 * from 2 to 1 million to divide each large number by. Each large prime is stored in array and
 * wrriten to file.
 */

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class ThreadPrimes {


	public static final int nthread = 4; // # of "workers"
	public static int count = 0; //# of primes from 40 billion to 40 billion 10 million
	public static FindPrime[] matrix = new FindPrime[nthread];
	public static long[] smallprimes = new long[78498]; // primes from 1 to 1,000,000

	public static long end = 40_010_000_000L;

	public static long next = 40_000_000_001L; // static number every thread will grab.

	long starttime = System.currentTimeMillis();

	public boolean isPrime(long n) // check each small number for smallprimes[]
	{

		for(long i = 3;i <= Math.sqrt(n);i+=2)
		{
			if(n%i == 0)
			{
				return false;
			}
		}

		return true;
	}


	public ThreadPrimes() throws InterruptedException
	{

		int index = 1;

		smallprimes[0] = 2;

		for(long n = 3; n <= 1_000_000;n+=2) // test each number from 3 to 1 million
		{
			if(isPrime(n) == true)
			{
				smallprimes[index] = n;
				index++;
			}
		}

		for(int i = 0;i < nthread;i++)
			matrix[i] = new FindPrime(next); //pass next number to every thread used

		for(int i = 0;i < nthread;i++)
			matrix[i].start();

		for(int i = 0;i < nthread; i++)
			matrix[i].join();

		System.out.println();

		for(int i = 0;i < nthread;i++)
		{
			System.out.println("Thread "+i+": "+matrix[i].mycount); //print out # of primes each thread found
		}

		System.out.println("----------------------------\nTotal number of primes: "+count); // total #

		//output(); // write bigprimes[] list to text file (may need to change file location)

		long endtime = System.currentTimeMillis();
		long elapsed = endtime-starttime;

		System.out.println("Time: "+elapsed+" ms.");
	}

	public void output() {
		PrintWriter primes=null;

		try 
		{
			primes = new PrintWriter("c:\\eclipse\\primes.txt");
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
			System.exit(1);
		}

		for (int i=0; i < count; i++)
			primes.println(FindPrime.bigprimes[i]); // write each prime in bigprimes[] to file

		primes.close();
	}


	public static void main(String[] arg) throws InterruptedException
	{
		new ThreadPrimes();		
	}
}