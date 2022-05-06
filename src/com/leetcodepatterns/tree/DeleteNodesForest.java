package com.leetcodepatterns.tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class DeleteNodesForest {

	public static List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
		if (root == null) {
			return new ArrayList<>();
		}

		Set<TreeNode> resSet = new HashSet<>();
		resSet.add(root);

		if (to_delete.length == 0) {
			return new ArrayList<>(resSet);
		}

		Set<Integer> toDelete = new HashSet<>();
		for (int val : to_delete) {
			toDelete.add(val);
		}

		Queue<TreeNode> q = new LinkedList<>();
		q.offer(root);

		while (!q.isEmpty()) {
			TreeNode node = q.poll();

			if (toDelete.contains(node.val)) {
				resSet.remove(node);
				if (node.left != null) {
					resSet.add(node.left);
				}
				if (node.right != null) {
					resSet.add(node.right);
				}
			}

			if (node.left != null) {
				q.offer(node.left);
				if (toDelete.contains(node.left.val)) {
					node.left = null;
				}
			}

			if (node.right != null) {
				q.offer(node.right);
				if (toDelete.contains(node.right.val)) {
					node.right = null;
				}
			}
		}
		return new ArrayList<>(resSet);
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

		List<TreeNode> result = delNodes(root, new int[] {3, 5});
		result.stream().forEach(e -> System.out.println(e.val));
	}

}
