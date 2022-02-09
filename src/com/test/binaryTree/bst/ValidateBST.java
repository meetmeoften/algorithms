package com.test.binaryTree.bst;

public class ValidateBST {

	public static boolean validateBst(BST tree) {
		// Write your code here.
		return validateBst(tree, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	public static boolean validateBst(BST tree, int minValue, int maxValue) {
		if(tree.value < minValue || tree.value >= maxValue) {
			return false;
		}

		if(tree.left != null && !validateBst(tree.left, minValue, tree.value)) {
			return false;
		}

		if(tree.right != null && !validateBst(tree.right, tree.value, maxValue)) {
			return false;
		}

		return true;
	}

	public static void main(String[] args) {
		var bst = BST.createBinaryTree();
		System.out.println(validateBst(bst));
	}

}
