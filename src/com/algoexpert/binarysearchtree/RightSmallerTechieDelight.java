package com.algoexpert.binarysearchtree;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class RightSmallerTechieDelight {

	static class Node {
		int key;
		Node left = null, right = null;

		Node(int key) {
			this.key = key;
		}
	}

	// Function to insert a specified key into the binary search tree
	// rooted at the specified node and find its successor
	public static Node insert(Node root, int key, AtomicInteger successor) {
		// base case: empty tree
		if (root == null) {
			return new Node(key);
		}

		// if the key is less than root
		if (key < root.key) {
			// set successor as the current node
			successor.set(root.key);

			// traverse the left subtree
			root.left = insert(root.left, key, successor);
		}

		// if the key is more than root
		else if (key > root.key) {
			// traverse the right subtree
			root.right = insert(root.right, key, successor);
		}

		return root;
	}

	// Replace each element of the specified array with the
	// least greater element on its right
	public static void replace(int[] nums) {
		// root of the binary search tree
		Node root = null;

		// traverse the array from the end
		//		for (int i = nums.length - 1; i >= 0; i--) {
		//			// insert the current element into the binary search tree
		//			// and replace it with its inorder successor
		//			AtomicInteger successor = new AtomicInteger(-1);
		//			root = insert(root, nums[i], successor);
		//			nums[i] = successor.get();
		//		}
		//
		for (int i = 0; i< nums.length; i++) {
			// insert the current element into the binary search tree
			// and replace it with its inorder successor
			AtomicInteger successor = new AtomicInteger(-1);
			root = insert(root, nums[i], successor);
			nums[i] = successor.get();
		}


		// print the resultant array
		System.out.println(Arrays.toString(nums));
	}

	public static void main(String[] args) {
		int[] nums = { 10, 100, 93, 32, 35, 65, 80, 90, 94, 6 };
		replace(nums);
	}

}
