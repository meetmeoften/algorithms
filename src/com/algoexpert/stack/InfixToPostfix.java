package com.algoexpert.stack;

import java.util.ArrayDeque;

public class InfixToPostfix {

	public static String infixToPostfix(String exp) {
		String res = "";
		ArrayDeque<Character> stack = new ArrayDeque<>();

		for (int i = 0; i < exp.length(); i++) {
			char c = exp.charAt(i);

			if (c == '+' || c == '-' || c == '*' || c == '/' || c == '^') {
				while (!stack.isEmpty() && getPre(stack.peek()) >= getPre(c)) {
					res += stack.pop();
				}
				stack.push(c);
			} else if (c == '(') {
				stack.push(c);
			} else if (c == ')') {
				while (stack.peek() != '(') {
					res += stack.pop();
				}
				stack.pop();
			} else {
				res += c;
			}
		}

		while (!stack.isEmpty()) {
			res += stack.pop();
		}
		return res;
	}

	private static int getPre(char op) {
		switch (op) {
		case '+':
		case '-':
			return 1;
		case '*':
		case '/':
			return 2;
		case '^':
			return 3;
		}

		return -1;
	}

	public static void main(String[] args) {
		System.out.println(infixToPostfix("A*(B*C+D*E)+F"));
	}

}
