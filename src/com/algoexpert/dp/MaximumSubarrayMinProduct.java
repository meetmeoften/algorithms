package com.algoexpert.dp;

import java.util.Stack;

public class MaximumSubarrayMinProduct {

	// MONOTONIC INCREASING STACK

	public static int maxSumMinProduct(int[] arr) {
		Stack<Integer> st = new Stack<>();
		long dp[] = new long[arr.length + 1], res = 0;
		for (int i = 0; i < arr.length; ++i) {
			dp[i + 1] = dp[i] + arr[i];
		}
		for (int i = 0; i <= arr.length; ++i) {
			while (!st.empty() && (i == arr.length || arr[st.peek()] > arr[i])) {
				int j = st.pop();

				long val = 0;
				if (st.empty()) {
					val = (dp[i] - dp[0]) * arr[j];
				} else {
					val = (dp[i] - dp[st.peek() + 1]) * arr[j];
				}

				res = Math.max(res, val);

				// res = Math.max(res, (dp[i] - dp[st.empty() ? 0 : st.peek() + 1]) * arr[j]);
			}
			st.push(i);
		}
		return (int) (res % 1000000007);
	}

	// -----------

	/**
	 * 	1.We will first find a prefix sum of the array having size(n+1).
		2.Then we will be initializing 2 stacks one for left min and another for right min.
		3.We will be using stack to find the next smallest no on the left and on the right and
			then using the prefix sum we will find the final sum of the window and then multiply wth current element.
		4.We will do it for each element and the take the maximum of it.
		5.In the left min if there are no elements on the left we will assign valueto -1 and in right min if there are no values on the right we will assign value to n.
	 * @param nums
	 * @return
	 */

	public static int maxSumMinProduct2(int[] nums) {
		int n = nums.length;

		// prefix sum
		long prefixsum[] = new long[n + 1];
		for (int i = 1; i <= n; i++) {
			prefixsum[i] = prefixsum[i - 1] + nums[i - 1];
		}

		Stack<Integer> st1 = new Stack<>();
		Stack<Integer> st2 = new Stack<>();

		int left[] = new int[n];
		int right[] = new int[n];

		// left MIN
		for (int i = 0; i < n; i++) {
			while (!st1.isEmpty() && nums[i] <= nums[st1.peek()]) {
				st1.pop();
			}
			if (st1.isEmpty()) {
				left[i] = -1;
			} else {
				left[i] = st1.peek();
			}
			st1.push(i);
		}
		// right MIN
		for (int i = n - 1; i >= 0; i--) {
			while (!st2.isEmpty() && nums[i] <= nums[st2.peek()]) {
				st2.pop();
			}
			if (st2.isEmpty()) {
				right[i] = n;
			} else {
				right[i] = st2.peek();
			}
			st2.push(i);
		}

		long max = 0;
		for (int i = 0; i < n; i++) {
			int l = left[i];
			int r = right[i];
			long sum = prefixsum[r] - prefixsum[l + 1];
			long prod = sum * nums[i];
			max = Math.max(max, prod);
		}
		return (int) (max % 1000000007);
	}

	public static void main(String[] args) {
		maxSumMinProduct(new int[] { 1, 2, 3, 2 });
		maxSumMinProduct2(new int[] { 1, 2, 3, 2 });

	}

}
