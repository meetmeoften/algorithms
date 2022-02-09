package com.algoexpert.binarytree;

import java.util.ArrayList;
import java.util.List;

public class NodeDepth {

	public static int nodeDepths(BinaryTree root) {
		return helper(root, 0);
	}

	private static int helper(BinaryTree root, int depth) {
		if(root == null) {
			return 0;
		}
		int left = helper(root.left, depth+1);
		int right = helper(root.right, depth+1);
		return depth + left + right;

	}


	public static int nodeDepths2(BinaryTree root) {
		// Write your code here.
		List<Level> stack = new ArrayList<>();
		stack.add(new Level(root, 0));
		int sum = 0;
		while(stack.size() > 0) {
			Level top = stack.remove(stack.size()-1);
			int depth = top.depth;
			BinaryTree node = top.root;

			if(node == null) {
				continue;
			}
			sum = sum + depth;
			stack.add(new Level(node.left, depth+1));
			stack.add(new Level(node.right, depth+1));
		}
		return sum;
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

	static class Level {
		public BinaryTree root;
		public int depth;

		public Level(BinaryTree root, int depth) {
			this.root = root;
			this.depth = depth;
		}

	}


	public static void main(String[] args) {
		var root = new NodeDepth.BinaryTree(1);
		root.left = new NodeDepth.BinaryTree(2);
		root.left.left = new NodeDepth.BinaryTree(4);
		root.left.left.left = new NodeDepth.BinaryTree(8);
		root.left.left.right = new NodeDepth.BinaryTree(9);
		root.left.right = new NodeDepth.BinaryTree(5);
		root.right = new NodeDepth.BinaryTree(3);
		root.right.left = new NodeDepth.BinaryTree(6);
		root.right.right = new NodeDepth.BinaryTree(7);
		int actual = NodeDepth.nodeDepths(root);
		System.out.println(actual);
	}

}
