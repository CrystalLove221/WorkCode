/* Tyler Hogue (hoguet@etown.edu)
 * Dr. Leap (CS 122A)
 * 4-1-2017
 * 
 *  Description: This merge sort combination uses two threads in its first runthrough to merge sort a list of size "size".
 *  Then, it merge sorts with only one thread, and finally sorts with the basic bubble sort.
 */

/*
 * Multi-Threaded Bubble Runtime: 6178
 * One-Threaded Bubble Runtime: 10721
 * Basic Bubble Runtime: 20209
 */

public class BubbleSort {

	public static int size = 100000;

	public static int[] arr = new int[size]; // full list (used in unthreaded merge sort test)
	public static int[] list1 = new int[size/2]; // 1st half of full list
	public static int[] list2 = new int[size-size/2]; // 2nd half of full list
	public static int[] list3 = new int[size]; //copy of full list (basic bubble sort test)

	public static ThreadSort[] threadmatrix = new ThreadSort[4];

	public static void main(String[] args) throws InterruptedException {

		//create random list

		int i;
		for(i = 0;i < arr.length;i++)
			list3[i] = arr[i] = (int) (Math.random()*10000);

		division(list1, list2); // split full list arr into two lists

		threadmatrix[0] = new ThreadSort(list1); // used in multi- and single-threaded tests
		threadmatrix[1] = new ThreadSort(list2); // used in multi- and single-threaded tests
		threadmatrix[2] = new ThreadSort(arr); // used in single-threaded test
		threadmatrix[3] = new ThreadSort(list3); // used in basic test

		long runtime = System.currentTimeMillis();

		//multi-thread merge start
		threadmatrix[0].start();
		threadmatrix[1].start();
		threadmatrix[0].join();
		threadmatrix[1].join();

		mergesort(arr, list1, list2);

		runtime = System.currentTimeMillis()-runtime;
		//multi-thread stop

		System.out.println("Multi-Threaded Bubble Runtime: "+runtime+"\n");

		division(list1, list2);

		// single-threaded merge start
		runtime = System.currentTimeMillis();

		threadmatrix[0].run();
		threadmatrix[1].run();

		mergesort(arr, list1, list2);

		runtime = System.currentTimeMillis()-runtime;
		System.out.println("One-Threaded Bubble Runtime: "+runtime+"\n");
		// single-threaded stop

		//basic bubble start
		runtime = System.currentTimeMillis();

		threadmatrix[3].run();

		runtime = System.currentTimeMillis()-runtime;

		System.out.print("Basic Bubble Runtime: "+runtime);
		//basic bubble stop
	}

	public static void division(int[] list1, int[] list2)
	{
		int i,j;

		for(i = 0;i < arr.length;i++) // reassign original arr values
			arr[i] = list3[i];

		for(i = 0; i< list1.length;i++) // copy 1st half of arr[] to list1
			list1[i] = arr[i];

		for(j = 0;j < list2.length;j++) // copy 2nd half of arr to list2
			list2[j] = arr[i++];
	}

	public static void mergesort(int[] fullmerge, int[] merge1, int[] merge2)
	{
		int i,j,k;
		i = j = k = 0;

		while(i < merge1.length && j < merge2.length) // check 1st and 2nd half values until one half reaches end of its values
		{
			if(merge1[i] >= merge2[j])
				fullmerge[k++] = merge2[j++];

			else if(merge1[i] <= merge2[j])
				fullmerge[k++] = merge1[i++];
		}

		while(k < fullmerge.length) // copy remnants of other half of list that did not run through all its values
		{
			if(i < merge1.length)
				fullmerge[k++] = merge1[i++];

			else if(j < merge2.length)
				fullmerge[k++] = merge2[j++];
		}
	}
}