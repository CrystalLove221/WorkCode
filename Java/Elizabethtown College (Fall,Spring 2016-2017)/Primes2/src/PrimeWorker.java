
public class PrimeWorker extends Thread {

	private long start;
	private long end;
	
	String name;

	public PrimeWorker(long s, long e, String name)
	{
		
		this.start = s;
		this.end = e;
		this.name = name;
		
	}
	
	public void run()
	{

		if(start%2 == 0)
			start++;
		
		for(long i = start; i <= end;i+=2)
		{
			if(prime(i) == true)
				System.out.println(name+" "+i);
		}

	}

	public boolean prime(long n)
	{
		long i;

		for(i = 3; i <= Math.sqrt(n); i+=2)
		{
			if(n%i == 0)
				return true;
		}

		return false;
	}
}