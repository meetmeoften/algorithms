package com.algoexpert2.binarytree;

import java.util.ArrayList;
import java.util.List;

public class FindSuccessor {

	static class BinaryTree {
		public int value;
		public BinaryTree left = null;
		public BinaryTree right = null;
		public BinaryTree parent = null;

		public BinaryTree(int value) {
			this.value = value;
		}
	}

	public static BinaryTree findSuccessorOptimised(BinaryTree tree, BinaryTree node) {
		// Case 1: Node has a right subtree
		if (node.right != null) {
			return getLeftmostChild(node.right);
		}

		// Case 2: No right subtree → climb upward
		return getRightmostParent(node);
	}

	private static BinaryTree getLeftmostChild(BinaryTree node) {
		BinaryTree current = node;
		while (current.left != null) {
			current = current.left;
		}
		return current;
	}

	private static BinaryTree getRightmostParent(BinaryTree node) {
		BinaryTree current = node;
		while (current.parent != null && current.parent.right == current) {
			current = current.parent;
		}
		return current.parent;
	}


	public static BinaryTree findSuccessor(BinaryTree tree, BinaryTree node) {
		List<BinaryTree> orders = new ArrayList<>();
		inOrderTraversal(tree, orders);

		for (int i = 0; i < orders.size(); i++) {
			BinaryTree current = orders.get(i);

			if (current != node) {
				continue;
			}
			if (i == orders.size() - 1) {
				return null;
			}
			return orders.get(i + 1);
		}
		return null;
	}

	public static void inOrderTraversal(BinaryTree tree, List<BinaryTree> orders) {
		if (tree == null) {
			return;
		}
		inOrderTraversal(tree.left, orders);
		orders.add(tree);
		inOrderTraversal(tree.right, orders);

	}

	public static void main(String[] args) {
		BinaryTree root = new BinaryTree(1);
		root.left = new BinaryTree(2);
		root.left.parent = root;
		root.right = new BinaryTree(3);
		root.right.parent = root;
		root.left.left = new BinaryTree(4);
		root.left.left.parent = root.left;
		root.left.right = new BinaryTree(5);
		root.left.right.parent = root.left;
		root.left.left.left = new BinaryTree(6);
		root.left.left.left.parent = root.left.left;
		BinaryTree node = root;
		BinaryTree expected = root;
		BinaryTree output = findSuccessorOptimised(root, node);
		assert (expected == output);
	}

}
