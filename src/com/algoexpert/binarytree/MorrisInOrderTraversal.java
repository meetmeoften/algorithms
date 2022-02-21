package com.algoexpert.binarytree;

public class MorrisInOrderTraversal {

	public static void iterativeInOrderTraversal(BinaryTree root) {
		// Write your code here.
		BinaryTree cur = root;
		while (cur != null) {
			if (cur.left == null) {
				//callback.apply(cur);
				System.out.println(cur.value);
				cur = cur.right;
			} else {
				BinaryTree p = cur.left;
				while (p.right != null && p.right != cur) {
					p = p.right;
				}
				if (p.right == null) {
					p.right = cur;
					cur = cur.left;
				} else {
					p.right = null;
					//callback.apply(cur);
					System.out.println(cur.value);
					cur = cur.right;
				}
			}
		}
	}

	static class BinaryTree {
		public int value;
		public BinaryTree left;
		public BinaryTree right;
		public BinaryTree parent;

		public BinaryTree(int value) {
			this.value = value;
		}

		public BinaryTree(int value, BinaryTree parent) {
			this.value = value;
			this.parent = parent;
		}
	}


	public static void main(String[] args) {
		var root = new MorrisInOrderTraversal.BinaryTree(1);
		root.left = new MorrisInOrderTraversal.BinaryTree(2, root);
		root.left.left = new MorrisInOrderTraversal.BinaryTree(4, root.left);
		root.left.left.right = new MorrisInOrderTraversal.BinaryTree(9, root.left.left);
		root.right = new MorrisInOrderTraversal.BinaryTree(3, root);
		root.right.left = new MorrisInOrderTraversal.BinaryTree(6, root.right);
		root.right.right = new MorrisInOrderTraversal.BinaryTree(7, root.right);

		MorrisInOrderTraversal.iterativeInOrderTraversal(root);
	}
}
