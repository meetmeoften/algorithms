package com.algoexpert.binarytree.view;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class BottomView {

	public ArrayList<Integer> bottomView(Node root) {
		ArrayList<Integer> ans = new ArrayList<>();
		if (root == null) {
			return ans;
		}
		Map<Integer, Integer> map = new TreeMap<>();
		Queue<Node> q = new LinkedList<Node>();
		root.hd = 0;
		q.add(root);
		while (!q.isEmpty()) {
			Node temp = q.remove();
			int hd = temp.hd;
			map.put(hd, temp.data);
			if (temp.left != null) {
				temp.left.hd = hd - 1;
				q.add(temp.left);
			}
			if (temp.right != null) {
				temp.right.hd = hd + 1;
				q.add(temp.right);
			}
		}

		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			ans.add(entry.getValue());
		}
		return ans;

	}

	public class pair {
		int depth;
		int data;

		pair(int depth, int data) {
			this.depth = depth;
			this.data = data;
		}
	}

	Map<Integer, pair> map = new TreeMap<>();
	private Node root = null;
	private int size = 0;

	// for bottom view
	private void verticallevelorder(Node parent, int ht, int depth) {
		if (parent == null) {
			return;
		}
		if (map.containsKey(ht)) {
			pair mp = map.get(ht);
			if (mp.depth < depth) { // This has to be changed for top View
				map.replace(ht, new pair(depth, parent.data));
			}
		} else {
			map.put(ht, new pair(depth, parent.data));
		}
		verticallevelorder(parent.left, ht - 1, depth + 1);
		verticallevelorder(parent.right, ht + 1, depth + 1);

	}

	public void bottomview() {
		bottomview(this.root);
	}

	public void verticallevelorder() {
		verticallevelorder(this.root, 0, 0);
	}

	private void bottomview(Node parent) {
		this.verticallevelorder();
		for (Map.Entry<Integer, pair> mp : map.entrySet()) {
			System.out.println(mp.getValue().data);
		}

	}

}
