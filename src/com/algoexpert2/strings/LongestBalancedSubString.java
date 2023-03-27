package com.algoexpert2.strings;

import java.util.Stack;

public class LongestBalancedSubString {

	public int longestBalancedSubstring(String string) {
		int maxLength = 0;
		Stack<Integer> stack = new Stack<>();
		stack.push(-1);

		for (int i = 0; i < string.length(); i++) {
			if (string.charAt(i) == '(') {
				stack.push(i);
			} else {
				stack.pop();
				if (stack.size() == 0) {
					stack.push(i);
				} else {
					int startIdx = stack.peek();
					int length = i - startIdx;
					maxLength = Math.max(maxLength, length);
				}
			}
		}
		return maxLength;
	}

	public int longestBalancedSubstring2(String string) {
		// Write your code here.
		int maxLength = 0;

		int openingCount = 0;
		int closingCount = 0;

		for (int i = 0; i < string.length(); i++) {
			char c = string.charAt(i);

			if (c == '(') {
				openingCount++;
			} else {
				closingCount++;
			}

			if (openingCount == closingCount) {
				maxLength = Math.max(maxLength, closingCount * 2);
			} else if (closingCount > openingCount) {
				openingCount = 0;
				closingCount = 0;
			}

		}

		openingCount = 0;
		closingCount = 0;

		// Case ((())(
		for (int i = string.length() - 1; i >= 0; i--) {
			char c = string.charAt(i);

			if (c == '(') {
				openingCount++;
			} else {
				closingCount++;
			}

			if (openingCount == closingCount) {
				maxLength = Math.max(maxLength, openingCount * 2);
			} else if (openingCount > closingCount) {
				openingCount = 0;
				closingCount = 0;
			}

		}
		return maxLength;
	}

	public static void main(String[] args) {
		var input = "(()))(";
		var expected = 4;
		new LongestBalancedSubString().longestBalancedSubstring(input);
		new LongestBalancedSubString().longestBalancedSubstring2(input);
		// Utils.assertTrue(expected == actual);
	}

}
