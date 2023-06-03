package com.algoexpert2.binarytree;

import java.util.ArrayList;
import java.util.List;

public class SplitBinaryTree {

	static class BinaryTree {
		public int value;
		public BinaryTree left = null;
		public BinaryTree right = null;

		public BinaryTree(int value) {
			this.value = value;
		}
	}

	public int splitBinaryTree(BinaryTree tree) {
		List<Integer> sums = new ArrayList<>();
		int total = helper(sums, tree);
		if(total % 2 != 0) {
			return 0;
		}

		for(int sum: sums) {
			if(sum == total/2) {
				return sum;
			}
		}
		return 0;
	}

	private int helper(List<Integer> sums, BinaryTree tree) {
		if(tree == null) {
			return 0;
		}

		int left = helper(sums, tree.left);
		int right = helper(sums, tree.right);
		int sum = tree.value + left + right;
		sums.add(sum);
		return sum;
	}

	public static void main(String[] args) {
		BinaryTree tree = new BinaryTree(1);
		tree.left = new BinaryTree(3);
		tree.left.left = new BinaryTree(6);
		tree.left.left.left = new BinaryTree(2);
		tree.left.right = new BinaryTree(-5);
		tree.right = new BinaryTree(-2);
		tree.right.left = new BinaryTree(5);
		tree.right.right = new BinaryTree(2);
		int expected = 16;
		int actual = new SplitBinaryTree().splitBinaryTree(tree);
	}

}
