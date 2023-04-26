package com.neetcode.binaryTree.view;

import java.util.ArrayList;
import java.util.List;

import com.neetcode.binaryTree.TreeNode;

public class LevelOrder {

	public List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> res = new ArrayList<>();
		dfs(root, res, 0);
		return res;
	}

	private void dfs(TreeNode root, List<List<Integer>> res, int level) {
		if (root == null) {
			return;
		}

		if (level >= res.size()) {
			res.add(new ArrayList<>());
		}

		res.get(level).add(root.val);
		dfs(root.left, res, level + 1);
		dfs(root.right, res, level + 1);
	}

}
