package com.algoexpert2.binarytree.veryhard;

import java.util.Stack;

public class CompareLeafTraversals {

	static class BinaryTree {
		public int value;
		public BinaryTree left = null;
		public BinaryTree right = null;

		public BinaryTree(int value) {
			this.value = value;
		}
	}

	public boolean compareLeafTraversal(BinaryTree tree1, BinaryTree tree2) {
		// Write your code here.
		Stack<BinaryTree> tree1Stack = new Stack<BinaryTree>();
		tree1Stack.push(tree1);
		Stack<BinaryTree> tree2Stack = new Stack<BinaryTree>();
		tree2Stack.push(tree2);

		while (tree1Stack.size() > 0 && tree2Stack.size() > 0) {
			BinaryTree tree1Leaf = getNextLeafNode(tree1Stack);
			BinaryTree tree2Leaf = getNextLeafNode(tree2Stack);

			if (tree1Leaf.value != tree2Leaf.value) {
				return false;
			}
		}

		return tree1Stack.size() == 0 && tree2Stack.size() == 0;
	}

	public BinaryTree getNextLeafNode(Stack<BinaryTree> traversalStack) {
		BinaryTree currentNode = traversalStack.pop();

		while (!isLeafNode(currentNode)) {
			if (currentNode.right != null) {
				traversalStack.push(currentNode.right);
			}
			if (currentNode.left != null) {
				traversalStack.push(currentNode.left);
			}
			currentNode = traversalStack.pop();
		}

		return currentNode;
	}

	public boolean isLeafNode(BinaryTree node) {
		return (node.left == null) && (node.right == null);
	}

	public static void main(String[] args) {
		BinaryTree tree1 = new BinaryTree(1);
		tree1.left = new BinaryTree(2);
		tree1.right = new BinaryTree(3);
		tree1.left.left = new BinaryTree(4);
		tree1.left.right = new BinaryTree(5);
		tree1.right.right = new BinaryTree(6);
		tree1.left.right.left = new BinaryTree(7);
		tree1.left.right.right = new BinaryTree(8);

		BinaryTree tree2 = new BinaryTree(1);
		tree2.left = new BinaryTree(2);
		tree2.right = new BinaryTree(3);
		tree2.left.left = new BinaryTree(4);
		tree2.left.right = new BinaryTree(7);
		tree2.right.right = new BinaryTree(5);
		tree2.right.right.left = new BinaryTree(8);
		tree2.right.right.right = new BinaryTree(6);

		var expected = true;
		var actual = new CompareLeafTraversals().compareLeafTraversal(tree1, tree2);
	}

}
