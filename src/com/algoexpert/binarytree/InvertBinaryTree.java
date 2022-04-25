package com.algoexpert.binarytree;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class InvertBinaryTree {

	public static void invertBinaryTree(BinaryTree tree) {
		if (tree == null) {
			return;
		}
		System.out.println(tree.value);
		swap(tree);
		invertBinaryTree(tree.left);
		invertBinaryTree(tree.right);
	}

	public static void invertBinaryTree2(BinaryTree tree) {
		Queue<BinaryTree> queue = new LinkedList<>();
		queue.add(tree);
		while (!queue.isEmpty()) {
			BinaryTree node = queue.remove();
			swap(tree);
			if (node.left != null) {
				queue.add(node.left);
			}
			if (node.right != null) {
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

	public static void main(String[] args) {
		TestBinaryTree tree = new TestBinaryTree(1);
		tree.insert(new int[] { 2, 3, 4, 5, 6, 7, 8, 9 }, 0);
		invertBinaryTree(tree);
	}

	static class InvertedBinaryTree {
		public int value;
		public InvertedBinaryTree left;
		public InvertedBinaryTree right;

		public InvertedBinaryTree(int value) {
			this.value = value;
		}

		public void insert(int[] values, int i) {
			if (i >= values.length) {
				return;
			}
			ArrayDeque<InvertedBinaryTree> queue = new ArrayDeque<InvertedBinaryTree>();
			queue.addLast(this);
			while (queue.size() > 0) {
				InvertedBinaryTree current = queue.pollFirst();
				if (current.right == null) {
					current.right = new InvertedBinaryTree(values[i]);
					break;
				}
				queue.addLast(current.right);
				if (current.left == null) {
					current.left = new InvertedBinaryTree(values[i]);
					break;
				}
				queue.addLast(current.left);
			}
			insert(values, i + 1);
		}
	}

	static class TestBinaryTree extends BinaryTree {
		public TestBinaryTree(int value) {
			super(value);
		}

		public void insert(int[] values, int i) {
			if (i >= values.length) {
				return;
			}
			ArrayDeque<BinaryTree> queue = new ArrayDeque<BinaryTree>();
			queue.addLast(this);
			while (queue.size() > 0) {
				BinaryTree current = queue.pollFirst();
				if (current.left == null) {
					current.left = new BinaryTree(values[i]);
					break;
				}
				queue.addLast(current.left);
				if (current.right == null) {
					current.right = new BinaryTree(values[i]);
					break;
				}
				queue.addLast(current.right);
			}
			insert(values, i + 1);
		}
	}
}
