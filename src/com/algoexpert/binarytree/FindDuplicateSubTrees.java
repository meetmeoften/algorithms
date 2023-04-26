package com.algoexpert.binarytree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FindDuplicateSubTrees {

	public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
		List<TreeNode> res = new LinkedList<>();
		Map<String, Integer> map = new HashMap<>();
		preOrder(root, map, res);
		return res;
	}

	public String preOrder(TreeNode cur, Map<String, Integer> map, List<TreeNode> res) {
		if (cur == null) {
			return "#";
		}
		String serial = cur.val + ",";
		serial += preOrder(cur.left, map, res) + ",";
		serial += preOrder(cur.right, map, res);

		map.put(serial, map.getOrDefault(serial, 0) + 1);
		if (map.get(serial) == 2) {
			res.add(cur);
		}
		return serial;

	}

}
