
public class BubbleSort {

	
	public static void main(String[] arfgs)
	{
		int[] array = {9,8,7,6,5,4,3,2,1};
		
		int temp = 0;
		
		for(int x = 0;x < array.length;x++)
		{
			for(int y = 0;y < array.length-1;y++)
			{
				if(array[y+1] < array[y])
				{
					temp = array[y+1];
					array[y+1] = array[y];
					array[y] = temp;
					
				}
			}
		}
	for(int z = 0;z < array.length; z++)
	{
		System.out.print(array[z]);
	}
	}
}
