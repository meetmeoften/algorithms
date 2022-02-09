package com.test.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PowerSet {

	public static List<List<Integer>> powerset(List<Integer> array) {
		return powerSetHelper(array, array.size()-1);
	}

	public static List<List<Integer>> powerSetHelper(List<Integer> array, int index) {
		if(index <0) {
			List<List<Integer>> emptySet = new ArrayList<>();
			emptySet.add(new ArrayList<>());
			return emptySet;
		}

		int element = array.get(index);
		List<List<Integer>> subsets = powerSetHelper(array, index-1);
		int length =  subsets.size();
		for(int i= 0; i<length; i++) {
			List<Integer> currentSubset = new ArrayList<Integer>(subsets.get(i));
			currentSubset.add(element);
			subsets.add(currentSubset);
		}
		return subsets;

	}

	public static void main(String[] args) {
		System.out.println(powerset(new ArrayList<Integer>(Arrays.asList(1, 2, 3))));
	}

}
