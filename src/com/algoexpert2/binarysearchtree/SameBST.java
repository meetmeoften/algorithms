package com.algoexpert2.binarysearchtree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SameBST {

	public static boolean sameBsts(List<Integer> arrayOne, List<Integer> arrayTwo) {
		// Write your code here.
		if (arrayOne.size() == 0 && arrayTwo.size() == 0) {
			return true;
		}

		if (arrayOne.size() != arrayTwo.size()) {
			return false;
		}

		if (arrayOne.get(0).intValue() != arrayTwo.get(0).intValue()) {
			return false;
		}

		List<Integer> leftOne = getSmaller(arrayOne);
		List<Integer> leftTwo = getSmaller(arrayTwo);

		List<Integer> rightOne = getBiggerOrEqual(arrayOne);
		List<Integer> rightTwo = getBiggerOrEqual(arrayTwo);

		return sameBsts(leftOne, leftTwo) && sameBsts(rightOne, rightTwo);
	}

	public static List<Integer> getSmaller(List<Integer> array) {
		ArrayList<Integer> smaller = new ArrayList<>();
		for (int i = 1; i < array.size(); i++) {
			if (array.get(i).intValue() < array.get(0).intValue()) {
				smaller.add(array.get(i));
			}
		}

		return smaller;
	}

	public static List<Integer> getBiggerOrEqual(List<Integer> array) {
		ArrayList<Integer> biggerOrEqual = new ArrayList<>();
		for (int i = 1; i < array.size(); i++) {
			if (array.get(i).intValue() >= array.get(0).intValue()) {
				biggerOrEqual.add(array.get(i));
			}
		}

		return biggerOrEqual;
	}

	public static void main(String[] args) {
		List<Integer> arrayOne = new ArrayList<Integer>(Arrays.asList(10, 15, 8, 12, 94, 81, 5, 2, 11));
		List<Integer> arrayTwo = new ArrayList<Integer>(Arrays.asList(10, 8, 5, 15, 2, 12, 11, 94, 81));
		sameBsts(arrayOne, arrayTwo);
	}

}
