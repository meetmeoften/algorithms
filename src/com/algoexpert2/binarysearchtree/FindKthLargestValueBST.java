package com.algoexpert2.binarysearchtree;

import java.util.ArrayList;
import java.util.List;

public class FindKthLargestValueBST {

	static class BST {
		public int value;
		public BST left = null;
		public BST right = null;

		public BST(int value) {
			this.value = value;
		}
	}

	public int findKthLargestValueInBst(BST tree, int k) {
		List<Integer> sorted = new ArrayList<>();
		performInorder(tree, sorted);
		return sorted.get(sorted.size() - k);
	}

	public void performInorder(BST bst, List<Integer> sorted) {

		if (bst != null) {
			performInorder(bst.left, sorted);
			sorted.add(bst.value);
			performInorder(bst.right, sorted);
		}
	}

	public static void main(String[] args) {
		BST root = new BST(15);
		root.left = new BST(5);
		root.left.left = new BST(2);
		root.left.left.left = new BST(1);
		root.left.left.right = new BST(3);
		root.left.right = new BST(5);
		root.right = new BST(20);
		root.right.left = new BST(17);
		root.right.right = new BST(22);
		int k = 3;
		int expected = 17;
		var actual = new FindKthLargestValueBST().findKthLargestValueInBst(root, k);
	}

}
