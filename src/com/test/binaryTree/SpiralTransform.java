package com.test.binaryTree;

import java.util.Stack;

public class SpiralTransform {

	public static void spiralOrZigzagLevelOrder(TreeNode root) {
		if(root==null) {
			return;
		}
		Stack<TreeNode> stack=new Stack<>();
		stack.push(root);

		boolean directionflag=false;
		while(!stack.isEmpty())
		{
			Stack<TreeNode> tempStack=new Stack<>();

			while(!stack.isEmpty())
			{
				TreeNode tempNode=stack.pop();
				System.out.printf("%d ",tempNode.val);
				if(!directionflag)
				{
					if(tempNode.left!=null) {
						tempStack.push(tempNode.left);
					}
					if(tempNode.right!=null) {
						tempStack.push(tempNode.right);
					}
				}else
				{
					if(tempNode.right!=null) {
						tempStack.push(tempNode.right);
					}
					if(tempNode.left!=null) {
						tempStack.push(tempNode.left);
					}
				}
			}
			// for changing direction
			directionflag=!directionflag;

			stack=tempStack;
		}

	}
	public static void main(String[] args)
	{
		SpiralTransform bi=new SpiralTransform();
		// Creating a binary tree
		TreeNode rootNode=TreeNode.createBinaryTree();
		System.out.println("Spiral/Zigzag traversal of binary tree :");
		spiralOrZigzagLevelOrder(rootNode);
	}

}
