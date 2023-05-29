package com.algoexpert.arrays;

import java.util.ArrayList;
import java.util.List;

public class AlternatePositiveAndNegative {

	public static void rearrange(int arr[], int n) {
		ArrayList<Integer> list1 = new ArrayList<>();// storing positive integers
		ArrayList<Integer> list2 = new ArrayList<>();// storing negative integers

		for (int i = 0; i < n; i++) {
			if (arr[i] >= 0) {
				list1.add(arr[i]);
			} else {
				list2.add(arr[i]);
			}
		}

		int i = 0, j = 0, k = 0;
		while (i < list1.size() && j < list2.size()) {

			arr[k] = list1.get(i);
			k++;
			i++;

			arr[k] = list2.get(j);
			k++;
			j++;

		}

		while (i < list1.size()) {
			arr[k] = list1.get(i);
			k++;
			i++;
		}

		while (j < list2.size()) {
			arr[k] = list2.get(j);
			k++;
			j++;
		}
		System.out.println(arr);
	}


	public static void leaders(int[] arr) {

		List<Integer> list = new ArrayList<>();

		int n = arr.length;

		list.add(arr[n-1]);

		int max = arr[n-1];

		for(int i= n-2; i >= 0; i--) {
			if(arr[i] > max) {
				list.add(0, arr[i]);
				max = arr[i];
			}
		}
		System.out.println(list);
	}

	public static void main(String[] args) {
		// int[] arr = new int[] { 9, 4, -2, -1, 5, 0, -5, -3, 2 };
		int[] arr = new int[] {1,2,-3,-1,-2,3 };

		int[] arr2 = {10, 22, 12, 3, 0, 6};
		rearrange(arr, arr.length);
		leaders(arr2);
	}

}
