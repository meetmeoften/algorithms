package com.test.binaryTree.bst;

public class BST {

	public int value;
	public BST left;
	public BST right;

	public BST(int value) {
		this.value = value;
	}

	public static BST createBinaryTree() {

		var root = new BST(10);
		root.left = new BST(5);
		root.left.left = new BST(2);
		root.left.left.left = new BST(1);
		root.left.right = new BST(5);
		root.right = new BST(15);
		root.right.left = new BST(13);
		root.right.left.right = new BST(14);
		root.right.right = new BST(22);

		return root;

	}

}
