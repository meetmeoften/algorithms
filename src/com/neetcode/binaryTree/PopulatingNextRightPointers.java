package com.neetcode.binaryTree;

public class PopulatingNextRightPointers {

	public static Node connect(Node root) {
		if (root == null) {
			return null;
		}
		Node curr = root;
		while (curr != null) {
			Node next = curr.left;
			if (next == null) {
				break;
			}
			while (curr != null) {
				curr.left.next = curr.right;
				if (curr.next != null) {
					curr.right.next = curr.next.left;
				}
				curr = curr.next;
			}
			curr = next;
		}
		return root;

	}

	static class Node {
		int val;
		Node left = null, right = null, next = null;

		Node(int key) {
			this.val = key;
		}
	}

	public static void main(String[] args) {

		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		root.left.left = new Node(5);
		root.left.right = new Node(6);

		root.right.left = new Node(6);
		root.right.right = new Node(7);

		connect(root);



	}

}
