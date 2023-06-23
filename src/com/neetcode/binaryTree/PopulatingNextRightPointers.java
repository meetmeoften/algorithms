package com.neetcode.binaryTree;

import java.util.LinkedList;
import java.util.Queue;

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

	public static Node connectBFS(Node root) {

		if (root == null) {
			return null;
		}

		Queue<Node> q = new LinkedList<>();
		q.add(root);
		q.add(null);

		while (!q.isEmpty()) {

			Node curr = q.poll();

			if (curr == null && q.isEmpty()) {
				break;
			}

			if (curr == null) {
				q.add(null);
				continue;
			}

			curr.next = q.peek();

			if (curr.left != null) {
				q.offer(curr.left);
			}

			if (curr.right != null) {
				q.offer(curr.right);
			}
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
		root.left.left = new Node(4);
		root.left.right = new Node(5);

		root.right.left = new Node(6);
		root.right.right = new Node(7);

		// connect(root);
		connectBFS(root);

	}

}
