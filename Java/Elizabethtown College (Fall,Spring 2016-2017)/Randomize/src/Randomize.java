
public class Randomize {

	public static void main(String[] args)
	{
		int[] list = new int[10];
		int[] random = new int[10];
		int[] index = new int[10];
		
		int i, n; i = n = 0;
		
		for(i = 0;i < list.length;i++)
		{
		
			n = (int) (Math.random()*10);
			
			list[i] = i+1;
			index[i] = n;
		}
					
					
		
		
		for(i = 0;i < list.length;i++)
		{	
			if(random[i] == 0)
				random[i] = list[index[i]];
			
			else
				continue;
		}
	}
}
