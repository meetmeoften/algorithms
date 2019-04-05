package com.test.general;

import java.util.Stack;

public class NextGreaterSolution {



	public static void printNextGreaterElement(int[] input) {
		Stack<Integer> stack = new Stack<Integer>();
		int inputSize = input.length;
		stack.push(input[0]);
		for (int i = 1; i < inputSize; i++) {
			while (!stack.isEmpty() && stack.peek() < input[i]) {
				System.out.println("Next greater element for "
						+ stack.pop() + "\t = "  + input[i]);
			}
			stack.push(input[i]);
		}
		while (!stack.isEmpty()) {
			int top = stack.pop();
			System.out.println("Next greater element for "  + top + "\t = "  + null);
		}
	}

	public static void main(String[] args) {
		int[] input = { 22, 10 , 98, 23, 54, 12, 20, 7, 27 };
		printNextGreaterElement(input);
	}

}
