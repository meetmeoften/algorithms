package com.algoexpert2.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearch {

	static class Node {
		String name;
		List<Node> children = new ArrayList<Node>();
		boolean visited = false;

		public Node(String name) {
			this.name = name;
		}

		public List<String> breadthFirstSearch(List<String> array) {
			// Write your code here.
			Queue<Node> queue = new LinkedList<Node>();
			queue.add(this);
			while (!queue.isEmpty()) {
				Node node = queue.poll();
				array.add(node.name);
				for (int i = 0; i < node.children.size(); i++) {
					queue.add(node.children.get(i));
				}
				// queue.addAll(node.children);
			}
			return array;
		}

		public Node addChild(String name) {
			Node child = new Node(name);
			children.add(child);
			return this;
		}
	}

	public static void main(String[] args) {
		Node graph = new Node("A");
		graph.addChild("B").addChild("C").addChild("D");
		graph.children.get(0).addChild("E").addChild("F");
		graph.children.get(2).addChild("G").addChild("H");
		graph.children.get(0).children.get(1).addChild("I").addChild("J");
		graph.children.get(2).children.get(0).addChild("K");
		String[] expected = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K" };
		List<String> inputArray = new ArrayList<String>();
		// graph.depthFirstSearch(inputArray);
		graph.breadthFirstSearch(inputArray);

	}

}
