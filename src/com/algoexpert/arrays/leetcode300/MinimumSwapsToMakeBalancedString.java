package com.algoexpert.arrays.leetcode300;

import java.util.Stack;

public class MinimumSwapsToMakeBalancedString {

	// Each operation gets rid of two closing brackets

	public static int minSwapsWithStack(String s) {
		Stack<Character> st = new Stack<>();

		for (char c : s.toCharArray()) {
			if (c == '[') {
				st.push(c);
			} else {
				if (!st.isEmpty() && st.peek() == '[') {
					st.pop();
				} else {
					st.push(c);
				}
			}
		}

		return (st.size() / 2 + 1) / 2;
	}

	public static int minSwapsWithStackS1(String s) {
		Stack<Character> st = new Stack<>();

		for (char c : s.toCharArray()) {
			if (c == 'o') {
				st.push(c);
			} else {
				if (!st.isEmpty() && st.peek() == 'c') {
					st.pop();
				} else {
					st.push(c);
				}
			}
		}

		return (st.size() / 2 + 1) / 2;
	}

	public static int minSwapsWithoutStack(String s) {
		int closed = 0, maxClosed = 0;

		for (char c : s.toCharArray()) {
			if (c == '[') {
				closed--;
			} else {
				closed++;
			}
			maxClosed = Math.max(closed, maxClosed);
		}

		return (maxClosed + 1) / 2;
	}

	public static void main(String[] args) {
		String s = "][][";
		String s1 = "coco";
		minSwapsWithStack(s);
		minSwapsWithStackS1(s1);
		minSwapsWithoutStack(s);
	}
}
