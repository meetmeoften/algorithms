package com.algoexpert.binarysearch;

public class FindPeakElement {

	public static int findPeakElement(int[] nums) {
		int i = 0, j = nums.length - 1;

		while (i < j) {
			int m = (i + j) / 2;
			if (nums[m] < nums[m + 1]) {
				i = m + 1;
			} else {
				j = m;
			}
		}
		return i;
	}

	public static int findPeakElement2(int[] array) {
		if(array.length == 1) {
			return 0;
		}
		int i= 1;
		int index = 0;
		int max = array[0];
		while(i < array.length-1) {
			boolean isPeak = array[i] > array[i-1] && array[i] > array[i+1] && array[i] > max;
			if(isPeak) {
				index = i;
				max = array[i];
			}
			i++;
		}

		if(array[array.length-1] > max) {
			return array.length-1;
		}

		return index;

	}

	public static void main(String[] args) {
		int nums[] = { 1, 7, 1, 3, 5, 6, 4 };
		findPeakElement(nums);
		findPeakElement2(nums);
	}

}
