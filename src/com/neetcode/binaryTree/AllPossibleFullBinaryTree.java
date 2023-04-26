package com.neetcode.binaryTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllPossibleFullBinaryTree {

	static Map<Integer, List<TreeNode>> map = new HashMap<>();

	public static List<TreeNode> allPossibleFBT(int n) {

		if (map.containsKey(n)) {
			return map.get(n);
		}

		List<TreeNode> res = new ArrayList<>();

		if (n % 2 == 0) {
			return res; // if n is even, no complete B-tree possible
		}

		if (n == 1) { // if n is 1, only 1 complete B-tree is possible (0 or 2 children)
			res.add(new TreeNode(0));
			return res;
		}

		for (int i = 1; i < n; i += 2) {

			List<TreeNode> left = allPossibleFBT(i); // recursive call for left subtree children

			List<TreeNode> right = allPossibleFBT(n - i - 1); // recursive call for right subtree children

			for (TreeNode l : left) { // for - each loop of java is used here
				for (TreeNode r : right) {
					TreeNode root = new TreeNode(0); // iterating values, making trees
					root.left = l;
					root.right = r;
					res.add(root); // filling up the results in list
				}
			}
		}

		map.put(n,  res);
		return res;
	}

	public static void main(String[] args) {
		allPossibleFBT(7);
	}
}
