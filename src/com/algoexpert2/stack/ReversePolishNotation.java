package com.algoexpert2.stack;

import java.util.Stack;

public class ReversePolishNotation {

	public static int reversePolishNotation(String[] tokens) {
		Stack<Integer> st = new Stack<>();

		for (String token : tokens) {
			if (token.equals("+")) {
				int val1 = st.pop();
				int val2 = st.pop();
				st.push(val1 + val2);
			}
			else if (token.equals("-")) {
				int val1 = st.pop();
				int val2 = st.pop();
				st.push(val2 - val1);
			}
			else if (token.equals("*")) {
				int val1 = st.pop();
				int val2 = st.pop();
				st.push(val1 * val2);
			}
			else if (token.equals("/")) {
				int val1 = st.pop();
				int val2 = st.pop();
				st.push(val2 / val1);
			} else {
				st.push(Integer.parseInt(token));
			}
		}
		return st.pop();
	}

	public static void main(String[] args) {
		var input = new String[] {"3", "2", "+", "7", "*"};
		var expected = 35;
		var actual = reversePolishNotation(input);
	}

}
