package com.algoexpert.binarysearchtree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RightSmallerThan {

	public static List<Integer> rightSmallerThan(List<Integer> array) {
		if(array.size() == 0) {
			return new ArrayList<Integer>();
		}
		List<Integer> rightSmallerCounts = new ArrayList<>(array);
		int lastIdx = array.size() -1;
		SpecialBST bst = new SpecialBST(array.get(lastIdx));
		rightSmallerCounts.set(lastIdx, 0);
		for(int i = array.size() -2; i >= 0; i--) {
			bst.insert(array.get(i), i, rightSmallerCounts);
		}
		return rightSmallerCounts;
	}

	static class SpecialBST {
		int value;
		int leftSubTreeSize;
		SpecialBST left;
		SpecialBST right;


		public SpecialBST(int value) {
			this.value = value;
			leftSubTreeSize = 0;
			left =  null;
			right = null;
		}


		public void insert(int value, int idx, List<Integer> rightSmallerCounts) {
			insertHelper(value, idx, rightSmallerCounts, 0);
		}


		private void insertHelper(int value, int idx, List<Integer> rightSmallerCounts, int numSmallerAtInsertTime) {
			if(value < this.value) {
				leftSubTreeSize++;
				if(left == null) {
					left = new SpecialBST(value);
					rightSmallerCounts.set(idx, numSmallerAtInsertTime);
				} else {
					left.insertHelper(value, idx, rightSmallerCounts, numSmallerAtInsertTime);
				}
			} else {
				numSmallerAtInsertTime += leftSubTreeSize;
				if(value > this.value) {
					numSmallerAtInsertTime++;
				}
				if(right == null) {
					right = new SpecialBST(value);
					rightSmallerCounts.set(idx, numSmallerAtInsertTime);
				} else {
					right.insertHelper(value, idx, rightSmallerCounts, numSmallerAtInsertTime);
				}
			}
		}
	}

	public static void main(String[] args) {
		var array = Arrays.asList(8, 5, 11, -1, 3, 4, 2);
		var expected = Arrays.asList(5, 4, 4, 0, 1, 1, 0);
		var actual = rightSmallerThan(array);
	}

}
