package com.algoexpert2.binarytree.veryhard;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class FlattenBinaryTree {


	public static BinaryTree flattenBinaryTree(BinaryTree root) {
		// Write your code here.
		List<BinaryTree> inOrderNodes = getNodesInOrder(root, new ArrayList<BinaryTree>());
		for(int i= 0; i< inOrderNodes.size()-1; i++) {
			BinaryTree leftNode = inOrderNodes.get(i);
			BinaryTree rightNode = inOrderNodes.get(i+1);
			leftNode.right = rightNode;
			rightNode.left = leftNode;
		}
		return inOrderNodes.get(0);
	}

	public static List<BinaryTree> getNodesInOrder(BinaryTree tree, List<BinaryTree> array) {
		if(tree != null) {
			getNodesInOrder(tree.left, array);
			array.add(tree);
			getNodesInOrder(tree.right, array);
		}
		return array;
	}

	// This is the class of the input root. Do not edit it.
	static class BinaryTree {
		int value;
		BinaryTree left = null;
		BinaryTree right = null;

		public BinaryTree(int value) {
			this.value = value;
		}
	}


	public static void main(String[] args) {
		BinaryTree root = new BinaryTree(1);
		insert(root, new int[] {2, 3, 4, 5, 6});
		root.left.right.left = new BinaryTree(7);
		root.left.right.right = new BinaryTree(8);
		BinaryTree leftMostNode = flattenBinaryTree(root);
		List<Integer> leftToRightToLeft = leftToRightToLeft(leftMostNode);
		List<Integer> expected =
				new ArrayList<Integer>(Arrays.asList(4, 2, 7, 5, 8, 1, 6, 3, 3, 6, 1, 8, 5, 7, 2, 4));
		//Utils.assertTrue(expected.equals(leftToRightToLeft));
	}

	public static void insert(BinaryTree root, int[] values) {
		insert(root, values, 0);
	}

	public static void insert(BinaryTree root, int[] values, int i) {
		if (i >= values.length) {
			return;
		}
		Deque<BinaryTree> queue = new ArrayDeque<BinaryTree>();
		queue.addLast(root);
		while (queue.size() > 0) {
			BinaryTree current = queue.pollFirst();
			if (current.left == null) {
				current.left = new BinaryTree(values[i]);
				break;
			}
			queue.addLast(current.left);
			if (current.right == null) {
				current.right = new BinaryTree(values[i]);
				break;
			}
			queue.addLast(current.right);
		}
		insert(root, values, i + 1);
	}

	public static List<Integer> leftToRightToLeft(BinaryTree leftMost) {
		List<Integer> nodes = new ArrayList<Integer>();
		BinaryTree current = leftMost;
		while (current.right != null) {
			nodes.add(current.value);
			current = current.right;
		}
		nodes.add(current.value);
		while (current != null) {
			nodes.add(current.value);
			current = current.left;
		}
		return nodes;
	}

}
