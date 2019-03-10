
public class Clock
	{
		private int hrs;
		private int mins;
		private int secs;
		
		public Clock()
		{
			hrs = 0;
			mins = 0;
			secs = 0;
		}
		
		public Clock(int hours, int minutes, int seconds)
		{
			if(hrs >= 0 && hrs < 24)
			{
				hrs = hours;
			}
			if(mins >= 0 && mins < 60)
			{
				mins = minutes;
			}
			else
			{
				mins = 0;
			}
			
			if(secs >= 0 && secs < 60)
			{
				secs = seconds;
			}
			else
			{
				secs = 0;
			}	
		}
		
		
		
		public void setTime(int hours, int minutes, int seconds)
		{
			if(hrs >=0 && hrs < 24)
			{
				hrs = hours;
			}
			else
			{
				hrs = 0;
			}
			
			if(mins >= 0 && mins < 60)
			{
				mins = minutes;
			}
			
			else
			{
				mins = 0;
			}
			
			if(secs >= 0 && secs < 60)
			{
				secs = seconds;
			}
		else
		{
			secs = 0;
		}
	}
		
		
		public void inchours()
		{
			hrs++;
			
			if(hrs > 23)
			{
				hrs = 0;
			}
		}
		
		public void incminutes()
		{
			mins++;
			
			if(mins > 59)
			{
				mins = 0;
				hrs++;
				
				if(hrs > 23)
				{
					hrs = 0;
				}
			}
		}
		
		public void incSeconds()
		{
			secs++;
			
			if(secs > 59)
			{
				secs = 0;
				
				mins++;
				if(mins > 59)
				{
					mins = 0;
					
					hrs++;
					if(hrs > 23)
					{
						hrs = 0;
					}
				}
			}
		}
		public int getHours(int hrs)
		{
			return hrs;
		}
		
		public int getMinutes(int mins)
		{	
			return mins;
		}
		
		public int getSeconds(int secs)
		{
			return secs;
		}
	
	
	public void printTime()
	{
		System.out.print(hrs+":"+mins+":"+secs);
	}
	
	public static void Compare( Clock myclock, Clock yourclock)
	{
		if(myclock.hrs > yourclock.hrs || myclock.mins > yourclock.mins || myclock.secs > yourclock.secs)
			{
				System.out.print("My clock is ahead.");
			}
		else
			System.out.print("Your clock is ahead.");
	}

public static void main(String[] args)
{
	Clock myclock;
	Clock yourclock;
	
	myclock = new Clock();
	yourclock = new Clock(12,25,36);
	
	System.out.println();
	myclock.printTime();
	
	System.out.println();
	yourclock.printTime();
	
	myclock.setTime(12, 55, 45);
	
	System.out.println();
	myclock.printTime();
	
	for(int x = 1;x<=31;x++)
		yourclock.incminutes();
	
	for(int x = 1;x<=9;x++)
		yourclock.incSeconds();
	
	System.out.println();
	yourclock.printTime();
	
	Compare(myclock, yourclock);

}
	}