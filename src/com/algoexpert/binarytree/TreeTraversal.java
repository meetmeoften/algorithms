package com.algoexpert.binarytree;

import java.util.Stack;

public class TreeTraversal {

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
				System.out.printf("%d ", n.val);
				currentNode = n.right;
			}
		}
	}

	public void preorderIter(TreeNode root) {
		if (root == null) {
			return;
		}
		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(root);

		while (!stack.empty()) {
			TreeNode n = stack.pop();
			System.out.printf("%d ", n.val);
			if (n.right != null) {
				stack.push(n.right);
			}
			if (n.left != null) {
				stack.push(n.left);
			}
		}
	}

	public void postorderIter(TreeNode root) {
		if (root == null) {
			return;
		}

		Stack<TreeNode> s = new Stack<TreeNode>();
		TreeNode current = root;

		while (true) {
			if (current != null) {
				if (current.right != null) {
					s.push(current.right);
				}
				s.push(current);
				current = current.left;
				continue;
			}

			if (s.isEmpty()) {
				return;
			}
			current = s.pop();
			if (current.right != null && !s.isEmpty() && current.right == s.peek()) {
				s.pop();
				s.push(current);
				current = current.right;
			} else {
				System.out.print(current.val + " ");
				current = null;
			}
		}
	}

}
