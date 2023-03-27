package com.algoexpert2.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MoveElementToEnd {

	public static List<Integer> moveElementToEnd(List<Integer> array, int toMove) {
		// Write your code here.

		int i = 0;
		int j = array.size() - 1;

		while (i < j) {
			while (i < j && array.get(j) == toMove) {
				j--;
			}
			if (array.get(i) == toMove) {
				int temp = array.get(i);
				array.set(i, array.get(j));
				array.set(j, temp);
			}
			i++;

		}
		return array;
	}

	public static void main(String[] args) {
		List<Integer> array = new ArrayList<Integer>(Arrays.asList(2, 1, 2, 2, 2, 3, 4, 2));
		int toMove = 2;
		List<Integer> expectedStart = new ArrayList<Integer>(Arrays.asList(1, 3, 4));
		List<Integer> expectedEnd = new ArrayList<Integer>(Arrays.asList(2, 2, 2, 2, 2));
		List<Integer> output = moveElementToEnd(array, toMove);
		List<Integer> outputStart = output.subList(0, 3);
		outputStart.sort(Comparator.naturalOrder());
		List<Integer> outputEnd = output.subList(3, output.size());
		// Utils.assertTrue(outputStart.equals(expectedStart));
		// Utils.assertTrue(outputEnd.equals(expectedEnd));
	}

}
