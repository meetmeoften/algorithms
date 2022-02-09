package com.algoexpert.stack;

import java.util.Arrays;
import java.util.Stack;

public class NextGreaterElement {

	public int[] nextGreaterElement(int[] array) {
		// Write your code here.
		int[] result = new int[array.length];
		Arrays.fill(result, -1);

		Stack<Integer> stack = new Stack<>();

		for(int i= 0;i < 2* array.length; i++) {

			int circularIndex = i % array.length;

			while(stack.size() > 0 && array[stack.peek()] < array[circularIndex]) {
				int top = stack.pop();
				result[top] = array[circularIndex];
			}
			stack.push(circularIndex);
		}

		return result;
	}

	public static void main(String[] args) {
		int[] input = new int[] {2, 5, -3, -4, 6, 7, 2};
		int[] actual = new NextGreaterElement().nextGreaterElement(input);
	}

}
