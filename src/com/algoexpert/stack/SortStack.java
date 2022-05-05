package com.algoexpert.stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class SortStack {

	public ArrayList<Integer> sortStack(ArrayList<Integer> stack) {
		// Write your code here.
		if (stack.size() == 0) {
			return stack;
		}
		int top = stack.remove(stack.size() - 1);
		sortStack(stack);
		insertInSortedOrder(stack, top);
		return stack;
	}

	public void insertInSortedOrder(ArrayList<Integer> stack, int value) {
		if (stack.size() == 0 || stack.get(stack.size() - 1) <= value) {
			stack.add(value);
			return;
		}
		int top = stack.remove(stack.size() - 1);
		insertInSortedOrder(stack, value);
		stack.add(top);
	}

	public Stack<Integer> sort(Stack<Integer> s) {
		Stack<Integer> tmp = new Stack<Integer>();
		Stack<Integer> tmp2 = new Stack<Integer>();
		tmp.push(s.pop());
		while (!s.empty()) {
			if (s.peek() < tmp.peek()) {
				while (!tmp.empty() && s.peek() < tmp.peek()) {
					tmp2.push(tmp.pop());
				}
				tmp.push(s.pop());
				while (!tmp2.empty()) {
					tmp.push(tmp2.pop());
				}
			} else {
				tmp.push(s.pop());
			}
		}
		return tmp;
	}

	public static void main(String[] args) {
		ArrayList<Integer> stack = new ArrayList<Integer>(Arrays.asList(-5, 2, -2, 4, 3, 1));
		ArrayList<Integer> expected = new ArrayList<Integer>(Arrays.asList(-5, -2, 1, 2, 3, 4));
		var actual = new SortStack().sortStack(stack);
	}

}
