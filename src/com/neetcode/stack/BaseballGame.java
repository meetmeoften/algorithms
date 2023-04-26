package com.neetcode.stack;

import java.util.Stack;

public class BaseballGame {

	public static int calPoints(String[] operations) {
		Stack<Integer> st = new Stack<>();

		for (String op : operations) {
			if (op.equals("+") && st.size() >= 2) {
				int score1 = st.pop();
				int score2 = st.peek();
				int score3 = score1 + score2;
				st.push(score1);
				st.push(score3);
			} else if (op.equals("D") && !st.isEmpty()) {
				int score = st.peek();
				st.push(score * 2);
			} else if (op.equals("C") && !st.isEmpty()) {
				st.pop();
			} else {
				st.push(Integer.parseInt(op));
			}
		}

		int sum = 0;
		while (!st.isEmpty()) {
			sum += st.pop();
		}

		return sum;
	}

	public static void main(String[] args) {
		String[] points = { "5","2","C","D","+" };
		calPoints(points);
	}

}
