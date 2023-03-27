package com.algoexpert2.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PowerSetAlgoExpert {

	public static List<List<Integer>> powerset(List<Integer> array) {
		if (array.isEmpty()) {
			return new ArrayList<>();
		}
		List<List<Integer>> result = new ArrayList<>();
		helper(0, array, result, new ArrayList<Integer>());
		return result;
	}

	public static void helper(int index, List<Integer> array, List<List<Integer>> result, List<Integer> curr) {
		result.add(new ArrayList<>(curr));

		for (int i = index; i < array.size(); i++) {
			int value = array.get(i);
			curr.add(value);
			helper(i + 1, array, result, curr);
			curr.remove(curr.size() - 1);
		}
	}

	// ---------------------

	public static List<List<Integer>> powerset2(List<Integer> array) {
		return powerSetHelper(array, array.size() - 1);
	}

	public static List<List<Integer>> powerSetHelper(List<Integer> array, int index) {
		if (index < 0) {
			List<List<Integer>> emptySet = new ArrayList<>();
			emptySet.add(new ArrayList<>());
			return emptySet;
		}

		int element = array.get(index);
		List<List<Integer>> subsets = powerSetHelper(array, index - 1);
		int length = subsets.size();
		for (int i = 0; i < length; i++) {
			List<Integer> currentSubset = new ArrayList<Integer>(subsets.get(i));
			currentSubset.add(element);
			subsets.add(currentSubset);
		}
		return subsets;

	}

	public static void main(String[] args) {
		List<List<Integer>> output = powerset(new ArrayList<Integer>(Arrays.asList(1, 2, 3)));
		System.out.println(output);
	}
}
