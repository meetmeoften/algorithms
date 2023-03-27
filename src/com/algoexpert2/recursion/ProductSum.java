package com.algoexpert2.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductSum {

	public static int productSum(List<Object> array) {
		return helper(array, 1);
	}

	public static int helper(List<Object> array, int depth) {
		int total = 0;
		for (Object value : array) {
			if (value instanceof List) {
				List<Object> subList = (List<Object>) value;
				total += helper(subList, depth + 1);
			} else if (value instanceof Integer) {
				total = total + (Integer) value;
			}

		}
		return total * depth;
	}

	public static void main(String[] args) {
		List<Object> test = new ArrayList<Object>(Arrays.asList(5, 2, new ArrayList<Object>(Arrays.asList(7, -1)), 3,
				new ArrayList<Object>(Arrays.asList(6, new ArrayList<Object>(Arrays.asList(-13, 8)), 4))));
		productSum(test);
	}

}
