package com.algoexpert.intuit;

import java.util.ArrayList;

public class AlternatePositiveAndNegative {

	void rearrange(int arr[], int n) {
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
	}
}
