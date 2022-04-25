package com.algoexpert.binarytree;

import java.util.Stack;

public class InOrderUsingStack {

	public void inOrderIter(TreeNode root) {
		if (root == null) {
			return;
		}
		Stack<TreeNode> s = new Stack<TreeNode>();
		TreeNode currentNode = root;

		while (!s.empty() || currentNode != null) {

			if (currentNode != null) {
				s.push(currentNode);
				currentNode = currentNode.left;
			} else {
				TreeNode n = s.pop();
				System.out.printf("%d ", n.data);
				currentNode = n.right;
			}
		}
	}

	public static class TreeNode {
		int data;
		TreeNode left;
		TreeNode right;

		TreeNode(int data) {
			this.data = data;
		}
	}

}
