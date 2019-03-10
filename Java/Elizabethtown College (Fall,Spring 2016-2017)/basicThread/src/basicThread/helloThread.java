package basicThread;

public class helloThread {
	
	public static void main(String[] arg) throws InterruptedException
	{
		Worker andy = new Worker("Andy");
		Worker donald = new Worker("Donald");
		
		andy.start();
		donald.start();
		
		System.out.println("Main is waiting...");
		
		andy.join();
		
		System.out.println("Thread exited.");
		
		donald.join();
		
		System.out.println("All done.");
		
	}

}
