package com.algoexpert2.stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class LargestRectangleUnderSkyLine {


	public int largestRectangleUnderSkyline(ArrayList<Integer> buildings) {
		Stack<Integer> stack = new Stack<Integer>();
		int maxArea = 0;

		ArrayList<Integer> copy = new ArrayList<>(buildings);
		copy.add(0);

		for (int i = 0; i < copy.size(); i++) {
			int height = copy.get(i);
			//			System.out.println(i + " , " + height);

			while (!stack.isEmpty() && copy.get(stack.peek()) >= height) {
				int pillarHeight = copy.get(stack.pop());
				int width;
				if (stack.isEmpty()) {
					width = i;
				} else {
					width = i - stack.peek() - 1;
				}
				maxArea = Math.max(width * pillarHeight, maxArea);
			}
			stack.push(i);
		}

		return maxArea;
	}

	public static void main(String[] args) {
		// ArrayList<Integer> input = new ArrayList<Integer>(Arrays.asList(1, 3, 3, 2, 4, 1, 5, 3, 2));
		ArrayList<Integer> input = new ArrayList<Integer>(Arrays.asList(2,1,5,6,2,3));
		int expected = 9;
		var actual = new LargestRectangleUnderSkyLine().largestRectangleUnderSkyline(input);
	}

}
