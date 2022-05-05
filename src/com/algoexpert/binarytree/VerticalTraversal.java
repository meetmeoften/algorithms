package com.algoexpert.binarytree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class VerticalTraversal {

	static class Pair<U, V> {
		public final U first; // first field of a pair
		public final V second; // second field of a pair

		// Constructs a new Pair with specified values
		private Pair(U first, V second) {
			this.first = first;
			this.second = second;
		}

		// Factory method for creating a Typed Pair immutable instance
		public static <U, V> Pair<U, V> of(U a, V b) {
			// calls private constructor
			return new Pair<>(a, b);
		}
	}

	public static List<List<Integer>> verticalOrder(TreeNode root) {
		if (root == null) {
			return Collections.emptyList();
		}

		List<List<Integer>> result = new ArrayList<>();
		Map<Integer, List<Integer>> map = new HashMap<>();
		Queue<TreeNode> q = new LinkedList<>();
		Queue<Integer> cols = new LinkedList<>();

		Queue<Pair<TreeNode, Integer>> qq = new LinkedList<>();

		q.offer(root);
		cols.offer(0);

		int min = 0, max = 0;

		while (!q.isEmpty()) {
			TreeNode curr = q.poll();
			int colNum = cols.poll();

			if (!map.containsKey(colNum)) {
				map.put(colNum, new ArrayList<>());
			}

			map.get(colNum).add(curr.val);

			if (curr.left != null) {
				q.offer(curr.left);
				cols.offer(colNum - 1);
				min = Math.min(min, colNum - 1);
			}

			if (curr.right != null) {
				q.offer(curr.right);
				cols.offer(colNum + 1);
				max = Math.max(max, colNum + 1);
			}
		}

		for (int i = min; i <= max; i++) {
			result.add(map.get(i));
		}

		return result;
	}

	static class TreeNode {
		int val;
		TreeNode left = null, right = null;

		TreeNode(int key) {
			this.val = key;
		}
	}

	public static void main(String[] args) {
		/* Construct the following tree
        1
      /   \
     /     \
    2       3
          /   \
         /     \
        5       6
      /   \
     /     \
    7       8
          /   \
         /     \
        9      10
		 */

		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.right.left = new TreeNode(5);
		root.right.right = new TreeNode(6);
		root.right.left.left = new TreeNode(7);
		root.right.left.right = new TreeNode(8);
		root.right.left.right.left = new TreeNode(9);
		root.right.left.right.right = new TreeNode(10);

		verticalOrder(root);
	}

}
