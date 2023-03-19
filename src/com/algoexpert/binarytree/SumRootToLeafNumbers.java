package com.algoexpert.binarytree;

public class SumRootToLeafNumbers {

	private int sum;

	public int sumNumbers(TreeNode root) {
		sum = 0;
		dfs(root, new StringBuilder());
		return sum;
	}

	public void dfs(TreeNode node, StringBuilder sb) {
		if(node == null) {
			return;
		}

		sb.append(node.val);

		if(node.left == null && node.right == null) {
			sum += Integer.parseInt(sb.toString());
		}

		dfs(node.left, sb);
		dfs(node.right, sb);
		sb.deleteCharAt(sb.length()-1);

	}

	public static void main(String[] args) {
		// TreeNode rootNode = TreeNode.createBinaryTree123();
		TreeNode rootNode = createBinaryTree49051();
		SumRootToLeafNumbers leaf = new SumRootToLeafNumbers();
		leaf.sumNumbers(rootNode);
	}

	public static TreeNode createBinaryTree49051() {
		TreeNode rootNode = new TreeNode(4);
		TreeNode node2 = new TreeNode(9);
		TreeNode node3 = new TreeNode(0);
		TreeNode node4 = new TreeNode(5);
		TreeNode node5 = new TreeNode(1);

		rootNode.left = node2;
		rootNode.right = node3;

		node2.left = node4;
		node2.right = node5;

		return rootNode;
	}

}
