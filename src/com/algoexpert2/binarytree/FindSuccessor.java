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
		BinaryTree node = root.left.right;
		BinaryTree expected = root;
		BinaryTree output = findSuccessor(root, node);
		assert (expected == output);
	}

}
