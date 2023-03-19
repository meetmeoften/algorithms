package com.algoexpert.stack;

import java.util.Stack;

public class AsteroidCollision {

	public static int[] asteroidCollision(int[] asteroids) {
		Stack<Integer> stack = new Stack<>();
		int index = 0;

		while (index < asteroids.length) {
			int a = asteroids[index];
			if (stack.isEmpty()) {
				stack.push(a);
				index++;
			} else {
				if (stack.peek() > 0 && a < 0) {
					if (Math.abs(stack.peek()) > Math.abs(a)) {
						index++;
					} else if (Math.abs(stack.peek()) < Math.abs(a)) {
						stack.pop();
					} else {
						index++;
						stack.pop();
					}
				} else {
					stack.push(a);
					index++;
				}
			}
		}

		int[] res = new int[stack.size()];
		for (int i = res.length - 1; i >= 0; i--) {
			res[i] = stack.pop();
		}
		return res;
	}

	public static void main(String[] args) {
		int[] asteroids = { 5, 4, -5 };
		asteroidCollision(asteroids);
	}

}
