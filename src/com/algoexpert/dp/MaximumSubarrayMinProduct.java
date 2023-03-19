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

	public static void main(String[] args) {
		maxSumMinProduct(new int[] { 1, 2, 3, 2 });

	}

}
