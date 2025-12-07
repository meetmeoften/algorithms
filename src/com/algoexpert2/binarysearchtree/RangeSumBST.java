package com.algoexpert2.binarysearchtree;

public class RangeSumBST {

	static class BST {
		public int value;
		public BST left = null;
		public BST right = null;

		public BST(int value) {
			this.value = value;
		}
	}

	public int rangeSumBST(BST root, int low, int high) {
		if (root == null) return 0;

		// Prune: current node outside range
		if (root.value < low) {
			return rangeSumBST(root.right, low, high);
		}
		if (root.value > high) {
			return rangeSumBST(root.left, low, high);
		}

		// Current node in range + recurse both sides
		return root.value +
				rangeSumBST(root.left, low, high) +
				rangeSumBST(root.right, low, high);
	}

	public static void main(String[] args) {
		var root = new BST(10);
		root.left = new BST(5);
		root.right = new BST(15);
		root.left.left = new BST(3);
		root.left.right = new BST(7);
		root.right.left = new BST(18);

		var nodeOne = root;
		var nodeTwo = root.left;
		var nodeThree = root.left.right.left;
		boolean expected = true;
		int actual = new RangeSumBST().rangeSumBST(root, 7, 15);
		System.out.println(actual);
	}

}
