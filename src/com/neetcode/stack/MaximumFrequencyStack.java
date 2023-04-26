package com.neetcode.stack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class MaximumFrequencyStack {


	private List<Stack<Integer>> sList;
	private Map<Integer, Integer> map;

	public MaximumFrequencyStack() {
		sList = new ArrayList<>();
		map = new HashMap<>();
	}

	public void push(int x) {
		map.put(x, map.getOrDefault(x, 0) + 1);

		if (sList.size() < map.get(x)) {
			sList.add(new Stack<>());
		}
		sList.get(map.get(x) - 1).push(x);
	}

	public int pop() {
		int result = sList.get(sList.size() - 1).pop();
		map.put(result, map.get(result) - 1);

		if (sList.get(sList.size() - 1).isEmpty()) {
			sList.remove(sList.size() - 1);
		}

		return result;
	}

	public static void main(String[] args) {
		MaximumFrequencyStack freqStack = new MaximumFrequencyStack();
		freqStack.push(5);
		freqStack.push(7);
		freqStack.push(5);
		freqStack.push(7);
		freqStack.push(4);
		freqStack.push(5);
		freqStack.pop();
		freqStack.pop();
		freqStack.pop();
		freqStack.pop();
	}

}
