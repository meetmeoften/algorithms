package com.test.binaryTree.bst;

public class KthLargestValue {

	public static int findKthLargestValueInBst(BST tree, int k) {
		// Write your code here.
		TreeInfo treeInfo = new TreeInfo(0, -1);
		reverseInOrderTraverse(tree, k, treeInfo);
		return treeInfo.latestVisitedNodeValue;
	}

	public static void reverseInOrderTraverse(BST bst, int k, TreeInfo treeInfo) {
		System.out.println(k + "  "+ treeInfo.numberOfNodesVisited + "  " + treeInfo.latestVisitedNodeValue);
		if(bst == null || treeInfo.numberOfNodesVisited >= k) {
			return;
		}
		//System.out.println(k + "  "+ treeInfo.numberOfNodesVisited);
		reverseInOrderTraverse(bst.right, k , treeInfo);
		if(treeInfo.numberOfNodesVisited < k) {
			treeInfo.numberOfNodesVisited +=1;
			treeInfo.latestVisitedNodeValue = bst.value;
			//			System.out.println(k + "  "+ treeInfo.numberOfNodesVisited + "  " + treeInfo.latestVisitedNodeValue);
			reverseInOrderTraverse(bst.left, k, treeInfo);
		}

	}

	public static void main(String[] args) {
		var bst = BST.createBinaryTree();
		System.out.println(findKthLargestValueInBst(bst, 3));
	}


	static class TreeInfo {
		public int numberOfNodesVisited;
		public int latestVisitedNodeValue;

		public TreeInfo(int numberOfNodesVisited, int latestVisitedNodeValue) {
			this.numberOfNodesVisited = numberOfNodesVisited;
			this.latestVisitedNodeValue = latestVisitedNodeValue;
		}
	}
}
