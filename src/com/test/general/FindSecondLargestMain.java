package com.test.general;

public class FindSecondLargestMain {
	public static void main(String args[])
	{
		int[] arr1={7,5,6,1,4,2};
		int secondHighest=findSecondLargestNumberInTheArray(arr1);
		System.out.println("Second largest element in the array : "+ secondHighest);
	}

	public static int findSecondLargestNumberInTheArray(int array[])
	{
		// Initialize these to the smallest value possible
		int highest = Integer.MIN_VALUE;
		int secondHighest = Integer.MIN_VALUE;

		// Loop over the array
		for (int element : array) {

			// If current element is greater than highest
			if (element > highest) {

				// assign second highest element to highest element
				secondHighest = highest;

				// highest element to current element
				highest = element;
			} else if (element > secondHighest) {
				// Just replace the second highest
				secondHighest = element;
			}
		}


		// After exiting the loop, secondHighest now represents the second
		// largest value in the array
		return secondHighest;
	}
}
