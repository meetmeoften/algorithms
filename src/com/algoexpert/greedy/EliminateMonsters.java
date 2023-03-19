package com.algoexpert.greedy;

import java.util.Arrays;

public class EliminateMonsters {

	public static int eliminateMaximum(int[] dist, int[] speed) {
		int[] time = new int[dist.length];

		for (int i = 0; i < dist.length; i++) {
			time[i] = (int) Math.ceil((double) dist[i] / speed[i]);
		}

		Arrays.sort(time);

		int ans = 0;
		int T = 0;
		for (int i = 0; i < time.length; i++) {
			if (T++ < time[i]) {
				ans++;
			} else {
				break;
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		int[] dist = { 3, 100, 4 };
		int[] speed = { 5, 100, 2 };
		eliminateMaximum(dist, speed);

	}

}
