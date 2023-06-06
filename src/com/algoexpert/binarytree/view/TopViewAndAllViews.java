package com.algoexpert.binarytree.view;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class TopViewAndAllViews {

	public static ArrayList<Integer> topView(Node root) {
		ArrayList<Integer> ans = new ArrayList<>();
		if (root == null) {
			return ans;
		}
		Map<Integer, Integer> map = new TreeMap<>();
		Queue<Pair> q = new LinkedList<Pair>();
		q.add(new Pair(root, 0));

		while (!q.isEmpty()) {
			Pair it = q.remove();
			int hd = it.hd;
			Node temp = it.node;

			if (map.get(hd) == null) {
				map.put(hd, temp.data);
			}

			if (temp.left != null) {
				q.add(new Pair(temp.left, hd - 1));
			}

			if (temp.right != null) {
				q.add(new Pair(temp.right, hd + 1));
			}
		}

		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			ans.add(entry.getValue());
		}
		return ans;

	}

	public static ArrayList<Integer> bottomView(Node root) {
		ArrayList<Integer> ans = new ArrayList<>();
		if (root == null) {
			return ans;
		}
		Map<Integer, Integer> map = new TreeMap<>();
		Queue<Pair> q = new LinkedList<Pair>();
		q.add(new Pair(root, 0));

		while (!q.isEmpty()) {
			Pair it = q.remove();
			int hd = it.hd;
			Node temp = it.node;

			map.put(hd, temp.data);

			if (temp.left != null) {
				q.add(new Pair(temp.left, hd - 1));
			}

			if (temp.right != null) {
				q.add(new Pair(temp.right, hd + 1));
			}
		}

		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			ans.add(entry.getValue());
		}
		return ans;

	}

	public static List<List<Integer>> verticalOrder(Node root) {
		List<List<Integer>> ans = new ArrayList<>();
		if (root == null) {
			return ans;
		}

		Map<Integer, List<Integer>> map = new TreeMap<>();
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(root, 0));
		int min = 0;
		int max = 0;
		while(!q.isEmpty()) {
			Pair it = q.poll();
			int hd = it.hd;
			Node node = it.node;

			if(map.get(hd) == null) {
				map.put(hd, new ArrayList<>());
			}
			map.get(hd).add(node.data);

			if(node.left != null) {
				q.add(new Pair(node.left, hd-1));
				min = Math.min(min, hd-1);
			}

			if(node.right != null) {
				q.add(new Pair(node.right, hd + 1));
				max = Math.max(max,  hd + 1);
			}
		}

		for (int i = min; i <= max; i++) {
			ans.add(map.get(i));
		}

		return ans;
	}

	public static class Pair {
		int depth;
		Node node;
		int hd;

		Pair(Node node, int hd) {
			this.node = node;
			this.hd = hd;
		}
	}

	public static void main(String[] args) {
		Node node = Node.createBinaryTree12345();
		topView(node);
		bottomView(node);
		verticalOrder(node);
	}

}
