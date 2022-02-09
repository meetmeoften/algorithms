package com.test.binaryTree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class TreeTraversals {


	public static List<Integer> inorder(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		if (root == null) {
			return res;
		}
		TreeNode cur = root;
		while (cur != null) {
			if (cur.left == null) {
				res.add(cur.value);
				cur = cur.right;
			} else {
				TreeNode p = cur.left;
				while (p.right != null && p.right != cur) {
					p = p.right;
				}
				if (p.right == null) {
					p.right = cur;
					cur = cur.left;
				} else {
					p.right = null;
					res.add(cur.value);
					cur = cur.right;
				}
			}
		}
		return res;
	}

	public static List<Integer> preorder(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		if (root == null) {
			return res;
		}
		TreeNode cur = root;
		while (cur != null) {
			if (cur.left == null) {
				res.add(cur.value);
				cur = cur.right;
			}
			else {
				TreeNode p = cur.left;
				while (p.right != null && p.right != cur) {
					p = p.right;
				}
				if (p.right == null) {
					p.right = cur;
					res.add(cur.value);
					cur = cur.left;
				}
				else {
					p.right = null;
					cur = cur.right;
				}
			}
		}
		return res;
	}

	public static List<Integer> postorder(TreeNode root) {
		Deque<Integer> res = new ArrayDeque<>();
		if (root == null) {
			return new ArrayList<>(res);
		}
		TreeNode cur = root;
		while (cur != null) {
			if (cur.right != null) {
				TreeNode p = cur.right;
				while (p.left != null && p.left != cur) {
					p = p.left;
				}
				if (p.left == null) {
					p.left = cur;
					res.addFirst(cur.value);
					cur = cur.right;
				}
				else {
					p.left = null;
					cur = cur.left;
				}
			}
			else {
				res.addFirst(cur.value);
				cur = cur.left;
			}
		}
		return new ArrayList<>(res);
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);
		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(7);
		List<Integer> res = inorder(root);
		System.out.println("inorder: ");
		System.out.println(res);
		//		res = preorder(root);
		//		System.out.println("preorder: ");
		//		System.out.println(res);
		//		res = postorder(root);
		//		System.out.println("postorder: ");
		//		System.out.println(res);
	}

}
