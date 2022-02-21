package com.algoexpert.binarysearchtree;

import java.util.ArrayList;
import java.util.List;

public class SameBST {

	public static boolean sameBsts(List<Integer> arrayOne, List<Integer> arrayTwo) {
		// Write your code here.
		if(arrayOne.size() == 0 && arrayTwo.size() == 0 ) {
			return true;
		}

		if(arrayOne.size() != arrayTwo.size()) {
			return false;
		}

		if(arrayOne.get(0).intValue() != arrayTwo.get(0).intValue()) {
			return false;
		}

		List<Integer> leftOne = getSmaller(arrayOne);
		List<Integer> leftTwo = getSmaller(arrayTwo);

		List<Integer> rightOne = getBiggerOrEqual(arrayOne);
		List<Integer> rightTwo = getBiggerOrEqual(arrayTwo);


		return sameBsts(leftOne, leftTwo) && sameBsts(rightOne, rightTwo);
	}

	public static List<Integer> getSmaller(List<Integer> array ) {
		ArrayList<Integer> smaller = new ArrayList<>();
		for(int i= 1; i< array.size(); i++) {
			if(array.get(i).intValue() < array.get(0).intValue()) {
				smaller.add(array.get(i));
			}
		}

		return smaller;
	}

	public static List<Integer> getBiggerOrEqual(List<Integer> array ) {
		ArrayList<Integer> biggerOrEqual = new ArrayList<>();
		for(int i= 1; i< array.size(); i++) {
			if(array.get(i).intValue() >= array.get(0).intValue()) {
				biggerOrEqual.add(array.get(i));
			}
		}

		return biggerOrEqual;
	}

}
