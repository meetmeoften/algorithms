package com.algoexpert2.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DepthFirstSearch {

	static class Node {
		String name;
		List<Node> children = new ArrayList<Node>();
		boolean visited = false;

		public Node(String name) {
			this.name = name;
		}

		public List<String> depthFirstSearch(List<String> array) {
			array.add(this.name);
			for (int i = 0; i < children.size(); i++) {
				children.get(i).depthFirstSearch(array);
			}
			return array;
		}

		public List<String> depthFirstSearch2(List<String> array) {
			// Write your code here
			Stack<Node> stack = new Stack<Node>();
			stack.add(this);
			while (!stack.isEmpty()) {
				Node node = stack.pop();

				if (!node.visited) {
					array.add(node.name);
					node.visited = true;
				}

				for (int i = node.children.size()-1; i >= 0; i--) {
					Node n = node.children.get(i);
					if (n != null && !n.visited) {
						System.out.println(n.name);
						stack.add(n);
					}
				}
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
		String[] expected = { "A", "B", "E", "F", "I", "J", "C", "D", "G", "K", "H" };
		List<String> inputArray = new ArrayList<String>();
		// graph.depthFirstSearch(inputArray);
		graph.depthFirstSearch2(inputArray);

	}

}
