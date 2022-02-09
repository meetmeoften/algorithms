package com.leetcodepatterns.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CloneGraph {

	public Node cloneGraph(Node node) {
		Map<Node, Node> m = new HashMap<>();
		return helper(node, m);
	}

	private Node helper(Node node, Map<Node, Node> m) {
		if (node == null) {
			return null;
		}

		if (m.containsKey(node)) {
			return m.get(node);
		}

		Node copy = new Node(node.val);
		m.put(node, copy);

		for (Node neighbour : node.neighbors) {
			Node neighbourCopy = helper(neighbour, m);
			m.get(node).neighbors.add(neighbourCopy);
		}

		return m.get(node);
	}

	static class Node {
		public int val;
		public List<Node> neighbors;
		public Node() {
			val = 0;
			neighbors = new ArrayList<Node>();
		}
		public Node(int _val) {
			val = _val;
			neighbors = new ArrayList<Node>();
		}
		public Node(int _val, ArrayList<Node> _neighbors) {
			val = _val;
			neighbors = _neighbors;
		}
	}

}
