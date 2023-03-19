package com.algoexpert.binarytree;

import java.util.HashMap;
import java.util.Map;

public class HouseRobber3 {

	public static int rob(TreeNode root) {
		Map<TreeNode, Integer> memo = new HashMap<>();
		return robHelper(root, memo);
	}

	private static int robHelper(TreeNode root, Map<TreeNode, Integer> memo) {
		if (root == null) {
			return 0;
		}
		if (memo.containsKey(root)) {
			return memo.get(root);
		}

		int sum = 0;

		if (root.left != null) {
			sum += robHelper(root.left.left, memo) + robHelper(root.left.right, memo);
		}

		if (root.right != null) {
			sum += robHelper(root.right.left, memo) + robHelper(root.right.right, memo);
		}

		sum = Math.max(sum + root.val, robHelper(root.left, memo) + robHelper(root.right, memo));

		memo.put(root, sum);
		return sum;
	}


	public static void main(String[] args) {
		// int[] root = {3,4,5,1,3,999,1};
		TreeNode rootNode = createBinaryTree();
		System.out.println(rob(rootNode));

	}

	public static TreeNode createBinaryTree() {
		TreeNode rootNode = new TreeNode(3);
		TreeNode node2 = new TreeNode(4);
		TreeNode node3 = new TreeNode(5);
		TreeNode node4 = new TreeNode(1);
		TreeNode node5 = new TreeNode(3);
		TreeNode node6 = new TreeNode(1);

		rootNode.left = node2;
		rootNode.right = node3;

		node2.left = node4;
		node2.right = node5;

		node3.right = node6;

		return rootNode;
	}

}
