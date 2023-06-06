package com.algoexpert.binarytree.view;

import java.util.LinkedList;
import java.util.Queue;

import com.neetcode.binaryTree.TreeNode;

public class BottomLeftValue {

	public int findBottomLeftValue(TreeNode root) {
		// base case
		if (root == null) {
			return 0;
		}
		// creating queue for iteration over child nodes
		Queue<TreeNode> que = new LinkedList<>();
		que.offer(root);

		// creating a node to store the result
		TreeNode result = null;

		while (!que.isEmpty()) {
			int size = que.size();

			boolean flag = true;

			for (int i = 0; i < size; i++) {
				TreeNode tempNode = que.poll();
				// to make sure that the node should be first
				if (flag) {
					result = tempNode;
					flag = false;
				}

				// adding left and right child of parent node if they exist
				if (tempNode.left != null) {
					que.offer(tempNode.left);
				}
				if (tempNode.right != null) {
					que.offer(tempNode.right);
				}
			}
		}
		// return the result
		return result.val;
	}

}
