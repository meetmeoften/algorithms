package com.test.binaryTree;

public class BinaryTreeGetLevelNode {


	// Recursive Solution
	//To get level of node in a binary tree
	public static int getLevelOfNode(TreeNode root,int key,int level)
	{
		if(root==null) {
			return 0;
		}
		if(root.val==key) {
			return level;
		}

		int result=getLevelOfNode(root.left,key,level+1) ;
		if(result!=0)
		{
			// If found in left subtree , return
			return result;
		}
		result= getLevelOfNode(root.right,key,level+1);

		return result;
	}


	public static void main(String[] args)
	{
		// Creating a binary tree
		TreeNode rootNode=TreeNode.createBinaryTree();
		System.out.println("Node data: 70,Level :"+getLevelOfNode(rootNode, 30, 1));
		System.out.println("Node data: 100,Level :"+getLevelOfNode(rootNode, 100, 1));
		System.out.println("Node data: 60,Level :"+getLevelOfNode(rootNode, 60, 1));
		System.out.println("Node data: 40,Level :"+getLevelOfNode(rootNode, 40, 1));
	}
}