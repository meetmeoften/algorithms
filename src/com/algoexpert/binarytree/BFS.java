package com.algoexpert.binarytree;

import java.util.ArrayDeque;
import java.util.Deque;

public class BFS {


	static class BinaryTree {
		public int value;
		public BinaryTree left = null;
		public BinaryTree right = null;

		public BinaryTree(int value) {
			this.value = value;
		}
	}

	static class TreeInfo {
		public int diameter;
		public int height;

		public TreeInfo(int diameter, int height) {
			this.diameter = diameter;
			this.height = height;
		}

	}

	public void bfs(BinaryTree tree) {
		Deque<BinaryTree> queue = new ArrayDeque<>();
		queue.add(tree);

		while(!queue.isEmpty()) {
			BinaryTree node = queue.poll();

			if(node.left !=  null) {
				queue.add(node.left);
			}
			if(node.right != null) {
				queue.add(node.right);
			}
		}
	}
}
