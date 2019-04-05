package com.test.java2Blog;

import java.util.Arrays;
import java.util.HashMap;

public class FindPairEqualToGivenNumberMain {

	public static void main(String[] args) {
		int array[] = { -40, -5, 1, 7, 8, 3, 6, 9, 20 };
		findPairsWithSumEqualsToXBruteForce(array, 15);
		findPairsEqualsToX(array, 15);
		findPairsEqualsToXUsingHashing(array, 15);
	}

	public static void findPairsWithSumEqualsToXBruteForce(int arr[], int X) {
		if (arr.length < 2) {
			return;
		}

		System.out.println(
				"The pair whose sum is closest to 15 using brute force method: ");
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				int tempSum = arr[i] + arr[j];

				if (tempSum == X) {
					System.out.println(arr[i] + " " + arr[j]);
				}
			}
		}
	}

	public static void findPairsEqualsToX(int arr[], int X) {

		int n = arr.length;
		if (n < 2) {
			return;
		}
		Arrays.sort(arr);
		System.out.println("The pair whose sum is closest to 15 : ");
		// left and right index variables
		int l = 0, r = n - 1;

		while (l < r) {
			int currentSum = arr[l] + arr[r];
			if (currentSum == X) {
				System.out.println(arr[l] + " " + arr[r]);
				l++;
				r--;
			} else if (arr[l] + arr[r] < X) {
				l++;
			} else {
				r--;
			}
		}
	}

	public static void findPairsEqualsToXUsingHashing(int arr[], int X) {
		HashMap<Integer, Integer> elementIndexMap = new HashMap<Integer, Integer>();
		System.out.println("The pair whose sum is closest to 15 : ");
		for (int i = 0; i < arr.length; i++) {
			elementIndexMap.put(arr[i], i);
		}
		for (int i = 0; i < arr.length; i++) {
			// we have used elementIndexMap.get(X-arr[i])!=i to avoid using same
			// element twice
			if (elementIndexMap.get(X - arr[i]) != null
					&& elementIndexMap.get(X - arr[i]) != i) //
			{
				System.out.println(arr[i] + " " + (X - arr[i]));
			}
		}
	}
}
