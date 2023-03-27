package com.algoexpert2.binarytree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BranchSums {

	public static List<Integer> branchSums(BinaryTree root) {
		// Write your code here.
		List<Integer> sums = new ArrayList<>();
		calculate(root, 0, sums);
		return sums;
	}

	private static void calculate(BinaryTree root, int runningSum, List<Integer> sums) {
		if(root == null) {
			return;
		}
		int newRunningSum = runningSum + root.value;
		if(root.left == null && root.right == null) {
			sums.add(newRunningSum);
			return;
		}
		calculate(root.left, newRunningSum, sums);
		calculate(root.right, newRunningSum, sums);
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


		public static class TestBinaryTree extends BranchSums.BinaryTree {
			TestBinaryTree(int value) {
				super(value);
			}

			TestBinaryTree insert(List<Integer> values) {
				return insert(values, 0);
			}

			TestBinaryTree insert(List<Integer> values, int i) {
				if (i >= values.size()) {
					return null;
				}

				List<TestBinaryTree> queue = new ArrayList<TestBinaryTree>();
				queue.add(this);
				while (queue.size() > 0) {
					TestBinaryTree current = queue.get(0);
					queue.remove(0);
					if (current.left == null) {
						current.left = new TestBinaryTree(values.get(i));
						break;
					}
					queue.add((TestBinaryTree) current.left);
					if (current.right == null) {
						current.right = new TestBinaryTree(values.get(i));
						break;
					}
					queue.add((TestBinaryTree) current.right);
				}
				insert(values, i + 1);
				return this;
			}
		}

		public static void main(String[] args) {
			TestBinaryTree tree = new TestBinaryTree(1).insert(Arrays.asList(2, 3, 4, 5, 6, 7, 8, 9, 10));
			List<Integer> expected = new ArrayList<Integer>(Arrays.asList(15, 16, 18, 10, 11));
			branchSums(tree);
		}
	}

}
