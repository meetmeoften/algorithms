package com.test.binaryTree;

public class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) {
		val = x;
	}

	public static TreeNode createBinaryTree()
	{

		TreeNode rootNode =new TreeNode(40);
		TreeNode node20=new TreeNode(20);
		TreeNode node10=new TreeNode(10);
		TreeNode node30=new TreeNode(30);
		TreeNode node60=new TreeNode(60);
		TreeNode node50=new TreeNode(50);
		TreeNode node70=new TreeNode(70);
		TreeNode node5 = new TreeNode(5);

		rootNode.left=node20;
		rootNode.right=node60;

		node20.left=node10;
		node20.right=node30;

		node60.left=node50;
		node60.right=node70;

		node10.left = node5;

		return rootNode;
	}
}