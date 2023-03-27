package com.algoexpert2.dp;

public class WaterArea {

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

			if(heights[leftIndex] < heights[rightIndex]) {
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



	public static void main(String[] args) {
		int[] input = {0, 8, 0, 0, 5, 0, 0, 10, 0, 0, 1, 1, 0, 3};
		waterArea(input);
	}


}
