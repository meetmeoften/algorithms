package com.algoexpert.arrays;

public class ContainerWithMostWater {

	public int maxArea(int[] height) {
		if (height == null || height.length < 2) {
			return 0;
		}
		int start = 0, end = height.length - 1;
		int maxArea = Integer.MIN_VALUE;

		while (start < end) {

			int area = Math.min(height[start], height[end]) * (end - start);
			maxArea = Math.max(maxArea, area);

			if (height[start] < height[end]) {
				start++;
			} else {
				end--;
			}
		}
		return maxArea;
	}

	public static void main(String[] args) {
		new ContainerWithMostWater().maxArea(new int[] {1,8,6,2,5,4,8,3,7});
	}

}
