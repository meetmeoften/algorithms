package com.neetcode.graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

public class PathWithMaximumProbability {

	public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
		List<List<Node>> adj = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			adj.add(new ArrayList<>());
		}
		for (int i = 0; i < edges.length; i++) {
			int u = edges[i][0];
			int v = edges[i][1];
			double wt = succProb[i];

			adj.get(u).add(new Node(v, wt));
			adj.get(v).add(new Node(u, wt));
		}

		PriorityQueue<Node> pq = new PriorityQueue<>(n, new Node());
		pq.add(new Node(start, 1));

		HashSet<Integer> pst = new HashSet<>();

		while (!pq.isEmpty()) {
			Node node = pq.poll();
			int num = node.getV();
			double prob = node.getWeight();

			pst.add(num);

			if (num == end) {
				return prob;
			}

			if (adj.get(num) != null) {
				for (Node it : adj.get(num)) {
					if (!pst.contains(it.getV())) {
						pq.add(new Node(it.getV(), prob * it.getWeight()));
					}
				}
			}
		}

		return 0;
	}

	class Node implements Comparator<Node> {
		private int v;
		private double weight;

		Node(int v, double weight) {
			this.v = v;
			this.weight = weight;
		}

		Node() {

		}

		int getV() {
			return v;
		}

		double getWeight() {
			return weight;
		}

		@Override
		public int compare(Node n1, Node n2) { // max heap
			if (n1.weight < n2.weight) {
				return 1;
			}
			if (n1.weight > n2.weight) {
				return -1;
			}
			return 0;
		}
	}

	public static void main(String[] args) {
		int[][] edges = { { 0, 1 }, { 1, 2 }, { 0, 2 } };
		int n = 3;
		double[] succProb = { 0.5, 0.5, 0.3 };
		int start = 0;
		int end = 2;

		PathWithMaximumProbability max = new PathWithMaximumProbability();
		max.maxProbability(n, edges, succProb, start, end);
	}

}
