package com.algoexpert2.binarytree.veryhard;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class RightSiblingTree {


	public static BinaryTree rightSiblingTree(BinaryTree root) {
		mutuate(root, null, false);
		return root;
	}

	private static void mutuate(BinaryTree node, BinaryTree parent, boolean isLeftChild) {
		if(node == null) {
			return;
		}

		var left = node.left;
		var right = node.right;
		mutuate(left, node, true);

		if(parent == null) {
			node.right = null;
		} else if(isLeftChild) {
			node.right = parent.right;
		} else {
			if(parent.right == null) {
				node.right = null;
			} else {
				node.right = parent.right.left;
			}
		}
		mutuate(right, node, false);
	}


	static class BinaryTree {
		int value;
		BinaryTree left = null;
		BinaryTree right = null;

		public BinaryTree(int value) {
			this.value = value;
		}
	}

	public static void main(String[] args) {
		BinaryTree root = new BinaryTree(1);
		insert(root, new int[] {2, 3, 4, 5, 6, 7, 8, 9});
		root.left.right.right = new BinaryTree(10);
		root.right.left.left = new BinaryTree(11);
		root.right.right.left = new BinaryTree(12);
		root.right.right.right = new BinaryTree(13);
		root.right.left.left.left = new BinaryTree(14);
		BinaryTree mutatedRoot = rightSiblingTree(root);
		List<Integer> actual = getDfsOrder(mutatedRoot);
		var expected = Arrays.asList(1, 2, 4, 8, 9, 5, 6, 11, 14, 7, 12, 13, 3, 6, 11, 14, 7, 12, 13);
		System.out.println(actual);
	}

	public static void insert(BinaryTree root, int[] values) {
		insert(root, values, 0);
	}

	public static void insert(BinaryTree root, int[] values, int i) {
		if (i >= values.length) {
			return;
		}
		Deque<BinaryTree> queue = new ArrayDeque<BinaryTree>();
		queue.addLast(root);
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
		insert(root, values, i + 1);
	}

	public static List<Integer> getDfsOrder(BinaryTree tree) {
		List<Integer> values = new ArrayList<Integer>();
		values.add(tree.value);
		if (tree.left != null) {
			values.addAll(getDfsOrder(tree.left));
		}
		if (tree.right != null) {
			values.addAll(getDfsOrder(tree.right));
		}
		return values;
	}
}
