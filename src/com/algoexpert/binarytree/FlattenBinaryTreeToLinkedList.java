package com.algoexpert.binarytree;

import java.util.Stack;

public class FlattenBinaryTreeToLinkedList {

	public void flatten(TreeNode root) {
		if (root == null) {
			return;
		}

		Stack<TreeNode> st = new Stack<>();
		st.push(root);

		while (!st.isEmpty()) {
			TreeNode curr = st.pop();

			if (curr.right != null) {
				st.push(curr.right);
			}

			if (curr.left != null) {
				st.push(curr.left);
			}

			if (!st.isEmpty()) {
				curr.right = st.peek();
			}

			curr.left = null;
		}

		return;
	}

	public static void main(String[] args) {
		TreeNode rootNode = TreeNode.createBinaryTree123();
		FlattenBinaryTreeToLinkedList g = new FlattenBinaryTreeToLinkedList();
		g.flatten(rootNode);
	}

}
