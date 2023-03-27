package com.algoexpert.arrays;

public class SecondSmallest {

	public static int secondSmallest(int[] arr) {
		int smallest = Integer.MAX_VALUE;
		int secondSmallest = Integer.MAX_VALUE;

		for(int i= 0; i < arr.length; i++) {
			int value = arr[i];

			if(value < smallest) {
				secondSmallest = smallest;
				smallest =value;
			} else if(value < secondSmallest && value != smallest) {
				secondSmallest = value;
			}
		}
		return secondSmallest;
	}

	public static void main(String[] args) {
		int result = secondSmallest(new int[] {0, 0});
		System.out.println(result);
	}


	public static int[] findThreeLargestNumbers(int[] array) {
		int[] largest = new int[] {Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE};

		for(int i: array ) {
			if(i > largest[2]) {
				int temp = largest[2];
				int temp2 = largest[1];
				largest[2] = i;
				largest[1] = temp;
				largest[0] = temp2;
			} else if(i > largest[1]) {

			} else if(i > largest[0]) {

			}
		}
		return largest;
	}

}
