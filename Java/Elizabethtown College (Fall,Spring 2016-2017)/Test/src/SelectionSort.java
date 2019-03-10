
public class SelectionSort {

	
	public static void main(String[] args){
		
int[] array = {9,8,7,6,5,4,3,2,1};

for(int x = 0;x < array.length-1;x++)
{
	int minindex = x;
	for(int y = x+1;y < array.length;y++)
	{
		if(array[y] < array[x])
		{
			minindex = y;
		}
		
	}
	int temp = array[x];
	array[x] = array[minindex];
	array[minindex] = temp;
}
for(int z = 0;z < array.length;z++)
{
	System.out.print(array[z]+" ");
}
	}	
}	