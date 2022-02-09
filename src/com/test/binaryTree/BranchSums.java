package com.test.binaryTree;

import java.util.ArrayList;
import java.util.List;

public class BranchSums {

	public static List<Integer> branchSums(TreeNode root) {
		// Write your code here.
		List<Integer> sums = new ArrayList<>();
		calculate(root, 0, sums);
		return sums;
	}

	public static void calculate(TreeNode node, int runningSum, List<Integer> sums) {
		if(node == null) {
			return;
		}
		int newRunningSum = runningSum + node.value;
		if(node.left == null && node.right == null) {
			sums.add(newRunningSum);
			return;
		}
		calculate(node.left, newRunningSum, sums);
		calculate(node.right, newRunningSum, sums);
	}

	public static void main(String[] args) {
		TreeNode rootNode=TreeNode.createBinaryTree();
		branchSums(rootNode);
	}



}
