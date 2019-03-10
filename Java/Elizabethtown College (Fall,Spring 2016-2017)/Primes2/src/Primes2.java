
public class Primes2 {


	static long start = 10_000_000_000_000_000L;
	static long end = 10_000_000_000_008_000L;
	
	public static void main(String[] args) throws InterruptedException
	{
		
		final int endthread = 8; // number of threads
		
		long range, s;
		
		range = end-start+1;
		
		
		range /= endthread;
		s = start;
		
		long starttime;
		long runtime;
		
		
		starttime = System.currentTimeMillis();
		
		PrimeWorker[] matrix = new PrimeWorker[endthread];
		
		int i;
		
		for(i=0; i < endthread;i++)
		{
			matrix[i] = new PrimeWorker(s, s+range, "thread "+i);
			s += range;
		}
		
		for(i = 0; i < endthread;i++)
			matrix[i].start();
		
		for(i = 0;i < endthread;i++)
			matrix[i].join();
		
		runtime = System.currentTimeMillis() - starttime;
		
		System.out.println(starttime+" "+runtime);
	}
	
}
