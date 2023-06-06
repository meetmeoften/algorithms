package com.algoexpert.heap;

import java.util.PriorityQueue;
import java.util.TreeSet;

public class MinimizeDeviation {

	public static int minimumDeviation(int[] nums) {
		TreeSet<Integer> set = new TreeSet<>();
		for (int x : nums) {
			if (x % 2 == 0) {
				set.add(x);
			} else {
				set.add(x * 2);
			}
		}
		int ans = Integer.MAX_VALUE;
		while (true) {
			int val = set.last();
			ans = Math.min(ans, val - set.first());
			if (val % 2 == 0) {
				set.remove(val);
				set.add(val / 2);
			} else {
				break;
			}
		}
		return ans;
	}

	public static int minimumDeviation2(int[] A) {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		int n = A.length, mi = Integer.MAX_VALUE, res = Integer.MAX_VALUE;
		for (int a : A) {
			if (a % 2 == 1) {
				a *= 2;
			}
			pq.add(-a);
			mi = Math.min(mi, a);
		}
		while (true) {
			int a = -pq.poll();
			res = Math.min(res, a - mi);
			if (a % 2 == 1) {
				break;
			}
			mi = Math.min(mi, a / 2);
			pq.add(-a / 2);
		}
		return res;
	}

	public static void main(String[] args) {
		int[] nums = { 1, 2, 3, 4 };
		minimumDeviation(nums);
		minimumDeviation2(nums);
	}

}
