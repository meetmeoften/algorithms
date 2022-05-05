package com.algoexpert.binarytree;

public class NextRightPointers {

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

	public static Node connect2(Node root) {
		Node head = root, dummy = new Node(-1), prev = dummy;

		while (root != null) {
			if (root.left != null) {
				prev.next = root.left;
				prev = prev.next;
			}

			if (root.right != null) {
				prev.next = root.right;
				prev = prev.next;
			}

			root = root.next;

			if (root == null) {
				root = dummy.next;
				prev = dummy;
				dummy.next = null;
			}
		}

		return head;
	}

	static class Node {
		int val;
		Node left = null, right = null, next = null;

		Node(int key) {
			this.val = key;
		}
	}

	public static void main(String[] args) {
		/* Construct the following tree
        1
      /   \
     /     \
    2       3
          /   \
         /     \
        5       6
      /   \
     /     \
    7       8
          /   \
         /     \
        9      10
		 */

		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		root.right.left = new Node(5);
		root.right.right = new Node(6);
		root.right.left.left = new Node(7);
		root.right.left.right = new Node(8);
		root.right.left.right.left = new Node(9);
		root.right.left.right.right = new Node(10);

		connect2(root);
	}

}
