package com.algoexpert.dp;

import java.util.Arrays;

public class MinimumNumberOfJumps {

	public static int minNumberOfJumps(int[] array) {
		if(array.length == 1) {
			return 0;
		}
		int[] jumps = new int[array.length];
		Arrays.fill(jumps, Integer.MAX_VALUE);
		jumps[0] = 0;

		for(int i=1; i< array.length; i++) {
			for(int j= 0; j < i; j++) {
				if(array[j] + j >= i) {
					jumps[i] = Math.min(jumps[i] , jumps[j]+1);
				}
			}
		}
		return jumps[jumps.length-1];
	}

	public static int minNumberOfJumps2(int[] array) {
		if(array.length == 1) {
			return 0;
		}
		int jumps = 0;
		int maxReach = array[0];
		int steps = array[0];

		for(int i=1; i < array.length-1; i++){
			maxReach = Math.max(maxReach, i+ array[i]);
			steps--;
			if(steps == 0) {
				jumps++;
				steps = maxReach-i;
			}

		}
		return jumps+1;
	}

	public static void main(String[] args) {
		int[] input = {3, 4, 2, 1, 2, 3, 7, 1, 1, 1, 3};
		minNumberOfJumps2(input);
	}

}
