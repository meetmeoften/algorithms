package com.algoexpert2.stack;

import java.util.Stack;

public class BestDigits {

	public static String bestDigits(String num, int k) {
		int len = num.length();

		if (k == len) {
			return "0";
		}

		Stack<Character> stack = new Stack<>();
		int i = 0;

		while (i < len) {
			char c = num.charAt(i);

			while (k > 0 && !stack.isEmpty() && stack.peek() < c) {
				stack.pop();
				k--;
			}

			stack.push(c);
			i++;
		}

		while (!stack.isEmpty() && k > 0) {
			stack.pop();
			k--;
		}

		StringBuilder sb = new StringBuilder();
		while (!stack.isEmpty()) {
			sb.append(stack.pop());
		}

		sb.reverse();

		while (sb.length() > 1 && sb.charAt(0) == '0') {
			sb.deleteCharAt(0);
		}

		return sb.toString();
	}

	public static void main(String[] args) {
		String number = "462839";
		int numDigits = 2;
		String expected = "6839";
		var actual = bestDigits(number, numDigits);
	}

}
