package com.algoexpert.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class HardProblemsStringsAndOthers {

	// Largest Rectangle Under Skyline
	public int largestRectangleUnderSkyline(ArrayList<Integer> buildings) {
		// Write your code here.
		int maxArea = 0;
		for (int i = 0; i < buildings.size(); i++) {
			int height = buildings.get(i);

			int area = height;
			int left = i;
			while (left > 0 && buildings.get(left - 1) >= height) {
				area += height;
				left--;
			}

			int right = i;
			while (right < buildings.size() - 1 && buildings.get(right + 1) >= height) {
				area += height;
				right++;
			}
			maxArea = Math.max(area, maxArea);
		}
		return maxArea;
	}

	public int largestRectangleUnderSkyline2(ArrayList<Integer> buildings) {
		Stack<Integer> stack = new Stack<Integer>();
		int maxArea = 0;

		ArrayList<Integer> copy = new ArrayList<>(buildings);
		copy.add(0);

		for (int i = 0; i < copy.size(); i++) {
			int height = copy.get(i);

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

	//Longest Substrings without duplicates
	public static String longestSubstringWithoutDuplication(String str) {
		Map<Character, Integer> map = new HashMap<>();
		int[] longest = {0, 1};
		int start = 0;

		for(int i= 0; i < str.length(); i++) {
			Character c = str.charAt(i);
			if(map.containsKey(c)) {
				start = Math.max(start, map.get(c) +1);
			}
			if(longest[1] - longest[0] < i+1 - start) {
				longest = new int[]{start, i+1};
			}

			map.put(c, i);
		}
		return str.substring(longest[0], longest[1]);
	}
}
