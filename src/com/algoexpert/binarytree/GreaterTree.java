package com.algoexpert.binarytree;

public class GreaterTree {

	int sum = 0;

	public TreeNode convertBST(TreeNode root) {
		if (root == null) {
			return root;
		}
		reverseInorder(root);
		return root;
	}

	private void reverseInorder(TreeNode root) {
		if (root == null) {
			return;
		}
		reverseInorder(root.right);
		root.val += sum;
		sum = root.val;
		reverseInorder(root.left);
		return;
	}


	public static void main(String[] args) {
		TreeNode rootNode = TreeNode.createBinaryTree123();
		GreaterTree g = new GreaterTree();
		g.convertBST(rootNode);
	}

}
