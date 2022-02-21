package com.algoexpert.binarytree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class FindNodeDistanceK {

	static class BinaryTree {
		public int value;
		public BinaryTree left = null;
		public BinaryTree right = null;

		public BinaryTree(int value) {
			this.value = value;
		}
	}

	static class Pair<U, V> {
		public final U first;
		public final V second;

		private Pair(U first, V second) {
			this.first = first;
			this.second = second;
		}
	}

	public ArrayList<Integer> findNodesDistanceK(BinaryTree tree, int target, int k) {
		// Write your code here.
		HashMap<Integer, BinaryTree> nodeToParents = new HashMap<>();
		populateNodesToParents(tree, nodeToParents, null);
		BinaryTree targetNode = getNodeFromValue(target, tree, nodeToParents);
		return breadthFirstSearchForNodesDistanceK(targetNode, nodeToParents, k);
	}

	public BinaryTree getNodeFromValue(int value, BinaryTree tree, Map<Integer, BinaryTree> nodeToParents) {
		if(tree.value == value){
			return tree;
		}
		BinaryTree nodeParent = nodeToParents.get(value);
		if(nodeParent.left != null && nodeParent.left.value == value) {
			return nodeParent.left;
		}
		return nodeParent.right;
	}

	public void populateNodesToParents(BinaryTree node, Map<Integer, BinaryTree> nodeToParents, BinaryTree parent ) {
		if(node != null) {
			nodeToParents.put(node.value, parent);
			populateNodesToParents(node.left, nodeToParents, node);
			populateNodesToParents(node.right, nodeToParents, node);
		}
	}

	public ArrayList<Integer> breadthFirstSearchForNodesDistanceK(BinaryTree targetNode,
			Map<Integer, BinaryTree> nodeToParents, int k) {
		Queue<Pair<BinaryTree, Integer>> queue = new LinkedList<>();
		queue.offer(new Pair<BinaryTree, Integer>(targetNode, 0));

		HashSet<Integer> seen = new HashSet<>();
		seen.add(targetNode.value);

		while(queue.size() > 0) {

			var vals = queue.poll();
			BinaryTree currentNode = vals.first;
			int distanceFromTarget = vals.second;

			if(distanceFromTarget == k) {
				ArrayList<Integer> nodeDistanceK = new ArrayList<>();
				for(Pair<BinaryTree, Integer>  pair:  queue) {
					nodeDistanceK.add(pair.first.value);
				}
				nodeDistanceK.add(currentNode.value);
				return nodeDistanceK;
			}

			List<BinaryTree> connectedNodes = new ArrayList<>();
			connectedNodes.add(currentNode.left);
			connectedNodes.add(currentNode.right);
			connectedNodes.add(nodeToParents.get(currentNode.value));

			for(BinaryTree node: connectedNodes) {
				if(node == null) {
					continue;
				}
				if(seen.contains(node.value)) {
					continue;
				}

				seen.add(node.value);
				queue.add(new Pair<BinaryTree, Integer>(node, distanceFromTarget+1));
			}

		}
		return new ArrayList<Integer>();
	}


	public static void main(String[] args) {
		FindNodeDistanceK.BinaryTree root = new FindNodeDistanceK.BinaryTree(1);
		root.left = new FindNodeDistanceK.BinaryTree(2);
		root.right = new FindNodeDistanceK.BinaryTree(3);
		root.left.left = new FindNodeDistanceK.BinaryTree(4);
		root.left.right = new FindNodeDistanceK.BinaryTree(5);
		root.right.right = new FindNodeDistanceK.BinaryTree(6);
		root.right.right.left = new FindNodeDistanceK.BinaryTree(7);
		root.right.right.right = new FindNodeDistanceK.BinaryTree(8);
		int target = 3;
		int k = 2;
		var expected = new ArrayList<Integer>(Arrays.asList(2, 7, 8));
		var actual = new FindNodeDistanceK().findNodesDistanceK(root, target, k);
		Collections.sort(actual);
	}

}
