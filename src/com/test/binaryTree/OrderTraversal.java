package com.test.binaryTree;

import java.util.Stack;

public class OrderTraversal {

	public static void inOrderIter(TreeNode root) {

		if(root == null) {
			return;
		}

		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode currentNode=root;

		while(!stack.empty() || currentNode!=null){

			if(currentNode!=null)
			{
				stack.push(currentNode);
				currentNode=currentNode.left;
			}
			else
			{
				TreeNode n=stack.pop();
				System.out.printf("%d ",n.value);
				currentNode=n.right;
			}
		}
	}

	public static void main(String[] args) {
		TreeNode rootNode=TreeNode.createBinaryTree();
		inOrderIter(rootNode);
	}



}
