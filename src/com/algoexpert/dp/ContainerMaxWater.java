package com.algoexpert.dp;

public class ContainerMaxWater {

	public static int waterArea(int[] heights) {
		// Write your code here
		if(heights.length == 0) {
			return 0;
		}
		var leftIndex = 0;
		var rightIndex = heights.length -1;
		var leftMax = heights[leftIndex];
		var rightMax = heights[rightIndex];
		var surfaceArea = 0;
		while(leftIndex < rightIndex) {
			var lh = heights[leftIndex];
			var rh = heights[rightIndex];
			System.out.println(lh + " | " + rh);
			if(lh < rh) {
				leftIndex++;
				leftMax = Math.max(leftMax, heights[leftIndex]);
				surfaceArea += leftMax- heights[leftIndex];
			} else {
				rightIndex--;
				rightMax = Math.max(rightMax, heights[rightIndex]);
				surfaceArea += rightMax- heights[rightIndex];

			}
		}
		return surfaceArea;
	}

	public static int waterArea2(int[] heights) {
		// Write your code here.
		int[] leftMaxes = new int[heights.length];
		int leftMax = 0;
		for(int i=0; i < heights.length; i++) {
			int height = heights[i];
			leftMaxes[i] = leftMax;
			leftMax = Math.max(leftMax, height);
		}

		int[] rightMaxes = new int[heights.length];
		int rightMax = 0;
		for(int i= heights.length-1; i>=0; i--) {
			int height = heights[i];
			rightMaxes[i] = rightMax;
			rightMax = Math.max(rightMax, height);
		}

		int[] maxes = new int[heights.length];
		for(int i= 0; i< heights.length; i++) {
			if(heights[i] < Math.min(leftMaxes[i], rightMaxes[i])) {
				maxes[i] = Math.min(leftMaxes[i], rightMaxes[i]) - heights[i];
			} else {
				maxes[i] = 0;
			}
		}

		int total = 0;
		for(int i= 0; i < heights.length; i++) {
			total = total+ maxes[i];
		}
		return total;
	}

	public static void main(String[] args) {
		int[] input = {0, 8, 0, 0, 5, 0, 0, 10, 0, 0, 1, 1, 0, 3};
		waterArea(input);
	}

}
