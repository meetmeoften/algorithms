package com.neetcode.stack;

import java.util.Arrays;
import java.util.Stack;

public class CarFleet {

	public static int carFleet2(int target, int[] position, int[] speed) {
		if (position.length == 1) {
			return 1;
		}
		Stack<Double> stack = new Stack<>();
		int[][] combine = new int[position.length][2];

		for (int i = 0; i < position.length; i++) {
			combine[i][0] = position[i];
			combine[i][1] = speed[i];
		}

		Arrays.sort(combine, java.util.Comparator.comparingInt(o -> o[0]));

		for (int i = combine.length - 1; i >= 0; i--) {
			double currentTime = (double) (target - combine[i][0]) / combine[i][1];
			if (!stack.isEmpty() && currentTime <= stack.peek()) {
				continue;
			} else {
				stack.push(currentTime);
			}
		}
		return stack.size();
	}

	public static int carFleet(int target, int[] position, int[] speed) {
		int n = position.length;
		if (n == 0) return 0;

		// Step 1: Pair each car with its time to reach the destination
		double[][] cars = new double[n][2];
		for (int i = 0; i < n; i++) {
			cars[i][0] = position[i];
			cars[i][1] = (double)(target - position[i]) / speed[i];
		}

		// Step 2: Sort cars by position in descending order (from closest to farthest from target)
		Arrays.sort(cars, (a, b) -> Double.compare(b[0], a[0]));

		// Step 3: Count fleets using a stack-like approach
		int fleets = 0;
		double lastTime = 0;

		for (int i = 0; i < n; i++) {
			double currentTime = cars[i][1];
			if (currentTime > lastTime) {
				fleets++;
				lastTime = currentTime; // New fleet leader
			}
			// else: current car joins an existing fleet
		}

		return fleets;
	}

	public static void main(String[] args) {
		int target = 12;
		int[] position = {10,8,0,5,3}, speed = {2,4,1,1,3};
		carFleet(target, position, speed);
	}

}
