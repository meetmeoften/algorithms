package com.algoexpert2.binarysearchtree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BSTTraversal {

	public static List<Integer> inOrderTraverse(BST tree, List<Integer> array) {
		// Write your code here.
		if (tree != null) {
			inOrderTraverse(tree.left, array);
			array.add(tree.value);
			inOrderTraverse(tree.right, array);
		}

		return array;
	}

	public static List<Integer> preOrderTraverse(BST tree, List<Integer> array) {
		array.add(tree.value);
		if (tree.left != null) {
			preOrderTraverse(tree.left, array);
		}
		if (tree.right != null) {
			preOrderTraverse(tree.right, array);
		}
		return array;
	}

	public static List<Integer> postOrderTraverse(BST tree, List<Integer> array) {
		if (tree.left != null) {
			postOrderTraverse(tree.left, array);
		}
		if (tree.right != null) {
			postOrderTraverse(tree.right, array);
		}
		array.add(tree.value);
		return array;
	}

	static class BST {
		public int value;
		public BST left;
		public BST right;

		public BST(int value) {
			this.value = value;
		}
	}

	public static void main(String[] args) {
		var root = new BST(10);
		root.left = new BST(5);
		root.left.left = new BST(2);
		root.left.left.left = new BST(1);
		root.left.right = new BST(5);
		root.right = new BST(15);
		root.right.right = new BST(22);

		List<Integer> inOrder = Arrays.asList(new Integer[] { 1, 2, 5, 5, 10, 15, 22 });
		List<Integer> preOrder = Arrays.asList(new Integer[] { 10, 5, 2, 1, 5, 15, 22 });
		List<Integer> postOrder = Arrays.asList(new Integer[] { 1, 2, 5, 5, 22, 15, 10 });

		inOrderTraverse(root, new ArrayList<Integer>());
		preOrderTraverse(root, new ArrayList<Integer>());
		postOrderTraverse(root, new ArrayList<Integer>());
	}

}
