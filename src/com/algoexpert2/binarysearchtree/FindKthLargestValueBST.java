package com.algoexpert2.binarysearchtree;

import com.zjava8.java.B;

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


	public int findKthLargestValueInBstOptimized(BST tree, int k) {
		int[] arr = new int[]{0, -1}; // count, latestValue
		helper(tree, k,arr);
		return arr[1];
	}

	private void helper(BST tree, int k, int[] arr) {
		if(tree == null || arr[0] >= k) {
			return;
		}
		helper(tree.right, k, arr);
		if(arr[0] < k) {
			arr[0]++;
			arr[1] = tree.value;
		}
		helper(tree.left, k, arr);
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
		//var actual = new FindKthLargestValueBST().findKthLargestValueInBst(root, k);
		var actual = new FindKthLargestValueBST().findKthLargestValueInBstOptimized(root, k);
		System.out.println(actual);
	}
}
