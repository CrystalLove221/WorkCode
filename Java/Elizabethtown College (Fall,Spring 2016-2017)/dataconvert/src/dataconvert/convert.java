package dataconvert;

public class convert {

	public static void main(String[] args)
	
	{
		String bin = "";
		int dec = 201;
		
		while(dec > 1)
		{
			int rem = dec%2;
			int quo = dec/2;
			
			dec /= 2;
			
			if(rem == 1)
			{
				bin+= "1";
			}
			else
			{
				bin += "0";
			}
		}
		
		int a = 0;
		String bin2=bin;
		
		bin = "";
		for(a=0;a < bin.length();a++)
		{
			bin += bin2.charAt(a);
		}
	}
}
