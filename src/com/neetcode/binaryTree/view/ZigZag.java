package com.neetcode.binaryTree.view;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.neetcode.binaryTree.TreeNode;

public class ZigZag {

	public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> result = new ArrayList<>();

		if (root == null) {
			return result;
		}

		Queue<TreeNode> q = new LinkedList<>();
		q.offer(root);

		boolean leftToRight = true;

		while (!q.isEmpty()) {
			List<Integer> temp = new ArrayList<>();
			int level = q.size();

			for (int i = 0; i < level; i++) {
				TreeNode t = q.poll();

				if (!leftToRight) {
					temp.add(0, t.val);
				} else {
					temp.add(t.val);
				}

				if (t.left != null) {
					q.offer(t.left);
				}
				if (t.right != null) {
					q.offer(t.right);
				}
			}

			leftToRight = !leftToRight;
			result.add(temp);
		}

		return result;
	}

	public static void main(String[] args) {
		TreeNode node = TreeNode.createBinaryTree12345();
		zigzagLevelOrder(node);
	}

}
