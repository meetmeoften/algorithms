

package com.test.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutations {

	public static List<List<Integer>> getPermutations(List<Integer> array) {
		// Write your code here.
		List<List<Integer>> permutations = new ArrayList<>();
		getPermutations(0, array, permutations);
		return permutations;
	}

	public static void getPermutations(int i,
			List<Integer> array,
			List<List<Integer>> permutations) {
		if(i == array.size() -1) {
			permutations.add(new ArrayList<Integer>(array));
		} else {
			for(int j= i; j < array.size(); j++) {
				swap(array, i, j);
				getPermutations(i+1, array, permutations);
				swap(array, i, j);
			}
		}
	}

	public static void swap(List<Integer> array, int i, int j) {
		var temp = array.get(i);
		array.set(i, array.get(j));
		array.set(j, temp);
	}

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3));
		var result = getPermutations(list);
		result.stream().forEach(e ->  System.out.println(e));
	}

}
