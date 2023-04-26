package com.neetcode.stack;

import java.util.Stack;

public class RemoveAdjacentDuplicates {

	public static String removeDuplicates2(String s, int k) {
		int i = 0, n = s.length();
		int count[] = new int[n];

		char[] stack = s.toCharArray();

		for (int j = 0; j < n; ++j, ++i) {
			stack[i] = stack[j];
			count[i] = 1;
			if (i > 0 && stack[i - 1] == stack[j]) {
				count[i] = count[i - 1] + 1;
			}
			// count[i] = i > 0 && stack[i - 1] == stack[j] ? count[i - 1] + 1 : 1;
			if (count[i] == k) {
				i = i - k;
			}
		}
		return new String(stack, 0, i);
	}

	public static String removeDuplicates(String s, int k) {
		Stack<CharCounter> stack = new Stack<>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (!stack.isEmpty() && c == stack.peek().c) {
				if (stack.peek().count == k - 1) {
					stack.pop();
				} else {
					stack.peek().count++;
				}
			} else {
				stack.push(new CharCounter(c));
			}
		}

		StringBuilder res = new StringBuilder();
		for (CharCounter charCount : stack) {
			res.append(charCount);
		}
		return res.toString();
	}

	private static class CharCounter {
		char c;
		int count;

		CharCounter(char c) {
			this.c = c;
			this.count = 1;
		}

		@Override
		public String toString() {
			return Character.toString(c).repeat(count);
		}
	}

	public static void main(String[] args) {
		removeDuplicates("deeedbbcccbdaa", 3);
	}

}
