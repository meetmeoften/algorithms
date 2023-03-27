package com.algoexpert2.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MergeKSortedArrays {

	public static List<Integer> mergeSortedArrays(List<List<Integer>> arrays) {
		// Write your code here.
		List<Integer> sortedList = new ArrayList<Integer>();
		List<Integer> elementIndexes = new ArrayList<Integer>(Collections.nCopies(arrays.size(), 0));

		while (true) {
			List<Item> smallestItems = new ArrayList<>();
			for(int i = 0; i < arrays.size(); i++) {
				List<Integer> relevantArray = arrays.get(i);
				int elementIndex = elementIndexes.get(i);
				if(elementIndex == relevantArray.size()) {
					continue;
				}
				smallestItems.add(new Item(i, relevantArray.get(elementIndex)));
			}

			if(smallestItems.size() ==0) {
				break;
			}
			Item nextItem = getMinValue(smallestItems);
			sortedList.add(nextItem.num);
			elementIndexes.set(nextItem.arrayIndex, elementIndexes.get(nextItem.arrayIndex) +1);
		}
		return sortedList;
	}

	public static Item getMinValue(List<Item> items) {
		int minValueIndex = 0;
		for(int i=1; i< items.size(); i++) {
			if(items.get(i).num <items.get(minValueIndex).num) {
				minValueIndex = i;
			}
		}
		return items.get(minValueIndex);
	}

	static class Item {
		public int arrayIndex;
		public int num;

		public Item(int arrayIndex, int num) {
			this.arrayIndex = arrayIndex;
			this.num = num;
		}
	}


	public static void main(String[] args) {
		List<List<Integer>> arrays = new ArrayList<List<Integer>>();
		arrays.add(Arrays.asList(new Integer[] {1, 5, 9, 21}));
		arrays.add(Arrays.asList(new Integer[] {-1, 0}));
		arrays.add(Arrays.asList(new Integer[] {-124, 81, 121}));
		arrays.add(Arrays.asList(new Integer[] {3, 6, 12, 20, 150}));
		var actual = mergeSortedArrays(arrays);
	}
}
