package com.practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class BinaryTreePractice {



	public void printRightView(Node root) {
		List<Integer> result = new ArrayList<>();
		helperView(root, 0, result);
	}

	private void helperView(Node node, int level, List<Integer> result) {
		if(node == null) {
			return;
		}
		if(level == result.size()) {
			result.add(node.val);
		}
		helperView(node.right, level+1, result);
		helperView(node.left, level+1, result);
	}

	public void topView() {
		Map<Integer, Pair<Integer, Integer>> map = new TreeMap<>();
	}

	public static void printTop(Node root, int dist, int level, Map<Integer, Pair<Integer, Integer>> map) {
		if(root == null) {
			return;
		}

		if(!map.containsKey(dist) || map.get(dist).second > level) {
			map.put(dist, Pair.of(root.val, level));
		}

		printTop(root.left, dist-1, level+1, map);
		printTop(root.right, dist+1, level+1, map);
	}


	public ArrayList<Integer> topViewIterative(Node root) {
		ArrayList<Integer> ans = new ArrayList<>();
		if(root == null) {
			return ans;
		}
		Map<Integer, Integer> map = new TreeMap<>();
		Queue<Pair<Node, Integer>> q = new LinkedList<Pair<Node, Integer>>();
		q.add(new Pair(root, 0));

		while(!q.isEmpty()) {
			Pair<Node, Integer> it = q.remove();
			int hd = it.second;
			Node temp = it.first;

			if(map.get(hd) == null) {
				map.put(hd, temp.val);
			}

			if(temp.left != null) {
				q.add(new Pair(temp.left, hd - 1));
			}
			if(temp.right != null) {
				q.add(new Pair(temp.right, hd + 1));
			}
		}
		for (Map.Entry<Integer,Integer> entry : map.entrySet()) {
			ans.add(entry.getValue());
		}
		return ans;
	}

	public ArrayList<Integer> bottomViewIterative(Node root) {
		ArrayList<Integer> ans = new ArrayList<>();
		if(root == null) {
			return ans;
		}
		Map<Integer, Integer> map = new TreeMap<>();
		Queue<Pair<Node, Integer>> q = new LinkedList<Pair<Node, Integer>>();
		q.add(new Pair<Node, Integer>(root, 0));

		while(!q.isEmpty()) {
			Pair<Node, Integer> it = q.remove();
			int cols = it.second;
			Node node = it.first;

			map.put(cols, node.val);

			if(node.left != null) {
				q.add(new Pair<Node, Integer>(node.left, cols - 1));
			}
			if(node.right != null) {

				q.add(new Pair<Node, Integer>(node.right, cols + 1));
			}
		}
		for (Map.Entry<Integer,Integer> entry : map.entrySet()) {
			ans.add(entry.getValue());
		}


		return ans;
	}


	public void leftBoundary(Node root, List<Integer> result) {
		Node curr = root.left;

		while(curr != null) {
			if(!curr.isLeaf()) {
				result.add(curr.val);
			}
			if(curr.left != null) {
				curr = curr.left;
			} else {
				curr = curr.right;
			}
		}

	}

	public void rightBoundary(Node root, List<Integer> result) {
		Node curr = root.right;

		List<Integer> temp = new ArrayList<>();

		while(curr != null) {
			if(!curr.isLeaf()) {
				temp.add(curr.val);
			}
			if(curr.right != null) {
				curr = curr.right;
			} else {
				curr = curr.left;
			}
		}

		for(int i= temp.size()-1; i >= 0; i--) {
			result.add(temp.get(i));
		}
	}

	public void sortThreeNumbers(int[] arr) {

		// {0, 1, 2, 0, 1, 1, 2, 2}

		int low = 0;
		int mid = 0;
		int high = arr.length-1;

		int first = 0;
		int second = 1;

		while(mid <= high) {
			int midV = arr[mid];

			if(first == midV) {
				//swap(low, mid, arr);
				low++;
				mid++;
			} else if(second == midV) {
				mid++;
			} else {
				//swap(high, mid, arr);
				high--;
			}
		}
	}

	public String runLengthEncoding(String s) {

		char[] chars = s.toCharArray();
		char prev = chars[0];
		int counter = 1;

		StringBuilder sb = new StringBuilder();

		for(int i= 1; i < chars.length; i++) {
			char curr = chars[i];

			if(curr != prev || counter == 9) {
				sb.append(counter);
				sb.append(prev);
				counter = 1;

			} else {
				counter++;
			}
			prev = curr;


		}

		sb.append(counter);
		sb.append(s.charAt(s.length()-1));

		return sb.toString();
	}


	static class Pair<U, V> {
		public final U first; // first field of a pair
		public final V second; // second field of a pair

		// Constructs a new Pair with specified values
		public Pair(U first, V second) {
			this.first = first;
			this.second = second;
		}

		// Factory method for creating a Typed Pair immutable instance
		public static <U, V> Pair<U, V> of(U a, V b) {
			// calls private constructor
			return new Pair<>(a, b);
		}
	}

	public Node lca(Node root, Node a, Node b) {
		if(root == null || root == a || root == b) {
			return root;
		}
		Node left = lca(root.left, a, b);
		Node right = lca(root.right, a, b);

		if(left == null) {
			return right;
		} else if(right == null) {
			return left;
		} else {
			return root;
		}
	}

	public Node lcaBst(Node root, Node a, Node b) {
		while(root != null) {
			if(root.val > a.val && root.val > b.val) {
				root = root.left;
			}
			else if(root.val < a.val && root.val < b.val) {
				root = root.left;
			} else {
				return root;
			}
		}
		return null;
	}

	public ArrayList<Integer> bottomViewIterative2(Node root) {
		ArrayList<Integer> list = new ArrayList<>();

		Map<Integer, Integer> map = new HashMap<>();
		Queue<Pair<Node, Integer>> q = new LinkedList<>();
		q.offer(new Pair<>(root, 0));


		while(!q.isEmpty()) {

			Pair<Node,Integer> p  =q.poll();
			Node node = p.first;
			Integer cols = p.second;

			map.put(cols, node.val);

			if(node.left != null) {
				q.offer(new Pair<>(node.left, cols-1));
			}

			if(node.right != null) {
				q.offer(new Pair<>(node.right, cols+1));
			}

		}

		for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
			list.add(entry.getValue());
		}


		return list;

	}
}

class Node {
	int val;
	Node left, right;

	Node(int val) {
		this.val = val;
		left = null;
		right = null;
	}

	boolean isLeaf() {
		return this.left == null && this.right == null;
	}
}

class Pair<U, V> {
	public final U first; // first field of a pair
	public final V second; // second field of a pair

	// Constructs a new Pair with specified values
	public Pair(U first, V second) {
		this.first = first;
		this.second = second;
	}

	// Factory method for creating a Typed Pair immutable instance
	public static <U, V> Pair<U, V> of(U a, V b) {
		// calls private constructor
		return new Pair<>(a, b);
	}
}
