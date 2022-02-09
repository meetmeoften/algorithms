package com.algoexpert.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeNumberSum {

	public static List<Integer[]> threeNumberSum(int[] array, int targetSum) {
		// Write your code here.
		Arrays.sort(array);
		List<Integer[]> triplets = new ArrayList<Integer[]>();

		for(int i=0; i<array.length; i++) {
			int left = i+1;
			int right = array.length -1;
			while(left < right) {
				int cs = array[i] + array[left] + array[right];
				if(cs == targetSum) {
					Integer[] newTriplet = {array[i], array[left], array[right]};
					triplets.add(newTriplet);
					left++;
					right--;
				} else if (cs > targetSum) {
					right--;
				} else if(cs < targetSum) {
					left++;
				}
			}
		}
		return triplets;
	}

	public static void main(String[] args) {
		List<Integer[]> expected = new ArrayList<Integer[]>();
		expected.add(new Integer[] {-8, 2, 6});
		expected.add(new Integer[] {-8, 3, 5});
		expected.add(new Integer[] {-6, 1, 5});
		List<Integer[]> output = threeNumberSum(new int[] {12, 3, 1, 2, -6, 5, -8, 6}, 0);
	}

}
