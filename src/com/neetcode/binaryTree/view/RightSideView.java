package com.neetcode.binaryTree.view;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.neetcode.binaryTree.TreeNode;


public class RightSideView {

	public List<Integer> rightSideView(TreeNode root) {
		List<Integer> result = new ArrayList<>();
		if(root == null) {
			return result;
		}

		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);

		while(!q.isEmpty()) {
			int size = q.size();

			for(int i= 0; i< size; i++) {
				TreeNode n = q.poll();

				if(n.left != null) {
					q.add(n.left);
				}

				if(n.right != null) {
					q.add(n.right);
				}

				if(i == (size-1)) {
					result.add(n.val);
				}
			}
		}

		return result;

	}

	public List<Integer> leftSideView(TreeNode root) {
		List<Integer> result = new ArrayList<>();
		if(root == null) {
			return result;
		}

		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);

		while(!q.isEmpty()) {
			int size = q.size();

			for(int i= 0; i< size; i++) {
				TreeNode n = q.poll();

				if(n.left != null) {
					q.add(n.left);
				}

				if(n.right != null) {
					q.add(n.right);
				}

				if(i == 0) {
					result.add(n.val);
				}
			}
		}

		return result;

	}

}
