package com.algoexpert2.recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StaircaseTraversal {

	public static int staircaseTraversal(int height, int maxSteps) {
		// Write your code here.
		Map<Integer, Integer> map = new HashMap<>();
		return numberOfWaysToTop(height, maxSteps, map);
	}

	public static int numberOfWaysToTop(int height, int maxSteps, Map<Integer, Integer> map) {
		if (height <= 1) {
			return 1;
		}
		if(map.containsKey(height)) {
			return map.get(height);
		}

		int numberOfWays = 0;
		for (int i = 1; i < Math.min(maxSteps, height) + 1; i++) {
			numberOfWays += numberOfWaysToTop(height - i, maxSteps, map);
		}
		map.put(height, numberOfWays);
		return numberOfWays;
	}

	public static int staircaseTraversal2(int height, int maxSteps) {
		int[] waysToTop = new int[height+1];

		waysToTop[0] = 1;
		waysToTop[1] = 1;

		for(int i= 2; i < height+1; i++) {
			int step = 1;
			while(step <= maxSteps && step <= i) {
				waysToTop[i] = waysToTop[i] + waysToTop[i-step];
				step++;
			}
		}

		return waysToTop[height];

	}

	public static int staircaseTraversal3(int height, int maxSteps) {
		// Write your code here.
		int currentNumberOfWays = 0;
		List<Integer> waysToTop = new ArrayList<Integer>();
		waysToTop.add(1);

		for (int currentHeight = 1; currentHeight <= height; currentHeight++) {
			int startOfWindow = currentHeight - maxSteps - 1;
			int endOfWindow = currentHeight - 1;

			if (startOfWindow >= 0) {
				currentNumberOfWays -= waysToTop.get(startOfWindow);
			}

			currentNumberOfWays += waysToTop.get(endOfWindow);
			waysToTop.add(currentNumberOfWays);
		}

		return waysToTop.get(height);
	}

	public static void main(String[] args) {
		int stairs = 4;
		int maxSteps = 2;
		int expected = 5;
		int actual = staircaseTraversal(stairs, maxSteps);
	}

}
