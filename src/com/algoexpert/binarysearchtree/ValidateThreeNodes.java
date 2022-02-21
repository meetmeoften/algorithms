package com.algoexpert.binarysearchtree;

public class ValidateThreeNodes {

	static class BST {
		public int value;
		public BST left = null;
		public BST right = null;

		public BST(int value) {
			this.value = value;
		}
	}

	public boolean validateThreeNodes(BST nodeOne, BST nodeTwo, BST nodeThree) {
		// Write your code here.
		if(isDescendant(nodeTwo, nodeOne)) {
			return isDescendant(nodeThree, nodeTwo);
		}

		if(isDescendant(nodeTwo, nodeThree)) {
			return isDescendant(nodeOne, nodeTwo);
		}
		return false;
	}

	public boolean isDescendant(BST node, BST target) {
		if(node == null) {
			return false;
		}

		if(node == target) {
			return true;
		}

		return (node.value > target.value)
				? isDescendant(node.left, target) : isDescendant(node.right, target);
	}

}
