package com.algoexpert.binarysearchtree;

import com.algoexpert.binarytree.TreeNode;

public class InsertDeleteTrimBST {

	public static TreeNode deleteNode(TreeNode root, int key) {
		if (root == null) {
			return root;
		}

		if (key < root.val) {
			root.left = deleteNode(root.left, key);
		} else if (key > root.val) {
			root.right = deleteNode(root.right, key);
		} else {
			if (root.left == null) {
				return root.right;
			}
			if (root.right == null) {
				return root.left;
			}

			root.val = minvalue(root.right);// to find inorder succesor

			root.right = deleteNode(root.right, root.val);
		}
		return root;
	}

	public static int minvalue(TreeNode root) {
		int min = root.val;
		while (root.left != null) {
			min = root.left.val;
			root = root.left;
		}
		return min;
	}

	//https://leetcode.com/problems/trim-a-binary-search-tree/solutions/1950100/java-iterative-explanation-with-comments/

	public static TreeNode trimBST(TreeNode root, int low, int high) {
		if(root == null){
			return null;
		}
		System.out.println(root.val);

		if(root.val < low){
			TreeNode value = trimBST(root.right, low, high);
			return value;
		}
		if(root.val > high){
			TreeNode value =  trimBST(root.left, low, high);
			return value;
		}


		root.left = trimBST(root.left, low, high);
		root.right = trimBST(root.right, low, high);

		return root;
	}

	public static void main(String[] args) {
		// root = [5,3,6,2,4,null,7], key = 3
		TreeNode rootNode = createBinaryTree();
		// deleteNode(rootNode, 5);

		TreeNode modifiedRoot = trimBST(rootNode, 1, 7);
		System.out.println(modifiedRoot);

	}

	public static TreeNode createBinaryTree() {
		TreeNode rootNode = new TreeNode(8);
		TreeNode node2 = new TreeNode(5);
		TreeNode node3 = new TreeNode(9);
		TreeNode node4 = new TreeNode(2);
		TreeNode node5 = new TreeNode(6);
		TreeNode node6 = new TreeNode(10);
		TreeNode node7 = new TreeNode(4);

		rootNode.left = node2;
		rootNode.right = node3;

		node2.left = node4;
		node2.right = node5;

		node3.right = node6;

		node5.left = node7;

		return rootNode;
	}

}
