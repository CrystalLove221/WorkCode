
public class Insertion {

	public static void main(String[] args) {

		int[] list = new int[10];
		int[] sort = new int[list.length];
		
		int i, j, k, l;
		l = 0;
		
		for(i = 0;i < list.length;i++)
		{
			int n = (int) (Math.random()*100);
			list[i] = n;
			System.out.print(list[i]+ " ");
		}
		
		for(j = 0;j < list.length;j++)
		{
			for(i = 0;i < l;i++)
				if(sort[i] > list[j]) break;
			
			for(k = l; k > i;k--)
				sort[k] = sort[k-1];
			
			sort[i] = list[j];
			l++;
		}
	
	for(i = 0;i < list.length;i++)
		System.out.println(sort[i]);
	}
}
