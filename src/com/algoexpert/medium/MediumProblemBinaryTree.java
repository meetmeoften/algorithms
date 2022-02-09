package com.algoexpert.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MediumProblemBinaryTree {

	// BranchSums
	public static List<Integer> branchSums(BinaryTree root) {
		List<Integer> sums = new ArrayList<>();
		branchSumsHelper(root, 0, sums);
		return sums;
	}


	private static void branchSumsHelper(BinaryTree tree, int runningSum, List<Integer> sums) {
		if(tree == null) {
			return;
		}
		int newRunningSum = runningSum + tree.value;
		if(tree.left == null && tree.right == null) {
			sums.add(newRunningSum);
			return;
		}
		branchSumsHelper(tree.left, newRunningSum, sums);
		branchSumsHelper(tree.right, newRunningSum, sums);

	}

	//Node Depths
	public static int nodeDepths(BinaryTree root) {
		// Write your code here.
		return nodeDepthHelper(root, 0);
	}

	private static int nodeDepthHelper(BinaryTree tree, int depth) {
		// TODO Auto-generated method stub
		if(tree == null) {
			return 0;
		}
		int leftDepth = nodeDepthHelper(tree.left, depth+1);
		int rightDepth = nodeDepthHelper(tree.right, depth+1);
		return depth + leftDepth + rightDepth;
	}

	//Invert Binary Tree
	public static void invertBinaryTree(BinaryTree tree) {
		if(tree == null) {
			return;
		}
		swapTree(tree);
		invertBinaryTree(tree.left);
		invertBinaryTree(tree.right);
	}

	private static void swapTree(BinaryTree tree) {
		// TODO Auto-generated method stub
		var temp = tree.left;
		tree.left = tree.right;
		tree.right = temp;

	}

	public static void invertBinaryTree2(BinaryTree tree) {
		Queue<BinaryTree> queue = new LinkedList<>();
		queue.add(tree);

		while(queue.size() > 0) {
			BinaryTree current = queue.remove();
			swapTree(current);
			if(current.left != null) {
				queue.add(current.left);
			}
			if(current.right != null) {
				queue.add(current.right);
			}
		}
	}

	//BinaryTree Diameter
	public int binaryTreeDiameter(BinaryTree tree) {
		// Write your code here.
		return getTreeInfoDiameter(tree).diameter;
	}


	private TreeInfoDiameter getTreeInfoDiameter(BinaryTree tree) {
		if(tree == null) {
			return new TreeInfoDiameter(0, 0);
		}

		TreeInfoDiameter leftTreeInfo = getTreeInfoDiameter(tree.left);
		TreeInfoDiameter rightTreeInfo = getTreeInfoDiameter(tree.right);

		int currentHeight = 1 + Math.max(leftTreeInfo.height, rightTreeInfo.height);

		int longestPathThroughout = leftTreeInfo.height + rightTreeInfo.height;
		int maxDiameterSoFar = Math.max(leftTreeInfo.diameter, rightTreeInfo.diameter);
		int currentDiameter = Math.max(longestPathThroughout, maxDiameterSoFar);

		return new TreeInfoDiameter(currentDiameter, currentHeight);
	}


	static class TreeInfoDiameter {
		public int diameter;
		public int height;

		public TreeInfoDiameter(int diameter, int height) {
			this.diameter = diameter;
			this.height = height;
		}
	}


	// Find Successor
	public BinaryTree findSuccessor(BinaryTree tree, BinaryTree node) {
		// Left Most Child;
		// Right Most Parent
		if(node.right != null) {
			return getLeftMostChild(node.right);
		}

		return getRightMostParent(node);
	}

	private BinaryTree getLeftMostChild(BinaryTree node) {
		BinaryTree currentNode = node;
		while(currentNode.left != null) {
			currentNode = currentNode.left;
		}
		return currentNode;
	}

	private BinaryTree getRightMostParent(BinaryTree node) {
		BinaryTree currentNode = node;
		while(currentNode.parent != null && currentNode.parent.right == currentNode) {
			currentNode = currentNode.parent;
		}
		return currentNode.parent;
	}

	// Height Balanced Tree
	public boolean heightBalancedBinaryTree(BinaryTree tree) {
		// Write your code here.
		TreeInfoHeight treeInfoHeight = getTreeInfoHeight(tree);
		return treeInfoHeight.isBalanced;
	}

	private TreeInfoHeight getTreeInfoHeight(BinaryTree tree) {
		// TODO Auto-generated method stub
		if(tree == null) {
			return new TreeInfoHeight(true, -1);
		}

		TreeInfoHeight leftInfo = getTreeInfoHeight(tree.left);
		TreeInfoHeight rightInfo = getTreeInfoHeight(tree.right);

		boolean isBalanced = leftInfo.isBalanced && rightInfo.isBalanced &&
				Math.abs(leftInfo.height - rightInfo.height) <=1;

		if(!isBalanced) {
			return new TreeInfoHeight(false, -1);
		}

		int currentHeight = 1 + Math.max(leftInfo.height, rightInfo.height);
		return new TreeInfoHeight(true, currentHeight);
	}


	static class TreeInfoHeight {
		public boolean isBalanced;
		public int height;

		public TreeInfoHeight(boolean isBalanced, int height){
			this.isBalanced = isBalanced;
			this.height = height;
		}

	}

	public static class BinaryTree {
		int value;
		BinaryTree left;
		BinaryTree right;
		BinaryTree parent = null;

		BinaryTree(int value) {
			this.value = value;
			this.left = null;
			this.right = null;
		}
	}


	// MinHeap Construction
	static class MinHeap {
		List<Integer> heap = new ArrayList<Integer>();

		public MinHeap(List<Integer> array) {
			heap = buildHeap(array);
		}

		public List<Integer> buildHeap(List<Integer> array) {
			int parentIndex = (array.size() -2)/2;
			for(int current = parentIndex; current >=0; current--) {
				siftDown(current, array.size()-1, array);
			}
			return array;
		}

		public void siftDown(int currentIdx, int endIdx, List<Integer> heap) {
			// Write your code here.
			int childOneIndex = currentIdx * 2 +1;
			while(childOneIndex <= endIdx) {
				int childTwoIndex = currentIdx * 2 + 2 <= endIdx ?  currentIdx * 2 + 2 : -1;
				int indexToSwap;

				if(childTwoIndex != -1 && heap.get(childOneIndex) > heap.get(childTwoIndex)) {
					indexToSwap = childTwoIndex;
				} else {
					indexToSwap = childOneIndex;
				}

				if(heap.get(currentIdx) > heap.get(indexToSwap)) {
					swap(currentIdx, indexToSwap, heap);
					currentIdx = indexToSwap;
					childOneIndex = currentIdx * 2 + 1;
				} else {
					return;
				}
			}
		}

		public void swap(int i, int j, List<Integer> heap){
			Integer temp = heap.get(j);
			heap.set(j, heap.get(i));
			heap.set(i, temp);
		}

		public void siftUp(int currentIdx, List<Integer> heap) {
			// Write your code here.
			int parentIndex = (currentIdx -1)/2;
			while(currentIdx > 0 && heap.get(parentIndex) > heap.get(currentIdx)) {
				swap(currentIdx, parentIndex, heap);
				currentIdx = parentIndex;
				parentIndex = (currentIdx -1)/2;
			}
		}

		public int peek() {
			// Write your code here.
			return heap.get(0);
		}

		public int remove() {
			// Write your code here.
			return -1;
		}

		public void insert(int value) {
			// Write your code here.
		}
	}

}
