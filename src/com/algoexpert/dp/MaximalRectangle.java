package com.algoexpert.dp;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class MaximalRectangle {


	public static int maximalRectangle(char[][] matrix) {
		int max = 0;
		int M = matrix.length;
		int N = matrix[0].length;
		int[] currHistogram = new int[N];

		for (int i=0; i<M; i++) {
			for (int j=0; j<N; j++) {
				if (matrix[i][j] == '0') {
					currHistogram[j] = 0;
				} else {
					currHistogram[j] += (matrix[i][j]-'0');
				}
			}
			max = Math.max(max, largestRectangleArea(currHistogram));
		}

		return max;
	}

	public static int largestRectangleArea(int[] heights) {

		Stack<Integer> stack = new Stack<>();

		List<Integer> copy = Arrays.stream(heights).boxed().collect(Collectors.toList());
		copy.add(0);

		int maxArea = 0;

		for(int i= 0; i < copy.size(); i++) {
			int height = copy.get(i);

			while(!stack.isEmpty() && copy.get(stack.peek()) >= height) {
				int pillarHeight = copy.get(stack.pop());
				int width;
				if(stack.isEmpty()) {
					width  = i;
				} else {
					width = i- stack.peek() - 1;
				}
				int area = pillarHeight * width;
				maxArea = Math.max(maxArea, area);
			}
			stack.push(i);
		}

		return maxArea;
	}


	public static void main(String[] args) {

		char[][] edges = new char[][] {
			{ '1', '0', '1', '0', '0' },
			{ '1', '0', '1', '1', '1' },
			{ '1', '1', '1', '1', '1' },
			{ '1', '0', '0', '1', '0' } };
			maximalRectangle(edges);
	}

}
