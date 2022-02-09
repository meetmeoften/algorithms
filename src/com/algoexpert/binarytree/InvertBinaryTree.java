package com.algoexpert.binarytree;

import java.util.LinkedList;
import java.util.Queue;

public class InvertBinaryTree {

	public static void invertBinaryTree(BinaryTree tree) {
		if(tree == null) {
			return;
		}
		swap(tree);
		invertBinaryTree(tree.left);
		invertBinaryTree(tree.right);
	}

	public static void invertBinaryTree2(BinaryTree tree) {
		Queue<BinaryTree> queue = new LinkedList<>();
		queue.add(tree);
		while(!queue.isEmpty()) {
			BinaryTree node = queue.remove();
			swap(tree);
			if(node.left != null) {
				queue.add(node.left);
			}
			if(node.right != null) {
				queue.add(node.right);
			}
		}
	}

	private static void swap(BinaryTree tree) {
		BinaryTree temp = tree.left;
		tree.left = tree.right;
		tree.right = temp;
	}

	static class BinaryTree {
		public int value;
		public BinaryTree left;
		public BinaryTree right;

		public BinaryTree(int value) {
			this.value = value;
		}
	}
}
