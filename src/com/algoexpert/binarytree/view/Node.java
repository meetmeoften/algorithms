package com.algoexpert.binarytree.view;

public class Node {

	int data;
	int hd;
	Node left;
	Node right;

	public Node(int data, Node left, Node right) {
		this.data = data;
		this.left = left;
		this.right = right;

	}

	public Node(int data) {
		this.data = data;
	}

	public static Node createBinaryTree12345() {
		Node rootNode = new Node(1);
		Node node2 = new Node(2);
		Node node3 = new Node(3);

		Node node4 = new Node(4);
		Node node5 = new Node(5);
		Node node6 = new Node(6);

		rootNode.left = node2;
		rootNode.right = node3;

		node2.left = node4;
		node2.right = node5;
		node3.right = node6;

		return rootNode;
	}


	/**
	 * 			 1
	 * 		 2		   3
	 *    4      5   -    6
	 *
	 *
	 */

}
