package com.algoexpert2.binarytree;

public class HeightBalancedTree {

	static class BinaryTree {
		public int value;
		public BinaryTree left = null;
		public BinaryTree right = null;

		public BinaryTree(int value) {
			this.value = value;
		}
	}

	static class TreeInfoHeight {
		public boolean isBalanced;
		public int height;

		public TreeInfoHeight(boolean isBalanced, int height) {
			this.isBalanced = isBalanced;
			this.height = height;
		}

	}

	public boolean heightBalancedBinaryTree(BinaryTree tree) {
		TreeInfoHeight treeInfoHeight = getTreeInfoHeight(tree);
		return treeInfoHeight.isBalanced;
	}

	private TreeInfoHeight getTreeInfoHeight(BinaryTree tree) {
		if (tree == null) {
			return new TreeInfoHeight(true, 0);
		}

		TreeInfoHeight leftInfo = getTreeInfoHeight(tree.left);
		TreeInfoHeight rightInfo = getTreeInfoHeight(tree.right);

		boolean isBalanced = leftInfo.isBalanced && rightInfo.isBalanced
				&& Math.abs(leftInfo.height - rightInfo.height) <= 1;

		if (!isBalanced) {
			return new TreeInfoHeight(false, 0);
		}

		int currentHeight = 1 + Math.max(leftInfo.height, rightInfo.height);
		return new TreeInfoHeight(true, currentHeight);
	}

	public static void main(String[] args) {
		BinaryTree root = new BinaryTree(1);
		root = new BinaryTree(1);
		root.left = new BinaryTree(2);
		root.right = new BinaryTree(3);
		root.left.left = new BinaryTree(4);
		root.left.right = new BinaryTree(5);
		root.right.right = new BinaryTree(6);
		root.left.right.left = new BinaryTree(7);
		root.left.right.right = new BinaryTree(8);
		boolean expected = true;
		var actual = new HeightBalancedTree().heightBalancedBinaryTree(root);
	}

}
