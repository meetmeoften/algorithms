package com.test.binaryTree.patel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SubArraysWithZeroSum {


	public static void findSumZeroSubArrays(int[] arr) {
		Map<Integer, Integer> sumMap = new HashMap<Integer, Integer>();
		Integer sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];

			if (sum == 0) {
				printSubArray(arr, 0, i);
			}
			if (sumMap.get(sum) != null) {
				printSubArray(arr, sumMap.get(sum) + 1, i);
			} else {
				sumMap.put(sum, i);
			}
		}
	}

	public static int[] zeroSum(int[] arr) {
		HashMap<Integer, Integer> sums = new HashMap<Integer, Integer>();

		int sum = 0;
		for (int i = 0; i <= arr.length; i++) {
			Integer oldIndex = sums.get(sum);
			if (oldIndex == null && i == arr.length) {
				return null;
			} else if (oldIndex == null) {
				sums.put(sum, i);
				sum += arr[i];
			} else {
				System.out.println("DHD");
				//				return Arrays.copyOfRange(arr, oldIndex, i);
			}
		}

		return null;
	}

	public static void printSubArray(int[] arr, int startIndex, int endIndex) {
		for (int i = startIndex; i <= endIndex; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void printallSubarrays(int arr[])
	{
		// n is length of the array
		int n = arr.length;

		// consider all sub-arrays starting from i
		for (int i = 0; i < n; i++)
		{
			int sum = 0;

			// consider all sub-arrays ending at j
			for (int j = i; j < n; j++)
			{
				// sum of elements so far
				sum += arr[j];

				// if sum is seen before, we have found a sub-array with 0 sum
				if (sum == 0) {
					System.out.println("Subarray [" + i + ".." + j + "]");
				}
			}
		}
	}

	public static void printallSubarraysMap(int arr[])
	{
		// n is length of the array
		int n = arr.length;

		// create an empty Multi-map to store ending index of all
		// sub-arrays having same sum
		Map<Integer, List<Integer>> hashMap = new LinkedHashMap<>();

		// insert (0, -1) pair into the map to handle the case when
		// sub-array with 0 sum starts from index 0
		insert(hashMap, 0, -1);

		int sum = 0;

		// traverse the given array
		for (int i = 0; i < n; i++)
		{
			// sum of elements so far
			sum += arr[i];

			// if sum is seen before, there exists at-least one
			// sub-array with 0 sum
			if (hashMap.containsKey(sum))
			{
				List<Integer> list = hashMap.get(sum);

				// find all sub-arrays with same sum
				for (Integer value: list) {
					System.out.println("Subarray [" + (value + 1) + ".." +
							i + "]");
				}
			}

			// insert (sum so far, current index) pair into the Multi-map
			insert(hashMap, sum, i);
		}
	}

	private static<K,V> void insert(Map<K, List<V>> hashMap, K key, V value)
	{
		// if the key is seen for the first time, initialize the list
		if (!hashMap.containsKey(key)) {
			hashMap.put(key, new ArrayList<>());
		}

		hashMap.get(key).add(value);
	}


	public static void main(String[] args) {
		int[] arr = {6, 3, -1, -3, 4,  -2, 2,
				4, 6, -12, -7};
		//		findSumZeroSubArrays(arr);
		//		zeroSum(arr);
		printallSubarrays(arr);
		System.out.println("-------------");
		printallSubarraysMap(arr);
	}

}
