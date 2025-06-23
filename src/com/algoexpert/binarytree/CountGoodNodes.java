package com.algoexpert.binarytree;

import java.util.LinkedList;
import java.util.Queue;

public class CountGoodNodes {

	public int goodNodes(TreeNode root) {
		return preorder(root, root.val);
	}

	private int preorder(TreeNode node, int v) {
		if (node == null) {
			return 0;
		}
		int max = Math.max(node.val, v); // maximum so far on the path.

		int result = 0;
		if (node.val >= v) {
			result = 1;
		}

		int left = preorder(node.left, max);
		int right = preorder(node.right, max);
		// recurse to children.

		return result + left + right;
	}

	static class Node {
		TreeNode node;
		int max;

		Node(TreeNode node, int m) {
			this.node = node;
			this.max = m;
		}
	}

	public int goodNodes2(TreeNode root) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(root, root.val));
		int ans = 0;
		while (!q.isEmpty()) {
			Node node = q.poll();
			TreeNode t = node.node;
			if (t.val == node.max) {
				ans++;
			}
			if (t.left != null) {
				q.add(new Node(t.left, Math.max(t.left.val, node.max)));
			}
			if (t.right != null) {
				q.add(new Node(t.right, Math.max(t.right.val, node.max)));
			}
		}
		return ans;
	}


	public boolean validate(TreeNode root, Integer min, Integer max) {
		if(root.val < min || root.val > max) {
			return false;
		}

		if(root.left != null && !validate(root.left, min, root.val )) {
			return false;
		}

		if(root.right != null && !validate(root.right, root.val, max )) {
			return false;
		}




		return true;
	}

}
