package com.neetcode.binaryTree;

public class TreeNode {

	public int val;
	public TreeNode left;
	public TreeNode right;

	public TreeNode(int val) {
		this.val = val;
	}


	public static TreeNode createBinaryTree1() {

		TreeNode rootNode = new TreeNode(40);
		TreeNode node20 = new TreeNode(20);
		TreeNode node10 = new TreeNode(10);
		TreeNode node30 = new TreeNode(30);
		TreeNode node60 = new TreeNode(60);
		TreeNode node50 = new TreeNode(50);
		TreeNode node70 = new TreeNode(70);
		TreeNode node5 = new TreeNode(5);
		TreeNode node45 = new TreeNode(45);
		TreeNode node55 = new TreeNode(55);

		rootNode.left = node20;
		rootNode.right = node60;

		node20.left = node10;
		node20.right = node30;

		node60.left = node50;
		node60.right = node70;

		node10.left = node5;
		node50.right = node55;
		return rootNode;
	}

	public static TreeNode createBinaryTree123() {
		TreeNode rootNode = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);

		rootNode.left = node2;
		rootNode.right = node3;

		return rootNode;
	}

	public static TreeNode createBinaryTree12345() {
		TreeNode rootNode = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);

		TreeNode node4 = new TreeNode(4);
		TreeNode node5 = new TreeNode(5);
		TreeNode node6 = new TreeNode(6);

		rootNode.left = node2;
		rootNode.right = node3;

		node2.left = node4;
		node2.right = node5;
		node3.right = node6;

		return rootNode;
	}

}
