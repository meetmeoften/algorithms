package com.algoexpert2.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwoNumberSum {

	public static int[] twoNumberSum(int[] array, int targetSum) {
		// Write your code here.
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

		for (int i = 0; i < array.length; i++) {
			var result = targetSum - array[i];
			var secondNum = map.get(result);
			if (secondNum == null) {
				map.put(array[i], i);
			} else {
				return new int[] { array[secondNum], array[i] };
			}
		}
		return new int[0];
	}

	static List<List<Integer>> twoSum(int[] nums, int left, int right, int target) {
		var twosumList = new ArrayList<List<Integer>>();
		while (left < right) {
			var sum = nums[left] + nums[right];
			if (sum > target) {
				right--;
			} else if (sum < target) {
				left++;
			} else {
				var temp = new ArrayList<Integer>();
				temp.add(nums[left]);
				temp.add(nums[right]);
				twosumList.add(temp);

				while (left < right && nums[left] == nums[left + 1]) {
					left++;
				}

				while (left < right && nums[right] == nums[right - 1]) {
					right--;
				}
				left++;
				right--;
			}
		}
		return twosumList;

	}

	// Two Number sum, array contains duplicates, answer should be unique

	public static List<List<Integer>> twoSum(int[] num, int target) {
		List<List<Integer>> listSet = new ArrayList<List<Integer>>();
		Map<Integer, Integer> map = new HashMap<>();
		if (num.length < 2) {
			return listSet;
		}
		for (int i = 0; i < num.length; i++) {
			int key1 = num[i];
			int key2 = target - num[i];
			if (map.containsKey(key2) && map.get(key2) > 0) {
				List<Integer> list = new ArrayList<Integer>();
				if (key1 < key2) {
					list = Arrays.asList(key1, key2);
				} else {
					list = Arrays.asList(key2, key1);
				}

				if (!listSet.contains(list)) {
					listSet.add(list);
				}
				map.put(key2, map.get(key2) - 1);
			} else {
				if (!map.containsKey(key1)) {
					map.put(key1, 1);
				} else {
					map.put(key1, map.get(key1) + 1);
				}
			}
		}
		return listSet;
	}

	public static int[] twoSum3(int[] nums, int target) {

		// The result array that we will return
		int[] result = new int[2];

		// A map of numbers to a list of their indices(as numbers may occur multiple
		// times)
		Map<Integer, List<Integer>> numIndexMap = new HashMap<>();

		for (int i = 0; i < nums.length; i++) {
			int currentNumber = nums[i];

			// If the number has already been added to the map, it looks we have another
			// occurence of the same number
			// Add the new index to the already created list
			if (numIndexMap.containsKey(currentNumber)) {

				numIndexMap.get(currentNumber).add(i);

			} else {
				// This is the first time we have seen this number.
				// Create a new list and add this index to it

				List<Integer> indexList = new ArrayList<>();
				indexList.add(i);

				numIndexMap.put(currentNumber, indexList);
			}
		}

		// Now, time to solve the problem :-)

		for (int i = 0; i < nums.length; i++) {

			// Get the current number and the remainder
			final int currentNumber = nums[i];
			final int remainder = target - currentNumber;

			// The condition is self-explanatory
			if (currentNumber == remainder) {

				// First check if there indeed are two or more copies of the same number in the
				// array
				// This is where storing the list of indices comes in handy
				if (numIndexMap.get(currentNumber).size() < 2) {
					// Looks like we don't really have another copy of the number. Continue
					continue;
				}

				// We have multiple copies of the same number. The first index is the current
				// one a.k.a "i"
				result[0] = i;

				// We will have to use two different indices.
				// Since we cannot use the same index twice, we find the indexList index of "i"
				// using binary search(indices added in increasing order, so its already sorted)
				// and then get the next available index for currentNumber
				List<Integer> indexList = numIndexMap.get(currentNumber);

				// Find location of current index i
				final int indexOfI = Collections.binarySearch(indexList, i);
				// Get the next index
				result[1] = indexList.get((indexOfI + 1) % indexList.size());
				break;

			} else {

				// Check if remainder exists in the array
				if (!numIndexMap.containsKey(remainder)) {
					continue;
				}

				result[0] = i;

				// Get the first available index
				result[1] = numIndexMap.get(remainder).get(0);
				break;
			}

		}

		return result;
	}

	public static void main(String[] args) {
		twoNumberSum(new int[] { 3, 5, -4, 8, 11, 1, -1, 6 }, 10);
		List<List<Integer>> res = twoSum(new int[] { 8, 7, 5, 6, 5, 7 }, 0, 5, 13);
		List<List<Integer>> result = twoSum(new int[] { 8, 7, 5, 6, 5, 7 }, 13);
		System.out.println(result);
	}

}
