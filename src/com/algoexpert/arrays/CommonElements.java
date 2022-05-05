package com.algoexpert.arrays;

import java.util.ArrayList;

public class CommonElements {

	ArrayList<Integer> commonElements(int arr1[], int arr2[], int arr3[], int n1, int n2, int n3) {
		// stores starting index of arr1
		int i = 0;

		// stores starting index of arr2
		int j = 0;

		// stores starting index of arr3
		int k = 0;
		ArrayList<Integer> result = new ArrayList<>();

		while (i < n1 && j < n2 && k < n3) {
			if (arr1[i] == arr2[j] && arr2[j] == arr3[k]) {
				if (!result.contains(arr1[i])) {
					result.add(arr1[i]);
				}
				i++;
				j++;
				k++;
			} else if (arr1[i] < arr2[j]) {
				i++;
			} else if (arr2[j] < arr3[k]) {
				j++;
			} else {
				k++;
			}
		}
		return result;
	}

}
