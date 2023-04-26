package com.algoexpert2.binarytree.veryhard;

import java.util.ArrayList;
import java.util.List;

public class AllKindsOfNodeDepths {

	public static int allKindsOfNodeDepths(BinaryTree root) {
		// Write your code here.
		int sum = 0;
		List<BinaryTree> stack = new ArrayList<>();
		stack.add(root);
		while (stack.size() > 0) {
			BinaryTree node = stack.remove(stack.size() - 1);
			if (node == null) {
				continue;
			}
			sum = sum + nodeDepths(node, 0);
			stack.add(node.left);
			stack.add(node.right);
		}
		return sum;
	}

	public static int allKindsOfNodeDepths2(BinaryTree root) {
		if(root == null) {
			return 0;
		}
		return allKindsOfNodeDepths2(root.left) + allKindsOfNodeDepths2(root.right) + nodeDepths(root, 0);
	}

	public static int nodeDepths(BinaryTree node, int depth) {
		if (node == null) {
			return 0;
		}
		return depth + nodeDepths(node.left, depth + 1) + nodeDepths(node.right, depth + 1);
	}

	static class BinaryTree {
		int value;
		BinaryTree left;
		BinaryTree right;

		public BinaryTree(int value) {
			this.value = value;
			left = null;
			right = null;
		}
	}

	public static void main(String[] args) {
		var root = new BinaryTree(1);
		root.left = new BinaryTree(2);
		root.left.left = new BinaryTree(4);
		root.left.left.left = new BinaryTree(8);
		root.left.left.right = new BinaryTree(9);
		root.left.right = new BinaryTree(5);
		root.right = new BinaryTree(3);
		root.right.left = new BinaryTree(6);
		root.right.right = new BinaryTree(7);
		int actual = allKindsOfNodeDepths(root);
		// Utils.assertEquals(26, actual);
	}

}
