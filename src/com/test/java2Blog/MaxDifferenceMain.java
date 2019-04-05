package com.test.java2Blog;

public class MaxDifferenceMain {

	public static void main(String[] args) {
		//		int arr[]={14, 12, 70, 15, 95, 65, 22, 30};
		int arr[] = {12, 14};
		System.out.println("Maximum difference between two elements : "+calculateMaxDifferenceBetweenTwoElements(arr));

	}
	public static int calculateMaxDifferenceBetweenTwoElements(int[] arr)
	{
		int minElementTillNow=arr[0];
		int maxDifference=Integer.MIN_VALUE;
		for (int element : arr) {
			int difference=0;
			if(element >minElementTillNow)
			{
				difference=element-minElementTillNow;
				if(difference > maxDifference)
				{
					maxDifference=difference;
				}
			}
			else
			{
				minElementTillNow=element;
			}
		}
		return maxDifference;
	}

}
