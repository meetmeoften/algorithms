package com.algoexpert.binarytree;

public class PathSum3 {

	// 113. Path Sum 2

	int counter = 0;

	public int pathSum(TreeNode root, int sum) {

		if (root == null) {
			return 0;
		}

		pathSumHelper(root, sum, 0);
		pathSum(root.left, sum);
		pathSum(root.right, sum);

		return counter;

	}

	void pathSumHelper(TreeNode root, long sum, long currentSum) {
		if (root == null) {
			return;
		}
		currentSum += root.val;
		if (currentSum == sum) {
			counter++;
		}
		pathSumHelper(root.left, sum, currentSum);
		pathSumHelper(root.right, sum, currentSum);
	}

}
