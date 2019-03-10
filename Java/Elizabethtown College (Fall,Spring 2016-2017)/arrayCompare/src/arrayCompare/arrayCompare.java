/* Tyler Hogue (hoguet@etown.edu)
 * Dr. Ting Gu (CSC 121B) (11-18-2016)
 * 
 * Description: This program will ask the user to input numbers for an array of five elements.
 * Each array will be sorted in ascending order and then the first (smallest) number will be 
 * compared with both arrays. If the first array's element is larger, it tells user that the first array
 * is bigger and so on. If the first element in both arrays is =, it will continue. If all numbers are =
 * it will tell user both arrays are the same.
 */

package arrayCompare;

import java.util.Scanner;

public class arrayCompare {
	static Scanner console = new Scanner(System.in);
	
	public static int compare(int[] list1, int[] list2)
	{ // Key- 0 = equal, 1 = array 1 is greater, 2 = array 2 is greater
		
		int compare = 0; // initialize to 0 in case the two numbers are the same for the entire loop.
		
		for(int x = 0;x < list1.length;x++)
		{
			if(list2[x] < list1[x])
				compare = 1;
			
			else if(list1[x] < list2[x])
				compare = 2;
		}
		return compare;
	}
	
	public static void main(String[] args)
	{
		int[] list1 = new int[5]; // initialize size of arrays
		int[] list2 = new int[5];
		
		System.out.print("Enter values for 1st array. \n"); //ask user for numbers of 1st array
		for(int x = 0;x < list1.length;x++)
		{
			int list1val = console.nextInt();
			list1[x] = list1val;
		}
		
		System.out.print("Enter values for 2nd array. \n"); //ask user for numbers of array 2
		for(int y = 0;y < list2.length;y++)
		{
			int list2val = console.nextInt();
			list2[y] = list2val;
		}
		
		int temp = 0;
		for(int a = 0;a < list1.length;a++) //sort array 1 in ascending order
		{
			for(int b = a+1;b < list1.length;b++)
			{
				if(list1[b] < list1[a]) //check if right element is < left-hand element (adjacent elements)
				{
					temp = list1[b]; // swap elements if 2nd is smaller than 1st.
					list1[b] = list1[a];
					list1[a] = temp;
				}
			}
		}
		
		for(int a = 0;a < list2.length;a++) //do the same sorting algorithm as for array 1.
		{
			for(int b = a+1;b < list2.length;b++)
			{
				if(list2[b] < list2[a])
				{
					temp = list2[b];
					list2[b] = list2[a];
					list2[a] = temp;
				}
			}
		}
		System.out.println();
		
		System.out.print("1st Array: ");
		for(int x = 0;x < list1.length;x++) //output array 1
			System.out.print(list1[x]+", ");
		
		System.out.println();
		
		System.out.print("2nd Array: ");
		for(int y = 0;y < list2.length;y++) //output array 2
			System.out.print(list2[y]+", ");

	System.out.println();
	
	int compare = compare(list1, list2);
	
	//Return to compare method and output user message according the comparison.
	
	if(compare == 1)
		System.out.print("The first array is larger!");
	
	else if(compare == 2)
		System.out.print("The second array is larger!");
	
	else
		System.out.print("Both arrays are the same.");
	}
}
