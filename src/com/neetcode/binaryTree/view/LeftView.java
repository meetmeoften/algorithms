package com.neetcode.binaryTree.view;

import java.util.ArrayList;
import java.util.List;

import com.neetcode.binaryTree.TreeNode;

public class LeftView {

	public static List<Integer> lightSideView(TreeNode root) {
		List<Integer> result = new ArrayList<Integer>();
		leftView(root, result, 0);
		return result;
	}

	public static void leftView(TreeNode curr, List<Integer> result, int currDepth) {
		if (curr == null) {
			return;
		}
		if (currDepth == result.size()) {
			result.add(curr.val);
		}

		leftView(curr.left, result, currDepth + 1);
		leftView(curr.right, result, currDepth + 1);

	}

	public static void main(String[] args) {
		lightSideView(TreeNode.createBinaryTree12345());

	}

}
