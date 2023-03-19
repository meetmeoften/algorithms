package com.algoexpert.greedy;

public class MaxTurbulence {

	public static int maxTurbulenceSize(int[] arr) {
		if (arr.length == 1) {
			return 1;
		}
		int i = 1;
		while (i < arr.length && arr[i - 1] == arr[i]) {
			i++;
		}
		if (i == arr.length) {
			return 1;
		}
		int maxTurb = 2;
		int turb = maxTurb;

		for (; i < arr.length - 1; i++) {
			int prev = i - 1;
			int current = i;
			int next = i + 1;
			if ((arr[prev] < arr[current] && arr[current] > arr[next])
					|| (arr[prev] > arr[current] && arr[current] < arr[next])) {
				turb++;
				if (turb > maxTurb) {
					maxTurb = turb;
				}
			} else {
				turb = 2;
			}
		}
		return maxTurb;
	}

	public static int maxTurbulenceSize2(int[] arr) {
		int ans = 1;
		int s = -1;
		int i = 0, j = 1;
		for (; i < j && j < arr.length; j++) {
			if (arr[j] == arr[j - 1]) {
				ans = Math.max(ans, j - i);
				s = -1;
				i = j;
			} else if (s == -1) {
				if (arr[j] > arr[j - 1]) {
					s = 1;
				} else {
					s = 0;
				}
				ans = Math.max(ans, 2);
			} else {
				if (arr[j] > arr[j - 1] && s == 1) {
					ans = Math.max(ans, j - i);
					i = j - 1;
				} else if (arr[j] < arr[j - 1] && s == 0) {
					ans = Math.max(ans, j - i);
					i = j - 1;
				} else {
					s ^= 1;
				}
			}
		}
		ans = Math.max(ans, j - i);
		return ans;
	}

	public static int maxTurbulenceSize3(int[] arr) {
		int n = arr.length;
		if (n < 2) {
			return n;
		}
		int max = 1;
		int st = 0;
		int end = 0;

		while (st + 1 < n) {
			if (arr[st] == arr[st + 1]) {
				st++;
				continue;
			}
			end = st + 1;
			while (end + 1 < n && isTurbulence(arr, end)) {
				end++;
			}
			max = Math.max(max, (end - st + 1));
			st = end;
		}
		return max;
	}

	private static boolean isTurbulence(int[] arr, int k) {
		return (arr[k] > arr[k - 1] && arr[k] > arr[k + 1]) || (arr[k] < arr[k - 1] && arr[k] < arr[k + 1]);
	}

	public static void main(String[] args) {
		int[] arr = { 9, 4, 2, 10, 7, 8, 8, 1, 9 };
		maxTurbulenceSize3(arr);

	}

}
