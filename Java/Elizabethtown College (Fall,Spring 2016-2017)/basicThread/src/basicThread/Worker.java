package basicThread;

public class Worker extends Thread {
	
	
	
	String name;
	
	public Worker (String name)
	{
		this.name = name;
	}
	
	public void run() //always void, never pass vars to it (pass through constructor
	{
		int c = 0;
		
		while(c < 10)
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			int n = 1;
			
			while(n != 0)
			{
				n++;
				
				
			}
			
				System.out.println(name+": "+c++);
			
			
		}
		
	}
}

//non deterministic: upredictable program