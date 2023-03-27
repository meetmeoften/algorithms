package com.algoexpert2.binarytree;

public class MergeBinaryTrees {

	static class BinaryTree {
		public int value;
		public BinaryTree left = null;
		public BinaryTree right = null;

		public BinaryTree(int value) {
			this.value = value;
		}
	}

	public static BinaryTree mergeBinaryTrees(BinaryTree tree1, BinaryTree tree2) {
		if (tree1 == null) {
			return tree2;
		}

		if (tree2 == null) {
			return tree1;
		}

		tree1.value += tree2.value;
		tree1.left = mergeBinaryTrees(tree1.left, tree2.left);
		tree1.right = mergeBinaryTrees(tree1.right, tree2.right);

		return tree1;
	}

	public static void main(String[] args) {
		BinaryTree tree1 = new BinaryTree(1);
		tree1.left = new BinaryTree(3);
		tree1.left.left = new BinaryTree(7);
		tree1.left.right = new BinaryTree(4);
		tree1.right = new BinaryTree(2);

		BinaryTree tree2 = new BinaryTree(1);
		tree2.left = new BinaryTree(5);
		tree2.left.left = new BinaryTree(2);
		tree2.right = new BinaryTree(9);
		tree2.right.left = new BinaryTree(7);
		tree2.right.right = new BinaryTree(6);

		BinaryTree actual = mergeBinaryTrees(tree1, tree2);
	}

}
