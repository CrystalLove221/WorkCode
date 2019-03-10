/* Tyler Hogue (hoguet@etown.edu)
 * Dr. Leap CS122A
 * 
 * 4-4-2017
 * 
 * The only numbers that work are powers of two. I couldn't work why no others worked. :(
 */


/* Runtimes:
 * 1024 items: 0 ms
 * 8096		'		16 ms
 * 1024*128: 	78 ms
 * 1024*1024: 265 ms
 * 
 */
public class MergeSort {

	public static void main(String[] args) {

		long time = System.currentTimeMillis();
		
		//initialize full array
		int size = 300000000; // unfortunate limitation, isn't it, Leap? :'(
		int[] full = new int[size];

		int i;
		for(i = 0; i < full.length;i++)
			full[i] = (int) (Math.random()*100);
		
		merge(full, full.length);
		
		time = System.currentTimeMillis()-time;
		
		System.out.print("Runtime: "+time);
		
	}

	public static void merge(int[] full, int size)
	{
		int i,j,k;
		
		//create  two halves
		int[] list1 = new int[size/2];
		int[] list2 = new int[size-size/2];	

		//set 1st half equal to 1st half of full array and so on
		for(i = 0;i < list1.length;i++)
			list1[i] = full[i];
		for(j = 0;j < list2.length;j++)
			list2[j] = full[i++];

		size/=2; //for next recursive level

		//keep going if either list1 has > 1 elements or list2 has > 1
		if(list1.length > 1)
			merge(list1, list1.length);
		
		if(list2.length > 1)
			merge(list2, list2.length);

		//actual sorting
		
		i = j = k = 0;

		while(i < list1.length && j < list2.length)
		{
			if(list1[i] <= list2[j])
				full[k++] = list1[i++];

			else if(list2[j] <= list1[i])
				full[k++] = list2[j++];
		}
		
		
		//rebuild components
		while(k < full.length)
		{
			if(i < list1.length)
				full[k++] = list1[i++];
			
			else if(j < list2.length)
				full[k++] = list2[j++];
		}
	}
}