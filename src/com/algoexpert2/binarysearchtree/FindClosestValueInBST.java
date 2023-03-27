package com.algoexpert2.binarysearchtree;

import com.test.binaryTree.bst.BST;

public class FindClosestValueInBST {

	// Closest value in BST
	public static int findClosestValueInBst(BST tree, int target) {
		return findClosestValueInBst(tree, target, tree.value);

	}

	public static int findClosestValueInBst(BST tree, int target, int closest) {
		BST currentNode = tree;

		while (currentNode != null) {

			var value1 = Math.abs(target - closest);
			var value2 = Math.abs(target - currentNode.value);

			if (value1 > value2) {
				closest = currentNode.value;
			}

			if (target < currentNode.value) {
				currentNode = currentNode.left;
			} else if (target > currentNode.value) {
				currentNode = currentNode.right;
			} else {
				break;
			}
		}

		return closest;
	}

	public static void main(String[] args) {
		var root = new BST(10);
		root.left = new BST(5);
		root.left.left = new BST(2);
		root.left.left.left = new BST(1);
		root.left.right = new BST(5);
		root.right = new BST(15);
		root.right.left = new BST(13);
		root.right.left.right = new BST(14);
		root.right.right = new BST(22);

		var expected = 13;
		var actual = findClosestValueInBst(root, 12);
	}

}
