package com.algoexpert.binarytree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class MaximumWidthOfBinaryTree {

	public static int widthOfBinaryTree(TreeNode root) {
		if (root == null) {
			return 0;
		}

		Map<Integer, Integer> map = new HashMap<>();
		Queue<TreeNode> q = new LinkedList<>();
		q.offer(root);
		map.put(root.val, 1);

		int maxWidth = Integer.MIN_VALUE;

		while (!q.isEmpty()) {
			int start = 0, end = 0;
			int size = q.size();

			for (int i = 0; i < size; i++) {
				TreeNode t = q.poll();

				if (i == 0) {
					start = map.get(t.val);
				}
				if (i == size - 1) {
					end = map.get(t.val);
				}

				if (t.left != null) {
					q.offer(t.left);
					map.put(t.left.val, map.get(t.val) * 2);
				}

				if (t.right != null) {
					q.offer(t.right);
					map.put(t.right.val, map.get(t.val) * 2 + 1);
				}
			}

			maxWidth = Math.max(maxWidth, end - start + 1);
		}

		return maxWidth;
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

		widthOfBinaryTree(root);
	}

}
