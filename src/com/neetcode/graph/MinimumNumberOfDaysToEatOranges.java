package com.neetcode.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class MinimumNumberOfDaysToEatOranges {

	private Map<Integer, Integer> f = new HashMap<>();

	public int minDays(int n) {
		return dfs(n);
	}

	private int dfs(int n) {
		if (n < 2) {
			return n;
		}
		if (f.containsKey(n)) {
			return f.get(n);
		}
		int res = 1 + Math.min(n % 2 + dfs(n / 2), n % 3 + dfs(n / 3));
		f.put(n, res);
		return res;
	}

	// ----------

	public int minDays2(int n) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(n);
		int res = 0;
		Set<Integer> set = new HashSet<>();

		while (!q.isEmpty()) {
			res++;
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int cur = q.poll();
				if (cur == 0) {
					return res - 1;
				}
				if (set.contains(cur)) {
					continue;
				} else {
					set.add(cur);
				}
				if (cur % 3 == 0) {
					q.offer(cur / 3);
				}
				if (cur % 2 == 0) {
					q.offer(cur / 2);
				}
				q.offer(cur - 1);
			}
		}

		return res;

	}

	public static void main(String[] args) {
		MinimumNumberOfDaysToEatOranges min = new MinimumNumberOfDaysToEatOranges();
		System.out.println(min.minDays(10));
	}

}
