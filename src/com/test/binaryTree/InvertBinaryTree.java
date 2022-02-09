package com.test.binaryTree;

public class InvertBinaryTree {

	public static void invertBinaryTree(TreeNode tree) {
		// Write your code here.
		if(tree == null){
			return;
		}
		//		System.out.println("Before " + tree.left.value + ", " + tree.right.value);
		swapLeftAndRight(tree);
		//		System.out.println("After " + tree.left.value + ", " + tree.right.value);
		invertBinaryTree(tree.left);
		invertBinaryTree(tree.right);

	}

	private static void swapLeftAndRight(TreeNode tree){
		TreeNode temp = tree.left;
		tree.left = tree.right;
		tree.right = temp;

	}

	public static void main(String[] args) {
		TreeNode rootNode=TreeNode.createBinaryTree();
		invertBinaryTree(rootNode);
		System.out.println(rootNode);
	}



}
