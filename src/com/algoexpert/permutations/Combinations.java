package com.algoexpert.permutations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Combinations {

	public List<List<Integer>> combine(int n, int k) {
		if (n == 0 || k > n) {
			return Collections.emptyList();
		}

		List<List<Integer>> result = new ArrayList<>();
		helper(1, n, k, result, new ArrayList<>());
		return result;
	}

	private void helper(int start, int end, int numbersLeft, List<List<Integer>> result, List<Integer> temp) {
		if (numbersLeft == 0) {
			result.add(new ArrayList<>(temp));
			return;
		}

		for (int i = start; i <= end; i++) {
			temp.add(i);
			helper(i + 1, end, numbersLeft - 1,  result, temp);
			temp.remove(temp.size() - 1);
		}
	}
}
