package com.test.binaryTree.patel;

import java.util.Stack;

class MinStack {

	/** initialize your data structure here. */

	int min;
	Stack<Integer> stack;

	public MinStack() {
		min = Integer.MAX_VALUE;
		stack = new Stack<Integer>();

	}

	public void push(int x) {
		if(x <= min){
			stack.push(min);
			min=x;
		}
		stack.push(x);
	}

	public void pop() {
		if(stack.pop() == min) {
			min=stack.pop();
		}
	}

	public int top() {
		return stack.peek();
	}

	public int getMin() {
		return min;
	}


	public static void main(String[] args) {
		MinStack minStack = new MinStack();
		minStack.push(-2);
		minStack.push(0);
		minStack.push(-3);
		minStack.getMin();  // --> Returns -3.
		minStack.pop();
		minStack.top();      //--> Returns 0.
		minStack.getMin();   // --> Returns -2.
	}
}