package com.algoexpert.greedy;

import java.util.Stack;

public class ValidParenthesisString {

	public static boolean checkValidString(String s) {
		Stack<Integer> op = new Stack<>();
		Stack<Integer> st = new Stack<>();

		for (int i = 0; i < s.length(); ++i) {
			if (s.charAt(i) == '(') {
				op.push(i); // index of opening
			} else if (s.charAt(i) == ')') {
				if (op.size() > 0) {
					op.pop(); // if we have brackets
				} else if (st.size() > 0) {
					st.pop(); // if not brackets do we have stars
				} else {
					return false; // a closing bracket without opening and star
				}
			} else {
				st.push(i); // index of star
			}
		}

		// if we left with some opening bracket that over stars con cover up
		while (op.size() > 0 && st.size() > 0) {
			if (op.peek() > st.peek()) {
				return false;
			}
			op.pop();
			st.pop();
		}

		return op.size() == 0;
	}

	public static boolean checkValidString2(String s) {
		int low = 0, high = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				low++;
				high++;
			} else if (s.charAt(i) == ')') {
				if (low > 0) {
					low--;
				}
				high--;
			} else {
				if (low > 0) {
					low--;
				}
				high++;
			}
			if (high < 0) {
				return false;
			}
		}
		return low == 0;
	}

	// --------------------------

	private Boolean[][] dp;

	public boolean checkValidString3(String s) {
		dp = new Boolean[s.length() + 1][s.length() + 1];
		return check(s, 0, 0);
	}

	private boolean check(String s, int start, int count) {
		if (count < 0) {
			return false;
		}
		int y = count;
		if (dp[start][y] != null) {
			return dp[start][y];
		}
		for (int i = start; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '(') {
				count++;
			} else if (c == ')') {
				if (count <= 0) {
					return false;
				}
				count--;
			} else if (c == '*') {
				dp[start][y] = (check(s, i + 1, count + 1) || check(s, i + 1, count - 1) || check(s, i + 1, count));
				return dp[start][y];
			}
		}
		dp[start][y] = (count == 0);
		return dp[start][y];
	}

	public static void main(String[] args) {
		checkValidString("(*))*(*");
		checkValidString2("(*()");
	}

}
