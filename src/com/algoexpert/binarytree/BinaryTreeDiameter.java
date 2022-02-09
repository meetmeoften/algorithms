package com.algoexpert.binarytree;

public class BinaryTreeDiameter {

	public int binaryTreeDiameter(BinaryTree tree) {
		return getTreeInfo(tree).diameter;
	}

	private TreeInfo getTreeInfo(BinaryTree tree) {
		// TODO Auto-generated method stub
		if(tree == null) {
			return new TreeInfo(0, 0);
		}
		TreeInfo leftInfo = getTreeInfo(tree.left);
		TreeInfo rightInfo = getTreeInfo(tree.right);

		int currentHeight = 1 + Math.max(leftInfo.height, rightInfo.height);

		int longestPathThroughout = leftInfo.height + rightInfo.height;
		int maxDiameterSoFar = Math.max(leftInfo.diameter, rightInfo.diameter);

		int currentDiameter = Math.max(maxDiameterSoFar, longestPathThroughout);

		return new TreeInfo(currentDiameter, currentHeight);
	}

	static class BinaryTree {
		public int value;
		public BinaryTree left = null;
		public BinaryTree right = null;

		public BinaryTree(int value) {
			this.value = value;
		}
	}

	static class TreeInfo {
		public int diameter;
		public int height;

		public TreeInfo(int diameter, int height) {
			this.diameter = diameter;
			this.height = height;
		}

	}
}
