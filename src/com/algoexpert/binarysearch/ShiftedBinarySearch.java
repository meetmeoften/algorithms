package com.algoexpert.binarysearch;

public class ShiftedBinarySearch {

	public static int shiftedBinarySearch(int[] array, int target) {
		// Write your code here.
		return shiftedBinarySearch(array, target, 0, array.length-1);
	}

	public static int shiftedBinarySearch(int[] array, int target, int left, int right) {
		while(left <= right) {
			int middle = (left+ right)/2;
			int potentialMatch = array[middle];
			int leftNum = array[left];
			int rightNum = array[right];
			if(target == potentialMatch) {
				return middle;
			} else if (leftNum <= potentialMatch) {
				if(target < potentialMatch && target >= leftNum) {
					right = middle -1;
				} else {
					left = middle +1;
				}
			} else {
				if(target > potentialMatch && target <= rightNum) {
					left = middle + 1;
				} else {
					right= middle -1;
				}
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		System.out.println(shiftedBinarySearch(new int[] {45, 61, 71, 72, 73, 0, 1, 21, 33, 37}, 33));
	}

}
