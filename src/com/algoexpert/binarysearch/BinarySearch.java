package com.algoexpert.binarysearch;

public class BinarySearch {

	public static int binarySearch(int[] array, int target) {
		// Write your code here.
		int leftIdx = 0;
		int rightIdx = array.length-1;

		while (leftIdx <= rightIdx) {
			System.out.println(leftIdx + " | " + rightIdx);
			int mid = (leftIdx + rightIdx)/2;
			if(array[mid] == target) {
				return mid;
			} else if(target < array[mid]) {
				rightIdx = mid - 1;
			} else {
				leftIdx = mid + 1;
			}
		}
		return -1;
	}



	public static void main(String[] args) {
		binarySearch(new int[] {0, 1, 21, 33, 45, 45, 61, 71, 72, 73}, 33);
	}

}
