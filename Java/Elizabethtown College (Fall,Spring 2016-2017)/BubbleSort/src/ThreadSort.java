public class ThreadSort extends Thread {

	int[] arr;

	public ThreadSort(int[] arr)
	{
		this.arr = arr;
	}

	public void run()
	{
		boolean inorder = false; // whether or not list is in order
		int i,j,temp;

		// j is last element that can be swapped with for each passthrough.
		// j moves back one since the last number (the largest) will be in its correct place.
		// The smallest j is 2 because indices 0 and 1 are swapped and index 1 swaps with 2 (j) and finishes passthrough.

		while(!inorder)
		{
			inorder = true; // assume list is in order

			for(j = arr.length-1;j >= 2;j--) // shrink search space as large #s are pushed to end.
			{
				for(i = 0;i < j;i++) //keep searching through until the end is reached
				{
					if(arr[i] > arr[i+1])
					{
						inorder = false;

						temp = arr[i];
						arr[i] = arr[i+1];
						arr[i+1] = temp;
					}
				}
			}

			if(inorder) // if nothing was swapped, everything is in order. TERMINATE.
				return;
		}
	}
}