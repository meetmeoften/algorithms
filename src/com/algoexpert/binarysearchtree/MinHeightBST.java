package com.algoexpert.binarysearchtree;

import java.util.List;

public class MinHeightBST {

	public static BST minHeightBst(List<Integer> array) {
		// Write your code here.
		return construct(array, null, 0, array.size() -1);
	}

	public static BST construct(List<Integer> array, BST bst, int start, int end) {
		if(start > end) {
			return null;
		}
		int mid = (start + end)/2;

		int valueToAdd = array.get(mid);
		if(bst == null) {
			bst = new BST(valueToAdd);
		} else {
			bst.insert(valueToAdd);
		}

		construct(array, bst, start, mid-1);
		construct(array, bst, mid+1, end);

		return bst;
	}

	public static BST construct(List<Integer> array, int start, int end) {
		if(start > end) {
			return null;
		}
		int mid = (start + end)/2;
		int valueToAdd = array.get(mid);
		BST bst = new BST(valueToAdd);
		bst.left = construct(array, start, mid-1);
		bst.right = construct(array, mid+1, end);
		return bst;
	}

	static class BST {
		public int value;
		public BST left;
		public BST right;

		public BST(int value) {
			this.value = value;
			left = null;
			right = null;
		}

		public void insert(int value) {
			if (value < this.value) {
				if (left == null) {
					left = new BST(value);
				} else {
					left.insert(value);
				}
			} else {
				if (right == null) {
					right = new BST(value);
				} else {
					right.insert(value);
				}
			}
		}
	}

}
