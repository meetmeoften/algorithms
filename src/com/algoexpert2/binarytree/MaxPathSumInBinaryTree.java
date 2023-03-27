package com.algoexpert2.binarytree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxPathSumInBinaryTree {

	static class BinaryTree {
		public int value;
		public BinaryTree left;
		public BinaryTree right;

		public BinaryTree(int value) {
			this.value = value;
		}
	}

	public static int maxPathSum(BinaryTree tree) {
		// Write your code here.
		List<Integer> maxSumArray = findMaxSum(tree);
		return maxSumArray.get(1);
	}

	public static List<Integer> findMaxSum(BinaryTree tree) {
		if (tree == null) {
			return new ArrayList<Integer>(Arrays.asList(0, Integer.MIN_VALUE));
		}

		List<Integer> leftMaxSumArray = findMaxSum(tree.left);
		Integer leftMaxSumAsBranch = leftMaxSumArray.get(0);
		Integer leftMaxSumPath = leftMaxSumArray.get(1);

		List<Integer> rightMaxSumArray = findMaxSum(tree.right);
		Integer rightMaxSumAsBranch = rightMaxSumArray.get(0);
		Integer rightMaxSumPath = rightMaxSumArray.get(1);

		Integer maxChildSumAsBranch = Math.max(leftMaxSumAsBranch, rightMaxSumAsBranch);
		Integer maxSumAsBranch = Math.max(maxChildSumAsBranch + tree.value, tree.value);

		Integer maxSumAsRootNode = Math.max(leftMaxSumAsBranch + tree.value + rightMaxSumAsBranch, maxSumAsBranch);
		int maxPathSum = Math.max(leftMaxSumPath, Math.max(rightMaxSumPath, maxSumAsRootNode));

		return new ArrayList<Integer>(Arrays.asList(maxSumAsBranch, maxPathSum));

	}

	public static void main(String[] args) {
		TestBinaryTree test = new TestBinaryTree(1);
		test.insert(new int[] { 2, 3, 4, 5, 6, 7 }, 0);
		maxPathSum(test);
	}

	public static class TestBinaryTree extends BinaryTree {
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
