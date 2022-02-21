package com.algoexpert.binarysearchtree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RightSmallerThan2 {

	public static List<Integer> rightSmallerThan(List<Integer> array) {
		if(array.size() == 0) {
			return new ArrayList<Integer>();
		}
		int lastIdx = array.size() -1;
		SpecialBST bst = new SpecialBST(array.get(lastIdx), lastIdx, 0);
		for(int i = array.size() -2; i >= 0; i--) {
			bst.insert(array.get(i), i);
		}
		List<Integer> rightSmallerCounts = new ArrayList<Integer>(array);
		getRightSmallerCounts(bst, rightSmallerCounts);
		return rightSmallerCounts;
	}


	private static void getRightSmallerCounts(SpecialBST bst, List<Integer> rightSmallerCounts) {
		if(bst == null) {
			return;
		}
		rightSmallerCounts.set(bst.idx, bst.numSmallerAtInsertTime);
		getRightSmallerCounts(bst.left, rightSmallerCounts);
		getRightSmallerCounts(bst.right, rightSmallerCounts);

	}


	static class SpecialBST {
		int value;
		int idx;
		int numSmallerAtInsertTime;
		int leftSubTreeSize;
		SpecialBST left;
		SpecialBST right;


		public SpecialBST(int value, int idx, int numSmallerAtInsertTime) {
			this.value = value;
			this.idx = idx;
			this.numSmallerAtInsertTime = numSmallerAtInsertTime;
			leftSubTreeSize = 0;
			left =  null;
			right = null;
		}


		public void insert(int value, int idx) {
			insertHelper(value, idx,  0);		}


		private void insertHelper(int value, int idx, int numSmallerAtInsertTime) {
			if(value < this.value) {
				leftSubTreeSize++;
				if(left == null) {
					left = new SpecialBST(value, idx, numSmallerAtInsertTime);
				} else {
					left.insertHelper(value, idx, numSmallerAtInsertTime);
				}
			} else {
				numSmallerAtInsertTime += leftSubTreeSize;
				if(value > this.value) {
					numSmallerAtInsertTime++;
				}
				if(right == null) {
					right = new SpecialBST(value, idx, numSmallerAtInsertTime);
				} else {
					right.insertHelper(value, idx, numSmallerAtInsertTime);
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
