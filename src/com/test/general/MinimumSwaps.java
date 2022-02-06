//package com.test.general;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Comparator;
//
//import com.sun.tools.javac.util.Pair;
//
//public class MinimumSwaps {
//	// Function returns the minimum number of swaps
//	// required to sort the array
//	public static int minSwaps(int[] arr) {
//		int n = arr.length;
//
//		// Create two arrays and use as pairs where first
//		// array is element and second array
//		// is position of first element
//		ArrayList<Pair<Integer, Integer>> arrpos = new ArrayList<jdk.internal.util.xml.impl.Pair<Integer, Integer>>();
//		for (int i = 0; i < n; i++) {
//			arrpos.add(new Pair<Integer, Integer>(arr[i], i));
//		}
//
//		// Sort the array by array element values to
//		// get right position of every element as the
//		// elements of second array.
//		arrpos.sort(new Comparator<Pair<Integer, Integer>>() {
//			@Override
//			public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
//				if (o1.getValue() > o2.getValue()) {
//					return -1;
//				} else if (o1.getValue().equals(o2.getValue())) {
//					return 0;
//				} else {
//					return 1;
//				}
//			}
//		});
//
//		// To keep track of visited elements. Initialize
//		// all elements as not visited or false.
//		Boolean[] vis = new Boolean[n];
//		Arrays.fill(vis, false);
//
//		// Initialize result
//		int ans = 0;
//
//		// Traverse array elements
//		for (int i = 0; i < n; i++) {
//			// already swapped and corrected or
//			// already present at correct pos
//			if (vis[i] || arrpos.get(i).getValue() == i) {
//				continue;
//			}
//
//			// find out the number of node in
//			// this cycle and add in ans
//			int cycle_size = 0;
//			int j = i;
//			while (!vis[j]) {
//				vis[j] = true;
//
//				// move to next node
//				j = arrpos.get(j).getValue();
//				cycle_size++;
//			}
//
//			// Update answer by adding current cycle.
//			ans += (cycle_size - 1);
//		}
//
//		// Return result
//		return ans;
//	}
//
//	static int findLargestSubArray(int arr[], int n) {
//		int sum = 0;
//		int maxsize = -1, fromIndex;
//
//		// Pick a starting point as i
//		for (int i = 0; i < n - 1; i++) {
//			if (arr[i] == 0) {
//				sum = -1;
//			} else {
//				sum = 1;
//			}
//
//			// Consider all subarrays
//			for (int j = i + 1; j < n; j++) {
//				if (arr[j] == 0) {
//					sum += -1;
//				} else {
//					sum += 1;
//				}
//
//				/*
//				 * If this is a 0 sum subarray, then compare it with maximum size subarray
//				 * calculated so far
//				 */
//				if (sum == 0 && maxsize < j - i + 1) {
//					maxsize = j - i + 1;
//					fromIndex = i;
//				}
//			}
//		}
//
//		return maxsize;
//	}
//
//	public static long count(int n) {
//		int result = 2;
//		int total = 1;
//		for (int i = 1; i <= n; i++) {
//			if (i == 1) {
//				continue;
//			} else {
//				result = result * 2;
//			}
//			total = total + result;
//
//		}
//		return total;
//	}
//
//	// Driver class
//	// Driver program to test the above function
//	public static void main(String[] args) {
//		int[] a = { 1, 1, 0, 1 };
//		MinimumSwaps g = new MinimumSwaps();
//		System.out.println(g.minSwaps(a));
//
//	}
//}
//// This code is contributed by Saksham Seth