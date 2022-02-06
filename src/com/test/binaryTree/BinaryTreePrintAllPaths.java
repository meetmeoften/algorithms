package com.test.binaryTree;

import java.util.ArrayList;

public class BinaryTreePrintAllPaths {

	public static void printAllPathsToLeaf(TreeNode node, int[] path, int len) {
		if ( node == null ) {
			return;
		}

		// storing data in array
		path[len] = node.value;
		len++;
		System.out.println(node.value);
		if(node.left == null && node.right == null) {
			// leaf node is reached
			printArray(path,len);
			return;
		}

		printAllPathsToLeaf(node.left, path, len);
		printAllPathsToLeaf(node.right, path, len);
	}


	public static String generate(char[] input) {
		String result = "";
		ArrayList<Integer> remain = new ArrayList<Integer>();
		for(int i = 0; i < input.length; i ++) {
			remain.add(input.length - i);
		}
		for(int i = 0; i < input.length; i ++) {
			result = result + " " + remain.get(input[i]);
			remain.remove(input[i]);
		}
		return result;
	}


	public static void main(String[] args)
	{
		char[] input = new char[] {6, 3, 0, 2, 2, 0};
		System.out.println(generate(input));
		//		// Creating a binary tree
		//		TreeNode rootNode=TreeNode.createBinaryTree();
		//		System.out.println("Printing all paths from root to leaf :");
		//		printAllPathsToLeaf(rootNode,new int[1000],0);
	}

	public static void  printArray(int[] path,int len)
	{
		for (int i = 0; i < len; i++) {
			System.out.print(" "+path[i]);
		}
		System.out.println();
	}

}
