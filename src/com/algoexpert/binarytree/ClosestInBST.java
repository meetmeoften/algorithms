package com.algoexpert.binarytree;

public class ClosestInBST {

	static class BST {
		public int value;
		public BST left;
		public BST right;

		public BST(int value) {
			this.value = value;
		}
	}

	public static int findClosestValueInBst(BST tree, int target) {
		return findClosestValueInBst(tree, target, tree.value);

	}

	public static int findClosestValueInBst(BST tree, int target, int closest) {
		BST currentNode = tree;

		while (currentNode != null) {
			var value1 = Math.abs(target - closest);
			var value2 = Math.abs(target - currentNode.value);
			System.out.println("closest1 - " +  value1 + ": " + closest + " currentNode - "  + value2 + ":" +currentNode.value );
			if (value1 > value2) {
				closest = currentNode.value;
				System.out.println("Closest : " + closest);
			}

			if (target < currentNode.value) {
				currentNode = currentNode.left;
			} else if (target > currentNode.value) {
				currentNode = currentNode.right;
			} else {
				break;
			}
		}

		return closest;
	}

	public static void main(String[] args) {
		var root = new ClosestInBST.BST(10);
		root.left = new ClosestInBST.BST(5);
		root.left.left = new ClosestInBST.BST(2);
		root.left.left.left = new ClosestInBST.BST(1);
		root.left.right = new ClosestInBST.BST(5);
		root.right = new ClosestInBST.BST(15);
		root.right.left = new ClosestInBST.BST(13);
		root.right.left.right = new ClosestInBST.BST(14);
		root.right.right = new ClosestInBST.BST(22);

		var expected = 13;
		var actual = ClosestInBST.findClosestValueInBst(root, 12);
	}

}
