package com.algoexpert.intuit;

import java.util.ArrayList;

public class MaxSumSubarray {

	static ArrayList<Integer> findSubarray(int a[], int n) {
		// code here
		int s = 0;
		int end = 0;
		int start = 0;
		long sum = 0;
		long max = 0;

		for (int i = 0; i < n; i++) {
			sum = sum + a[i];
			if (sum > max) {
				max = sum;
				start = s;
				end = i;
			}

			if (a[i] == 0 && sum == max) {
				end++;
			}

			if (a[i] < 0) {
				sum = 0;
				s = i + 1;
			}
		}
		// System.out.println(count);
		ArrayList<Integer> list = new ArrayList<>();
		if (max > 0) {
			for (int i = start; i <= end; i++) {
				list.add(a[i]);
			}
			return list;
		}
		list.add(-1);
		return list;
	}


	public static void main(String[] args) {
		int[] arr = new int[]{1, 2, 5, 0, -7, 2, 3};
		findSubarray(arr, arr.length);
	}

}
