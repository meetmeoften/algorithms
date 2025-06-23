package com.algoexpert.binarytree;

public class MaximumPathSum {

	int max = Integer.MIN_VALUE;

	public int maxPathSum(TreeNode root) {
		helper(root);
		return max;
	}

	private int helper(TreeNode root) {
		if (root == null) {
			return 0;
		}

		int leftSum = Math.max(0, helper(root.left));
		int rightSum = Math.max(0, helper(root.right));

		max = Math.max(max, root.data + leftSum + rightSum);

		return Math.max(root.data, root.data + Math.max(leftSum, rightSum));

	}

	public static void main(String[] args) {


		MaximumPathSum pathSum = new MaximumPathSum();
		TreeNode rootNode = pathSum.createBinaryTree();
		pathSum.maxPathSum(rootNode);

	}

	private class TreeNode {
		int data;
		TreeNode left;
		TreeNode right;

		TreeNode(int data) {
			this.data = data;
		}
	}

	public TreeNode createBinaryTree() {
		TreeNode rootNode = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);

		rootNode.left = node2;
		rootNode.right = node3;

		return rootNode;
	}

}
