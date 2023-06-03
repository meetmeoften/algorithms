package com.algoexpert2.binarysearchtree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class RepairBST {

	static class BST {
		public int value;
		public BST left = null;
		public BST right = null;

		public BST(int value) {
			this.value = value;
		}
	}

	private BST nodeOne = null;
	private BST nodeTwo = null;
	private BST previousNode = null;

	public BST repairBst(BST tree) {
		// Write your code here.

		inOrderTraversal(tree);
		int temp = nodeOne.value;
		nodeOne.value = nodeTwo.value;
		nodeTwo.value = temp;

		return tree;
	}

	public void inOrderTraversal(BST node) {
		if (node == null) {
			return;
		}

		inOrderTraversal(node.left);
		if (previousNode != null && this.previousNode.value > node.value) {
			if (this.nodeOne == null) {
				this.nodeOne = this.previousNode;
			}
			this.nodeTwo = node;
		}
		this.previousNode = node;
		inOrderTraversal(node.right);

	}

	public BST repairBst2(BST node) {

		BST nodeOne = null;
		BST nodeTwo = null;
		BST previousNode = null;

		Stack<BST> stack = new Stack<>();
		BST currentNode = node;

		while (currentNode != null || !stack.isEmpty()) {
			while (currentNode != null) {
				stack.push(currentNode);
				currentNode = currentNode.left;
			}

			currentNode = stack.pop();

			if (previousNode != null && previousNode.value > currentNode.value) {
				if (nodeOne == null) {
					nodeOne = previousNode;
				}
				nodeTwo = currentNode;
			}

			previousNode = currentNode;
			currentNode = currentNode.right;
		}

		int temp = nodeOne.value;
		nodeOne.value = nodeTwo.value;
		nodeTwo.value = temp;

		return node;

	}

	public static void main(String[] args) {
		BST tree = new BST(10);
		tree.left = new BST(7);
		tree.left.left = new BST(3);
		tree.left.right = new BST(12);
		tree.left.left.left = new BST(3);

		tree.right = new BST(20);
		tree.right.left = new BST(8);
		tree.right.right = new BST(22);
		tree.right.left.right = new BST(14);



		List<Integer> expected = Arrays.asList(0, 1, 2, 3, 4);
		List<Integer> actual = inOrderTraverse(new RepairBST().repairBst2(tree), new ArrayList<Integer>());
		// Utils.assertEquals(actual, expected);
	}

	private static List<Integer> inOrderTraverse(BST tree, List<Integer> array) {
		if (tree.left != null) {
			inOrderTraverse(tree.left, array);
		}
		array.add(tree.value);
		if (tree.right != null) {
			inOrderTraverse(tree.right, array);
		}
		return array;
	}
}
