package com.algoexpert2.binarysearchtree;

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
		if (isDescendant(nodeTwo, nodeOne)) {
			return isDescendant(nodeThree, nodeTwo);
		}

		if (isDescendant(nodeTwo, nodeThree)) {
			return isDescendant(nodeOne, nodeTwo);
		}
		return false;
	}

	public boolean isDescendant(BST node, BST target) {
		if (node == null) {
			return false;
		}

		if (node == target) {
			return true;
		}

		return (node.value > target.value) ? isDescendant(node.left, target) : isDescendant(node.right, target);
	}

	public static void main(String[] args) {
		var root = new BST(5);
		root.left = new BST(2);
		root.right = new BST(7);
		root.left.left = new BST(1);
		root.left.right = new BST(4);
		root.right.left = new BST(6);
		root.right.right = new BST(8);
		root.left.left.left = new BST(0);
		root.left.right.left = new BST(3);

		var nodeOne = root;
		var nodeTwo = root.left;
		var nodeThree = root.left.right.left;
		boolean expected = true;
		boolean actual = new ValidateThreeNodes().validateThreeNodes(nodeOne, nodeTwo, nodeThree);
	}

}
