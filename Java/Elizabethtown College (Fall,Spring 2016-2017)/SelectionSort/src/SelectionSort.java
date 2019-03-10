
public class SelectionSort {

	public static void main(String[] args) {

		int[] list = new int[20];
		
		int min,minindex,i,j;
		
		for(i = 0;i < list.length;i++)
			list[i] = (int) (Math.random()*100);
		
		for(i = 0;i < list.length-1;i++)
		{
			minindex = i;
			
			for(j = i+1;j < list.length;j++)
			{
				if(list[j] < list[minindex])
					minindex = j;
			}
			
			int t = list[i];
			list[i] = list[minindex];
			list[minindex] = t;
			
		}
	
		for(i = 0;i < list.length;i++)
			System.out.print(list[i]+" ");
		
	}
}