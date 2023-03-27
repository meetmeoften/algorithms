package com.algoexpert2.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermutationsAlgoExpert {

	public static List<List<Integer>> getPermutations(List<Integer> array) {
		if (array.isEmpty()) {
			return new ArrayList<>();
		}
		List<List<Integer>> permutations = new ArrayList<>();
		helper(array, permutations, new ArrayList<Integer>());
		return permutations;
	}

	public static void helper(List<Integer> array, List<List<Integer>> permutations, List<Integer> curr) {
		if (curr.size() == array.size()) {
			permutations.add(new ArrayList<>(curr));
			return;
		}

		for (int i = 0; i < array.size(); i++) {
			int value = array.get(i);
			if (curr.contains(value)) {
				continue;
			}
			curr.add(value);
			helper(array, permutations, curr);
			curr.remove(curr.size() - 1);
		}
	}


	// ------------------------


	public static void main(String[] args) {
		List<Integer> input = new ArrayList<Integer>(Arrays.asList(1, 2, 3));
		List<List<Integer>> perms = getPermutations(input);
		System.out.println(perms);
	}

}
