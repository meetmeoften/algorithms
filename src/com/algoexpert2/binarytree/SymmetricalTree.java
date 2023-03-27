package com.algoexpert2.binarytree;

public class SymmetricalTree {

	static class BinaryTree {
		public int value;
		public BinaryTree left = null;
		public BinaryTree right = null;

		public BinaryTree(int value) {
			this.value = value;
		}
	}

	public boolean symmetricalTree(BinaryTree tree) {
		// Write your code here.
		if (tree == null) {
			return false;
		}
		return symmetrical(tree.left, tree.right);
	}

	private boolean symmetrical(BinaryTree tree1, BinaryTree tree2) {
		if (tree1 == null && tree2 == null) {
			return true;
		}

		if (tree1 == null || tree2 == null) {
			return false;
		}

		if (tree1.value != tree2.value) {
			return false;
		}
		return symmetrical(tree1.left, tree2.right) && symmetrical(tree1.right, tree2.left);
	}

	public static void main(String[] args) {
		BinaryTree tree = new BinaryTree(10);
		tree.left = new BinaryTree(5);
		tree.right = new BinaryTree(5);
		tree.left.left = new BinaryTree(7);
		tree.left.right = new BinaryTree(9);
		tree.right.left = new BinaryTree(9);
		tree.right.right = new BinaryTree(7);
		var expected = true;
		var actual = new SymmetricalTree().symmetricalTree(tree);
	}

}
