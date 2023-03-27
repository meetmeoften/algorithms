package com.algoexpert2.binarysearchtree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReConstructBST {

	static class BST {
		public int value;
		public BST left = null;
		public BST right = null;

		public BST(int value) {
			this.value = value;
		}
	}

	static class TreeInfo {
		public int rootIdx;

		public TreeInfo (int rootIdx) {
			this.rootIdx = rootIdx;
		}
	}


	public BST reconstructBst2(List<Integer> preOrderTraversalValues) {
		if(preOrderTraversalValues.size() == 0) {
			return null;
		}
		int currentValue = preOrderTraversalValues.get(0);
		int rightSubTreeRootIdx = preOrderTraversalValues.size();

		for(int i=1; i<preOrderTraversalValues.size(); i++) {
			int value = preOrderTraversalValues.get(i);
			if(value >= currentValue) {
				rightSubTreeRootIdx = i;
				break;
			}
		}

		BST leftSubtree = reconstructBst2(preOrderTraversalValues.subList(1, rightSubTreeRootIdx));
		BST rightSubtree = reconstructBst2(preOrderTraversalValues.subList(rightSubTreeRootIdx, preOrderTraversalValues.size()));

		BST bst = new BST(currentValue);
		bst.left = leftSubtree;
		bst.right = rightSubtree;

		return bst;

	}


	public BST reconstructBst(List<Integer> preOrderTraversalValues) {
		TreeInfo treeInfo = new TreeInfo(0);
		return reconstruct(Integer.MIN_VALUE, Integer.MAX_VALUE, preOrderTraversalValues, treeInfo);
	}

	public BST reconstruct(int lowerBound, int upperBound, List<Integer> preOrderTraversalValues, TreeInfo treeInfo) {
		if(treeInfo.rootIdx == preOrderTraversalValues.size()) {
			return null;
		}
		int rootValue = preOrderTraversalValues.get(treeInfo.rootIdx);
		if(rootValue < lowerBound || rootValue >= upperBound) {
			return null;
		}

		treeInfo.rootIdx += 1;
		BST leftSubTree = reconstruct(lowerBound, rootValue, preOrderTraversalValues, treeInfo );
		BST rightSubTree = reconstruct(rootValue, upperBound, preOrderTraversalValues, treeInfo);

		BST bst = new BST(rootValue);
		bst.left = leftSubTree;
		bst.right = rightSubTree;

		return bst;
	}


	public static void main(String[] args) {
		List<Integer> preOrderTraversalValues =
				new ArrayList<Integer>(Arrays.asList(10, 4, 2, 1, 5, 17, 19, 18));
		ReConstructBST.BST tree = new ReConstructBST.BST(10);
		tree.left = new ReConstructBST.BST(4);
		tree.left.left = new ReConstructBST.BST(2);
		tree.left.left.left = new ReConstructBST.BST(1);
		tree.left.right = new ReConstructBST.BST(3);
		tree.right = new ReConstructBST.BST(17);
		tree.right.right = new ReConstructBST.BST(19);
		tree.right.right.left = new ReConstructBST.BST(18);
		var actual = new ReConstructBST().reconstructBst2(preOrderTraversalValues);
	}

}
