package com.algoexpert.binarytree;

import java.util.ArrayList;
import java.util.List;

public class PathSum2 {

	// 113. Path Sum 2

	public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> tempList = new ArrayList<>();
		helper(root, targetSum, tempList, result);
		return result;
	}

	public void helper(TreeNode node, int targetSum, List<Integer> tempList, List<List<Integer>> list) {
		if (node == null) {
			return;
		}

		tempList.add(node.val);
		if (node.left == null && node.right == null && targetSum - node.val == 0) {
			list.add(new ArrayList<>(tempList));
			return;
		}

		if (node.left != null) {
			helper(node.left, targetSum - node.val, tempList, list);
			tempList.remove(tempList.size() - 1);
		}

		if (node.right != null) {
			helper(node.right, targetSum - node.val, tempList, list);
			tempList.remove(tempList.size() - 1);
		}
	}

}
